package com.pams.gxgl.rep.wwcqk.bm.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.core.spring.jdo.DyDaoHelper;
import com.headray.framework.services.db.SQLParser;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.services.function.StringToolKit;
import com.pams.gxgl.rep.helper.ZXQKHelper;
import com.ray.nwpn.itsm.report.common.RepHelper;

/**
 * 共享未完成情况报表
 * 超时执行总数(部门)
 * @author Administrator
 *
 */
@Component
@Transactional
public class Tab_WWCQK_BM_CSZXZS
{
	JdbcTemplate jt;

	public List execute(DynamicObject obj) throws Exception
	{
		System.out.println("超时执行总数(部门)--->>>>>>>>");
		String begindate = obj.getFormatAttr("begindate");
		String enddate = obj.getFormatAttr("enddate");
		String internal = Struts2Utils.getRequest().getParameter("internal");
		String sql_cdate = RepHelper.compare_sysdate(enddate);		

		obj.setAttr("sql_cdate", sql_cdate);
		obj.setAttr("ispublish", "N");
		obj.setAttr("isovertime", "");
		obj.setAttr("isnodeovertime", "");
		
		StringBuffer sql = new StringBuffer();

		sql.append(" select org.internal, org.cname, (case when count(v.cno) is null then 0 else count(v.cno) end) num ").append("\n");
		sql.append("  from t_sys_organ org ").append("\n");
		sql.append("   left join  ").append("\n");
		sql.append(" ( ").append("\n");
		
		sql.append(ZXQKHelper.sql_xxgx_zxqk(obj));

		sql.append(" ) v   ").append("\n");
		sql.append("  on org.id = v.deptid ").append("\n");
		sql.append(" and  v.cs>0   ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append(" and org.ctype = 'DEPT' ").append("\n");
		if (!StringToolKit.isBlank(internal))
		{
			sql.append(" and substr(org.internal, 0, length(org.internal) - 4) = " + SQLParser.charValue(internal)).append("\n");
		}
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
