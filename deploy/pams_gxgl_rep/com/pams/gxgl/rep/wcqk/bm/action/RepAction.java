package com.pams.gxgl.rep.wcqk.bm.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.services.function.Types;
import com.pams.gxgl.rep.wcqk.bm.service.Tab_WCQK_BM_CSFBJDZS;
import com.pams.gxgl.rep.wcqk.bm.service.Tab_WCQK_BM_CSFBZS;
import com.pams.gxgl.rep.wcqk.bm.service.Tab_WCQK_BM_FBZS;
import com.pams.gxgl.rep.wcqk.bm.service.Tab_WCQK_BM_YFBZXSC;
import com.pams.gxgl.rep.wcqk.bm.service.Tab_WCQK_BM_ZCFBJDZS;
import com.pams.gxgl.rep.wcqk.bm.service.Tab_WCQK_BM_ZCFBZS;
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

	public String main_wcqk() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		this.arg.put("begindate", begindate);
		this.arg.put("enddate", enddate);
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
		Tab_WCQK_BM_FBZS tabFBZS = new Tab_WCQK_BM_FBZS();
		tabFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List fbzs = tabFBZS.execute(obj);
		data.put("fbzs", fbzs);
		
		
		//正常发布总数
		Tab_WCQK_BM_ZCFBZS tabZCFBZS = new Tab_WCQK_BM_ZCFBZS();
		tabZCFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List zcfbzs = tabZCFBZS.execute(obj);
		data.put("zcfbzs", zcfbzs);
		
		//正常发布节点总数
		Tab_WCQK_BM_ZCFBJDZS tabZCFBJDZS = new Tab_WCQK_BM_ZCFBJDZS();
		tabZCFBJDZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List zcfbjdzs = tabZCFBJDZS.execute(obj);
		data.put("zcfbjdzs", zcfbjdzs);
		
		//超时发布节点总数
		Tab_WCQK_BM_CSFBJDZS tabCSFBJDZS = new Tab_WCQK_BM_CSFBJDZS();
		tabCSFBJDZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List csfbjdzs = tabCSFBJDZS.execute(obj);
		data.put("csfbjdzs", csfbjdzs);
		
		//超时发布总数
		Tab_WCQK_BM_CSFBZS tabCSFBZS = new Tab_WCQK_BM_CSFBZS();
		tabCSFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List csfbzs = tabCSFBZS.execute(obj);
		data.put("csfbzs", csfbzs);
		
		//计划执行时长、实际执行时长
		Tab_WCQK_BM_YFBZXSC tabYFBZXSC = new Tab_WCQK_BM_YFBZXSC();
		tabYFBZXSC.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List yfbsxzc = tabYFBZXSC.execute(obj);
		data.put("yfbsxzc", yfbsxzc);
		
		arg.put("begindate", begindate);
		arg.put("enddate", enddate);
		arg.put("internal", internal);
		return "tab_wcqk";
	}
	public String xls_wcqk() throws Exception
	{
		String begindate = Struts2Utils.getRequest().getParameter("begindate");
		String enddate = Struts2Utils.getRequest().getParameter("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		
		//发布总数
		Tab_WCQK_BM_FBZS tabFBZS = new Tab_WCQK_BM_FBZS();
		tabFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List fbzs = tabFBZS.execute(obj);
		data.put("fbzs", fbzs);
		
		
		//正常发布总数
		Tab_WCQK_BM_ZCFBZS tabZCFBZS = new Tab_WCQK_BM_ZCFBZS();
		tabZCFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List zcfbzs = tabZCFBZS.execute(obj);
		data.put("zcfbzs", zcfbzs);
		
		//正常发布节点总数
		Tab_WCQK_BM_ZCFBJDZS tabZCFBJDZS = new Tab_WCQK_BM_ZCFBJDZS();
		tabZCFBJDZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List zcfbjdzs = tabZCFBJDZS.execute(obj);
		data.put("zcfbjdzs", zcfbjdzs);
		
		//超时发布节点总数
		Tab_WCQK_BM_CSFBJDZS tabCSFBJDZS = new Tab_WCQK_BM_CSFBJDZS();
		tabCSFBJDZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List csfbjdzs = tabCSFBJDZS.execute(obj);
		data.put("csfbjdzs", csfbjdzs);
		
		//超时发布总数
		Tab_WCQK_BM_CSFBZS tabCSFBZS = new Tab_WCQK_BM_CSFBZS();
		tabCSFBZS.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List csfbzs = tabCSFBZS.execute(obj);
		data.put("csfbzs", csfbzs);
		
		//计划执行时长、实际执行时长
		Tab_WCQK_BM_YFBZXSC tabYFBZXSC = new Tab_WCQK_BM_YFBZXSC();
		tabYFBZXSC.setJdbcTemplate(reportDao.getJdbcTemplate());
		
		List yfbsxzc = tabYFBZXSC.execute(obj);
		data.put("yfbsxzc", yfbsxzc);
		
		List datas = new ArrayList();
		
		for(int i=0;i<fbzs.size();i++)
		{
			DynamicObject aobj = new DynamicObject();
			aobj.put("orgcname", ((DynamicObject)fbzs.get(i)).getFormatAttr("cname"));
			aobj.put("num_fbzs", ((DynamicObject)fbzs.get(i)).getFormatAttr("num"));
			aobj.put("num_zcfbzs", ((DynamicObject)zcfbzs.get(i)).getFormatAttr("num"));
			aobj.put("num_zcfbjdzs", ((DynamicObject)zcfbjdzs.get(i)).getFormatAttr("num"));
			aobj.put("num_csfbjdzs", ((DynamicObject)csfbjdzs.get(i)).getFormatAttr("num"));
			aobj.put("num_csfbzs", ((DynamicObject)csfbzs.get(i)).getFormatAttr("num"));
			aobj.put("num_jhsc", ((DynamicObject)yfbsxzc.get(i)).getFormatAttr("jhsc"));
			aobj.put("num_sjsc", ((DynamicObject)yfbsxzc.get(i)).getFormatAttr("sjsc"));
			
			double rate_jdcsb = 0;
			double num_zcfbjdzs = Types.parseFloat(((DynamicObject)zcfbjdzs.get(i)).getFormatAttr("num"), 0);
			double num_csfbjdzs = Types.parseFloat(((DynamicObject)csfbjdzs.get(i)).getFormatAttr("num"), 0);
			if((num_zcfbjdzs + num_csfbjdzs) != 0)
			{
				rate_jdcsb = num_csfbjdzs / (num_zcfbjdzs + num_csfbjdzs);
			}
			
			double rate_zxxl = 0;
			double num_jhsc = Types.parseFloat(((DynamicObject)yfbsxzc.get(i)).getFormatAttr("jhsc"), 0);
			double num_sjsc = Types.parseFloat(((DynamicObject)yfbsxzc.get(i)).getFormatAttr("sjsc"), 0);
			if(num_sjsc!=0)
			{
				rate_zxxl = num_jhsc / num_sjsc;
			}
			
			rate_jdcsb = Math.round(rate_jdcsb*10000)/100.0;
			rate_zxxl = Math.round(rate_zxxl*10000)/100.0;
			
			aobj.put("rate_jdcsb", rate_jdcsb);
			aobj.put("rate_zxxl", rate_zxxl);
			
			datas.add(aobj);
		}
		
		String[] cnames = new String[]{ "统计单位","已发布总数","	正常发布总数","正常发布节点总数","超时发布节点总数","节点超时比%","超时发布总数","计划执行时长","实际执行时长","执行效率%"};
		String[] pnames = new String[]{ "orgcname", "num_fbzs","num_zcfbzs","num_zcfbjdzs","num_csfbjdzs","rate_jdcsb","num_csfbzs","num_jhsc","num_sjsc","rate_zxxl"};
		String fileName = "信息共享完成情况部门统计.xls";

		exp_excel(cnames, pnames, datas, fileName);
		
		return "excel";
	}

}