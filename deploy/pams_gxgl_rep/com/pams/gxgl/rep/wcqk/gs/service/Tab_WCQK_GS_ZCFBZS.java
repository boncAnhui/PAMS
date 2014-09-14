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
 * 正常发布总数
 * @author zhouq
 *
 */
@Component
@Transactional
public class Tab_WCQK_GS_ZCFBZS
{
	JdbcTemplate jt;

	public List execute(DynamicObject obj) throws Exception
	{
		System.out.println("正常发布总数(公司)---------->>>>>>>>");
		String begindate = obj.getFormatAttr("begindate");
		String enddate = obj.getFormatAttr("enddate");
		String sql_cdate = RepHelper.compare_sysdate(enddate);		

		obj.setAttr("sql_cdate", sql_cdate);
		obj.setAttr("ispublish", "Y");
		obj.setAttr("isovertime", "N");		
		
		StringBuffer sql = new StringBuffer();

		sql.append(" select (case when sum(v.jds) is null then 0 else sum(v.jds) end) num,sum(v.cs) zxsc from  ").append("\n");
		sql.append(" ( ").append("\n");
		
		sql.append(ZXQKHelper.sql_xxgx_zxqk(obj));

		sql.append(" ) v   ").append("\n");
		
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
