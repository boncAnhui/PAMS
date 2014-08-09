package com.pams.hbgl.xxsj.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.action.ActionSessionHelper;
import com.blue.ssh.core.action.SimpleAction;
import com.blue.ssh.core.orm.Page;
import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.services.function.StringToolKit;
import com.pams.entity.FileTemplate;
import com.pams.entity.MeterApply;
import com.pams.entity.Relay;
import com.pams.gxgl.wjwh.service.FileAttachmentService;
import com.pams.gxgl.wjwh.service.FileTemplateService;
import com.pams.hbgl.main.service.MeterApplyService;
import com.pams.hbgl.main.service.RelayService;
import com.ray.app.query.action.QueryActionHelper;
import com.ray.app.query.entity.Search;
import com.ray.app.query.service.QueryService;
import com.ray.app.workflow.enginee.WorkFlowEngine;
import com.ray.app.workflow.spec.GlobalConstants;
import com.ray.app.workflow.ui.action.FormHelper;

public class ApplyAction extends SimpleAction
{
	private String _searchname;

	@Autowired
	private QueryService queryService;

	@Autowired
	private WorkFlowEngine workFlowEngine;
	
	@Autowired
	private MeterApplyService meterapplyService;

	@Autowired
	private RelayService relayService;
	
	@Autowired
	private FileAttachmentService fileattachmentService;
	
	@Autowired
	private FileTemplateService filetemplateService;
	
	public String browsewait() throws Exception
	{
		String username = ActionSessionHelper._get_username();
		String loginname = ActionSessionHelper._get_loginname();
		String userid = ActionSessionHelper._get_userid();
		QueryActionHelper helper = new QueryActionHelper();

		arg.putAll(helper.mockArg(_searchname, queryService));

		Map amap = new HashMap();
		amap = (HashMap) ((HashMap) arg).clone();
		// 特殊字段
		amap.put("loginname", loginname);
		amap.put(GlobalConstants.swap_coperatorid, userid);

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

	// 我的已办（参与过的）
	public String browsehandle() throws Exception
	{
		String username = ActionSessionHelper._get_username();
		String loginname = ActionSessionHelper._get_loginname();
		String userid = ActionSessionHelper._get_userid();
		
		QueryActionHelper helper = new QueryActionHelper();

		arg.putAll(helper.mockArg(_searchname, queryService));

		Map amap = new HashMap();
		amap = (HashMap) ((HashMap) arg).clone();
		// 特殊字段
		amap.put("loginname", loginname);
		amap.put(com.ray.app.workflow.spec.GlobalConstants.swap_coperatorid, userid);

		String flowsno = Struts2Utils.getRequest().getParameter("flowsno");
		amap.put("flowsno", flowsno);

		Search search = (Search) BeanUtils.cloneBean(queryService.findUniqueByOfSearch("searchname", _searchname));
		search.setMysql(meterapplyService.get_browsehandle_sql(amap));// 查询已办记录

		Page page = helper.mockJdbcPage(search, queryService);
		Map vo = helper.mockVO(_searchname, queryService);

		arg.put("flowsno", flowsno);

		data.put("vo", vo);
		data.put("page", page);

		return "browsehandle";
	}
	


	public String browsegroupall() throws Exception
	{
		String username = ActionSessionHelper._get_username();
		String loginname = ActionSessionHelper._get_loginname();
		String userid = ActionSessionHelper._get_userid();
		QueryActionHelper helper = new QueryActionHelper();

		arg.putAll(helper.mockArg(_searchname, queryService));

		Map amap = new HashMap();
		amap = (HashMap) ((HashMap) arg).clone();
		// 特殊字段
		amap.put("loginname", loginname);
		amap.put(com.ray.app.workflow.spec.GlobalConstants.swap_coperatorid, userid);

		String flowsno = Struts2Utils.getRequest().getParameter("flowsno");
		amap.put("flowsno", flowsno);

		Search search = (Search) BeanUtils.cloneBean(queryService.findUniqueByOfSearch("searchname", _searchname));
		search.setMysql(meterapplyService.get_browsegroupall_sql(amap));// 查询全体记录

		Page page = helper.mockJdbcPage(search, queryService);
		Map vo = helper.mockVO(_searchname, queryService);

		arg.put("flowsno", flowsno);

		data.put("vo", vo);
		data.put("page", page);

		return "browsegroupall";
	}
	
	public String readpageframe() throws Exception
	{
		String loginname = ActionSessionHelper._get_loginname();
		String username = ActionSessionHelper._get_username();

		String runactkey = Struts2Utils.getRequest().getParameter("runactkey");
		
		DynamicObject obj_ract = workFlowEngine.getDemandManager().getRAct(runactkey, "MeterApply");
		String runflowkey = obj_ract.getFormatAttr("runflowkey");
		String id = obj_ract.getFormatAttr("dataid");
		
		arg.put("id", id);
		arg.put("runactkey", runactkey);
		arg.put("runflowkey", runflowkey);
		
		// 判断是否可以进入基本信息编辑页面
		DynamicObject flowobj = new DynamicObject();
		flowobj.setAttr(GlobalConstants.swap_coperatorid, ActionSessionHelper._get_userid());
		flowobj.setAttr(GlobalConstants.swap_coperatorcname, ActionSessionHelper._get_username());
		flowobj.setAttr(GlobalConstants.swap_coperatorloginname, ActionSessionHelper._get_loginname());

		flowobj.setAttr(GlobalConstants.swap_coperatordeptid, ActionSessionHelper._get_deptid());
		flowobj.setAttr(GlobalConstants.swap_coperatordeptcname, ActionSessionHelper._get_deptname());
		flowobj.setAttr(GlobalConstants.swap_runactkey, runactkey);

		flowobj.setAttr(GlobalConstants.swap_tableid, "MeterApply");
		flowobj.setAttr(GlobalConstants.swap_dataid, id);
		boolean isedit = meterapplyService.isedit(flowobj); // 是否修改页面

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
		String loginname = ActionSessionHelper._get_loginname();
		String username = ActionSessionHelper._get_username();
		String runactkey = Struts2Utils.getRequest().getParameter("runactkey");


		DynamicObject obj_ract = workFlowEngine.getDemandManager().getRAct(runactkey, "MeterApply");
		String id = obj_ract.getFormatAttr("dataid");
		String actdefid = obj_ract.getFormatAttr("actdefid");
		String tableid = obj_ract.getFormatAttr("tableid");
		String runflowkey = obj_ract.getFormatAttr("runflowkey");
		String runactname = workFlowEngine.getDemandManager().getDao_bact().findById(actdefid).getFormatAttr("cname");
		MeterApply meterapply = meterapplyService.get(id);
		data.put("meterapply", meterapply);

		// 获取各类权限
		set_author();
		// 获取流程环节状态
		String wfstate = workFlowEngine.getDemandManager().getFlowState("MeterApply", id);

		arg.put("loginname", loginname);
		arg.put("id", id);
		arg.put("runactkey", runactkey);
		arg.put("wfstate", wfstate);
		arg.put("actdefid", actdefid);
		arg.put("tableid", tableid);
		arg.put("runflowkey", runflowkey);
		arg.put("runactname", runactname);

		return "readpage";
	}
	
	public String locateframe() throws Exception
	{
		String runactkey = Struts2Utils.getRequest().getParameter("runactkey");
		DynamicObject obj_ract = workFlowEngine.getDemandManager().getRAct(runactkey, "MeterApply");
		String runflowkey = obj_ract.getFormatAttr("runflowkey");
		String id = obj_ract.getFormatAttr("dataid");
		String isinput = Struts2Utils.getRequest().getParameter("isinput");
		arg.put("runactkey", runactkey);
		arg.put("runflowkey", runflowkey);
		arg.put("id", id);
		arg.put("isinput", isinput);
		return "locateframe";
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

		String tableid = "MeterApply";

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
		DynamicObject obj_bact = workFlowEngine.getDemandManager().getAct(actdefid);
		String flowdefid = obj_bact.getFormatAttr("flowid");

		// 权限设置
		set_author();
		
		String actcname = obj_bact.getFormatAttr("cname");

		data.put("meterapply", meterapply);

		arg.put("id", id);
		arg.put("runactkey", runactkey);
		arg.put("actdefid", actdefid);
		arg.put("tableid", tableid);
		arg.put("flowdefid", flowdefid);
		arg.put("actcname", actcname);

		return "locate";
	}
	
	public String browsefile() throws Exception
	{
		String runactkey = Struts2Utils.getRequest().getParameter("runactkey");
		DynamicObject obj_ract = workFlowEngine.getDemandManager().getRAct(runactkey, "MeterApply");
		String flowdefid = obj_ract.getFormatAttr("flowdefid");
		String actdefid = obj_ract.getFormatAttr("actdefid");
		String runflowkey = obj_ract.getFormatAttr("runflowkey");
		
		// 查询流程的相关文档
		Map map = new HashMap();
		map.put("flowdefid", flowdefid);
		map.put("runflowkey", runflowkey);
		List flowfiles = fileattachmentService.find_by_acttemplate(map);
		
		// 查询流程当前活动的相关文档
		map = new HashMap();
		map.put("flowdefid", flowdefid);
		map.put("actdefid", actdefid);

		map.put("runflowkey", runflowkey);
		map.put("runactkey", runactkey);
		
		List actfiles = fileattachmentService.find_by_acttemplate(map);
		
		// 查询流程当前活动的上传文档附件
		map = new HashMap();
		map.put("runactkey", runactkey);
		List fileattachments = fileattachmentService.find(map);
		
		data.put("fileattachments", fileattachments);
		data.put("flowfiles", flowfiles);
		data.put("actfiles", actfiles);
		arg.put("runactkey", runactkey);
		
		return "browsefile";
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
	
	// 签收
	public String apply() throws Exception
	{
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();

		String tableid = "MeterApply";
		String id = request.getParameter("id");
		String userid = ActionSessionHelper._get_userid();
		String runactkey = request.getParameter("runactkey");

		boolean sign = workFlowEngine.getDemandManager().enableApplyNew(runactkey, tableid, userid, "PERSON");

		if (!sign)
		{
			throw new RuntimeException("您没有操作权限!");
		}
		DynamicObject swapFlow = FormHelper.preparedSwapFlow(request, response);

		swapFlow.setAttr(GlobalConstants.swap_runactkey, runactkey);

		workFlowEngine.getActManager().apply(swapFlow);

		arg.put("runactkey", runactkey);

		return "apply";

	}


	// 转办
	public String relayfrom() throws Exception
	{
		// 生成转办单记录，完成跨流程交接
		String username = ActionSessionHelper._get_username();
		String loginname = ActionSessionHelper._get_loginname();
		String userid = ActionSessionHelper._get_userid();

		String runactkey = Struts2Utils.getRequest().getParameter("runactkey");

		DynamicObject obj_ract = workFlowEngine.getDemandManager().getRAct(runactkey, "MeterApply");
		String dataid = obj_ract.getFormatAttr("dataid");
		String tableid = "MeterApply";
		String runflowkey = obj_ract.getFormatAttr("runflowkey");
		String flowdefid = obj_ract.getFormatAttr("flowdefid");
		String flowsno = workFlowEngine.getDemandManager().getBFlow(flowdefid).getFormatAttr("sno");
		Relay relay = new Relay();
		relay.setFromuserid(loginname);
		relay.setFromusername(username);
		relay.setRunflowkey(runflowkey);
		relay.setFlowsno(flowsno);
		relay.setTableid(tableid);
		relay.setDataid(dataid);
		relay.setState("待处理");
		relay.setIsuse("Y");

		relayService.create(relay);

		return "relayfrom";
	}

	public void set_author() throws Exception
	{
		DynamicObject flowobj = get_author_common();

		// 以下为常用权限；
		boolean isread = meterapplyService.isread(flowobj); // 是否只读页面
		boolean isedit = meterapplyService.isedit(flowobj); // 是否修改页面

		boolean isapply = meterapplyService.isapply(flowobj); // 可否签收
		boolean isforward = meterapplyService.isforward(flowobj); // 可否转发
		boolean iscallback = meterapplyService.iscallback(flowobj); // 可否收回
		boolean isbackward = meterapplyService.isbackward(flowobj); // 可否退回

		boolean issave = meterapplyService.issave(flowobj); // 可否保存
		boolean isdelete = meterapplyService.isdelete(flowobj); // 可否删除

		arg.put("isread", isread);
		arg.put("isedit", isedit);

		arg.put("isapply", isapply);
		arg.put("isforward", isforward);
		arg.put("iscallback", iscallback);
		arg.put("isbackward", isbackward);

		arg.put("issave", issave);
		arg.put("isdelete", isdelete);
	}

	public DynamicObject get_author_common() throws Exception
	{
		// 获取各类权限
		// 流程信息
		String runactkey = Struts2Utils.getRequest().getParameter("runactkey");
		DynamicObject obj_ract = workFlowEngine.getDemandManager().getRAct(runactkey, "MeterApply");
		String id = obj_ract.getFormatAttr("dataid");

		DynamicObject flowobj = FormHelper.preparedFlowAuthor(Struts2Utils.getRequest(), Struts2Utils.getResponse());

		flowobj.setAttr(GlobalConstants.swap_tableid, "MeterApply");
		flowobj.setAttr(GlobalConstants.swap_dataid, id);

		return flowobj;
	}

	public String upload() throws Exception
	{
		HttpServletRequest req = Struts2Utils.getRequest();
		String runactkey = req.getParameter("runactkey");
		String cno = req.getParameter("cno");
		
		DynamicObject obj_ract = workFlowEngine.getActManager().getRAct(runactkey);
		String actdefid = obj_ract.getFormatAttr("actdefid");
		
		DynamicObject amap = new DynamicObject();
		amap.put("actdefid", actdefid);
		amap.put("cno", cno);
		FileTemplate filetemplate = filetemplateService.locate_by(amap);
		
		data.put("obj_filetemplate", filetemplate);
		data.put("obj_ract", obj_ract);

		return "upload";
	}	
	
	public String get_searchname()
	{
		return _searchname;
	}

	public void set_searchname(String searchname)
	{
		_searchname = searchname;
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

	public FileAttachmentService getFileattachmentService()
	{
		return fileattachmentService;
	}

	public void setFileattachmentService(FileAttachmentService fileattachmentService)
	{
		this.fileattachmentService = fileattachmentService;
	}

	public FileTemplateService getFiletemplateService()
	{
		return filetemplateService;
	}

	public void setFiletemplateService(FileTemplateService filetemplateService)
	{
		this.filetemplateService = filetemplateService;
	}
	
}
