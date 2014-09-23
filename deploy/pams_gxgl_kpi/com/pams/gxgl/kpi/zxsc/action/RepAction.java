package com.pams.gxgl.kpi.zxsc.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.action.SimpleAction;
import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.spec.GlobalConstants;
import com.pams.gxgl.kpi.zxsc.service.TabZXSC_BM;
import com.pams.gxgl.kpi.zxsc.service.TabZXSC_GS;
import com.pams.gxgl.kpi.zxsc.service.TabZXSC_RY;
import com.ray.app.chart.report.dao.ReportDao;
import com.ray.app.query.service.QueryService;
import com.ray.nwpn.itsm.report.common.RepHelper;

public class RepAction extends SimpleAction
{
	private String _searchname;

	@Autowired
	private QueryService queryService;

	@Autowired
	ReportDao reportDao;
	
	InputStream excelStream; 
	
	String excelFileName;

	public RepAction()
	{
	}

	public String mainframe() throws Exception
	{
		return "mainframe";
	}

	// 集团公司统计报表
	public String main_zxsc_gs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		
		return "main_zxsc_gs";
	}
	
	// 本公司统计报表
	public String main_local_zxsc_gs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = ((DynamicObject)Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token)).getFormatAttr(GlobalConstants.sys_login_org_internal);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		
		return "main_zxsc_gs";
	}
	
	public String tab_zxsc_gs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		
		//发布总数
		TabZXSC_GS tabZXSC_GS = new TabZXSC_GS();
		tabZXSC_GS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List datas = tabZXSC_GS.execute(obj);
		data.put("zxscs", datas);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		
		return "tab_zxsc_gs";
	}
	
	
	public String export_tab_zxsc_gs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		
		//发布总数
		TabZXSC_GS tabZXSC_GS = new TabZXSC_GS();
		tabZXSC_GS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List datas = tabZXSC_GS.execute(obj);
		

		String[] cnames = new String[]{"公司","考核总分"};
		String[] pnames = new String[]{"orgcname","zxsccskh"};
		HSSFWorkbook workbook = RepHelper.poiexcel(cnames, pnames, datas);
		
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
	        workbook.write(out); 
	        out.flush(); 
	        byte[] aa = out.toByteArray();
	        excelStream = new ByteArrayInputStream(aa, 0, aa.length);
	        out.close();
		
	        String fileName = "执行时长考核公司统计.xls";
	        excelFileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
	        
		return "export_tab_zxsc_gs";
	}
	
	
	public String main_zxsc_bm() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		
		return "main_zxsc_bm";
	}
	
	public String main_local_zxsc_bm() throws Exception
	{
		
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = ((DynamicObject)Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token)).getFormatAttr(GlobalConstants.sys_login_org_internal);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		
		return "main_zxsc_bm";
	}
	
	public String tab_zxsc_bm() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		
		//发布总数
		TabZXSC_BM tabZXSC_BM = new TabZXSC_BM();
		tabZXSC_BM.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List datas = tabZXSC_BM.execute(obj);
		data.put("zxscs", datas);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		return "tab_zxsc_bm";
	}
	
	public String main_zxsc_ry() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		
		return "main_zxsc_ry";
	}
	
	public String main_local_zxsc_ry() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = ((DynamicObject)Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token)).getFormatAttr(GlobalConstants.sys_login_org_internal);
		String orginternal = ((DynamicObject)Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token)).getFormatAttr(GlobalConstants.sys_login_org_internal);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		arg.put("orginternal", orginternal);
		
		return "main_zxsc_ry";
	}
	
	public String tab_zxsc_ry() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		
		//发布总数
		TabZXSC_RY tabZXSC_RY = new TabZXSC_RY();
		tabZXSC_RY.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List datas = tabZXSC_RY.execute(obj);
		data.put("zxscs", datas);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		return "tab_zxsc_ry";
	}

	public String get_searchname() {
		return _searchname;
	}

	public void set_searchname(String _searchname) {
		this._searchname = _searchname;
	}

	public QueryService getQueryService() {
		return queryService;
	}

	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

	public ReportDao getReportDao() {
		return reportDao;
	}

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}	
	
	


}