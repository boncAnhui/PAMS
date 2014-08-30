$(function(){
/////////////////////////////////////////

//initial html
var ohtml='';
$.each(homeJson,function(j,k){
	if(k.visable!="0"){
		ohtml+='<div class="psec psec_'+k.pname+'"><h2 data-id="'+k.id+'" class="collapsable"><span class="t">'+k.name+'</span></h2></div>';
	}
	
})

$('#homepage').append(ohtml);

$('#homepage').find('div.psec').each(function(j,k){
	var thisDiv=$(this);
	$.ajax({
		url:homeJson[j].url,
		data:{},
		cache:false,
		success:function(d){
			eval('var d='+d);
			var ohtml='';
			//console.log(d.type)
			if(homeJson[j].type=='list'){//如果普通列表
			ohtml='<ul>';
			$.each(d,function(n,m){
				ohtml+='<li ><span class="date">'+m.date+'</span><a class="signstate_'+m.signstate+'" href="'+m.url+'" title="'+m.name+'">'+m.name+'</a></li>'
			})
			ohtml+='</ul>';
			}else if(homeJson[j].type=='flashMap'){
				var flashdata="<tags><a href='home.htm' style='font-size:+28pt;'>项目任务</a><a href='home.htm' style='font-size:+28pt;'>配置管理</a><a href='home.htm' style='font-size:+28pt;'>信息运维</a><a href='home.htm' style='font-size:+28pt;'>SQL常用语句</a><a href='http://127.0.0.1/itms2012/html05/home.htm' style='font-size:+28pt;'>网络维护</a></tags>";
				thisDiv.append('<div id="flashDiv">需要flash</div>');
				//////
			var fixFlashHeight=265;
			if($.browser.msie&&$.browser.version<8){
				//what ever damn ie7 mode
				//console.log(document.compatMode)
				if(document.compatMode=='BackCompat'){
					fixFlashHeight=268;
				}else if(document.compatMode=='CSS1Compat'){
					fixFlashHeight=264;
				}
			}
			//
			$('#flashDiv').empty().append('<object type="application/x-shockwave-flash" data="tagcloud.swf?r=3371772" height="'+fixFlashHeight+'" width="360"><param name="movie" value="tagcloud.swf?r=3371772"><param name="bgcolor" value="#fffff"><param name="AllowScriptAccess" value="sameDomain"><param name="wmode" value="transparent"><param name="flashvars" value="tcolor=0x005290&amp;tcolor2=0xcc0000&amp;hicolor=0xcccccc&amp;tspeed=100&amp;distr=true&amp;mode=tags&amp;tagcloud='+flashdata+'"></object>');
			//////
				
				
			}
			thisDiv.append(ohtml)
			
			
			
		}
	})
})


//设置首页 current
$('.topMenu li:first',top.document).addClass('current');

$('#homepage').sortable({handle:'h2',placeholder:'ui-sortable-placeholder',stop:function(){
	var oIndex=[];
	$(this).find('h2').each(function(j,k){
		oIndex.push($(k).attr('data-id'));
	}),
	//console.log(oIndex,'ajax提交顺序');
	$.ajax({
		url:'save-portal-index.php',
		data:{order:oIndex},
		success:function(){
			//
		}
	})
}});



/////////////////////////////////////////
})

$(window).load(function(){
	//$('#switchHome,#currentHomeIndi',window.parent.document).css({visibility:'visible'})
})