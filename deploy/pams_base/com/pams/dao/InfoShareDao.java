package com.pams.dao;

import org.springframework.stereotype.Component;

import com.blue.ssh.core.orm.hibernate.HibernateDao;
import com.pams.entity.InfoShare;

@Component
public class InfoShareDao extends HibernateDao<InfoShare, String>
{

}
