package com.pams.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.blue.ssh.core.entity.IdEntity;

@Entity
@Table(name = "T_APP_KNOWLEDGE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = false)
public class Knowledge extends IdEntity
{
	private String origin; // 源标识

	private String restype; // 源类型

	private String kno; // 编号
	
	private String title; // 文档标题

	private String keyword; // 关键字

	private String summary; // 摘要
	
	@Column(length = 2000)
	private String content; // 正文（详细内容）	
	
	private String mauthor; // 主作者
	
	private String mauthorid; // 主作者标识
	
	private String sauthor; // 次作者
	
	private String mauthordept; // 主作者部门
	
	private String mauthordeptid; // 主作者部门标识

	private String slevel; // 安全级别
	
	private String cclassid; // 文档分类标识（系统分类）
	
	private String cclassallname; // 文档分类（全名）
	
	private String publisher; // 发布者
	
	private String publisherid; // 发布者标识

	private Timestamp publishdate; // 发布时间
	
	private Integer views; // 浏览次数

	private Integer comments; // 评价次数

	private Integer collects; // 收藏次数
	
	private Integer isstandard; // 是否标准解决方案	

	private String attachment; // 附件标识
	
	private String attachurl; // 附件链接地址

	private String attachname; // 附件文件名

	private String attachextend; // 附件文件扩展名	
	
	private String iseffective; // 是否生效  Y 表示生效 发布状态，N 表示未生效新建状态
	
	@Column(length = 2000)
	private String memo; // 备注
	
	private String createuser; // 创建者
	
	private String createuserid; // 创建者标识

	private Timestamp createtime; // 创建时间

	public String getOrigin()
	{
		return origin;
	}

	public void setOrigin(String origin)
	{
		this.origin = origin;
	}

	public String getRestype()
	{
		return restype;
	}

	public void setRestype(String restype)
	{
		this.restype = restype;
	}

	public String getKno()
	{
		return kno;
	}

	public void setKno(String kno)
	{
		this.kno = kno;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getKeyword()
	{
		return keyword;
	}

	public void setKeyword(String keyword)
	{
		this.keyword = keyword;
	}

	public String getSummary()
	{
		return summary;
	}

	public void setSummary(String summary)
	{
		this.summary = summary;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getMauthor()
	{
		return mauthor;
	}

	public void setMauthor(String mauthor)
	{
		this.mauthor = mauthor;
	}

	public String getMauthorid()
	{
		return mauthorid;
	}

	public void setMauthorid(String mauthorid)
	{
		this.mauthorid = mauthorid;
	}

	public String getSauthor()
	{
		return sauthor;
	}

	public void setSauthor(String sauthor)
	{
		this.sauthor = sauthor;
	}

	public String getMauthordept()
	{
		return mauthordept;
	}

	public void setMauthordept(String mauthordept)
	{
		this.mauthordept = mauthordept;
	}

	public String getMauthordeptid()
	{
		return mauthordeptid;
	}

	public void setMauthordeptid(String mauthordeptid)
	{
		this.mauthordeptid = mauthordeptid;
	}

	public String getSlevel()
	{
		return slevel;
	}

	public void setSlevel(String slevel)
	{
		this.slevel = slevel;
	}

	public String getCclassid()
	{
		return cclassid;
	}

	public void setCclassid(String cclassid)
	{
		this.cclassid = cclassid;
	}

	public String getCclassallname()
	{
		return cclassallname;
	}

	public void setCclassallname(String cclassallname)
	{
		this.cclassallname = cclassallname;
	}

	public String getPublisher()
	{
		return publisher;
	}

	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}

	public String getPublisherid()
	{
		return publisherid;
	}

	public void setPublisherid(String publisherid)
	{
		this.publisherid = publisherid;
	}

	public Timestamp getPublishdate()
	{
		return publishdate;
	}

	public Integer getViews()
	{
		return views;
	}

	public void setViews(Integer views)
	{
		this.views = views;
	}

	public Integer getIsstandard()
	{
		return isstandard;
	}

	public void setIsstandard(Integer isstandard)
	{
		this.isstandard = isstandard;
	}

	public Integer getComments()
	{
		return comments;
	}

	public Integer getCollects()
	{
		return collects;
	}

	public String getAttachment()
	{
		return attachment;
	}

	public void setPublishdate(Timestamp publishdate)
	{
		this.publishdate = publishdate;
	}


	public void setAttachment(String attachment)
	{
		this.attachment = attachment;
	}

	public String getAttachurl()
	{
		return attachurl;
	}

	public void setAttachurl(String attachurl)
	{
		this.attachurl = attachurl;
	}

	public String getAttachname()
	{
		return attachname;
	}

	public void setAttachname(String attachname)
	{
		this.attachname = attachname;
	}

	public String getAttachextend()
	{
		return attachextend;
	}

	public void setAttachextend(String attachextend)
	{
		this.attachextend = attachextend;
	}

	public String getIseffective()
	{
		return iseffective;
	}

	public void setIseffective(String iseffective)
	{
		this.iseffective = iseffective;
	}

	public String getMemo()
	{
		return memo;
	}

	public void setMemo(String memo)
	{
		this.memo = memo;
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

	public void setComments(Integer comments)
	{
		this.comments = comments;
	}

	public void setCollects(Integer collects)
	{
		this.collects = collects;
	}



}
