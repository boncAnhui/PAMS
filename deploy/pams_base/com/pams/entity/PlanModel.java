package com.pams.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.blue.ssh.core.entity.IdEntity;

@Entity
@Table(name = "T_APP_PLANMODEL")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = false)
// 信息共享单
public class PlanModel extends IdEntity
{
	private String cname; // 模板名称
	
	private String sno; // 模板编码
	
	private String flowdefid; // 流程定义标识
	
	private String actdefid; // 活动定义标识

	private int periodnum; // 标准工期时长

	private String periodunit; // 标准工期时长单元
	
	private String supmodelid; // 上级模板标识
	
	private String unittype; // 计划单元类型 （工作包、工作单元）
	
	private String version; // 版本号
	
	private String state; // 状态
	
	private String internal; //	内部吗

	public String getCname()
	{
		return cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
	}

	public String getSno()
	{
		return sno;
	}

	public void setSno(String sno)
	{
		this.sno = sno;
	}

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

	public int getPeriodnum()
	{
		return periodnum;
	}

	public void setPeriodnum(int periodnum)
	{
		this.periodnum = periodnum;
	}

	public String getPeriodunit()
	{
		return periodunit;
	}

	public void setPeriodunit(String periodunit)
	{
		this.periodunit = periodunit;
	}

	public String getSupmodelid()
	{
		return supmodelid;
	}

	public void setSupmodelid(String supmodelid)
	{
		this.supmodelid = supmodelid;
	}

	public String getUnittype()
	{
		return unittype;
	}

	public void setUnittype(String unittype)
	{
		this.unittype = unittype;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getInternal()
	{
		return internal;
	}

	public void setInternal(String internal)
	{
		this.internal = internal;
	}
	
	
}
