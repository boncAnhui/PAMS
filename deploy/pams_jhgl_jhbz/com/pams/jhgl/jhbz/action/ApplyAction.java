package com.pams.jhgl.jhbz.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.action.ActionSessionHelper;
import com.blue.ssh.core.action.SimpleAction;
import com.blue.ssh.core.orm.Page;
import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.common.generator.TimeGenerator;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.pams.entity.Plan;
import com.pams.jhgl.jhbz.service.PlanService;
import com.pams.jhgl.jhmb.service.PlanModelService;
import com.ray.app.query.action.QueryActionHelper;
import com.ray.app.query.entity.Search;
import com.ray.app.query.service.QueryService;
import com.ray.app.workflow.enginee.WorkFlowEngine;
import com.ray.app.workflow.spec.GlobalConstants;
import com.ray.app.workflow.ui.action.FormHelper;
import com.ray.xj.sgcc.irm.system.organ.user.service.UserService;

public class ApplyAction extends SimpleAction
{
	private String _searchname;

	@Autowired
	private QueryService queryService;

	@Autowired
	private WorkFlowEngine workFlowEngine;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PlanModelService planmodelService;
	
	@Autowired
	private PlanService planService;
	
	public String mainframe() throws Exception
	{
		return "mainframe";
	}
	
	// 查询编制的计划
	public String browsecreate() throws Exception
	{
		QueryActionHelper helper = new QueryActionHelper();

		arg.putAll(helper.mockArg(_searchname, queryService));

		Map amap = new HashMap();
		amap = (HashMap) ((HashMap) arg).clone();

		Search search = (Search) BeanUtils.cloneBean(queryService.findUniqueByOfSearch("searchname", _searchname));
		search.setMysql(planService.get_browsecreate_sql(amap));

		Page page = helper.mockJdbcPage(search, queryService);
		Map vo = helper.mockVO(_searchname, queryService);

		data.put("vo", vo);
		data.put("page", page);

		return "browsecreate";
	}
	
	// 浏览子任务
	public String treebrowse() throws Exception
	{
		String username = ActionSessionHelper._get_username();
		String loginname = ActionSessionHelper._get_loginname();
		String userid = ActionSessionHelper._get_userid();
		String supid = Struts2Utils.getRequest().getParameter("supid");
		Map map = new DynamicObject();
		map = (HashMap) ((HashMap) arg).clone();
		map.put("loginname", loginname);
		map.put("swap_coperatorid", userid);
		map.put("supid", supid);

		List plans = planService.childplan(map);

		data.put("plans", plans);

		return "treebrowse";
	}	
	
	public String input() throws Exception
	{
		String loginname = ActionSessionHelper._get_loginname();
		String username = ActionSessionHelper._get_username();
		String userid = ActionSessionHelper._get_userid();
		String deptid = ActionSessionHelper._get_deptid();
		String deptname = ActionSessionHelper._get_deptname();
		String deptinternal = ActionSessionHelper._get_dept_internal();
		
		// 岗位信息
		String position = userService.getUserByLoginName(loginname).getPosition();
		
		String supid = Struts2Utils.getRequest().getParameter("supid");
		String createtime = TimeGenerator.getDateStr();
		
		data.put("loginname", loginname);
		data.put("username", username);
		data.put("deptid", deptid);
		data.put("deptname", deptname);
		data.put("createtime", createtime);

		arg.put("supid", supid);
		return "input";
	}
	
	// 创建
	public String insert() throws Exception
	{
		String userid = ActionSessionHelper._get_userid();
		String loginname = ActionSessionHelper._get_loginname();
		String username = ActionSessionHelper._get_username();
		String usertype = "PERSON";
		String deptid = ActionSessionHelper._get_deptid();
		String deptname = ActionSessionHelper._get_deptname();
		String orgid = ActionSessionHelper._get_orgid();
		String orgname = ActionSessionHelper._get_orgname();

		String tableid = "Plan";
		
		String planmodelid =  Struts2Utils.getRequest().getParameter("planmodelid"); //计划模板标识
		String supid = Struts2Utils.getRequest().getParameter("supid");
		String title = Struts2Utils.getRequest().getParameter("title");
		String memo = Struts2Utils.getRequest().getParameter("memo");

		DynamicObject obj = new DynamicObject();
		
		obj.setAttr("planmodelid", planmodelid);
		obj.setAttr("supid", supid);
		obj.setAttr("title", title);
		obj.setAttr("memo", memo);

		obj.setAttr("loginname", loginname);
		obj.setAttr("username", username);

		DynamicObject swapFlow = new DynamicObject();
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_coperatorid, userid);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_coperatorloginname, loginname);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_coperatorcname, username);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_coperatordeptid, deptid);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_coperatordeptcname, deptname);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_coperatororgid, orgid);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_coperatororgcname, orgname);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_tableid, tableid);

		String id = planService.create(obj, swapFlow);

		arg.put("id", id);

		return "insert";
	}	
	
	public String readpageframe() throws Exception
	{

		String id = Struts2Utils.getRequest().getParameter("id");

		// 判断是否可以进入基本信息编辑页面
		DynamicObject flowobj = new DynamicObject();
		flowobj.setAttr(GlobalConstants.swap_coperatorid, ActionSessionHelper._get_userid());
		flowobj.setAttr(GlobalConstants.swap_coperatorcname, ActionSessionHelper._get_username());
		flowobj.setAttr(GlobalConstants.swap_coperatorloginname, ActionSessionHelper._get_loginname());

		flowobj.setAttr(GlobalConstants.swap_coperatordeptid, ActionSessionHelper._get_deptid());
		flowobj.setAttr(GlobalConstants.swap_coperatordeptcname, ActionSessionHelper._get_deptname());

		flowobj.setAttr(GlobalConstants.swap_tableid, "Plan");
		flowobj.setAttr(GlobalConstants.swap_dataid, id);
		boolean isedit = planService.isedit(flowobj); // 是否修改页面
		
		arg.put("id", id);

		if (isedit)
		{
			return "tolocateframe";
		}
		else
		{
			return "readpageframe";
		}
	}
	
	public String readpage() throws Exception
	{
		String id = Struts2Utils.getRequest().getParameter("id");
		Plan plan = planService.locate(id);

		data.put("plan", plan);

		return "readpage";
	}
	
	public String locateframe() throws Exception
	{
		String id = Struts2Utils.getRequest().getParameter("id");
		String isinput = Struts2Utils.getRequest().getParameter("isinput");
		arg.put("id", id);
		arg.put("isinput", isinput);
		return "locateframe";
	}	
	
	// 查看修改
	public String locate() throws Exception
	{
		String id = Struts2Utils.getRequest().getParameter("id");
		// 查询业务数据
		Plan plan = planService.locate(id);

		// 权限设置
		set_author();
		
		data.put("plan", plan);
		arg.put("id", id);

		return "locate";
	}
	
	// 查看修改
	public String update() throws Exception
	{
		String id = Struts2Utils.getRequest().getParameter("id");
		arg.put("id", id);		
		return "update";
	}
	
	public void set_author() throws Exception
	{
		DynamicObject flowobj = get_author_common();

		// 以下为常用权限；
		boolean isread = planService.isread(flowobj); // 是否只读页面
		boolean isedit = planService.isedit(flowobj); // 是否修改页面

		boolean issave = planService.issave(flowobj); // 可否保存
		boolean isdelete = planService.isdelete(flowobj); // 可否删除

		arg.put("isread", isread);
		arg.put("isedit", isedit);

		arg.put("issave", issave);
		arg.put("isdelete", isdelete);	
	}
	
	public DynamicObject get_author_common() throws Exception
	{
		// 获取各类权限
		// 流程信息
		String id = Struts2Utils.getRequest().getParameter("id");

		DynamicObject flowobj = FormHelper.preparedFlowAuthor(Struts2Utils.getRequest(), Struts2Utils.getResponse());

		flowobj.setAttr(GlobalConstants.swap_tableid, "Plan");
		flowobj.setAttr(GlobalConstants.swap_dataid, id);

		return flowobj;
	}
	
	// 查看计划模板
	public String selectmodel() throws Exception
	{
		QueryActionHelper helper = new QueryActionHelper();

		arg.putAll(helper.mockArg(_searchname, queryService));

		Map amap = new HashMap();
		amap = (HashMap) ((HashMap) arg).clone();

		Search search = (Search) BeanUtils.cloneBean(queryService.findUniqueByOfSearch("searchname", _searchname));
		search.setMysql(planService.get_selectmodel_sql(amap));

		Page page = helper.mockJdbcPage(search, queryService);
		Map vo = helper.mockVO(_searchname, queryService);

		data.put("vo", vo);
		data.put("page", page);

		return "selectmodel";
	}
	
	public String startflow() throws Exception
	{
		String id = Struts2Utils.getRequest().getParameter("id"); //计划标识
		String flowdefid = Struts2Utils.getRequest().getParameter("flowdefid"); // 流程定义标识
		
		arg.put("id", id);
		arg.put("flowdefid", flowdefid);
		
		return "startflow";
	}
	
	public String get_searchname()
	{
		return _searchname;
	}

	public void set_searchname(String _searchname)
	{
		this._searchname = _searchname;
	}

	public QueryService getQueryService()
	{
		return queryService;
	}

	public void setQueryService(QueryService queryService)
	{
		this.queryService = queryService;
	}

	public WorkFlowEngine getWorkFlowEngine()
	{
		return workFlowEngine;
	}

	public void setWorkFlowEngine(WorkFlowEngine workFlowEngine)
	{
		this.workFlowEngine = workFlowEngine;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public PlanModelService getPlanmodelService()
	{
		return planmodelService;
	}

	public void setPlanmodelService(PlanModelService planmodelService)
	{
		this.planmodelService = planmodelService;
	}

	public PlanService getPlanService()
	{
		return planService;
	}

	public void setPlanService(PlanService planService)
	{
		this.planService = planService;
	}
	
	

}
