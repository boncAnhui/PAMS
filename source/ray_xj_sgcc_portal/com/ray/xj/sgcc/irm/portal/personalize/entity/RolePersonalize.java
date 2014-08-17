package com.ray.xj.sgcc.irm.portal.personalize.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.blue.ssh.core.entity.IdEntity;

@Entity
@Table(name = "T_APP_PT_ROLEPERSONALIZE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = false)
public class RolePersonalize extends IdEntity
{
	private String roleid; // 导航英文名称

	private String rolename; // 导航中文名称

	private String leftmenu; //左侧菜单

	private String topmenu; //顶部菜单
	
	
	private String mainsubject; //主体
	

	
	public String getRoleid()
	{
		return roleid;
	}

	public void setRoleid(String roleid)
	{
		this.roleid = roleid;
	}

	public String getRolename()
	{
		return rolename;
	}

	public void setRolename(String rolename)
	{
		this.rolename = rolename;
	}

	public String getLeftmenu()
	{
		return leftmenu;
	}

	public void setLeftmenu(String leftmenu)
	{
		this.leftmenu = leftmenu;
	}

	public String getTopmenu()
	{
		return topmenu;
	}

	public void setTopmenu(String topmenu)
	{
		this.topmenu = topmenu;
	}

	public String getMainsubject()
	{
		return mainsubject;
	}

	public void setMainsubject(String mainsubject)
	{
		this.mainsubject = mainsubject;
	}

	

}
