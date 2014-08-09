package com.pams.hbgl.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pams.dao.RelayDao;
import com.pams.entity.Relay;
import com.ray.app.query.generator.GeneratorService;

@Component
@Transactional
public class RelayService
{
	@Autowired
	GeneratorService generatorService;

	@Autowired
	private RelayDao relayDao;
	
	@Transactional
	public String create(Relay relay) throws Exception
	{
		relayDao.save(relay);
		return relay.getId();
	}

	@Transactional
	public Relay get(String id) throws Exception
	{
		return relayDao.get(id);
	}

	public GeneratorService getGeneratorService()
	{
		return generatorService;
	}

	public void setGeneratorService(GeneratorService generatorService)
	{
		this.generatorService = generatorService;
	}

	public RelayDao getRelayDao()
	{
		return relayDao;
	}

	public void setRelayDao(RelayDao relayDao)
	{
		this.relayDao = relayDao;
	}
	


}
