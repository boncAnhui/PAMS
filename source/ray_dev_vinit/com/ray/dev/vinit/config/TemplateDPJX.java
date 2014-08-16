package com.ray.dev.vinit.config;

import java.util.ArrayList;
import java.util.List;

public class TemplateDPJX
{
	public static List get_dpjx()
	{
		List<String[]> items = new ArrayList();
		items.addAll(get_dpjx_jbsx_all());
		items.addAll(get_dpjx_gjsx_all());
		items.addAll(get_dpjx_glsx_all());
		return items;
	}
	
	// 全体基本属性
	public static List get_dpjx_jbsx_all()
	{
		List<String[]> items = new ArrayList();
		

		items.add(new String[]{"设备序列号","sbxlh","","",""});
		items.add(new String[]{"设备名称","sbmc","","",""});	
		items.add(new String[]{"俗称","sc","","",""});
		items.add(new String[]{"ERP设备号","erpsbh","","",""});
		items.add(new String[]{"资产编号","zcbh","","",""});			
		items.add(new String[]{"国网编号","gwbh","","",""});
		items.add(new String[]{"设备类型","sblx","","",""});
		items.add(new String[]{"内外网标识","nwwbs","","",""});	
		items.add(new String[]{"设备品牌","sbpp","","",""});			
		items.add(new String[]{"设备型号","sbxh","","",""});
		items.add(new String[]{"生产厂商","sccs","","",""});
		items.add(new String[]{"供应商","gys","","",""});
		items.add(new String[]{"服务商","fws","","",""});
		items.add(new String[]{"所属项目","ssxm","","",""});
		items.add(new String[]{"合同金额","htje","","",""});
		items.add(new String[]{"购买日期","gmrq","","",""});
		items.add(new String[]{"投运日期","tyrq","","",""});
		items.add(new String[]{"出保日期","cbrq","","",""});
		items.add(new String[]{"安全等级","aqdj","","",""});
		items.add(new String[]{"服务级别","fwjb","","",""});
		items.add(new String[]{"所属地域","ssdy","","",""});
		items.add(new String[]{"所属机房","ssjf","","",""});
		items.add(new String[]{"机柜位置","jgwz","","",""});
		items.add(new String[]{"运行状态","yxzt","","",""});
		items.add(new String[]{"备注","bz","","",""});
		items.add(new String[]{"附件","fj","","",""});
		
		return items;
	}

	// 获取全部固件属性
	public static List<String[]> get_dpjx_gjsx_all()
	{
		List<String[]> items = new ArrayList();
		items.addAll(get_dpjx_gjsx());
		items.addAll(get_dpjx_gjsx_dy());
		items.addAll(get_dpjx_gjsx_jhj());
		items.addAll(get_dpjx_gjsx_gl());

		return items;
	}
	// 获取固件属性（简单属性）
	public static List get_dpjx_gjsx()
	{
		List<String[]> items = new ArrayList();
		
		// 固件属性
		items.add(new String[]{"设备规格","sbgg","","",""});
		items.add(new String[]{"机架高度","jjgd","","",""});	
		items.add(new String[]{"刀片数量","dpsl","","",""});
		items.add(new String[]{"机箱风扇模块数量","jxfsmksl","","",""});		
		items.add(new String[]{"电源模块数量","dymksl","","",""});
		items.add(new String[]{"交换机模块数量","jhjmksl","","",""});
		items.add(new String[]{"管理模块数量","glmksl","","",""});
		items.add(new String[]{"设备净重","sbjz","","",""});	
		
		return items;
	}
	// 获取固件属性（电源子项）
	public static List get_dpjx_gjsx_dy()
	{
		List<String[]> items = new ArrayList();
		
		// 固件属性.电源
		items.add(new String[]{"电源","dy","1","",""});
		
		items.add(new String[]{"插槽编号","ccbh","2","",""});
		items.add(new String[]{"型号","xh","2","",""});		
		items.add(new String[]{"功率","gl","2","",""});
		items.add(new String[]{"接口类型","jklx","2","",""});
		
		return items;
	}
	// 获取固件属性（交换机子项）
	public static List get_dpjx_gjsx_jhj()
	{
		List<String[]> items = new ArrayList();
		
		// 固件属性.交换机
		items.add(new String[]{"交换机","jhj","1","",""});

		items.add(new String[]{"插槽编号","ccbh","2","",""});
		items.add(new String[]{"型号","xh","2","",""});		
		items.add(new String[]{"端口数量","dksl","2","",""});
		items.add(new String[]{"管理IP","glip","2","",""});
		
		return items;
	}
	// 获取固件属性（管理子项）
	public static List get_dpjx_gjsx_gl()
	{
		List<String[]> items = new ArrayList();
		
		// 固件属性.管理
		items.add(new String[]{"管理","gl","1","",""});

		items.add(new String[]{"插槽编号","ccbh","2","",""});
		items.add(new String[]{"型号","xh","2","",""});		
		items.add(new String[]{"端口数量","dksl","2","",""});
		items.add(new String[]{"管理IP","glip","2","",""});
		
		return items;
	}
	
	// 获取全部管理属性
	public static List get_dpjx_glsx_all()
	{
		List<String[]> items = new ArrayList();
		items.addAll(get_dpjx_glsx());
		items.addAll(get_dpjx_glsx_glzhxx());
		
		return items;
	}
	//获取管理属性（简单属性）
	public static List get_dpjx_glsx()
	{
		List<String[]> items = new ArrayList();
		
		// 管理属性
		items.add(new String[]{"设备领用单位","sblydw","","",""});
		items.add(new String[]{"设备领用人","sblyr","","",""});
		items.add(new String[]{"联系电话","lyrlxdh","","",""});
		items.add(new String[]{"系统维护单位","xtwhdw","","",""});
		items.add(new String[]{"设备维护人","sbwhr","","",""});
		items.add(new String[]{"联系电话","whrlxdh","","",""});
		
		return items;
	}
	//获取管理属性（管理帐号信息子项）
	public static List get_dpjx_glsx_glzhxx()
	{
		List<String[]> items = new ArrayList();
		
		// 管理属性.管理帐号信息
		items.add(new String[]{"管理帐号信息","glzhxx","1","",""});
		
		items.add(new String[]{"帐号名称","zhmc","2","",""});
		items.add(new String[]{"帐号说明","zhsm","2","",""});
		items.add(new String[]{"帐号使用人","zhsyr","2","",""});
		items.add(new String[]{"备注","bz","2","",""});
		
		return items;
	}


		
	
}
