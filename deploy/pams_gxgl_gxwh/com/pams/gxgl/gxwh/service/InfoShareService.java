package com.pams.gxgl.gxwh.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import com.pams.dao.FileAttachmentDao;
import com.pams.dao.InfoShareDao;
import com.pams.dao.InfoShareScopeDao;
import com.pams.dao.KnowledgeClassRelationDao;
import com.pams.dao.KnowledgeDao;
import com.pams.entity.FileAttachment;
import com.pams.entity.InfoShare;
import com.pams.entity.InfoShareScope;
import com.pams.entity.Knowledge;
import com.pams.entity.KnowledgeClassRelation;
import com.pams.entity.Relay;
import com.pams.gxgl.zswh.service.KnowledgeService;
import com.ray.app.query.generator.GeneratorService;
import com.ray.app.workflow.enginee.WorkFlowEngine;
import com.ray.app.workflow.spec.DBFieldConstants;
import com.ray.app.workflow.spec.GlobalConstants;
import com.ray.app.workflow.ui.service.UIService;
import com.ray.xj.sgcc.irm.system.author.userrole.dao.UserRoleDao;

@Component
@Transactional
public class InfoShareService
{
	public final static String _tableid = "InfoShare";
	
	@Autowired
	GeneratorService generatorService;

	@Autowired
	private WorkFlowEngine workFlowEngine;

	@Autowired
	private UIService uIService;

	@Autowired
	private InfoShareDao infoshareDao;
	
	@Autowired
	private InfoShareScopeDao infosharescopeDao;	

	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private FileAttachmentDao fileattachmentDao;
	
	@Autowired
	private KnowledgeDao knowledgeDao;
	
	@Autowired
	private KnowledgeClassRelationDao knowledgeclassrelationDao;

	// 查询待办记录
	public String get_browsewait_sql(Map map) throws Exception
	{
		String cno = (String)map.get("cno");
		String title = (String)map.get("title");	
		String sourcename = (String)map.get("sourcename");	
		String cclassname = (String)map.get("cclassname");
		String creatername = (String)map.get("creatername");	
		String bactcname = (String)map.get("bactcname");
		
		StringBuffer sql = new StringBuffer();

		sql.append(" select bv.id, bv.cno, bv.title, bv.obtaintime, ").append("\n");
		sql.append(uIService.get_browsewait_field(map)).append("\n");
		sql.append(" from t_app_infoshare bv, " + uIService.get_browsewait_table(map)).append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append(uIService.get_browsewait_where(map)).append("\n");
		if (!StringToolKit.isBlank(cno))
		{
			sql.append(" and bv.cno like " + SQLParser.charLikeValue(cno));
		}		
		if (!StringToolKit.isBlank(title))
		{
			sql.append(" and bv.title like " + SQLParser.charLikeValue(title));
		}
		if (!StringToolKit.isBlank(sourcename))
		{
			sql.append(" and bv.sourcename like " + SQLParser.charLikeValue(sourcename));
		}		
		if (!StringToolKit.isBlank(cclassname))
		{
			sql.append(" and bv.cclassname like " + SQLParser.charLikeValue(cclassname));
		}
		
		if (!StringToolKit.isBlank(creatername))
		{
			sql.append(" and bv.creatername like " + SQLParser.charLikeValue(creatername));
		}		
		if (!StringToolKit.isBlank(bactcname))
		{
			sql.append(" and bact.cname like " + SQLParser.charLikeValue(bactcname));
		}		
		
		
		sql.append(" order by ract.createtime desc ").append("\n");
		return sql.toString();
	}

	// 查询已办记录
	public String get_browsehandle_sql(Map map) throws Exception
	{
		StringBuffer sql = new StringBuffer();

		sql.append(" select v.id, v.cno, v.title, v.obtaintime, v.bactid, v.bactcname, v.ractcreatetime, v.ractstate, v.runactkey, ractowner.ownerctx ractownerctx, usr.cname username").append("\n");
		sql.append("  from ( ").append("\n");
		
		sql.append(" select distinct bv.id, bv.cno, bv.title, bv.obtaintime, ").append("\n");
		sql.append(uIService.get_browsehandle_field(map)).append("\n");
		sql.append(" from t_app_infoshare bv, " + uIService.get_browsehandle_table(map)).append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append(uIService.get_browsehandle_where(map)).append("\n");
		
		sql.append("   ) v ").append("\n");
		sql.append("   left join t_sys_wfractowner ractowner ").append("\n");
		sql.append("   on v.runactkey = ractowner.runactkey ").append("\n");
		sql.append("   left join t_sys_user usr ").append("\n");
		sql.append("   on ractowner.ownerctx = usr.loginname ").append("\n");
		
		sql.append(" order by v.cno desc, v.ractcreatetime desc ").append("\n");
		return sql.toString();
	}
	
	// 查询个人全部记录
	public String get_browseall_sql(Map map) throws Exception
	{
		StringBuffer sql = new StringBuffer();

		sql.append(" select bv.id, bv.title, bv.obtaintime, ").append("\n");
		sql.append(uIService.get_browsehandle_field(map)).append("\n");
		sql.append(" from t_app_infoshare bv, " + uIService.get_browsehandle_table(map)).append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append(uIService.get_browsehandle_where(map)).append("\n");
		sql.append(" order by ract.createtime desc ").append("\n");
		return sql.toString();
	}


	// 查询全体记录（当前流程）
	public String get_browsegroupall_sql(Map map) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" select bv.id, bv.title, bv.obtaintime, ").append("\n");
		sql.append(uIService.get_browsegroupall_field(map)).append("\n");
		sql.append(" from t_app_infoshare bv, " + uIService.get_browsegroupall_table(map));
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
		sql.append(" from t_app_infoshare bv, " + uIService.get_browsegroupsall_table(map));
		sql.append(" where 1 = 1 ").append("\n");
		sql.append(uIService.get_browsegroupsall_where(map));
		sql.append(" order by ract.createtime desc ").append("\n");
		return sql.toString();
	}

	// 查询转办记录
	public String get_browserelay_sql(Map map) throws Exception
	{
		String flowsno = (String) map.get("flowsno");

		StringBuffer sql = new StringBuffer();

		sql.append(" select * from t_app_relay ");
		sql.append(" where 1 = 1 ");
		sql.append(" and isuse = 'Y' ");
		sql.append(" and state = '待处理' ");
		sql.append(" and flowsno = " + SQLParser.charValue(flowsno));

		return sql.toString();
	}

	@Transactional
	public String create(InfoShare infoshare, DynamicObject swapFlow) throws Exception
	{
		// 创建业务数据
		String cno = gen_cno();
		infoshare.setCno(cno);
		infoshareDao.save(infoshare);
		
		// 创建信息共享范围
		String infosharescope = infoshare.getInfosharescope();
		String infosharescopeid = infoshare.getInfosharescopeid();
		String infosharescopectype = infoshare.getInfosharescopectype();
		if(!StringToolKit.isBlank(infosharescopeid))
		{
			String[] infosharescopeids = StringToolKit.split(infosharescopeid, ",");
			String[] infosharescopenames = StringToolKit.split(infosharescope, ",");
			String[] infosharescopectypes = StringToolKit.split(infosharescopectype, ",");
			
			for(int i=0;i<infosharescopeids.length;i++)
			{
				InfoShareScope scope = new InfoShareScope();
				scope.setGroupid(infosharescopeids[i]);
				scope.setGroupname(infosharescopenames[i]);
				scope.setGrouptype(infosharescopectypes[i]);
				infosharescopeDao.save(scope);
			}
		}

		// 创建流程数据
		String flowdefid = swapFlow.getFormatAttr(GlobalConstants.swap_flowdefid);
		String priority = "1";
		String dataid = infoshare.getId();
		String tableid = swapFlow.getFormatAttr(GlobalConstants.swap_tableid);
		String creater = swapFlow.getFormatAttr(GlobalConstants.swap_coperatorid);
		String creatercname = swapFlow.getFormatAttr(GlobalConstants.swap_coperatorcname);

		String runactkey = workFlowEngine.getFlowManager().create(cno, flowdefid, priority, dataid, tableid, creater, creatercname);

		return runactkey;
	}
	
	public void save(InfoShare infoshare) throws Exception
	{
		infoshareDao.save(infoshare);
		
		String id = infoshare.getId();
		// 创建信息共享范围
		infosharescopeDao.batchExecute(" delete from InfoShareScope where 1 = 1 and infoshareid = " + SQLParser.charValue(id));
		
		String infosharescope = infoshare.getInfosharescope();
		String infosharescopeid = infoshare.getInfosharescopeid();
		String infosharescopectype = infoshare.getInfosharescopectype();
		if(!StringToolKit.isBlank(infosharescopeid))
		{
			String[] infosharescopeids = StringToolKit.split(infosharescopeid, ",");
			String[] infosharescopenames = StringToolKit.split(infosharescope, ",");
			String[] infosharescopectypes = StringToolKit.split(infosharescopectype, ",");
			
			for(int i=0;i<infosharescopeids.length;i++)
			{
				InfoShareScope scope = new InfoShareScope();
				scope.setInfoshareid(id);
				scope.setGroupid(infosharescopeids[i]);
				scope.setGroupname(infosharescopenames[i]);
				scope.setGrouptype(infosharescopectypes[i]);
				infosharescopeDao.save(scope);
			}
		}
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
	
	public InfoShare get(String id) throws Exception
	{
		return infoshareDao.get(id);
	}
	
	public void publish(String id, String cclassid, DynamicObject login_token) throws Exception
	{
		String loginname = login_token.getFormatAttr(com.headray.framework.spec.GlobalConstants.sys_login_user);
		String username = login_token.getFormatAttr(com.headray.framework.spec.GlobalConstants.sys_login_username);
		String deptid = login_token.getFormatAttr(com.headray.framework.spec.GlobalConstants.sys_login_dept);
		String deptname = login_token.getFormatAttr(com.headray.framework.spec.GlobalConstants.sys_login_deptname);
		
		InfoShare infoshare = infoshareDao.get(id);
		Timestamp nowtime = new Timestamp(System.currentTimeMillis());
		infoshare.setPublishtime(nowtime);
		infoshareDao.save(infoshare);
		
		String title = infoshare.getTitle();
		String memo = infoshare.getMemo();
		String summary = infoshare.getSummary();
		
		Knowledge knowledge = new Knowledge();
		knowledge.setOrigin(id);
		knowledge.setRestype(_tableid);
		knowledge.setCclassid(cclassid);
		knowledge.setTitle(title);
		knowledge.setSummary(summary);
		knowledge.setCreateuser(username);
		knowledge.setCreateuserid(loginname);
		knowledge.setCreatetime(nowtime);
		knowledge.setMauthor(username);
		knowledge.setMauthorid(loginname);
		knowledge.setMauthordept(deptname);
		knowledge.setMauthordeptid(deptid);
		knowledge.setPublisher(username);
		knowledge.setPublisherid(loginname);
		knowledge.setPublishdate(nowtime);
		knowledge.setViews(0);
		knowledge.setComments(0);
		knowledge.setCollects(0);
		knowledge.setMemo(memo);
		
		
		knowledgeDao.save(knowledge);
		String kid = knowledge.getId();
		
		// 创建共享知识与知识分类关联（知识库支持多分类）
		KnowledgeClassRelation knowledgeclassrelation = new KnowledgeClassRelation();
		knowledgeclassrelation.setKid(kid);
		knowledgeclassrelation.setClassid(cclassid);
		
		knowledgeclassrelationDao.save(knowledgeclassrelation);
		
		// 创建共享知识与附件文档关联
		StringBuffer sql = new StringBuffer();
		sql.append(" from FileAttachment where 1 = 1 and dataid = " + SQLParser.charValue(id));
		List<FileAttachment> fileattachments = fileattachmentDao.find(sql.toString());
		for(int i=0;i<fileattachments.size();i++)
		{
			FileAttachment file = fileattachments.get(i);
			FileAttachment newfile = new FileAttachment();
			
			newfile.setDataid(kid);
			newfile.setCclass(KnowledgeService._tableid);
			newfile.setFilename(file.getFilename());
			newfile.setFileextname(file.getFileextname());
			newfile.setSfilename(file.getSfilename());
			newfile.setCurl(file.getCurl());
			
			newfile.setCreater(file.getCreater());
			newfile.setCreatername(file.getCreatername());
			newfile.setCreatetime(file.getCreatetime());
			
			fileattachmentDao.save(newfile);
		}
	}

	@Transactional
	public String relayto(Relay relay, DynamicObject swapFlow) throws Exception
	{
		relay.setState("已处理");
		String runactkey = createflow(swapFlow);
		return runactkey;
	}

	@Transactional
	public InfoShare locate(String id) throws Exception
	{
		return infoshareDao.get(id);
	}

	@Transactional
	public void update(InfoShare infoshare) throws Exception
	{
		infoshareDao.save(infoshare);
	}

	@Transactional
	public void delete(String id) throws Exception
	{
		// 删除流程信息
		workFlowEngine.getFlowManager().delete("InfoShare", id);
		// 删除数据
		infoshareDao.delete(id);
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public String gen_cno()
	{
		// 生成编号
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		String sysdate = sf.format(date);
		String csql = " select substring(max(cno),length(max(cno))-3, 4) as cno from InfoShare where to_char(createtime,'yyyy-mm-dd') = to_char(to_date('" + sysdate + "', 'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd')";
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

		// 信息共享管理员具有权限
		if (isarole(loginname, "GXGL_GLY"))
		{
			sign = true;
			return sign;
		}

		// 信息共享用户具有权限
		if (isarole(loginname, "GXGL_ZZ_YH"))
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
		boolean sign = false;
		boolean isedit = workFlowEngine.getDemandManager().isEdit(map);
		
		if(!isedit)
		{
			sign = false;
			return sign;
		}
		
		String tableid = map.getFormatAttr(GlobalConstants.swap_tableid);
		String dataid = map.getFormatAttr(GlobalConstants.swap_dataid);

		String state = workFlowEngine.getDemandManager().getFlowState(tableid, dataid);
		
		// 流程在起草环节，允许保存
		if ("起草".equals(state))
		{
			sign = true;
			return sign;
		}
		
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

		// 信息共享管理员具有该功能；
		if (isarole(loginname, "GXGL_GLY"))
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
		String tableid = "InfoShare";
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

		// 信息共享管理员具有权限
		if (isarole(loginname, "GXGL_GLY"))
		{
			sign = true;
			return sign;
		}

		if (DBFieldConstants.RFlOW_STATE_COMPLETED.equals(state) || DBFieldConstants.RFlOW_STATE_TERMINATED.equals(state))
		{
			return sign;
		}
		// 判断流程是否允许删除
		boolean enabledelete = workFlowEngine.getDemandManager().checkforwarded("GXGL_ZZ", dataid);// 值真表示转未发过，值为false表示转发过

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
	
	// 是否可发布
	public boolean ispublish(DynamicObject map) throws Exception
	{
		boolean sign = false;
		
		String tableid = map.getFormatAttr(GlobalConstants.swap_tableid);
		String dataid = map.getFormatAttr(GlobalConstants.swap_dataid);

		String state = workFlowEngine.getDemandManager().getFlowState(tableid, dataid);
		
		// 流程在起草环节，允许保存
		if (!"信息共享".equals(state))
		{
			sign = false;
			return sign;
		}
		
		Timestamp publishtime = infoshareDao.get(dataid).getPublishtime();
		if(publishtime==null)
		{
			sign = true;
			return sign;
		}
		
		return sign;
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

	public UIService getuIService()
	{
		return uIService;
	}

	public void setuIService(UIService uIService)
	{
		this.uIService = uIService;
	}

	public InfoShareDao getInfoshareDao()
	{
		return infoshareDao;
	}

	public void setInfoshareDao(InfoShareDao infoshareDao)
	{
		this.infoshareDao = infoshareDao;
	}

	public InfoShareScopeDao getInfosharescopeDao()
	{
		return infosharescopeDao;
	}

	public void setInfosharescopeDao(InfoShareScopeDao infosharescopeDao)
	{
		this.infosharescopeDao = infosharescopeDao;
	}

	public UserRoleDao getUserRoleDao()
	{
		return userRoleDao;
	}

	public void setUserRoleDao(UserRoleDao userRoleDao)
	{
		this.userRoleDao = userRoleDao;
	}

	public FileAttachmentDao getFileattachmentDao()
	{
		return fileattachmentDao;
	}

	public void setFileattachmentDao(FileAttachmentDao fileattachmentDao)
	{
		this.fileattachmentDao = fileattachmentDao;
	}

	public KnowledgeDao getKnowledgeDao()
	{
		return knowledgeDao;
	}

	public void setKnowledgeDao(KnowledgeDao knowledgeDao)
	{
		this.knowledgeDao = knowledgeDao;
	}

	public KnowledgeClassRelationDao getKnowledgeclassrelationDao()
	{
		return knowledgeclassrelationDao;
	}

	public void setKnowledgeclassrelationDao(KnowledgeClassRelationDao knowledgeclassrelationDao)
	{
		this.knowledgeclassrelationDao = knowledgeclassrelationDao;
	}

	public static String getTableid()
	{
		return _tableid;
	}

}
