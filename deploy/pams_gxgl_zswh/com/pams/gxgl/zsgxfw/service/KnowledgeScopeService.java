package com.pams.gxgl.zsgxfw.service;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pams.dao.KnowledgeScopeDao;
import com.pams.entity.KnowledgeScope;

@Component
@Transactional
public class KnowledgeScopeService
{
	@Autowired
	private KnowledgeScopeDao knowledgescopeDao;
	
	@Transactional(readOnly = true)
	public KnowledgeScope get(String id) throws Exception
	{
		return knowledgescopeDao.get(id);
	}

	@Transactional
	public String save(KnowledgeScope entity) throws Exception
	{
		knowledgescopeDao.save(entity);
		return entity.getId();
	}
	
	public List<KnowledgeScope> findBy(String propertyName, String value, Order order)
	{
		return knowledgescopeDao.findBy(propertyName, value, order);
	}
	
	

}
