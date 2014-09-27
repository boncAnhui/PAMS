package com.pams.dao;

import org.springframework.stereotype.Component;

import com.blue.ssh.core.orm.hibernate.HibernateDao;
import com.pams.entity.MarketPower;

@Component
public class MarketPowerDao extends HibernateDao<MarketPower, String>
{

}
