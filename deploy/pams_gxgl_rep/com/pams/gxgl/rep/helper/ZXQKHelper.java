package com.pams.gxgl.rep.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.headray.framework.services.db.SQLParser;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.services.function.StringToolKit;
import com.ray.nwpn.itsm.report.common.RepHelper;

public class ZXQKHelper
{
	// 执行情况通用查询方法
	public static String sql_xxgx_zxqk(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate"); // 结束日期
		String enddate = obj.getFormatAttr("enddate"); // 起始日期
		String sql_cdate = obj.getFormatAttr("sql_cdate"); // 系统与查询比较时间
		
		String ispublish = obj.getFormatAttr("ispublish"); // 发布状态开关
		String isovertime = obj.getFormatAttr("isovertime"); // 超时状态开关
		String isnodeovertime = obj.getFormatAttr("isnodeovertime"); // 节点超时状态开关
		String creater = obj.getFormatAttr("creater"); // 创建人
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select v.deptid, v.creater, v.cno, count(v.actdefid) jds, sum(v.cs) cs  ").append("\n");
		sql.append("   from ").append("\n");
		sql.append(" ( ").append("\n");
		
		sql.append(" select info.deptid, info.creater, info.cno, ract.actdefid, ").append("\n");
		if("Y".equals(ispublish))
		{
			// sql.append(" case when sum(ract.completetime - ract.createtime) > 1 then 1 else 0 end cs ").append("\n");
			sql.append(" case when sum(UF_Calculate_Duration(ract.completetime, ract.createtime)) > 1 then 1 else 0 end cs ").append("\n");
			
		}
		else
		if("N".equals(ispublish))
		{
			// sql.append(" case when sum(case when ract.completetime is null then " + sql_cdate + " - ract.createtime else ract.completetime - ract.createtime end) > 1 then 1 else 0 end cs ").append("\n");
			sql.append(" case when sum(case when ract.completetime is null then UF_Calculate_Duration(" + sql_cdate + ", ract.createtime) else UF_Calculate_Duration(ract.completetime, ract.createtime) end) > 1 then 1 else 0 end cs ").append("\n");
		}
		else
		{
			// sql.append(" case when sum(case when ract.completetime is null then " + sql_cdate + " - ract.createtime else ract.completetime - ract.createtime end) > 1 then 1 else 0 end cs ").append("\n");
			sql.append(" case when sum(case when ract.completetime is null then UF_Calculate_Duration(" + sql_cdate + ", ract.createtime) else UF_Calculate_Duration(ract.completetime, ract.createtime) end) > 1 then 1 else 0 end cs ").append("\n");
		}
		
		sql.append("   from t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_app_infoshare info ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and rflow.dataid = info.id ").append("\n");
		sql.append("    and rflow.tableid = 'InfoShare' ").append("\n");
		sql.append("    and rflow.runflowkey = ract.runflowkey ").append("\n");
		sql.append("    and bact.id = ract.actdefid ").append("\n");
		sql.append("    and bact.ctype <> 'BEGIN' ").append("\n");
		sql.append("    and bact.ctype <> 'END' ").append("\n");
		
		if (!StringToolKit.isBlank(begindate))
		{
			sql.append(RepHelper.date_begin_eq("info.createtime", begindate)).append("\n");
		}

		if (!StringToolKit.isBlank(enddate))
		{
			sql.append(RepHelper.date_end("info.createtime", enddate)).append("\n");
		}
		
		if("Y".equals(ispublish))
		{
			sql.append("    and rflow.state = '结束' ").append("\n");
			sql.append("    and info.publishtime is not null ").append("\n");			
			if (!StringToolKit.isBlank(enddate))
			{
				sql.append(RepHelper.date_end("info.publishtime", enddate)).append("\n");
			}
		}
		else
		if("N".equals(ispublish))
		{
			sql.append("    and info.publishtime is null ").append("\n");
		}

		if (!StringToolKit.isBlank(creater))
		{
			sql.append("    and info.creater = " + SQLParser.charValue(creater)).append("\n");
		}

		sql.append("  group by info.deptid, info.creater, info.cno, ract.actdefid ").append("\n");
		
		if("Y".equals(isnodeovertime))
		{
			// sql.append(" having sum(ract.completetime - ract.createtime) > 1 ").append("\n");
			sql.append(" having sum(UF_Calculate_Duration(ract.completetime, ract.createtime)) > 1 ").append("\n");
		}
		else 
		if("N".equals(isnodeovertime))
		{
			sql.append(" having sum(UF_Calculate_Duration(ract.completetime, ract.createtime)) < 1 ").append("\n");
		}		
		
		sql.append("   union  ").append("\n");
		sql.append("   select info.deptid, info.creater, info.cno, 'XXHQ' actdefid, case when (UF_Calculate_Duration(info.createtime, info.obtaintime)) > 1 then 1 else 0 end cs ").append("\n");
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
		
		if("Y".equals(ispublish))
		{
			sql.append("    and info.publishtime is not null ").append("\n");			
			if (!StringToolKit.isBlank(enddate))
			{
				sql.append(RepHelper.date_end("info.publishtime", enddate)).append("\n");
			}
		}
		else
		if("N".equals(ispublish))
		{
			sql.append("     and info.publishtime is null ").append("\n");
		}

		if (!StringToolKit.isBlank(creater))
		{
			sql.append("    and info.creater = " + SQLParser.charValue(creater)).append("\n");
		}		
		
		if("Y".equals(isnodeovertime))
		{
			sql.append(" and (UF_Calculate_Duration(info.createtime, info.obtaintime)) > 1 ").append("\n");
		}
		else 
		if("N".equals(isnodeovertime))
		{
			sql.append(" and (UF_Calculate_Duration(info.createtime, info.obtaintime)) < 1 ").append("\n");
		}	
		
		
		sql.append(" ) v ").append("\n");
		sql.append(" group by deptid, creater, cno").append("\n");
		
		if("Y".equals(isovertime))
		{
			sql.append(" having sum(v.cs) > 0 ").append("\n");
		}
		else
		if("N".equals(isovertime))
		{
			sql.append(" having sum(v.cs) = 0 ").append("\n");
		}
		
		// sql.append("  order by deptid, creater, cno, actdefid  ").append("\n");
		
		return sql.toString();
	}
	
	public static String sql_xxgx_zxqk_zxsc(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate"); // 结束日期
		String enddate = obj.getFormatAttr("enddate"); // 起始日期
		String sql_cdate = obj.getFormatAttr("sql_cdate"); // 系统与查询比较时间
		
		String ispublish = obj.getFormatAttr("ispublish"); // 发布状态开关
		String isovertime = obj.getFormatAttr("isovertime"); // 超时状态开关
		String isnodeovertime = obj.getFormatAttr("isnodeovertime"); // 节点超时状态开关
		String creater = obj.getFormatAttr("creater"); // 创建人

		StringBuffer sql = new StringBuffer();
		
		sql.append(" select info.creater, info.creatername, rflow.runflowkey, v.cno, ract.actdefid, bact.cname actcname, ").append("\n");
		sql.append(" sum(case when ract.completetime is null then UF_Calculate_Duration(" + sql_cdate + ", ract.createtime) else UF_Calculate_Duration(ract.completetime, ract.createtime) end) zxsc ").append("\n");
		sql.append(" from t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_app_infoshare info, ").append("\n");
		sql.append(" ( ").append("\n");
		sql.append(ZXQKHelper.sql_xxgx_zxqk(obj));
		sql.append(" ) v   ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and v.cno = info.cno ").append("\n");
		sql.append("    and rflow.dataid = info.id ").append("\n");
		sql.append("    and rflow.tableid = 'InfoShare' ").append("\n");
		sql.append("    and rflow.runflowkey = ract.runflowkey ").append("\n");
		sql.append("    and bact.id = ract.actdefid ").append("\n");
		sql.append("    and bact.ctype <> 'BEGIN' ").append("\n");
		sql.append("    and bact.ctype <> 'END' ").append("\n");		
		sql.append("  group by info.creater, info.creatername, rflow.runflowkey, v.cno, ract.actdefid, bact.cname ").append("\n");
		sql.append(" union ").append("\n");
		sql.append(" select info.creater, info.creatername, rflow.runflowkey, v.cno, 'XXHQ' actdefid, '信息获取' actcname, (UF_Calculate_Duration(info.createtime, info.obtaintime)) zxsc ").append("\n");
		sql.append(" from t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_app_infoshare info, ").append("\n");
		sql.append(" ( ").append("\n");
		sql.append(ZXQKHelper.sql_xxgx_zxqk(obj));
		sql.append(" ) v   ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and v.cno = info.cno ").append("\n");
		sql.append("    and rflow.dataid = info.id ").append("\n");
		sql.append("    and rflow.tableid = 'InfoShare' ").append("\n");
		sql.append("    and rflow.runflowkey = ract.runflowkey ").append("\n");
		sql.append("    and bact.id = ract.actdefid ").append("\n");
		sql.append("    and bact.ctype <> 'BEGIN' ").append("\n");
		sql.append("    and bact.ctype <> 'END' ").append("\n");	
		
		sql.append("  order by cno desc ").append("\n");
		
		return sql.toString();
	}
	
	
	public static String sql_xxgx_kpi_zxsc(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate"); // 结束日期
		String enddate = obj.getFormatAttr("enddate"); // 起始日期
		String sql_cdate = obj.getFormatAttr("sql_cdate"); // 系统与查询比较时间
		
		String ispublish = obj.getFormatAttr("ispublish"); // 发布状态开关
		String isovertime = obj.getFormatAttr("isovertime"); // 超时状态开关
		String isnodeovertime = obj.getFormatAttr("isnodeovertime"); // 节点超时状态开关
		String creater = obj.getFormatAttr("creater"); // 创建人
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select info.deptid, info.creater, info.cno, ract.actdefid, ").append("\n");
		if("Y".equals(ispublish))
		{
			sql.append(" sum(UF_Calculate_Duration(ract.completetime, ract.createtime)) zxsc ").append("\n");
		}
		else
		if("N".equals(ispublish))
		{
			sql.append(" sum(case when ract.completetime is null then UF_Calculate_Duration(" + sql_cdate + ", ract.createtime) else UF_Calculate_Duration(ract.completetime, ract.createtime) end) zxsc ").append("\n");
		}
		else
		{
			sql.append(" sum(case when ract.completetime is null then UF_Calculate_Duration(" + sql_cdate + ", ract.createtime) else UF_Calculate_Duration(ract.completetime, ract.createtime) end) zxsc ").append("\n");
		}
		
		sql.append("   from t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_app_infoshare info ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and rflow.dataid = info.id ").append("\n");
		sql.append("    and rflow.tableid = 'InfoShare' ").append("\n");
		sql.append("    and rflow.runflowkey = ract.runflowkey ").append("\n");
		sql.append("    and bact.id = ract.actdefid ").append("\n");
		sql.append("    and bact.ctype <> 'BEGIN' ").append("\n");
		sql.append("    and bact.ctype <> 'END' ").append("\n");
		
		if (!StringToolKit.isBlank(begindate))
		{
			sql.append(RepHelper.date_begin_eq("info.createtime", begindate)).append("\n");
		}

		if (!StringToolKit.isBlank(enddate))
		{
			sql.append(RepHelper.date_end("info.createtime", enddate)).append("\n");
		}
		
		if("Y".equals(ispublish))
		{
			sql.append("    and rflow.state = '结束' ").append("\n");
			sql.append("    and info.publishtime is not null ").append("\n");			
			if (!StringToolKit.isBlank(enddate))
			{
				sql.append(RepHelper.date_end("info.publishtime", enddate)).append("\n");
			}
		}
		else
		if("N".equals(ispublish))
		{
			sql.append("    and info.publishtime is null ").append("\n");
		}

		if (!StringToolKit.isBlank(creater))
		{
			sql.append("    and info.creater = " + SQLParser.charValue(creater)).append("\n");
		}

		sql.append("  group by info.deptid, info.creater, info.cno, ract.actdefid ").append("\n");
		
		if("Y".equals(isnodeovertime))
		{
			sql.append(" having sum(UF_Calculate_Duration(ract.completetime, ract.createtime)) > 1 ").append("\n");
		}
		else 
		if("N".equals(isnodeovertime))
		{
			sql.append(" having sum(UF_Calculate_Duration(ract.completetime, ract.createtime)) < 1 ").append("\n");
		}		
		
		sql.append("   union  ").append("\n");
		sql.append("   select info.deptid, info.creater, info.cno, 'XXHQ' actdefid, case when (UF_Calculate_Duration(info.createtime, info.obtaintime)) > 1 then 1 else 0 end cs ").append("\n");
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
		
		if("Y".equals(ispublish))
		{
			sql.append("    and info.publishtime is not null ").append("\n");			
			if (!StringToolKit.isBlank(enddate))
			{
				sql.append(RepHelper.date_end("info.publishtime", enddate)).append("\n");
			}
		}
		else
		if("N".equals(ispublish))
		{
			sql.append("     and info.publishtime is null ").append("\n");
		}

		if (!StringToolKit.isBlank(creater))
		{
			sql.append("    and info.creater = " + SQLParser.charValue(creater)).append("\n");
		}		
		
		if("Y".equals(isnodeovertime))
		{
			sql.append(" and (UF_Calculate_Duration(info.createtime, info.obtaintime)) > 1 ").append("\n");
		}
		else 
		if("N".equals(isnodeovertime))
		{
			sql.append(" and (UF_Calculate_Duration(info.createtime, info.obtaintime)) < 1 ").append("\n");
		}	
		
		// sql.append("  order by deptid, creater, cno, actdefid  ").append("\n");
		
		return sql.toString();
	}
	
	
	/**
	 * 该方法实现信息共享及时率的计算
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String sql_xxgx_kpi_jsl(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate"); // 结束日期
		String enddate = obj.getFormatAttr("enddate"); // 起始日期
		String sql_cdate = obj.getFormatAttr("sql_cdate"); // 系统与查询比较时间
		
		String ispublish = obj.getFormatAttr("ispublish"); // 发布状态开关
		String isovertime = obj.getFormatAttr("isovertime"); // 超时状态开关
		String isnodeovertime = obj.getFormatAttr("isnodeovertime"); // 节点超时状态开关
		String creater = obj.getFormatAttr("creater"); // 创建人
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select usr.loginname, usr.cname, usr.ownerdept, usr.deptname, sum(bzsc) bzsc, sum(zxsc) zxsc, sum(cskh)cskh ").append("\n");
		sql.append("   from t_app_infoshare info, t_sys_user usr, ").append("\n");
		sql.append("   (  ").append("\n");
		sql.append("     select runflowkey, cno,  bzsc, zxsc, (case when (zxsc - bzsc) > 5 then 5 else (zxsc - bzsc) end) cskh ").append("\n");
		sql.append("       from  ").append("\n");
		sql.append("       ( ").append("\n");
		sql.append("         select zx.runflowkey, zx.cno, zx.bzsc, ").append("\n");
		
		//sql.append("              ((case when info.publishtime is null then " + sql_cdate + " - info.obtaintime else info.publishtime - info.obtaintime end)) zxsc ").append("\n");
		if("Y".equals(ispublish))
		{
			sql.append(" (ract.completetime - ract.createtime) zxsc ").append("\n");
		}
		else
		if("N".equals(ispublish))
		{
			sql.append(" (case when ract.completetime is null then " + sql_cdate + " - info.obtaintime else info.publishtime - info.obtaintime end) zxsc ").append("\n");
		}
		else
		{
			sql.append(" (case when ract.completetime is null then " + sql_cdate + " - ract.createtime else info.publishtime - info.obtaintime end) zxsc ").append("\n");
		}
		
		
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
		
		return sql.toString();
	}
}
