package com.pams.jhgl.jhbz.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.headray.framework.services.db.SQLParser;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.services.function.StringToolKit;
import com.pams.dao.PlanDao;
import com.pams.dao.PlanModelDao;
import com.pams.entity.Plan;
import com.pams.entity.PlanModel;
import com.ray.app.query.generator.GeneratorService;
import com.ray.app.workflow.spec.GlobalConstants;

@Component
@Transactional
public class PlanService
{
	public final static String _tableid = "Plan";

	@Autowired
	GeneratorService generatorService;

	@Autowired
	private PlanModelDao planmodelDao;

	@Autowired
	private PlanDao planDao;

	// 查询我编制的计划
	public String get_browsecreate_sql(Map map) throws Exception
	{
		String userid = (String)map.get(GlobalConstants.swap_coperatorid);
		String loginname = (String)map.get(GlobalConstants.swap_coperatorloginname);
		String username = (String)map.get(GlobalConstants.swap_coperatorcname);		
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select * from t_app_plan ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and supid = 'R0' ").append("\n");
		sql.append("    and creater = " + SQLParser.charValue(loginname));
		sql.append("  order by createtime desc ").append("\n");

		return sql.toString();
	}
	
	// 查询我负责的计划
	public String get_browsehead_sql(Map map) throws Exception
	{
		String userid = (String)map.get(GlobalConstants.swap_coperatorid);
		String loginname = (String)map.get(GlobalConstants.swap_coperatorloginname);
		String username = (String)map.get(GlobalConstants.swap_coperatorcname);		
		
		StringBuffer sql = new StringBuffer();

		sql.append(" select * from t_app_plan ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and supid = 'R0' ").append("\n");
		sql.append("    and header = " + SQLParser.charValue(loginname));
		sql.append("  order by createtime desc ").append("\n");

		return sql.toString();
	}	

	// 查询计划模板
	public String get_selectmodel_sql(Map map) throws Exception
	{
		StringBuffer sql = new StringBuffer();

		sql.append(" select * from t_app_planmodel where 1 = 1 and unittype = 'MODEL' ").append("\n");

		return sql.toString();
	}

	public List childplan(Map map) throws RuntimeException
	{
		String supid = (String) map.get("supid");

		StringBuffer sql = new StringBuffer();
		sql.append(" from Plan ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append(" and supid = " + SQLParser.charValue(supid));
		sql.append(" order by internal ").append("\n");

		List task = planDao.find(sql.toString());

		return task;
	}

	@Transactional
	public String create(DynamicObject obj, DynamicObject swapFlow) throws Exception
	{
		Plan plan = createplan(obj, swapFlow);
		String planmodelid = plan.getPlanmodelid();
		String id = plan.getId();

		// 根据计划模板生成相关子计划
		if (!StringToolKit.isBlank(planmodelid))
		{
			DynamicObject aobj = new DynamicObject();
			aobj.setAttr("planmodelid", planmodelid);
			aobj.setAttr("supid", id);
			createsubplan(aobj, swapFlow);
		}

		return id;
	}

	@Transactional
	public Plan createplan(DynamicObject obj, DynamicObject swapFlow) throws Exception
	{
		String supid = obj.getFormatAttr("supid"); // 上级计划标识
		String planmodelid = obj.getFormatAttr("planmodelid");
		String title = obj.getFormatAttr("title");
		String memo = obj.getFormatAttr("memo");
		String creater = obj.getFormatAttr("creater");
		String creatername = obj.getFormatAttr("creatername");

		String tlevel = "1";
		String islast = "0";

		String internal = gen_internal(supid);

		Plan supplan = new Plan();
		PlanModel planmodel = planmodelDao.get(planmodelid);

		if (!"R0".equals(supid))
		{
			supplan = planDao.get(supid);
			supplan.setIslast("1"); // 上级计划不再为叶子节点
			planDao.save(supplan);
			tlevel = String.valueOf(Integer.parseInt(supplan.getTlevel()) + 1);
		}

		// 创建业务数据
		Plan plan = new Plan();
		String cno = gen_cno();
		Timestamp nowtime = new Timestamp(System.currentTimeMillis());
		plan.setSupid(supid);
		plan.setPlanmodelid(planmodelid);
		plan.setTitle(title);
		plan.setCno(cno);
		plan.setTlevel(tlevel);
		plan.setIslast(islast);
		plan.setInternal(internal);
		plan.setCreater(creater);
		plan.setCreatername(creatername);
		plan.setCreatetime(nowtime);

		plan.setPeriodnum(planmodel.getPeriodnum());
		plan.setPeriodunit(planmodel.getPeriodunit());
		plan.setPlanperiodnum(planmodel.getPeriodnum());
		plan.setPlanperiodunit(planmodel.getPeriodunit());
		plan.setFlowdefid(planmodel.getFlowdefid());
		plan.setActdefid(planmodel.getActdefid());

		Calendar cal = new GregorianCalendar();

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Date pdate = sf.parse(sf.format(cal.getTime()));
		Timestamp planbegintime = new Timestamp(pdate.getTime() + (24*60*60*1000));
		Timestamp planendtime = new Timestamp(planbegintime.getTime() + (24*60*60*1000));
		
		plan.setPlanbegintime(planbegintime);
		plan.setPlanendtime(planendtime);

		planDao.save(plan);

		return plan;
	}

	@Transactional
	public void createsubplan(DynamicObject obj, DynamicObject swapFlow) throws Exception
	{
		String planmodelid = obj.getFormatAttr("planmodelid");
		String supid = obj.getFormatAttr("supid");

		List planmodels = planmodelDao.find(" from PlanModel where 1 = 1 and supmodelid = " + SQLParser.charValue(planmodelid));
		// 根据模板生成对应计划
		for (int i = 0; i < planmodels.size(); i++)
		{
			PlanModel planmodel = (PlanModel) planmodels.get(i);
			DynamicObject aobj = new DynamicObject();
			aobj.setAttr("supid", supid);
			aobj.setAttr("planmodelid", planmodel.getId());
			aobj.setAttr("title", planmodel.getCname());
			aobj.setAttr("memo", "");

			createplan(aobj, swapFlow);
		}
	}

	@Transactional
	public Plan locate(String id) throws Exception
	{
		return planDao.get(id);
	}

	// 是否可读
	public boolean isread(DynamicObject map) throws Exception
	{
		boolean sign = true;
		return sign;
	}

	// 是否可写（修改）
	public boolean isedit(DynamicObject map) throws Exception
	{
		boolean sign = true;
		return sign;
	}

	// 是否可保存（修改）
	public boolean issave(DynamicObject map) throws Exception
	{
		boolean sign = true;
		return sign;
	}

	// 是否可删除
	public boolean isdelete(DynamicObject map) throws Exception
	{
		boolean sign = true;
		return sign;
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public String gen_cno()
	{
		// 生成编号
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		String sysdate = sf.format(date);
		String csql = " select substring(max(cno),length(max(cno))-3, 4) as cno from Plan where to_char(createtime,'yyyy-mm-dd') = to_char(to_date('" + sysdate + "', 'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd')";
		String express = "$yy$mm$dd####";

		Map map = new HashMap();
		map.put("csql", csql);
		map.put("express", express);

		return generatorService.getNextValue(map);
	}

	public String gen_internal(String supid) throws Exception
	{
		// 查询上级内部编码
		String internal = new String();
		if ("R0".equals(supid))
		{
			internal = "0000";
		}
		else
		{
			Plan plan = planDao.get(supid);
			internal = plan.getInternal();
		}

		Map map = new HashMap();
		map.put("field_names", new String[]
		{ "internal", "supid" });
		map.put("field_values", new String[]
		{ internal, supid });

		String csql = " select substring(max(internal),length(max(internal))-3, 4) as internal from Plan where supid = :supid";
		String express = "$internal####";

		map.put("csql", csql);
		map.put("express", express);

		return generatorService.getNextValue(map);
	}
	
	public Plan get(String id)
	{
		return planDao.get(id);
	}

}
