package com.pams.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.blue.ssh.core.entity.IdEntity;

@Entity
@Table(name = "T_APP_KNOWLEDGECLASS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = false)
public class KnowledgeClass extends IdEntity
{
	private String cno; // 分类编号

	private String cname; // 分类名称

	private String supid; // 上级分类标识
	
	private String islast; //是否叶子节点
	
	private String description; // 分类描述	

	private String ctype; // 分类类型

	private String foldertype; // 文件夹类型
	
	private String roottype; // 分类根结点类型
	
	private String issys; // 是否系统分类

	private String btype; // 业务类型
	
	private String bid; // 业务数据标识
	
	private String templateid; // 模板标识
	
	private String createuser; // 创建者

	private String createuserid; // 创建者标识

	private Timestamp createtime; // 创建时间
	
	private String ordernum; // 排序码

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

	public String getSupid()
	{
		return supid;
	}

	public void setSupid(String supid)
	{
		this.supid = supid;
	}

	public String getIslast()
	{
		return islast;
	}

	public void setIslast(String islast)
	{
		this.islast = islast;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getCtype()
	{
		return ctype;
	}

	public void setCtype(String ctype)
	{
		this.ctype = ctype;
	}

	public String getFoldertype()
	{
		return foldertype;
	}

	public void setFoldertype(String foldertype)
	{
		this.foldertype = foldertype;
	}
	
	public String getRoottype()
	{
		return roottype;
	}

	public void setRoottype(String roottype)
	{
		this.roottype = roottype;
	}
	
	public String getIssys()
	{
		return issys;
	}

	public void setIssys(String issys)
	{
		this.issys = issys;
	}

	public String getBtype()
	{
		return btype;
	}

	public void setBtype(String btype)
	{
		this.btype = btype;
	}

	public String getBid()
	{
		return bid;
	}

	public void setBid(String bid)
	{
		this.bid = bid;
	}

	public String getTemplateid()
	{
		return templateid;
	}

	public void setTemplateid(String templateid)
	{
		this.templateid = templateid;
	}

	public String getCreateuser()
	{
		return createuser;
	}

	public void setCreateuser(String createuser)
	{
		this.createuser = createuser;
	}

	public String getCreateuserid()
	{
		return createuserid;
	}

	public void setCreateuserid(String createuserid)
	{
		this.createuserid = createuserid;
	}

	public Timestamp getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Timestamp createtime)
	{
		this.createtime = createtime;
	}

	public String getOrdernum()
	{
		return ordernum;
	}

	public void setOrdernum(String ordernum)
	{
		this.ordernum = ordernum;
	}

}
