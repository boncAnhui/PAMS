package com.pams.gxgl.kpi.jsl.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.headray.core.spring.jdo.DyDaoHelper;
import com.headray.framework.services.db.SQLParser;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.services.function.StringToolKit;
import com.pams.gxgl.rep.helper.ZXQKHelper;
import com.ray.nwpn.itsm.report.common.RepHelper;

@Component
@Transactional
public class TabJSL_BM
{
	JdbcTemplate jt;

	public List execute(DynamicObject obj) throws Exception
	{
		System.out.println("信息共享及时率(部门)");
		
		String begindate = obj.getFormatAttr("begindate");
		String enddate = obj.getFormatAttr("enddate");
		String internal = obj.getFormatAttr("internal");
		
		String sql_cdate = RepHelper.compare_sysdate(enddate);
		
		obj.setAttr("sql_cdate", sql_cdate);
		obj.setAttr("ispublish", "Y");
		obj.setAttr("isovertime", "Y");	
		obj.setAttr("isnodeovertime", "");		
	    
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select org.internal, org.cname orgcname, case when sum(zxsccskh) is null then 0 else sum(zxsccskh) end zxsccskh ").append("\n");
		sql.append("   from t_sys_organ org ").append("\n");
		sql.append("   left join   ").append("\n");
		sql.append("  (   ").append("\n");
		
		sql.append("  select org.internal, sum(zxsckh) zxsccskh ").append("\n");
		sql.append("  from t_sys_organ org,  ").append("\n");
		sql.append("  ( ").append("\n");
		
		sql.append("    select v.deptid, v.creater, v.cno,case when ceil(sum(zxsc)-count(v.actdefid)) < 5 then  ceil(sum(zxsc)-count(v.actdefid)) else 5 end zxsckh  ").append("\n");
		sql.append("      from ").append("\n");
		sql.append("      ( ").append("\n");
		
		sql.append(ZXQKHelper.sql_xxgx_kpi_zxsc(obj));
		
		sql.append("      ) v ").append("\n");
		sql.append(" group by v.deptid, v.creater, v.cno");
		
		sql.append("  ) v ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append("   and org.id = v.deptid ").append("\n");
		sql.append("   and zxsckh > 0  ").append("\n");
		sql.append(" group by org.internal ").append("\n");
		
		sql.append("  ) v ").append("\n");		
		sql.append(" on substr(v.internal, 0, length(org.internal)) = org.internal ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append("   and org.ctype = 'DEPT' ").append("\n");
		if(!StringToolKit.isBlank(internal))
		{
			sql.append(" and substr(org.internal, 0, length(org.internal) - 4) = " + SQLParser.charValue(internal)).append("\n");
		}
		sql.append("   group by org.internal, org.cname ").append("\n");
		sql.append(" order by internal ").append("\n");

		List datas = DyDaoHelper.query(jt, sql.toString());

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
