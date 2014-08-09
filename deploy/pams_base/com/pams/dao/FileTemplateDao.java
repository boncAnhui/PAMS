package com.pams.dao;

import org.springframework.stereotype.Component;

import com.blue.ssh.core.orm.hibernate.HibernateDao;
import com.pams.entity.FileTemplate;

@Component
public class FileTemplateDao extends HibernateDao<FileTemplate, String>
{

}
