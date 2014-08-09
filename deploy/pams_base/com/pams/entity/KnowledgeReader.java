package com.pams.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.blue.ssh.core.entity.IdEntity;

@Entity
@Table(name = "T_APP_KNOWLEDGEREADER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = false)
// 信息共享单
public class KnowledgeReader extends IdEntity
{
	private String knowledgeid; //知识标识
	
	private String reader; //读者用户名
	
	private String readername; //读者名称

	private String readertype; //读者类型

	public String getKnowledgeid()
	{
		return knowledgeid;
	}

	public void setKnowledgeid(String knowledgeid)
	{
		this.knowledgeid = knowledgeid;
	}

	public String getReader()
	{
		return reader;
	}

	public void setReader(String reader)
	{
		this.reader = reader;
	}

	public String getReadername()
	{
		return readername;
	}

	public void setReadername(String readername)
	{
		this.readername = readername;
	}

	public String getReadertype()
	{
		return readertype;
	}

	public void setReadertype(String readertype)
	{
		this.readertype = readertype;
	}
}
