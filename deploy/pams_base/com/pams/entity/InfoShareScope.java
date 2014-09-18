package com.pams.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.blue.ssh.core.entity.IdEntity;

@Entity
@Table(name = "T_APP_INFOSHARESCOPE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = false)
// 信息共享单范围
public class InfoShareScope extends IdEntity
{
	private String infoshareid; //共享单标识
	
	private String grouptype; //分组类型（组织机构、岗位、人员）
	
	private String groupid; //分组标识
	
	private String groupname; //分组名称
	
	private String groupinternal; //分组内部码	
	
	public String getInfoshareid()
	{
		return infoshareid;
	}

	public void setInfoshareid(String infoshareid)
	{
		this.infoshareid = infoshareid;
	}

	public String getGrouptype()
	{
		return grouptype;
	}

	public void setGrouptype(String grouptype)
	{
		this.grouptype = grouptype;
	}

	public String getGroupid()
	{
		return groupid;
	}

	public void setGroupid(String groupid)
	{
		this.groupid = groupid;
	}

	public String getGroupname()
	{
		return groupname;
	}

	public void setGroupname(String groupname)
	{
		this.groupname = groupname;
	}

	public String getGroupinternal() {
		return groupinternal;
	}

	public void setGroupinternal(String groupinternal) {
		this.groupinternal = groupinternal;
	}
	
	
}
