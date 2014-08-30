package com.pams.kpi.gxgl.action;

import com.blue.ssh.core.action.SimpleAction;
import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.pams.kpi.gxgl.service.KpiWJWHLIST;
import com.pams.kpi.gxgl.service.KpiWJWHSL;
import com.pams.kpi.gxgl.service.KpiWJWHSLBM;
import com.pams.kpi.gxgl.service.KpiXXGXJSL;
import com.pams.kpi.gxgl.service.KpiXXGXJSLBM;
import com.pams.kpi.gxgl.service.KpiXXGXJSLLCJDMX;
import com.pams.kpi.gxgl.service.KpiXXGXJSLLCMX;
import com.pams.kpi.gxgl.service.KpiZXSC;
import com.pams.kpi.gxgl.service.KpiZXSCBM;
import com.pams.kpi.gxgl.service.KpiZXSCMX;
import com.ray.app.chart.report.dao.ReportDao;
import com.ray.app.query.service.QueryService;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class KpiAction extends SimpleAction
{
  private String _searchname;
  @Autowired
  private QueryService queryService;
  @Autowired
  ReportDao reportDao;
  
  public KpiAction() {}
  
  public String mainframe() throws Exception
  {
    return "mainframe";
  }
  
  public String main_zxsc() throws Exception
  {
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    
    this.arg.put("begindate", begindate);
    this.arg.put("enddate", enddate);
    
    return "main_zxsc";
  }
  
  public String kpi_zxsc() throws Exception
  {
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    
    DynamicObject obj = new DynamicObject();
    obj.setAttr("begindate", begindate);
    obj.setAttr("enddate", enddate);
    
    KpiZXSC tab = new KpiZXSC();
    tab.setJdbcTemplate(this.reportDao.getJdbcTemplate());
    List datas = tab.execute(obj);
    this.arg.put("begindate", begindate);
    this.arg.put("enddate", enddate);
    this.data.put("zxscs", datas);
    return "kpi_zxsc";
  }
  

  public String depart_zxsc()
    throws Exception
  {
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    
    this.arg.put("begindate", begindate);
    this.arg.put("enddate", enddate);
    return "depart_zxsc";
  }
  
  public String depart_xxgxjsl()
  {
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    
    this.arg.put("begindate", begindate);
    this.arg.put("enddate", enddate);
    return "depart_xxgxjsl";
  }
  
  public String depart_zxsc_tb() throws Exception
  {
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    
    DynamicObject obj = new DynamicObject();
    obj.setAttr("begindate", begindate);
    obj.setAttr("enddate", enddate);
    
    KpiZXSCBM zxscTab = new KpiZXSCBM();
    zxscTab.setJdbcTemplate(this.reportDao.getJdbcTemplate());
    List datas = zxscTab.execute(obj);
    
    this.arg.put("begindate", begindate);
    this.arg.put("enddate", enddate);
    
    this.data.put("zxscs", datas);
    return "depart_zxsc_tb";
  }
  
  public String depart_xxgxjsl_tb() throws Exception
  {
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    
    DynamicObject obj = new DynamicObject();
    obj.setAttr("begindate", begindate);
    obj.setAttr("enddate", enddate);
    
    KpiXXGXJSLBM xxgxjslTab = new KpiXXGXJSLBM();
    xxgxjslTab.setJdbcTemplate(this.reportDao.getJdbcTemplate());
    List datas = xxgxjslTab.execute(obj);
    
    this.arg.put("begindate", begindate);
    this.arg.put("enddate", enddate);
    
    this.data.put("xxgxjsls", datas);
    return "depart_xxgxjsl_tb";
  }
  
  public String main_zxscmx() throws Exception
  {
    String ownerctx = Struts2Utils.getRequest().getParameter("ownerctx");
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    
    this.arg.put("begindate", begindate);
    this.arg.put("enddate", enddate);
    this.arg.put("ownerctx", ownerctx);
    return "main_zxscmx";
  }
  
  public String kpi_zxscmx() throws Exception
  {
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    String ownerctx = Struts2Utils.getRequest().getParameter("ownerctx");
    
    DynamicObject obj = new DynamicObject();
    obj.setAttr("ownerctx", ownerctx);
    obj.setAttr("begindate", begindate);
    obj.setAttr("enddate", enddate);
    
    KpiZXSCMX tab = new KpiZXSCMX();
    tab.setJdbcTemplate(this.reportDao.getJdbcTemplate());
    List datas = tab.execute(obj);
    
    this.data.put("zxscs", datas);
    return "kpi_zxscmx";
  }
  

  public String main_xxgxjsl()
    throws Exception
  {
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    this.arg.put("begindate", begindate);
    this.arg.put("enddate", enddate);
    
    return "main_xxgxjsl";
  }
  
  public String kpi_xxgxjsl() throws Exception
  {
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    
    DynamicObject obj = new DynamicObject();
    obj.setAttr("begindate", begindate);
    obj.setAttr("enddate", enddate);
    
    KpiXXGXJSL tab = new KpiXXGXJSL();
    tab.setJdbcTemplate(this.reportDao.getJdbcTemplate());
    List datas = tab.execute(obj);
    
    this.data.put("xxgxjsls", datas);
    
    this.arg.put("begindate", begindate);
    this.arg.put("enddate", enddate);
    
    return "kpi_xxgxjsl";
  }
  
  public String main_xxgxjsllcmx()
    throws Exception
  {
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    String loginname = Struts2Utils.getRequest().getParameter("loginname");
    
    this.arg.put("begindate", begindate);
    this.arg.put("enddate", enddate);
    this.arg.put("loginname", loginname);
    
    return "main_xxgxjsllcmx";
  }
  
  public String kpi_xxgxjsllcmx() throws Exception
  {
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    String loginname = Struts2Utils.getRequest().getParameter("loginname");
    

    DynamicObject obj = new DynamicObject();
    obj.setAttr("begindate", begindate);
    obj.setAttr("enddate", enddate);
    obj.setAttr("loginname", loginname);
    
    KpiXXGXJSLLCMX tab = new KpiXXGXJSLLCMX();
    tab.setJdbcTemplate(this.reportDao.getJdbcTemplate());
    List datas = tab.execute(obj);
    this.data.put("xxgxjsllcmxs", datas);
    this.arg.put("begindate", begindate);
    this.arg.put("enddate", enddate);
    return "kpi_xxgxjsllcmx";
  }
  

  public String main_xxgxjsllcjdmx()
    throws Exception
  {
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    String cno = Struts2Utils.getRequest().getParameter("cno");
    
    DynamicObject obj = new DynamicObject();
    obj.setAttr("begindate", begindate);
    obj.setAttr("enddate", enddate);
    obj.setAttr("cno", cno);
    
    this.arg.put("begindate", begindate);
    this.arg.put("enddate", enddate);
    this.arg.put("cno", cno);
    
    return "main_xxgxjsllcjdmx";
  }
  
  public String kpi_xxgxjsllcjdmx() throws Exception
  {
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    String cno = Struts2Utils.getRequest().getParameter("cno");
    
    DynamicObject obj = new DynamicObject();
    obj.setAttr("begindate", begindate);
    obj.setAttr("enddate", enddate);
    obj.setAttr("cno", cno);
    
    KpiXXGXJSLLCJDMX tab = new KpiXXGXJSLLCJDMX();
    tab.setJdbcTemplate(this.reportDao.getJdbcTemplate());
    List datas = tab.execute(obj);
    this.data.put("xxgxjsllcjdmxs", datas);
    
    this.arg.put("begindate", begindate);
    this.arg.put("enddate", enddate);
    return "kpi_xxgxjsllcjdmx";
  }
  





  public String main_wjwhsl()
  {
    String ownerctx = Struts2Utils.getRequest().getParameter("ownerctx");
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    String deptid = Struts2Utils.getRequest().getParameter("deptid");
    
    DynamicObject obj = new DynamicObject();
    obj.setAttr("begindate", begindate);
    obj.setAttr("enddate", enddate);
    obj.setAttr("deptid", deptid);
    
    this.arg.put("begindate", begindate);
    this.arg.put("enddate", enddate);
    this.arg.put("ownerctx", ownerctx);
    this.arg.put("deptid", deptid);
    
    return "main_wjwhsl";
  }
  

  public String main_wjwhlist()
  {
    String ownerctx = Struts2Utils.getRequest().getParameter("ownerctx");
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    String creater = Struts2Utils.getRequest().getParameter("creater");
    
    DynamicObject obj = new DynamicObject();
    obj.setAttr("begindate", begindate);
    obj.setAttr("enddate", enddate);
    obj.setAttr("creater", creater);
    
    this.arg.put("begindate", begindate);
    this.arg.put("enddate", enddate);
    this.arg.put("ownerctx", ownerctx);
    this.arg.put("creater", creater);
    
    return "main_wjwhlist";
  }
  
  public String kpi_wjwhsl() throws Exception
  {
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    String deptid = Struts2Utils.getRequest().getParameter("deptid");
    
    DynamicObject obj = new DynamicObject();
    obj.setAttr("begindate", begindate);
    obj.setAttr("enddate", enddate);
    obj.setAttr("deptid", deptid);
    
    KpiWJWHSL wjwhsl = new KpiWJWHSL();
    wjwhsl.setJdbcTemplate(this.reportDao.getJdbcTemplate());
    List datas = wjwhsl.execute(obj);
    this.arg.put("begindate", begindate);
    this.arg.put("enddate", enddate);
    this.data.put("wjwhsls", datas);
    this.arg.put("deptid", deptid);
    return "kpi_wjwhsl";
  }
  
  public String kpi_wjwhlist() throws Exception
  {
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    String creater = Struts2Utils.getRequest().getParameter("creater");
    
    DynamicObject obj = new DynamicObject();
    obj.setAttr("begindate", begindate);
    obj.setAttr("enddate", enddate);
    obj.setAttr("creater", creater);
    
    KpiWJWHLIST wjwhsl = new KpiWJWHLIST();
    wjwhsl.setJdbcTemplate(this.reportDao.getJdbcTemplate());
    List datas = wjwhsl.execute(obj);
    this.arg.put("begindate", begindate);
    this.arg.put("enddate", enddate);
    this.data.put("wjwhsls", datas);
    this.arg.put("creater", creater);
    return "kpi_wjwhlist";
  }
  
  public String main_wjwhslmx() throws Exception
  {
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    String cno = Struts2Utils.getRequest().getParameter("cno");
    
    DynamicObject obj = new DynamicObject();
    obj.setAttr("begindate", begindate);
    obj.setAttr("enddate", enddate);
    obj.setAttr("cno", cno);
    
    this.arg.put("begindate", begindate);
    this.arg.put("enddate", enddate);
    this.arg.put("cno", cno);
    
    return "main_wjwhslmx";
  }
  
  public String kpi_wjwhslmx() throws Exception
  {
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    String cno = Struts2Utils.getRequest().getParameter("cno");
    
    DynamicObject obj = new DynamicObject();
    obj.setAttr("begindate", begindate);
    obj.setAttr("enddate", enddate);
    obj.setAttr("cno", cno);
    
    KpiXXGXJSLLCJDMX tab = new KpiXXGXJSLLCJDMX();
    tab.setJdbcTemplate(this.reportDao.getJdbcTemplate());
    List datas = tab.execute(obj);
    this.data.put("wjwhslmxs", datas);
    
    this.arg.put("begindate", begindate);
    this.arg.put("enddate", enddate);
    return "kpi_wjwhslmx";
  }
  
  public String depart_wjwhsl() throws Exception
  {
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    String deptid = Struts2Utils.getRequest().getParameter("deptid");
    
    DynamicObject obj = new DynamicObject();
    obj.setAttr("deptid", deptid);
    
    this.arg.put("begindate", begindate);
    this.arg.put("enddate", enddate);
    return "depart_wjwhsl";
  }
  
  public String depart_wjwhsl_tb()
    throws Exception
  {
    String begindate = Struts2Utils.getRequest().getParameter("begindate");
    String enddate = Struts2Utils.getRequest().getParameter("enddate");
    
    DynamicObject obj = new DynamicObject();
    obj.setAttr("begindate", begindate);
    obj.setAttr("enddate", enddate);
    
    KpiWJWHSLBM wjwhslTab = new KpiWJWHSLBM();
    wjwhslTab.setJdbcTemplate(this.reportDao.getJdbcTemplate());
    List datas = wjwhslTab.execute(obj);
    
    this.arg.put("begindate", begindate);
    this.arg.put("enddate", enddate);
    
    this.data.put("wjwhsls", datas);
    return "depart_wjwhsl_tb";
  }
  


  public String get_searchname()
  {
    return this._searchname;
  }
  
  public void set_searchname(String searchname)
  {
    this._searchname = searchname;
  }
  
  public QueryService getQueryService()
  {
    return this.queryService;
  }
  
  public void setQueryService(QueryService queryService)
  {
    this.queryService = queryService;
  }
  
  public ReportDao getReportDao()
  {
    return this.reportDao;
  }
  
  public void setReportDao(ReportDao reportDao)
  {
    this.reportDao = reportDao;
  }
}
