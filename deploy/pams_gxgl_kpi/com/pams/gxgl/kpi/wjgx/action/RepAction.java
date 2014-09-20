package com.pams.gxgl.kpi.wjgx.action;

import java.util.List;

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

public class RepAction extends SimpleAction
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
	public String main_wjgx_gs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		
		return "main_wjgx_gs";
	}
	
	// 本公司统计报表
	public String main_local_wjgx_gs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = ((DynamicObject)Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token)).getFormatAttr(GlobalConstants.sys_login_org_internal);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		
		return "main_wjgx_gs";
	}
	
	public String tab_wjgx_gs() throws Exception
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
		data.put("wjgxs", datas);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		return "tab_wjgx_gs";
	}	
	
	
	public String main_wjgx_bm() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		
		return "main_wjgx_bm";
	}
	
	public String main_local_wjgx_bm() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = ((DynamicObject)Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token)).getFormatAttr(GlobalConstants.sys_login_org_internal);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		
		return "main_wjgx_bm";
	}
	
	public String tab_wjgx_bm() throws Exception
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
		data.put("wjgxs", datas);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		return "tab_wjgx_bm";
	}
	
	public String main_wjgx_ry() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		
		return "main_wjgx_ry";
	}
	
	public String main_local_wjgx_ry() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = ((DynamicObject)Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token)).getFormatAttr(GlobalConstants.sys_login_org_internal);
		String orginternal = ((DynamicObject)Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token)).getFormatAttr(GlobalConstants.sys_login_org_internal);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		arg.put("orginternal", orginternal);
		
		return "main_wjgx_ry";
	}
	
	public String tab_wjgx_ry() throws Exception
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
		data.put("wjgxs", datas);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		return "tab_wjgx_ry";
	}	


}