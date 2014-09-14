package com.pams.gxgl.rep.wwcqk.ry.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.action.SimpleAction;
import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.pams.gxgl.rep.wwcqk.ry.service.Tab_WWCQK_RY_CSZXZS;
import com.pams.gxgl.rep.wwcqk.ry.service.Tab_WWCQK_RY_WFBZS;
import com.pams.gxgl.rep.wwcqk.ry.service.Tab_WWCQK_RY_JDCSZS;
import com.pams.gxgl.rep.wwcqk.ry.service.Tab_WWCQK_RY_YZXJDZS;
import com.pams.gxgl.rep.wwcqk.ry.service.Tab_WWCQK_RY_ZCZXZS;
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
	
	public String tab_wwcqk() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		
		//未发布总数
		Tab_WWCQK_RY_WFBZS tabWFBZS = new Tab_WWCQK_RY_WFBZS();
		tabWFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List wfbzs = tabWFBZS.execute(obj);
		data.put("wfbzs", wfbzs);
		
		
		//正常执行总数
		Tab_WWCQK_RY_ZCZXZS tabZCZXZS = new Tab_WWCQK_RY_ZCZXZS();
		tabZCZXZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List zczxzs = tabZCZXZS.execute(obj);
		data.put("zczxzs", zczxzs);
		
		//超时执行总数
		Tab_WWCQK_RY_CSZXZS tabCSZXZS = new Tab_WWCQK_RY_CSZXZS();
		tabCSZXZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List cszxzs = tabCSZXZS.execute(obj);
		data.put("cszxzs", cszxzs);
		
		//已执行节点总数
		Tab_WWCQK_RY_YZXJDZS tabYZXJDZS = new Tab_WWCQK_RY_YZXJDZS();
		tabYZXJDZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List yzxjdzs = tabYZXJDZS.execute(obj);
		data.put("yzxjdzs", yzxjdzs);
		
		
		//节点超时总数
		Tab_WWCQK_RY_JDCSZS tabJDCSZS = new Tab_WWCQK_RY_JDCSZS();
		tabJDCSZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List jdcszs = tabJDCSZS.execute(obj);
		data.put("jdcszs", jdcszs);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		return "tab_wwcqk";
	}

}