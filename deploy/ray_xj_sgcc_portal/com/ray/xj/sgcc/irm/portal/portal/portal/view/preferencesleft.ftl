<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>信息资源管理系统</title>
<link rel="shortcut icon" href="favicon.ico" />
<script type="text/javascript" src="${base}/themes/default/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${base}/themes/default/gex.js"></script>
<script type="text/javascript" src="${base}/themes/default/main.js"></script>
<style type="text/css">@import url(${base}/themes/default/main.css);</style>
<script type="text/javascript">
var navigationJSON=[{name:'个性设置',link:'${base}/module/irm/portal/portal/portal/portal_preferences.action'},{name:'左侧菜单定制'}];

var GS_leftMenu;//ajax get use topmenu json
$.ajax({
	url:'${base}/menu!leftmenu.action',
	cache:false,
	//data:{userid:1234},//根据用户id 取其设置
	async:false,
	success:function(d){
		if (d.substr(0,9) == "while(1);") { d = d.substring(10); } 
		
		if (d.substr(0,2) == "/*"){ d = d.substring(2, d.length - 2)}; 
		
		eval('GS_leftMenu=('+d+')');
	}	
});

/*
var GS_leftMenu=({
	status:'closed',//默认是打开还是关闭状态 //closed opened
	items:[
		{name:'个人中心',show:1,items:[
			{name:'未读消息',link:'home.htm',classname:'i_unread',show:1},
			{name:'已读消息',link:'home.htm',classname:'i_allmessages',show:0},
			{name:'已发消息',link:'home.htm',classname:'i_sent',show:0},
			{name:'待办工作',link:'home.htm',classname:'i_todo',show:1},
			{name:'我的未阅',link:'home.htm',classname:'i_noread',show:1},
			{name:'我的文档',link:'home.htm',classname:'i_mydoc',show:1},
			{name:'我的收藏',link:'home.htm',classname:'i_fav',show:1},
			{name:'个人资料',link:'#1',classname:'1234',show:1},
			{name:'代理授权',link:'#1',classname:'1234',show:1}
		]},
		{name:'配置资源',show:0,items:[
			{name:'新增配置项',link:'#1',classname:'1234',show:1},
			{name:'我的配置项',link:'#1',classname:'1234',show:1},
			{name:'配置项列表',link:'#1',classname:'1234',show:1},
			{name:'应用系统综合拓扑图',link:'#1',classname:'1234',show:1},
			{name:'应用系统拓扑图',link:'#1',classname:'1234',show:1},
			{name:'主机拓扑图',link:'#1',classname:'1234',show:1},
			{name:'变更申请',link:'#1',classname:'1234',show:1},
			{name:'我的变更',link:'#1',classname:'1234',show:1},
			{name:'变更列表',link:'#1',classname:'1234',show:1},
			{name:'变更查询',link:'#1',classname:'1234',show:1}
		]},
		{name:'项目任务',show:1,items:[
			{name:'项目下达',link:'#1',classname:'1234',show:1},
			{name:'我的项目',link:'#1',classname:'1234',show:1},
			{name:'主管项目',link:'#1',classname:'1234',show:1},
			{name:'项目列表',link:'#1',classname:'1234',show:1},
			{name:'项目查询',link:'#1',classname:'1234',show:1},
			{name:'项目人力资金表',link:'#1',classname:'1234',show:1},
			{name:'项目进度表',link:'#1',classname:'1234',show:1},
			{name:'项目移交',link:'#1',classname:'1234',show:1},
			{name:'任务下达',link:'#1',classname:'1234',show:1},
			{name:'我的任务',link:'#1',classname:'1234',show:1},
			{name:'任务列表',link:'#1',classname:'1234',show:1},
			{name:'任务查询',link:'#1',classname:'1234',show:1}
		]},
		{name:'业务数据',show:1,items:[
			{name:'导入运维事件',link:'#1',classname:'1234',show:1},
			{name:'运维事件列表',link:'#1',classname:'1234',show:1},
			{name:'运维事件统计',link:'#1',classname:'1234',show:1},
			{name:'导入一单两票',link:'#1',classname:'1234',show:1},
			{name:'一单两票列表',link:'#1',classname:'1234',show:1},
			{name:'一单两票统计',link:'#1',classname:'1234',show:1},
			{name:'新增业务活动',link:'#1',classname:'1234',show:1},
			{name:'我的业务',link:'#1',classname:'1234',show:1},
			{name:'业务活动列表',link:'#1',classname:'1234',show:1},
			{name:'业务活动统计',link:'#1',classname:'1234',show:1}
		]},
		{name:'业务知识',show:1,items:[
			{name:'新增知识',link:'#1',classname:'1234',show:1},
			{name:'知识目录',link:'#1',classname:'1234',show:1},
			{name:'知识资料',link:'#1',classname:'1234',show:1},
			{name:'知识地图',link:'#1',classname:'1234',show:1}
		]},
		{name:'综合展现',show:1,items:[
			{name:'1111111111',link:'#1',classname:'1234',show:1}
		]},
		{name:'系统管理',show:1,items:[
			{name:'数据字典',link:'#1',classname:'1234',show:1},
			{name:'通用查询',link:'#1',classname:'1234',show:1},
			{name:'通用图表',link:'#1',classname:'1234',show:1},
			{name:'组织机构',link:'#1',classname:'1234',show:1},
			{name:'人员',link:'#1',classname:'1234',show:1},
			{name:'角色',link:'#1',classname:'1234',show:1},
			{name:'组织人员',link:'#1',classname:'1234',show:1},
			{name:'人员角色',link:'#1',classname:'1234',show:1},
			{name:'功能',link:'#1',classname:'1234',show:1},
			{name:'菜单',link:'#1',classname:'1234',show:1},
			{name:'角色功能',link:'#1',classname:'1234',show:1},
			{name:'角色菜单',link:'#1',classname:'1234',show:1},
			{name:'栏目',link:'#1',classname:'1234',show:1},
			{name:'角色栏目',link:'#1',classname:'1234',show:1},
			{name:'快捷方式',link:'#1',classname:'1234',show:1},
			{name:'角色快捷',link:'#1',classname:'1234',show:1},
			{name:'流程定义',link:'#1',classname:'1234',show:1},
			{name:'流程列表',link:'#1',classname:'1234',show:1},
			{name:'流程监控',link:'#1',classname:'1234',show:1},
			{name:'配置项模板',link:'#1',classname:'1234',show:1},
			{name:'项目文档目录模板',link:'#1',classname:'1234',show:1},
			{name:'项目文档模板',link:'#1',classname:'1234',show:1}
		]}
	]
});
*/
jQuery(function($){
///////////////////

//initial HTML
var ulHTML='';
ulHTML+='<ul id="sortContainer" class="level1">';

$.each(GS_leftMenu.items,function(i,k){
	//外循环 start
	
	if(k.show!="0"){
		ulHTML+='<li><span class="l1" data-class="'+k.classname+'">'+k.name+'</span>';
	}else{
		ulHTML+='<li><span class="l1 notShow data-class="'+k.classname+'">'+k.name+'</span>';
	}
	ulHTML+='<ul class="level2">';
	
	$.each(k.items,function(n,m){
		//循环 start
		if(m.show!="0"&&k.show!="0"){
			ulHTML+='<li><span data-link="'+m.link+'" data-class="'+k.classname+'">'+m.name+'</span></li>';
		}else{			
			ulHTML+='<li><span  data-link="'+m.link +'" class="notShow" data-class="'+k.classname+'">'+m.name+'</span></li>';
		}
		//循环 end
	})
	
	ulHTML+='</ul>';
	ulHTML+='<div style="clear:left;height:10px;"></div>';
	ulHTML+='</li>'
	//外循环 end	
})

ulHTML+='</ul>';

$('#sortContainerDiv').empty().append(ulHTML);

$('#sortContainer').sortable({placeholder: "placeholder2"}).disableSelection();
$('.level2').sortable({placeholder: "placeholder"}).disableSelection();

//sortContainer 点击操作---------开始
$('#sortContainer li span').click(function(){

	$(this).toggleClass('notShow');
	
	//if level1 clicked 
	if(!$(this).parents('ul').hasClass('level2')){
		if($(this).hasClass('notShow')){
			$(this).parent().find('ul li span').addClass('notShow')
		}else{
			$(this).parent().find('ul li span').removeClass('notShow')
		}
	}else{//如果level2 全部关掉，应该关掉 level1
		//console.log($(this).parent().parent().html())
		var ifAllHide=true;	
		var oSpans=$(this).parent().parent().find('li span');

		$(this).parent().parent().find('li span').each(function(){
			//console.log($(this).hasClass('notShow'))
			if(!$(this).hasClass('notShow')){
				ifAllHide=false;
			}
		})
			
		if(ifAllHide){//如果都没选的话！
			$(this).parent().parent().parent().find('>span').addClass('notShow');
			//console.log($(this).parent().parent().parent().find('>span'))
		}else{
			$(this).parent().parent().parent().find('>span').removeClass('notShow');
		}
	}
})
//sortContainer 点击操作---------结束

$('#configLeftMenu .l1').click(function(){
	//$(this).toggleClass('checked').sibling().toggleClass('checked');
	$(this).parent().find('.l1').removeClass('checked');
	$(this).addClass('checked');
})

$('#saveLeftMenu').click(function(){
	//构造菜单json提交给后台 start
	
	var leftMenuJSON=({status:'closed',items:[]});
	leftMenuJSON.status = $('input[name=status]').val();
	$('#sortContainer').find('.l1').each(function(i,k){
			
			var showHide=1;
			if($(k).hasClass('notShow')){showHide=0;}
			
			leftMenuJSON.items.push({name:$(k).text(),show:showHide,classname:$(k).attr('data-class'),items:[]});
			var citem
				
			$(this).parent().find('li').each(function(n,m){
					var ifShow=1;
					if($(m).find('span').hasClass('notShow')){
						ifShow=0;
					}
					
					citem = {name:$(m).text(),link:$(m).find('span').attr('data-linke'),classname:$(m).find('span').attr('data-class'),show:ifShow}
					leftMenuJSON.items[i].items.push(citem)
			})
			
	})
	//console.log(leftMenuJSON)
	//console.log(JSON.stringify(leftMenuJSON))
	
	$('#gjson_data').val(JSON.stringify(leftMenuJSON));
	//$(this).parents('form').trigger('submit')
	//alert('这就是给后台的json结构');
	
	//构造菜单json提交给后台 end
	var param = {json:JSON.stringify(leftMenuJSON)};
	
	$.ajax({
	  url: "portal_updatepersonleftmenu.action",
	  data: param,
	  type: 'post',
	  traditional: true,
	  success: function(data) {
	  	if(data=="1"){
	  		alert('保存成功！请注销登陆查看效果'); 
			
	  	}else{
	  		alert('保存出错！');
	  	}
	    
	  },
	  error: function() {
	    alert('保存出错！');  
	  }
	});
	
	
	
})

$('#resetBtn').click(function(){
	//$('#iwant2reset').attr('value',1)
	//$(this).parents('form').trigger('submit')
	var param = {ctype:'left'};
	$.ajax({
	  url: "portal_deletepersonmenu.action",
	  data: param,
	  type: 'post',
	  traditional: true,
	  success: function(data) {
	  	if(data=="1"){
	  		alert('删除成功！'); 
			window.location.reload();	
			
	  	}else{
	  		alert('删除出错！');
	  	}
	    
	  },
	  error: function() {
	    alert('删除出错！');  
	  }
	});
	
})

///////////////////
})
</script>
<style type="text/css">
.placeholder {background:#eee;height:18px;display:inline-block;width:60px;border:dashed 1px #ccc;}
.placeholder2 {background:#eee;height:23px;display:inline-block;width:100%;border:dashed 1px #ccc;} 
.ui-sortable-helper {background:#fff;opacity:0.8;filter:alpha(opacity=80);;}

#sortContainer ul.level2 li {display:inline-block;﻿*display:inline;}
#sortContainer li span {padding:0 10px 0 28px;display:block;cursor:move;font-size:14px;height:23px;line-height:23px;background: url(${base}/themes/default/images/checkradio.png) 6px -50px no-repeat;﻿*display:inline;*zoom:1;background-image:url(${base}/themes/default/images/checkradio.gif)}
#sortContainer li span.l1 {border:solid 1px #fff;border-bottom:solid 1px #ccc;padding-bottom:3px;display:block;}
#sortContainer li span.l1:hover {border-color:#CAE6FF;background-color:#E0F1FF;}
#sortContainer ul.level2 li span {background-color:transparent;border:solid 1px #fff;}

#sortContainer ul.level2 li span:hover {border-color:#CAE6FF;background-color:#E0F1FF;}
#sortContainer ul.level2 {padding-left:20px;}
#sortContainer ul.level2 li span {font-size:12px;}
#sortContainer li span.notShow {background:none;color:#999;}
.level1 span {border-bottom1:solid 1px #ccc;}
.level2 span {border-bottom:0;}
.level2 {margin-top:10px;}
#sortContainerDiv {padding-top:10px;}
#infoDiv {padding:8px;}

#configLeftMenu {line-height:25px;}
#configLeftMenu span {line-height:25px;height:25px;display:inline-block;}
#configLeftMenu span.radioSpan {line-height:25px;height:25px;display:inline-block;padding-left:20px;cursor:pointer;margin-left:10px;color:#999;}
#configLeftMenu span.radioSpanOpened {background-position:0 -25px;}
#configLeftMenu span span {background:url(${base}/themes/default/images/icon_lmenu.gif) 0 0 no-repeat;}
#configLeftMenu span span.open {background-position:0 -75px;color:#999;font-size:12px;font-weight:normal;}
#configLeftMenu span.checked span.open {background-position:0 -25px;color:#000;font-size:14px;font-weight:700;}
#configLeftMenu span span.close {background-position:0 -50px;color:#999;font-size:12px;font-weight:normal;}
#configLeftMenu span.checked span.close {background-position:0 0;color:#000;font-size:14px;font-weight:700;}
</style>
</head>
<body><form method="get">

<div id="pageContainer">

<h1>左侧菜单设置</h1>

<div id="sortContainerDiv"></div>

<p id="configLeftMenu">菜单默认效果：<span class="allRadios"><input name="status" value="closed" type="hidden" /><label><input class="radio" value="opened" />展开</label><label><input class="radio" value="closed" />收起</label></span></p>

<div class="op"><button id="saveLeftMenu">提交修改结果</button> 
<input type="hidden" name="iwant2reset" id="iwant2reset"/>
<button id="resetBtn" value="0">初始化到出厂状态！</button>
<br /><br /><strong>操作说明：</strong>鼠标单击切换 是否显示；拖动 切换顺序；</div>

<input name="gjson_data" id="gjson_data" type="hidden" />

</div>
</form></body>
</html>