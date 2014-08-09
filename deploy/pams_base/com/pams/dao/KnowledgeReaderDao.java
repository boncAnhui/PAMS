package com.pams.dao;

import org.springframework.stereotype.Component;

import com.blue.ssh.core.orm.hibernate.HibernateDao;
import com.pams.entity.KnowledgeReader;

@Component
public class KnowledgeReaderDao extends HibernateDao<KnowledgeReader, String>
{

}
