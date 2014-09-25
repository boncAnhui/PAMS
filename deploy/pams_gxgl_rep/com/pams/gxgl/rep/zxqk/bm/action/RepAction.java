package com.pams.gxgl.rep.zxqk.bm.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.services.function.Types;
import com.headray.framework.spec.GlobalConstants;
import com.pams.gxgl.rep.zxqk.bm.service.TabBM_WFBZS;
import com.pams.gxgl.rep.zxqk.bm.service.TabBM_WFBZS_CSZS;
import com.pams.gxgl.rep.zxqk.bm.service.TabBM_WFBZS_ZCZS;
import com.pams.gxgl.rep.zxqk.bm.service.TabBM_YFBZS;
import com.pams.gxgl.rep.zxqk.bm.service.TabBM_YFBZS_CSFBZS;
import com.pams.gxgl.rep.zxqk.bm.service.TabBM_YFBZS_WJZS;
import com.pams.gxgl.rep.zxqk.bm.service.TabBM_YFBZS_ZCFBZS;
import com.pams.gxgl.rep.zxqk.bm.service.TabBM_YFQZS;
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

	public String main_zxqk() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		
		return "main_zxqk";
	}
	
	public String main_local_zxqk() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = ((DynamicObject)Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token)).getFormatAttr(GlobalConstants.sys_login_org_internal);

		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		
		return "main_zxqk";
	}
	
	public String tab_zxqk() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		
		TabBM_YFQZS tabYFQZS = new TabBM_YFQZS();
		tabYFQZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		TabBM_YFBZS tabYFBZS = new TabBM_YFBZS();
		tabYFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		TabBM_WFBZS tabWFBZS = new TabBM_WFBZS();
		tabWFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		TabBM_YFBZS_ZCFBZS tabYFBZS_ZCFBZS = new TabBM_YFBZS_ZCFBZS();
		tabYFBZS_ZCFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		TabBM_YFBZS_CSFBZS tabYFBZS_CSFBZS = new TabBM_YFBZS_CSFBZS();
		tabYFBZS_CSFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());		
		
		TabBM_YFBZS_WJZS tabYFBZS_WJZS = new TabBM_YFBZS_WJZS();
		tabYFBZS_WJZS.setJdbcTemplate(reportDao.getJdbcTemplate());	
		
		TabBM_WFBZS_ZCZS tabWFBZS_ZCZS = new TabBM_WFBZS_ZCZS();
		tabWFBZS_ZCZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		TabBM_WFBZS_CSZS tabWFBZS_CSZS = new TabBM_WFBZS_CSZS();
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
		
		return "tab_zxqk";
	}
	
	public String xls_zxqk() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		
		TabBM_YFQZS tabYFQZS = new TabBM_YFQZS();
		tabYFQZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		TabBM_YFBZS tabYFBZS = new TabBM_YFBZS();
		tabYFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		TabBM_WFBZS tabWFBZS = new TabBM_WFBZS();
		tabWFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		TabBM_YFBZS_ZCFBZS tabYFBZS_ZCFBZS = new TabBM_YFBZS_ZCFBZS();
		tabYFBZS_ZCFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		TabBM_YFBZS_CSFBZS tabYFBZS_CSFBZS = new TabBM_YFBZS_CSFBZS();
		tabYFBZS_CSFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());		
		
		TabBM_YFBZS_WJZS tabYFBZS_WJZS = new TabBM_YFBZS_WJZS();
		tabYFBZS_WJZS.setJdbcTemplate(reportDao.getJdbcTemplate());	
		
		TabBM_WFBZS_ZCZS tabWFBZS_ZCZS = new TabBM_WFBZS_ZCZS();
		tabWFBZS_ZCZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		TabBM_WFBZS_CSZS tabWFBZS_CSZS = new TabBM_WFBZS_CSZS();
		tabWFBZS_CSZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List yfqzs = tabYFQZS.execute(obj);
		List yfbzs = tabYFBZS.execute(obj);
		List wfbzs = tabWFBZS.execute(obj);
		List yfbzs_zcfbzs = tabYFBZS_ZCFBZS.execute(obj);
		List yfbzs_csfbzs = tabYFBZS_CSFBZS.execute(obj);
		List yfbzs_wjzs = tabYFBZS_WJZS.execute(obj);
		List wfbzs_zczs = tabWFBZS_ZCZS.execute(obj);
		List wfbzs_cszs = tabWFBZS_CSZS.execute(obj);
		
		List datas = new ArrayList();
		for(int i=0;i<yfqzs.size();i++)
		{
			DynamicObject aobj = new DynamicObject();
			aobj.put("orgcname", ((DynamicObject)yfqzs.get(i)).getFormatAttr("cname"));
			aobj.put("num_yfqzs", ((DynamicObject)yfqzs.get(i)).getFormatAttr("num"));
			aobj.put("num_yfbzs", ((DynamicObject)yfbzs.get(i)).getFormatAttr("num"));
			aobj.put("num_wfbzs", ((DynamicObject)wfbzs.get(i)).getFormatAttr("num"));
			aobj.put("num_yfbzs_zcfbzs", ((DynamicObject)yfbzs_zcfbzs.get(i)).getFormatAttr("num"));
			aobj.put("num_yfbzs_csfbzs", ((DynamicObject)yfbzs_csfbzs.get(i)).getFormatAttr("num"));
			aobj.put("num_yfbzs_wjzs", ((DynamicObject)yfbzs_wjzs.get(i)).getFormatAttr("num"));
			aobj.put("num_wfbzs_zczs", ((DynamicObject)wfbzs_zczs.get(i)).getFormatAttr("num"));
			aobj.put("num_wfbzs_cszs", ((DynamicObject)wfbzs_cszs.get(i)).getFormatAttr("num"));
			
			double rate_yfbcsl = 0;
			double num_yfbzs = Types.parseFloat(((DynamicObject)yfbzs.get(i)).getFormatAttr("num"), 0);
			double num_yfbzs_csfbzs = Types.parseFloat(((DynamicObject)yfbzs_csfbzs.get(i)).getFormatAttr("num"), 0);
			if(num_yfbzs!=0)
			{
				rate_yfbcsl = num_yfbzs_csfbzs / num_yfbzs;
			}
			
			double rate_wfbcsl = 0;
			double num_wfbzs = Types.parseFloat(((DynamicObject)wfbzs.get(i)).getFormatAttr("num"), 0);
			double num_wfbzs_csfbzs = Types.parseFloat(((DynamicObject)wfbzs_cszs.get(i)).getFormatAttr("num"), 0);
			if(num_wfbzs!=0)
			{
				rate_wfbcsl = num_wfbzs_csfbzs / num_wfbzs;
			}
			
			rate_yfbcsl = Math.round(rate_yfbcsl*10000)/100.0;
			rate_wfbcsl = Math.round(rate_wfbcsl*10000)/100.0;
			
			aobj.put("rate_yfbcsl", rate_yfbcsl);
			aobj.put("rate_wfbcsl", rate_wfbcsl);
			
			datas.add(aobj);
		}
		
		String[] cnames = new String[]{ "部门", "已发起总数", "已发布总数","未发布总数","正常发布总数","超时发布总数","发布文件总数","已发布超时率","正常执行总数","超时执行总数","未发布超时率"};
		String[] pnames = new String[]{ "orgcname", "num_yfqzs","num_yfbzs","num_wfbzs","num_yfbzs_zcfbzs","num_yfbzs_csfbzs","num_yfbzs_wjzs","rate_yfbcsl","num_wfbzs_zczs","num_wfbzs_cszs","rate_wfbcsl"};
		String fileName = "执行情况部门统计.xls";

		exp_excel(cnames, pnames, datas, fileName);
		
		return "excel";
	}

}