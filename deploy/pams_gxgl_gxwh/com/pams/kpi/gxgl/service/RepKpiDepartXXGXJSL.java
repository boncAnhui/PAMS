package com.pams.kpi.gxgl.service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.headray.framework.services.db.dybeans.DynamicObject;
import com.ray.app.chart.report.service.IReportService;

public class RepKpiDepartXXGXJSL implements IReportService
{
	JdbcTemplate jt;
	
	public List execute(DynamicObject map) throws Exception
	{
		KpiXXGXJSLBM kpiXXGXJSLBM = new KpiXXGXJSLBM();
		kpiXXGXJSLBM.setJdbcTemplate(jt);
		return kpiXXGXJSLBM.execute(map);
	}
	

	public void setJdbcTemplate(JdbcTemplate jt)
	{
		this.jt = jt;
	}
	
	public JdbcTemplate getJdbcTemplate()
	{
		return this.jt;
	}	

}
