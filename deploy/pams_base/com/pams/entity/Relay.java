package com.pams.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.blue.ssh.core.entity.IdEntity;

@Entity
@Table(name = "T_APP_RELAY")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = false)
// 户表申请单
public class Relay extends IdEntity
{
	private String cno; // 转办单号
	
	private String fromuserid; // 转办人账号

	private String fromusername; // 转办人姓名
	
	private String touserid; // 接收人账号

	private String tousername; // 接收人姓名	
	
	private String dataid; // 业务数据标识
	
	private String tableid; // 业务表标识
	
	private String runflowkey; // 流程实例标识
	
	private String flowsno; // 流程序列号

	private String state;  // 处理状态（待处理、已处理）
	
	private String isuse;  // 当前可用状态
	
	public String getCno()
	{
		return cno;
	}

	public void setCno(String cno)
	{
		this.cno = cno;
	}

	public String getFromuserid()
	{
		return fromuserid;
	}

	public void setFromuserid(String fromuserid)
	{
		this.fromuserid = fromuserid;
	}

	public String getFromusername()
	{
		return fromusername;
	}

	public void setFromusername(String fromusername)
	{
		this.fromusername = fromusername;
	}

	public String getTouserid()
	{
		return touserid;
	}

	public void setTouserid(String touserid)
	{
		this.touserid = touserid;
	}

	public String getTousername()
	{
		return tousername;
	}

	public void setTousername(String tousername)
	{
		this.tousername = tousername;
	}

	public String getDataid()
	{
		return dataid;
	}

	public void setDataid(String dataid)
	{
		this.dataid = dataid;
	}

	public String getTableid()
	{
		return tableid;
	}

	public void setTableid(String tableid)
	{
		this.tableid = tableid;
	}

	public String getRunflowkey()
	{
		return runflowkey;
	}

	public void setRunflowkey(String runflowkey)
	{
		this.runflowkey = runflowkey;
	}

	public String getIsuse()
	{
		return isuse;
	}

	public void setIsuse(String isuse)
	{
		this.isuse = isuse;
	}

	public String getFlowsno()
	{
		return flowsno;
	}

	public void setFlowsno(String flowsno)
	{
		this.flowsno = flowsno;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}
	
	

}
