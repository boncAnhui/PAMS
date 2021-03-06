 package com.pams.kpi.gxgl.service;
 
 import com.headray.core.spring.jdo.DyDaoHelper;
 import com.headray.framework.services.db.dybeans.DynamicObject;
 import com.headray.framework.services.function.StringToolKit;
 import com.ray.nwpn.itsm.report.common.RepHelper;
 import java.util.List;
 import org.springframework.jdbc.core.JdbcTemplate;
 import org.springframework.stereotype.Component;
 import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional
 public class KpiWJWHSL
 {
   JdbcTemplate jt;
   
   public KpiWJWHSL() {}
   
   public List execute(DynamicObject obj) throws Exception
   {
     String begindate = obj.getFormatAttr("begindate");
     String enddate = obj.getFormatAttr("enddate");
     String deptid = obj.getFormatAttr("deptid");
     
     StringBuffer sql = new StringBuffer();
     sql.append("select creater,createname,count(filenums) nums, deptname,deptid from ( ");
     sql.append("select infos.creater creater,infos.creatername createname,infos.deptid deptid,infos.deptname ,infos.filenums,infos.memo,infos.id from T_APP_INFOSHARE infos where 1=1 ").append("\n");
     
     if ((deptid != null) && (!"".equals(deptid)))
     {
       sql.append(" and ");
       sql.append(" deptid='" + deptid + "'");
     }
     
     if (!StringToolKit.isBlank(begindate))
     {
       sql.append(RepHelper.date_begin_eq("infos.createtime", begindate)).append("\n");
     }
     
     if (!StringToolKit.isBlank(enddate))
     {
       sql.append(RepHelper.date_end("infos.createtime", enddate)).append("\n");
     }
     sql.append(" )xx ");
     sql.append("   group by creater,createname,deptname,deptid ");
     sql.append("  order by creater  ");
     
     List datas = DyDaoHelper.query(this.jt, sql.toString());
     
     return datas;
   }
   
   public void setJdbcTemplate(JdbcTemplate jt)
   {
     this.jt = jt;
   }
   
   public JdbcTemplate getJdbcTemplate()
   {
     return this.jt;
   }
 }
