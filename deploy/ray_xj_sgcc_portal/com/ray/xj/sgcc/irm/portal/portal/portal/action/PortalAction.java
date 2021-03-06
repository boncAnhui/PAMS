package com.ray.xj.sgcc.irm.portal.portal.portal.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.action.ActionSessionHelper;
import com.blue.ssh.core.action.BaseAction;
import com.blue.ssh.core.orm.Page;
import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.services.function.StringToolKit;
import com.headray.framework.spec.GlobalConstants;
import com.ray.app.query.action.QueryActionHelper;
import com.ray.app.query.entity.Search;
import com.ray.app.query.service.QueryService;
import com.ray.xj.sgcc.irm.portal.personalize.entity.RolePersonalize;
import com.ray.xj.sgcc.irm.portal.personalize.entity.UserPersonalize;
import com.ray.xj.sgcc.irm.portal.personalize.service.PersonalizService;
import com.ray.xj.sgcc.irm.portal.portal.portal.service.PortalService;
import com.ray.xj.sgcc.irm.system.organ.user.entity.User;
import com.ray.xj.sgcc.irm.system.organ.user.service.UserService;
import com.ray.xj.sgcc.irm.system.portal.leftpersonalized.service.LeftPersonalizedServie;
import com.ray.xj.sgcc.irm.system.portal.navitem.entity.Navitem;
import com.ray.xj.sgcc.irm.system.portal.navitem.service.NavitemService;
import com.ray.xj.sgcc.irm.system.portal.pmsubject.service.PmsubjectService;
import com.ray.xj.sgcc.irm.system.portal.shortcut.service.ShortCutService;
import com.ray.xj.sgcc.irm.system.portal.subject.service.SubjectService;
import com.ray.xj.sgcc.irm.system.portal.toppersonalized.service.TopPersonalizedServie;

public class PortalAction extends BaseAction
{
	@Autowired
	private PortalService portalService;

	@Autowired
	private ShortCutService shortCutService;

	@Autowired
	private TopPersonalizedServie topPersonalizedServie;

	@Autowired
	private LeftPersonalizedServie leftPersonalizedServie;

	@Autowired
	private NavitemService navitemService;

	@Autowired
	private PersonalizService personalizService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private UserService userService; // from nwpn code;

	private String _searchname; // from nwpn code;

	@Autowired
	private QueryService queryService; // from nwpn code;

	@Autowired
	private PmsubjectService pmsubjectService; // from nwpn code;

	public String browse() throws Exception
	{
		String forward = "browse";
		String ccate = Struts2Utils.getRequest().getParameter("ccate");
		String type = Struts2Utils.getRequest().getParameter("type");
		String navitemname = new String();
		if ("admin".equals(ccate))
		{
			navitemname = "系统设置";
		}
		else if ("multiple".equals(ccate))
		{
			forward = "mainframe";
		}
		else if ("show".equals(ccate))
		{
			String popupclass = Struts2Utils.getRequest().getParameter("popupclass");

			arg.put("popupclass", popupclass);

			forward = "show";
		}
		else if ("home".equals(ccate))
		{
			forward = "home";
		}
		else
		{
			Navitem navitem = navitemService.findUniqueBy("ccate", ccate);
			navitemname = navitem.getCname();
		}

		data.put("ccate", ccate);
		data.put("type", type);
		data.put("navitem", navitemname);
		return forward;
	}

	public String mainframe() throws Exception
	{
		String model = Struts2Utils.getRequest().getParameter("model");
		String type = Struts2Utils.getRequest().getParameter("type");
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		arg.put("model", model);
		arg.put("type", type);
		arg.put("begintime", begintime);
		arg.put("endtime", endtime);
		arg.put("_ctype", _ctype);

		return "mainframedeepen";
	}

	public String getchartdata() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");

		DynamicObject map = new DynamicObject();
		map.put("begintime", begintime);
		map.put("endtime", endtime);
		if (type == null || type == "")
		{
			map.put("type", "all");
		}
		else
		{
			map.put("type", type);
		}

		List datas = portalService.getChartData(map);

		data.put("datas", datas);
		return "getchartdata";
	}

	public String browsejson() throws Exception
	{
		String ccate = Struts2Utils.getRequest().getParameter("portal_id");
		List items = portalService.getportalbyccate(ccate);
		data.put("items", items);
		return "browsejson";
	}

	public String preferences() throws Exception
	{
		return "preferences";
	}

	public String preferenceshome() throws Exception
	{

		return "preferenceshome";
	}

	public String browsedeepen() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");

		DynamicObject map = new DynamicObject();
		map.put("begintime", begintime);
		map.put("endtime", endtime);
		if (type == null || type == "")
		{
			map.put("type", "all");
		}
		else
		{
			map.put("type", type);
		}
		data.put("type", (String) map.get("type"));

		Map tjzl = portalService.browsedeepen(map);
		data.put("tjzl", tjzl);

		data.put("map", map);
		return "browsedeepen";
	}

	public String browseyjya() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		data.put("begintime", begintime);
		data.put("endtime", endtime);
		data.put("type", type);

		arg.put("begindate", begintime);
		arg.put("enddate", endtime);
		arg.put("_ctype", _ctype);
		arg.put("type", type);
		return "browseyjya";
	}

	public String browseyjyagl() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		data.put("begintime", begintime);
		data.put("endtime", endtime);
		data.put("type", type);

		arg.put("begindate", begintime);
		arg.put("enddate", endtime);
		arg.put("_ctype", _ctype);
		arg.put("type", type);
		return "browseyjyagl";
	}

	public String browsealarm() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		data.put("begintime", begintime);
		data.put("endtime", endtime);
		data.put("type", type);

		arg.put("begindate", begintime);
		arg.put("enddate", endtime);
		arg.put("type", type);
		arg.put("_ctype", _ctype);
		return "browsealarm";
	}

	public String browsealarmgl() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		data.put("begintime", begintime);
		data.put("endtime", endtime);
		data.put("type", type);

		arg.put("begindate", begintime);
		arg.put("enddate", endtime);
		arg.put("_ctype", _ctype);
		arg.put("type", type);
		return "browsealarmgl";
	}

	public String browsedefect() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		data.put("begintime", begintime);
		data.put("endtime", endtime);
		data.put("type", type);

		arg.put("begindate", begintime);
		arg.put("enddate", endtime);
		arg.put("_ctype", _ctype);
		arg.put("type", type);
		return "browsedefect";
	}

	public String browsedefectgl() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		data.put("begintime", begintime);
		data.put("endtime", endtime);
		data.put("type", type);

		arg.put("begindate", begintime);
		arg.put("enddate", endtime);
		arg.put("_ctype", _ctype);
		arg.put("type", type);
		return "browsedefectgl";
	}

	public String browseevent() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		data.put("begintime", begintime);
		data.put("endtime", endtime);
		data.put("type", type);

		arg.put("begindate", begintime);
		arg.put("enddate", endtime);
		arg.put("_ctype", _ctype);
		arg.put("type", type);
		return "browseevent";
	}

	public String browsephonerecord() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		data.put("begintime", begintime);
		data.put("endtime", endtime);
		data.put("type", type);

		arg.put("begindate", begintime);
		arg.put("enddate", endtime);
		arg.put("_ctype", _ctype);
		arg.put("type", type);
		return "browsephonerecord";
	}

	public String browsephonerecordgl() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		data.put("begintime", begintime);
		data.put("endtime", endtime);
		data.put("type", type);

		arg.put("begindate", begintime);
		arg.put("enddate", endtime);
		arg.put("_ctype", _ctype);
		arg.put("type", type);
		return "browsephonerecordgl";
	}

	public String browseeventgl() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		data.put("begintime", begintime);
		data.put("endtime", endtime);
		data.put("type", type);

		arg.put("begindate", begintime);
		arg.put("enddate", endtime);
		arg.put("_ctype", _ctype);
		arg.put("type", type);
		return "browseeventgl";
	}

	public String browseaudit() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		data.put("begintime", begintime);
		data.put("endtime", endtime);
		data.put("type", type);

		arg.put("begindate", begintime);
		arg.put("enddate", endtime);
		arg.put("_ctype", _ctype);
		arg.put("type", type);
		return "browseaudit";
	}

	public String browseauditgl() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		data.put("begintime", begintime);
		data.put("endtime", endtime);
		data.put("type", type);

		arg.put("begindate", begintime);
		arg.put("enddate", endtime);
		arg.put("_ctype", _ctype);
		arg.put("type", type);
		return "browseauditgl";
	}

	public String browsemaintance() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		data.put("begintime", begintime);
		data.put("endtime", endtime);
		data.put("type", type);

		arg.put("begindate", begintime);
		arg.put("enddate", endtime);
		arg.put("_ctype", _ctype);
		arg.put("type", type);
		return "browsemaintance";
	}

	public String browsemaintancegl() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		data.put("begintime", begintime);
		data.put("endtime", endtime);
		data.put("type", type);

		arg.put("begindate", begintime);
		arg.put("enddate", endtime);
		arg.put("_ctype", _ctype);
		arg.put("type", type);
		return "browsemaintancegl";
	}

	public String browsechange() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		data.put("begintime", begintime);
		data.put("endtime", endtime);
		data.put("type", type);

		arg.put("begindate", begintime);
		arg.put("enddate", endtime);
		arg.put("_ctype", _ctype);
		arg.put("type", type);
		return "browsechange";
	}

	public String browsechangegl() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		data.put("begintime", begintime);
		data.put("endtime", endtime);
		data.put("type", type);

		arg.put("begindate", begintime);
		arg.put("enddate", endtime);
		arg.put("_ctype", _ctype);
		arg.put("type", type);
		return "browsechangegl";
	}

	public String browsestopapp() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		data.put("begintime", begintime);
		data.put("endtime", endtime);
		data.put("type", type);

		arg.put("begindate", begintime);
		arg.put("enddate", endtime);
		arg.put("_ctype", _ctype);
		arg.put("type", type);
		return "browsestopapp";
	}

	public String browsestopappgl() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		data.put("begintime", begintime);
		data.put("endtime", endtime);
		data.put("type", type);

		arg.put("begindate", begintime);
		arg.put("enddate", endtime);
		arg.put("_ctype", _ctype);
		arg.put("type", type);
		return "browsestopappgl";
	}

	public String browseequip() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		data.put("begintime", begintime);
		data.put("endtime", endtime);
		data.put("type", type);

		arg.put("begindate", begintime);
		arg.put("enddate", endtime);
		arg.put("_ctype", _ctype);
		arg.put("type", type);
		return "browseequip";
	}

	public String browseequipgl() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		data.put("begintime", begintime);
		data.put("endtime", endtime);
		data.put("type", type);

		arg.put("begindate", begintime);
		arg.put("enddate", endtime);
		arg.put("_ctype", _ctype);
		arg.put("type", type);
		return "browseequipgl";
	}

	public String browsectooperation() throws Exception
	{
		String begintime = Struts2Utils.getRequest().getParameter("begintime");
		String endtime = Struts2Utils.getRequest().getParameter("endtime");
		String type = Struts2Utils.getRequest().getParameter("type");
		String _ctype = Struts2Utils.getRequest().getParameter("_ctype");

		data.put("begintime", begintime);
		data.put("endtime", endtime);
		data.put("type", type);

		arg.put("begindate", begintime);
		arg.put("enddate", endtime);
		arg.put("_ctype", _ctype);
		arg.put("type", type);

		return "browsectooperation";
	}

	public String preferencestop() throws Exception
	{
		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token);
		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);

		// TopPersonalized topPersonalized =
		// topPersonalizedServie.gettoppersonalizedbyuser(loginname);
		//
		// data.put("topPersonalized", topPersonalized);

		return "preferencestop";
	}

	public String topsave() throws Exception
	{
		String cmenu = Struts2Utils.getRequest().getParameter("menu");
		String amenu = Struts2Utils.getRequest().getParameter("availmenu");
		String status = Struts2Utils.getRequest().getParameter("status");
		String theme = Struts2Utils.getRequest().getParameter("theme");

		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token);
		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);
		String username = login_token.getFormatAttr(GlobalConstants.sys_login_username);

		portalService.savetop(cmenu, amenu, status, theme, loginname, username);

		return "savetop";
	}

	public String preferencesleft() throws Exception
	{
		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token);
		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);

		List shortCuts = shortCutService.getShortCutbyuser(loginname);
		// LeftPersonalized leftPersonalized =
		// leftPersonalizedServie.getleftpersonalizedbyuser(loginname);

		data.put("shortCuts", shortCuts);
		// data.put("leftPersonalized", leftPersonalized);

		return "preferencesleft";
	}

	public String subject() throws Exception
	{
		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token);
		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);

		UserPersonalize upl = personalizService.getUserPersonalizeByid(loginname);

		if (upl != null && !StringToolKit.isBlank(upl.getMainsubject()))
		{
			data.put("jsonobj", upl.getMainsubject());
			return "subjects";
		}

		RolePersonalize rpl = personalizService.getRolePersonalizeByid(loginname);

		if (rpl != null && !StringToolKit.isBlank(rpl.getMainsubject()))
		{
			data.put("jsonobj", rpl.getMainsubject());
			return "subjects";
		}

		List subjects = subjectService.getSubjectByUser(loginname);

		data.put("subjects", subjects);

		return "subjects";
	}

	public String waitwork() throws Exception
	{
		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token);
		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);

		List ww = portalService.waitwork(loginname);

		data.put("waitworks", ww);

		return "waitwork";
	}

	public String listsubject() throws Exception
	{
		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token);
		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);

		String ctype = Struts2Utils.getRequest().getParameter("ctype");
		String configtype = Struts2Utils.getRequest().getParameter("configtype");

		Map<String, String> m = new HashMap<String, String>();

		m.put("loginname", loginname);
		m.put("configtype", configtype);

		List datas = new ArrayList();
		if (ctype.equals("alltask"))
		{
			datas = portalService.alltask(m);
		}
		else if (ctype.equals("allchange"))
		{
			datas = portalService.allchange(m);

		}
		else if (ctype.equals("allconfig"))
		{
			datas = portalService.allconfig(m);

		}
		else if (ctype.equals("myact"))
		{
			datas = portalService.myact(m);
		}
		else if (ctype.equals("allproject"))
		{

			datas = portalService.allproject(m);
		}
		data.put("rows", datas);
		arg.put("returnpage", ctype);
		return "portalsubject";
	}

	public String knowledgemap() throws Exception
	{
		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token);
		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);

		// Page page =portalService.waitwork(loginname) ;

		// data.put("waitworks", page.getResult());

		return "knowledgemap";
	}

	public String updatepersonleftmenu() throws Exception
	{
		String json = Struts2Utils.getRequest().getParameter("json");
		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token);
		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);

		personalizService.updateleftmenubyuserid(loginname, json);

		data.put("info", "1");
		return "ajaxinfo";
	}

	public String updatepersonhomemenu() throws Exception
	{
		String json = Struts2Utils.getRequest().getParameter("json");
		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token);
		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);

		personalizService.updatehomemenubyuserid(loginname, json);

		data.put("info", "1");
		return "ajaxinfo";
	}

	public String updatepersontopmenu() throws Exception
	{
		String json = Struts2Utils.getRequest().getParameter("json");
		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token);
		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);

		personalizService.updatetopmenubyuserid(loginname, json);

		data.put("info", "1");
		return "ajaxinfo";
	}

	public String deletepersonmenu() throws Exception
	{
		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token);
		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);
		String ctype = Struts2Utils.getRequest().getParameter("ctype");

		personalizService.deletepersonmenu(loginname, ctype);
		data.put("info", "1");
		return "ajaxinfo";

	}
	// from nwpn code
	@SuppressWarnings("unchecked")
	public String welcome() throws Exception
	{
		arg.put("pmodel", "welcome");
		return "welcome";
	}

	// from nwpn code
	public String getAllToDo() throws Exception
	{

		String loginname = ActionSessionHelper._get_loginname();

		List alltodo = portalService.getAllToDo(loginname);
		data.put("alltodo", alltodo);

		return "alltodo";
	}

	// from nwpn code
	public String getAllProcess() throws Exception
	{
		// List listallprocess = new ArrayList();
		// List listevent = new ArrayList();
		// List listproblem = new ArrayList();
		// List listchange = new ArrayList();
		String loginname = ActionSessionHelper._get_loginname();
		//
		// listevent = portalService.getEventProcess(loginname);
		// listproblem = portalService.getProblemProcess(loginname);
		// listchange = portalService.getChangeProcess(loginname);
		//
		// listallprocess.addAll(listevent);
		// listallprocess.addAll(listproblem);
		// listallprocess.addAll(listchange);

		List listallprocess = portalService.getAllProcess(loginname);

		data.put("listprocess", listallprocess);

		return "allprocess";
	}

	// from nwpn code
	public String browseAllToDo() throws Exception
	{
		HttpServletRequest request = Struts2Utils.getRequest();
		QueryActionHelper helper = new QueryActionHelper();

		String loginname = ActionSessionHelper._get_loginname();
		Search search = (Search) BeanUtils.cloneBean(queryService.findUniqueByOfSearch("searchname", _searchname));

		search.setMysql(portalService.getAllToDo_sql(loginname));
		Page page = helper.mockJdbcPage(search, queryService);
		Map vo = helper.mockVO(_searchname, queryService);

		data.put("vo", vo);
		data.put("page", page);

		return "browsealltodo";
	}

	// from nwpn code
	public String browseAllProcess() throws Exception
	{
		HttpServletRequest request = Struts2Utils.getRequest();
		QueryActionHelper helper = new QueryActionHelper();

		String loginname = ActionSessionHelper._get_loginname();
		Search search = (Search) BeanUtils.cloneBean(queryService.findUniqueByOfSearch("searchname", _searchname));

		search.setMysql(portalService.getAllProcess_sql(loginname));
		Page page = helper.mockJdbcPage(search, queryService);
		Map vo = helper.mockVO(_searchname, queryService);

		data.put("vo", vo);
		data.put("page", page);

		return "browseallprocess";
	}

	public void zxms() throws Exception
	{
		HttpServletRequest request = Struts2Utils.getRequest();
		String zxms = request.getParameter("zxms");
		portalService.updatezxms(zxms);
	}
	
	public String workcount() throws Exception
	{
		String loginName = ActionSessionHelper._get_loginname();
		int workcount = getWorkCount(loginName);
		data.put("workcount", workcount);
		return "workcount";
	}	
	
	// from nwpn code
	public int getWorkCount(String loginname) throws Exception
	{
		Map fmap = new HashMap();
		fmap.put("loginname", loginname);

		List taskworks = portalService.getAllToDo(loginname);
		return taskworks.size();
	}	
	
	
	
	
	

	public PortalService getPortalService()
	{
		return portalService;
	}

	public void setPortalService(PortalService portalService)
	{
		this.portalService = portalService;
	}

	public ShortCutService getShortCutService()
	{
		return shortCutService;
	}

	public void setShortCutService(ShortCutService shortCutService)
	{
		this.shortCutService = shortCutService;
	}

	public TopPersonalizedServie getTopPersonalizedServie()
	{
		return topPersonalizedServie;
	}

	public void setTopPersonalizedServie(TopPersonalizedServie topPersonalizedServie)
	{
		this.topPersonalizedServie = topPersonalizedServie;
	}

	public LeftPersonalizedServie getLeftPersonalizedServie()
	{
		return leftPersonalizedServie;
	}

	public void setLeftPersonalizedServie(LeftPersonalizedServie leftPersonalizedServie)
	{
		this.leftPersonalizedServie = leftPersonalizedServie;
	}

	public NavitemService getNavitemService()
	{
		return navitemService;
	}

	public void setNavitemService(NavitemService navitemService)
	{
		this.navitemService = navitemService;
	}

	public PersonalizService getPersonalizService()
	{
		return personalizService;
	}

	public void setPersonalizService(PersonalizService personalizService)
	{
		this.personalizService = personalizService;
	}

	public SubjectService getSubjectService()
	{
		return subjectService;
	}

	public void setSubjectService(SubjectService subjectService)
	{
		this.subjectService = subjectService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public String get_searchname()
	{
		return _searchname;
	}

	public void set_searchname(String searchname)
	{
		_searchname = searchname;
	}

	public QueryService getQueryService()
	{
		return queryService;
	}

	public void setQueryService(QueryService queryService)
	{
		this.queryService = queryService;
	}

	public PmsubjectService getPmsubjectService()
	{
		return pmsubjectService;
	}

	public void setPmsubjectService(PmsubjectService pmsubjectService)
	{
		this.pmsubjectService = pmsubjectService;
	}

	@Override
	public Object getModel()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception
	{
		// TODO Auto-generated method stub

	}

}
