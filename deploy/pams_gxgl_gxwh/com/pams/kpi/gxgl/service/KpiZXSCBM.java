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
public class KpiZXSCBM {
	JdbcTemplate jt;

	public List execute(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate");
		String enddate = obj.getFormatAttr("enddate");

		StringBuffer sql = new StringBuffer();
		sql.append("select xx.dname,sum(sss) zxsccskh from (").append("\n");
		sql.append("  select zx.ownerctx, zx.cname, usr.ownerdept ownerdept, usr.deptname dname, sum(zxsccskh) sss ").append("\n");
		sql.append("  from t_sys_user usr, ").append("\n");
		sql.append("  ( ").append("\n");
		sql.append("    select ownerctx, cname, runflowkey, actdefid, zxsc, zxsccs, case when zxsccs > 5.0 then 5 else zxsccs end zxsccskh ").append("\n");
		sql.append("    from ").append("\n");
		sql.append("    ( ").append("\n");
		sql.append("      select ownerctx, cname, runflowkey, actdefid, zxsc, case when zxsc < 1 then 0 else ceil(zxsc - 1) end zxsccs ").append("\n");
		sql.append("      from ").append("\n");
		sql.append("      ( ").append("\n");
		sql.append("         select ownerctx, cname, runflowkey, actdefid, sum(zxsc) zxsc ").append("\n");
		sql.append("          from  ").append("\n");
		sql.append("         ( ").append("\n");
		sql.append("         select ractowner.ownerctx, usr.cname, ract.runflowkey, ract.flowdefid, ract.actdefid,  ").append("\n");
		// sql.append("         (case when ract.completetime is null then " +
		// RepHelper.to_time(endtime) +
		// " - ract.createtime else ract.completetime - ract.createtime end) zxsc  ").append("\n");
		sql.append("         (case when ract.completetime is null then sysdate - ract.createtime else ract.completetime - ract.createtime end)*24*60 zxsc  ").append("\n");
		sql.append("          from t_sys_wfract ract, t_sys_wfrflow rflow, t_sys_wfbact bact, t_sys_wfractowner ractowner, t_sys_user usr ").append("\n");
		sql.append("         where 1 = 1 ").append("\n");
		if (!StringToolKit.isBlank(begindate))
		{
			sql.append(RepHelper.date_begin_eq("rflow.createtime", begindate)).append("\n");
		}

		if (!StringToolKit.isBlank(enddate))
		{
			sql.append(RepHelper.date_end("rflow.createtime", enddate)).append("\n");
		}		
		
		sql.append("           and rflow.runflowkey = ract.runflowkey ").append("\n");
		sql.append("           and ract.actdefid = bact.id ").append("\n");
		sql.append("           and bact.ctype <> 'BEGIN' ").append("\n");
		sql.append("           and bact.ctype <> 'END' ").append("\n");
		sql.append("           and ract.runactkey = ractowner.runactkey ").append("\n");
		sql.append("           and ractowner.ownerctx = usr.id ").append("\n");


		sql.append("        ").append("\n");
		sql.append("          ) ").append("\n");
		sql.append("         group by ownerctx, cname, runflowkey, actdefid ").append("\n");
		sql.append("      ) ").append("\n");
		sql.append("    ) ").append("\n");
		sql.append("  ) zx ").append("\n");
		sql.append(" where 1 = 1 ");
		sql.append("  and zx.ownerctx = usr.loginname ").append("\n");
		sql.append("  group by zx.ownerctx, zx.cname, usr.ownerdept, usr.deptname  ").append("\n");
		sql.append(" order by ownerdept ").append("\n");
		sql.append(")xx  ").append("\n");
		sql.append(" group by xx.dname");
		
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
