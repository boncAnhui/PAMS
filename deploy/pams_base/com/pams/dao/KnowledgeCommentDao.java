package com.pams.dao;

import org.springframework.stereotype.Component;

import com.blue.ssh.core.orm.hibernate.HibernateDao;
import com.pams.entity.KnowledgeComment;

@Component
public class KnowledgeCommentDao extends HibernateDao<KnowledgeComment, String>
{

}
