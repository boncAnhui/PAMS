package com.pams.gxgl.zsplwh.service;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pams.dao.KnowledgeClassDao;
import com.pams.dao.KnowledgeClassRelationDao;
import com.pams.dao.KnowledgeCommentDao;
import com.pams.dao.KnowledgeDao;
import com.pams.entity.KnowledgeComment;
import com.ray.app.query.generator.GeneratorService;
import com.ray.xj.sgcc.irm.system.author.userrole.dao.UserRoleDao;

@Component
@Transactional
public class KnowledgeCommentService
{
	@Autowired
	private KnowledgeDao knowledgeDao;
	
	@Autowired
	private KnowledgeCommentDao knowledgecommentDao;	
	
	@Autowired
	private KnowledgeClassDao knowledgeclassDao;

	@Autowired
	private KnowledgeClassRelationDao knowledgeclassrelationDao;

	@Autowired
	private GeneratorService generatorService;

	@Autowired
	private UserRoleDao userRoleDao;

	public List<KnowledgeComment> findBy(String propertyName, String value, Order order)
	{
		return knowledgecommentDao.findBy(propertyName, value, order);
	}

	public KnowledgeDao getKnowledgeDao()
	{
		return knowledgeDao;
	}

	public void setKnowledgeDao(KnowledgeDao knowledgeDao)
	{
		this.knowledgeDao = knowledgeDao;
	}

	public KnowledgeCommentDao getKnowledgecommentDao()
	{
		return knowledgecommentDao;
	}

	public void setKnowledgecommentDao(KnowledgeCommentDao knowledgecommentDao)
	{
		this.knowledgecommentDao = knowledgecommentDao;
	}

	public KnowledgeClassDao getKnowledgeclassDao()
	{
		return knowledgeclassDao;
	}

	public void setKnowledgeclassDao(KnowledgeClassDao knowledgeclassDao)
	{
		this.knowledgeclassDao = knowledgeclassDao;
	}

	public KnowledgeClassRelationDao getKnowledgeclassrelationDao()
	{
		return knowledgeclassrelationDao;
	}

	public void setKnowledgeclassrelationDao(KnowledgeClassRelationDao knowledgeclassrelationDao)
	{
		this.knowledgeclassrelationDao = knowledgeclassrelationDao;
	}

	public GeneratorService getGeneratorService()
	{
		return generatorService;
	}

	public void setGeneratorService(GeneratorService generatorService)
	{
		this.generatorService = generatorService;
	}

	public UserRoleDao getUserRoleDao()
	{
		return userRoleDao;
	}

	public void setUserRoleDao(UserRoleDao userRoleDao)
	{
		this.userRoleDao = userRoleDao;
	}

}
