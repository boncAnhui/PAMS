package com.ray.xj.sgcc.irm.portal.portal.portal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.headray.core.spring.jdo.DyDaoHelper;
import com.headray.framework.services.db.SQLParser;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.services.function.Types;
import com.ray.app.dictionary.entity.Dictionary;
import com.ray.app.dictionary.service.DictionaryService;
import com.ray.xj.sgcc.irm.system.portal.navitem.entity.Navitem;
import com.ray.xj.sgcc.irm.system.portal.navitem.service.NavitemService;
import com.ray.xj.sgcc.irm.system.portal.portalitem.dao.PortalItemDao;
import com.ray.xj.sgcc.irm.system.portal.portalitem.entity.PortalItem;
import com.ray.xj.sgcc.irm.system.portal.toppersonalized.entity.TopPersonalized;
import com.ray.xj.sgcc.irm.system.portal.toppersonalized.service.TopPersonalizedServie;
import com.ray.xj.sgcc.irm.system.portal.usernavitem.entity.UserNavitem;
import com.ray.xj.sgcc.irm.system.portal.usernavitem.service.UserNavitemService;

@Component
@Transactional
public class PortalService
{
	@Autowired
	private PortalItemDao portalItemDao;

	@Autowired
	private TopPersonalizedServie topPersonalizedServie;

	@Autowired
	private NavitemService navitemService;

	@Autowired
	private UserNavitemService userNavitemService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private DictionaryService dictionaryService;

	public static int PORTAL_SUBJECT_MAXRECORD = 6;

	public List getChartData(DynamicObject map) throws Exception
	{
		Map data = browsedeepen(map);
		List datas = new ArrayList<Map>();

		Map temp = new HashMap<String, Object>();
		temp.put("lx", "调度业务");
		temp.put("num", (Integer) data.get("gjzl") + (Integer) data.get("qxzl"));
		datas.add(temp);

		temp = new HashMap<String, Object>();
		temp.put("lx", "运行业务");
		temp.put("num", (Integer) data.get("yjzl"));
		datas.add(temp);

		temp = new HashMap<String, Object>();
		temp.put("lx", "检修业务");
		temp.put("num", (Integer) data.get("xjzl") + (Integer) data.get("jxzl") + (Integer) data.get("bgzl"));
		datas.add(temp);

		temp = new HashMap<String, Object>();
		temp.put("lx", "客服业务");
		temp.put("num", (Integer) data.get("sjzl") + (Integer) data.get("bpbjzl"));
		datas.add(temp);

		temp = new HashMap<String, Object>();
		temp.put("lx", "系统建设");
		temp.put("num", (Integer) data.get("jzyzl"));
		datas.add(temp);

		return datas;
	}

	public Map browsedeepen(DynamicObject map) throws Exception
	{
		String type = (String) map.get("type");
		Map tjzs = new HashMap<String, Integer>();

		return tjzs;
	}

	public List getportalbyccate(String ccate) throws Exception
	{
		List pitems = new ArrayList();

		List<PortalItem> portalitem_group = portalItemDao.find("from PortalItem t where t.ctype='group' and t.ccate=? order by ordernum", ccate);
		for (int i = 0; i < portalitem_group.size(); i++)
		{
			PortalItem portalItem = portalitem_group.get(i);
			Map map = new HashMap();
			map.put("groupid", portalItem.getId());
			map.put("groupname", portalItem.getCname());
			map.put("groupstate", "opened");

			List<PortalItem> portalitem_item = portalItemDao.findBy("supid", portalItem.getId(), Order.asc("ordernum"));
			map.put("items", portalitem_item);

			pitems.add(map);
		}
		return pitems;

	}

	public void savetop(String cmenu, String amenu, String status, String theme, String loginname, String username) throws Exception
	{
		List<UserNavitem> navitems_user = userNavitemService.find("from UserNavitem ut where ut.loginname=" + SQLParser.charValue(loginname));
		for (int z = 0; z < navitems_user.size(); z++)
		{
			userNavitemService.deleteUserNavitem(navitems_user.get(z).getId());
		}

		if (StringUtils.isNotBlank(cmenu))
		{
			String[] cmenus = cmenu.split(",");
			for (int i = 0; i < cmenus.length; i++)
			{
				Navitem navitem = navitemService.getNavitem(cmenus[i]);
				UserNavitem cnavitem = new UserNavitem();
				cnavitem.setCtype("top");
				cnavitem.setLoginname(loginname);
				cnavitem.setNavitemid(cmenus[i]);
				cnavitem.setNavitemname(navitem.getCname());
				cnavitem.setUsername(username);
				cnavitem.setOrdernum(Types.parseString(i));
				userNavitemService.save(cnavitem);
			}
		}
		if (StringUtils.isNotBlank(amenu))
		{
			String[] amenus = amenu.split(",");
			for (int i = 0; i < amenus.length; i++)
			{
				Navitem navitem = navitemService.getNavitem(amenus[i]);
				UserNavitem cnavitem = new UserNavitem();
				cnavitem.setCtype("more");
				cnavitem.setLoginname(loginname);
				cnavitem.setNavitemid(amenus[i]);
				cnavitem.setNavitemname(navitem.getCname());
				cnavitem.setUsername(username);
				cnavitem.setOrdernum(Types.parseString(i));
				userNavitemService.save(cnavitem);
			}
		}

		TopPersonalized topPersonalized = topPersonalizedServie.gettoppersonalizedbyuser(loginname);
		if (topPersonalized == null)
		{
			topPersonalized = new TopPersonalized();
		}
		topPersonalized.setLoginname(loginname);
		topPersonalized.setState(status);
		topPersonalized.setSysskin(theme);
		topPersonalized.setUsername(username);

		topPersonalizedServie.savetoppersonalized(topPersonalized);

	}

	public List myact(Map<String, String> m) throws Exception
	{

		StringBuffer sql = new StringBuffer();

		sql.append(" select * ");
		sql.append(" from T_APP_BUSINESSACT t");
		sql.append(" where 1 = 1 ");
		sql.append(" and actorid = '" + m.get("loginname").toString() + "'");
		sql.append(" and rownum <=10");

		return jdbcTemplate.queryForList(sql.toString());

	}

	public List alltask(Map<String, String> m) throws Exception
	{

		StringBuffer sql = new StringBuffer();

		sql.append(" select t.id as id, ");
		sql.append(" t.workname as workname, t.chargedept as chargedept, ");
		sql.append(" t.chargeuser as chargeuser, t.audituser as audituser,");
		sql.append(" t.tbegintime as tbegintime, t.tendtime as tendtime,");
		sql.append(" t.tasktype as tasktype, t.pmstate as pmstate, ");
		sql.append("  case t.state when 'track' then '跟踪'  ");
		sql.append("  when 'issue' then '新建'");
		sql.append("  when 'audit' then '审核'  ");
		sql.append("  when 'finish' then '结束' end state , ");
		sql.append(" t.islast as islast,t.internal as internal, ");
		sql.append(" t.supid as supid, t.issuer as issuer, t.submituser as submituser");
		sql.append(" from t_app_task t");
		sql.append(" where 1 = 1 ");
		sql.append("  and rownum <=10");

		return jdbcTemplate.queryForList(sql.toString());

	}

	public List allchange(Map<String, String> m) throws Exception
	{

		StringBuffer sql = new StringBuffer();

		sql.append(" select * from ");
		sql.append("  (select a.id, a.cno, a.applytime, a.applyer, a.applyeruserid, a.applydept, a.ptimebegin, a.ptimeend, nvl(b.username,c.username) tuser, a.rtimebegin, a.rtimeend,  ");
		sql.append("  case a.state when 'apply' then '新建'  ");
		sql.append("  when 'confirm' then '审核'");
		sql.append("  when 'cost' then '调整跟踪'  ");
		sql.append("  when 'costconfirm' then '确认'");
		sql.append("  when 'execute' then '执行'");
		sql.append("  when 'invalidate' then '作废'  ");
		sql.append("  when 'finish' then '结束' end state   ");
		sql.append("   from t_app_configchange a left join t_sys_waitwork b ");
		sql.append("   on a.id=b.taskid  ");
		sql.append("   and b.cclass = 'change'");

		sql.append("   left join t_sys_flowlog c ");
		sql.append("   on a.id = c.businessid ");
		sql.append("   and c.btype = 'change' ");
		sql.append("   and c.dname = '结束' ");

		sql.append("   order by cno desc ");
		sql.append("   ) where rownum <=10");
		sql.append("   order by rownum asc ");

		return jdbcTemplate.queryForList(sql.toString());
	}

	public List allconfig(Map<String, String> m) throws Exception
	{

		StringBuffer sql = new StringBuffer();

		sql.append(" select * from ");
		sql.append("    	(select distinct v.id, v.cname sc, c.cvalue xtmc, fwcs.csmc fws, v.founder, v.version, v.state, v.updatetime ");
		sql.append("		 from t_app_config v ");
		sql.append("		 left join t_app_configitem b ");
		sql.append("		 on v.id = b.configid ");
		sql.append("		 and b.pname = 'fwsbh' ");
		sql.append("		 left join t_app_zdcs fwcs ");
		sql.append("		 on b.cvalue = fwcs.bh ");
		sql.append("		 left join t_app_configitem c ");
		sql.append("		 on v.id = c.configid ");
		sql.append("		 and c.pname = 'xtzwmc' ");
		sql.append("		 where 1 = 1 ");
		sql.append("		 and v.isuse = 'Y' ");
		sql.append("		 and v.state = '发布' ");
		sql.append("		 and v.ctype = 'yyxt' ");
		sql.append("		 order by v.updatetime desc ");
		sql.append("   ) where rownum <=10");
		sql.append("   order by rownum asc ");

		return jdbcTemplate.queryForList(sql.toString());
	}

	public List allproject(Map<String, String> m) throws Exception
	{

		StringBuffer sql = new StringBuffer();

		sql.append(" select * from ");
		sql.append("   (select  p.id as id, p.cname as cname, d.dtext as cclass, p.leader as leader,  ");
		sql.append("   p.pm as pm, p.pmdatebegin as pmdatebegin, p.pmdateend as pmdateend, ");
		sql.append("   p.pmstate as pmstate, p.sumhr as sumhr, p.sumfn as sumfn, ");
		sql.append("  case when p.state='issue' then '新建' ");
		sql.append(" when p.state='track' then '跟踪' ");
		sql.append(" when p.state='audit' then '审核' ");
		sql.append(" when p.state='finish' then '结束' end  as state, ");
		sql.append(" p.islast as islast, p.internal as internal, p.supid as supid   ");
		sql.append("  from  t_sys_dictionary d, t_app_project p ");
		sql.append("   where 1 = 1 and p.supid = 'R0' ");
		sql.append("   and p.cclass = d.id and d.dkey='app.project.cclass' ");
		sql.append("   order by p.pmcreatetime desc  ");
		sql.append("   ) where rownum <=10");
		sql.append("   order by rownum asc ");

		return jdbcTemplate.queryForList(sql.toString());
	}

	public List waitwork(String loginname) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append(" select * from (");
		sql.append("	select id,userid,username,cclass,CREATETIME,curl,SIGNSTATE,SIGNTIME,snode,suserid,SUSERNAME,taskid,title ");
		sql.append("		from t_sys_waitworkmessage t ");
		sql.append("		where userid='" + loginname + "'");
		sql.append(" UNION");
		sql.append("	select id,userid,username,cclass,CREATETIME,curl,SIGNSTATE,SIGNTIME,snode,suserid,SUSERNAME,taskid,title ");
		sql.append("		from T_SYS_WAITWORK ");
		sql.append("	where userid='" + loginname + "'");
		sql.append(" )t ");
		sql.append(" order by createtime desc ");
		sql.append(") tmp where  rownum<=10  ");
		sql.append("   order by rownum asc ");

		return jdbcTemplate.queryForList(sql.toString());

	}

	// from nwpn code
	public List getAllToDo(String loginname) throws Exception
	{
		String sql = getAllToDo_sql(loginname);
		JdbcTemplate jt = jdbcTemplate;
		List list = DyDaoHelper.query(jt, sql);

		return list;
	}

	public String getAllToDo_sql(String loginname) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_SYS_WFWAITWORK  where receiver='" + loginname + "' order by sendtime desc  ").append("\n");
		return sql.toString();
	}

	// from nwpn code
	public List getAllProcess(String loginname) throws Exception
	{
		String sql = getAllProcess_sql(loginname);
		JdbcTemplate jt = jdbcTemplate;
		List list = DyDaoHelper.query(jt, sql.toString());

		return list;
	}

	public String getAllProcess_sql(String loginname) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" select bclass, taskid, tasktime, taskname, susername, staskname, dusername,").append("\n");
		sql.append(" taked, bussinessid, ctype, cno, title, cclass, createtime, username ").append("\n");
		sql.append(" from  ").append("\n");
		sql.append(" ( ").append("\n");
		sql.append(" select  a.cclass bclass, a.dtaskid  taskid, a.dtasktime  tasktime, a.dtaskname  taskname,a.susername  susername,a.dusername  dusername,a.staskname  staskname,  ").append("\n");
		sql.append(" case when a.assignee_ is null then '未接单' else '已接单' end  taked,").append("\n");
		sql.append(" case when a.cclass='event' then e.id when a.cclass='changerecord' then c.id when a.cclass='problem' then p.id when a.cclass='alarm' then l.id when a.cclass='defect' then d.id end bussinessid,  ").append("\n");
		sql.append(" case when a.cclass='event' then '事件' when a.cclass='changerecord' then '变更' when a.cclass='problem' then '问题' when a.cclass='alarm' then '告警' when a.cclass='defect' then '缺陷' end ctype, ").append("\n");
		sql.append(" case when a.cclass='event' then e.cno when a.cclass='changerecord' then c.cno when a.cclass='problem' then p.cno when a.cclass='alarm' then l.cno when a.cclass='defect' then d.cno end cno, ").append("\n");
		sql.append(" case when a.cclass='event' then e.title when a.cclass='changerecord' then c.title when a.cclass='problem' then p.title when a.cclass='alarm' then l.description when a.cclass='defect' then d.title end title, ").append("\n");
		sql.append(" case when a.cclass='event' then e.cclass when a.cclass='changerecord' then c.cclass when a.cclass='problem' then p.cclass when a.cclass='alarm' then l.ctype when a.cclass='defect' then d.cclass end cclass, ").append("\n");
		sql.append(" case when a.cclass='event' then e.createtime when a.cclass='changerecord' then c.createtime when a.cclass='problem' then p.createtime when a.cclass='alarm' then l.findtime when a.cclass='defect' then d.createtime end createtime, ")
				.append("\n");
		sql.append(" case when a.cclass='event' then e.accepter when a.cclass='changerecord' then c.username when a.cclass='problem' then p.username when a.cclass='alarm' then l.username when a.cclass='defect' then d.username end username ")
				.append("\n");
		sql.append(" from  ").append("\n");
		sql.append(" ( ").append("\n");
		sql.append(" select ht.*,fv.* from jbpm4_hist_task ht, ").append("\n");
		sql.append(" ( ").append("\n");
		sql.append(" select distinct hp.dbid_  ").append("\n");
		sql.append(" from jbpm4_hist_task ht, jbpm4_hist_actinst ha, jbpm4_hist_procinst hp, t_app_flowvariable v  ").append("\n");
		sql.append(" where 1 = 1  ").append("\n");
		sql.append(" and ht.dbid_ = ha.htask_  ").append("\n");
		sql.append(" and ha.hproci_ = hp.dbid_  ").append("\n");
		sql.append(" and hp.dbid_ = v.procid  ").append("\n");
		sql.append(" and ht.assignee_ ='" + loginname + "' ").append("\n");
		sql.append(" ) e,  ").append("\n");
		sql.append(" t_app_flowvariable fv ").append("\n");
		sql.append(" where 1 = 1  ").append("\n");
		sql.append(" and ht.dbid_ = fv.dtaskid  ").append("\n");
		sql.append(" and (fv.duser is null or fv.duser <> '" + loginname + "')  ").append("\n");
		sql.append(" and fv.procid = e.dbid_ ").append("\n");

		sql.append(" order by ht.create_ desc  ").append("\n");
		sql.append(" ) a ").append("\n");
		sql.append(" left join t_app_event e ").append("\n");
		sql.append(" on a.bussinessid = e.id  ").append("\n");
		sql.append(" left join t_app_changerecord c ").append("\n");
		sql.append(" on a.bussinessid = c.id ").append("\n");
		sql.append(" left join t_app_problem p ").append("\n");
		sql.append(" on a.bussinessid = p.id ").append("\n");
		sql.append(" left join t_app_alarm l ").append("\n");
		sql.append(" on a.bussinessid = l.id ").append("\n");
		sql.append(" left join t_app_defect d ").append("\n");
		sql.append(" on a.bussinessid = d.id ").append("\n");
		sql.append(" union ").append("\n");
		sql.append(" select 'configchange' bclass, ract.runactkey taskid,").append("\n");
		sql.append(" ract.createtime tasktime, bact.cname taskname,   ").append("\n");
		sql.append(" '' susername,  u.cname dusername,'' staskname, ").append("\n");
		sql.append(" case when ract.state ='待处理' then '未接单' else '已接单' end  taked, bv.id bussinessid, '变更预评估' ctype, bv.cno cno, bv.cno title, '' cclass, ract.createtime createtime, u.cname username ").append("\n");
		sql.append(" from t_app_configchange bv, t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_sys_wfrflowreader rflowreader, t_sys_user u ").append("\n");
		sql.append(" where 1=1 ").append("\n");
		sql.append("  and bv.id = rflow.dataid  ").append("\n");
		sql.append("  and ract.runflowkey = rflow.runflowkey ").append("\n");
		sql.append("  and (  (ract.state in ('待处理', '正处理') and rflow.state <> '结束')  ").append("\n");
		sql.append("      or (bact.ctype = 'END' and rflow.state = '结束') ) ").append("\n");
		sql.append("  and bact.id = ract.actdefid ").append("\n");
		sql.append("  and ract.runflowkey = rflowreader.runflowkey ").append("\n");
		sql.append("  and rflowreader.readerctx =  u.id ").append("\n");
		sql.append("  and u.loginname =  " + SQLParser.charValue(loginname)).append("\n");

		sql.append(" union ").append("\n");
		sql.append(" select 'hdanger' bclass, ract.runactkey taskid,").append("\n");
		sql.append(" ract.createtime tasktime, bact.cname taskname,   ").append("\n");
		sql.append(" '' susername,  u.cname dusername,'' staskname, ").append("\n");
		sql.append(" case when ract.state ='待处理' then '未接单' else '已接单' end  taked, bv.id bussinessid, '隐患管理' ctype, bv.cno cno, bv.htitle title, '' cclass, ract.createtime createtime, u.cname username ").append("\n");
		sql.append(" from t_app_hdanger bv, t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_sys_wfrflowreader rflowreader, t_sys_user u ").append("\n");
		sql.append(" where 1=1 ").append("\n");
		sql.append("  and bv.id = rflow.dataid  ").append("\n");
		sql.append("  and ract.runflowkey = rflow.runflowkey ").append("\n");
		sql.append("  and (  (ract.state in ('待处理', '正处理') and rflow.state <> '结束')  ").append("\n");
		sql.append("      or (bact.ctype = 'END' and rflow.state = '结束') ) ").append("\n");
		sql.append("  and bact.id = ract.actdefid ").append("\n");
		sql.append("  and ract.runflowkey = rflowreader.runflowkey ").append("\n");
		sql.append("  and rflowreader.readerctx =  u.id ").append("\n");
		sql.append("  and u.loginname =  " + SQLParser.charValue(loginname)).append("\n");

		sql.append(" union ").append("\n");
		sql.append(" select 'knowledge' bclass, ract.runactkey taskid,").append("\n");
		sql.append(" ract.createtime tasktime, bact.cname taskname,   ").append("\n");
		sql.append(" '' susername,  u.cname dusername,'' staskname, ").append("\n");
		sql.append(" case when ract.state ='待处理' then '未接单' else '已接单' end  taked, bv.id bussinessid, '知识库' ctype, bv.kno cno, bv.title title, '' cclass, ract.createtime createtime, u.cname username ").append("\n");
		sql.append(" from t_app_knowledge bv, t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_sys_wfrflowreader rflowreader, t_sys_user u ").append("\n");
		sql.append(" where 1=1 ").append("\n");
		sql.append("  and bv.id = rflow.dataid  ").append("\n");
		sql.append("  and ract.runflowkey = rflow.runflowkey ").append("\n");
		sql.append("  and (  (ract.state in ('待处理', '正处理') and rflow.state <> '结束')  ").append("\n");
		sql.append("      or (bact.ctype = 'END' and rflow.state = '结束') ) ").append("\n");
		sql.append("  and bact.id = ract.actdefid ").append("\n");
		sql.append("  and ract.runflowkey = rflowreader.runflowkey ").append("\n");
		sql.append("  and rflowreader.readerctx =  u.id ").append("\n");
		sql.append("  and u.loginname =  " + SQLParser.charValue(loginname)).append("\n");

		sql.append(" union ").append("\n");
		sql.append(" select 'contingencyplan' bclass, ract.runactkey taskid, ").append("\n");
		sql.append(" ract.createtime tasktime, bact.cname taskname,   ").append("\n");
		sql.append(" '' susername,  u.cname dusername, '' staskname, ").append("\n");
		sql.append(" case when ract.state ='待处理' then '未接单' else '已接单' end  taked, bv.id bussinessid, '演练计划' ctype, bv.cno cno, bv.contingencyname title, '' cclass, ract.createtime createtime, u.cname username ").append("\n");
		sql.append(" from t_app_contingencyplan bv, t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_sys_wfrflowreader rflowreader, t_sys_user u ").append("\n");
		sql.append(" where 1=1 ").append("\n");
		sql.append("  and bv.id = rflow.dataid  ").append("\n");
		sql.append("  and ract.runflowkey = rflow.runflowkey ").append("\n");
		sql.append("  and (  (ract.state in ('待处理', '正处理') and rflow.state <> '结束')  ").append("\n");
		sql.append("      or (bact.ctype = 'END' and rflow.state = '结束') ) ").append("\n");
		sql.append("  and bact.id = ract.actdefid ").append("\n");
		sql.append("  and ract.runflowkey = rflowreader.runflowkey ").append("\n");
		sql.append("  and rflowreader.readerctx =  u.id ").append("\n");
		sql.append("  and u.loginname =  " + SQLParser.charValue(loginname)).append("\n");

		sql.append(" union ").append("\n");
		sql
				.append(
						" select 'maintenance' bclass, t.id taskid, t1.ctime tasktime, t1.dname taskname, '' susername,  t1.dusername dusername, '' staskname, ''  taked, t.id bussinessid, '检修计划' ctype, t.cno cno, t.title title, '' cclass, t.createtime createtime, '' username  ")
				.append("\n");
		sql.append("   from t_app_maintenance t, ").append("\n");
		sql.append("        (select t.* from t_app_maintenancelogs t, (select  max(t.ctime) ctime, t.maintenanceid  ").append("\n");
		sql.append("           from t_app_maintenancelogs t  ").append("\n");
		sql.append("          where userid = " + SQLParser.charValue(loginname) + "  group by t.maintenanceid) t1 where t.maintenanceid = t1.maintenanceid and t.ctime = t1.ctime) t1  ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and t.id = t1.maintenanceid ").append("\n");
		sql.append("    and t.id not in ").append("\n");
		sql.append("        (select m.id ").append("\n");
		sql.append("           from t_app_maintenance m, t_app_maintenancelogs t ").append("\n");
		sql.append("          where m.id = t.maintenanceid ").append("\n");
		sql.append("            and ((t.duserid = " + SQLParser.charValue(loginname) + " and m.state = '审核') or ").append("\n");
		sql.append("                (t.userid = " + SQLParser.charValue(loginname) + " and m.state = '编制')) ").append("\n");
		sql.append("            and t.ctime = ").append("\n");
		sql.append("                (select max(l.ctime) ").append("\n");
		sql.append("                   from t_app_maintenancelogs l ").append("\n");
		sql.append("                  where l.maintenanceid = t.maintenanceid)) ").append("\n");

		sql.append(" union ").append("\n");
		sql
				.append(
						" select 'audit' bclass, t.id taskid, t1.ctime tasktime, t1.dname taskname, '' susername,  t1.dusername dusername, '' staskname, ''  taked, t.id bussinessid, '巡检' ctype, t.cno cno, t.cno title, '' cclass, t.createtime createtime, '' username  ")
				.append("\n");
		sql.append("   from t_app_audit t, ").append("\n");
		sql.append("    (select t.* from t_app_auditlogs t, (select max(t.ctime) ctime, t.auditid ").append("\n");
		sql.append("            from t_app_auditlogs t ").append("\n");
		sql.append("          where userid = " + SQLParser.charValue(loginname) + "  group by t.auditid) t1 where t.auditid = t1.auditid and t.ctime = t1.ctime) t1 ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and t.id = t1.auditid ").append("\n");
		sql.append("    and t.id not in ").append("\n");
		sql.append("( select m.id ").append("\n");
		sql.append("   from t_app_audit m, ").append("\n");
		sql.append("        (select t.* ").append("\n");
		sql.append("           from t_app_auditlogs t, ").append("\n");
		sql.append("                (select max(l.ctime) ctime, auditid ").append("\n");
		sql.append("                   from t_app_auditlogs l ").append("\n");
		sql.append("                  group by l.auditid) t1 ").append("\n");
		sql.append("          where t.ctime = t1.ctime ").append("\n");
		sql.append("            and t.auditid = t1.auditid) t, ").append("\n");
		sql.append("        t_app_auditcims ac ").append("\n");
		sql.append("  where m.id = t.auditid ").append("\n");
		sql.append("    and t.duserid = '" + loginname + "'  ").append("\n");
		sql.append("    and t.dname <> '结束' ").append("\n");
		sql.append("    and m.state <> '生效' ").append("\n");
		sql.append("    and ac.id = m.cimsid )").append("\n");

		sql.append(" union ").append("\n");
		sql.append(" select 'ctooperation' bclass, ").append("\n");
		sql.append("        t.id taskid, ").append("\n");
		sql.append("        t1.ctime tasktime, ").append("\n");
		sql.append("        case t1.dname when 'build' then '建设阶段' when 'handover' then '交接阶段' when 'start' then '前期阶段' else '创建项目' end taskname,  ").append("\n");
		sql.append("        t1.username susername, ").append("\n");
		sql.append("        t1.dusername dusername, ").append("\n");
		sql.append("        '' staskname, ").append("\n");
		sql.append("        '' taked, ").append("\n");
		sql.append("        t.id bussinessid, ").append("\n");
		sql.append("        '建转运' ctype, ").append("\n");
		sql.append("        t.cno cno, ").append("\n");
		sql.append("        t.pname title, ").append("\n");
		sql.append("        '' cclass, ").append("\n");
		sql.append("        t.createtime createtime, ").append("\n");
		sql.append("        '' username ").append("\n");
		sql.append("   from t_app_ctooperation t, ").append("\n");
		sql.append("        (select l.* from t_app_ctooperation_log l, (select max(t.ctime) ctime, t.bussinessid  ").append("\n");
		sql.append("          from t_app_ctooperation_log t ").append("\n");
		sql.append("          where userid = " + SQLParser.charValue(loginname)).append("\n");
		sql.append("          group by t.bussinessid) temp where l.ctime = temp.ctime and l.bussinessid = temp.bussinessid) t1 ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and t.id = t1.bussinessid ").append("\n");
		sql.append("    and t.id not in ").append("\n");
		sql.append("        (select c.id  ").append("\n");
		sql.append("           from t_app_ctooperation c, t_app_ctooperation_log cl ").append("\n");
		sql.append("          where c.id = cl.bussinessid ").append("\n");
		sql.append("            and cl.duserid = " + SQLParser.charValue(loginname)).append("\n");
		sql.append("            and c.stage <> 'js' ").append("\n");
		sql.append("            and cl.ctime = (select max(g.ctime) ").append("\n");
		sql.append("                              from t_app_ctooperation_log g ").append("\n");
		sql.append("                             where g.bussinessid = cl.bussinessid)) ").append("\n");

		sql.append(" union ").append("\n");
		sql.append(" select 'ctooperation' bclass, ").append("\n");
		sql.append("        t.id taskid, ").append("\n");
		sql.append("        t1.ctime tasktime, ").append("\n");
		sql.append("        case t1.dname when 'leader' then '班组长审核' when 'manager' then '业务主管审核' when 'end' then '结束' else '创建' end taskname, ").append("\n");
		sql.append("        t1.username susername, ").append("\n");
		sql.append("        t1.dusername dusername, ").append("\n");
		sql.append("        '' staskname, ").append("\n");
		sql.append("        '' taked, ").append("\n");
		sql.append("        t.id bussinessid, ").append("\n");
		sql.append("        '建转运里程碑' ctype, ").append("\n");
		sql.append("        t.cno cno, ").append("\n");
		sql.append("        t.pname || '[里程碑]' || doc.cname title, ").append("\n");
		sql.append("        '' cclass, ").append("\n");
		sql.append("        t.createtime createtime, ").append("\n");
		sql.append("        '' username ").append("\n");
		sql.append("   from t_app_ctooperation t, ").append("\n");
		sql.append("        t_app_ctooperation_stage doc, ").append("\n");
		sql.append("        (select l.* from t_app_ctooperation_log l, (select max(t.ctime) ctime, t.bussinessid  ").append("\n");
		sql.append("          from t_app_ctooperation_log t ").append("\n");
		sql.append("          where userid = " + SQLParser.charValue(loginname)).append("\n");
		sql.append("          group by t.bussinessid) temp where l.ctime = temp.ctime and l.bussinessid = temp.bussinessid) t1 ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and t.id = doc.ctooperationid ").append("\n");
		sql.append("    and doc.id = t1.bussinessid ").append("\n");
		sql.append("    and t.id not in ").append("\n");
		sql.append("        (select c.id ").append("\n");
		sql.append("           from t_app_ctooperation     c, ").append("\n");
		sql.append("                t_app_ctooperation_stage d, ").append("\n");
		sql.append("                t_app_ctooperation_log l ").append("\n");
		sql.append("          where 1 = 1 ").append("\n");
		sql.append("            and d.id = l.bussinessid ").append("\n");
		sql.append("            and l.ctime = (select max(g.ctime) ").append("\n");
		sql.append("                             from t_app_ctooperation_log g ").append("\n");
		sql.append("                            where g.bussinessid = l.bussinessid ").append("\n");
		sql.append("                              and g.ttype <> '创建') ").append("\n");
		sql.append("            and c.id = d.ctooperationid ").append("\n");
		sql.append("            and l.duserid = " + SQLParser.charValue(loginname)).append("\n");
		sql.append("         union ").append("\n");
		sql.append("         select c.id ").append("\n");
		sql.append("           from t_app_ctooperation c, t_app_ctooperation_log cl ").append("\n");
		sql.append("          where c.id = cl.bussinessid ").append("\n");
		sql.append("            and cl.duserid = " + SQLParser.charValue(loginname)).append("\n");
		sql.append("            and c.stage <> 'js' ").append("\n");
		sql.append("            and cl.ctime = (select max(g.ctime) ").append("\n");
		sql.append("                              from t_app_ctooperation_log g ").append("\n");
		sql.append("                             where g.bussinessid = cl.bussinessid)) ").append("\n");

		sql.append(" )  ").append("\n");
		sql.append(" where bussinessid is not null  ").append("\n");
		sql.append(" order by tasktime  desc ").append("\n");

		return sql.toString();
	}

	public void updatezxms(String zxms) throws Exception
	{
		Dictionary dictionary = dictionaryService.findUniqueBy("dkey", "system.voice.zxms");
		if ("zz".equals(zxms))
		{
			dictionary.setDvalue("zz");
			dictionary.setDtext("主值模式");

		}
		else
		{
			dictionary.setDvalue("lx");
			dictionary.setDtext("轮循模式");
		}
		dictionaryService.save(dictionary);
	}
}