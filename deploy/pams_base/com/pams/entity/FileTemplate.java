package com.pams.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.blue.ssh.core.entity.IdEntity;

@Entity
@Table(name = "T_APP_FILETEMPLATE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = false)
// 文档模板
public class FileTemplate extends IdEntity
{
	private String cclass; // 业务分类

	private String flowdefid; // 流程标识

	private String flowsno; // 流程序列号

	private String actdefid; // 活动定义节点

	private String actname; // 活动定义节点名称

	private String cno; // 编号

	private String cname; // 文件名称 （系统定义名）

	private String ctype; // 文件类型 （系统定义类型）

	private String property; // 文件属性

	private String required; // 是否必要

	public String getCclass()
	{
		return cclass;
	}

	public void setCclass(String cclass)
	{
		this.cclass = cclass;
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
	
	

}
