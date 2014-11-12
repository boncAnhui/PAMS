package com.pams.jhgl.jhmb.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pams.dao.PlanModelDao;
import com.ray.app.query.generator.GeneratorService;

@Component
@Transactional
public class PlanModelService
{
	public final static String _tableid = "PlanModel";

	@Autowired
	GeneratorService generatorService;


	@Autowired
	private PlanModelDao planmodelDao;

	// 查询待办记录
	public String get_browse_sql(Map map) throws Exception
	{
		StringBuffer sql = new StringBuffer();

		sql.append(" select * from t_app_planmodel ").append("\n");

		return sql.toString();
	}


}
