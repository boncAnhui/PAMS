package com.pams.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.blue.ssh.core.entity.IdEntity;

@Entity
@Table(name = "T_APP_KNOWLEDGECLASSRELATION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = false)
public class KnowledgeClassRelation extends IdEntity
{
	private String kid; // 文档标识
	
	private String classid; // 分类标识
	
	private String memo; // 备注

	public String getKid()
	{
		return kid;
	}

	public void setKid(String kid)
	{
		this.kid = kid;
	}

	public String getClassid()
	{
		return classid;
	}

	public void setClassid(String classid)
	{
		this.classid = classid;
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
