<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>计划任务管理系统</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/themes/default/images/favicon.ico" />
<script type="text/javascript" src="<%=request.getContextPath()%>/themes/default/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/themes/default/gex.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/themes/default/main.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/themes/default/md5.js"></script> 
<style type="text/css">@import url(<%=request.getContextPath()%>/themes/default/main.css);</style>
<style type="text/css">@import url(<%=request.getContextPath()%>/themes/default/index.css);</style>
<script type="text/javascript">

if(window.top!=window.self)
{
	window.top.location=location;	
}

jQuery(function($){
///////////////////

function innitialLogin(){
	$('#loginDiv').css({left:($('html').width()-$('#loginDiv').width())/2+'px',top:($('html').height()-$('#loginDiv').height())/2+'px'})
}
innitialLogin();

$(window).resize(function(){
	innitialLogin();	
})

//drop down animation
$('#loginDiv').css({top:'-'+$('#loginDiv').height()+'px',opacity:0})
$('#loginDiv').animate({top:($('html').height()-$('#loginDiv').height())/2+'px',opacity:1},300,'easeInOutBack')

$('#loginDiv').draggable();
$('.skinMask').addClass('skin'+(parseInt(Math.random()*9)+1));
$('input').focusClass('focus')

if($.cookie('usrname')){
	$('#ousr').val($.cookie('usrname'))	;
	$('#opws').trigger('focus');
}else{
	$('#ousr').trigger('focus');	
}

$('form').submit(function(){
	
	$.cookie('usrname',$('#ousr').val(),{expires:30})
})

$('#osubmit').click(function(){
	if($('#opws').val()==''){
		$('.error').show();	
		//$('#opws').tigger('focus');
		return false;
	}
	var pass =  hex_md5($('#opws').val()); 
	$('#opws').val(pass);

	$('form').submit();	
})

/*ie6 enter key submit*/
$('#opws').keypress(function(e){
	if(e.keyCode==13){
		$('#osubmit').trigger('click');
	}
})

//临时代码
$('.error').hide();	


//preload skins
function preload(arrayOfImages) {
    $(arrayOfImages).each(function(){
        $('<img/>')[0].src = this;
    });
}

/*
preload([
    '<%=request.getContextPath()%>/themes/default/images/skin1.jpg',
   	'<%=request.getContextPath()%>/themes/default/images/skin2.jpg',
    '<%=request.getContextPath()%>/themes/default/images/skin3.jpg',
	'<%=request.getContextPath()%>/themes/default/images/skin4.jpg',
	'<%=request.getContextPath()%>/themes/default/images/skin5.jpg',
	'<%=request.getContextPath()%>/themes/default/images/skin6.jpg',
	'<%=request.getContextPath()%>/themes/default/images/skin7.jpg',
	'<%=request.getContextPath()%>/themes/default/images/skin8.jpg',
	'<%=request.getContextPath()%>/themes/default/images/skin9.jpg',
	'<%=request.getContextPath()%>/themes/default/images/skin10.jpg',
	'<%=request.getContextPath()%>/themes/default/images/skin11.jpg',
	'<%=request.getContextPath()%>/themes/default/images/skin12.jpg',
	'<%=request.getContextPath()%>/themes/default/images/skin13.jpg'
]);
*/

///////////////////
})
</script>

</head>
<body>
<div id="loginDiv">
    <form action="<%=request.getContextPath()%>/login.action" method="post">
    <div class="formDiv">
    <p><label for="ousr">用户名：<input name="usr" id="ousr" /></label></p>
    <p><label for="opws">密　码：<input name="pws"  type="password" id="opws"/></label><input type="submit" id="osubmit" value="" /> <strong class="error">登陆失败！</strong></p>
    </div></form>
    <div id="copyright">
    
<div class="copyright1" >
	<span style="color:#666;">相关下载：</span>
	<a href="<%=request.getContextPath()%>/file/install_flash_player_11_active_x.exe">Flash插件（IE）</a> &nbsp;  
	<a href="<%=request.getContextPath()%>/file/install_flash_player_11_plugin.exe">Flash插件（其它）</a> &nbsp;
	</div>
	<p>2014 <sup>&copy;</sup> 版权所有</p>    
    
    
    
    
    </div>    
</div>
</body>
</html>
