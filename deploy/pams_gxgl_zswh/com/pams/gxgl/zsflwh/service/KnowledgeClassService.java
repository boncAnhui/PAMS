package com.pams.gxgl.zsflwh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.headray.framework.services.function.StringToolKit;
import com.headray.framework.services.function.Types;
import com.pams.dao.KnowledgeClassDao;
import com.pams.dao.KnowledgeClassRelationDao;
import com.pams.entity.KnowledgeClass;
import com.ray.app.query.generator.GeneratorService;
import com.ray.xj.sgcc.irm.system.author.userrole.dao.UserRoleDao;

@Component
@Transactional
public class KnowledgeClassService
{
	@Autowired
	private KnowledgeClassDao knowledgeClassDao;

	@Autowired
	private KnowledgeClassRelationDao knowledgeClassRelationDao;

	@Autowired
	private GeneratorService generatorService;

	@Autowired
	private UserRoleDao userRoleDao;

	public void findUnique(String propertyName, String value)
	{
		knowledgeClassDao.findUnique(propertyName, value);
	}

	public void findBy(String propertyName, String value)
	{
		knowledgeClassDao.findBy(propertyName, value);
	}

	@Transactional(readOnly = true)
	public List<KnowledgeClass> tree() throws Exception
	{
		List<KnowledgeClass> knowledgeclasses = knowledgeClassDao.findBy("supid", "R0", Order.asc("ordernum"));
		return knowledgeclasses;
	}

	public List<KnowledgeClass> treechild(String supid) throws Exception
	{
		List<KnowledgeClass> knowledgeclasses = knowledgeClassDao.findBy("supid", supid, Order.asc("ordernum"));

		return knowledgeclasses;
	}

	public List<KnowledgeClass> treetask(String cclassid) throws Exception
	{
		List<KnowledgeClass> knowledgeclasses = knowledgeClassDao.find(" from KnowledgeClass where cname='任务知识库' and supid='R0' ");
		return knowledgeclasses;
	}

	public String get_browse_sql()
	{
		String hql = "from KnowledgeClass kc where 1=1 and kc.id = :id and kc.supid = :supid";
		return hql;
	}

	public void insert(KnowledgeClass knowledgeClass) throws Exception
	{
		createKnowledgeClass(knowledgeClass);
	}

	// 查找当前文件夹所在文件库
	public String get_folder_of_libary(String id)
	{
		String cno = knowledgeClassDao.get(id).getCno();
		StringBuffer sql = new StringBuffer();
		sql.append(" from KnowledgeClass a where 1 = 1 and a.cno like '" + cno.substring(0, 8) + "%' and foldertype = '文件库' ");

		KnowledgeClass aknowledgeclass = (KnowledgeClass) knowledgeClassDao.findUnique(sql.toString());
		String libaryid = aknowledgeclass.getId();
		return libaryid;
	}

	public void createKnowledgeClass(KnowledgeClass knowledgeClass) throws Exception
	{
		String cno = gen_cno(knowledgeClass.getSupid());
		knowledgeClass.setCno(cno);
		knowledgeClass.setIslast("0");

		if (StringToolKit.isBlank(knowledgeClass.getFoldertype()))
		{
			String foldertype = checkfoldertype(knowledgeClass.getSupid());
			knowledgeClass.setFoldertype(foldertype);
		}

		knowledgeClassDao.save(knowledgeClass);

		if (!"R0".equals(knowledgeClass.getSupid()))
		{
			// 更新上级节点islast属性
			knowledgeClassDao.batchExecute(" update KnowledgeClass set islast = 1 where 1 = 1 and id = ?", knowledgeClass.getSupid());
		}
	}

	public String checkfoldertype(String supid) throws Exception
	{
		// 检查下级文件库、柜、夹类型
		String foldertype = "文件库";
		if ("R0".equals(supid))
		{
			foldertype = "文件库";
		}
		else
		{
			KnowledgeClass knowledgeClassSup = knowledgeClassDao.get(supid);
			String supcno = knowledgeClassSup.getCno();
			if ((supcno.length() / 4) == 2)
			{
				foldertype = "文件柜";
			}
			else
			{
				foldertype = "文件夹";
			}
		}
		return foldertype;
	}

	public String gen_cno(String supid)
	{
		String cno = new String();
		if (!supid.equals("R0"))
		{
			KnowledgeClass knowledgeClass = knowledgeClassDao.get(supid);
			cno = knowledgeClass.getCno();
		}
		else
		{
			cno = "0000";
		}

		Map map = new HashMap();
		map.put("field_names", new String[]
		{ "cno", "supid" });
		map.put("field_values", new String[]
		{ cno, supid });

		String csql = " select substring(max(cno),length(max(cno))-3, 4) as cno from KnowledgeClass where supid = :supid";
		String express = "$cno####";

		map.put("csql", csql);
		map.put("express", express);

		return generatorService.getNextValue(map);
	}

	public void delete(String[] ids) throws Exception
	{
		if (ids == null)
		{
			return;
		}

		if (ids.length == 0)
		{
			return;
		}

		int num = ids.length;
		for (int i = 0; i < num; i++)
		{
			delete(ids[i]);
		}
	}

	public void delete(String id) throws Exception
	{
		KnowledgeClass knowledgeclass = knowledgeClassDao.get(id);
		deleteKnowledgeClass(knowledgeclass);
	}

	public void updateorder(String[] ids)
	{
		for (int i = 0; i < ids.length; i++)
		{
			KnowledgeClass knowledgeClasses = knowledgeClassDao.get(ids[i]);
			knowledgeClasses.setOrdernum(Types.parseString(i));
			knowledgeClassDao.save(knowledgeClasses);
		}
	}

	public KnowledgeClass getKnowledgeClass(String id) throws Exception
	{
		return knowledgeClassDao.get(id);
	}

	public List<KnowledgeClass> getAllKnowledgeClass() throws Exception
	{
		return knowledgeClassDao.getAll();
	}

	public void save(KnowledgeClass entity) throws Exception
	{
		knowledgeClassDao.save(entity);
	}

	public void deleteKnowledgeClass(KnowledgeClass knowledgeclass) throws Exception
	{
		// 系统分类不允许删除
		// 具有下级分类不允许删除
		// 分类下有文档不允许删除

		if (knowledgeclass == null)
		{
			return;
		}

		String issys = knowledgeclass.getIssys();
		String supid = knowledgeclass.getSupid();

		if ("Y".equals(issys))
		{
			throw new Exception(knowledgeclass.getCname() + "该分类为系统分类，不允许删除！");
		}

		List<KnowledgeClass> knowledgeClasses = knowledgeClassDao.findBy("supid", knowledgeclass.getId());
		if (knowledgeClasses.size() > 0)
		{
			throw new Exception(knowledgeclass.getCname() + "该分类有下级分类，不能删除！");
		}

		int count = ((Number) knowledgeClassRelationDao.findUnique(" select count(*) from KnowledgeClassRelation a where 1 = 1 and a.classid = ? ", knowledgeclass.getId())).intValue();
		if (count > 0)
		{
			throw new Exception(knowledgeclass.getCname() + "该分类有知识文档，不能删除！");
		}

		knowledgeClassDao.delete(knowledgeclass);

		// 更新上级节点islast属性
		if ("R0".equals(supid))
		{
			return;
		}

		int nums = ((Long) knowledgeClassDao.findUnique(" select count(id) as nums from KnowledgeClass where 1 = 1 and supid = ? ", supid)).intValue();
		if (nums > 0)
		{
			knowledgeClassDao.batchExecute(" update KnowledgeClass set islast = 1 where 1 = 1 and id = ?", knowledgeclass.getSupid());
		}
		else
		{
			knowledgeClassDao.batchExecute(" update KnowledgeClass set islast = 0 where 1 = 1 and id = ?", knowledgeclass.getSupid());
		}
	}

	public void deleteKnowledgeClass(String[] ids) throws Exception
	{
		for (int i = 0; i < ids.length; i++)
		{
			deleteKnowledgeClass(ids[i]);
		}
	}

	public void deleteKnowledgeClass(String id) throws Exception
	{
		KnowledgeClass entity = knowledgeClassDao.get(id);
		deleteKnowledgeClass(entity);
	}

	public KnowledgeClass getRootOfBid(String bid) throws Exception
	{
		KnowledgeClass knowledgeclass = knowledgeClassDao.findUnique(" from KnowledgeClass where 1 = 1 and bid = ? ", bid);
		// System.out.println(knowledgeclass.getId() + ":" +
		// knowledgeclass.getCname());
		return knowledgeclass;
	}

	public String getCclassallname(String knowledgeclassid) throws Exception
	{
		KnowledgeClass knowledgeclass = knowledgeClassDao.get(knowledgeclassid);
		String cclassallname = getSupcname(knowledgeclass.getSupid());
		if (!StringToolKit.isBlank(cclassallname))
		{
			cclassallname = knowledgeclass.getCname() + "/" + cclassallname;
		}
		else
		{
			cclassallname = knowledgeclass.getCname();
		}
		return cclassallname;
	}

	public String getAllname(String knowledgeclassid) throws Exception
	{

		KnowledgeClass knowledgeclass = knowledgeClassDao.get(knowledgeclassid);
		String cclassallname = getSupcname(knowledgeclass.getSupid());
		if (knowledgeclass.getCname() != null)
		{
			cclassallname = knowledgeclass.getCname() + "/" + cclassallname + "知识分类";
		}
		else
		{
			cclassallname = "知识分类";
		}
		return cclassallname;
	}

	public String getSupcname(String knowledgeclassid) throws Exception
	{

		KnowledgeClass knowledgeclass = knowledgeClassDao.findUnique(" from KnowledgeClass where 1=1 and id=?", knowledgeclassid);
		String cclassallname = "";
		if (knowledgeclass != null)
		{
			cclassallname = getSupcname(knowledgeclass.getSupid());
			if (StringToolKit.isBlank(cclassallname))
			{
				cclassallname = knowledgeclass.getCname();
			}
			else
			{
				cclassallname = knowledgeclass.getCname() + "/" + cclassallname;
			}
		}

		return cclassallname;
	}

	public KnowledgeClass getKnowledgeClassByBid(String bid) throws Exception
	{
		KnowledgeClass knowledgeClass = knowledgeClassDao.findUnique(" from KnowledgeClass where 1=1 and bid=?", bid);

		return knowledgeClass;
	}

	// 能否保存
	public boolean issave(Map map)
	{
		boolean sign = false;

		// 业务数据标识
		// 用户名
		String id = (String) map.get("id");
		String loginname = (String) map.get("loginname");

		long num = 0;

		KnowledgeClass knowledgeClass = knowledgeClassDao.get(id);

		if (loginname.equals(knowledgeClass.getCreateuserid()))
		{
			sign = true;
			return sign;
		}

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

		// 知识库管理员具有权限
		num = 0;
		num = (Long) userRoleDao.findUnique(" select count(*) from UserRole a where 1 = 1 and a.rname = ? and userid = ? ", "ZSKGLY", loginname);

		if (num > 0)
		{
			sign = true;
			return sign;
		}

		return sign;
	}

	// 能否新建下级目录
	public boolean iscreate(Map map)
	{
		boolean sign = false;

		// 业务数据标识
		// 用户名
		String id = (String) map.get("id");
		String loginname = (String) map.get("loginname");

		long num = 0;

		if (!"R0".equals(id))
		{
			KnowledgeClass knowledgeClass = knowledgeClassDao.get(id);

			if (loginname.equals(knowledgeClass.getCreateuserid()))
			{
				sign = true;
				return sign;
			}
		}
		else
		{
			// 知识库作者具有权限
			num = 0;
			num = (Long) userRoleDao.findUnique(" select count(*) from UserRole a where 1 = 1 and a.rname = ? and userid = ? ", "ZSKZZ", loginname);

			if (num > 0)
			{
				sign = true;
				return sign;
			}

		}

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

		// 知识库管理员具有权限
		num = 0;
		num = (Long) userRoleDao.findUnique(" select count(*) from UserRole a where 1 = 1 and a.rname = ? and userid = ? ", "ZSKGLY", loginname);

		if (num > 0)
		{
			sign = true;
			return sign;
		}

		return sign;
	}

	// 能否删除下级目录
	public boolean isdelete(Map map)
	{
		boolean sign = false;

		// 业务数据标识
		// 用户名
		String id = (String) map.get("id");
		String loginname = (String) map.get("loginname");

		long num = 0;
		if (!"R0".equals(id))
		{
			KnowledgeClass knowledgeClass = knowledgeClassDao.get(id);

			if (loginname.equals(knowledgeClass.getCreateuserid()))
			{
				sign = true;
				return sign;
			}
		}
		else
		{
			// 知识库作者具有权限
			num = 0;
			num = (Long) userRoleDao.findUnique(" select count(*) from UserRole a where 1 = 1 and a.rname = ? and userid = ? ", "ZSKZZ", loginname);

			if (num > 0)
			{
				sign = true;
				return sign;
			}

		}

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

		// 知识库管理员具有权限
		num = 0;
		num = (Long) userRoleDao.findUnique(" select count(*) from UserRole a where 1 = 1 and a.rname = ? and userid = ? ", "ZSKGLY", loginname);

		if (num > 0)
		{
			sign = true;
			return sign;
		}

		return sign;
	}

	public String getKnowledgeClassByCno(String cclassno)
	{
		String cclassid = null;
		KnowledgeClass knowledgeClass = knowledgeClassDao.findUniqueBy("cno", cclassno);
		if (knowledgeClass == null)
		{
			cclassid = "4028811b399016c601399032e360000a";
		}
		else
		{
			cclassid = knowledgeClass.getId();
		}
		return cclassid;
	}

	public KnowledgeClassDao getKnowledgeClassDao()
	{
		return knowledgeClassDao;
	}

	public void setKnowledgeClassDao(KnowledgeClassDao knowledgeClassDao)
	{
		this.knowledgeClassDao = knowledgeClassDao;
	}

	public GeneratorService getGeneratorService()
	{
		return generatorService;
	}

	public void setGeneratorService(GeneratorService generatorService)
	{
		this.generatorService = generatorService;
	}

}
