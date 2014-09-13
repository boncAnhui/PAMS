package com.pams.gxgl.rep.wcqk.ry.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.action.SimpleAction;
import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.pams.gxgl.rep.zxqk.ry.service.TabRY_WFBZS;
import com.pams.gxgl.rep.zxqk.ry.service.TabRY_WFBZS_CSZS;
import com.pams.gxgl.rep.zxqk.ry.service.TabRY_WFBZS_ZCZS;
import com.pams.gxgl.rep.zxqk.ry.service.TabRY_YFBZS;
import com.pams.gxgl.rep.zxqk.ry.service.TabRY_YFBZS_CSFBZS;
import com.pams.gxgl.rep.zxqk.ry.service.TabRY_YFBZS_WJZS;
import com.pams.gxgl.rep.zxqk.ry.service.TabRY_YFBZS_ZCFBZS;
import com.pams.gxgl.rep.zxqk.ry.service.TabRY_YFQZS;
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

	public String main_wcqk() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		
		return "main_wcqk";
	}
	
	public String tab_wcqk() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		
		
		TabRY_YFQZS tabYFQZS = new TabRY_YFQZS();
		tabYFQZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		TabRY_YFBZS tabYFBZS = new TabRY_YFBZS();
		tabYFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		TabRY_WFBZS tabWFBZS = new TabRY_WFBZS();
		tabWFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		TabRY_YFBZS_ZCFBZS tabYFBZS_ZCFBZS = new TabRY_YFBZS_ZCFBZS();
		tabYFBZS_ZCFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		TabRY_YFBZS_CSFBZS tabYFBZS_CSFBZS = new TabRY_YFBZS_CSFBZS();
		tabYFBZS_CSFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());		
		
		TabRY_YFBZS_WJZS tabYFBZS_WJZS = new TabRY_YFBZS_WJZS();
		tabYFBZS_WJZS.setJdbcTemplate(reportDao.getJdbcTemplate());	
		
		TabRY_WFBZS_ZCZS tabWFBZS_ZCZS = new TabRY_WFBZS_ZCZS();
		tabWFBZS_ZCZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		TabRY_WFBZS_CSZS tabWFBZS_CSZS = new TabRY_WFBZS_CSZS();
		tabWFBZS_CSZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List yfqzs = tabYFQZS.execute(obj);
		List yfbzs = tabYFBZS.execute(obj);
		List wfbzs = tabWFBZS.execute(obj);
		List yfbzs_zcfbzs = tabYFBZS_ZCFBZS.execute(obj);
		List yfbzs_csfbzs = tabYFBZS_CSFBZS.execute(obj);
		List yfbzs_wjzs = tabYFBZS_WJZS.execute(obj);
		List wfbzs_zczs = tabWFBZS_ZCZS.execute(obj);
		List wfbzs_cszs = tabWFBZS_CSZS.execute(obj);
		
		data.put("yfqzs", yfqzs);
		data.put("yfbzs", yfbzs);
		data.put("wfbzs", wfbzs);
		data.put("yfbzs_zcfbzs", yfbzs_zcfbzs);
		data.put("yfbzs_csfbzs", yfbzs_csfbzs);	
		data.put("yfbzs_wjzs", yfbzs_wjzs);	
		data.put("wfbzs_zczs", wfbzs_zczs);	
		data.put("wfbzs_cszs", wfbzs_cszs);	
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		
		return "tab_wcqk";
	}

}