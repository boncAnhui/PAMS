package com.pams.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.blue.ssh.core.entity.IdEntity;

@Entity
@Table(name = "T_APP_INFOSHARE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = false)
// 信息共享单
public class InfoShare extends IdEntity
{
	private String cno; //共享单编号
	
	private String deptid; //信息共享部门标识
	
	private String deptname; //信息共享部门名称
	
	private String positionname; //岗位名称
	
	private String sourceid; //信息来源标识
	
	private String sourcename; //信息来源
	
	private Timestamp obtaintime; //信息获取时间
	
	private String obtaintimed; //信息获取时间（日期）
	
	private String obtaintimet; //信息获取时间（时间）
	
	private Timestamp publishtime; //信息发布时间
	
	private String title; //信息共享名称（标题）
	
	private String summary; //摘要
	
	private String cclassid; //分类标识
	
	private String cclassname; //分类名称
	
	private String shareauthor; //共享权限
	
	private String infosharescope; //共享范围（冗余字段）
	
	private String infosharescopeid; //共享范围标识（冗余字段）	

	private String infosharescopectype; //共享范围类型（冗余字段）
	
	private int filenums; //文件数量
	
	private String filetype; //文件形式
	
	private String memo; //备注
	
	private String creater; //创建人

	private String creatername; //创建人姓名
	
	private Timestamp createtime; //创建人姓名
	
	public String getCno()
	{
		return cno;
	}

	public void setCno(String cno)
	{
		this.cno = cno;
	}

	public String getDeptid()
	{
		return deptid;
	}

	public void setDeptid(String deptid)
	{
		this.deptid = deptid;
	}

	public String getDeptname()
	{
		return deptname;
	}

	public void setDeptname(String deptname)
	{
		this.deptname = deptname;
	}

	public String getPositionname()
	{
		return positionname;
	}

	public void setPositionname(String positionname)
	{
		this.positionname = positionname;
	}

	public String getSourceid()
	{
		return sourceid;
	}

	public void setSourceid(String sourceid)
	{
		this.sourceid = sourceid;
	}

	public String getSourcename()
	{
		return sourcename;
	}

	public void setSourcename(String sourcename)
	{
		this.sourcename = sourcename;
	}

	public Timestamp getObtaintime()
	{
		return obtaintime;
	}

	public void setObtaintime(Timestamp obtaintime)
	{
		this.obtaintime = obtaintime;
	}

	public String getObtaintimed()
	{
		return obtaintimed;
	}

	public void setObtaintimed(String obtaintimed)
	{
		this.obtaintimed = obtaintimed;
	}

	public String getObtaintimet()
	{
		return obtaintimet;
	}

	public void setObtaintimet(String obtaintimet)
	{
		this.obtaintimet = obtaintimet;
	}

	public Timestamp getPublishtime()
	{
		return publishtime;
	}

	public void setPublishtime(Timestamp publishtime)
	{
		this.publishtime = publishtime;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getSummary()
	{
		return summary;
	}

	public void setSummary(String summary)
	{
		this.summary = summary;
	}

	public String getCclassid()
	{
		return cclassid;
	}

	public void setCclassid(String cclassid)
	{
		this.cclassid = cclassid;
	}

	public String getCclassname()
	{
		return cclassname;
	}

	public void setCclassname(String cclassname)
	{
		this.cclassname = cclassname;
	}

	public String getShareauthor()
	{
		return shareauthor;
	}

	public void setShareauthor(String shareauthor)
	{
		this.shareauthor = shareauthor;
	}

	public String getInfosharescope()
	{
		return infosharescope;
	}

	public void setInfosharescope(String infosharescope)
	{
		this.infosharescope = infosharescope;
	}

	public String getInfosharescopeid()
	{
		return infosharescopeid;
	}

	public void setInfosharescopeid(String infosharescopeid)
	{
		this.infosharescopeid = infosharescopeid;
	}
	
	public String getInfosharescopectype()
	{
		return infosharescopectype;
	}

	public void setInfosharescopectype(String infosharescopectype)
	{
		this.infosharescopectype = infosharescopectype;
	}

	public int getFilenums()
	{
		return filenums;
	}

	public void setFilenums(int filenums)
	{
		this.filenums = filenums;
	}

	public String getFiletype()
	{
		return filetype;
	}

	public void setFiletype(String filetype)
	{
		this.filetype = filetype;
	}

	public String getMemo()
	{
		return memo;
	}

	public void setMemo(String memo)
	{
		this.memo = memo;
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
	
}
