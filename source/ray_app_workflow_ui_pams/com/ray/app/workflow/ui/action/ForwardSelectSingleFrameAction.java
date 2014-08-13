package com.ray.app.workflow.ui.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.action.SimpleAction;
import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.ray.app.workflow.enginee.DemandManager;
import com.ray.app.workflow.enginee.WorkFlowEngine;

public class ForwardSelectSingleFrameAction extends SimpleAction
{
	@Autowired
	private WorkFlowEngine workFlowEngine;

	public String execute() throws Exception
	{
		HttpServletRequest req = Struts2Utils.getRequest();
		HttpServletResponse resp = Struts2Utils.getResponse();
		
		String runactkey = req.getParameter("runactkey");
		String tableid = req.getParameter("tableid");
		
		DemandManager demandManager = workFlowEngine.getDemandManager();
		DynamicObject ract = demandManager.getRAct(runactkey, tableid);
		String actdefid = ract.getFormatAttr("actdefid");
		String dataid = ract.getFormatAttr("dataid");
		String flowdefid = demandManager.getBFlowByBAct(actdefid).getFormatAttr("id");
		
		DynamicObject bact = demandManager.getBAct(actdefid);
		
		// 查询可转发的路由
		List routes = workFlowEngine.getDemandManager().getForwardRoutes(actdefid);

		List endacts = new ArrayList();
		for(int i=0;i<routes.size();i++)
		{
			String endactdefid = ((DynamicObject)routes.get(i)).getFormatAttr("endactid");
			DynamicObject obj_bact = demandManager.getBAct(endactdefid);
			endacts.add(obj_bact);
		}
		
		data.put("bact", bact);
		data.put("ract", ract);	
		data.put("routes", routes);
		data.put("endacts", endacts);
		
		arg.put("flowdefid", flowdefid);
		arg.put("runactkey", runactkey);
		arg.put("tableid", tableid);
		arg.put("dataid", dataid);
		arg.put("actdefid", actdefid);

		return "forwardselectsingleframe";
	}
}
