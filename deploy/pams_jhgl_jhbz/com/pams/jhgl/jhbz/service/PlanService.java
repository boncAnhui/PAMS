package com.pams.jhgl.jhbz.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.headray.framework.services.db.SQLParser;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.pams.dao.PlanDao;
import com.pams.dao.PlanModelDao;
import com.pams.entity.Plan;
import com.ray.app.query.generator.GeneratorService;

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
		StringBuffer sql = new StringBuffer();

		sql.append(" select * from t_app_plan ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and supid = 'R0' ").append("\n");
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
		
	@Transactional
	public String create(DynamicObject obj, DynamicObject swapFlow) throws Exception
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
		if (!"R0".equals(supid))
		{
	
			supplan = planDao.get(supid);
			supplan.setIslast("1"); //上级计划不再为叶子节点
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
		
		planDao.save(plan);
		
		String id = plan.getId();
		return id;
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


}
