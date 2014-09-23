package com.pams.gxgl.gxwh.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import com.headray.core.spring.jdo.JdbcDao;
import com.headray.framework.common.generator.FormatKey;
import com.headray.framework.common.generator.TimeGenerator;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.services.function.StringToolKit;
import com.pams.entity.FileTemplate;
import com.pams.entity.InfoShare;
import com.pams.entity.KnowledgeClass;
import com.pams.gxgl.gxwh.service.InfoShareService;
import com.pams.gxgl.wjwh.service.FileAttachmentService;
import com.pams.gxgl.wjwh.service.FileTemplateService;
import com.pams.gxgl.zsflwh.service.KnowledgeClassService;
import com.pams.gxgl.zswh.service.KnowledgeService;
import com.ray.app.dictionary.service.DictionaryService;
import com.ray.app.query.action.QueryActionHelper;
import com.ray.app.query.entity.Search;
import com.ray.app.query.service.QueryService;
import com.ray.app.workflow.enginee.WorkFlowEngine;
import com.ray.app.workflow.expression.formula.FormulaParser;
import com.ray.app.workflow.spec.GlobalConstants;
import com.ray.app.workflow.ui.action.FormHelper;
import com.ray.xj.sgcc.irm.system.author.userrole.entity.UserRole;
import com.ray.xj.sgcc.irm.system.author.userrole.service.UserRoleService;
import com.ray.xj.sgcc.irm.system.organ.organ.entity.Organ;
import com.ray.xj.sgcc.irm.system.organ.organ.service.OrganService;
import com.ray.xj.sgcc.irm.system.organ.role.entity.Role;
import com.ray.xj.sgcc.irm.system.organ.role.service.RoleService;
import com.ray.xj.sgcc.irm.system.organ.user.entity.User;
import com.ray.xj.sgcc.irm.system.organ.user.service.UserService;

public class ApplyAction extends SimpleAction
{
	private String _searchname;

	@Autowired
	private DictionaryService dictionaryService;

	@Autowired
	private QueryService queryService;

	@Autowired
	private WorkFlowEngine workFlowEngine;

	@Autowired
	private InfoShareService infoshareService;

	@Autowired
	private FileAttachmentService fileattachmentService;

	@Autowired
	private FileTemplateService filetemplateService;

	@Autowired
	private KnowledgeService knowledgeService;

	@Autowired
	private KnowledgeClassService knowledgeclassService;

	@Autowired
	private OrganService organService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRoleService userroleService;

	@Autowired
	private UserService userService;

	private final static String tableid = "InfoShare";
	
	private final static String flowclass = "GXGL";
	
	
	@Autowired
	JdbcDao jdbcDao;
	
	public String mainframe() throws Exception
	{
		return "mainframe";
	}
	
	public String selectflowdefine() throws Exception
	{
		List bflows = workFlowEngine.getDemandManager().getFlowsByClass(flowclass);
		
		StringBuffer bflow_texts = new StringBuffer();
		StringBuffer bflow_values = new StringBuffer();

		for (int i = 0; i < bflows.size(); i++)
		{
			DynamicObject bflow = (DynamicObject)bflows.get(i);
			String cname = bflow.getFormatAttr("cname");
			String id = bflow.getFormatAttr("id");
			bflow_texts.append(cname);
			bflow_values.append("id");

			if (i < bflows.size() - 1)
			{
				bflow_texts.append("||");
				bflow_values.append("||");
			}
		}
		
		data.put("bflows",  bflows);
		data.put("bflow_texts",  bflow_texts);
		data.put("bflow_values",  bflow_values);
		
		return "selectflowdefine";
	}

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

		String flowcclass = Struts2Utils.getRequest().getParameter("flowcclass");
		amap.put("flowcclass", flowcclass);

		Search search = (Search) BeanUtils.cloneBean(queryService.findUniqueByOfSearch("searchname", _searchname));
		search.setMysql(infoshareService.get_browsewait_sql(amap));// 查询待办记录

		Page page = helper.mockJdbcPage(search, queryService);
		Map vo = helper.mockVO(_searchname, queryService);

		arg.put("flowcclass", flowcclass);

		data.put("vo", vo);
		data.put("page", page);

		return "browsewait";
	}

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

		String flowcclass = Struts2Utils.getRequest().getParameter("flowcclass");
		amap.put("flowcclass", flowcclass);

		Search search = (Search) BeanUtils.cloneBean(queryService.findUniqueByOfSearch("searchname", _searchname));
		search.setMysql(infoshareService.get_browsehandle_sql(amap));// 查询已办记录

		Page page = helper.mockJdbcPage(search, queryService);
		Map vo = helper.mockVO(_searchname, queryService);

		arg.put("flowcclass", flowcclass);

		data.put("vo", vo);
		data.put("page", page);

		return "browsehandle";
	}

	// 我的所有工作
	public String browseall() throws Exception
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

		String flowcclass = Struts2Utils.getRequest().getParameter("flowcclass");
		amap.put("flowcclass", flowcclass);

		Search search = (Search) BeanUtils.cloneBean(queryService.findUniqueByOfSearch("searchname", _searchname));
		search.setMysql(infoshareService.get_browseall_sql(amap));// 查询已办记录

		Page page = helper.mockJdbcPage(search, queryService);
		Map vo = helper.mockVO(_searchname, queryService);

		arg.put("flowcclass", flowcclass);

		data.put("vo", vo);
		data.put("page", page);

		return "browseall";
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

		String flowcclass = Struts2Utils.getRequest().getParameter("flowcclass");
		amap.put("flowcclass", flowcclass);

		Search search = (Search) BeanUtils.cloneBean(queryService.findUniqueByOfSearch("searchname", _searchname));
		search.setMysql(infoshareService.get_browsegroupall_sql(amap));// 查询全体记录

		Page page = helper.mockJdbcPage(search, queryService);
		Map vo = helper.mockVO(_searchname, queryService);

		arg.put("flowcclass", flowcclass);

		data.put("vo", vo);
		data.put("page", page);

		return "browsegroupall";
	}

	public String readpageframe() throws Exception
	{
		String loginname = ActionSessionHelper._get_loginname();
		String username = ActionSessionHelper._get_username();

		String runactkey = Struts2Utils.getRequest().getParameter("runactkey");

		DynamicObject obj_ract = workFlowEngine.getDemandManager().getRAct(runactkey, "InfoShare");
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

		flowobj.setAttr(GlobalConstants.swap_tableid, "InfoShare");
		flowobj.setAttr(GlobalConstants.swap_dataid, id);
		boolean isedit = infoshareService.isedit(flowobj); // 是否修改页面

		if (isedit)
		{
			return "tolocateframe";
		}
		else
		{
			return "readpageframe";
		}
	}

	public String readpagekeyframe() throws Exception
	{
		String loginname = ActionSessionHelper._get_loginname();
		String username = ActionSessionHelper._get_username();

		String runflowkey = Struts2Utils.getRequest().getParameter("runflowkey");
		DynamicObject obj_ract = workFlowEngine.getDemandManager().getMaxRAct(runflowkey, tableid);

		String runactkey = obj_ract.getFormatAttr("runactkey");
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

		flowobj.setAttr(GlobalConstants.swap_tableid, "InfoShare");
		flowobj.setAttr(GlobalConstants.swap_dataid, id);

		return "readpagekeyframe";
	}

	public String readpage() throws Exception
	{
		String loginname = ActionSessionHelper._get_loginname();
		String username = ActionSessionHelper._get_username();
		String runactkey = Struts2Utils.getRequest().getParameter("runactkey");

		DynamicObject obj_ract = workFlowEngine.getDemandManager().getRAct(runactkey, "InfoShare");
		String id = obj_ract.getFormatAttr("dataid");
		String actdefid = obj_ract.getFormatAttr("actdefid");
		String tableid = obj_ract.getFormatAttr("tableid");
		String runflowkey = obj_ract.getFormatAttr("runflowkey");
		String runactname = workFlowEngine.getDemandManager().getDao_bact().findById(actdefid).getFormatAttr("cname");
		InfoShare infoshare = infoshareService.get(id);

		// 获取各类权限
		set_author();
		// 获取流程环节状态
		String wfstate = workFlowEngine.getDemandManager().getFlowState("InfoShare", id);

		// 查询流程的相关文档
		Map map = new HashMap();
		map.put("runflowkey", runflowkey);
		List flowfiles = fileattachmentService.find(map);

		List<UserRole> userroles = userroleService.findBy("userid", loginname);

		StringBuffer userrole_texts = new StringBuffer();

		for (int i = 0; i < userroles.size(); i++)
		{
			UserRole userrole = userroles.get(i);
			String rname = userrole.getRname();
			userrole_texts.append(rname);

			if (i < userroles.size() - 1)
			{
				userrole_texts.append("||");
			}
		}

		String sourcename_texts = dictionaryService.getTexts("app.infoshare.sourcename");
		String sourcename_values = dictionaryService.getValues("app.infoshare.sourcename");

		String shareauthor_texts = dictionaryService.getTexts("app.infoshare.shareauthor");
		String shareauthor_values = dictionaryService.getValues("app.infoshare.shareauthor");

		data.put("infoshare", infoshare);
		data.put("fileattachments", flowfiles);

		data.put("userrole_texts", userrole_texts);
		data.put("userrole_values", userrole_texts);

		data.put("sourcename_texts", sourcename_texts);
		data.put("sourcename_values", sourcename_values);

		data.put("shareauthor_texts", shareauthor_texts);
		data.put("shareauthor_values", shareauthor_values);

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
		DynamicObject obj_ract = workFlowEngine.getDemandManager().getRAct(runactkey, "InfoShare");
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
		String loginname = ActionSessionHelper._get_loginname();
		String username = ActionSessionHelper._get_username();
		String userid = ActionSessionHelper._get_userid();
		String deptid = ActionSessionHelper._get_deptid();
		String deptname = ActionSessionHelper._get_deptname();
		String deptinternal = ActionSessionHelper._get_dept_internal();

		String sourcename_texts = dictionaryService.getTexts("app.infoshare.sourcename");
		String sourcename_values = dictionaryService.getValues("app.infoshare.sourcename");

		String shareauthor_texts = dictionaryService.getTexts("app.infoshare.shareauthor");
		String shareauthor_values = dictionaryService.getValues("app.infoshare.shareauthor");
		
		// List bflows = workFlowEngine.getDemandManager().getFlowsByClassid(flowclassid);
		
		List bflows = workFlowEngine.getDemandManager().getEnableCreateFlow(loginname, "PERSON", flowclass);
		
		StringBuffer bflow_texts = new StringBuffer();
		StringBuffer bflow_values = new StringBuffer();

//		for (int i = 0; i < bflows.size(); i++)
//		{
//			DynamicObject bflow = (DynamicObject)bflows.get(i);
//			String cname = bflow.getFormatAttr("cname");
//			String id = bflow.getFormatAttr("id");
//			bflow_texts.append(cname);
//			bflow_values.append(id);
//
//			if (i < bflows.size() - 1)
//			{
//				bflow_texts.append("||");
//				bflow_values.append("||");
//			}
//		}
		
		if(bflows.size()>0)
		{
			DynamicObject bflow = (DynamicObject)bflows.get(0);
			String cname = bflow.getFormatAttr("cname");
			String id = bflow.getFormatAttr("id");
			bflow_texts.append(cname);
			bflow_values.append(id);			
		}
		
		List<UserRole> userroles = userroleService.findBy("userid", loginname);

		StringBuffer userrole_texts = new StringBuffer();

		for (int i = 0; i < userroles.size(); i++)
		{
			UserRole userrole = userroles.get(i);
			String rname = userrole.getRname();
			userrole_texts.append(rname);

			if (i < userroles.size() - 1)
			{
				userrole_texts.append("||");
			}
		}
		
		// 岗位信息
		String position = userService.getUserByLoginName(loginname).getPosition();

		String obtaintimed = TimeGenerator.getDateStr();
		Calendar ctime = new GregorianCalendar();
		
		String obtaintimet = "";
		obtaintimet += FormatKey.format(String.valueOf(ctime.get(Calendar.HOUR_OF_DAY)), 2);
		obtaintimet += ":";
		obtaintimet += FormatKey.format(String.valueOf(ctime.get(Calendar.MINUTE)), 2);
		
		// 默认共享范围
		String defsharescope = username + "," + deptname;
		String defsharescopeid = userid + "," + deptid;
		String defsharescopectype = "PERSON,DEPT";
		String defsharescopeinternal = userid + "," + deptinternal;
		
		data.put("loginname", loginname);
		data.put("username", username);

		data.put("deptid", deptid);
		data.put("deptname", deptname);

		data.put("bflow_texts",  bflow_texts);
		data.put("bflow_values",  bflow_values);		

		data.put("userrole_texts", userrole_texts);
		data.put("userrole_values", userrole_texts);

		data.put("sourcename_texts", sourcename_texts);
		data.put("sourcename_values", sourcename_values);

		data.put("shareauthor_texts", shareauthor_texts);
		data.put("shareauthor_values", shareauthor_values);
		
		data.put("position", position);

		data.put("obtaintimed", obtaintimed);
		data.put("obtaintimet", obtaintimet);
	
		data.put("defsharescope", defsharescope);
		data.put("defsharescopeid", defsharescopeid);
		data.put("defsharescopectype", defsharescopectype);
		data.put("defsharescopeinternal", defsharescopeinternal);

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

		String tableid = "InfoShare";

		String flowdefid = Struts2Utils.getRequest().getParameter("flowdefid");

		String positionname = Struts2Utils.getRequest().getParameter("positionname");
		String sourceid = Struts2Utils.getRequest().getParameter("sourceid");
		String sourcename = dictionaryService.getDtext("app.infoshare.sourcename", sourceid);
		String title = Struts2Utils.getRequest().getParameter("title");
		String obtaintimed = Struts2Utils.getRequest().getParameter("obtaintimed");
		String obtaintimet = Struts2Utils.getRequest().getParameter("obtaintimet");
		String obtaintime = obtaintimed + " " + obtaintimet + ":00";
		
		String summary = Struts2Utils.getRequest().getParameter("summary");

		String cclassid = Struts2Utils.getRequest().getParameter("cclassid");
		String cclassname = Struts2Utils.getRequest().getParameter("cclassname");
		String shareauthor = Struts2Utils.getRequest().getParameter("shareauthor");
		String infosharescope = Struts2Utils.getRequest().getParameter("infosharescope");
		String infosharescopeid = Struts2Utils.getRequest().getParameter("infosharescopeid");
		String infosharescopectype = Struts2Utils.getRequest().getParameter("infosharescopectype");
		String infosharescopeinternal = Struts2Utils.getRequest().getParameter("infosharescopeinternal");

		String memo = Struts2Utils.getRequest().getParameter("memo");

		InfoShare infoshare = new InfoShare();
		Timestamp nowtime = new Timestamp(System.currentTimeMillis());

		infoshare.setDeptid(deptid);
		infoshare.setDeptname(deptname);
		infoshare.setPositionname(positionname);
		infoshare.setSourceid(sourceid);
		infoshare.setSourcename(sourcename);
		infoshare.setObtaintime(Timestamp.valueOf(obtaintime));
		infoshare.setObtaintimed(obtaintimed);
		infoshare.setObtaintimet(obtaintimet);

		infoshare.setTitle(title);
		infoshare.setSummary(summary);
		infoshare.setCclassid(cclassid);
		infoshare.setCclassname(cclassname);
		infoshare.setShareauthor(shareauthor);
		infoshare.setInfosharescope(infosharescope);
		infoshare.setInfosharescopeid(infosharescopeid);
		infoshare.setInfosharescopectype(infosharescopectype);
		infoshare.setInfosharescopeinternal(infosharescopeinternal);
		infoshare.setMemo(memo);

		infoshare.setCreater(loginname);
		infoshare.setCreatername(username);
		infoshare.setCreatetime(nowtime);

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

		String runactkey = infoshareService.create(infoshare, swapFlow);

		arg.put("id", infoshare.getId());
		arg.put("runactkey", runactkey);

		return "insert";
	}

	// 查看修改
	public String locate() throws Exception
	{
		String loginname = ActionSessionHelper._get_loginname();
		String username = ActionSessionHelper._get_username();
		String userid = ActionSessionHelper._get_userid();
		String deptid = ActionSessionHelper._get_deptid();
		String deptname = ActionSessionHelper._get_deptname();
		String deptinternal = ActionSessionHelper._get_dept_internal();

		// String id = Struts2Utils.getRequest().getParameter("id");
		String runactkey = Struts2Utils.getRequest().getParameter("runactkey");
		DynamicObject obj_ract = workFlowEngine.getDemandManager().getRAct(runactkey, "InfoShare");
		String id = obj_ract.getFormatAttr("dataid");

		// 查询业务数据
		InfoShare infoshare = infoshareService.locate(id);
		
		// 查询流程数据
		String actdefid = obj_ract.getFormatAttr("actdefid");
		String tableid = obj_ract.getFormatAttr("tableid");
		DynamicObject obj_bact = workFlowEngine.getDemandManager().getAct(actdefid);
		String flowdefid = obj_bact.getFormatAttr("flowid");
		String runflowkey = obj_ract.getFormatAttr("runflowkey");

		// 权限设置
		set_author();

		String actcname = obj_bact.getFormatAttr("cname");

		// 查询流程的相关文档
		Map map = new HashMap();
		map.put("runflowkey", runflowkey);
		List flowfiles = fileattachmentService.find(map);

		String sourcename_texts = dictionaryService.getTexts("app.infoshare.sourcename");
		String sourcename_values = dictionaryService.getValues("app.infoshare.sourcename");

		String shareauthor_texts = dictionaryService.getTexts("app.infoshare.shareauthor");
		String shareauthor_values = dictionaryService.getValues("app.infoshare.shareauthor");

		List<UserRole> userroles = userroleService.findBy("userid", loginname);

		StringBuffer userrole_texts = new StringBuffer();

		for (int i = 0; i < userroles.size(); i++)
		{
			UserRole userrole = userroles.get(i);
			String rname = userrole.getRname();
			userrole_texts.append(rname);

			if (i < userroles.size() - 1)
			{
				userrole_texts.append("||");
			}
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String cdate = sdf.format(infoshare.getObtaintime());
		System.out.println(cdate);

		SimpleDateFormat stf = new SimpleDateFormat("hh:mm:ss");
		String ctime = stf.format(infoshare.getObtaintime());
		System.out.println(ctime);
		
		// 查询可选路由
		List routes = workFlowEngine.getDemandManager().getRoutes(actdefid);
		
		// 默认共享范围
		String defsharescope = username + "," + deptname;
		String defsharescopeid = userid + "," + deptid;
		String defsharescopectype = "PERSON,DEPT";
		String defsharescopeinternal = userid + "," + deptinternal;

		data.put("infoshare", infoshare);
		data.put("fileattachments", flowfiles);

		data.put("userrole_texts", userrole_texts);
		data.put("userrole_values", userrole_texts);

		data.put("sourcename_texts", sourcename_texts);
		data.put("sourcename_values", sourcename_values);

		data.put("shareauthor_texts", shareauthor_texts);
		data.put("shareauthor_values", shareauthor_values);

		data.put("routes", routes);
		
		data.put("defsharescope", defsharescope);
		data.put("defsharescopeid", defsharescopeid);
		data.put("defsharescopectype", defsharescopectype);
		data.put("defsharescopeinternal", defsharescopeinternal);
		
		arg.put("id", id);
		arg.put("runflowkey", runflowkey);
		arg.put("runactkey", runactkey);
		arg.put("actdefid", actdefid);
		arg.put("tableid", tableid);
		arg.put("flowdefid", flowdefid);
		arg.put("actcname", actcname);

		return "locate";
	}

	// 更新
	public String update() throws Exception
	{
		String userid = ActionSessionHelper._get_userid();
		String loginname = ActionSessionHelper._get_loginname();
		String username = ActionSessionHelper._get_username();
		String usertype = "PERSON";
		String deptid = ActionSessionHelper._get_deptid();
		String deptname = ActionSessionHelper._get_deptname();
		String orgid = ActionSessionHelper._get_orgid();
		String orgname = ActionSessionHelper._get_orgname();

		String runactkey = Struts2Utils.getRequest().getParameter("runactkey");
		DynamicObject obj_ract = workFlowEngine.getDemandManager().getRAct(runactkey, tableid);

		String id = Struts2Utils.getRequest().getParameter("id");
		String flowdefid = obj_ract.getFormatAttr("flowdefid");

		String positionname = Struts2Utils.getRequest().getParameter("positionname");
		String sourceid = Struts2Utils.getRequest().getParameter("sourceid");
		String sourcename = dictionaryService.getDtext("app.infoshare.sourcename", sourceid);
		String title = Struts2Utils.getRequest().getParameter("title");
		
		String obtaintimed = Struts2Utils.getRequest().getParameter("obtaintimed");
		String obtaintimet = Struts2Utils.getRequest().getParameter("obtaintimet");
		
		String obtaintime = obtaintimed + " " + obtaintimet + ":00";

		String summary = Struts2Utils.getRequest().getParameter("summary");

		String cclassid = Struts2Utils.getRequest().getParameter("cclassid");
		String cclassname = Struts2Utils.getRequest().getParameter("cclassname");
		String shareauthor = Struts2Utils.getRequest().getParameter("shareauthor");
		String infosharescope = Struts2Utils.getRequest().getParameter("infosharescope");
		String infosharescopeid = Struts2Utils.getRequest().getParameter("infosharescopeid");
		String infosharescopectype = Struts2Utils.getRequest().getParameter("infosharescopectype");
		String infosharescopeinternal = Struts2Utils.getRequest().getParameter("infosharescopeinternal");

		String memo = Struts2Utils.getRequest().getParameter("memo");

		InfoShare infoshare = infoshareService.get(id);
		Timestamp nowtime = new Timestamp(System.currentTimeMillis());

		infoshare.setDeptid(deptid);
		infoshare.setDeptname(deptname);
		infoshare.setPositionname(positionname);
		infoshare.setSourceid(sourceid);
		infoshare.setSourcename(sourcename);
		infoshare.setObtaintime(Timestamp.valueOf(obtaintime));
		infoshare.setObtaintimed(obtaintimed);
		infoshare.setObtaintimet(obtaintimet);

		infoshare.setTitle(title);
		infoshare.setSummary(summary);
		infoshare.setCclassid(cclassid);
		infoshare.setCclassname(cclassname);
		infoshare.setShareauthor(shareauthor);
		infoshare.setInfosharescope(infosharescope);
		infoshare.setInfosharescopeid(infosharescopeid);
		infoshare.setInfosharescopectype(infosharescopectype);
		infoshare.setInfosharescopeinternal(infosharescopeinternal);
		
		infoshare.setMemo(memo);

//		infoshare.setCreater(loginname);
//		infoshare.setCreatername(username);
//		infoshare.setCreatetime(nowtime);
		
		infoshareService.save(infoshare);

		arg.put("id", infoshare.getId());
		arg.put("runactkey", runactkey);

		return "update";
	}

	// 发布
	public String publish() throws Exception
	{
		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(com.headray.framework.spec.GlobalConstants.sys_login_token);

		String loginname = ActionSessionHelper._get_loginname();
		String username = ActionSessionHelper._get_username();

		String runactkey = Struts2Utils.getRequest().getParameter("runactkey");
		String id = workFlowEngine.getDemandManager().getRAct(runactkey, InfoShareService._tableid).getFormatAttr("dataid");

		String cclassid = infoshareService.get(id).getCclassid(); // 信息共享分类标识
		infoshareService.publish(id, cclassid, login_token);
		
		arg.put("runactkey", runactkey);

		return "publish";
	}
	
	// 发布
	public String ajaxpublish() throws Exception
	{
		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(com.headray.framework.spec.GlobalConstants.sys_login_token);

		String loginname = ActionSessionHelper._get_loginname();
		String username = ActionSessionHelper._get_username();

		String runactkey = Struts2Utils.getRequest().getParameter("runactkey");
		String id = workFlowEngine.getDemandManager().getRAct(runactkey, InfoShareService._tableid).getFormatAttr("dataid");

		String cclassid = infoshareService.get(id).getCclassid(); // 信息共享分类标识
		infoshareService.publish(id, cclassid, login_token);
		String flag = "success";
		arg.put("runactkey", runactkey);
		arg.put("flag", flag);
		return "ajaxpublish";
	}

	public String browsefile() throws Exception
	{
		String runactkey = Struts2Utils.getRequest().getParameter("runactkey");
		DynamicObject obj_ract = workFlowEngine.getDemandManager().getRAct(runactkey, "InfoShare");
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
		DynamicObject flowobj = get_author_common();

		String[] runactkeys = StringToolKit.split(Struts2Utils.getRequest().getParameter("runactkeys"), ",");
		if (runactkeys != null)
		{
			for (int i = 0; i < runactkeys.length; i++)
			{
				try
				{
					flowobj.setAttr("runactkey", runactkeys[i]);
					boolean isdelete = infoshareService.isdelete(flowobj);
					if(isdelete)
					{
						String id = workFlowEngine.getDemandManager().getRAct(runactkeys[i], "InfoShare").getFormatAttr("dataid");
						// infoshareService.delete(id);
					}
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

		String tableid = "InfoShare";
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

	public void set_author() throws Exception
	{
		DynamicObject flowobj = get_author_common();

		// 以下为常用权限；
		boolean isread = infoshareService.isread(flowobj); // 是否只读页面
		boolean isedit = infoshareService.isedit(flowobj); // 是否修改页面

		boolean isapply = infoshareService.isapply(flowobj); // 可否签收
		boolean isforward = infoshareService.isforward(flowobj); // 可否转发
		boolean iscallback = infoshareService.iscallback(flowobj); // 可否收回
		boolean isbackward = infoshareService.isbackward(flowobj); // 可否退回

		boolean issave = infoshareService.issave(flowobj); // 可否保存
		boolean isdelete = infoshareService.isdelete(flowobj); // 可否删除

		// 以下为特定业务权限
		boolean ispublish = infoshareService.ispublish(flowobj); // 可否发布
		boolean isupload = infoshareService.isupload(flowobj); // 可否上传文件
		
		arg.put("isread", isread);
		arg.put("isedit", isedit);

		arg.put("isapply", isapply);
		arg.put("isforward", isforward);
		arg.put("iscallback", iscallback);
		arg.put("isbackward", isbackward);

		arg.put("issave", issave);
		arg.put("isdelete", isdelete);

		arg.put("ispublish", ispublish);
		arg.put("isupload", isupload);
		
	}

	public DynamicObject get_author_common() throws Exception
	{
		// 获取各类权限
		// 流程信息
		String runactkey = Struts2Utils.getRequest().getParameter("runactkey");
		DynamicObject obj_ract = workFlowEngine.getDemandManager().getRAct(runactkey, "InfoShare");
		String id = obj_ract.getFormatAttr("dataid");

		DynamicObject flowobj = FormHelper.preparedFlowAuthor(Struts2Utils.getRequest(), Struts2Utils.getResponse());

		flowobj.setAttr(GlobalConstants.swap_tableid, "InfoShare");
		flowobj.setAttr(GlobalConstants.swap_dataid, id);

		return flowobj;
	}

	public String upload() throws Exception
	{
		HttpServletRequest req = Struts2Utils.getRequest();
		String runactkey = req.getParameter("runactkey");
		DynamicObject obj_ract = workFlowEngine.getActManager().getRAct(runactkey);

		DynamicObject amap = new DynamicObject();
		amap.put("cclass", flowclass);
		FileTemplate filetemplate = filetemplateService.locate_by(amap);

		data.put("obj_filetemplate", filetemplate);
		data.put("obj_ract", obj_ract);

		return "upload";
	}
	
	public String selectcclassname() throws Exception
	{
		// 查询资源库信息共享分类
		String cclassid = knowledgeclassService.treechild("R0", "信息共享").getId();
		KnowledgeClass knowledgeclass = knowledgeclassService.getKnowledgeClass(cclassid);
		List<KnowledgeClass> knowledgeclasses = knowledgeclassService.treechild(cclassid);
		data.put("knowledgeclass", knowledgeclass);
		data.put("knowledgeclasses", knowledgeclasses);
		return "selectcclassname";
	}
	
	public String selectchildcclassname() throws Exception
	{
		// 查询资源库信息共享分类
		String supid = Struts2Utils.getRequest().getParameter("supid");
		List<KnowledgeClass> knowledgeclasses = knowledgeclassService.treechild(supid);
		data.put("knowledgeclasses", knowledgeclasses);
		arg.put("supid", supid);
		return "selectchildcclassname";
	}


	public String selectscope() throws Exception
	{
		return "selectscope";
	}

	public String selectscopeorgan() throws Exception
	{
		List<Organ> organs = organService.getAllOrgan("ordernum", true);
		data.put("organs", organs);
		return "selectscopeorgan";
	}

	public String selectscoperole() throws Exception
	{
		List<Role> roles = roleService.getAllRole("cname", true);
		data.put("roles", roles);
		return "selectscoperole";
	}

	public String selectscopeuser() throws Exception
	{
		String organid = Struts2Utils.getRequest().getParameter("organid");
		List<User> users = userService.findOrganUsers(organid);
		data.put("users", users);

		return "selectscopeuser";
	}
	
	
	
	
	public String selectscopeusermainframe() throws Exception
	{
		return "selectscopeusermainframe";
	}	
	
	public String test() throws Exception
	{
		HttpServletRequest req = Struts2Utils.getRequest();
		HttpServletResponse resp = Struts2Utils.getResponse();
		
		DynamicObject obj = FormHelper.preparedFlow(req, resp);
		DynamicObject swapFlow = FormHelper.preparedSwapFlow(req, resp);
		
		String formula_ctx = "@DefDeptRoleByName(部门主任#10)";
		FormulaParser parser = new FormulaParser();
		parser.setJdbcDao(jdbcDao);
		parser.setSwapFlow(swapFlow);
		List owners = parser.parser(formula_ctx);
		
		data.put("owners", owners);
		
		return "test";
	}
	
	
	
	public String get_searchname()
	{
		return _searchname;
	}

	public void set_searchname(String searchname)
	{
		_searchname = searchname;
	}

	public DictionaryService getDictionaryService()
	{
		return dictionaryService;
	}

	public void setDictionaryService(DictionaryService dictionaryService)
	{
		this.dictionaryService = dictionaryService;
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

	public InfoShareService getInfoshareService()
	{
		return infoshareService;
	}

	public void setInfoshareService(InfoShareService infoshareService)
	{
		this.infoshareService = infoshareService;
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

	public KnowledgeService getKnowledgeService()
	{
		return knowledgeService;
	}

	public void setKnowledgeService(KnowledgeService knowledgeService)
	{
		this.knowledgeService = knowledgeService;
	}

	public KnowledgeClassService getKnowledgeclassService()
	{
		return knowledgeclassService;
	}

	public void setKnowledgeclassService(KnowledgeClassService knowledgeclassService)
	{
		this.knowledgeclassService = knowledgeclassService;
	}

	public OrganService getOrganService()
	{
		return organService;
	}

	public void setOrganService(OrganService organService)
	{
		this.organService = organService;
	}

	public RoleService getRoleService()
	{
		return roleService;
	}

	public void setRoleService(RoleService roleService)
	{
		this.roleService = roleService;
	}

	public UserRoleService getUserroleService()
	{
		return userroleService;
	}

	public void setUserroleService(UserRoleService userroleService)
	{
		this.userroleService = userroleService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public JdbcDao getJdbcDao()
	{
		return jdbcDao;
	}

	public void setJdbcDao(JdbcDao jdbcDao)
	{
		this.jdbcDao = jdbcDao;
	}

	public static String getTableid()
	{
		return tableid;
	}

	public static String getFlowclass()
	{
		return flowclass;
	}
	
	

}
