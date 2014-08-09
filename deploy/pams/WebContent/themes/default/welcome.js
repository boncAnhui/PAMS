// JavaScript by davidguoshuang at gmail.com

jQuery(function(){
/////////////////////////////////////////////////


//alert('首页不显示左侧菜单，隐藏之')
window.parent.$('.collapseTd').hide();
window.parent.$('.leftMenu').hide();
window.top.$('#knowledgeTd').hide();			
window.top.$('#knowledgeDiv').hide();

//initial topMenu home current
window.top.$('.topMenu').find('li').removeClass('current').end().find('.home').addClass('current') 

//得到当前用户id，以后可以产生关联操作
userID=window.top.userID;

//订制参数解析
customizeConfigstr=customizeConfigstr.split(';')
for(i=0;i<customizeConfigstr.length;i++){
	customizeConfigstr[i]=customizeConfigstr[i].split(',');
}
var customizeConfig=customizeConfigstr;

//ajax 2 server dababase 订制参数返回服务器
function ajaxSaveConfig(){
$.ajax({
	type:'post',
	url:'/ray_nwpn_itsm/module/app/system/user/userconfig/userconfig_saveconfig.action',
	data:'configstr='+customizeConfig.join(';')+'&userid='+userID
})
}

/*show hide customize*/
$('.showHide').click(function(){
	if($(this).attr('title')=='打开'){$(this).parent().animate({top:0},300,'easeOutBounce');$(this).attr('title','关闭');$('#wrapper').css({'padding-top':'70px'});}
	else{$(this).parent().animate({top:'-36px'},300,'easeOutBounce');$(this).attr('title','打开');$('#wrapper').css({'padding-top':'35px'});}	
})

/*定制功能  每个用户都需要登陆 所以不需要 cookie 后台记录定制项目*/

//initialCustomize li index
for(i=0;i<customizeConfig.length;i++){
			$('#customize li.'+customizeConfig[i][0]).appendTo('#customize ul');
			if(customizeConfig[i][1]==1)
				{
				$('#'+customizeConfig[i][0]).addClass('checkboxChecked');
				}else
				{
				$('#'+customizeConfig[i][0]).removeClass('checkboxChecked');
				}
			$('#'+customizeConfig[i][0]).val(customizeConfig[i][1]);
			
}

//check or uncheck
$('#customize input.checkbox').click(function(){
	obj=$(this);
//	alert('');
	if(obj.hasClass('checkboxChecked')){obj.removeClass('checkboxChecked');obj.val(0);}
	else{obj.addClass('checkboxChecked');obj.val(1);}
	initialcustomizeConfig();
	ajaxSaveConfig();
}).mouseover(function(){
	$(this).addClass('checkboxHover');
}).mouseout(function(){
	$(this).removeClass('checkboxHover');
})

//inital DOM
function initialWrapper(){
$('#wrapper .section').appendTo($('#wrapperTmp'))
	for(i=0;i<customizeConfig.length;i++){
		if(customizeConfig[i][1]==1){
			$('#wrapperTmp .'+customizeConfig[i][0]).appendTo('#wrapper');
		}
	}
}

initialWrapper();

//initial array and DOM
function initialcustomizeConfig(){
	thisArray=new Array();
	$('#customize').find('li').each(function(){
		oarray=new Array();
		oarray.push($(this).attr('class'));
		oarray.push($(this).find('input').val());
		oarray.push($(this).text());
		
		thisArray.push(oarray);
	})
	customizeConfig=thisArray;
//	alert(customizeConfig)
	//console.log(customizeConfig)
	initialWrapper();
}

$('#customize ul').sortable({placeholder: "ui-state-highlight",stop:function(){initialcustomizeConfig();ajaxSaveConfig();}})

/*last li no line*/
$('.section').each(function(){
	$(this).find('li:last').addClass('last');
})
	
/*searchType*/
$('.searchType ul li').click(function(){
	$(this).parent().find('li').removeClass('current');
	$(this).addClass('current');
	$(this).parents('.searchType').find('input').val($(this).text());
})

/*flashDiv*/
var flashvars = {
	path:'charts/',
	chart_data: "3月9日;91;196\n3月10日;87;112\n3月11日;68;79\n3月2日;30;32\n3月13日;52;57\n3月13日;52;57\n3月13日;52;57",
	chart_settings: "<settings><text_size>12</text_size><data_type>csv</data_type><plot_area><margins><left>10</left><right>10</right><top>30</top><bottom>55</bottom></margins></plot_area><grid><category><dashed>1</dashed><dash_length>4</dash_length></category><value><dashed>1</dashed><dash_length>4</dash_length></value></grid><axes><category><width>1</width><color>E7E7E7</color></category><value><width>1</width><color>E7E7E7</color></value></axes><values><value><min>0</min></value></values><legend><enabled>1</enabled></legend><y>0</y><angle>0</angle><column><width></width><balloon_text>{title}: {value} downloads</balloon_text><grow_time>3</grow_time><sequenced_grow>1</sequenced_grow></column><graphs><graph gid='0'><title>新事件</title><color>ADD981</color></graph><graph gid='1'><title>已解决事件</title><color>7F8DA9</color></graph></graphs><labels><label lid='0'><text><![CDATA[<b>一周事件统计1231313</b>]]></text><y>0</y><text_color>000000</text_color><text_size>13</text_size><align>center</align></label></labels></settings>"
	};
var params = {'wmode':'transparent',menu:'false'};
var attributes = {};
swfobject.embedSWF("charts/amcolumn.swf", "flashDiv", "100%", "100%", "9.0.0", false, flashvars, params, attributes);


/////////////////////////////////////////////////	
})