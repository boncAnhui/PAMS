package com.pams.gxgl.rep.zxqk.gs.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.headray.core.spring.jdo.DyDaoHelper;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.services.function.StringToolKit;
import com.pams.gxgl.rep.helper.ZXQKHelper;
import com.ray.nwpn.itsm.report.common.RepHelper;

@Component
@Transactional
public class TabGS_YFBZS_CSFBZS
{
	JdbcTemplate jt;

	public List execute(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate");
		String enddate = obj.getFormatAttr("enddate");
		
		String sql_cdate = RepHelper.compare_sysdate(enddate);
		
		obj.setAttr("sql_cdate", sql_cdate);
		obj.setAttr("ispublish", "Y");
		obj.setAttr("isovertime", "Y");			
		
		System.out.println("--执行情况统计  已发布 超时执行总数");
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select org.internal, org.cname, count(v.cno) num  ").append("\n");
		sql.append("   from t_sys_organ org ").append("\n");
		sql.append("   left join   ").append("\n");
		sql.append("  (   ").append("\n");
		sql.append("  select org.internal, v.cno, sum(v.cs) cs  ").append("\n");
		sql.append("  from t_sys_organ org ").append("\n");
		sql.append("  left join ").append("\n");
		sql.append("  (  ").append("\n");
		
		sql.append(ZXQKHelper.sql_xxgx_zxqk(obj));
		
		sql.append("   ) v  ").append("\n");
		sql.append("   on org.id = v.deptid  ").append("\n");
		sql.append("   group by org.internal, v.cno  ").append("\n");
		sql.append("   having sum(v.cs) > 0  ").append("\n");
		sql.append("   ) v  ").append("\n");
		sql.append("   on org.internal = substr(v.internal, 0, length(v.internal)-4) ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append("   and org.ctype = 'ORG' ").append("\n");
		sql.append("   group by org.internal, org.cname ").append("\n");
		sql.append("   order by internal ").append("\n");

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



