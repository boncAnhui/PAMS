package com.pams.gxgl.zswh.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.headray.core.spring.jdo.DyDaoHelper;
import com.headray.core.spring.jdo.JdbcDao;
import com.headray.framework.services.db.SQLParser;
import com.pams.dao.KnowledgeDao;
import com.pams.entity.Knowledge;

@Component
@Transactional

public class KnowledgeService
{
	public final static String _tableid = "Knowledge";
	
	@Autowired
	KnowledgeDao knowledgeDao;
	
	@Autowired
	JdbcDao jdbcDao;
	
	public List browse(Map map) throws Exception
	{
		String cclassid = (String) map.get("cclassid");
		String loginname = (String) map.get("loginname");
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select k.*  ").append("\n");
		sql.append("  from t_app_knowledge k, t_app_knowledgeclassrelation kcr, t_app_knowledgeclass kc, t_app_knowledgeclass kcc ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("  and k.id = kcr.kid  ").append("\n");
		sql.append("  and kcr.classid = kc.id ").append("\n");
		sql.append("  and kc.cno like kcc.cno || '%' ").append("\n");
		sql.append("  and kcc.id = " +  SQLParser.charValue(cclassid)).append("\n");
		sql.append(" order by k.createtime desc ").append("\n");

		JdbcTemplate jt = jdbcDao.getJdbcTemplate();
		List datas = DyDaoHelper.query(jt, sql.toString());

		return datas;
	}
	
	@Transactional(readOnly = true)
	public Knowledge get(String id) throws Exception
	{
		return knowledgeDao.get(id);
	}
	
	public String save(Knowledge entity) throws Exception
	{
		knowledgeDao.save(entity);
		return entity.getId();
	}

	public JdbcDao getJdbcDao()
	{
		return jdbcDao;
	}

	public void setJdbcDao(JdbcDao jdbcDao)
	{
		this.jdbcDao = jdbcDao;
	}
	
}
