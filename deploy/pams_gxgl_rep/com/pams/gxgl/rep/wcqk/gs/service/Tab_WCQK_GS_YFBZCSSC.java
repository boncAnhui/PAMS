package com.pams.gxgl.rep.wcqk.gs.service;

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

/**
 * 共享完成情况报表
 * 已发布总超时时长
 * @author Administrator
 *
 */
@Component
@Transactional
public class Tab_WCQK_GS_YFBZCSSC
{
	JdbcTemplate jt;

	public List execute(DynamicObject obj) throws Exception
	{
		System.out.println("已发布总超时时长(公司)------->>>");
		String begindate = obj.getFormatAttr("begindate");
		String enddate = obj.getFormatAttr("enddate");
		String sql_cdate = RepHelper.compare_sysdate(enddate);		
		String report_type = obj.getFormatAttr("reptype");//报表类型

		obj.setAttr("sql_cdate", sql_cdate);
		obj.setAttr("ispublish", "Y");
		obj.setAttr("isnodeovertime", "Y");		
		obj.setAttr("isovertime", "");		
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select org.internal, org.cname, round((case when sum(v.jhsc) is null then 0 else sum(v.jhsc) end),2) jhsc ,round((case when sum(sjsc) is null then 0 else sum(sjsc) end ),2) sjsc ").append("\n");
		sql.append("   from t_sys_organ org ").append("\n");
		sql.append("   left join   ").append("\n");
		sql.append("  (   ").append("\n");
		sql.append("  select org.internal, v.cno, sum(v.cs) cs , sum(v.jhsc) jhsc, sum(v.sjsc) sjsc ").append("\n");
		sql.append("  from t_sys_organ org ").append("\n");
		sql.append("  left join ").append("\n");
		sql.append("  (  ").append("\n");
		
		sql.append("select  info.cno,info.deptid,sum(bv.jhsc) jhsc, round(sum(bv.jhsc - (info.publishtime - info.obtaintime)),2) cs,round(sum(info.publishtime - info.obtaintime),2) sjsc").append("\n");
		sql.append(" from t_app_infoshare info, ").append("\n");
		sql.append("( ").append("\n");
		sql.append(" select bv.cno, (count(0) + 1) jhsc").append("\n");
		sql.append(" from").append("\n");
		sql.append(" (").append("\n");
		sql.append("select bv.cno, ract.actdefid").append("\n");
		
		sql.append("from t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_app_pubinfo bv").append("\n");
		sql.append(" where 1 = 1").append("\n");
		sql.append("   and rflow.dataid = bv.id").append("\n");
		sql.append("   and rflow.tableid = 'InfoShare'").append("\n");
		sql.append("   and rflow.runflowkey = ract.runflowkey").append("\n");
		sql.append("   and bact.id = ract.actdefid").append("\n");
		sql.append("   and bact.ctype <> 'BEGIN'").append("\n");	
		
		sql.append("   and bact.ctype <> 'END'").append("\n");
		sql.append("   and rflow.state = '结束'").append("\n");
		
		sql.append("    and bv.reptype='" + report_type + "' ").append("\n");
		
		if (!StringToolKit.isBlank(begindate))
		{
			sql.append(RepHelper.date_begin_eq("bv.createtime", begindate)).append("\n");
		}

		if (!StringToolKit.isBlank(enddate))
		{
			sql.append(RepHelper.date_end("bv.createtime", enddate)).append("\n");
		}
		
		sql.append("    and bv.publishtime is not null ").append("\n");			
		if (!StringToolKit.isBlank(enddate))
		{
			sql.append(RepHelper.date_end("bv.publishtime", enddate)).append("\n");
		}
		
		sql.append(" group by bv.cno, ract.actdefid").append("\n");
		sql.append("    ) bv").append("\n");
		sql.append("group by cno").append("\n");
		sql.append(") bv ").append("\n");
		sql.append(" where 1 = 1").append("\n");		
		sql.append("   and info.cno = bv.cno").append("\n");
		sql.append("group by info.cno,info.deptid").append("\n");

		sql.append("   ) v  ").append("\n");
		sql.append("   on org.id = v.deptid  ").append("\n");
		sql.append("   group by org.internal, v.cno  ").append("\n");
		sql.append("   having sum(v.cs) = 0  ").append("\n");
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
