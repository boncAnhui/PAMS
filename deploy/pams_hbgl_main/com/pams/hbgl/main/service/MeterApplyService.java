package com.pams.hbgl.main.service;

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
import com.pams.dao.MeterApplyDao;
import com.pams.entity.MeterApply;
import com.pams.entity.Relay;
import com.ray.app.query.generator.GeneratorService;
import com.ray.app.workflow.enginee.WorkFlowEngine;
import com.ray.app.workflow.spec.DBFieldConstants;
import com.ray.app.workflow.spec.GlobalConstants;
import com.ray.app.workflow.ui.service.UIService;
import com.ray.xj.sgcc.irm.system.author.userrole.dao.UserRoleDao;

@Component
@Transactional
public class MeterApplyService
{
	@Autowired
	GeneratorService generatorService;

	@Autowired
	private WorkFlowEngine workFlowEngine;

	@Autowired
	private UIService uIService;

	@Autowired
	private MeterApplyDao meterapplyDao;
	
	@Autowired
	private UserRoleDao userRoleDao;

	// 查询待办记录
	public String get_browsewait_sql(Map map) throws Exception
	{
		StringBuffer sql = new StringBuffer();

		sql.append(" select bv.id, bv.bt, bv.zzxqmc,").append("\n");
		sql.append(uIService.get_browsewait_field(map)).append("\n");
		sql.append(" from t_app_meterapply bv, " + uIService.get_browsewait_table(map)).append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append(uIService.get_browsewait_where(map)).append("\n");
		sql.append(" order by ract.createtime desc ").append("\n");		
		return sql.toString();
	}
	
	// 查询已办记录
	public String get_browsehandle_sql(Map map) throws Exception
	{
		StringBuffer sql = new StringBuffer();

		sql.append(" select bv.id, bv.bt, bv.zzxqmc,").append("\n");
		sql.append(uIService.get_browsehandle_field(map)).append("\n");
		sql.append(" from t_app_meterapply bv, " + uIService.get_browsehandle_table(map)).append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append(uIService.get_browsehandle_where(map)).append("\n");
		sql.append(" order by ract.createtime desc ").append("\n");	
		return sql.toString();
	}
	
	// 查询全体记录（当前流程）
	public String get_browsegroupall_sql(Map map) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" select bv.id, bv.bt, bv.zzxqmc,").append("\n");
		sql.append(uIService.get_browsegroupall_field(map)).append("\n");
		sql.append(" from t_app_meterapply bv, " + uIService.get_browsegroupall_table(map));
		sql.append(" where 1 = 1 ").append("\n");
		sql.append(uIService.get_browsegroupall_where(map));
		sql.append(" order by ract.createtime desc ").append("\n");	
		return sql.toString();
	}
	
	// 查询一级子流程全体记录
	public String get_browsegroupsall_sql(Map map) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" select bv.id, bv.bt, bv.zzxqmc,").append("\n");
		sql.append(uIService.get_browsegroupsall_field(map)).append("\n");
		sql.append(" from t_app_meterapply bv, " + uIService.get_browsegroupsall_table(map));
		sql.append(" where 1 = 1 ").append("\n");
		sql.append(uIService.get_browsegroupsall_where(map));
		sql.append(" order by ract.createtime desc ").append("\n");	
		return sql.toString();
	}
	
	// 查询转办记录
	public String get_browserelay_sql(Map map) throws Exception
	{
		String flowsno = (String)map.get("flowsno");
		
		StringBuffer sql = new StringBuffer();

		sql.append(" select * from t_app_relay ");
		sql.append(" where 1 = 1 ");
		sql.append(" and isuse = 'Y' ");
		sql.append(" and state = '待处理' ");
		sql.append(" and flowsno = " + SQLParser.charValue(flowsno));

		return sql.toString();
	}
	
	@Transactional
	public String create(MeterApply meterapply, DynamicObject swapFlow) throws Exception
	{
		// 创建业务数据
		String cno = gen_cno();
		meterapplyDao.save(meterapply);

		// 创建流程数据
		String flowdefid = swapFlow.getFormatAttr(GlobalConstants.swap_flowdefid);
		String priority = "1";
		String dataid = meterapply.getId();
		String tableid = swapFlow.getFormatAttr(GlobalConstants.swap_tableid);
		String creater = swapFlow.getFormatAttr(GlobalConstants.swap_coperatorid);
		String creatercname = swapFlow.getFormatAttr(GlobalConstants.swap_coperatorcname);

		String runactkey = workFlowEngine.getFlowManager().create(cno, flowdefid, priority, dataid, tableid, creater, creatercname);

		return runactkey;
	}
	
	@Transactional
	public String createflow(DynamicObject swapFlow) throws Exception
	{
		// 创建流程数据
		String flowdefid = swapFlow.getFormatAttr(GlobalConstants.swap_flowdefid);
		String priority = "1";
		String dataid = swapFlow.getFormatAttr(GlobalConstants.swap_dataid);
		String tableid = swapFlow.getFormatAttr(GlobalConstants.swap_tableid);
		String creater = swapFlow.getFormatAttr(GlobalConstants.swap_coperatorid);
		String creatercname = swapFlow.getFormatAttr(GlobalConstants.swap_coperatorcname);
		String workname = swapFlow.getFormatAttr(GlobalConstants.swap_workname);

		String runactkey = workFlowEngine.getFlowManager().create(workname, flowdefid, priority, dataid, tableid, creater, creatercname);
		
		return runactkey;
	}
	
	@Transactional
	public String relayto(Relay relay, DynamicObject swapFlow) throws Exception
	{
		relay.setState("已处理");
		String runactkey = createflow(swapFlow);
		return runactkey;
	}	

	@Transactional
	public MeterApply locate(String id) throws Exception
	{
		return meterapplyDao.get(id);
	}

	@Transactional
	public void update(MeterApply meterapply) throws Exception
	{
		meterapplyDao.save(meterapply);
	}

	@Transactional
	public void delete(String id) throws Exception
	{
		// 删除流程信息
		workFlowEngine.getFlowManager().delete("MeterApply", id);
		// 删除数据
		meterapplyDao.delete(id);
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public String gen_cno()
	{
		// 生成编号
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		String sysdate = sf.format(date);
		String csql = " select substring(max(cno),length(max(cno))-3, 4) as cno from MeterApply where to_char(applytime,'yyyy-mm-dd') = to_char(to_date('" + sysdate + "', 'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd')";
		String express = "$yy$mm$dd####";

		Map map = new HashMap();
		map.put("csql", csql);
		map.put("express", express);

		return generatorService.getNextValue(map);
	}
	
	// 当前用户是否为指定的角色
	public boolean isarole(String loginname, String rolename)
	{
		boolean sign = false;

		long num = 0;
		num = (Long) userRoleDao.findUnique(" select count(*) from UserRole a where 1 = 1 and a.rname = ? and userid = ? ", rolename, loginname);

		if (num > 0)
		{
			sign = true;
			return sign;
		}

		return sign;
	}
	
	// 是否可读
	public boolean isread(DynamicObject map)
	{
		// 读取权限后期可优化为由系统权限或工作流平台进行设置，权限控制将更为灵活；

		boolean sign = false;

		// 业务数据标识
		// 用户名
		String dataid = (String) map.get(GlobalConstants.swap_dataid);
		String loginname = (String) map.get(GlobalConstants.swap_coperatorloginname);

		long num = 0;

		// 系统管理员具有权限
		if ("admin".equals(loginname))
		{
			sign = true;
			return sign;
		}

		// 应用管理员具有权限
		if (isarole(loginname, "SYSTEM"))
		{
			sign = true;
			return sign;
		}

		// 信息收集管理员具有权限
		if (isarole(loginname, "HBGL_XXSJ_GLY"))
		{
			sign = true;
			return sign;
		}

		// 信息收集用户具有权限
		if (isarole(loginname, "HBGL_XXSJ_YH"))
		{
			sign = true;
			return sign;
		}

		return sign;
	}
	
	// 是否可写（修改）
	public boolean isedit(DynamicObject map) throws Exception
	{
		boolean sign = workFlowEngine.getDemandManager().isEdit(map);
		return sign;
	}
	
	// 能否保存
	public boolean issave(DynamicObject map) throws Exception
	{
		boolean sign = workFlowEngine.getDemandManager().isEdit(map);
		return sign;
	}
	
	// 浏览视图是否出现删除多条
	public boolean isdeletes(DynamicObject map) throws Exception
	{
		boolean sign = false;
		String loginname = (String) map.get(GlobalConstants.swap_coperatorloginname);

		// 系统管理员具有权限
		if ("admin".equals(loginname))
		{
			sign = true;
			return sign;
		}

		// 应用管理员具有权限
		if (isarole(loginname, "SYSTEM"))
		{
			sign = true;
			return sign;
		}

		// 信息收集管理员具有该功能；
		if (isarole(loginname, "HBGL_XXSJ_GLY"))
		{
			sign = true;
			return sign;
		}

		return sign;
	}
	
	// 是否可删
	public boolean isdelete(DynamicObject map) throws Exception
	{
		boolean sign = false;

		// 业务数据标识
		// 用户名
		String dataid = map.getFormatAttr(GlobalConstants.swap_dataid);
		String loginname = map.getFormatAttr(GlobalConstants.swap_coperatorloginname);
		String userid = map.getFormatAttr(GlobalConstants.swap_coperatorid);
		String tableid = "MeterApply";
		String runactkey = (String) map.get("runactkey");
		String actdefid = workFlowEngine.getActManager().getDao_ract().findById(runactkey, tableid).getFormatAttr("actdefid");
		String ctype = "PERSON";

		// 检查流程是否终止或结束 不允许删除,终止的视为流转过
		String state = workFlowEngine.getDemandManager().getRFlow(tableid, dataid).getFormatAttr("state");

		// 系统管理员具有权限
		if ("admin".equals(loginname))
		{
			sign = true;
			return sign;
		}

		// 应用管理员具有权限
		if (isarole(loginname, "SYSTEM"))
		{
			sign = true;
			return sign;
		}

		// 信息收集管理员具有权限
		if (isarole(loginname, "HBGL_XXSJ_GLY"))
		{
			sign = true;
			return sign;
		}

		if (DBFieldConstants.RFlOW_STATE_COMPLETED.equals(state) || DBFieldConstants.RFlOW_STATE_TERMINATED.equals(state))
		{
			return sign;
		}
		// 判断流程是否允许删除
		boolean enabledelete = workFlowEngine.getDemandManager().checkforwarded("HBGL_XXSJ", dataid);// 值真表示转未发过，值为false表示转发过

		if (enabledelete)
		{
			sign = true;
			return sign;
		}

		return sign;
	}
	
	// 是否可签收
	public boolean isapply(DynamicObject map) throws Exception
	{
		boolean sign = false;
		sign = workFlowEngine.getDemandManager().isApply(map);
		return sign;
	}

	// 是否可转发
	public boolean isforward(DynamicObject map) throws Exception
	{
		boolean sign = false;
		String loginname = map.getFormatAttr(GlobalConstants.swap_coperatorloginname);
		sign = workFlowEngine.getDemandManager().isForward(map);

		return sign;
	}
	
	// 是否可收回
	public boolean iscallback(DynamicObject map) throws Exception
	{
		boolean sign = false;

		sign = workFlowEngine.getDemandManager().isCallBack(map);

		return sign;
	}

	// 是否可退回
	public boolean isbackward(DynamicObject map) throws Exception
	{
		boolean sign = false;

		sign = workFlowEngine.getDemandManager().isBackward(map);

		return sign;
	}
	
	public MeterApply get(String id) throws Exception
	{
		return meterapplyDao.get(id);
	}

	public GeneratorService getGeneratorService()
	{
		return generatorService;
	}

	public void setGeneratorService(GeneratorService generatorService)
	{
		this.generatorService = generatorService;
	}

	public WorkFlowEngine getWorkFlowEngine()
	{
		return workFlowEngine;
	}

	public void setWorkFlowEngine(WorkFlowEngine workFlowEngine)
	{
		this.workFlowEngine = workFlowEngine;
	}

	public MeterApplyDao getMeterapplyDao()
	{
		return meterapplyDao;
	}

	public void setMeterapplyDao(MeterApplyDao meterapplyDao)
	{
		this.meterapplyDao = meterapplyDao;
	}

	public UIService getuIService()
	{
		return uIService;
	}

	public void setuIService(UIService uIService)
	{
		this.uIService = uIService;
	}

}
