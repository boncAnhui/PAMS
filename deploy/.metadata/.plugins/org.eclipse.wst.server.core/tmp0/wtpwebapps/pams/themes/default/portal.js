jQuery(function($){
///////////////////

try{
	if(portal_ID&&portal_ajax_url){
		$.ajax({
			url:portal_ajax_url,
			data:{portal_id:portal_ID},
			async:false,
			cache:false,
			success:function(d){
				eval('GS_portalMenu='+d);
			}	
		})	
	}
	
}catch(e){}

var portalHtml='';
$.each(GS_portalMenu,function(i,k){
	if(k.status=='closed'){
		portalHtml+='<div class="psec"><h2 data-id="'+k.id+'" class="collapsable closed"><span class="t">'+k.name+'</span></h2>';
		portalHtml+='<ul class="portal hide">';
	}else{
		portalHtml+='<div class="psec"><h2 data-id="'+k.id+'"  class="collapsable"><span class="t">'+k.name+'</span></h2>';	
		portalHtml+='<ul class="portal">';
	}
		
$.each(k.items,function(n,m){
			portalHtml+='<li><a title="'+m.name+'" href="'+m.link+'"><span class="g"><span class="i '+m.icon+'"></span></span><span class="t">'+m.name+'</span></a></li>';
	})
	portalHtml+='</ul></div>';
})

$('#pageContainer').empty().append(portalHtml);

$('ul.portal  li').mouseenter(function(){
	$(this).addClass('hover');
}).mouseleave(function(){
	$(this).removeClass('hover');
})

//temp code | modal that is ready
/*
$('ul.portal  li').each(function(){
	if($(this).find('a').attr('href')!='about:blank'){
		//$(this).css({'background':'red'})
		$(this).addClass('ready');
		}
})
*/

$('div.portalPage').sortable({handle:'h2',placeholder:'ui-sortable-placeholder',stop:function(){
	var oIndex=[];
	$(this).find('h2').each(function(j,k){
		ostatus='opened';
		if($(this).hasClass('closed')){ostatus='closed';}
		oIndex.push($(k).attr('data-id')+':'+ostatus)
	})
	
	//console.log(oIndex.join(','),'     ajax提交顺序');

	$.ajax({
		url:'save-portal-index.php',
		data:{order:oIndex.join(',')},
		success:function(){
			//
		}
	})
}});


	

$('h2.collapsable .t').click(function(){
	var othis=$(this);
	var obj=$(this).parent();
	var oStatus=obj.hasClass('closed')?'closed':'opened';
	
	$.ajax({
		url:'save-portal-status.php',
		data:{id:obj.attr('data-id'),status:oStatus},
		//async:false,
		beforeSend:function(){
			if(othis.find('.ajaxAni').length==0){
				othis.append('<span class="ajaxAni"></span>')	
			}
		},
		success:function(){
			//var oPos=othis.position();
			//console.log(oPos.top,oPos.left)
			othis.find('.ajaxAni').remove();
//$('#portalAjaxDone').css({left:oPos.left+othis.width()+'px',top:oPos.top+othis.height()+'px',width:othis.width()})
		},complete:function(){
			othis.find('.ajaxAni').remove();
		}
	})
		
})

//$('body').append('<div id="portalAjaxDone" style="position:absolute;left:0;top:0;background:transparent;text-align:center;">11111</div>')

//portal menu
var portalMenuHtml='<div id="portalMenuBtn"></div><ul id="portalMenuUl">';
$.each(GS_portalMenu,function(j,k){
	portalMenuHtml+='<li class="l1"><div class="p p1">'+k.name+'</div><ul class="u2">';
		$.each(k.items,function(n,m){
			portalMenuHtml+='<li class="l2" data-src="'+m.link+'"><div class="p">'+m.name+'</div></li>';
		});
	portalMenuHtml+='</ul></li>';
});
portalMenuHtml+='</ul>';

$('#portalMenu',window.top.document).empty().append(portalMenuHtml);

//console.log($('#portalMenuUl',window.top.document).width())
$('#portalMenuUl ul.u2',window.top.document).css({left:$('#portalMenuUl',window.top.document).width()+'px'});

///////////////////
})