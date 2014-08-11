<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>信息运维管理系统</title>
<link rel="shortcut icon" href="favicon.ico" />
<script type="text/javascript" src="${base}/themes/default/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${base}/themes/default/gex.js"></script>
<script type="text/javascript" src="${base}/themes/default/main.js"></script>
<style type="text/css">@import url(${base}/themes/default/main.css);</style>
<script type="text/javascript">
var navigationJSON=[{name:'个性设置'}];

$('#navigation .realNavi',window.top.document).empty().append(' &gt; <span>个性设置</span>');//写入导航

var GS_portalMenu=[
		{name:'个性设置',items:[
			//{name:'首页定制',link:'${base}/module/irm/portal/portal/portal/portal_preferenceshome.action',icon:'ic_pre_home'},
			//{name:'左侧菜单定制',link:'${base}/module/irm/portal/portal/portal/portal_preferencesleft.action',icon:'ic_pre_left'},
			{name:'上帧定制',link:'${base}/module/irm/portal/portal/portal/portal_preferencestop.action',icon:'ic_pre_top'},
			{name:'修改密码',link:'${base}/module/irm/system/organ/user/user_changepassword.action',icon:'ic_pws'}
		]}
]

jQuery(function($){
///////////////////

var portalHtml='';
$.each(GS_portalMenu,function(i,k){
	portalHtml+='<h2 class="collapsable"><span class="t">'+k.name+'</span></h2>';
	portalHtml+='<ul class="portal">';
	$.each(k.items,function(n,m){
			portalHtml+='<li><a title="'+m.name+'" href="'+m.link+'"><span class="g"><span class="i '+m.icon+'"></span></span><span class="t">'+m.name+'</span></a></li>';
	})
	portalHtml+='</ul>';
})

$('#pageContainer').empty().append(portalHtml);


///////////////////
})
</script>
</head>
<body>

<div id="pageContainer"></div>
</body>
</html>