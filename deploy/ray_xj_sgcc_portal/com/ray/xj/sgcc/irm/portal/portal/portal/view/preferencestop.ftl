<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>任务管理系统</title>
<link rel="shortcut icon" href="favicon.ico" />
<script type="text/javascript" src="${base}/themes/default/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${base}/themes/default/gex.js"></script>
<script type="text/javascript" src="${base}/themes/default/main.js"></script>
<style type="text/css">@import url(${base}/themes/default/main.css);</style>
<style type="text/css">
.ui-sortable-helper {background:#fff;opacity:0.8;filter:alpha(opacity=80);}
.connectedSortable li {cursor:move;background-color:#fff;border:solid 1px #ccc;padding:4px;margin-bottom:5px;_zoom:1;	}
.connectedSortable li.placeholder {background:#eee;height:12px;display:block;border:dashed 1px #ccc;width:30%;}
.connectedSortable li:hover,.connectedSortable li.hover {border-color:#CAE6FF;background-color:#E0F1FF;}
#currentMenuDiv {padding-right:20px;}
#availMenu li:hover,#availMenu li.hover {border-color:#ccc;background-color:#f4f4f4;}
#currentMenuDiv {padding-right:20px;}
#currentMenu {padding:8px;padding-bottom:0px;background:#eee;float:left;margin-right:20px;width:30%;}
#availMenu {padding:8px;padding-bottom:0px;background:#eee;float:left;width:30%;}
#currentMenu li {color:#21759b;font-weight:700;}
#availMenu li {color:#777;}
div.t {float:left;width:30%;margin-right:20px;font-weight:700;text-align:center;padding:8px 0;}
p {height:30px;line-height:30px;}
</style>
<script type="text/javascript">
var navigationJSON=[{name:'个性设置',link:'${base}/module/irm/portal/portal/portal/portal_preferences.action'},{name:'上帧定制'}];
var GS_topMenu;//ajax get use topmenu json
$.ajax({
	url:'${base}/menu!topmenu.action',
	cache:false,
	//data:{userid:1234},//根据用户id 取其设置
	async:false,
	success:function(d){
		if (d.substr(0,9) == "while(1);") { d = d.substring(10); } 
		
		if (d.substr(0,2) == "/*"){ d = d.substring(2, d.length - 2)}; 
		
		eval('GS_topMenu=('+d+')');
	}	
})

jQuery(function($){
///////////////////
var oHtml='';

oHtml+='<div id="currentMenuDiv"><ul id="currentMenu" class="connectedSortable">';
$.each(GS_topMenu.itemsShow,function(i,k){
	oHtml+='<li data-id="'+k.id+'" data-link="'+ k.link+'" data-target="'+k.target+'">'+k.name+'</li>';
})
oHtml+='</ul></div>';
oHtml+='<div id="availMenuDiv"><ul id="availMenu" class="connectedSortable">';
$.each(GS_topMenu.itemsMore,function(i,k){
	oHtml+='<li data-id="'+k.id+'" data-link="'+ k.link+'" data-target="'+k.target+'">'+k.name+'</li>';
})
oHtml+='</ul></div>';

$('#sortHtml').empty().append(oHtml);

$('#currentMenu,#availMenu').sortable({placeholder: 'placeholder',connectWith:'.connectedSortable',dropOnEmpty: true}).disableSelection();

$('.connectedSortable li').hover(function(){
	$(this).addClass('hover');	
},function(){
	$(this).removeClass('hover');	
})

//

$('#saveLeftMenu').click(function(){

	var topMenuJSON=({status:'closed',skin:1,itemsShow:[],itemsMore:[]});
	
	topMenuJSON.status = $('input[name=status]').val();
	topMenuJSON.skin = $('input[name=theme]').val();
	
	
	var mArray=[];
	$('#currentMenu li').each(function(i,k){
	
		topMenuJSON.itemsShow.push({name:$(k).text(),link:$(k).attr("data-link"),id:$(k).attr("data-id"),target:$(k).attr("data-target")});
		
	});
	
	$('#availMenu li').each(function(i,k){
		topMenuJSON.itemsMore.push({name:$(k).text(),link:$(k).attr("data-link"),id:$(k).attr("data-id"),target:$(k).attr("data-target")});
	
	})
	
	
	//$(this).parents('form').attr('action','${base}/module/irm/portal/portal/portal/portal_topsave.action');
	//alert($(this).parents('form').attr('action'))
	
	//console.log(topMenuJSON);
	//console.log(JSON.stringify(topMenuJSON));	
	//$(this).parents('form').trigger('submit');
	
	
	var param = {json:JSON.stringify(topMenuJSON)};
		
	$.ajax({
	  url: "portal_updatepersontopmenu.action",
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




$('#resetLeftMenu').click(function(){
		
		var param = {ctype:'top'};
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
	
});
///////////////////
})
</script>

</head>
<body>
<form>
<div id="pageContainer">

<h1>主导航	菜单设置</h1>

<div class="t">主导航菜单</div><div class="t" style="color:#666;">更多菜单</div>
<div style="clear:both;"></div>
<div id="sortHtml"></div>
<div style="clear:both;height:10px;"></div>

<p id="configLeftMenu">上帧默认效果：<span class="allRadios"><input name="status" value="<#if (data.topPersonalized.state)??>${data.topPersonalized.state}<#else>opened</#if>" type="hidden" /><label><input class="radio" value="opened" />展开</label><label><input class="radio" value="closed" />收起</label></span></p>

<p id="chooserDiv">　　默认主题：<span class="allRadios"  data-original="<#if (data.topPersonalized.sysskin)??>${data.topPersonalized.sysskin}<#else>1</#if>"><input name="theme" value="<#if (data.topPersonalized.sysskin)??>${data.topPersonalized.sysskin}<#else>1</#if>" type="hidden" /><label><input class="radio" value="1" />1</label><label><input class="radio" value="2" />2</label><label><input class="radio" value="3" />3</label><label><input class="radio" value="4" />4</label></span></p>

<input type="hidden" id="cmenu" name="menu" />
<input type="hidden" id="amenu" name="availmenu" />
<div class="op"><button id="saveLeftMenu">提交修改结果</button> <button id="resetLeftMenu">重置修改结果</button> </div>

</div>
</form></body>
</html>