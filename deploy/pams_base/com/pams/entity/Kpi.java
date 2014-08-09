package com.pams.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.blue.ssh.core.entity.IdEntity;

@Entity
@Table(name = "T_APP_KPI")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = false)
public class Kpi extends IdEntity
{
	private String flowdefid; //流程标识
	
	private String actdefid; // 流程节点标识
	
	private String cname; //指标名称
	
	private String pname; //指标英文名称
	
	private String datatype; //指标数据类型
	
	private String dataunit; //数据单位
	
	private String scopetype; // 指标应用范围类型（KPI应用于流程或单个节点）
	
	private double dvalue; // 指标要求值

	public String getFlowdefid()
	{
		return flowdefid;
	}

	public void setFlowdefid(String flowdefid)
	{
		this.flowdefid = flowdefid;
	}

	public String getActdefid()
	{
		return actdefid;
	}

	public void setActdefid(String actdefid)
	{
		this.actdefid = actdefid;
	}

	public String getCname()
	{
		return cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
	}

	public String getPname()
	{
		return pname;
	}

	public void setPname(String pname)
	{
		this.pname = pname;
	}

	public String getDatatype()
	{
		return datatype;
	}

	public void setDatatype(String datatype)
	{
		this.datatype = datatype;
	}

	public String getDataunit()
	{
		return dataunit;
	}

	public void setDataunit(String dataunit)
	{
		this.dataunit = dataunit;
	}

	public String getScopetype()
	{
		return scopetype;
	}

	public void setScopetype(String scopetype)
	{
		this.scopetype = scopetype;
	}

	public double getDvalue()
	{
		return dvalue;
	}

	public void setDvalue(double dvalue)
	{
		this.dvalue = dvalue;
	}
	
}
