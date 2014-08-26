package com.pams.gxgl.wjwh.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.headray.framework.services.db.SQLParser;
import com.headray.framework.services.function.StringToolKit;
import com.pams.dao.FileTemplateDao;
import com.pams.entity.FileTemplate;
import com.ray.app.query.generator.GeneratorService;

@Component
@Transactional
public class FileTemplateService
{
	@Autowired
	GeneratorService generatorService;

	@Autowired
	private FileTemplateDao filetemplateDao;
	
	@Transactional
	public String create(FileTemplate filetemplate) throws Exception
	{
		filetemplateDao.save(filetemplate);
		return filetemplate.getId();
	}

	@Transactional
	public FileTemplate get(String id) throws Exception
	{
		return filetemplateDao.get(id);
	}
	
	@Transactional
	public FileTemplate locate_by(Map<String, Object> map) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		String flowdefid = (String)map.get("flowdefid");
		String actdefid = (String)map.get("actdefid");
		String cno = (String)map.get("cno");
		String cclass = (String)map.get("cclass");
		String flowsno = (String)map.get("flowsno");
		
		sql.append(" from FileTemplate a where 1 = 1 ");
		
		if(!StringToolKit.isBlank(flowdefid))
		{
			sql.append(" and flowdefid = " + SQLParser.charValue(flowdefid));
		}
		if(!StringToolKit.isBlank(actdefid))
		{
			sql.append(" and actdefid = " + SQLParser.charValue(actdefid));
		}
		if(!StringToolKit.isBlank(cno))
		{
			sql.append(" and cno = " + SQLParser.charValue(cno));
		}
		if(!StringToolKit.isBlank(cclass))
		{
			sql.append(" and cclass = " + SQLParser.charValue(cclass));
		}
		if(!StringToolKit.isBlank(flowsno))
		{
			sql.append(" and flowsno = " + SQLParser.charValue(flowsno));
		}			
		
		return filetemplateDao.findUnique(sql.toString());
	}	

	public GeneratorService getGeneratorService()
	{
		return generatorService;
	}

	public void setGeneratorService(GeneratorService generatorService)
	{
		this.generatorService = generatorService;
	}

	public FileTemplateDao getFileTemplateDao()
	{
		return filetemplateDao;
	}

	public void setFileTemplateDao(FileTemplateDao filetemplateDao)
	{
		this.filetemplateDao = filetemplateDao;
	}
	


}
