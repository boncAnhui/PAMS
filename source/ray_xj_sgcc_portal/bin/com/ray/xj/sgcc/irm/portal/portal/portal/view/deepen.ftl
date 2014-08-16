<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>首页</title>
<script type="text/javascript" src="${base}/themes/default/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${base}/themes/default/gex.js"></script>
<script type="text/javascript" src="${base}/themes/default/main.js"></script>
<script type="text/javascript" src="/irm/resource/default/script/public_complete.js"></script>
<style type="text/css">@import url(${base}/themes/default/main.css);</style>
<style type="text/css">@import url(${base}/themes/default/welcome.css);</style>
<script type="text/javascript" src="${base}/resource/default/swf/FusionCharts.js"></script>
<style>
#gsbrpopup {
	position:fixed;
	bottom:0;right:0;_position:absolute;background: #fff;border:solid 1px #ddd;font-size: 12px;*width:380px;}
#gsbrpopup .title {background: 	#ddd;line-height: 30px;padding:0 8px;cursor:pointer;color:#666;white-space: nowrap;}

#gsbrpopup .title .collapse {float:right;border:solid 6px #999;border-right-color:transparent;border-left-color:transparent;border-bottom: 0;margin-top: 	12px; margin-left: 20px;height:0;overflow:hidden;}

#gsbrpopup .title .collapse {_border-right-color:#ddd;_border-left-color:#ddd;}

#gsbrpopup .body {height:200px;overflow: auto}

#gsbrpopup.closed .body {display: none;}
#gsbrpopup.closed .title .collapse {border-right-color:transparent;border-left-color:transparent;border-bottom: solid 6px #999;border-top:0;_border-right-color:#ddd;_border-left-color:#ddd;}
#gsbrpopup ul {margin: 0;	padding: 10px;	list-style-type: none;}
#gsbrpopup ul li {margin: 0;	padding: 0;	list-style-type: none;line-height: 20px;white-space: nowrap;}
#gsbrpopup ul li .date {float: right;color:#999;padding-left:40px;}
#gsbrpopup ul li a {display:inline-block;*display:inline;*zoom:1;white-space:nowrap;width:20em;overflow:hidden;text-overflow:ellipsis;color:#333;text-decoration: none;}
</style>
<script>
jQuery(function($){
////////////////////////

$('#gsbrpopup .title').on('click',function(){
	$('#gsbrpopup').toggleClass('closed');
	
})
if('${arg.popupclass}'=='closed' || ${data.bulletins?size} <= 0)
{
	$('#gsbrpopup').toggleClass('closed');
}
////////////////////////
})
</script>

<script type="text/javascript">
jQuery(function($){
/////////////////////////////////////

/////////////////////////////////////
})

function redirctmoduleurl(a,url){
	//location=url;
	openwin(url)
}

function page_change_model(model)
{
	window.location = "portal_welcome.action?pmodel=" + model+"&popupclass="+$('#gsbrpopup').attr('class');
}

function page_change_model(model,type)
{
	window.location = "portal_welcome.action?pmodel=" + model + "&type=" + type+"&popupclass="+$('#gsbrpopup').attr('class');
}

function page_change_browse(ccate)
{
	window.location = "portal_browse.action?ccate=" + ccate+"&popupclass="+$('#gsbrpopup').attr('class');
}

function open_alarm(begintime,endtime,type)
{
	 openwin('portal_mainframe.action?model=alarm&begintime='+begintime+'&endtime='+endtime+'&type='+type+'&_ctype='+_ctype,'alarmwin',pub_width_large,pub_height_large);
	// openwin('portal_browsealarm.action?begintime='+begintime+'&endtime='+endtime+'&type='+type+'&_ctype='+_ctype,'alarmwin',pub_width_large,pub_height_large);
}

function open_defect(begintime,endtime,type)
{
	 openwin('portal_mainframe.action?model=defect&begintime='+begintime+'&endtime='+endtime+'&type='+type+'&_ctype='+_ctype,'alarmwin',pub_width_large,pub_height_large);
	 //openwin('portal_browsedefect.action?begintime='+begintime+'&endtime='+endtime+'&type='+type+'&_ctype='+_ctype,'defectmwin',pub_width_large,pub_height_large);
}

function open_event(begintime,endtime,type)
{
	 openwin('portal_mainframe.action?model=event&begintime='+begintime+'&endtime='+endtime+'&type='+type+'&_ctype='+_ctype,'alarmwin',pub_width_large,pub_height_large);
//	 openwin('portal_browseevent.action?begintime='+begintime+'&endtime='+endtime+'&type='+type+'&_ctype='+_ctype,'eventmwin',pub_width_large,pub_height_large);
}

function open_phonerecord(begintime,endtime,type)
{
	 openwin('portal_mainframe.action?model=phonerecord&begintime='+begintime+'&endtime='+endtime+'&type='+type+'&_ctype='+_ctype,'alarmwin',pub_width_large,pub_height_large);
}

function open_audit(begintime,endtime,type)
{
	 openwin('portal_mainframe.action?model=audit&begintime='+begintime+'&endtime='+endtime+'&type='+type+'&_ctype='+_ctype,'alarmwin',pub_width_large,pub_height_large);
//	 openwin('portal_browseaudit.action?begintime='+begintime+'&endtime='+endtime+'&type='+type+'&_ctype='+_ctype,'eventmwin',pub_width_large,pub_height_large);
}

function open_maintance(begintime,endtime,type)
{
	openwin('portal_mainframe.action?model=maintance&begintime='+begintime+'&endtime='+endtime+'&type='+type+'&_ctype='+_ctype,'alarmwin',pub_width_large,pub_height_large);
//	 openwin('portal_browsemaintance.action?begintime='+begintime+'&endtime='+endtime+'&type='+type+'&_ctype='+_ctype,'eventmwin',pub_width_large,pub_height_large);
}

function open_change(begintime,endtime,type)
{
	 openwin('portal_mainframe.action?model=change&begintime='+begintime+'&endtime='+endtime+'&type='+type+'&_ctype='+_ctype,'alarmwin',pub_width_large,pub_height_large);
//	 openwin('portal_browsechange.action?begintime='+begintime+'&endtime='+endtime+'&type='+type+'&_ctype='+_ctype,'eventmwin',pub_width_large,pub_height_large);
}

function open_stopapp(begintime,endtime,type)
{
	 openwin('portal_mainframe.action?model=stopapp&begintime='+begintime+'&endtime='+endtime+'&type='+type+'&_ctype='+_ctype,'alarmwin',pub_width_large,pub_height_large);
//	 openwin('portal_browsestopapp.action?begintime='+begintime+'&endtime='+endtime+'&type='+type+'&_ctype='+_ctype,'eventmwin',pub_width_large,pub_height_large);
}

function open_equip(begintime,endtime,type)
{
	 openwin('portal_mainframe.action?model=equip&begintime='+begintime+'&endtime='+endtime+'&type='+type+'&_ctype='+_ctype,'alarmwin',pub_width_large,pub_height_large);
//	 openwin('portal_browseequip.action?begintime='+begintime+'&endtime='+endtime+'&type='+type+'&_ctype='+_ctype,'eventmwin',pub_width_large,pub_height_large);
}

function open_yjya(begintime,endtime,type)
{
	 openwin('portal_mainframe.action?model=yjya&begintime='+begintime+'&endtime='+endtime+'&type='+type+'&_ctype='+_ctype,'alarmwin',pub_width_large,pub_height_large);
//	 openwin('portal_browseyjya.action?begintime='+begintime+'&endtime='+endtime+'&type='+type+'&_ctype='+_ctype,'eventmwin',pub_width_large,pub_height_large);
}

function open_ctooperation(begintime,endtime,type)
{
	 openwin('portal_mainframe.action?model=ctooperation&begintime='+begintime+'&endtime='+endtime+'&type='+type+'&_ctype='+_ctype,'alarmwin',pub_width_large,pub_height_large);
//	 openwin('portal_browsectooperation.action?begintime='+begintime+'&endtime='+endtime+'&type='+type+'&_ctype='+_ctype,'eventmwin',pub_width_large,pub_height_large);
}

</script>
<style>
#otabs {padding:10px;}
#otabs a {display:inline-block;*display:inline;*zoom:1;line-height: 25px;margin-right:8px;padding:0 8px;}
#otabs a.active {background:#21759b;color: #fff;
}

</style>
<script>
jQuery(function($){
$('#otabs').on('click','a',function(){
$(this).siblings().removeClass('active');
$(this).addClass('active');
})
})
</script>
</head>
<body>

<div id="siteWizard">
	<ul>
		<li onclick="page_change_model('welcome')">个人工作平台</li>
		<li onclick="page_change_model('manage')">管理平台</li>
		<#--<li onclick="page_change_model('managerep')">管理平台图表</li>-->
		<li class="c" onclick="page_change_model('deepen','all')">相关业务查询</li>
		<li onclick="page_change_model('stat')">报表统计</li>
		<li onclick="page_change_browse('show')">系统拓扑</li>
	</ul>
</div>
<div id="pageContainer">
	<#include "/com/ray/nwpn/itsm/report/common/view/nav.ftl">
	<#include "/com/ray/nwpn/itsm/report/common/view/script.ftl">
	<div class="radios"  id="otabs">
		所属分类：
		<a href='#' onclick="page_query('all')" class="<#if data.type=='all'>active</#if>">全部</a>&nbsp;&nbsp;
		<a href='#' onclick="page_query('yyxt')" class="<#if data.type=='yyxt'>active</#if>">应用系统</a>&nbsp;&nbsp;
		<a href='#' onclick="page_query('fyyxt')" class="<#if data.type=='fyyxt'>active</#if>">非应用系统</a>
	</div>
	<div class="section sec2" style="padding-top:5px;">
	<div class="p8" id="rep_portal_deepen" style="float:left"></div>
	</div>
	<div id='guageDivs' style="float:left;padding-left:60px;"></div>
	<div style="clear:both;"></div>
	
	<div id="gsbrpopup">
	<div class="title"><span class="collapse"></span>公告栏</div>
	<div class="body">
		<ul>
		<#list data.bulletins as bulletin>
			<li><span class="date">${bulletin.createtime?string('yyyy-MM-dd')}</span><a href="javascript:void(0);" title='${bulletin.description}'>${bulletin.description}</a></li>
		</#list>
		</ul>
	</div>
</div>
</div>

<style>
ul.fsc li {margin-right:60px;display:inline-block;float:left;}
</style>

<script>
var ctype = '${data.type}';
function page_query(type)
{
	var begintime = $('#begindate').val();
	var endtime = $('#enddate').val();
	
	var chart = new FusionCharts("${base}/resource/default/swf/Pie3D.swf?ChartNoDataText=没有数据！", "ChartId", "400", "300", "0");
	chart.setTransparent(true);
	var url = "${base}/module/irm/portal/portal/portal/portal_getchartdata.action?endtime="+endtime+"&begintime="+begintime+"&type="+type;
	chart.setDataURL(escape(url));		
	chart.render("rep_portal_deepen");
	
	$.ajax({
		    url:'portal_browsedeepen.action',
		    data:{begintime:begintime,endtime:endtime,type:type},
		    cache:false,
		    async:false,
		    success:function(d){
		   		$('#guageDivs').empty().append(d);
		    }
		});
		
	ctype = type;
}

function page_load()
{
	var begintime = $('#begindate').val();
	var endtime = $('#enddate').val();
	var type = ctype;
	
	var chart = new FusionCharts("${base}/resource/default/swf/Pie3D.swf?ChartNoDataText=没有数据！", "ChartId", "400", "300", "0");
	chart.setTransparent(true);
	var url = "${base}/module/irm/portal/portal/portal/portal_getchartdata.action?endtime="+endtime+"&begintime="+begintime+"&type="+type;
	chart.setDataURL(escape(url));		
	chart.render("rep_portal_deepen");
	
	$.ajax({
		    url:'portal_browsedeepen.action',
		    data:{begintime:begintime,endtime:endtime,type:type},
		    cache:false,
		    async:false,
		    success:function(d){
		   		$('#guageDivs').empty().append(d);
		    }
		});
}
page_load();


</script>

</body>
</html>
