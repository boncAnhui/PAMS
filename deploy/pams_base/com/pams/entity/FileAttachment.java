package com.pams.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.blue.ssh.core.entity.IdEntity;

@Entity
@Table(name = "T_APP_FILEATTACHMENT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = false)
// 文档附件
public class FileAttachment extends IdEntity
{
	private String filetemplateid; //文档模板标识
	
	private String cclass; // 业务分类
	
	private String dataid; // 业务数据标识

	private String flowdefid; // 流程标识

	private String flowsno; // 流程序列号

	private String actdefid; // 活动定义节点

	private String actname; // 活动定义节点名称

	private String runflowkey; // 流程实例标识

	private String runactkey; // 活动实例标识

	private String cno; // 编号

	private String cname; // 文件名称 （系统定义名）

	private String ctype; // 文件类型 （系统定义类型）

	private String property; // 文件属性

	private String required; // 是否必要
	
	private String creater; // 创建人账号（上传人）
	
	private String creatername; // 创建人姓名（上传人）

	private Timestamp createtime; // 创建时间（上传人）

	private String filename; // 文件实际名称（上传文件保存名）
	
	private String sfilename; // 文件实际名称（上传文件名）
	
	private String fileextname; // 附件文件扩展名	

	private String curl; // 文件实际地址（上传文件地址）

	public String getFiletemplateid()
	{
		return filetemplateid;
	}

	public void setFiletemplateid(String filetemplateid)
	{
		this.filetemplateid = filetemplateid;
	}

	public String getCclass()
	{
		return cclass;
	}

	public void setCclass(String cclass)
	{
		this.cclass = cclass;
	}

	public String getDataid()
	{
		return dataid;
	}

	public void setDataid(String dataid)
	{
		this.dataid = dataid;
	}

	public String getFlowdefid()
	{
		return flowdefid;
	}

	public void setFlowdefid(String flowdefid)
	{
		this.flowdefid = flowdefid;
	}

	public String getFlowsno()
	{
		return flowsno;
	}

	public void setFlowsno(String flowsno)
	{
		this.flowsno = flowsno;
	}

	public String getActdefid()
	{
		return actdefid;
	}

	public void setActdefid(String actdefid)
	{
		this.actdefid = actdefid;
	}

	public String getActname()
	{
		return actname;
	}

	public void setActname(String actname)
	{
		this.actname = actname;
	}

	public String getRunflowkey()
	{
		return runflowkey;
	}

	public void setRunflowkey(String runflowkey)
	{
		this.runflowkey = runflowkey;
	}

	public String getRunactkey()
	{
		return runactkey;
	}

	public void setRunactkey(String runactkey)
	{
		this.runactkey = runactkey;
	}

	public String getCno()
	{
		return cno;
	}

	public void setCno(String cno)
	{
		this.cno = cno;
	}

	public String getCname()
	{
		return cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
	}

	public String getCtype()
	{
		return ctype;
	}

	public void setCtype(String ctype)
	{
		this.ctype = ctype;
	}

	public String getProperty()
	{
		return property;
	}

	public void setProperty(String property)
	{
		this.property = property;
	}

	public String getRequired()
	{
		return required;
	}

	public void setRequired(String required)
	{
		this.required = required;
	}

	public String getCreater()
	{
		return creater;
	}

	public void setCreater(String creater)
	{
		this.creater = creater;
	}

	public String getCreatername()
	{
		return creatername;
	}

	public void setCreatername(String creatername)
	{
		this.creatername = creatername;
	}

	public Timestamp getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Timestamp createtime)
	{
		this.createtime = createtime;
	}

	public String getFilename()
	{
		return filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	public String getSfilename()
	{
		return sfilename;
	}

	public void setSfilename(String sfilename)
	{
		this.sfilename = sfilename;
	}

	public String getFileextname()
	{
		return fileextname;
	}

	public void setFileextname(String fileextname)
	{
		this.fileextname = fileextname;
	}

	public String getCurl()
	{
		return curl;
	}

	public void setCurl(String curl)
	{
		this.curl = curl;
	}

}
