package com.pams.gxgl.rep.wwcqk.bm.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.pams.gxgl.rep.wwcqk.bm.service.Tab_WWCQK_BM_CSZXZS;
import com.pams.gxgl.rep.wwcqk.bm.service.Tab_WWCQK_BM_JDCSZS;
import com.pams.gxgl.rep.wwcqk.bm.service.Tab_WWCQK_BM_WFBZS;
import com.pams.gxgl.rep.wwcqk.bm.service.Tab_WWCQK_BM_YZXJDZS;
import com.pams.gxgl.rep.wwcqk.bm.service.Tab_WWCQK_BM_ZCZXZS;
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

	public String main_wwcqk() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		arg.put("reptype", "infoshare");
		return "main_wwcqk";
	}
	
	public String tab_wwcqk() throws Exception
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

		//未发布总数
		Tab_WWCQK_BM_WFBZS tabWFBZS = new Tab_WWCQK_BM_WFBZS();
		tabWFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List wfbzs = tabWFBZS.execute(obj);
		data.put("wfbzs", wfbzs);
		
		//正常执行总数
		Tab_WWCQK_BM_ZCZXZS tabZCZXZS = new Tab_WWCQK_BM_ZCZXZS();
		tabZCZXZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List zczxzs = tabZCZXZS.execute(obj);
		data.put("zczxzs", zczxzs);
		
		
		//超时执行总数
		Tab_WWCQK_BM_CSZXZS tabCSZXZS = new Tab_WWCQK_BM_CSZXZS();
		tabCSZXZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List cszxzs = tabCSZXZS.execute(obj);
		data.put("cszxzs", cszxzs);
		
		//已执行节点总数
		Tab_WWCQK_BM_YZXJDZS tabYZXJDZS = new Tab_WWCQK_BM_YZXJDZS();
		tabYZXJDZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List yzxjdzs = tabYZXJDZS.execute(obj);
		data.put("yzxjdzs", yzxjdzs);
		
		//节点超时总数
		Tab_WWCQK_BM_JDCSZS tabJDCSZS = new Tab_WWCQK_BM_JDCSZS();
		tabJDCSZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List jdcszs = tabJDCSZS.execute(obj);
		data.put("jdcszs", jdcszs);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		return "tab_wwcqk";
	}
	
	
	public String xls_wwcqk() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		
		//未发布总数
		Tab_WWCQK_BM_WFBZS tabWFBZS = new Tab_WWCQK_BM_WFBZS();
		tabWFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		//正常执行总数
		Tab_WWCQK_BM_ZCZXZS tabZCZXZS = new Tab_WWCQK_BM_ZCZXZS();
		tabZCZXZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		//超时执行总数
		Tab_WWCQK_BM_CSZXZS tabCSZXZS = new Tab_WWCQK_BM_CSZXZS();
		tabCSZXZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		//已执行节点总数
		Tab_WWCQK_BM_YZXJDZS tabYZXJDZS = new Tab_WWCQK_BM_YZXJDZS();
		tabYZXJDZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		//节点超时总数
		Tab_WWCQK_BM_JDCSZS tabJDCSZS = new Tab_WWCQK_BM_JDCSZS();
		tabJDCSZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List wfbzs = tabWFBZS.execute(obj);
		data.put("wfbzs", wfbzs);
		
		List zczxzs = tabZCZXZS.execute(obj);
		data.put("zczxzs", zczxzs);
		

		List cszxzs = tabCSZXZS.execute(obj);
		data.put("cszxzs", cszxzs);
		
		List yzxjdzs = tabYZXJDZS.execute(obj);
		data.put("yzxjdzs", yzxjdzs);

		List jdcszs = tabJDCSZS.execute(obj);
		data.put("jdcszs", jdcszs);
		
		List datas = new ArrayList();
		
		for(int i=0;i<wfbzs.size();i++)
		{
			DynamicObject aobj = new DynamicObject();
			aobj.put("orgcname", ((DynamicObject)wfbzs.get(i)).getFormatAttr("cname"));
			aobj.put("num_wfbzs", ((DynamicObject)wfbzs.get(i)).getFormatAttr("num"));
			aobj.put("num_zczxzs", ((DynamicObject)zczxzs.get(i)).getFormatAttr("num"));
			aobj.put("num_cszxzs", ((DynamicObject)cszxzs.get(i)).getFormatAttr("num"));
			aobj.put("num_yzxjdzs", ((DynamicObject)yzxjdzs.get(i)).getFormatAttr("num"));
			aobj.put("num_jdcszs", ((DynamicObject)jdcszs.get(i)).getFormatAttr("num"));
			
					
			datas.add(aobj);
		}
		
		String[] cnames = new String[]{ "部门","未发布总数","正常执行总数","超时执行总数","已执行节点数","节点超期总数"};
		String[] pnames = new String[]{ "orgcname", "num_wfbzs","num_zczxzs","num_cszxzs","num_yzxjdzs","num_jdcszs"};
		String fileName = "信息未共享完成情况部门统计.xls";

		exp_excel(cnames, pnames, datas, fileName);
		
		return "excel";
	}

}