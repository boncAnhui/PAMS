package com.pams.gxgl.rep.zxqk.ry.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.core.spring.jdo.DyDaoHelper;
import com.headray.framework.services.db.SQLParser;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.services.function.StringToolKit;
import com.ray.nwpn.itsm.report.common.RepHelper;

@Component
@Transactional
public class TabRY_YFBZS_WJZS
{
	JdbcTemplate jt;

	public List execute(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate");
		String enddate = obj.getFormatAttr("enddate");
		String internal = obj.getFormatAttr("internal");
		String orginternal = obj.getFormatAttr("orginternal");
		String report_type = Struts2Utils.getRequest().getParameter("reptype");	
		
		String sql_cdate = " sysdate ";
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    if(df.parse(enddate).getTime()<System.currentTimeMillis())
    	{
	    	sql_cdate = RepHelper.to_time_end(enddate);
    	}
		
		StringBuffer sql = new StringBuffer();
		

		sql.append(" select usr.loginname, usr.cname, case when sum(v.filenums) is null then 0 else sum(v.filenums) end num  ").append("\n");
		sql.append("  from t_sys_user usr ").append("\n");
		sql.append("   left join  ").append("\n");
		sql.append(" ( ").append("\n");
		sql.append("   select bv.cno, bv.creater, bv.creatername, bv.deptid, case when bv.filenums is null then 0 else filenums end filenums ").append("\n");
		sql.append("    from t_app_pubinfo bv ").append("\n");
		sql.append("   where 1 = 1 ").append("\n");
		sql.append("    and bv.reptype='" + report_type + "' ").append("\n");
		
		if (!StringToolKit.isBlank(begindate))
		{
			sql.append(RepHelper.date_begin_eq("bv.createtime", begindate)).append("\n");
		}

		if (!StringToolKit.isBlank(enddate))
		{
			sql.append(RepHelper.date_end("bv.createtime", enddate)).append("\n");
		}
		sql.append("     and bv.publishtime is not null ").append("\n");
		if (!StringToolKit.isBlank(enddate))
		{
			sql.append(RepHelper.date_end("bv.publishtime", enddate)).append("\n");
		}
		sql.append("      ").append("\n");
		sql.append(" ) v   ").append("\n");
		sql.append("  on usr.loginname = v.creater ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		
		if (!StringToolKit.isBlank(internal))
		{
			sql.append(" and substr(usr.ownerdept, 0, " + internal.length() + ") = " + SQLParser.charValue(internal)).append("\n");
		}
		
		if (!StringToolKit.isBlank(orginternal))
		{
			sql.append(" and usr.ownerorg = " + SQLParser.charValue(orginternal)).append("\n");
		}		
		
		sql.append(" group by loginname, cname ").append("\n");
		sql.append(" order by loginname ").append("\n");
		
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



