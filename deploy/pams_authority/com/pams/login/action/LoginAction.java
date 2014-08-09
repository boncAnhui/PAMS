package com.pams.login.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.spec.GlobalConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.pams.login.service.LoginService;
import com.ray.xj.sgcc.irm.system.organ.organ.entity.Organ;
import com.ray.xj.sgcc.irm.system.organ.organ.service.OrganService;
import com.ray.xj.sgcc.irm.system.organ.user.entity.User;
import com.ray.xj.sgcc.irm.system.organ.user.service.UserService;

public class LoginAction extends ActionSupport
{
	Map data = new HashMap();

	private static Logger logger = LoggerFactory.getLogger(LoginAction.class);

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrganService organService;

	public String execute() throws Exception
	{
		String forward = NONE;
		String loginname = Struts2Utils.getRequest().getParameter("usr");
		String pwd = Struts2Utils.getRequest().getParameter("pws");

		boolean sign = loginService.login(loginname, pwd);
		if (!sign)
		{
			// throw new Exception("登陆不成功!");
			data.put("error", "用户名或密码不正确！");
			forward = "error";
		}
		else
		{
			DynamicObject obj = new DynamicObject();
			User user = userService.findUniqueBy("loginname", loginname);
			obj.setAttr(GlobalConstants.sys_login_user, loginname);
			obj.setAttr(GlobalConstants.sys_login_username, user.getCname());
			obj.setAttr(GlobalConstants.sys_login_userid, user.getId());
			
			Organ organ = organService.getDeptByLoginname(loginname);

			if (organ != null)
			{
				obj.setAttr(GlobalConstants.sys_login_dept, organ.getId());
				obj.setAttr(GlobalConstants.sys_login_deptname, organ.getDeptname());
			}

			// 增加屏蔽系统设置代码
			boolean issystem = loginService.issystem(loginname);

			data.put("issystem", issystem);

			Struts2Utils.getRequest().getSession().setAttribute(GlobalConstants.sys_login_token, obj);
			Timestamp nowtime = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String format = "%s 于 %s 进行了登陆；";

			logger.info(String.format(format, user.getCname(), simpleDateFormat.format(nowtime)));

			forward = "success";
		}

		return forward;
	}

	public LoginService getLoginService()
	{
		return loginService;
	}

	public void setLoginService(LoginService loginService)
	{
		this.loginService = loginService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public Map getData()
	{
		return data;
	}

	public void setData(Map data)
	{
		this.data = data;
	}

	public OrganService getOrganService()
	{
		return organService;
	}

	public void setOrganService(OrganService organService)
	{
		this.organService = organService;
	}

}