<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>任务管理系统</title>
<link rel="shortcut icon" href="${base}/themes/default/images/favicon.ico" />
<script type="text/javascript" src="${base}/themes/default/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${base}/themes/default/gex.js"></script>
<script type="text/javascript" src="${base}/themes/default/index.js"></script>
<style type="text/css">@import url(${base}/themes/default/index.css);</style>
<script type="text/javascript">

var GS_leftMenu;
$.ajax({
	url:'${base}/menu!leftmenu.action',
	//data:{loginname:123},//根据用户id 取其设置
	cache:false,
	async:false,
	success:function(d){
		if (d.substr(0,9) == "while(1);") { d = d.substring(10); } 
		
		if (d.substr(0,2) == "/*"){ d = d.substring(2, d.length - 2)}; 
		
		eval('GS_leftMenu=('+d+')');
	}
})

var GS_topMenu;//ajax get use topmenu json
$.ajax({
	url:'${base}/menu!topmenu.action',
	cache:false,
	//data:{loginname:1234},//根据用户id 取其设置
	async:false,
	success:function(d){
		if (d.substr(0,9) == "while(1);") { d = d.substring(10); } 
		if (d.substr(0,2) == "/*"){ d = d.substring(2, d.length - 2)}; 
		
		eval('GS_topMenu='+d);
	}	
})


function openwin(url,winname,w,h,handler){
	if(!winname||winname==''){winname='win'+parseInt(Math.random());}
	if(!w){w=defaultWinWidth;}
	if(!h){h=defaultWinHeight;}	
	handlerChar='';
	if(handler){handlerChar='&handler='+handler}
	//console.log(handlerChar)
	winname=window.open(url+handlerChar,winname,'width='+w+',height='+h+',left='+(window.screen.availWidth-w)/2+',top='+(window.screen.availHeight-h)/2+',location=1,toolbar=1,scrollbars=1,menubar=0,resizable=1,status=1');
	winname.focus();
}

</script>
</head>
<body>



<span id="numSpan" style="position:absolute;left:200px;top:0px;z-index:9999;display:none;" >10</span>

<table border="0" cellspacing="0" cellpadding="0" style="width:100%;height:100%;">
<tr id="topTr"><td>
<div id="topContainer">
    <h1 class="logo">任务管理系统</h1>
    <div class="logo-s"></div>
    
    <div id="welcomeInfo"><span class="user">${Session.sys_login_token.sys_login_username}，欢迎回来</span> 当日有 <strong id="workcount"></strong> 条待办信息</div>
    
    <div class="topNavi">
		<form action="" target="mainIframe" id="topSearch"><input id="q" type="hidden" placeholder="搜索知识库" title="搜索知识库" name="q"/> </form>
		        
        <span id="skin" title="切换主题">
			<ul class="chooser disableSelection"><li title="主题1">1</li><li title="主题2">2</li><li title="主题3">3</li><li title="主题4">4</li></ul>
		</span>
		<a title="退出" class="quit" href="${base}/login.jsp" target="_top">注销</a>
		
        <a class="preferences" href="${base}/module/irm/portal/portal/portal/portal_browse.action?ccate=admin" title="系统管理" target="mainIframe">系统管理</a>
        <a class="preferences" href="${base}/module/irm/portal/portal/portal/portal_preferences.action" title="个性设置" target="mainIframe">个性设置</a>
        <br/>
       
    </div>
    <!--
    <ul id="statusG">
    <li id="mStatus"><span class="free"></span></li>
    </ul>
     -->
    <div class="toggleTop" title="收起"><span class="ani"></span></div>
    <div id="navigation" style="border-bottom:solid 0px #ccc;"><span class="s">当前位置：<a href="" target="mainIframe">首页</a><span class="realNavi"></span><span id="portalMenu"></span></span></div>
    
    <div class="backForward"><span id="backspace" title="后退"></span><span id="forward" title="前进"></span><span id="refresh" title="刷新"></span><!--<span id="homepage" title="首页"></span>--></div>
    <!--top end-->
</div>
</td></tr>
<tr id="bottomTr"><td>
    <table border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td id="leftTd">
			<div id="menuContainer"></div>  
			</td>
			 <td class="mainTd"><iframe src="${base}/module/irm/portal/portal/portal/portal_welcome.action" width="100%" height="100%" frameborder="0" allowtransparency="true" id="mainIframe" name="mainIframe"></iframe></td> 
		 
		</tr>
    </table>
</td></tr>
</table>

</body>
</html>
