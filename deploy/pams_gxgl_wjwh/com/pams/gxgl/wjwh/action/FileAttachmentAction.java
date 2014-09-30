package com.pams.gxgl.wjwh.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.action.SimpleAction;
import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.common.generator.FormatKey;
import com.headray.framework.common.generator.UUIDGenerator;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.services.function.StringToolKit;
import com.headray.framework.spec.GlobalConstants;
import com.pams.entity.FileAttachment;
import com.pams.entity.FileTemplate;
import com.pams.gxgl.wjwh.service.FileAttachmentService;
import com.pams.gxgl.wjwh.service.FileTemplateService;
import com.ray.app.dictionary.entity.Dictionary;
import com.ray.app.dictionary.service.DictionaryService;
import com.ray.app.query.service.QueryService;
import com.ray.app.workflow.enginee.WorkFlowEngine;

public class FileAttachmentAction extends SimpleAction
{
	private String _searchname;

	@Autowired
	private QueryService queryService;

	@Autowired
	private DictionaryService dictionaryService;

	private File fupload;

	private String fuploadFileName;

	private String realpath;

	private String inputpath;

	private InputStream inputStream;

	private String downFileName;

	@Autowired
	private FileAttachmentService fileattachmentService;

	@Autowired
	private FileTemplateService filetemplateService;

	@Autowired
	private WorkFlowEngine workFlowEngine;

	// public String upload() throws Exception
	// {
	// DynamicObject login_token = (DynamicObject)
	// Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token);
	// String loginname =
	// login_token.getFormatAttr(GlobalConstants.sys_login_user);
	// String username =
	// login_token.getFormatAttr(GlobalConstants.sys_login_username);
	// Timestamp nowtime = new Timestamp(System.currentTimeMillis());
	//
	// String runactkey = Struts2Utils.getRequest().getParameter("runactkey");
	// // 活动实例标识
	// String cno = Struts2Utils.getRequest().getParameter("cno"); // 文件模板编号
	// String cclass = Struts2Utils.getRequest().getParameter("cclass"); // 分类
	//
	// DynamicObject obj_ract =
	// workFlowEngine.getActManager().getRAct(runactkey);
	// String actdefid = obj_ract.getFormatAttr("actdefid");
	// String flowdefid = obj_ract.getFormatAttr("flowdefid");
	// DynamicObject obj_rflow =
	// workFlowEngine.getDemandManager().getBFlow(flowdefid);
	// DynamicObject obj_bact =
	// workFlowEngine.getDemandManager().getBAct(actdefid);
	//
	// Map amap = new DynamicObject();
	// amap.put("actdefid", actdefid);
	// amap.put("cno", cno);
	// amap.put("cclass", cclass);
	// FileTemplate filetemplate = filetemplateService.locate_by(amap);
	//
	// // 保存上传附件
	// String dir = String.valueOf(new GregorianCalendar().get(Calendar.YEAR));
	//
	// try
	// {
	// Map<String, String> map = uploaddoc(dir);
	// // 保存附件记录;
	// FileAttachment fileattachment = new FileAttachment();
	// fileattachment.setFiletemplateid(filetemplate.getId());
	// fileattachment.setCclass(cclass);
	// fileattachment.setDataid(obj_ract.getFormatAttr("dataid"));
	// fileattachment.setFlowdefid(obj_ract.getFormatAttr("flowdefid"));
	// fileattachment.setFlowsno(obj_rflow.getFormatAttr("sno"));
	// fileattachment.setActdefid(actdefid);
	// fileattachment.setActname(obj_bact.getFormatAttr("cname"));
	// fileattachment.setRunflowkey(obj_ract.getFormatAttr("runflowkey"));
	// fileattachment.setRunactkey(runactkey);
	// fileattachment.setCno(cno);
	// fileattachment.setCname(filetemplate.getCname());
	// fileattachment.setCtype(filetemplate.getCtype());
	// fileattachment.setProperty(filetemplate.getProperty());
	// fileattachment.setRequired(filetemplate.getRequired());
	// fileattachment.setCreater(loginname);
	// fileattachment.setCreatername(username);
	// fileattachment.setCreatetime(nowtime);
	// fileattachment.setSfilename(fuploadFileName);
	// fileattachment.setFilename(map.get("filename"));
	// fileattachment.setFileextname(map.get("fileextname"));
	// fileattachment.setCurl(dir.replace('\\', '/'));
	// fileattachmentService.create(fileattachment);
	//
	// // 返回
	// SimpleDateFormat simpleDateFormat = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm");
	// HttpServletResponse response = ServletActionContext.getResponse();
	// // response.getWriter().flush();
	// response.getWriter().append("['" + fileattachment.getCurl() + "','" +
	// fileattachment.getId() + "','" + fileattachment.getCname() + "','" +
	// fileattachment.getCreater() + "','" +
	// simpleDateFormat.format(fileattachment.getCreatetime()) + "']");
	//
	// }
	// catch (Exception e)
	// {
	// // 返回
	// HttpServletResponse response = ServletActionContext.getResponse();
	// response.getWriter().append(e.getMessage());
	// }
	//
	// return null;
	// }

	public String upload() throws Exception
	{
		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token);
		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);
		String username = login_token.getFormatAttr(GlobalConstants.sys_login_username);
		Timestamp nowtime = new Timestamp(System.currentTimeMillis());

		String runactkey = Struts2Utils.getRequest().getParameter("runactkey"); // 活动实例标识
		String filetemplateid = Struts2Utils.getRequest().getParameter("filetemplateid");

		DynamicObject obj_ract = workFlowEngine.getActManager().getRAct(runactkey);
		String actdefid = obj_ract.getFormatAttr("actdefid");
		String flowdefid = obj_ract.getFormatAttr("flowdefid");
		DynamicObject obj_rflow = workFlowEngine.getDemandManager().getBFlow(flowdefid);
		DynamicObject obj_bact = workFlowEngine.getDemandManager().getBAct(actdefid);

		Map amap = new DynamicObject();
		amap.put("id", filetemplateid);

		FileTemplate filetemplate = filetemplateService.locate_by(amap);
		
		String cclass = filetemplate.getCclass();
		String cclassname = filetemplate.getCclassname();

		String cno = filetemplate.getCno();

		Calendar cal = new GregorianCalendar();
		// 保存上传附件
		String dir = cclassname + "\\" + cal.get(Calendar.YEAR) + "\\" + (cal.get(Calendar.YEAR) + "" + FormatKey.format(cal.get(Calendar.MONTH)+1, 2)) + "\\" + ( cal.get(Calendar.YEAR) + "" + FormatKey.format(cal.get(Calendar.MONTH)+1, 2) + "" + FormatKey.format(cal.get(Calendar.DATE), 2));
		try
		{
			Map<String, String> map = uploaddoc(dir);
			// 保存附件记录;
			FileAttachment fileattachment = new FileAttachment();
			fileattachment.setFiletemplateid(filetemplate.getId());
			fileattachment.setCclass(cclass);
			fileattachment.setDataid(obj_ract.getFormatAttr("dataid"));
			fileattachment.setFlowdefid(obj_ract.getFormatAttr("flowdefid"));
			fileattachment.setFlowsno(obj_rflow.getFormatAttr("sno"));
			fileattachment.setActdefid(actdefid);
			fileattachment.setActname(obj_bact.getFormatAttr("cname"));
			fileattachment.setRunflowkey(obj_ract.getFormatAttr("runflowkey"));
			fileattachment.setRunactkey(runactkey);
			fileattachment.setCno(cno);
			fileattachment.setCname(filetemplate.getCname());
			fileattachment.setCtype(filetemplate.getCtype());
			fileattachment.setProperty(filetemplate.getProperty());
			fileattachment.setRequired(filetemplate.getRequired());
			fileattachment.setCreater(loginname);
			fileattachment.setCreatername(username);
			fileattachment.setCreatetime(nowtime);
			fileattachment.setSfilename(fuploadFileName);
			fileattachment.setFilename(map.get("filename"));
			fileattachment.setFileextname(map.get("fileextname"));
			fileattachment.setCurl(dir.replace('\\', '/'));
			fileattachmentService.create(fileattachment);

			// 返回
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			HttpServletResponse response = ServletActionContext.getResponse();
			// response.getWriter().flush();
			response.getWriter().append("['" + fileattachment.getCurl() + "','" + fileattachment.getId() + "','" + fileattachment.getCname() + "','" + fileattachment.getCreater() + "','" + simpleDateFormat.format(fileattachment.getCreatetime()) + "']");

		}
		catch (Exception e)
		{
			// 返回
			HttpServletResponse response = ServletActionContext.getResponse();
			response.getWriter().append(e.getMessage());
		}

		return null;
	}

	public String downloadbyid() throws Exception
	{
		String id = Struts2Utils.getRequest().getParameter("id");

		FileAttachment attach = fileattachmentService.get(id);
		fuploadFileName = attach.getFilename();
		String sname = attach.getSfilename();
		inputpath = attach.getFilename();

		Dictionary dictionary = dictionaryService.findUniqueBy("dkey", "app.system.attach.root");
		String root = dictionary.getDvalue();

		realpath = root + "upload\\";
		inputpath = attach.getCurl() + "\\" + inputpath;

		try
		{
			System.out.println(realpath + inputpath);
			inputStream = new FileInputStream(realpath + inputpath);
		}
		catch (Exception e)
		{
			System.out.println(e);
			throw new Exception("未找到该文件！");
		}

		this.downFileName = sname;

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse respone = ServletActionContext.getResponse();

		String Agent = request.getHeader("User-Agent");

		if (null != Agent)
		{
			Agent = Agent.toLowerCase();
			if (Agent.indexOf("firefox") != -1)
			{
				downFileName = new String(downFileName.getBytes(), "iso8859-1");
			}
			else if (Agent.indexOf("msie") != -1)
			{
				downFileName = java.net.URLEncoder.encode(downFileName, "UTF-8");
			}
			else
			{
				downFileName = java.net.URLEncoder.encode(downFileName, "UTF-8");
			}
		}

		return SUCCESS;
	}

	protected Map<String, String> uploaddoc(String dir) throws Exception
	{
		// 根据classid查找分类目录并按照层级转换为完整目录名；
		// 根据完整目录名创建附件目录，并上传附件至该目录；
		Map map = new HashMap();

		Dictionary dictionary = dictionaryService.findUniqueBy("dkey", "app.system.attach.root");
		String root = dictionary.getDvalue();

		if (StringToolKit.isBlank(root))
		{
			// throw new Exception("系统未设定上传文档目录，无法上传，请联系系统管理员！");
			throw new Exception("error");
		}

		String rootdir = root + "upload";
		String webrootdir = "/upload";

		File rootdirfile = new File(rootdir);
		if (!rootdirfile.isDirectory())
		{
			// throw new Exception("系统未创建上传文档目录，无法上传，请联系系统管理员！");
			throw new Exception("error");
		}

		String extName = "";
		String newFileName = "";
		String nowTimeStr = "";

		Random r = new Random();

		HttpServletResponse response = ServletActionContext.getResponse();

		response.setCharacterEncoding("utf-8");

		if (fuploadFileName.lastIndexOf(".") >= 0)
		{
			extName = fuploadFileName.substring(fuploadFileName.lastIndexOf(".") + 1);
		}

		newFileName = UUIDGenerator.getInstance().getNextValue() + "." + extName;
		
		String alldirname = rootdir + "\\" + dir;
		create_folders(alldirname);
		
		fupload.renameTo(new File(alldirname + "\\" + newFileName));

		map.put("filename", newFileName);
		map.put("fileextname", extName);

		return map;
	}

	public static void create_folders(final String folders)
	{
		StringTokenizer st = new StringTokenizer(folders, File.separator);
		StringBuilder sb = new StringBuilder();
		String osname = System.getProperty("os.name");
		if (osname.compareToIgnoreCase("linux") == 0)
			sb.append(File.separator);

		while (st.hasMoreTokens())
		{
			sb.append(st.nextToken());
			File file = new File(sb.toString());
			if (!file.exists())
				file.mkdir();
			sb.append(File.separator);
		}
	}

	// 删除上传文件(包括数据库记录以及服务器文件)
	public String ajaxdelete() throws Exception
	{
		String attachid = Struts2Utils.getRequest().getParameter("attachid");
		String flag = "";
		try
		{
			fileattachmentService.delete(attachid);
			flag = "done";
		}
		catch (Exception e)
		{
			System.out.println("删除文件操作出错");
			e.printStackTrace();
			flag = "error";
		}

		arg.put("flag", flag);

		return "ajaxdelete";

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

	public File getFupload()
	{
		return fupload;
	}

	public void setFupload(File fupload)
	{
		this.fupload = fupload;
	}

	public String getFuploadFileName()
	{
		return fuploadFileName;
	}

	public void setFuploadFileName(String fuploadFileName)
	{
		this.fuploadFileName = fuploadFileName;
	}

	public String getRealpath()
	{
		return realpath;
	}

	public void setRealpath(String realpath)
	{
		this.realpath = realpath;
	}

	public String getInputpath()
	{
		return inputpath;
	}

	public void setInputpath(String inputpath)
	{
		this.inputpath = inputpath;
	}

	public InputStream getInputStream()
	{
		return inputStream;
	}

	public void setInputStream(InputStream inputStream)
	{
		this.inputStream = inputStream;
	}

	public String getDownFileName()
	{
		return downFileName;
	}

	public void setDownFileName(String downFileName)
	{
		this.downFileName = downFileName;
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

	public WorkFlowEngine getWorkFlowEngine()
	{
		return workFlowEngine;
	}

	public void setWorkFlowEngine(WorkFlowEngine workFlowEngine)
	{
		this.workFlowEngine = workFlowEngine;
	}

}
