package com.pams.kpi.gxgl.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.action.SimpleAction;
import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.pams.kpi.gxgl.service.KpiXXGXJSL;
import com.pams.kpi.gxgl.service.KpiXXGXJSLLCJDMX;
import com.pams.kpi.gxgl.service.KpiXXGXJSLLCMX;
import com.pams.kpi.gxgl.service.KpiZXSC;
import com.pams.kpi.gxgl.service.KpiZXSCMX;
import com.ray.app.chart.report.dao.ReportDao;
import com.ray.app.query.service.QueryService;

public class KpiAction extends SimpleAction
{
	private String _searchname;

	@Autowired
	private QueryService queryService;

	@Autowired
	ReportDao reportDao;
	
	public String mainframe() throws Exception
	{
		return "mainframe";
	}
	
	public String main_zxsc() throws Exception
	{
		return "main_zxsc";
	}
	
	public String kpi_zxsc() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		
		KpiZXSC tab = new KpiZXSC();
		tab.setJdbcTemplate(reportDao.getJdbcTemplate());
		List datas = tab.execute(obj);
		data.put("zxscs", datas);
		return "kpi_zxsc";
	}

	public String main_zxscmx() throws Exception
	{
		String ownerctx = Struts2Utils.getRequest().getParameter("ownerctx");
		arg.put("ownerctx", ownerctx);
		return "main_zxscmx";
	}
	
	public String kpi_zxscmx() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String ownerctx = Struts2Utils.getRequest().getParameter("ownerctx");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("ownerctx", ownerctx);
		
		KpiZXSCMX tab = new KpiZXSCMX();
		tab.setJdbcTemplate(reportDao.getJdbcTemplate());
		List datas = tab.execute(obj);
		data.put("zxscs", datas);
		return "kpi_zxscmx";
	}
	
	
	// 信息共享及时率
	public String main_xxgxjsl() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		
		return "main_xxgxjsl";
	}
	
	public String kpi_xxgxjsl() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		
		KpiXXGXJSL tab = new KpiXXGXJSL();
		tab.setJdbcTemplate(reportDao.getJdbcTemplate());
		List datas = tab.execute(obj);
		
		data.put("xxgxjsls", datas);

		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		
		return "kpi_xxgxjsl";
	}
	
	// 信息共享及时率
	public String main_xxgxjsllcmx() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String loginname = Struts2Utils.getRequest().getParameter("loginname");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("loginname", loginname);
		
		return "main_xxgxjsllcmx";
	}
	
	public String kpi_xxgxjsllcmx() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String loginname = Struts2Utils.getRequest().getParameter("loginname");
		
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("loginname", loginname);
		
		KpiXXGXJSLLCMX tab = new KpiXXGXJSLLCMX();
		tab.setJdbcTemplate(reportDao.getJdbcTemplate());
		List datas = tab.execute(obj);
		data.put("xxgxjsllcmxs", datas);
		return "kpi_xxgxjsllcmx";
	}
	
	
	// 信息共享及时率（流程节点明细统计）
	public String main_xxgxjsllcjdmx() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String cno = Struts2Utils.getRequest().getParameter("cno");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("cno", cno);
		
		return "main_xxgxjsllcjdmx";
	}
	
	public String kpi_xxgxjsllcjdmx() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String cno = Struts2Utils.getRequest().getParameter("cno");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("cno", cno);
		
		KpiXXGXJSLLCJDMX tab = new KpiXXGXJSLLCJDMX();
		tab.setJdbcTemplate(reportDao.getJdbcTemplate());
		List datas = tab.execute(obj);
		data.put("xxgxjsllcjdmxs", datas);
		return "kpi_xxgxjsllcjdmx";
	}
	
	public String get_searchname()
	{
		return _searchname;
	}

	public void set_searchname(String searchname)
	{
		_searchname = searchname;
	}

	public QueryService getQueryService()
	{
		return queryService;
	}

	public void setQueryService(QueryService queryService)
	{
		this.queryService = queryService;
	}

	public ReportDao getReportDao()
	{
		return reportDao;
	}

	public void setReportDao(ReportDao reportDao)
	{
		this.reportDao = reportDao;
	}
	

	
}
