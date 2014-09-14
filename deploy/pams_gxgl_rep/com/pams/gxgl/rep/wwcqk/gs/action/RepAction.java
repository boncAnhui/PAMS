package com.pams.gxgl.rep.wwcqk.gs.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.action.SimpleAction;
import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.spec.GlobalConstants;
import com.pams.gxgl.rep.wwcqk.gs.service.Tab_WWCQK_GS_CSZXZS;
import com.pams.gxgl.rep.wwcqk.gs.service.Tab_WWCQK_GS_JDCSZS;
import com.pams.gxgl.rep.wwcqk.gs.service.Tab_WWCQK_GS_WFBZS;
import com.pams.gxgl.rep.wwcqk.gs.service.Tab_WWCQK_GS_YZXJDZS;
import com.pams.gxgl.rep.wwcqk.gs.service.Tab_WWCQK_GS_ZCZXZS;
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

	public String main_wwcqk() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		this.arg.put("begindate", begindate);
		this.arg.put("enddate", enddate);
		arg.put("internal", internal);
		return "main_wwcqk";
	}
	
	// 本公司统计报表
	public String main_local_wwcqk() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = ((DynamicObject)Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token)).getFormatAttr(GlobalConstants.sys_login_org_internal);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		
		return "main_wwcqk";
	}	
	public String tab_wwcqk() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		
		//正常执行总数
		Tab_WWCQK_GS_ZCZXZS tabZCZXZS = new Tab_WWCQK_GS_ZCZXZS();
		tabZCZXZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List zczxzs = tabZCZXZS.execute(obj);
		data.put("zczxzs", zczxzs);
		
		
		//已执行节点总数
		Tab_WWCQK_GS_YZXJDZS tabYZXJDZS = new Tab_WWCQK_GS_YZXJDZS();
		tabYZXJDZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List yzxjdzs = tabYZXJDZS.execute(obj);
		data.put("yzxjdzs", yzxjdzs);
		
		
		//未发布总数
		Tab_WWCQK_GS_WFBZS tabWFBZS = new Tab_WWCQK_GS_WFBZS();
		tabWFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List wfbzs = tabWFBZS.execute(obj);
		data.put("wfbzs", wfbzs);
		
		//超时执行总数
		Tab_WWCQK_GS_CSZXZS tabCSZXZS = new Tab_WWCQK_GS_CSZXZS();
		tabCSZXZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List cszxzs = tabCSZXZS.execute(obj);
		data.put("cszxzs", cszxzs);
		
		//节点超时总数
		Tab_WWCQK_GS_JDCSZS tabJDCSZS = new Tab_WWCQK_GS_JDCSZS();
		tabJDCSZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List jdcszs = tabJDCSZS.execute(obj);
		data.put("jdcszs", jdcszs);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		return "tab_wwcqk";
	}

}