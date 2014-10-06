package com.pams.gxgl.rep.wcqk.ry.service;

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
 * 共享完成情况报表
 * 正常发布总数(人员)
 * @author zhouq
 *
 */
@Component
@Transactional
public class Tab_WCQK_RY_ZCFBZS
{
	JdbcTemplate jt;

	public List execute(DynamicObject obj) throws Exception
	{
		System.out.println("正常发布总数(人员)---->>>>>>>");
		String begindate = obj.getFormatAttr("begindate");
		String enddate = obj.getFormatAttr("enddate");
		String internal = obj.getFormatAttr("internal");
		
		String sql_cdate = RepHelper.compare_sysdate(enddate);		
		String report_type = obj.getFormatAttr("reptype");//报表类型

		obj.setAttr("sql_cdate", sql_cdate);
		obj.setAttr("ispublish", "Y");
		obj.setAttr("isovertime", "N");		
		obj.setAttr("reptype", report_type);
		
		StringBuffer sql = new StringBuffer();


		sql.append(" select usr.loginname, usr.cname, count(v.cno) num ").append("\n");
		sql.append("  from t_sys_user usr ").append("\n");
		sql.append("   left join  ").append("\n");
		sql.append(" ( ").append("\n");
		
		sql.append(ZXQKHelper.sql_xxgx_zxqk1(obj));

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
