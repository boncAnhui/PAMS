package com.pams.dao;

import org.springframework.stereotype.Component;

import com.blue.ssh.core.orm.hibernate.HibernateDao;
import com.pams.entity.Relay;

@Component
public class RelayDao extends HibernateDao<Relay, String>
{

}
