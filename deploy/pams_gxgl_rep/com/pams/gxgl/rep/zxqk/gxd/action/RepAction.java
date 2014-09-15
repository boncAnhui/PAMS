package com.pams.gxgl.rep.zxqk.gxd.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.action.SimpleAction;
import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.pams.gxgl.rep.zxqk.gxd.service.TabGXD_WFBZS;
import com.pams.gxgl.rep.zxqk.gxd.service.TabGXD_WFBZS_CSZS;
import com.pams.gxgl.rep.zxqk.gxd.service.TabGXD_WFBZS_ZCZS;
import com.pams.gxgl.rep.zxqk.gxd.service.TabGXD_YFBZS;
import com.pams.gxgl.rep.zxqk.gxd.service.TabGXD_YFBZS_CSFBZS;
import com.pams.gxgl.rep.zxqk.gxd.service.TabGXD_YFBZS_ZCFBZS;
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
	
	public String main_zxqk_yfbzs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String ownerctx = Struts2Utils.getRequest().getParameter("ownerctx");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("ownerctx", ownerctx);
		
		return "main_zxqk_yfbzs";
	}
	
	public String tab_zxqk_yfbzs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String ownerctx = Struts2Utils.getRequest().getParameter("ownerctx");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("ownerctx", ownerctx);
		
		TabGXD_YFBZS tabYFBZS = new TabGXD_YFBZS();
		tabYFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List yfbzs = tabYFBZS.execute(obj);
		
		data.put("zxscs", yfbzs);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("ownerctx", ownerctx);
		
		return "tab_zxqk_yfbzs";
	}
	
	public String main_zxqk_wfbzs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String ownerctx = Struts2Utils.getRequest().getParameter("ownerctx");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("ownerctx", ownerctx);
		
		return "main_zxqk_wfbzs";
	}
	
	public String tab_zxqk_wfbzs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String ownerctx = Struts2Utils.getRequest().getParameter("ownerctx");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("ownerctx", ownerctx);
		
		TabGXD_WFBZS tabWFBZS = new TabGXD_WFBZS();
		tabWFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List wfbzs = tabWFBZS.execute(obj);
		
		data.put("zxscs", wfbzs);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("ownerctx", ownerctx);
		
		return "tab_zxqk_wfbzs";
	}
	
	public String main_zxqk_yfbzs_zcfbzs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String ownerctx = Struts2Utils.getRequest().getParameter("ownerctx");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("ownerctx", ownerctx);
		
		return "main_zxqk_yfbzs_zcfbzs";
	}
	
	public String tab_zxqk_yfbzs_zcfbzs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String ownerctx = Struts2Utils.getRequest().getParameter("ownerctx");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("ownerctx", ownerctx);
		
		TabGXD_YFBZS_ZCFBZS tabYFBZS_ZCFBZS = new TabGXD_YFBZS_ZCFBZS();
		tabYFBZS_ZCFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List yfbzs_zcfbzs = tabYFBZS_ZCFBZS.execute(obj);
		
		data.put("zxscs", yfbzs_zcfbzs);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("ownerctx", ownerctx);
		
		return "tab_zxqk_yfbzs_zcfbzs";
	}
	
	public String main_zxqk_yfbzs_csfbzs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String ownerctx = Struts2Utils.getRequest().getParameter("ownerctx");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("ownerctx", ownerctx);
		
		return "main_zxqk_yfbzs_csfbzs";
	}
	
	public String tab_zxqk_yfbzs_csfbzs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String ownerctx = Struts2Utils.getRequest().getParameter("ownerctx");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("ownerctx", ownerctx);
		
		TabGXD_YFBZS_CSFBZS tabYFBZS_CSFBZS = new TabGXD_YFBZS_CSFBZS();
		tabYFBZS_CSFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List yfbzs_csfbzs = tabYFBZS_CSFBZS.execute(obj);
		
		data.put("zxscs", yfbzs_csfbzs);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("ownerctx", ownerctx);
		
		return "tab_zxqk_yfbzs_csfbzs";
	}	
	
	public String main_zxqk_wfbzs_zczs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String ownerctx = Struts2Utils.getRequest().getParameter("ownerctx");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("ownerctx", ownerctx);
		
		return "main_zxqk_wfbzs_zczs";
	}
	
	public String tab_zxqk_wfbzs_zczs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String ownerctx = Struts2Utils.getRequest().getParameter("ownerctx");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("ownerctx", ownerctx);
		
		TabGXD_WFBZS_ZCZS tabWFBZS_ZCZS = new TabGXD_WFBZS_ZCZS();
		tabWFBZS_ZCZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List wfbzs_zczs = tabWFBZS_ZCZS.execute(obj);
		
		data.put("zxscs", wfbzs_zczs);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("ownerctx", ownerctx);
		
		return "tab_zxqk_wfbzs_zczs";
	}
	
	public String main_zxqk_wfbzs_cszs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String ownerctx = Struts2Utils.getRequest().getParameter("ownerctx");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("ownerctx", ownerctx);
		
		return "main_zxqk_wfbzs_cszs";
	}
	
	public String tab_zxqk_wfbzs_cszs() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String ownerctx = Struts2Utils.getRequest().getParameter("ownerctx");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("ownerctx", ownerctx);
		
		TabGXD_WFBZS_CSZS tabWFBZS_CSZS = new TabGXD_WFBZS_CSZS();
		tabWFBZS_CSZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List wfbzs_cszs = tabWFBZS_CSZS.execute(obj);
		
		data.put("zxscs", wfbzs_cszs);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("ownerctx", ownerctx);
		
		return "tab_zxqk_wfbzs_cszs";
	}


}