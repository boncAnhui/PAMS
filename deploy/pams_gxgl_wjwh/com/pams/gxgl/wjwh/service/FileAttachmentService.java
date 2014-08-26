package com.pams.gxgl.wjwh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.headray.core.spring.jdo.DyDaoHelper;
import com.headray.core.spring.jdo.JdbcDao;
import com.headray.framework.services.db.SQLParser;
import com.headray.framework.services.function.StringToolKit;
import com.pams.dao.FileAttachmentDao;
import com.pams.entity.FileAttachment;
import com.ray.app.query.generator.GeneratorService;

@Component
@Transactional
public class FileAttachmentService
{
	@Autowired
	GeneratorService generatorService;

	@Autowired
	private FileAttachmentDao fileattachmentDao;

	@Autowired
	private JdbcDao jdbcDao;

	@Transactional
	public String create(FileAttachment fileattachment) throws Exception
	{
		fileattachmentDao.save(fileattachment);
		return fileattachment.getId();
	}

	@Transactional
	public FileAttachment get(String id) throws Exception
	{
		return fileattachmentDao.get(id);
	}

	@Transactional
	public List<FileAttachment> find(Map<String, Object> map) throws Exception
	{
		String cclass = (String) map.get("cclass");
		String dataid = (String) map.get("dataid");
		String flowdefid = (String) map.get("flowdefid");
		String flowsno = (String) map.get("flowsno");
		String actdefid = (String) map.get("actdefid");
		String actname = (String) map.get("actname");
		String runflowkey = (String) map.get("runflowkey");
		String runactkey = (String) map.get("runactkey");
		String cno = (String) map.get("cno");
		String ctype = (String) map.get("ctype");
		String cname = (String) map.get("cname");

		List files = new ArrayList();

		StringBuffer sql = new StringBuffer();
		sql.append(" from FileAttachment a where 1 = 1 ");

		if (!StringToolKit.isBlank(cclass))
		{
			sql.append(" and a.cclass = " + SQLParser.charValue(cclass));
		}

		if (!StringToolKit.isBlank(dataid))
		{
			sql.append(" and a.dataid = " + SQLParser.charValue(dataid));
		}

		if (!StringToolKit.isBlank(runflowkey))
		{
			sql.append(" and a.runflowkey = " + SQLParser.charValue(runflowkey));
		}

		if (!StringToolKit.isBlank(runactkey))
		{
			sql.append(" and a.runactkey = " + SQLParser.charValue(runactkey));
		}

		if (!StringToolKit.isBlank(cno))
		{
			sql.append(" and a.cno = " + SQLParser.charValue(cno));
		}

		return fileattachmentDao.find(sql.toString());
	}

	// 根据流程活动模板查找相应文档附件
	@Transactional
	public List<Map> find_by_acttemplate(Map<String, Object> map) throws Exception
	{
		List files = new ArrayList();

		String cclass = (String) map.get("cclass");
		String dataid = (String) map.get("dataid");
		String flowdefid = (String) map.get("flowdefid");
		String flowsno = (String) map.get("flowsno");
		String actdefid = (String) map.get("actdefid");
		String actname = (String) map.get("actname");
		String runflowkey = (String) map.get("runflowkey");
		String runactkey = (String) map.get("runactkey");
		String ctype = (String) map.get("ctype");
		String cname = (String) map.get("cname");

		StringBuffer sql = new StringBuffer();
		sql.append("  select a.*, b.num ").append("\n");
		sql.append("    from t_app_filetemplate a ").append("\n");
		sql.append("    left join ").append("\n");
		sql.append("    ( ").append("\n");
		sql.append("  select b.flowdefid, b.actdefid, b.cno, count(b.sfilename) num ").append("\n");
		sql.append("    from t_app_fileattachment b ").append("\n");
		sql.append("   where 1 = 1 ").append("\n");

		if (!StringToolKit.isBlank(runflowkey))
		{
			sql.append(" and b.runflowkey = " + SQLParser.charValue(runflowkey));
		}

		if (!StringToolKit.isBlank(runactkey))
		{
			sql.append(" and b.runactkey = " + SQLParser.charValue(runactkey));
		}

		sql.append("   group by b.flowdefid, b.actdefid, b.cno ").append("\n");
		sql.append("     ) b ").append("\n");
		sql.append("     on a.actdefid = b.actdefid ").append("\n");
		sql.append("    and a.flowdefid = b.flowdefid ").append("\n");
		sql.append("    and a.cno = b.cno ").append("\n");
		sql.append("  ").append("\n");

		sql.append("  where 1 = 1 ");

		if (!StringToolKit.isBlank(flowdefid))
		{
			sql.append(" and a.flowdefid = " + SQLParser.charValue(flowdefid));
		}

		if (!StringToolKit.isBlank(actdefid))
		{
			sql.append(" and a.actdefid = " + SQLParser.charValue(actdefid));
		}

		if (!StringToolKit.isBlank(ctype))
		{
			sql.append(" and a.ctype = " + SQLParser.charValue(ctype));
		}
		sql.append(" order by a.cno ");

		JdbcTemplate jt = jdbcDao.getJdbcTemplate();
		files = DyDaoHelper.query(jt, sql.toString());

		return files;
	}
	
	public void delete(String id) throws Exception
	{
		fileattachmentDao.delete(id);
	}

	public GeneratorService getGeneratorService()
	{
		return generatorService;
	}

	public void setGeneratorService(GeneratorService generatorService)
	{
		this.generatorService = generatorService;
	}

	public FileAttachmentDao getFileattachmentDao()
	{
		return fileattachmentDao;
	}

	public void setFileattachmentDao(FileAttachmentDao fileattachmentDao)
	{
		this.fileattachmentDao = fileattachmentDao;
	}

}
