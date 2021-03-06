package com.pams.gxgl.rep.zxqk.bm.service;

import java.sql.Timestamp;
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
public class TabBM_WFBZS
{
	JdbcTemplate jt;

	public List execute(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate");
		String enddate = obj.getFormatAttr("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		String report_type = Struts2Utils.getRequest().getParameter("reptype");//报表类型
		
		String sql_cdate = RepHelper.compare_sysdate(enddate);
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select org.internal, org.cname, count(v.cno) num ").append("\n");
		sql.append("  from t_sys_organ org ").append("\n");
		sql.append("   left join  ").append("\n");
		sql.append(" ( ").append("\n");
		sql.append("   select bv.cno, bv.creater, bv.creatername, bv.deptid ").append("\n");
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
		sql.append("  on org.id = v.deptid ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append(" and org.ctype = 'DEPT' ").append("\n");
		if (!StringToolKit.isBlank(internal))
		{
			sql.append(" and substr(org.internal, 0, length(org.internal) - 4) = " + SQLParser.charValue(internal)).append("\n");
		}
		sql.append(" group by internal, cname ").append("\n");
		sql.append(" order by internal   ").append("\n");


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



