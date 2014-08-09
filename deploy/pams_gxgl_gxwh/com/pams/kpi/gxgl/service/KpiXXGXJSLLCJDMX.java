package com.pams.kpi.gxgl.service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.headray.core.spring.jdo.DyDaoHelper;
import com.headray.framework.services.db.SQLParser;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.services.function.StringToolKit;
import com.ray.nwpn.itsm.report.common.RepHelper;

@Component
@Transactional
public class KpiXXGXJSLLCJDMX
{
	JdbcTemplate jt;

	// 信息共享及时率流程节点明细
	public List execute(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate");
		String enddate = obj.getFormatAttr("enddate");
		String loginname = obj.getFormatAttr("loginname");
		String cno = obj.getFormatAttr("cno");

		StringBuffer sql = new StringBuffer();
		
		sql.append(" select ract.runflowkey, ract.flowdefid, info.cno, ract.actdefid, bact.cname actname, sum(kpi.dvalue) bzsc, ").append("\n");
		sql.append(" sum((case when ract.completetime is null then sysdate - ract.createtime else ract.completetime - ract.createtime end)*24*60) zxsc  ").append("\n");		
		
		sql.append("   from t_sys_wfract ract, t_sys_wfrflow rflow, t_sys_wfbflow bflow, t_sys_wfbact bact, t_app_kpi kpi, t_app_infoshare info ").append("\n");
		sql.append("   where 1 = 1    ").append("\n");
		sql.append("   and rflow.runflowkey = ract.runflowkey   ").append("\n");
		sql.append("   and ract.actdefid = bact.id    ").append("\n");
		sql.append("   and bact.ctype <> 'BEGIN'    ").append("\n");
		sql.append("   and bact.ctype <> 'END'   ").append("\n");
		sql.append("   and ract.flowdefid = bflow.id   ").append("\n");
		sql.append("   and bflow.classid = 'GXGL'   ").append("\n");
		sql.append("   and ract.dataid = info.id  ").append("\n");
		sql.append("   and ract.flowdefid = kpi.flowdefid  ").append("\n");
		sql.append("   and ract.actdefid = kpi.actdefid  ").append("\n");
		sql.append("   and kpi.pname = 'XXGX.ZXSC'  ").append("\n");

		if (!StringToolKit.isBlank(cno))
		{
			sql.append("and info.cno = " + SQLParser.charValue(cno)).append("\n");
		}

		sql.append("   group by ract.runflowkey, ract.flowdefid, info.cno, ract.actdefid, bact.cname  ").append("\n");
		sql.append("   order by cno  ").append("\n");

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
