package com.pams.gxgl.rep.zxqk.gzl.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.ssh.core.action.SimpleAction;
import com.blue.ssh.core.utils.web.struts2.Struts2Utils;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.pams.gxgl.rep.zxqk.gxd.service.TabGXD_WFBZS;
import com.pams.gxgl.rep.zxqk.gxd.service.TabGXD_WFBZS_CSZS;
import com.pams.gxgl.rep.zxqk.gxd.service.TabGXD_WFBZS_ZCZS;
import com.pams.gxgl.rep.zxqk.gxd.service.TabGXD_YFBZS;
import com.pams.gxgl.rep.zxqk.gxd.service.TabGXD_YFBZS_CSFBZS;
import com.pams.gxgl.rep.zxqk.gxd.service.TabGXD_YFBZS_ZCFBZS;
import com.pams.gxgl.rep.zxqk.gzl.service.TabGZLTJ;
import com.ray.app.chart.report.dao.ReportDao;
import com.ray.app.query.service.QueryService;

public class RepAction extends SimpleAction
{
	private String _searchname;

	@Autowired
	private QueryService queryService;

	@Autowired
	ReportDao reportDao;

	public RepAction()
	{
	}


	public String tab_gzltj() throws Exception
	{
		String runflowkey = Struts2Utils.getRequest().getParameter("runflowkey");

		DynamicObject obj = new DynamicObject();
		obj.setAttr("runflowkey", runflowkey);
		
		TabGZLTJ tabGZLTJ = new TabGZLTJ();
		tabGZLTJ.setJdbcTemplate(reportDao.getJdbcTemplate());
		List datas = tabGZLTJ.execute(obj);

		data.put("datas", datas);
		arg.put("runflowkey", runflowkey);
		return "tab_gzltj";
	}
}