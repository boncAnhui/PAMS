package com.pams.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.blue.ssh.core.entity.IdEntity;

@Entity
@Table(name = "T_APP_PLAN")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = false)
// 信息共享单
public class Plan extends IdEntity
{
	private String cno; // 计划编号
	
	private String title; // 计划标题
	
	private Timestamp planbegintime; //
	
	private Timestamp planendtime; //
	
	private int periodnum; // 标准工期时长

	private String periodunit; // 标准工期时长单元
	
	private int planperiodnum; // 计划工期时长

	private String planperiodunit; // 计划工期时长单元
	
	private String headername; //
	
	private String header; //
	
	private String headerdeptname; //
	
	private String headerdept; //
	
	private String supid; //
	
	private String unittype; //
	
	private String planmodelid; //
	
	private String version; //
	
	private String state; //
	
	private String creatername; //
	
	private String creater; //
	
	private String createrdeptname; //
	
	private String createrdept; //
	
	private Timestamp createtime; //
	
	private Timestamp realbegintime; //
	
	private Timestamp realendtime; //
	
	private int realperiodnum; // 实际工期时长

	private String realperiodunit; // 实际工期时长单元	
	
	private String realheadername; //
	
	private String realheader; //
	
	private String realheaderdeptname; //
	
	private String realheaderdept; //
	
	private String memo; //
	
	private String flowdefid; //
	
	private String actdefid; //
	
	private String runflowkey; //
	
	private String runactkey; //
	
	private String tlevel; // 层级

	private String islast;// 是否叶子节点   1-不是  0-是	
	
	private String internal;//内部码  用于记录树形关系及计算层级	

	public String getCno()
	{
		return cno;
	}

	public void setCno(String cno)
	{
		this.cno = cno;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Timestamp getPlanbegintime()
	{
		return planbegintime;
	}

	public void setPlanbegintime(Timestamp planbegintime)
	{
		this.planbegintime = planbegintime;
	}

	public Timestamp getPlanendtime()
	{
		return planendtime;
	}

	public void setPlanendtime(Timestamp planendtime)
	{
		this.planendtime = planendtime;
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

	public int getPlanperiodnum()
	{
		return planperiodnum;
	}

	public void setPlanperiodnum(int planperiodnum)
	{
		this.planperiodnum = planperiodnum;
	}

	public String getPlanperiodunit()
	{
		return planperiodunit;
	}

	public void setPlanperiodunit(String planperiodunit)
	{
		this.planperiodunit = planperiodunit;
	}

	public String getHeadername()
	{
		return headername;
	}

	public void setHeadername(String headername)
	{
		this.headername = headername;
	}

	public String getHeader()
	{
		return header;
	}

	public void setHeader(String header)
	{
		this.header = header;
	}

	public String getHeaderdeptname()
	{
		return headerdeptname;
	}

	public void setHeaderdeptname(String headerdeptname)
	{
		this.headerdeptname = headerdeptname;
	}

	public String getHeaderdept()
	{
		return headerdept;
	}

	public void setHeaderdept(String headerdept)
	{
		this.headerdept = headerdept;
	}

	public String getSupid()
	{
		return supid;
	}

	public void setSupid(String supid)
	{
		this.supid = supid;
	}

	public String getUnittype()
	{
		return unittype;
	}

	public void setUnittype(String unittype)
	{
		this.unittype = unittype;
	}

	public String getPlanmodelid()
	{
		return planmodelid;
	}

	public void setPlanmodelid(String planmodelid)
	{
		this.planmodelid = planmodelid;
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

	public String getCreatername()
	{
		return creatername;
	}

	public void setCreatername(String creatername)
	{
		this.creatername = creatername;
	}

	public String getCreater()
	{
		return creater;
	}

	public void setCreater(String creater)
	{
		this.creater = creater;
	}

	public String getCreaterdeptname()
	{
		return createrdeptname;
	}

	public void setCreaterdeptname(String createrdeptname)
	{
		this.createrdeptname = createrdeptname;
	}

	public String getCreaterdept()
	{
		return createrdept;
	}

	public void setCreaterdept(String createrdept)
	{
		this.createrdept = createrdept;
	}

	public Timestamp getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Timestamp createtime)
	{
		this.createtime = createtime;
	}

	public Timestamp getRealbegintime()
	{
		return realbegintime;
	}

	public void setRealbegintime(Timestamp realbegintime)
	{
		this.realbegintime = realbegintime;
	}

	public Timestamp getRealendtime()
	{
		return realendtime;
	}

	public void setRealendtime(Timestamp realendtime)
	{
		this.realendtime = realendtime;
	}

	public int getRealperiodnum()
	{
		return realperiodnum;
	}

	public void setRealperiodnum(int realperiodnum)
	{
		this.realperiodnum = realperiodnum;
	}

	public String getRealperiodunit()
	{
		return realperiodunit;
	}

	public void setRealperiodunit(String realperiodunit)
	{
		this.realperiodunit = realperiodunit;
	}

	public String getRealheadername()
	{
		return realheadername;
	}

	public void setRealheadername(String realheadername)
	{
		this.realheadername = realheadername;
	}

	public String getRealheader()
	{
		return realheader;
	}

	public void setRealheader(String realheader)
	{
		this.realheader = realheader;
	}

	public String getRealheaderdeptname()
	{
		return realheaderdeptname;
	}

	public void setRealheaderdeptname(String realheaderdeptname)
	{
		this.realheaderdeptname = realheaderdeptname;
	}

	public String getRealheaderdept()
	{
		return realheaderdept;
	}

	public void setRealheaderdept(String realheaderdept)
	{
		this.realheaderdept = realheaderdept;
	}

	public String getMemo()
	{
		return memo;
	}

	public void setMemo(String memo)
	{
		this.memo = memo;
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

	public String getTlevel()
	{
		return tlevel;
	}

	public void setTlevel(String tlevel)
	{
		this.tlevel = tlevel;
	}

	public String getIslast()
	{
		return islast;
	}

	public void setIslast(String islast)
	{
		this.islast = islast;
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
