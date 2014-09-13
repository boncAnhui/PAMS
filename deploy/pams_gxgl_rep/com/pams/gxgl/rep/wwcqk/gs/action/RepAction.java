package com.pams.gxgl.rep.wwcqk.gs.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.action.SimpleAction;
import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.pams.gxgl.rep.zxqk.gs.service.TabWFBZS;
import com.pams.gxgl.rep.zxqk.gs.service.TabWFBZS_CSZS;
import com.pams.gxgl.rep.zxqk.gs.service.TabWFBZS_ZCZS;
import com.pams.gxgl.rep.zxqk.gs.service.TabYFBZS;
import com.pams.gxgl.rep.zxqk.gs.service.TabYFBZS_CSFBZS;
import com.pams.gxgl.rep.zxqk.gs.service.TabYFBZS_WJZS;
import com.pams.gxgl.rep.zxqk.gs.service.TabYFBZS_ZCFBZS;
import com.pams.gxgl.rep.zxqk.gs.service.TabYFQZS;
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
		this.arg.put("begindate", begindate);
		this.arg.put("enddate", enddate);
		return "main_wwcqk";
	}
	
	public String tab_wwcqk() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		
		TabYFQZS tabYFQZS = new TabYFQZS();
		tabYFQZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		TabYFBZS tabYFBZS = new TabYFBZS();
		tabYFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		TabWFBZS tabWFBZS = new TabWFBZS();
		tabWFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		TabYFBZS_ZCFBZS tabYFBZS_ZCFBZS = new TabYFBZS_ZCFBZS();
		tabYFBZS_ZCFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		TabYFBZS_CSFBZS tabYFBZS_CSFBZS = new TabYFBZS_CSFBZS();
		tabYFBZS_CSFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());		
		
		TabYFBZS_WJZS tabYFBZS_WJZS = new TabYFBZS_WJZS();
		tabYFBZS_WJZS.setJdbcTemplate(reportDao.getJdbcTemplate());	
		
		TabWFBZS_ZCZS tabWFBZS_ZCZS = new TabWFBZS_ZCZS();
		tabWFBZS_ZCZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		TabWFBZS_CSZS tabWFBZS_CSZS = new TabWFBZS_CSZS();
		tabWFBZS_CSZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		String yfqzs = ((DynamicObject)(tabYFQZS.execute(obj)).get(0)).getFormatAttr("yfqzs");
		String yfbzs = ((DynamicObject)(tabYFBZS.execute(obj)).get(0)).getFormatAttr("yfbzs");
		String wfbzs = ((DynamicObject)(tabWFBZS.execute(obj)).get(0)).getFormatAttr("yfbzs");
		String yfbzs_zcfbzs = ((DynamicObject)(tabYFBZS_ZCFBZS.execute(obj)).get(0)).getFormatAttr("num");
		String yfbzs_csfbzs = ((DynamicObject)(tabYFBZS_CSFBZS.execute(obj)).get(0)).getFormatAttr("num");
		String yfbzs_wjzs = ((DynamicObject)(tabYFBZS_WJZS.execute(obj)).get(0)).getFormatAttr("num");
		String wfbzs_zczs = ((DynamicObject)(tabWFBZS_ZCZS.execute(obj)).get(0)).getFormatAttr("num");
		String wfbzs_cszs = ((DynamicObject)(tabWFBZS_CSZS.execute(obj)).get(0)).getFormatAttr("num");
		
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
		return "tab_wwcqk";
	}

}