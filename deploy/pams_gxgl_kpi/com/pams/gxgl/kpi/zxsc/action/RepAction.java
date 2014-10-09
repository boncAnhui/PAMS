package com.pams.gxgl.kpi.zxsc.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.spec.GlobalConstants;
import com.pams.gxgl.kpi.zxsc.service.TabZXSC_BM;
import com.pams.gxgl.kpi.zxsc.service.TabZXSC_GS;
import com.pams.gxgl.kpi.zxsc.service.TabZXSC_RY;
import com.ray.app.chart.report.dao.ReportDao;
import com.ray.app.query.service.QueryService;
import com.ray.nwpn.itsm.report.common.ReportAction;

public class RepAction extends ReportAction
{
	private String _searchname;

	@Autowired
	private QueryService queryService;

	@Autowired
	private ReportDao reportDao;

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
		String reptype = Struts2Utils.getRequest().getParameter("reptype");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		arg.put("reptype", reptype);
		
		return "main_zxsc_gs";
	}

	// 本公司统计报表
	public String main_local_zxsc_gs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = ((DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token)).getFormatAttr(GlobalConstants.sys_login_org_internal);
		String reptype = Struts2Utils.getRequest().getParameter("reptype");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		arg.put("reptype", reptype);

		return "main_zxsc_gs";
	}

	public String tab_zxsc_gs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		String reptype = Struts2Utils.getRequest().getParameter("reptype");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		obj.setAttr("reptype", reptype);
		
		// 发布总数
		TabZXSC_GS tabZXSC_GS = new TabZXSC_GS();
		tabZXSC_GS.setJdbcTemplate(reportDao.getJdbcTemplate());

		List datas = tabZXSC_GS.execute(obj);
		data.put("zxscs", datas);

		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);

		return "tab_zxsc_gs";
	}

	public String xls_zxsc_gs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		String reptype = Struts2Utils.getRequest().getParameter("reptype");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		obj.setAttr("reptype", reptype);
		
		// 发布总数
		TabZXSC_GS tabZXSC_GS = new TabZXSC_GS();
		tabZXSC_GS.setJdbcTemplate(reportDao.getJdbcTemplate());

		List datas = tabZXSC_GS.execute(obj);
		
		String[] cnames = new String[]{ "公司", "考核总分" };
		String[] pnames = new String[]{ "orgcname", "zxsccskh" };
		String fileName = "执行时长考核公司统计.xls";

		exp_excel(cnames, pnames, datas, fileName);

		return "excel";
	}

	public String main_zxsc_bm() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		String reptype = Struts2Utils.getRequest().getParameter("reptype");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		arg.put("reptype", reptype);
		
		return "main_zxsc_bm";
	}

	public String main_local_zxsc_bm() throws Exception
	{

		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = ((DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token)).getFormatAttr(GlobalConstants.sys_login_org_internal);
		String reptype = Struts2Utils.getRequest().getParameter("reptype");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		arg.put("reptype", reptype);
		
		return "main_zxsc_bm";
	}

	public String tab_zxsc_bm() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		String reptype = Struts2Utils.getRequest().getParameter("reptype");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		obj.setAttr("reptype", reptype);
		
		// 发布总数
		TabZXSC_BM tabZXSC_BM = new TabZXSC_BM();
		tabZXSC_BM.setJdbcTemplate(reportDao.getJdbcTemplate());

		List datas = tabZXSC_BM.execute(obj);
		data.put("zxscs", datas);

		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		return "tab_zxsc_bm";
	}
	
	public String xls_zxsc_bm() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		String reptype = Struts2Utils.getRequest().getParameter("reptype");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		obj.setAttr("reptype", reptype);
		
		// 发布总数
		TabZXSC_BM tabZXSC_BM = new TabZXSC_BM();
		tabZXSC_BM.setJdbcTemplate(reportDao.getJdbcTemplate());

		List datas = tabZXSC_BM.execute(obj);

		String[] cnames = new String[]{ "部门", "考核总分" };
		String[] pnames = new String[]{ "orgcname", "zxsccskh" };
		String fileName = "执行时长考核部门统计.xls";

		exp_excel(cnames, pnames, datas, fileName);

		return "excel";		
	}

	public String main_zxsc_ry() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		String reptype = Struts2Utils.getRequest().getParameter("reptype");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		arg.put("reptype", reptype);
		
		return "main_zxsc_ry";
	}

	public String main_local_zxsc_ry() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = ((DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token)).getFormatAttr(GlobalConstants.sys_login_org_internal);
		String orginternal = ((DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token)).getFormatAttr(GlobalConstants.sys_login_org_internal);
		String reptype = Struts2Utils.getRequest().getParameter("reptype");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		arg.put("orginternal", orginternal);
		arg.put("reptype", reptype);
		
		return "main_zxsc_ry";
	}

	public String tab_zxsc_ry() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		String reptype = Struts2Utils.getRequest().getParameter("reptype");

		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		obj.setAttr("reptype", reptype);

		// 发布总数
		TabZXSC_RY tabZXSC_RY = new TabZXSC_RY();
		tabZXSC_RY.setJdbcTemplate(reportDao.getJdbcTemplate());

		List datas = tabZXSC_RY.execute(obj);
		data.put("zxscs", datas);

		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		arg.put("reptype", reptype);
		return "tab_zxsc_ry";
	}
	
	public String xls_zxsc_ry() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		String reptype = Struts2Utils.getRequest().getParameter("reptype");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		obj.setAttr("reptype", reptype);
		
		// 发布总数
		TabZXSC_RY tabZXSC_RY = new TabZXSC_RY();
		tabZXSC_RY.setJdbcTemplate(reportDao.getJdbcTemplate());

		List datas = tabZXSC_RY.execute(obj);
		
		String[] cnames = new String[]{ "人员", "考核总分", "所属公司", "所属部门"};
		String[] pnames = new String[]{ "username", "zxsccskh", "orgcname", "deptcname" };
		String fileName = "执行时长考核人员统计.xls";

		exp_excel(cnames, pnames, datas, fileName);

		return "excel";	
	}	

	public String get_searchname()
	{
		return _searchname;
	}

	public void set_searchname(String _searchname)
	{
		this._searchname = _searchname;
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