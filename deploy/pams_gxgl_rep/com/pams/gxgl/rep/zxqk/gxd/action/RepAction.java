package com.pams.gxgl.rep.zxqk.gxd.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.action.SimpleAction;
import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.pams.gxgl.rep.zxqk.gxd.service.TabGXD_YFQZS;
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

	public String main_zxqk_yfqzs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String ownerctx = Struts2Utils.getRequest().getParameter("ownerctx");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("ownerctx", ownerctx);
		
		return "main_zxqk_yfqzs";
	}
	
	public String tab_zxqk_yfqzs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String ownerctx = Struts2Utils.getRequest().getParameter("ownerctx");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("ownerctx", ownerctx);
		
		TabGXD_YFQZS tabYFQZS = new TabGXD_YFQZS();
		tabYFQZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List yfqzs = tabYFQZS.execute(obj);
		
		data.put("zxscs", yfqzs);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("ownerctx", ownerctx);
		
		return "tab_zxqk_yfqzs";
	}

}