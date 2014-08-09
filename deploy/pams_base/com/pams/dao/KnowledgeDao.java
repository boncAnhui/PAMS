package com.pams.dao;

import org.springframework.stereotype.Component;

import com.blue.ssh.core.orm.hibernate.HibernateDao;
import com.pams.entity.Knowledge;

@Component
public class KnowledgeDao extends HibernateDao<Knowledge, String>
{

}
