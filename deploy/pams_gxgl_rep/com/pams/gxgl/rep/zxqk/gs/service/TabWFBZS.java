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
import com.ray.nwpn.itsm.report.common.RepHelper;

/**
 * 未发布总数
 * @author Administrator
 *
 */
@Component
@Transactional
public class TabWFBZS
{
	JdbcTemplate jt;

	public List execute(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate");
		String enddate = obj.getFormatAttr("enddate");
		
		String sql_cdate = " sysdate ";
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    if(df.parse(enddate).getTime()<System.currentTimeMillis())
    	{
	    	sql_cdate = RepHelper.to_time_end(enddate);
    	}
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select sum(v.yfbzs) yfbzs ").append("\n");
		sql.append(" from ").append("\n");
		sql.append(" ( ").append("\n");

		sql.append(" select org.internal, org.cname, count(v.cno) yfbzs ").append("\n");
		sql.append("  from t_sys_organ org ").append("\n");
		sql.append("   left join  ").append("\n");
		sql.append(" ( ").append("\n");
		sql.append("   select bv.cno, bv.title, bv.creater, bv.creatername, bv.deptid ").append("\n");
		sql.append("    from t_app_infoshare bv ").append("\n");
		sql.append("   where 1 = 1 ").append("\n");
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
		sql.append(" group by internal, cname ").append("\n");
		sql.append(" ) v ").append("\n");
		sql.append("  ").append("\n");
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



