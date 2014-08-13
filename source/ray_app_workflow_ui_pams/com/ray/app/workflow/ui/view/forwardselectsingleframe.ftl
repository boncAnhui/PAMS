<html>
<head>
<meta charset="utf-8" />
<title></title>
<script type="text/javascript" src="${base}/themes/default/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${base}/themes/default/gex.js"></script>
<script type="text/javascript" src="${base}/themes/default/main.js"></script>
<style type="text/css">@import url(${base}/themes/default/main.css);</style>
</head>
<body>

<style>
h1 {
font-family: Arial, sans-serif;
font-size: 12px;
color: #369;
padding-bottom: 14px;
text-align:left;
margin-left:10px
}
</style>

<script type="text/javascript">

var cindex = 1;

var navigationJSON=[ {name:'共享管理',link:'${base}/module/irm/portal/portal/portal/portal_browse.action?ccate=gxgl'}, {name:'共享管理'}];

function initialTabNsHeight(){
	//console.log($('#gTabsContainterN .topTr').height())
	var avialHeight=$('body').height()-40;
	$('#gTabsContainterN iframe').css({height:avialHeight+'px'});
	$('.bottomTr').css({height:avialHeight+'px'})
}

$(function(){
/////////////////////////////////////////

//解决ie6 bottomTr 高度问题；ff iframe 100% 问题
initialTabNsHeight();
$(window).resize(function(){initialTabNsHeight();})

//点击标签页
$('.topTr li').click(function(){

	cindex = $(this).index()+1;	
	var oindex=$(this).index()+1;
	$('.topTr li').removeClass('c')
	$(this).addClass('c');
	$('.bottomTr li').removeClass('c')
	$('.bottomTr li:nth-child('+oindex+')').addClass('c');
	
	var oFrame=$('.bottomTr').find('iframe')
	
	// 点击标签页
	if(oindex==1)
	{
		// 组织机构
		var endactid = list_endacts[c_rindex];
		var url = '${base}/module/app/system/workflow/ui/selectownertoperson.action?actdefid=' + endactid;
		oFrame.attr('src',url);
	}
	else if(oindex==2)
	{ 
		// 组织机构
		oFrame.attr('src','about:blank');
	}
	else if(oindex==2)
	{ 
		// 角色岗位
		oFrame.attr('src','about:blank');
	}
	else if(oindex==3)
	{ 
		// 人员
		oFrame.attr('src','about:blank');
	}
	
}).hoverClass('hover');

//radius border
$('#gTabsContainterN .topTr li').wrapInner('<span class="r"><span class="m"></span></span>')

$('.back2grid').click(function()
{
	window.close();
})

/////////////////////////////////////////
})
</script>

<#assign txt_ctype="">
<#assign txt_split="">

<#if data.bact.ctype=="NORMAL">
	<#assign txt_ctype="普通">
</#if>	
<#if data.bact.split=="OR">
	<#assign txt_split="单一">
<#elseif data.bact.split=="AND">
	<#assign txt_split="单一">
</#if>

<div class="formDiv">

<div id="fixedOp">
<button onclick="page_forward()">转发</button>
<button onclick="page_showflowdefine()">流程</button>
<button onclick="page_close()">关闭</button>
</div>

<form id="aform">
<div class="formDiv">

	<h1 style="cursor:hand" onclick="page_toggle_node()">1.当前节点  </h1>
	<div id="fs_node">
	<table id="tb_node" class="formGrid" width="600">
	<tbody>
	<tr>
	<td width="20">&nbsp;</td>
	<td width="80" align="right">节点名：</td>
	<td width="100" align="left">${data.bact.cname}</td>
	<td width="400"></td>
	</tr>
	<tr>
	<td>&nbsp;</td>
	<td>节点类型：</td>
	<td>${txt_ctype}</td>
	<td></td>
	</tr>
	<tr>
	<td></td>
	<td>转出方式：</td>
	<td>${txt_split}</td>
	<td align="left" style="color:#3355aa">在后续节点为多项时，仅能选择其中一项后续节点。</td>
	</tr>
	</tbody>
	</table>
	</div>
	
	<h1 style="cursor:hand" onclick="page_toggle_routes()">2.选择转发节点</h1>
	<div id="fs_routes">
	<table id="tb_routes" class="formGrid" width="600">
	<tbody>
	<#list data.routes as route>
	<#assign endact = data.endacts[route_index]>
	<tr>
	<td width="20">&nbsp;</td>
	<td width="40" align="center">
	<#if data.bact.split=="OR" || data.routes?size==1>
		<input class="route" type="radio" id="rindex${route_index}" name="rindex" <#if data.routes?size==1>checked</#if> onclick="page_selectroute('${route_index}','${route.endactid}')" acttype="${endact.ctype}">
	<#elseif data.bact.split=="AND">
		<input class="route" type="checkbox" id="rindex${route_index}" name="rindex" checked onclick="page_selectroute('${route_index}','${route.endactid}')" acttype="${endact.ctype}">
	</#if>
	</td>
	<td width="140">${route.cname}</td>
	<td width="400">&nbsp;<input readonly class="text" id="actowner${route_index}" name="actowner" style="width:300;">&nbsp;&nbsp;<a href="javascript:void(0)" onclick="page_selectroute('${route_index}','${route.endactid}')">人员</a></td>
	</tr>
	</#list>
	</tbody>
	</table>
	</div>
	
	<h1 style="cursor:hand" onclick="page_toggle_persons()">3.选择转发人员 </h1>	
	<div id="fs_persons" style="display:none">
		<table id="gTabsContainterN">
		<tr class="topTr">
		<td>
			<ul>
				<li class="c" id="tab1">流程人员</li>
				<li id="tab2">组织机构</li>
			    <li id="tab3">岗位角色</li>
				<li id="tab4">组织人员</li>
			</ul>	
		</td>
		</tr>
		<tr class="bottomTr">
		<td height="200">
		<iframe id="frame_selectowner" name="frame_selectowner" src="" frameborder="1" height="200" ></iframe>
		</td>
		</tr>
		</table>
	</div>
	</div>
</div>
</form>

<script>

state_checkroute = "N";
state_checkroutemessage = "";

state_checkactowner = "N";
state_checkactownermessage = "";

var list_endacts = new Array(); // 可转发的目标活动
var c_rindex = 0; // 记录当前所选的目标活动;

var list_endactpersons = new Array();

<#list data.routes as route>

list_endacts[${route_index}] = "${route.endactid}";
list_endactpersons[${route_index}] = new Array();

</#list>
function page_close()
{
	window.top.opener.top.location.reload();
	window.close();
}

function page_showflowdefine()
{
	url = "${base}/module/app/system/workflow/define/define_main.action?flowid=${arg.flowdefid}";
	openwinT(url,'define_viewprogress',pub_width_large,pub_height_large,null,'流程图');
}

// 选择路由
function page_selectroute(index, actdefid)
{
	c_rindex = index;	
	$("#fs_persons").show();
	$("#tab1").trigger("click");
}

function page_add_tr(pindex, obj)
{
	var len = $("#tb_persons tr").length;        
    var trHtml = "<tr>";
    trHtml += "<td><input class='checkbox' value='0' name='pindex' ownerctx='" + obj.ownerctx + "' cname='" + obj.cname + "' ctype='" + obj.ctype + "'></td>";
    trHtml += "<td>" + obj.cname + "</td>";
    trHtml += "</tr>";
    $("#tb_persons tbody").append(trHtml);
}

function page_clear()
{
	$("#tb_persons tbody").empty();
}

// 添加人员
function page_addperson()
{
	list_endactpersons[c_rindex] = new Array();
	var act = list_endactpersons[c_rindex];
	var num = 0;
	
	$("#tb_persons tbody .checkbox", window.frames[0].document).each(function(j,k)
	{
		if($(this).val()==1)
		{
			act[num] = new Array();
			act[num][0] = $(this).attr('ownerctx');
			act[num][1] = $(this).attr('ctype');
			act[num][2] = $(this).attr('cname'); 
			num++;
		}		
	})
	
	// 更新显示在人员列表
	$("#" + "actowner" + c_rindex).val("");
	
	var str_persons = "";
	for(i=0;i<act.length;i++)
	{
		str_persons += act[i][2];
		if(i<act.length-1)
		{
			str_persons += ",";
		}
	}
	
	$("#" + "actowner" + c_rindex).val(str_persons);
	
	$("#fs_persons", window.frames[0].document).hide();
		
}


function page_forward()
{
	var forwardtar = "${arg.forwardtar}";
	var runactkey = "${arg.runactkey}";
	var user = "${data.userid}";
	var priority = "";
	var tableid = "${arg.tableid}";
	var dataid = "${arg.dataid}";
	var beginactdefid = "${arg.actdefid}";
	
	//添加完成转出后页面刷新功能参数
	var wf_formid = "${arg.wf_formid}";
	var wf_action = "${arg.wf_action}";
	
	url = "";
	
	if(forwardtar=="")
	{
		url = "${base}/module/app/system/workflow/ui/forward.action";
	}
	else
	{
		url_temp="${arg.wf_formid}";
		url = url_temp + forwardtar;
	}	
	
	url += "?runactkey=" + runactkey;
	url += "&tableid=" + tableid;
	url += "&dataid=" + dataid;
	url += "&beginactdefid=" + beginactdefid;
	

	// 检查路由选择规则是否正确
	page_checkroute();
	if(state_checkroute=="N")
	{
		alert(state_checkroutemessage);
		return;
	}
	
	// 检查目标节点人员选择规则是否正确
	page_checkactowner();
	if(state_checkactowner=="N")
	{
		alert(state_checkactownermessage);
		return;
	}

	// 循环检查路由是否选中
	// 选中路由的人员方可加入转发参数列表内
	var selectnum = 0;
	for(i=0;i<list_endacts.length;i++)
	{
		if($("#tb_routes tbody .route:eq(" + i + ")").is(":checked")==false)
		{
			continue;
		}

		var act = list_endactpersons[i];
		
		if(act.length==0)
		{
			url += "&endact=" + list_endacts[i];
		}
		else
		{
			for(j=0;j<act.length;j++)
			{
				url += "&endact=" + list_endacts[i];
				url += "&actors=" + act[j][0];
				url += "&actorstype=" + act[j][1];
				url += encodeURI("&actorsname=" + act[j][2]);
				
				url += "&agents=" + act[j][0];
				url += "&agentstype=" + act[j][1];
				url += encodeURI("&agentsname=" + act[j][2]);
			}
		}
		
		selectnum++;
	}
	
	if(selectnum==0)
	{
		return;
	}
	
	openwin(url,"forward",pub_width_mid,pub_height_mid);
}

// 检查路由
// 多选一路由仅允许选择一条路由
// 全选路由必须全部选中
// 不允许未选择路由
function page_checkroute()
{
	state_checkroute = "N";
	state_checkroutemessage = "";
	var split = "${data.bact.split}";
	var routenum = "${data.routes?size}";
	var selectnum = 0;
	
	$("#tb_routes tbody .route").each(function(j,k)
	{
		if($(k).is(":checked"))
		{
			selectnum++;		
		}
	});
	
	if(routenum==0)
	{
		state_checkroute = "N"; //异常
		state_checkroutemessage = "流程异常，当前业务环节没有任何可选择的后续业务环节，请您联系系统管理人员。";
		return;
	}

	if(selectnum == 0)
	{
		state_checkroute = "N"; //异常
		state_checkroutemessage = "未选择要转发的后续业务环节，请您重新选择。";
		return;
	}
	
	if(split=="OR")
	{
		if(selectnum > 1)
		{
			state_checkroute = "N"; //异常
			state_checkroutemessage = "当前业务环节仅允许选择一项要转发的后续业务环节，请您重新选择。";
			return;
		}
	}
	
	if(split=="AND")
	{
		if(selectnum < routenum)
		{
			state_checkroute = "N"; //异常
			state_checkroutemessage = "当前业务环节必须选择所有的后续业务环节，请您重新选择。";
			return;	
		}
	}
	
	state_checkroute = "Y";
}

// 检查路由目标活动人员是否正确
// 普通活动必须选择人员
// 结束活动不用指定人员
function page_checkactowner()
{
	state_checkactowner = "N";
	state_checkactownermessage = "";
	
	var selectnum = 0;
	
	$("#tb_routes tbody .route").each(function(j,k)
	{
		var actctype = $("#tb_routes tbody .route:eq(" + j + ")").attr("acttype");
		
		if($(k).is(":checked"))
		{
			if(actctype=="END")
			{
				return true; 
			}
			
			if($("#tb_routes tbody .text:eq(" + j + ")").val()=="")
			{
				selectnum++;
			}
		}
	});
	
	if(selectnum>0)
	{
		state_checkactowner = "N"; //异常
		state_checkactownermessage = "后续业务环节必须指定人员，请您重新选择。";
		return;
	}
	
	state_checkactowner = "Y";
}


function page_load_actowner(actdefid)
{
	page_clear();
	
	$.ajax({
		url:'${base}/module/app/system/workflow/ui/selectownertopersonajax.action',
		type:'POST',
		data: {actdefid:actdefid},
		dataType: "json", 
		success:function(d)
		{
			$.each(d,function(key,obj)
			{
				page_add_tr(key, obj);
			});		
		}
	});
}

function page_toggle_node()
{
	$("#fs_node").toggle();
}

function page_toggle_routes()
{
	$("#fs_routes").toggle();
}

function page_toggle_persons()
{
	$("#fs_persons").toggle();
	if($("#fs_persons").is(":visible"))
	{
		$("#tab1").trigger("click");
	}
}

page_toggle_node();


</script>

</body>
</html>