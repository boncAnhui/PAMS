package com.pams.dao;

import org.springframework.stereotype.Component;

import com.blue.ssh.core.orm.hibernate.HibernateDao;
import com.pams.entity.PlanModel;

@Component
public class PlanModelDao extends HibernateDao<PlanModel, String>
{

}
