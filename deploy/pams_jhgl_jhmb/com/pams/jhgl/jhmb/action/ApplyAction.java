package com.pams.jhgl.jhmb.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.action.ActionSessionHelper;
import com.blue.ssh.core.action.SimpleAction;
import com.blue.ssh.core.orm.Page;
import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.pams.jhgl.jhmb.service.PlanModelService;
import com.ray.app.query.action.QueryActionHelper;
import com.ray.app.query.entity.Search;
import com.ray.app.query.service.QueryService;
import com.ray.app.workflow.enginee.WorkFlowEngine;
import com.ray.app.workflow.spec.GlobalConstants;

public class ApplyAction extends SimpleAction
{
	private String _searchname;

	@Autowired
	private QueryService queryService;

	@Autowired
	private WorkFlowEngine workFlowEngine;
	
	@Autowired
	private PlanModelService planmodelService;
	
	public String browse() throws Exception
	{
		QueryActionHelper helper = new QueryActionHelper();

		arg.putAll(helper.mockArg(_searchname, queryService));

		Map amap = new HashMap();
		amap = (HashMap) ((HashMap) arg).clone();

		Search search = (Search) BeanUtils.cloneBean(queryService.findUniqueByOfSearch("searchname", _searchname));
		search.setMysql(planmodelService.get_browse_sql(amap));

		Page page = helper.mockJdbcPage(search, queryService);
		Map vo = helper.mockVO(_searchname, queryService);

		data.put("vo", vo);
		data.put("page", page);

		return "browse";
	}

	public String get_searchname()
	{
		return _searchname;
	}

	public void set_searchname(String _searchname)
	{
		this._searchname = _searchname;
	}

	public QueryService getQueryService()
	{
		return queryService;
	}

	public void setQueryService(QueryService queryService)
	{
		this.queryService = queryService;
	}

	public WorkFlowEngine getWorkFlowEngine()
	{
		return workFlowEngine;
	}

	public void setWorkFlowEngine(WorkFlowEngine workFlowEngine)
	{
		this.workFlowEngine = workFlowEngine;
	}

	public PlanModelService getPlanmodelService()
	{
		return planmodelService;
	}

	public void setPlanmodelService(PlanModelService planmodelService)
	{
		this.planmodelService = planmodelService;
	}
	
	

}
