package com.pams.dao;

import org.springframework.stereotype.Component;

import com.blue.ssh.core.orm.hibernate.HibernateDao;
import com.pams.entity.KnowledgeScope;

@Component
public class KnowledgeScopeDao extends HibernateDao<KnowledgeScope, String>
{

}
