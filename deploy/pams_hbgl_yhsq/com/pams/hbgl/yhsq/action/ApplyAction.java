package com.pams.hbgl.yhsq.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.action.ActionSessionHelper;
import com.blue.ssh.core.action.SimpleAction;
import com.blue.ssh.core.orm.Page;
import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.services.function.StringToolKit;
import com.headray.framework.spec.GlobalConstants;
import com.pams.entity.MeterApply;
import com.pams.entity.Relay;
import com.pams.hbgl.main.service.MeterApplyService;
import com.pams.hbgl.main.service.RelayService;
import com.ray.app.query.action.QueryActionHelper;
import com.ray.app.query.entity.Search;
import com.ray.app.query.service.QueryService;
import com.ray.app.workflow.enginee.WorkFlowEngine;

public class ApplyAction extends SimpleAction
{
	private String _searchname;

	@Autowired
	private QueryService queryService;

	@Autowired
	private MeterApplyService meterapplyService;
	
	@Autowired
	private RelayService relayService;

	@Autowired
	private WorkFlowEngine workFlowEngine;

	public String browsewait() throws Exception
	{
		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token);
		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);
		QueryActionHelper helper = new QueryActionHelper();

		arg.putAll(helper.mockArg(_searchname, queryService));

		Map amap = new HashMap();
		amap = (HashMap) ((HashMap) arg).clone();
		// 特殊字段
		amap.put("loginname", loginname);
		String userid = ActionSessionHelper._get_userid();
		amap.put(com.ray.app.workflow.spec.GlobalConstants.swap_coperatorid, userid);

		String flowsno = Struts2Utils.getRequest().getParameter("flowsno");
		amap.put("flowsno", flowsno);		
		
		Search search = (Search) BeanUtils.cloneBean(queryService.findUniqueByOfSearch("searchname", _searchname));
		search.setMysql(meterapplyService.get_browsewait_sql(amap));// 查询待办记录

		Page page = helper.mockJdbcPage(search, queryService);
		Map vo = helper.mockVO(_searchname, queryService);

		arg.put("flowsno", flowsno);
		
		data.put("vo", vo);
		data.put("page", page);

		return "browsewait";
	}
	
	public String browsehandle() throws Exception
	{
		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token);
		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);
		QueryActionHelper helper = new QueryActionHelper();

		arg.putAll(helper.mockArg(_searchname, queryService));

		Map amap = new HashMap();
		amap = (HashMap) ((HashMap) arg).clone();
		// 特殊字段
		amap.put("loginname", loginname);
		String userid = ActionSessionHelper._get_userid();
		amap.put(com.ray.app.workflow.spec.GlobalConstants.swap_coperatorid, userid);

		Search search = (Search) BeanUtils.cloneBean(queryService.findUniqueByOfSearch("searchname", _searchname));
		search.setMysql(meterapplyService.get_browsehandle_sql(amap));// 查询已办记录

		Page page = helper.mockJdbcPage(search, queryService);
		Map vo = helper.mockVO(_searchname, queryService);

		data.put("vo", vo);
		data.put("page", page);

		return "browsehandle";
	}	
	
	public String browsegroupall() throws Exception
	{
		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token);
		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);
		QueryActionHelper helper = new QueryActionHelper();

		arg.putAll(helper.mockArg(_searchname, queryService));

		Map amap = new HashMap();
		amap = (HashMap) ((HashMap) arg).clone();
		// 特殊字段
		amap.put("loginname", loginname);
		String userid = ActionSessionHelper._get_userid();
		amap.put(com.ray.app.workflow.spec.GlobalConstants.swap_coperatorid, userid);

		Search search = (Search) BeanUtils.cloneBean(queryService.findUniqueByOfSearch("searchname", _searchname));
		search.setMysql(meterapplyService.get_browsegroupall_sql(amap));// 查询全体记录

		Page page = helper.mockJdbcPage(search, queryService);
		Map vo = helper.mockVO(_searchname, queryService);

		data.put("vo", vo);
		data.put("page", page);

		return "browsegroupall";
	}
	
	public String browserelay() throws Exception
	{
		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token);
		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);
		QueryActionHelper helper = new QueryActionHelper();

		arg.putAll(helper.mockArg(_searchname, queryService));

		Map amap = new HashMap();
		amap = (HashMap) ((HashMap) arg).clone();
		// 特殊字段
		amap.put("loginname", loginname);
		String userid = ActionSessionHelper._get_userid();
		amap.put(com.ray.app.workflow.spec.GlobalConstants.swap_coperatorid, userid);
		
		String flowsno = Struts2Utils.getRequest().getParameter("flowsno");
		amap.put("flowsno", flowsno);		

		Search search = (Search) BeanUtils.cloneBean(queryService.findUniqueByOfSearch("searchname", _searchname));
		search.setMysql(meterapplyService.get_browserelay_sql(amap));// 查询转办记录

		Page page = helper.mockJdbcPage(search, queryService);
		Map vo = helper.mockVO(_searchname, queryService);

		arg.put("flowsno", flowsno);
		
		data.put("vo", vo);
		data.put("page", page);

		return "browsewait";
	}
	
	public String locaterelay() throws Exception
	{
		String id = Struts2Utils.getRequest().getParameter("id");
		Relay relay = relayService.get(id);
		data.put("relay", relay);
		arg.put("id", id);
		return "locaterelay";
	}
	
	// 接收转办单，创建业务流程。
	public String relayto() throws Exception
	{
		String id = Struts2Utils.getRequest().getParameter("id");
		
		Relay relay = relayService.get(id);
		
		String tableid = "METERAPPLY";
		String dataid = relay.getDataid();
		String flowdefid = workFlowEngine.getDemandManager().get_bflow_by_sno("HBGL_YHSQ").getFormatAttr("id");
		
		String workname = "";

		String userid = ActionSessionHelper._get_userid();
		String loginname = ActionSessionHelper._get_loginname();
		String username = ActionSessionHelper._get_username();
		String usertype = "PERSON";
		String deptid = ActionSessionHelper._get_deptid();
		String deptname = ActionSessionHelper._get_deptname();
		String orgid = ActionSessionHelper._get_orgid();
		String orgname = ActionSessionHelper._get_orgname();

		DynamicObject swapFlow = new DynamicObject();
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_coperatorid, userid);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_coperatorloginname, loginname);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_coperatorcname, username);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_coperatordeptid, deptid);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_coperatordeptcname, deptname);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_coperatororgid, orgid);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_coperatororgcname, orgname);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_flowdefid, flowdefid);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_tableid, tableid);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_dataid, dataid);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_workname, workname);

		String runactkey = meterapplyService.createflow(swapFlow);

		arg.put("id", dataid);
		arg.put("runactkey", runactkey);		
		
		arg.put("id", id);
		return "relayto";
	}


	// 新增
	public String input() throws Exception
	{
		return "input";
	}

	// 创建
	public String insert() throws Exception
	{
		String bt = Struts2Utils.getRequest().getParameter("bt");
		String zzxqmc = Struts2Utils.getRequest().getParameter("zzxqmc");

		String flowdefid = Struts2Utils.getRequest().getParameter("flowdefid");

		MeterApply meterapply = new MeterApply();
		meterapply.setBt(bt);
		meterapply.setZzxqmc(zzxqmc);

		String userid = ActionSessionHelper._get_userid();
		String loginname = ActionSessionHelper._get_loginname();
		String username = ActionSessionHelper._get_username();
		String usertype = "PERSON";
		String deptid = ActionSessionHelper._get_deptid();
		String deptname = ActionSessionHelper._get_deptname();
		String orgid = ActionSessionHelper._get_orgid();
		String orgname = ActionSessionHelper._get_orgname();

		String tableid = "METERAPPLY";

		DynamicObject swapFlow = new DynamicObject();
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_coperatorid, userid);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_coperatorloginname, loginname);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_coperatorcname, username);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_coperatordeptid, deptid);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_coperatordeptcname, deptname);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_coperatororgid, orgid);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_coperatororgcname, orgname);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_flowdefid, flowdefid);
		swapFlow.setAttr(com.ray.app.workflow.spec.GlobalConstants.swap_tableid, tableid);

		String runactkey = meterapplyService.create(meterapply, swapFlow);

		arg.put("id", meterapply.getId());
		arg.put("runactkey", runactkey);

		return "insert";
	}

	// 查看修改
	public String locate() throws Exception
	{
		// String id = Struts2Utils.getRequest().getParameter("id");
		String runactkey = Struts2Utils.getRequest().getParameter("runactkey");
		DynamicObject obj_ract = workFlowEngine.getDemandManager().getRAct(runactkey, "MeterApply");
		String id = obj_ract.getFormatAttr("dataid");

		// 查询业务数据
		MeterApply meterapply = meterapplyService.locate(id);

		// 查询流程数据
		String actdefid = obj_ract.getFormatAttr("actdefid");
		String tableid = obj_ract.getFormatAttr("tableid");
		String flowdefid = workFlowEngine.getDemandManager().getAct(actdefid).getFormatAttr("flowid");

		data.put("meterapply", meterapply);

		arg.put("id", id);
		arg.put("runactkey", runactkey);
		arg.put("actdefid", actdefid);
		arg.put("tableid", tableid);
		arg.put("flowdefid", flowdefid);

		return "locate";
	}

	// 删除
	public String delete() throws Exception
	{
		String[] runactkeys = StringToolKit.split(Struts2Utils.getRequest().getParameter("runactkeys"), ",");
		if (runactkeys != null)
		{
			for (int i = 0; i < runactkeys.length; i++)
			{
				try
				{
					String id = workFlowEngine.getDemandManager().getRAct(runactkeys[i], "MeterApply").getFormatAttr("dataid");
					meterapplyService.delete(id);
				}
				catch (Exception e)
				{
					System.out.println(e);
				}
			}
		}

		return "delete";
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

	public MeterApplyService getMeterapplyService()
	{
		return meterapplyService;
	}

	public void setMeterapplyService(MeterApplyService meterapplyService)
	{
		this.meterapplyService = meterapplyService;
	}

	public RelayService getRelayService()
	{
		return relayService;
	}

	public void setRelayService(RelayService relayService)
	{
		this.relayService = relayService;
	}

	public WorkFlowEngine getWorkFlowEngine()
	{
		return workFlowEngine;
	}

	public void setWorkFlowEngine(WorkFlowEngine workFlowEngine)
	{
		this.workFlowEngine = workFlowEngine;
	}
	
	

}
