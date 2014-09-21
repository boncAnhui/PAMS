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
import com.headray.framework.services.function.StringToolKit;
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
		String title = (String) map.get("title");
		String kno = (String) map.get("kno");
		String mauthor = (String) map.get("mauthor");
		String mauthordept = (String) map.get("mauthordept");
		String beginpublishdate = (String) map.get("beginpublishdate");
		String endpublishdate = (String) map.get("endpublishdate");		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select k.*  ").append("\n");
		sql.append("  from t_app_knowledge k, t_app_knowledgeclassrelation kcr, t_app_knowledgeclass kc, t_app_knowledgeclass kcc, ").append("\n");
		sql.append(" ( ").append("\n");
		sql.append(" select k.id  ").append("\n");
		sql.append("    from t_app_knowledge k, t_app_knowledgescope kscope, t_sys_wfgroupuser gu ").append("\n");
		sql.append("   where 1 = 1 ").append("\n");
		sql.append("     and k.id = kscope.knowledgeid ").append("\n");
		sql.append("     and (kscope.grouptype = 'ORG' or kscope.grouptype = 'DEPT')  ").append("\n");
		sql.append("     and (gu.ctype = 'ORG' or gu.ctype = 'DEPT')  ").append("\n");
		// sql.append("     and kscope.grouptype = gu.ctype     ").append("\n");
		sql.append("     and gu.groupinternal like kscope.groupinternal || '%'  ").append("\n");
		sql.append("     and gu.loginname = " + SQLParser.charValue(loginname)).append("\n");
		sql.append("   union ").append("\n");
		sql.append("  select k.id  ").append("\n");
		sql.append("    from t_app_knowledge k, t_app_knowledgescope kscope, t_sys_wfgroupuser gu ").append("\n");
		sql.append("   where 1 = 1 ").append("\n");
		sql.append("     and k.id = kscope.knowledgeid ").append("\n");
		sql.append("     and kscope.grouptype = 'PERSON'  ").append("\n");
		sql.append("     and kscope.grouptype = gu.ctype     ").append("\n");
		sql.append("     and kscope.groupid = gu.groupid     ").append("\n");
		sql.append("     and gu.loginname = " + SQLParser.charValue(loginname)).append("\n");
		sql.append("   union ").append("\n");
		sql.append("  select k.id  ").append("\n");
		sql.append("    from t_app_knowledge k, t_app_knowledgescope kscope, t_sys_wfgroupuser gu ").append("\n");
		sql.append("   where 1 = 1 ").append("\n");
		sql.append("     and k.id = kscope.knowledgeid ").append("\n");
		sql.append("     and kscope.grouptype = 'ROLE'  ").append("\n");
		sql.append("     and kscope.grouptype = gu.ctype     ").append("\n");
		sql.append("     and kscope.groupid = gu.groupid     ").append("\n");
		sql.append("     and gu.loginname = " + SQLParser.charValue(loginname)).append("\n");
		sql.append(" ) v ").append("\n");
		
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("  and k.id = kcr.kid  ").append("\n");
		sql.append("  and kcr.classid = kc.id ").append("\n");
		sql.append("  and kc.cno like kcc.cno || '%' ").append("\n");
		sql.append("  and kcc.id = " +  SQLParser.charValue(cclassid)).append("\n");
		sql.append("  and k.id = v.id ").append("\n");
		
		if (!StringToolKit.isBlank(title))
		{
			sql.append(" and lower(k.title) like lower(" + SQLParser.charLikeValue(title) + ")");
		}
		if (!StringToolKit.isBlank(kno))
		{
			sql.append(" and lower(k.kno) like lower(" + SQLParser.charLikeValue(kno) + ")");
		}
		if (!StringToolKit.isBlank(mauthor))
		{
			sql.append(" and lower(k.mauthor) like lower(" + SQLParser.charLikeValue(mauthor) + ")");
		}
		if (!StringToolKit.isBlank(mauthordept))
		{
			sql.append(" and lower(k.mauthordept) like lower(" + SQLParser.charLikeValue(mauthordept) + ")");
		}
		if (!StringToolKit.isBlank(beginpublishdate))
		{
			sql.append(" and k.publishdate >= to_date('" + beginpublishdate + " 00:00:00','yyyy-mm-dd hh24:mi:ss') ");
		}		
		if (!StringToolKit.isBlank(endpublishdate))
		{
			sql.append(" and k.publishdate >= to_date('" + endpublishdate + " 00:00:00','yyyy-mm-dd hh24:mi:ss') ");
		}
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

	@Transactional
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
