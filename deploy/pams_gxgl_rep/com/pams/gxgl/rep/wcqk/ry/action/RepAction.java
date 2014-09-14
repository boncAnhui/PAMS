package com.pams.gxgl.rep.wcqk.ry.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.action.SimpleAction;
import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.pams.gxgl.rep.wcqk.ry.service.Tab_WCQK_RY_CSFBJDZS;
import com.pams.gxgl.rep.wcqk.ry.service.Tab_WCQK_RY_CSFBZS;
import com.pams.gxgl.rep.wcqk.ry.service.Tab_WCQK_RY_FBZS;
import com.pams.gxgl.rep.wcqk.ry.service.Tab_WCQK_RY_YFBZXSC;
import com.pams.gxgl.rep.wcqk.ry.service.Tab_WCQK_RY_ZCFBJDZS;
import com.pams.gxgl.rep.wcqk.ry.service.Tab_WCQK_RY_ZCFBZS;
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
		
		
		//发布总数
		Tab_WCQK_RY_FBZS tabFBZS = new Tab_WCQK_RY_FBZS();
		tabFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List fbzs = tabFBZS.execute(obj);
		data.put("fbzs", fbzs);
		
		
		//正常发布总数
		Tab_WCQK_RY_ZCFBZS tabZCFBZS = new Tab_WCQK_RY_ZCFBZS();
		tabZCFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List zcfbzs = tabZCFBZS.execute(obj);
		data.put("zcfbzs", zcfbzs);
		
		//正常发布节点总数
		Tab_WCQK_RY_ZCFBJDZS tabZCFBJDZS = new Tab_WCQK_RY_ZCFBJDZS();
		tabZCFBJDZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List zcfbjdzs = tabZCFBJDZS.execute(obj);
		data.put("zcfbjdzs", zcfbjdzs);
		
		//超时发布节点总数
		Tab_WCQK_RY_CSFBJDZS tabCSFBJDZS = new Tab_WCQK_RY_CSFBJDZS();
		tabCSFBJDZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List csfbjdzs = tabCSFBJDZS.execute(obj);
		data.put("csfbjdzs", csfbjdzs);
		
		//超时发布总数
		Tab_WCQK_RY_CSFBZS tabCSFBZS = new Tab_WCQK_RY_CSFBZS();
		tabCSFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List csfbzs = tabCSFBZS.execute(obj);
		data.put("csfbzs", csfbzs);
		
		//计划执行时长、实际执行时长
		Tab_WCQK_RY_YFBZXSC tabYFBZXSC = new Tab_WCQK_RY_YFBZXSC();
		tabYFBZXSC.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List yfbsxzc = tabYFBZXSC.execute(obj);
		data.put("yfbsxzc", yfbsxzc);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		
		return "tab_wcqk";
	}

}