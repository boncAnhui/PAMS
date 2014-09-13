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
 * 正常总数
 * 
 */
@Component
@Transactional
public class TabWFBZS_ZCZS
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

		sql.append("select count(v.cno) num ").append("\n");
		sql.append("  from  ").append("\n");
		sql.append(" (  ").append("\n");
		sql.append(" select v.deptid, v.cno, sum(v.zxsc) zxsc ").append("\n");
		sql.append(" from ").append("\n");
		sql.append(" ( ").append("\n");
		
		sql.append(" select bv.deptid, bv.cno, ract.actdefid, case when sum(case when ract.completetime is null then " + sql_cdate + " - ract.createtime else ract.completetime - ract.createtime end) > 1 then 1 else 0 end zxsc ").append("\n");
		sql.append("   from t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_app_infoshare bv ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and rflow.dataid = bv.id ").append("\n");
		sql.append("    and rflow.tableid = 'InfoShare' ").append("\n");
		sql.append("    and rflow.runflowkey = ract.runflowkey ").append("\n");
		sql.append("    and bact.id = ract.actdefid ").append("\n");
		sql.append("    and bact.ctype <> 'BEGIN' ").append("\n");
		sql.append("    and bact.ctype <> 'END' ").append("\n");

		if (!StringToolKit.isBlank(begindate))
		{
			sql.append(RepHelper.date_begin_eq("bv.createtime", begindate)).append("\n");
		}

		if (!StringToolKit.isBlank(enddate))
		{
			sql.append(RepHelper.date_end("bv.createtime", enddate)).append("\n");
		}
		
		sql.append("    and bv.publishtime is null ").append("\n");
		sql.append("  group by bv.deptid, bv.cno, ract.actdefid       ").append("\n");
		sql.append("   union  ").append("\n");
		sql.append("   select info.deptid, info.cno, 'XXHQ' actdefid, case when (info.createtime - info.obtaintime) > 1 then 1 else 0 end zxsc  ").append("\n");
		sql.append("     from t_app_infoshare info ").append("\n");
		sql.append("    where 1 = 1 ").append("\n");
		
		if (!StringToolKit.isBlank(begindate))
		{
			sql.append(RepHelper.date_begin_eq("info.createtime", begindate)).append("\n");
		}

		if (!StringToolKit.isBlank(enddate))
		{
			sql.append(RepHelper.date_end("info.createtime", enddate)).append("\n");
		}
		
		sql.append("     and info.publishtime is null ").append("\n");
		sql.append("  order by deptid, cno, actdefid   ").append("\n");
		sql.append("  ) v ").append("\n");
		sql.append("   ").append("\n");
		sql.append("  group by deptid, cno ").append("\n");
		sql.append("  having sum(v.zxsc) = 0 ").append("\n");
		sql.append("  ) v ").append("\n");

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
