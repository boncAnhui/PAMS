package com.ray.xj.sgcc.irm.portal.personalize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.headray.framework.services.db.SQLParser;
import com.ray.xj.sgcc.irm.portal.personalize.dao.RolePersonalizeDao;
import com.ray.xj.sgcc.irm.portal.personalize.dao.UserPersonalizeDao;
import com.ray.xj.sgcc.irm.portal.personalize.entity.RolePersonalize;
import com.ray.xj.sgcc.irm.portal.personalize.entity.UserPersonalize;

@Component
@Transactional
public class PersonalizService
{
	@Autowired
	private RolePersonalizeDao rolePersonalizeDao;

	@Autowired
	private UserPersonalizeDao userPersonalizeDao;

	public RolePersonalize getRolePersonalizeByid(String userid) throws Exception
	{
		// rolePersonalizeDao.createQuery("", "");

		StringBuffer sql = new StringBuffer();
		sql.append(" select a  ");
		sql.append("   from RolePersonalize a, UserRole b");
		sql.append("  where 1 = 1 ");
		sql.append("    and a.roleid = b.rname ");
		sql.append("    and b.userid = " + SQLParser.charValue(userid));

		return rolePersonalizeDao.findUnique(sql.toString());
	}

	public UserPersonalize getUserPersonalizeByid(String userid) throws Exception
	{
		return userPersonalizeDao.findUniqueBy("userid", userid);
	}

	public void updatetopmenubyuserid(String userid, String json) throws Exception
	{
		UserPersonalize up = userPersonalizeDao.findUniqueBy("userid", userid);

		if (up == null)
		{
			up = new UserPersonalize();
			up.setUserid(userid);
		}
		up.setTopmenu(json);
		userPersonalizeDao.save(up);
		
	}
	
	public void updatehomemenubyuserid(String userid, String json) throws Exception
	{
		UserPersonalize up = userPersonalizeDao.findUniqueBy("userid", userid);

		if (up == null)
		{
			up = new UserPersonalize();
			up.setUserid(userid);
		}
		up.setMainsubject(json);
		userPersonalizeDao.save(up);

	}

	public void updateleftmenubyuserid(String userid, String json) throws Exception
	{
		UserPersonalize up = userPersonalizeDao.findUniqueBy("userid", userid);

		if (up == null)
		{
			up = new UserPersonalize();
			up.setUserid(userid);
		}
		up.setLeftmenu(json);
		userPersonalizeDao.save(up);
	}

	public void deletepersonmenu(String userid,String ctype) throws Exception
	{
		
		
		UserPersonalize up = userPersonalizeDao.findUniqueBy("userid", userid);
		if(ctype.equals("left")){
			up.setLeftmenu("");
		}else if (ctype.equals("top")){
			up.setTopmenu("");
		}else if(ctype.equals("home")){
			up.setMainsubject("");
		}
		userPersonalizeDao.save(up);
		
	}

}
