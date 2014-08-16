package com.ray.xj.sgcc.irm.menu.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.core.webwork.action.Anonymous;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.services.function.StringToolKit;
import com.headray.framework.spec.GlobalConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.ray.xj.sgcc.irm.portal.personalize.entity.RolePersonalize;
import com.ray.xj.sgcc.irm.portal.personalize.entity.UserPersonalize;
import com.ray.xj.sgcc.irm.portal.personalize.service.PersonalizService;
import com.ray.xj.sgcc.irm.system.portal.leftpersonalized.service.LeftPersonalizedServie;
import com.ray.xj.sgcc.irm.system.portal.navitem.service.NavitemService;
import com.ray.xj.sgcc.irm.system.portal.shortcut.service.ShortCutService;
import com.ray.xj.sgcc.irm.system.portal.toppersonalized.service.TopPersonalizedServie;

public class MenuAction extends ActionSupport implements Anonymous
{

	private Map data = new HashMap();

	@Autowired
	private ShortCutService shortCutService;

	@Autowired
	private NavitemService navitemService;

	@Autowired
	private PersonalizService personalizService;

	@Autowired
	private TopPersonalizedServie topPersonalizedServie;

	@Autowired
	private LeftPersonalizedServie leftPersonalizedServie;

	public String execute() throws Exception
	{
		String forward = NONE;

		forward = "othermenu";

		return forward;
	}

	public String leftmenu() throws Exception
	{
		String forward = NONE;

		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token);
		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);
		
		
		
		UserPersonalize upl = personalizService.getUserPersonalizeByid(loginname);
		
		if (upl!=null && !StringToolKit.isBlank(upl.getLeftmenu()))
		{
			data.put("jsonobj", upl.getLeftmenu());
			return "leftmenu";
		}

		RolePersonalize rpl = personalizService.getRolePersonalizeByid(loginname);

		if (rpl!=null && !StringToolKit.isBlank(rpl.getLeftmenu()))
		{
			data.put("jsonobj", rpl.getLeftmenu());
			return "leftmenu";
		}
		
		

		List shortCuts = shortCutService.getShortCutbyuser(loginname);
//		LeftPersonalized leftPersonalized = leftPersonalizedServie.getleftpersonalizedbyuser(loginname);

		data.put("shortCuts", shortCuts);
//		data.put("leftPersonalized", leftPersonalized);
		forward = "leftmenu";

		return forward;
	}

	public String topmenu() throws Exception
	{
		String forward = NONE;
		DynamicObject login_token = (DynamicObject) Struts2Utils.getRequest().getSession().getAttribute(GlobalConstants.sys_login_token);
		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);
		
		UserPersonalize upl = personalizService.getUserPersonalizeByid(loginname);

		if (upl!=null && !StringToolKit.isBlank(upl.getTopmenu()))
		{
			data.put("jsonobj", upl.getTopmenu());
			return "topmenu";
		}

		RolePersonalize rpl = personalizService.getRolePersonalizeByid(loginname);

		if (rpl!=null && !StringToolKit.isBlank(rpl.getTopmenu()))
		{
			data.put("jsonobj", rpl.getTopmenu());
			return "topmenu";
		}

		Map navitems = navitemService.getnavitembyuser(loginname);
//		TopPersonalized topPersonalized = topPersonalizedServie.gettoppersonalizedbyuser(loginname);

		data.put("navitems", navitems);
//		data.put("topPersonalized", topPersonalized);
		forward = "topmenu";

		return forward;
	}

	public Map getData()
	{
		return data;
	}

	public void setData(Map data)
	{
		this.data = data;
	}

	public ShortCutService getShortCutService()
	{
		return shortCutService;
	}

	public void setShortCutService(ShortCutService shortCutService)
	{
		this.shortCutService = shortCutService;
	}

	public NavitemService getNavitemService()
	{
		return navitemService;
	}

	public void setNavitemService(NavitemService navitemService)
	{
		this.navitemService = navitemService;
	}

}