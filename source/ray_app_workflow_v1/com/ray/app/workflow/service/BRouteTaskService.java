package com.ray.app.workflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.headray.core.spring.jdo.JdbcDao;
import com.ray.app.workflow.dao.BRouteTaskDao;

@Component
@Transactional
public class BRouteTaskService
{
	@Autowired
	JdbcDao jdbcDao;

	@Autowired
	private BRouteTaskDao brouteTaskDao;

	public List findBy(String propertyName, String value) throws Exception
	{
		return brouteTaskDao.findBy(propertyName, value);
	}
}
