package com.pams.gxgl.kpi.zxsc.service;

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
public class TabZXSC_RY
{
	JdbcTemplate jt;

	public List execute(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate");
		String enddate = obj.getFormatAttr("enddate");
		String internal = obj.getFormatAttr("internal");
		String reptype = obj.getFormatAttr("reptype");
		
		String sql_cdate = RepHelper.compare_sysdate(enddate);
		
		obj.setAttr("sql_cdate", sql_cdate);
		obj.setAttr("ispublish", "");
		obj.setAttr("isovertime", "");			
		obj.setAttr("reptype", reptype);	
	    
		StringBuffer sql = new StringBuffer();
		
		sql.append("  select usr.loginname, usr.cname username, usr.ordernum, usr.owneorgname orgcname, usr.ownerdept, usr.deptname deptcname, case when sum(zxsccskh) is null then 0 else sum(zxsccskh) end zxsccskh ").append("\n");
		sql.append("  from t_sys_user usr ").append("\n");
		sql.append("  left join ").append("\n");
		sql.append("  ( ").append("\n");
		
		sql.append("  select v.deptid, v.creater, v.cno, v.actdefid, case when zxsccs > 5 then 5 else zxsccs end zxsccskh ").append("\n");
		sql.append("    from").append("\n");
		sql.append("    ( ").append("\n");
		sql.append("    select v.deptid, v.creater, v.cno, v.actdefid, case when zxsc < 1 then 0 else ceil(zxsc - 1) end zxsccs ").append("\n");
		sql.append("      from ").append("\n");
		sql.append("      ( ").append("\n");
		
		sql.append(ZXQKHelper.sql_xxgx_kpi_zxsc1(obj));
		
		sql.append("      ) v ").append("\n");
		sql.append("    ) v ").append("\n");
		sql.append("  ) v ").append("\n");
		sql.append("  on usr.loginname = v.creater ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		if(!StringToolKit.isBlank(internal))
		{
			sql.append(" and usr.ownerdept = " + SQLParser.charValue(internal)).append("\n");
		}		
		
		sql.append(" group by usr.loginname, usr.cname, usr.ordernum, usr.owneorgname, usr.ownerdept, usr.deptname ").append("\n");
		sql.append(" order by usr.ownerdept, usr.ordernum, usr.cname ").append("\n");

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
