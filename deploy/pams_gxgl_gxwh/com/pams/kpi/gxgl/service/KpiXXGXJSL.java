package com.pams.kpi.gxgl.service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.headray.core.spring.jdo.DyDaoHelper;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.services.function.StringToolKit;
import com.ray.nwpn.itsm.report.common.RepHelper;

@Component
@Transactional
public class KpiXXGXJSL
{
	JdbcTemplate jt;

	// 信息共享及时率
	public List execute(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate");
		String enddate = obj.getFormatAttr("enddate");

		StringBuffer sql = new StringBuffer();
		
		sql.append(" select usr.loginname, usr.cname, usr.ownerdept, usr.deptname, sum(bzsc) bzsc, sum(zxsc) zxsc, sum(cskh)cskh ").append("\n");
		sql.append("   from t_app_infoshare info, t_sys_user usr, ").append("\n");
		sql.append("   (  ").append("\n");
		sql.append("     select runflowkey, cno,  bzsc, zxsc, (case when (zxsc - bzsc) > 5 then 5 else (zxsc - bzsc) end) cskh ").append("\n");
		sql.append("       from  ").append("\n");
		sql.append("       ( ").append("\n");
		sql.append("         select zx.runflowkey, zx.cno, zx.bzsc, ").append("\n");
		sql.append("              ((case when info.publishtime is null then sysdate - info.obtaintime else info.publishtime - info.obtaintime end)*24*60) zxsc ").append("\n");
		sql.append("           from t_app_infoshare info,  ").append("\n");
		sql.append("           ( ").append("\n");
		sql.append("             select runflowkey, flowdefid, cno, sum(bzsc) bzsc ").append("\n");
		sql.append("               from ").append("\n");
		sql.append("               (  ").append("\n");
		sql.append("                 select ract.runflowkey, ract.flowdefid, info.cno, ract.actdefid, sum(kpi.dvalue) bzsc               ").append("\n");
		sql.append("                   from t_sys_wfract ract, t_sys_wfrflow rflow, t_sys_wfbflow bflow, t_sys_wfbact bact, t_app_kpi kpi, t_app_infoshare info  ").append("\n");
		sql.append("                  where 1 = 1   ").append("\n");
		sql.append("                    and rflow.runflowkey = ract.runflowkey  ").append("\n");
		sql.append("                    and ract.actdefid = bact.id   ").append("\n");
		sql.append("                    and bact.ctype <> 'BEGIN'   ").append("\n");
		sql.append("                    and bact.ctype <> 'END'  ").append("\n");
		sql.append("                    and ract.flowdefid = bflow.id  ").append("\n");
		sql.append("                    and bflow.classid = 'GXGL'  ").append("\n");
		sql.append("                    and ract.dataid = info.id ").append("\n");
		sql.append("                    and ract.flowdefid = kpi.flowdefid ").append("\n");
		sql.append("                    and ract.actdefid = kpi.actdefid ").append("\n");
		sql.append("                    and kpi.pname = 'XXGX.ZXSC' ").append("\n");
		if (!StringToolKit.isBlank(begindate))
		{
			sql.append(RepHelper.date_begin_eq("rflow.createtime", begindate)).append("\n");
		}

		if (!StringToolKit.isBlank(enddate))
		{
			sql.append(RepHelper.date_end("rflow.createtime", enddate)).append("\n");
		}	
		
		sql.append("                  group by ract.runflowkey, info.cno, ract.flowdefid, ract.actdefid  ").append("\n");
		sql.append("                  order by runflowkey ").append("\n");
		sql.append("               ) zx  ").append("\n");
		sql.append("             where 1 = 1  ").append("\n");
		sql.append("             group by runflowkey, flowdefid, cno ").append("\n");
		sql.append("           ) zx ").append("\n");
		sql.append("           where 1 = 1 ").append("\n");
		sql.append("             and info.cno = zx.cno    ").append("\n");
		sql.append("       ) zx     ").append("\n");
		sql.append("   ) zx  ").append("\n");
		sql.append("   where 1 = 1  ").append("\n");
		sql.append("     and zx.cno = info.cno  ").append("\n");
		sql.append("     and info.creater = usr.loginname ");
		sql.append("   group by usr.loginname, usr.cname, usr.ownerdept, usr.deptname ").append("\n");
		sql.append("   order by usr.ownerdept, usr.deptname");

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
