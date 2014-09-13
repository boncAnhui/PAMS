package com.pams.gxgl.rep.zxqk.bm.service;

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
public class TabBM_WFBZS_CSZS
{
	JdbcTemplate jt;

	public List execute(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate");
		String enddate = obj.getFormatAttr("enddate");
		String sql_cdate = RepHelper.compare_sysdate(enddate);		
		
		obj.setAttr("sql_cdate", sql_cdate);
		obj.setAttr("ispublish", "N");
		obj.setAttr("isovertime", "Y");		

		StringBuffer sql = new StringBuffer();

		sql.append(" select org.internal, org.cname, count(v.cno) num ").append("\n");
		sql.append("  from t_sys_organ org ").append("\n");
		sql.append("   left join  ").append("\n");
		sql.append(" ( ").append("\n");

		sql.append(ZXQKHelper.sql_xxgx_zxqk(obj));

		sql.append("  ) v ").append("\n");
		sql.append("  on org.id = v.deptid ").append("\n");
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
