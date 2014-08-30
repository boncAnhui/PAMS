package com.pams.gxgl.zswh.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.action.SimpleAction;
import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.services.function.StringToolKit;
import com.headray.framework.spec.GlobalConstants;
import com.pams.entity.FileAttachment;
import com.pams.entity.Knowledge;
import com.pams.entity.KnowledgeClass;
import com.pams.entity.KnowledgeComment;
import com.pams.gxgl.wjwh.service.FileAttachmentService;
import com.pams.gxgl.zsflwh.service.KnowledgeClassService;
import com.pams.gxgl.zsplwh.service.KnowledgeCommentService;
import com.pams.gxgl.zswh.service.KnowledgeService;
import com.ray.app.dictionary.service.DictionaryService;
import com.ray.app.query.service.QueryService;

public class KnowledgeAction extends SimpleAction
{
	private String _searchname;

	@Autowired
	private QueryService queryService;

	@Autowired
	private DictionaryService dictionaryService;

	@Autowired
	private KnowledgeClassService knowledgeclassService;

	@Autowired
	private KnowledgeService knowledgeService;

	@Autowired
	private KnowledgeCommentService knowledgecommentService;

	@Autowired
	private FileAttachmentService fileattachmentService;

	public String mainframe() throws Exception
	{
		String rootid = StringToolKit.formatText(Struts2Utils.getRequest().getParameter("rootid"), "R0");
		String rootname = "";

		String foldertype = "";
		if ("R0".equals(rootid))
		{
			rootname = "共享资源库";
		}
		else
		{
			KnowledgeClass knowledgeClass = knowledgeclassService.getKnowledgeClass(rootid);
			rootname = knowledgeClass.getCname();
		}

		arg.put("rootid", rootid);
		arg.put("rootname", rootname);

		return "mainframe";
	}

	public String treechild() throws Exception
	{
		String cclassid = Struts2Utils.getRequest().getParameter("cclassid");
		List<KnowledgeClass> knowledgeClasses = knowledgeclassService.treechild(cclassid);
		data.put("list", knowledgeClasses);
		return "treechild";
	}

	public String browse() throws Exception
	{
		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token);
		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);
		
		String cclassid = Struts2Utils.getRequest().getParameter("cclassid");
		String title = Struts2Utils.getRequest().getParameter("title");
		String kno = Struts2Utils.getRequest().getParameter("kno");
		String mauthor = Struts2Utils.getRequest().getParameter("mauthor");
		String mauthordept = Struts2Utils.getRequest().getParameter("mauthordept");
		String beginpublishdate = Struts2Utils.getRequest().getParameter("beginpublishdate");
		String endpublishdate = Struts2Utils.getRequest().getParameter("endpublishdate");
		
		DynamicObject map = new DynamicObject();
		map.setAttr("cclassid", cclassid);
		map.setAttr("loginname", loginname);
		map.setAttr("title", title);
		map.setAttr("kno", kno);
		map.setAttr("mauthor", mauthor);
		map.setAttr("mauthordept", mauthordept);
		map.setAttr("beginpublishdate", beginpublishdate);
		map.setAttr("endpublishdate", endpublishdate);
		
		List knowledges = knowledgeService.browse(map);

		// 查看知识分类属性（是否文件库、文件柜、文件柜，文件夹方可允许新增文档）
		KnowledgeClass knowledgeclass = knowledgeclassService.getKnowledgeClass(cclassid);

		String foldertype = knowledgeclass.getFoldertype();
		String leaf = knowledgeclass.getIslast();

		data.put("knowledges", knowledges);
		
		arg.put("cclassid", cclassid);
		arg.put("title", title);
		arg.put("kno", kno);
		arg.put("mauthor", mauthor);
		arg.put("mauthordept", mauthordept);
		arg.put("beginpublishdate", beginpublishdate);
		arg.put("endpublishdate", endpublishdate);		
		
		arg.put("leaf", leaf);
		arg.put("foldertype", foldertype);

		return "browse";
	}

	public String readpage() throws Exception
	{
		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token);
		String username = login_token.getFormatAttr(GlobalConstants.sys_login_username);
		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);

		String id = Struts2Utils.getRequest().getParameter("id");
		String cclassid = Struts2Utils.getRequest().getParameter("cclassid");

		Knowledge knowledge = knowledgeService.get(id);
		if ("undefined".equals(cclassid) || StringToolKit.isBlank(cclassid))
		{
			cclassid = knowledge.getCclassid();
		}

		// 目录进行计算
		String cclassallname = knowledgeclassService.getCclassallname(cclassid);
		List<KnowledgeComment> knowledgecomment = knowledgecommentService.findBy("kno", id, Order.desc("sugdate"));

		Map amap = new HashMap();
		amap.put("dataid", id);
		List<FileAttachment> fileattachments = fileattachmentService.find(amap);

		Integer getViews = knowledge.getViews();
		if (getViews == null)
		{
			getViews = 1;
		}
		else
		{
			getViews = getViews + 1;
		}

		knowledge.setViews(getViews);

		knowledgeService.save(knowledge);

		data.put("knowledge", knowledge);
		data.put("fileattachments", fileattachments);

		data.put("knowledgecomment", knowledgecomment);

		arg.put("views", getViews);

		arg.put("username", username);

		arg.put("id", id);
		arg.put("cclassid", cclassid);
		arg.put("cclassallname", cclassallname);
		return "readpage";
	}

	public String locate() throws Exception
	{
		String id = Struts2Utils.getRequest().getParameter("id");
		String cclassid = Struts2Utils.getRequest().getParameter("cclassid");
		Knowledge knowledge = knowledgeService.get(id);
		String cclassallname = knowledgeclassService.getCclassallname(cclassid);

		Map amap = new HashMap();
		amap.put("dataid", id);
		List<FileAttachment> fileattachments = fileattachmentService.find(amap);

		data.put("knowledge", knowledge);
		data.put("fileattachments", fileattachments);

		arg.put("cclassallname", cclassallname);
		arg.put("id", id);
		arg.put("cclassid", cclassid);

		return "locate";
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

	public DictionaryService getDictionaryService()
	{
		return dictionaryService;
	}

	public void setDictionaryService(DictionaryService dictionaryService)
	{
		this.dictionaryService = dictionaryService;
	}

	public KnowledgeClassService getKnowledgeclassService()
	{
		return knowledgeclassService;
	}

	public void setKnowledgeclassService(KnowledgeClassService knowledgeclassService)
	{
		this.knowledgeclassService = knowledgeclassService;
	}

	public KnowledgeService getKnowledgeService()
	{
		return knowledgeService;
	}

	public void setKnowledgeService(KnowledgeService knowledgeService)
	{
		this.knowledgeService = knowledgeService;
	}

}
