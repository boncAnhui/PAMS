package com.pams.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.blue.ssh.core.entity.IdEntity;

@Entity
@Table(name = "T_APP_KNOWLEDGECOMMENT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = false)
public class KnowledgeComment extends IdEntity
{
	private String kno;// 知识编号

	private String clevel;// 评价等级

	private String kcomment;// 评价内容

	private String isuseful;// 是否采纳

	private Timestamp sugdate;// 评价时间

	private String sugperson;// 评价人

	private String memo;// 备注

	public String getKno()
	{
		return kno;
	}

	public void setKno(String kno)
	{
		this.kno = kno;
	}

	public String getClevel()
	{
		return clevel;
	}

	public void setClevel(String clevel)
	{
		this.clevel = clevel;
	}

	public String getKcomment()
	{
		return kcomment;
	}

	public void setKcomment(String kcomment)
	{
		this.kcomment = kcomment;
	}

	public String getIsuseful()
	{
		return isuseful;
	}

	public void setIsuseful(String isuseful)
	{
		this.isuseful = isuseful;
	}

	public Timestamp getSugdate()
	{
		return sugdate;
	}

	public void setSugdate(Timestamp sugdate)
	{
		this.sugdate = sugdate;
	}

	public String getSugperson()
	{
		return sugperson;
	}

	public void setSugperson(String sugperson)
	{
		this.sugperson = sugperson;
	}

	public String getMemo()
	{
		return memo;
	}

	public void setMemo(String memo)
	{
		this.memo = memo;
	}

}
