package com.pams.gxgl.rep.zxqk.gzl.service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.headray.core.spring.jdo.DyDaoHelper;
import com.headray.framework.services.db.SQLParser;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.pams.gxgl.rep.helper.ZXQKHelper;
import com.ray.nwpn.itsm.report.common.RepHelper;

@Component
@Transactional
public class TabGZLTJ
{
	JdbcTemplate jt;

	public List execute(DynamicObject obj) throws Exception
	{
		String runflowkey = obj.getFormatAttr("runflowkey");
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select 'XXHQ' id, '信息获取' cname, info.creatername username, info.obtaintime createtime, info.obtaintime applytime, info.createtime completetime, ").append("\n");
		sql.append(" trunc(UF_Calculate_Duration(info.createtime, info.obtaintime), 3) zxsc ").append("\n");
		sql.append(" from t_app_infoshare info, t_sys_wfrflow rflow ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append("   and info.id = rflow.dataid ").append("\n");
		sql.append("   and rflow.tableid = 'InfoShare' ").append("\n");
		sql.append("   and rflow.runflowkey = " + SQLParser.charValue(runflowkey));
		sql.append(" union ").append("\n");
		sql.append(" select bact.id, bact.cname, usr.cname username, ract.createtime, ract.applytime, ract.completetime, ").append("\n");
		sql.append(" case when ract.completetime is null then trunc(UF_Calculate_Duration(sysdate, ract.createtime), 3)  else trunc(UF_Calculate_Duration(ract.completetime, ract.createtime), 3) end zxsc ").append("\n");
		sql.append(" from t_sys_wfrflow rflow, t_sys_wfract ract, t_sys_wfbact bact, t_sys_wfractowner ractowner, t_sys_user usr ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append(" and bact.ctype <> 'BEGIN'").append("\n");
		sql.append(" and bact.ctype <> 'END'").append("\n");
		sql.append(" and rflow.runflowkey = ract.runflowkey ").append("\n");
		sql.append(" and ract.actdefid = bact.id ").append("\n");
		sql.append(" and rflow.runflowkey = " + SQLParser.charValue(runflowkey)).append("\n");
		sql.append(" and ract.runactkey = ractowner.runactkey ").append("\n");
		sql.append(" and ractowner.ownerctx = usr.loginname ").append("\n");
		sql.append(" order by createtime ").append("\n");
		
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
