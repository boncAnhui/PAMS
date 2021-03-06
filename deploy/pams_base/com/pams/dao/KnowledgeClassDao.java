package com.pams.dao;

import org.springframework.stereotype.Component;

import com.blue.ssh.core.orm.hibernate.HibernateDao;
import com.pams.entity.KnowledgeClass;

@Component
public class KnowledgeClassDao extends HibernateDao<KnowledgeClass, String>
{

}
