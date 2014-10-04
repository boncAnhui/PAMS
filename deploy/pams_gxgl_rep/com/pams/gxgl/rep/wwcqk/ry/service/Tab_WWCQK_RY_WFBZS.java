package com.pams.gxgl.rep.wwcqk.ry.service;

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

/**
 * 共享未完成情况报表
 * 未发布总数(人员)
 * @author zhouq
 *
 */
@Component
@Transactional
public class Tab_WWCQK_RY_WFBZS
{
	JdbcTemplate jt;

	public List execute(DynamicObject obj) throws Exception
	{
		System.out.println("未发布总数(人员)---------->");
		String begindate = obj.getFormatAttr("begindate");
		String enddate = obj.getFormatAttr("enddate");
		String internal = obj.getFormatAttr("internal");
		String sql_cdate = RepHelper.compare_sysdate(enddate);		
		String report_type = obj.getFormatAttr("reptype");//报表类型	

		obj.setAttr("sql_cdate", sql_cdate);
		obj.setAttr("ispublish", "N");
		obj.setAttr("isnodeovertime", "");		
		obj.setAttr("isovertime", "");		
		obj.setAttr("reptype",report_type);	
		
		StringBuffer sql = new StringBuffer();

		sql.append(" select usr.loginname, usr.cname, count(v.cno) num ").append("\n");
		sql.append("  from t_sys_user usr ").append("\n");
		sql.append("   left join  ").append("\n");
		sql.append(" ( ").append("\n");
		sql.append("   select bv.cno,bv.creater, bv.creatername, bv.deptid ").append("\n");
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
		
		sql.append("     and bv.publishtime is null ").append("\n");
		
		sql.append(" ) v   ").append("\n");
		sql.append("  on usr.loginname = v.creater ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		
		if (!StringToolKit.isBlank(internal))
		{
			sql.append(" and usr.ownerdept = " + SQLParser.charValue(internal)).append("\n");
		}
		
		sql.append(" group by loginname, cname ").append("\n");
		sql.append(" order by loginname   ").append("\n");
		
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
