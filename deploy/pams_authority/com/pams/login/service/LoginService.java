package com.pams.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ray.xj.sgcc.irm.system.author.userrole.dao.UserRoleDao;
import com.ray.xj.sgcc.irm.system.organ.user.entity.User;
import com.ray.xj.sgcc.irm.system.organ.user.service.UserService;

@Component
@Transactional
public class LoginService
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleDao userRoleDao;	

	public boolean login(String username, String password) throws Exception
	{
		boolean sign = false;
		
		User user = userService.findUniqueBy("loginname", username);
		
		if(user==null)
		{
			return sign;
		}
		
		if (password.equals(user.getPassword())&&!"N".equals(user.getIsusing()))
		{
			sign = true;
		}
		return sign;
	}
	
	// 判断是否具有系统设置功能
	// 2012/09/05
	// 蒲剑
	public boolean issystem(String loginname) throws Exception
	{
		boolean sign = false;
		
		long num = 0;

		// 系统管理员具有权限
		if ("admin".equals(loginname))
		{
			sign = true;
			return sign;
		}

		// 应用管理员具有权限
		num = 0;
		num = (Long) userRoleDao.findUnique(" select count(*) from UserRole a where 1 = 1 and a.rname = ? and userid = ? ", "SYSTEM", loginname);

		if (num > 0)
		{
			sign = true;
			return sign;
		}

		// 配置子系统管理员具有权限
		num = 0;
		num = (Long) userRoleDao.findUnique(" select count(*) from UserRole a where 1 = 1 and a.rname = ? and userid = ? ", "PZGLY", loginname);

		if (num > 0)
		{
			sign = true;
			return sign;
		}
		
		// 项目子系统管理员具有权限
		num = 0;
		num = (Long) userRoleDao.findUnique(" select count(*) from UserRole a where 1 = 1 and a.rname = ? and userid = ? ", "XMGLY", loginname);

		if (num > 0)
		{
			sign = true;
			return sign;
		}	
		
		// 任务子系统管理员具有权限
		num = 0;
		num = (Long) userRoleDao.findUnique(" select count(*) from UserRole a where 1 = 1 and a.rname = ? and userid = ? ", "RWGLY", loginname);

		if (num > 0)
		{
			sign = true;
			return sign;
		}			
		
		// 业务活动子系统管理员具有权限
		num = 0;
		num = (Long) userRoleDao.findUnique(" select count(*) from UserRole a where 1 = 1 and a.rname = ? and userid = ? ", "YWSJXTGLY", loginname);

		if (num > 0)
		{
			sign = true;
			return sign;
		}				
		
		// 备品备件子系统管理员具有权限
		num = 0;
		num = (Long) userRoleDao.findUnique(" select count(*) from UserRole a where 1 = 1 and a.rname = ? and userid = ? ", "BPBJXTGLY", loginname);

		if (num > 0)
		{
			sign = true;
			return sign;
		}	
		
		return sign;
		
	}
	
	// 判断是否具有电话外接设置权限
	public boolean getoutStatus(String loginname)
	{
		boolean sign = false;
		
		long num = 0;

		// 系统管理员具有权限
		if ("admin".equals(loginname))
		{
			sign = true;
			return sign;
		}

		// 应用管理员具有权限
		num = 0;
		num = (Long) userRoleDao.findUnique(" select count(*) from UserRole a where 1 = 1 and a.rname = ? and userid = ? ", "SYSTEM", loginname);

		if (num > 0)
		{
			sign = true;
			return sign;
		}
		
		return sign;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}
	
}
