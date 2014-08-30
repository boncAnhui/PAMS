function value2id(source,aim){
	var sourceArr=source.split(',');
	var aimArr=aim.split(',');
	$.each(aimArr,function(j,k){
		//console.log($('#'+k))
		$('#'+k).val(sourceArr[j])
	})
}

jQuery(function($){
///////////////////////////////

$('input.ajax').wrap('<span class="ajaxInputWrapper"></span>').after('<div class="ajaxDiv"></div>');

$('.ajaxDiv').each(function(){
	//firefox ie6-7 可以，ie8910可能会有问题 需要单独写
	$(this).css({'min-width':$(this).parent().find('input').width()+24-6});
	if($.browser.msie&&parseInt($.browser.version)<8){
		//$(this).css({'width':$(this).parent().find('input').width()+24-6,background:'red'});
		//$('.ajaxInputWrapper').css({overflow:'visible'})
		//$(this).css({'width':'10px',overflow:'visible'});
	}
});

var ajaxTimer=null;
var ajaxStartSize=0;

$('input.ajax').keyup(function(e){
	
	if(e.keyCode==13){return false;}
	if($.trim($(this).val())==''){return false;}
	
	//每次敲键盘，清理 ajaxInput.attr('data-aim') 为空；他们如果有默认值就惨了 :(
	var aimArr=$(this).attr('data-aim').split(',');
	
	var othis=$(this);
	
	$.each(aimArr,function(j,k){
		//console.log($(this))
		//alert(aimArr[0]+' :::::  '+othis.attr('id'))
		
		//console.log()
		
		if(aimArr[0]==$('#'+k).attr('id')){
			
		}
		else{
			$('#'+k).val('')
		}
	})
	

	
	var $this=$(this);
	var $ajaxDiv=$this.parent().find('.ajaxDiv').find('tbody');

	if(e.keyCode==13){
		$('.ajaxDiv').hide();
		return false;
	}
	
	if($(this).val().length>ajaxStartSize){
	///////////////////////
	clearInterval(ajaxTimer);
	
	if(!$(this).attr('data-ajax-url'))return false;
	var ajaxUrl=$(this).attr('data-ajax-url');
	
	eval('var odata=new Object({'+$this.attr('data-key')+':"'+$this.val()+'"})');
	
	ajaxTimer=setTimeout(function(){
		$.ajax({
			url:ajaxUrl,
			type:'post',
			data:odata,
			beforeSend:function(j,k){
				$this.addClass('ajaxAni');
				},
			success:function(d,k){
				$this.removeClass('ajaxAni');
				if(k!='success'){return false;}
				eval('var d='+d);
				var ohtml='<table>';
				
				ohtml+='<thead><tr>';
				$.each(d.th,function(j,k){
					//ohtml+='<th>'+k+'</th>';
					if(j==d.th.length-1){
						ohtml+='<th><span class="close">X</span>'+k+'</th>';
					}else{
						ohtml+='<th>'+k+'</th>';
					}					
				})
				ohtml+='</tr></thead><tbody>';
				
				$.each(d.items,function(j,k){					
					//如果返回多值 需要先在这里 date-xxx="xxxxx"
					
					//$(this).attr('data-ajax-url')
					var sourceArr=$this.attr('data-source').split(',');
					
					if(sourceArr.length>0){
						var arr=[];
						$.each(sourceArr,function(n,m){
							arr.push(k[m])
						})
						sourceHtml='data-source="'+arr.join(',')+'"'
						//console.log(sourceHtml)
					}
					
					
					/*ohtml+='<tr '+sourceHtml+'data-id="'+k.id+'"><td>'+k.name+'</td>';
					$.each(k.cells,function(n,m){
						alert(m)
						ohtml+='<td>'+m+'</td>';
					})*/
					
					ohtml+='<tr '+sourceHtml+'data-id="'+k.id+'">';
					$.each(k.cells,function(n,m){
						//alert(m)
						ohtml+='<td>'+m+'</td>';
					})
					ohtml+='</tr>';
				})
				ohtml+='</tbody></table>';
				$('.ajaxDiv').hide();//避免多个同时打开
				$this.parent().find('.ajaxDiv').css({top:($this.position().top+$this.height())+'px',left:$this.position().left+'px'}).empty().append(ohtml).show();
			}
		})
	
	},500)
	///////////////////
	}
})

/*
$('input.ajax').focus(function(){
	if($(this).val().length>ajaxStartSize){
		$(this).parents('.ajaxInputWrapper').find('.ajaxDiv').show();
	}
}).click(function(e){
	if($(this).val().length>ajaxStartSize){
		$(this).parents('.ajaxInputWrapper').find('.ajaxDiv').show();
	}
	e.stopPropagation();
})
*/

$('.ajaxDiv tbody tr').live('mouseenter',function(){$(this).addClass('h');}).live('mouseleave',function(){$(this).removeClass('h');}).live('click',function(e){
		var oParent=$(this).parents('.ajaxInputWrapper');
		var ajaxInput=oParent.find('.ajax');
		ajaxInput.val($(this).find('td:first').text())
		
		value2id($(this).attr('data-source'),ajaxInput.attr('data-aim'))
		//$('#k1').val($(this).attr('data-id'))
		$('.ajaxDiv').hide();
		e.stopPropagation();
})

$(document).click(function(e){
	$('.ajaxDiv').hide();
})

///////////////////////////////
})