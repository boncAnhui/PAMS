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
<style type="text/css">
#homeUl li  input {margin-right: 8px;}
.title {border-bottom:solid 1px #eef;padding-bottom:6px;margin-bottom:6px;}
.t {display:inline-block;*display:inline;*zoom:1;width:6em;color:#666;text-align:Center;}
.t2 {color:#666;}

#homeUl li:hover,#homeUl li.hover {border-color:#ccc;background-color:#f4f4f4;}

</style>
<script type="text/javascript">
var navigationJSON=[{name:'个性设置',link:'${base}/module/irm/portal/portal/portal/portal_preferences.action'},{name:'首页定制'}];
var homeJson;

$.ajax({
	url:'${base}/module/irm/portal/portal/portal/portal_subject.action',
	cache:false,
	data:{userid:1234},//根据用户id 取其设置
	async:false,
	success:function(d){
		eval('homeJson=('+d+')'); 
	}	
})

jQuery(function($){
///////////////////

var ohtml='<ul id="homeUl">';
$.each(homeJson,function(j,k){
	var isCheck=k.visable=='0'?'':' checkboxChecked';
	ohtml+='<li data-id="'+k.id+'" data-pname="'+k.pname+'" data-url="'+k.url+'" data-status="'+k.status+'" data-type="'+k.type+'"><span class="t"><input class="checkbox'+isCheck+'" value="'+k.visable+'"/></span>'+k.name+'</li>';
})
ohtml+='</ul>'
$('#ulContainer').append(ohtml);

$('#homeUl').sortable({
	axis:'y'
})

$('#saveBtn').click(function(){

	var homeJSON=([]);
	$('#homeUl li').each(function(j,k){
		//var ostatus=$(k).find('.checkboxChecked').length==0?'closed':'opened';
		//console.log(ostatus.length)
		//submitStr+='&id='+$(k).attr('data-id')+'&status='+ostatus+'&order='+$(this).index();
		
		if($(k).find('.checkboxChecked').length!=0){
			homeJSON.push({name:$(k).text(),pname:$(k).attr('data-pname'),visable:"1",url:$(k).attr('data-url'),status:$(k).attr('data-status'),id:$(k).attr('data-id'),type:$(k).attr('data-type')});
		}else{
			homeJSON.push({name:$(k).text(),pname:$(k).attr('data-pname'),visable:"0",url:$(k).attr('data-url'),status:$(k).attr('data-status'),id:$(k).attr('data-id'),type:$(k).attr('data-type')});
		}
	})
	//console.log(homeJSON);
	
	var param = {json:JSON.stringify(homeJSON)};
	//console.log(param);	
	$.ajax({
	  url: "portal_updatepersonhomemenu.action",
	  data: param,
	  type: 'post',
	  traditional: true,
	  success: function(data) {
	  	if(data=="1"){
	  		alert('保存成功！请点击首页查看效果'); 
			
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
		
		var param = {ctype:'home'};
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
<body><div id="pageContainer">
<h1>首页定制</h1>

<div class="title"><span class="t">是否显示</span><span class="t2">栏目名称</span></div>
<div id="ulContainer">
</div>
<br/>
<p><button id="saveBtn">保存首页布局</button> <button id="resetLeftMenu">重置修改结果</button></p>
</div></body>
</html>