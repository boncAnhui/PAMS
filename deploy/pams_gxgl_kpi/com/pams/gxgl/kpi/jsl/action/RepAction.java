package com.pams.gxgl.kpi.jsl.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.spec.GlobalConstants;
import com.pams.gxgl.kpi.jsl.service.TabJSL_BM;
import com.pams.gxgl.kpi.jsl.service.TabJSL_GS;
import com.pams.gxgl.kpi.jsl.service.TabJSL_RY;
import com.ray.app.chart.report.dao.ReportDao;
import com.ray.app.query.service.QueryService;
import com.ray.nwpn.itsm.report.common.ReportAction;

public class RepAction extends ReportAction
{
	private String _searchname;

	@Autowired
	private QueryService queryService;

	@Autowired
	ReportDao reportDao;

	public RepAction()
	{
	}

	public String mainframe() throws Exception
	{
		return "mainframe";
	}

	// 集团公司统计报表
	public String main_jsl_gs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		String reptype = Struts2Utils.getRequest().getParameter("reptype");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		arg.put("reptype", reptype);
		
		return "main_jsl_gs";
	}
	
	// 本公司统计报表
	public String main_local_jsl_gs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = ((DynamicObject)Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token)).getFormatAttr(GlobalConstants.sys_login_org_internal);
		String reptype = Struts2Utils.getRequest().getParameter("reptype");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		arg.put("reptype", reptype);
		
		return "main_jsl_gs";
	}
	
	public String tab_jsl_gs() throws Exception
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
		
		//发布总数
		TabJSL_GS tabJSL_GS = new TabJSL_GS();
		tabJSL_GS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List datas = tabJSL_GS.execute(obj);
		data.put("jsls", datas);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		arg.put("reptype", reptype);
		return "tab_jsl_gs";
	}
	
	public String xls_jsl_gs() throws Exception
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
		
		//发布总数
		TabJSL_GS tabJSL_GS = new TabJSL_GS();
		tabJSL_GS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List datas = tabJSL_GS.execute(obj);
		
		String[] cnames = new String[]{ "公司", "考核总分" };
		String[] pnames = new String[]{ "orgcname", "zxsccskh" };
		String fileName = "共享及时率考核公司统计.xls";

		exp_excel(cnames, pnames, datas, fileName);

		return "excel";
	}	
	
	
	public String main_jsl_bm() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		String reptype = Struts2Utils.getRequest().getParameter("reptype");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		arg.put("reptype", reptype);
		
		return "main_jsl_bm";
	}
	
	public String main_local_jsl_bm() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = ((DynamicObject)Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token)).getFormatAttr(GlobalConstants.sys_login_org_internal);
		String reptype = Struts2Utils.getRequest().getParameter("reptype");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		arg.put("reptype", reptype);
		
		return "main_jsl_bm";
	}
	
	public String tab_jsl_bm() throws Exception
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
		
		//发布总数
		TabJSL_BM tabJSL_BM = new TabJSL_BM();
		tabJSL_BM.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List datas = tabJSL_BM.execute(obj);
		data.put("jsls", datas);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		arg.put("reptype", reptype);
		return "tab_jsl_bm";
	}
	
	public String xls_jsl_bm() throws Exception
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
		
		//发布总数
		TabJSL_BM tabJSL_BM = new TabJSL_BM();
		tabJSL_BM.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List datas = tabJSL_BM.execute(obj);

		String[] cnames = new String[]{ "部门", "考核总分" };
		String[] pnames = new String[]{ "orgcname", "zxsccskh" };
		String fileName = "共享及时率考核部门统计.xls";

		exp_excel(cnames, pnames, datas, fileName);

		return "excel";
	}	
	
	public String main_jsl_ry() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		String reptype = Struts2Utils.getRequest().getParameter("reptype");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		arg.put("reptype", reptype);
		
		return "main_jsl_ry";
	}
	
	public String main_local_jsl_ry() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = ((DynamicObject)Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token)).getFormatAttr(GlobalConstants.sys_login_org_internal);
		String orginternal = ((DynamicObject)Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token)).getFormatAttr(GlobalConstants.sys_login_org_internal);
		String reptype = Struts2Utils.getRequest().getParameter("reptype");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		arg.put("orginternal", orginternal);
		arg.put("reptype", reptype);
		
		return "main_jsl_ry";
	}
	
	public String tab_jsl_ry() throws Exception
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
		
		//发布总数
		TabJSL_RY tabJSL_RY = new TabJSL_RY();
		tabJSL_RY.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List datas = tabJSL_RY.execute(obj);
		data.put("jsls", datas);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		arg.put("reptype", reptype);
		return "tab_jsl_ry";
	}	
	
	
	public String xls_jsl_ry() throws Exception
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
		
		//发布总数
		TabJSL_RY tabJSL_RY = new TabJSL_RY();
		tabJSL_RY.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List datas = tabJSL_RY.execute(obj);

		String[] cnames = new String[]{ "人员", "考核总分", "所属公司", "所属部门"};
		String[] pnames = new String[]{ "username", "zxsccskh", "orgcname", "deptcname" };
		String fileName = "共享及时率考核人员统计.xls";

		exp_excel(cnames, pnames, datas, fileName);

		return "excel";
	}


}