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
public class KpiWJWHLIST
{
	JdbcTemplate jt;

	public List execute(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate");
		String enddate = obj.getFormatAttr("enddate");
		String deptid = obj.getFormatAttr("deptid");

		StringBuffer sql = new StringBuffer();
		sql.append("select infos.creater creater,infos.creatername creatername,infos.deptid deptid,infos.deptname ,infos.filenums filenums,infos.memo memo,infos.id from T_APP_INFOSHARE infos where 1=1   ");
		

		if (!StringToolKit.isBlank(begindate))
		{
			sql.append(RepHelper.date_begin_eq("infos.createtime", begindate)).append("\n");
		}

		if (!StringToolKit.isBlank(enddate))
		{
			sql.append(RepHelper.date_end("infos.createtime", enddate)).append("\n");
		}		
		sql.append("    order by infos.memo ");

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
