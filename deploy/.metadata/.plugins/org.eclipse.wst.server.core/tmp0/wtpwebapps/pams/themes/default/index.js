var pub_width_large = "960";
var pub_height_large = "540";

// 以下为整合运维资源管理系统index.js

//by guoshuang for 2sao

var hoverDelay=300,hoverTimer=null;//延迟显示 避免鼠标划过的闪烁

jQuery(function($){
/////////////////////////////////////

$(document).keydown(function(e){
	//禁止向下箭头 暴露框架结构
	if(e.keyCode==40){return false;}	
})

//initial leftmenu current indicator
$('body').append('<div id="leftIndi"></div>')

//hide ie focus dotted line
$('a').attr('hidefocus','true;')

if($.browser.version<7&&$.browser.msie){
	//
}else{//if is NOT ie6
	$('#topContainer').append('<div id="maskgra"></div>')
}

/*if(GS_topMenu.skin==0){
	GS_topMenu.skin=Math.ceil(Math.random()*($('#skin li').length-1));
}*/
if(GS_topMenu.skin==0){
	GS_topMenu.skin=Math.ceil(Math.random()*19);
}
$('body').addClass('skin'+GS_topMenu.skin);
$('#skin .chooser li').each(function(){
	$(this).removeClass('current');
	if($(this).text()==GS_topMenu.skin){
		$(this).addClass('current');
	}
})


//topMenu -----------------start----------------------
var topMenuHtml='';
topMenuHtml+='<ul class="topMenu">';
//topMenuHtml+='<li><a href="main.htm" target="_top"><span class="s1"><span class="s2">首页</span></span></a></li>'
$.each(GS_topMenu.itemsShow,function(i,k){
	topMenuHtml+='<li><a href="'+k.link+'" target="'+k.target+'"><span class="s1"><span class="s2">'+k.name+'</span></span></a>';
})
topMenuHtml+='<li class="more"><span class="m"></span></li>';
topMenuHtml+='</ul>';
//topMenuHtml+='><div class="more"><span class="m"></span></div>';
topMenuHtml+='<ul id="subMenu">'
$.each(GS_topMenu.itemsMore,function(i,k){
	if(k.target=='mainIframe'){
		topMenuHtml+='<li><a href="'+k.link+'" target="'+k.target+'"><span class="s1"><span class="s2">'+k.name+'</span></span></a>';
	}else{
		// 改为可调窗口大小
		topMenuHtml+='<li><a href="javascript:void(0);" onclick="window.open(\''+k.link+'\',\'otherwin\',\'resizable=yes,left='+(window.screen.availWidth-pub_width_large)/2+',top='+(window.screen.availHeight-pub_height_large)/2+',width='+pub_width_large+',height='+pub_height_large+'\')"><span class="s1"><span class="s2">'+k.name+'</span></span></a>';
		 // topMenuHtml+='<li><a href="javascript:void(0);" onclick="window.open(\''+k.link+'\',\'otherwin\',\'resizable=yes,left='+(window.screen.availWidth-800)/2+',top='+(window.screen.availHeight-600)/2+',width=800,height=600\')"><span class="s1"><span class="s2">'+k.name+'</span></span></a>';
	}
})
topMenuHtml+='</ul>';

$('#topContainer .logo').after(topMenuHtml);
//topMenu -----------------end------------------------

//Left Menu -----------------------------------------

//initial leftMenu
var shortcutDivHtml='';

shortcutDivHtml+='<div id="shortcutDiv">\n';
shortcutDivHtml+='\t<h2 class="toggleShortcuts"><a href="javascript:void(0);">收起菜单</a></h2>\n';

$.each(GS_leftMenu.items,function(i,k){
	//console.log(i,k.name)
	//循环1--开始------------
	shortcutDivHtml+='\t<div class="sec">\n';
	shortcutDivHtml+='\t\t<h2 class="collapsable"><a href="javascript:void(0);">'+k.name+'</a></h2>\n';
	
	//循环2--start----------
	shortcutDivHtml+='\t\t\t<ul>\n';
	
	//<a target="mainIframe" href="home.htm" class="i_unread"><span>未读消息</span></a><
	$.each(k.items,function(m,n){
		shortcutDivHtml+='\t\t\t\t<li><a target="mainIframe" href="'+n.link+'" class="'+n.classname+'" title="'+n.name+'"><span>'+n.name+'</span></a></li>\n';
	})
	
	shortcutDivHtml+='\t\t\t</ul>\n';
	//循环2--end------------
	
	shortcutDivHtml+='\t\t</div>\n';	
	//循环1--结束------------
})

shortcutDivHtml+='\t<h2 class="appendMenu"><a href="javascript:void(0);">轮换顺序</a></h2>\n';
shortcutDivHtml+='</div>\n';

//small shotcut div start 

shortcutDivHtml+='<div id="shortcutDivSmall">\n';
shortcutDivHtml+='\t<h2 class="toggleShortcuts"><a href="javascript:void(0);" title="打开菜单">打开菜单</a></h2> \n';
shortcutDivHtml+='\t\t<ul>\n';

$.each(GS_leftMenu.items,function(i,k){
	//循环1--开始------------	
	//循环2--start----------
	
	//<a target="mainIframe" href="home.htm" class="i_unread"><span>未读消息</span></a><
	$.each(k.items,function(m,n){
		shortcutDivHtml+='\t\t\t<li><a target="mainIframe" href="'+n.link+'" class="'+n.classname+'" title="'+n.name+'"><span>'+n.name+'</span></a></li>\n';
	})
	
	//循环2--end------------
	//循环1--结束------------
})

shortcutDivHtml+='\t\t</ul>\n';
shortcutDivHtml+='\t<h2 class="appendMenu"><a href="javascript:void(0);">轮换顺序</a></h2>\n';
shortcutDivHtml+='</div>\n';

$('#menuContainer').empty().append(shortcutDivHtml)

//滚轮切换项目
$('#shortcutDiv ul').mousewheel(function(e,delta){
	if(delta<0){
		$(this).find('li:first').appendTo($(this))
	}else{
		$(this).find('li:last').prependTo($(this))
	}
})

//if more menu empty -> remove it
if($('#subMenu li').length==0){
	$('#subMenu,.topMenu .more').hide()
}

/////////////////////////////清除所有的 current 状态
function clearAllCurrent(){
		$('#topContainer .topMenu li').removeClass('current');
		$('#shortcutDivSmall').find('li').removeClass('current');
		$('#shortcutDiv').find('li').removeClass('current');
}

//toggleShortcuts
$('.toggleShortcuts').click(function(){	
	if(GS_leftMenu.status=='opened'){
		$('#shortcutDiv').hide();
		$('#shortcutDivSmall').show();
		$('#leftTd').css({width:'30px'});
		GS_leftMenu.status='closed';
		//原来选中的项目应该保持
		//console.log($(this).parent().find('li.current').find('a').attr('class'))
		var currentClass=$(this).parent().find('li.current').find('a').attr('class');
		$('#shortcutDivSmall ul li').removeClass('current');
		$('#shortcutDivSmall ul li a.'+currentClass).parent().addClass('current');
	}else{
		$('#shortcutDiv').show();
		$('#shortcutDivSmall').hide();
		$('#leftTd').css({width:'150px'});
		GS_leftMenu.status='opened';
		var currentClass=$(this).parent().find('li.current').find('a').attr('class');
		$('#shortcutDiv ul li').removeClass('current');
		$('#shortcutDiv ul li a.'+currentClass).parent().addClass('current');
	}
})

//初始化的时候就需要根据 open closed 状态处理页面！
$('.toggleShortcuts').click();

$('#shortcutDiv .appendMenu a').click(function(){
	obj=$('#shortcutDiv')
	obj.find('.sec').first().appendTo(obj);
})

//滚轮切换项目
$('#shortcutDivSmall ul').mousewheel(function(e,delta){
	if(delta<0){
		$(this).find('li:first').appendTo($(this))
	}else{
		$(this).find('li:last').prependTo($(this))
	}
})

$('#shortcutDivSmall .appendMenu a').click(function(){
	var obj=$('#shortcutDivSmall ul');
	obj.find('li').first().appendTo(obj);
})

$('#shortcutDiv ul li a').click(function(){
	//$('#shortcutDiv').find('li').removeClass('current');
	clearAllCurrent();
	$(this).parent().addClass('current');
	
	//test!!!!!!!!!!!!!!!
	var tmp=$(this).attr('href');
	$(this).attr('href',tmp+'?'+escape($(this).text()))
	//console.log($(this).html())
})

//indi animation
$('#shortcutDiv ul li,#shortcutDivSmall ul li').mouseenter(function(){	
	$('#leftIndi').show();
	$('#leftIndi').stop().animate({top:($(this).position().top+1)+'px',height:$(this).height()-2+'px'},'fast','easeOutBack')	
}).mouseleave(function(){
	$('#leftIndi').hide();
})

$('#shortcutDivSmall ul li a').click(function(){
	//$('#shortcutDivSmall').find('li').removeClass('current');
	clearAllCurrent();
	$(this).parent().addClass('current');
})

$('#navigation').before('<div id="switchHome"><a class="workroom current" href="/pams/module/irm/portal/portal/portal/portal_browse.action?ccate=home" target="mainIframe">工作频道</a> <a class="workchart" href="home-chart.htm" target="mainIframe">图表频道</a></div><div id="currentHomeIndi"></div>');

//currentHomeIndi
function initialCurrentHomeIndi(){
	$('#currentHomeIndi').css({left:($('#switchHome .current').offset().left)+'px',width:+($('#switchHome .current').width()+18)+'px'})
}
initialCurrentHomeIndi();
$(window).resize(initialCurrentHomeIndi)

$('#switchHome a').click(function(){
	$(this).parent().find('a').removeClass('current')
	$(this).addClass('current')
	$('#currentHomeIndi').animate({left:($('#switchHome .current').offset().left)+'px',width:+($('#switchHome .current').width()+18)+'px'},'fast','swing')
})

$('h2.collapsable a').click(function(){
	$(this).parent().toggleClass('closed').next().toggle();
})

$('#topContainer .topMenu li a').click(function(){
	if($(this).parent().hasClass('more')){return;}//如果是最后一个的话
	//$('#topContainer li').removeClass('current');
	clearAllCurrent();
	$(this).parent().addClass('current');
})

$('#topContainer .topMenu li a').mouseenter(function(){
	//if($(this).parent().hasClass('more')){return;}//如果是最后一个的话
	$('#topContainer li').removeClass('hover');
	$(this).parent().addClass('hover');
}).mouseleave(function(){
	$('#topContainer li').removeClass('hover');
})

///////////////////////////////////////////////////////

var topHEIGHT1=130;
var topHEIGHT2=70;

var topHeight;
if(GS_topMenu.status=='closed'){
	topHeight=topHEIGHT2;
}else{
	topHeight=topHEIGHT1;
}

//console.log(GS_topMenu.status,topHeight)

//intial some heights for ie,ff(bottomTr iframe etc.)
function initialHeight(){
	var avialHeight=$('body').height()-topHeight;//header height configed by GS_topMenu JSON
	$('#menuContainer').css('height',avialHeight)
	$('#mainIframe').css('height',avialHeight)
	$('#bottomTr').css('height',avialHeight)
}

//initial status
if(GS_topMenu.status!='closed'){
		$('#topContainer').css({top:'0px'});
		$('#topTr').css({height:topHEIGHT1+'px'});			
		$('#topContainer .toggleTop span').removeClass('down').stop().css({backgroundPosition:'0 0'});		
		$('#topContainer .toggleTop').attr('title','收起');
		topHeight=topHEIGHT1;
		$('.logo').show();
		$('.logo-s').hide();
		initialHeight();
	}else{
		$('#topContainer').css({top:'-60px'});
		$('#topTr').css({height:topHEIGHT2+'px'});	
		$('#topContainer .toggleTop span').addClass('down').stop().css({backgroundPosition:'-24px -14px'});
		$('#topContainer .toggleTop').attr('title','展开');
		topHeight=topHEIGHT2;
		$('.logo').hide();
		$('.logo-s').show();
		initialHeight();
}


$(window).resize(function(){
	initialHeight();
})

$('#topContainer .toggleTop').click(function(){
		if(GS_topMenu.status=='closed'){
			$('#topContainer').css({top:'0px'});
			$('#topTr').css({height:topHEIGHT1+'px'});			
			$(this).find('span').removeClass('down').stop().css({backgroundPosition:'0 0'});
			$('#topContainer .toggleTop').attr('title','收起');
			topHeight=topHEIGHT1;
			GS_topMenu.status='opened';
			$('.logo').show();
			$('.logo-s').hide();
			initialHeight();
		}else{
			$('#topContainer').css({top:'-60px'});
			$('#topTr').css({height:topHEIGHT2+'px'});	
			
			$(this).find('span').addClass('down').stop().css({backgroundPosition:'-24px -14px'});
			$('#topContainer .toggleTop').attr('title','展开');
			topHeight=topHEIGHT2;
			GS_topMenu.status='closed';
			$('.logo').hide();
			$('.logo-s').show();
			initialHeight();
		}
		//console.log(GS_topMenu.status,topHeight)
})

//$('#topContainer .toggleTop').trigger('click');//初始化根据json 定义先模拟点击一次

$('#topContainer .toggleTop .ani').mouseenter(function(){
	if(!$(this).hasClass('down')){
		$(this).css({backgroundPosition:'0 0'}).stop().animate({backgroundPosition:'0 -14px'},600);
	}else{
		$(this).css({backgroundPosition:'-24px -14px'}).stop().animate({backgroundPosition:'-24px 0'},600);
	}
})

$('.more .m').click(function(){
	var l=$(this).offset().left-$('#subMenu').width()+$(this).width();
	var t=$(this).offset().top+30;
	if(GS_topMenu.status=='closed'){
		t=$(this).offset().top+90;
	}
	var w=$('#subMenu').width();
	$('#subMenu').css({left:l+'px',top:t+'px'});
	if($.browser.msie&&parseInt($.browser.version)<7){
		$('#subMenu').bgiframe();
	}
	
	//$(this).css({background:'#fff'})
	$(this).addClass('mopen');
	return false;
})

$('#subMenu').mouseleave(function(){
	$(this).css({top:'-9999px',left:'-9999px'});
	$('.more .m').removeClass('mopen');
})

$('#subMenu').click(function(){
	clearAllCurrent();
	$(this).css({top:'-9999px',left:'-9999px'});
	$('.more .m').removeClass('mopen');
})

$('body').click(function(){
	$('#subMenu').css({top:'-9999px',left:'-9999px'});
	$('.more .m').removeClass('mopen');
	$('#skin .chooser').hide();
})

$('#q').css({opacity:0.3}).focus(function(){
	$(this).addClass('focus').stop().animate({opacity:1,width:'160px'},'fast');
}).blur(function(){
	$(this).removeClass('focus').stop().animate({opacity:0.3,width:'80px'},'fast');
})

//skins----
$('#skin').mouseenter(function(){
	clearInterval(hoverTimer);
	var obj=$(this).find('.chooser');
	hoverTimer=setTimeout(function(){
		var mleft=obj.width()/2-6;
		obj.css({'margin-left':'-'+mleft+'px'}).show().disableSelection();
		return false;
	},hoverDelay);
}).mouseleave(function(){
 	clearInterval(hoverTimer);
	hoverTimer=setTimeout(function(){
		$('#skin .chooser').hide();
	},hoverDelay)
}).click(function(){
	return false;
})

$('#skin .chooser').mouseleave(function(){
	clearInterval(hoverTimer);
	$(this).hide();	
});

$('#skin .chooser li').click(function(){
	var skinNum=$(this).text();
	if(skinNum=='*'){skinNum=Math.ceil(Math.random()*($('#skin li').length-1));}
	$('body').attr('class','').addClass('skin'+skinNum);
	$('#skin .chooser').hide();
	$('#skin .chooser li').removeClass('current');
	$(this).addClass('current');
	return false;
}).disableSelection();

if($.browser.version<7&&$.browser.msie){//if ie 6
	$('#skin .chooser li').mouseenter(function(){
		$(this).addClass('hover');
	}).mouseleave(function(){
		$(this).removeClass('hover');
	})
}

//搜索，个性化设置 清除 current
$('.topNavi .preferences').click(clearAllCurrent);
$('#topSearch').submit(clearAllCurrent);

$('#backspace').click(function(){
	window.mainIframe.history.go(-1);
	//window.mainIframe.location.reload();
	clearAllCurrent();
	//console.log(window.mainIframe.history)
})

$('#forward').click(function(){
	window.mainIframe.history.go(1);
	//window.mainIframe.location.reload();
	clearAllCurrent();
	//console.log(window.mainIframe.history)
})

$('#refresh').click(function(){
	window.mainIframe.location.reload();
	//console.log(window.mainIframe.history)
})

$('div.backForward span').hoverClass('hover');

/*portal menu 2012-10-18*/

var portalTimer=null;
$('#portalMenuBtn').live('mouseenter',function(){
	clearInterval(portalTimer);
	$('#portalMenuUl').show();
	$(this).addClass('h');
}).live('mouseleave',function(){
	portalTimer=setTimeout(function(){
		$('#portalMenuUl').hide();
		$(this).removeClass('h');
	},1000)
})


$('#portalMenuUl li').live('mouseenter',function(){
	clearInterval(portalTimer);
	$(this).addClass('hover');
}).live('mouseleave',function(){
	$(this).removeClass('hover');
}).live('click',function(){
	$('#portalMenuUl').hide();
})

$('#portalMenuUl li.l1').live('mouseenter',function(){
	$('#portalMenuUl').find('ul').hide();
	$(this).find('>ul').show();
})

$('#portalMenuUl li.l2').live('click',function(){
	window.mainIframe.location=$(this).attr('data-src');
})

$('#portalMenuUl').live('mouseleave',function(){
	portalTimer=setTimeout(function(){
		$('#portalMenuUl').hide();
		$('#portalMenuBtn').removeClass('h');
	},1000)
})





/////////////////////////////////////
})