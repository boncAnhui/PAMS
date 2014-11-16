package com.pams.dao;

import org.springframework.stereotype.Component;

import com.blue.ssh.core.orm.hibernate.HibernateDao;
import com.pams.entity.Plan;

@Component
public class PlanDao extends HibernateDao<Plan, String>
{

}
