/*js for all project by davidguoshuang at gmail.com*/

var pub_width_mini = "320";
var pub_height_mini = "180";

var pub_width_small = "640";
var pub_height_small = "360"; 

var pub_width_mid = "800";
var pub_height_mid = "450";

var pub_width_large = "960";
var pub_height_large = "540";

//console.log for ie6-7
if(!window.console){
	window.console={log:function(a){
		alert('console.log输出为：'+a);
	}}
}

function num2digital(n){
	if(n<10){n='0'+n}
	n=n+'';
	return n;
}
//周期时间显示转换程序
function readCron(cron){
	var cweek='一二三四五六日';
	var ca=cron.split(' ');
	if (ca[5]=='?'&&ca[4]=='*'&&ca[3]=='*'&&ca[2]=='*'){
		document.write('每小时 第'+ca[1]+'分钟')
	}else if (ca[5]=='?'&&ca[4]=='*'&&ca[3]=='*'){
		document.write('每天 '+num2digital(ca[2])+':'+num2digital(ca[1]))
	}else if (ca[5]=='?'&&ca[4]=='*'){
		document.write('每月的 '+ca[3]+'日 '+num2digital(ca[2])+':'+num2digital(ca[1]))
	}else if (ca[4]=='*'){
		document.write('每周'+cweek.charAt(ca[5]-1)+'的 '+num2digital(ca[2])+':'+num2digital(ca[1]))
	}else if (ca[5]=='?'){
		document.write('每年的 '+ca[4]+'月'+ca[3]+'日 '+num2digital(ca[2])+':'+num2digital(ca[1]))
	}
}


//通用查询 翻页函数
function pub_query_selectpage(pagenum)
{
	// document.all.form_view.page.value = pagenum;
	// $('#form_view').find('[name=page]').val(pagenum);
	 $('#form_view').find('[name=page]').val(pagenum);
	 $('#form_view').find('[name=step]').val($('#jumppagesize').val());
	 
}

function pub_query_go2page(url)
{
	$('#form_view').attr("action", url);
	$('#form_view').submit();
	
//	document.all.form_view.action = url;
//	document.all.form_view.submit();
}

function pub_query_jump2page(pagenum, url)
{
    $('#form_view').find('[name=page]').val(pagenum);
	$('#form_view').attr("action", url);  
	$('#form_view').submit();
	// document.all.form_view.page.value = pagenum;
	// document.all.form_view.action = url;
	// document.all.form_view.submit();	
}

$(function()
{	
  	// 视图导航条翻页事件
	
	$('#jumppageindex').keydown(function(e)
	{
		//console.log(e.keyCode);
	    //alert(e.keyCode)
		if(e.keyCode==13)
	    {
			// 将导航的页码数传至通用查询表单的page字段；
			// 将导航的视图行数传至通用查询表单的step字段；
			
	    	$('#form_view').find('[name=page]').val($('#jumppageindex').val());
	    	$('#form_view').find('[name=step]').val($('#jumppagesize').val());
	    	
	    	$('#form_view').submit();
	    	return false;
	    }
	})
	
	$('#jumppagesize').keydown(function(e)
	{
		//console.log(e.keyCode);
	    //alert(e.keyCode)
		if(e.keyCode==13)
	    {
			// 将导航的页码数传至通用查询表单的page字段；
			// 将导航的视图行数传至通用查询表单的step字段；
			//alert($('#jumppagesize').val());
	    	$('#form_view').find('[name=page]').val($('#jumppageindex').val());
	    	$('#form_view').find('[name=step]').val($('#jumppagesize').val());
	    	
	    	$('#form_view').submit();
	    	return false;
	    }
	})				
})


//判断后面时间大于前面时间
function comparetotime(begintime,endtime)
{
	var btime = new Date(begintime.replace("-","/")).getTime();
	var etime = new Date(endtime.replace("-","/")).getTime();
	if(etime - btime>=0)
	{
		return true;
	}
	else
	{
		return false;
	}
}

//日期函数
function pub_date_format_value(cdate)
{
	
	year = cdate.getFullYear();
	month = cdate.getMonth() + 1;
	day = cdate.getDate();
	
	if(month < 10)
	{
		month = "0" + month;
	}
	
	if(day < 10)
	{
		day = "0" + day;
	}
	
	adate = year + "-" + month + "-" + day;

	return adate;
}

// 字符串转换为日期
function pub_string_to_date(cvalue)
{
	/*fcvalue = cvalue.replace(/-/g, "/");
	adate  = new Date(Date.parse(fcvalue));
	return adate;
	*/
	//console.log(Date.parse(fcvalue))
	
}

pub_string_to_date=Date.parse;
	


// 计算指定时差日期
function pub_date_add(cdate, field, nums)
{
	adate = new Date(cdate.getFullYear(), cdate.getMonth(), cdate.getDate());
	
	if(field == "D")
	{
		adate = new Date(adate.getFullYear(), adate.getMonth(), adate.getDate() + nums);
		return adate;
	}
	
	if(field == "M")
	{
		adate = new Date(adate.getFullYear(), adate.getMonth() + (nums), adate.getDate());
		return adate;
	}
	
	if(field == "Y")
	{
		adate = new Date(adate.getFullYear() + nums, adate.getMonth(), adate.getDate());
		return adate;
	}
}

//获得天的开始日期
function pub_day_begin_date(cdate, nums)
{
	adate = new Date(cdate.getFullYear(), cdate.getMonth(), cdate.getDate() + nums); 
	return adate;
}

//获得天的开始日期
function pub_day_end_date(cdate, nums)
{
	adate = new Date(cdate.getFullYear(), cdate.getMonth(), cdate.getDate() + nums); 
	return adate;
}


// 获得周的开始日期
function pub_week_begin_date(cdate, nums)
{
	adate = new Date(cdate.getFullYear(), cdate.getMonth(), cdate.getDate() - cdate.getDay() + 1); 
	adate = new Date(adate.getFullYear(), adate.getMonth(), adate.getDate() + (nums * 7));
	return adate;
}

// 获得周的结束日期
function pub_week_end_date(cdate, nums)
{
	adate = new Date(cdate.getFullYear(), cdate.getMonth(), cdate.getDate() + (6 - cdate.getDay()) + 1); 
	adate = new Date(adate.getFullYear(), adate.getMonth(), adate.getDate() + (nums * 7));
	return adate;
}

// 获得月的开始日期
function pub_month_begin_date(cdate, nums)
{
	adate = new Date(cdate.getFullYear(), cdate.getMonth(), 1);
	adate = new Date(adate.getFullYear(), adate.getMonth() + (nums), adate.getDate());
	return adate;
}

// 获得月的结束日期    
function pub_month_end_date(cdate, nums)
{
	adate = new Date(cdate.getFullYear(), cdate.getMonth() + (nums), 1);
	maxday = pub_max_day_of_month(adate.getFullYear(), adate.getMonth());
	adate = new Date(adate.getFullYear(), adate.getMonth(), maxday);	
	return adate;
}

// 获得季度的开始日期      
function pub_quarter_begin_date(cdate, nums)
{      
    adate = new Date(cdate.getFullYear(), get_quarter_begin_month(cdate), 1);
    adate = new Date(adate.getFullYear(), adate.getMonth() + (nums * 3), 1);
    return adate;
}

// 获得季度的结束日期 
function pub_quarter_end_date(cdate, nums)
{
	quarter_end_month = get_quarter_begin_month(cdate) + 2;
    adate = new Date(cdate.getFullYear(), quarter_end_month, 1);
    adate = new Date(adate.getFullYear(), adate.getMonth() + (nums * 3), 1);
    
	
	max_day = pub_max_day_of_month(adate.getFullYear(), adate.getMonth());
    adate = new Date(adate.getFullYear(), adate.getMonth(), max_day);       
    return adate;     
}

// 获得半年的开始日期
function pub_halfyear_begin_date(cdate, nums)
{
	cmonth = 0;
	if(cdate.getMonth()>=6)
	{
		cmonth = 6; 
	}
	
	adate = new Date(cdate.getFullYear(), cmonth + (nums * 6), 1);
	return adate;
}

//获得半年的结束日期
function pub_halfyear_end_date(cdate, nums)
{
	cmonth = 5;
	if(cdate.getMonth()>5)
	{
		cmonth = 11; 
	}

	adate = new Date(cdate.getFullYear(), cmonth + (nums * 6), 1);
	
	max_day = pub_max_day_of_month(adate.getFullYear(), adate.getMonth());	
	adate = new Date(adate.getFullYear(), adate.getMonth(), max_day);
	
	return adate;
}

//获得年的开始日期
function pub_year_begin_date(cdate, nums)
{
	cmonth = 0;
	if(cdate.getMonth()>=12)
	{
		cmonth = 12; 
	}
	
	adate = new Date(cdate.getFullYear(), cmonth + (nums * 12), 1);
	return adate;
}

//获得年的结束日期
function pub_year_end_date(cdate, nums)
{
	cmonth = 12;
	if(cdate.getMonth()>12)
	{
		cmonth = 0; 
	}

	adate = new Date(cdate.getFullYear(), cmonth + (nums * 12), 1);
	
	max_day = pub_max_day_of_month(adate.getFullYear(), adate.getMonth());	
	adate = new Date(adate.getFullYear(), adate.getMonth(), max_day);
	
	return adate;
}

// 计算本月的最大天数      
function pub_max_day_of_month(cyear, cmonth)
{
	var days = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	if (((cyear % 4 == 0) && (cyear % 100 != 0)) || (cyear % 400 == 0))
	{
		 days[1] = "29";
	}
	return days[cmonth];
}

// 计算本季度的开始月份      
function get_quarter_begin_month(cdate)
{
	now_month = cdate.getMonth();

    var quarter_begin_month = 0;
          
    if(now_month<3)
    {      
       quarter_begin_month = 0;      
    }      
    if(2<now_month && now_month<6)
    {      
       quarter_begin_month = 3;      
    }      
    if(5<now_month && now_month<9)
    {      
       quarter_begin_month = 6;      
    }      
    if(now_month>8)
    {      
       quarter_begin_month = 9;      
    }      
    return quarter_begin_month;      
}  




function pub_void()
{
}	


var defaultWinWidth=600;
var defaultWinHeight=400;
var dataGridTextLimit=16;//限制dataGrid td里面XX个字符

var hoverDelay=300,hoverTimer;//延迟显示 避免鼠标划过的闪烁
var isIE6=false,isIE67=false,isIE7=false;
if($.browser.msie&&$.browser.version<8){isIE67=true;}
if($.browser.msie&&$.browser.version<7){isIE6=true;}
if($.browser.msie&&parseInt($.browser.version)==7){isIE7=true;}

function ajax4Tree(url,num){
	
var ohtml='';
$.ajax({
    url:url,
    data:{id:num},
    cache:false,
    async:false,
    success:function(d){
        eval('var d='+d);		
        $.each(d.items,function(j,k){
			ohtml+='<li data-leaf="'+k.leaf+'" data-id="'+k.id+'"><span class="'+k.type+'">'+k.name+'</span></li>';
        })
    }
})
return ohtml;
}







function openwin(url,winname,w,h,handler){
	if(!winname||winname==''){winname='win'+parseInt(Math.random());}
	if(!w){w=defaultWinWidth;}
	if(!h){h=defaultWinHeight;}	
	handlerChar='';
	if(handler){handlerChar='&handler='+handler}
	//console.log(handlerChar)
	winname=window.open(url+handlerChar,winname,'width='+w+',height='+h+',left='+(window.screen.availWidth-w)/2+',top='+(window.screen.availHeight-h)/2+',location=1,toolbar=0,scrollbars=1,menubar=0,resizable=1,status=1');
	winname.focus();
}

function openwinT (url,winname,w,h,handler,title){
	if(!winname||winname==''){winname='win'+parseInt(Math.random());}
	if(!w){w=defaultWinWidth;}
	if(!h){h=defaultWinHeight;}	
	handlerChar='';
	if(handler){handlerChar='&handler='+handler}
	//console.log(handlerChar)
	winname=window.open(url+handlerChar+'#title='+encodeURIComponent(title),winname,'width='+w+',height='+h+',left='+(window.screen.availWidth-w)/2+',top='+(window.screen.availHeight-h)/2+',location=1,toolbar=0,scrollbars=1,menubar=0,resizable=1,status=1');
	winname.focus();
}


function ajaxValidate(objArray){
	var ok=true;
	var info='';
	$.each(objArray,function(){
		obj=$(this);
		obj.removeClass('error');
		obj.parents('td').prev().find('label').removeClass('error');
		
		if(obj.hasClass('required')&&obj.val()==''){//required
		info='<strong>'+obj.parents('td').prev().text().replace('：','').replace(':','')+'</strong>是必填项目';
		$('.ui-dialog').remove();
		$('<div class="popContent">'+info+'</div>').dialog({bgiframe:true,width:'300px'});
		obj.addClass('error').parents('td').prev().find('label').addClass('error');
		setTimeout(function(){
			$('.popContent').dialog('close')
		},2000)
		obj.trigger('focus');
		ok=false;
		return false;
	}
	else if(obj.val()!=''&&obj.hasClass('email')&&!(obj.val().match(/^\w+@[a-zA-Z_0-9]+\.[a-zA-Z]{2,3}$/))){//email
		info='<strong>'+obj.parents('td').prev().text().replace('：','').replace(':','')+'</strong>必须有效';
		$('.ui-dialog').remove();
		$('<div class="popContent">'+info+'</div>').dialog({bgiframe:true,width:'300px'});
		obj.addClass('error').parents('td').prev().find('label').addClass('error');
		setTimeout(function(){
			$('.popContent').dialog('close')
		},2000)
		obj.trigger('focus');
		ok=false;
		return false;
	}else if(obj.val()!=''&&obj.hasClass('phone')&&!(obj.val().match(/^[0-9\-]+$/))){//phone
		info='<strong>'+obj.parents('td').prev().text().replace('：','').replace(':','')+'</strong>必须有效';
		$('.ui-dialog').remove();
		$('<div class="popContent">'+info+'</div>').dialog({bgiframe:true,width:'300px'});
		obj.addClass('error').parents('td').prev().find('label').addClass('error');
		setTimeout(function(){
			$('.popContent').dialog('close')
		},2000)
		obj.trigger('focus');
		ok=false;
		return false;
	}else if(obj.val()!=''&&obj.hasClass('date')&&!(obj.val().match(/^[1-2]+[0-9]{3}-[0-1]+[0-9]+-[0-3]+[0-9]+$/))){//date
		info='<strong>'+obj.parents('td').prev().text().replace('：','').replace(':','')+'</strong>必须是有效日期';
		$('.ui-dialog').remove();
		$('<div class="popContent">'+info+'</div>').dialog({bgiframe:true,width:'300px'});
		obj.addClass('error').parents('td').prev().find('label').addClass('error');
		setTimeout(function(){
			$('.popContent').dialog('close')
		},2000)
		obj.trigger('focus');
		ok=false;
		return false;
	}else if(obj.val()!=''&&obj.hasClass('int')&&!(obj.val().match(/^-?[0-9]+$/))){//int
		info='<strong>'+obj.parents('td').prev().text().replace('：','').replace(':','')+'</strong>必须是整数';
		$('.ui-dialog').remove();
		$('<div class="popContent">'+info+'</div>').dialog({bgiframe:true,width:'300px'});
		obj.addClass('error').parents('td').prev().find('label').addClass('error');
		setTimeout(function(){
			$('.popContent').dialog('close')
		},2000)
		obj.trigger('focus');
		ok=false;
		return false;
	}else if(obj.val()!=''&&obj.hasClass('num')&&isNaN(obj.val())){//int
			info='<strong>'+obj.parents('td').prev().text().replace('：','').replace(':','')+'</strong>必须是数字';
			$('.ui-dialog').remove();
			$('<div class="popContent">'+info+'</div>').dialog({bgiframe:true,width:'300px'});
			obj.addClass('error').parents('td').prev().find('label').addClass('error');
			setTimeout(function(){
			$('.popContent').dialog('close')
		},2000)
		obj.trigger('focus');
			ok=false;
			return false;
		}

	})
	return ok;
}

function initialTabsHeight(){
	var avialHeight=$('body').height()-30;
	$('#gTabsContainter iframe').css({height:avialHeight+'px'});
	$('.bottomTr').css({height:avialHeight+'px'})
}

//ready start------------------------------------

jQuery(function($){
////////////////

$('#switchHome,#currentHomeIndi',window.parent.document).css({visibility:'hidden'})

//框架中没有 marginTop 新窗口有
if(window.top==window.self){
	$('#gTabsContainterN').css({'margin-top':'6px'})
}

//框架中没有 marginTop 新窗口有
/*if(window.top==window.self){
	$('#gTabsContainterN').css({'margin-top':'6px'})
}

if($('#gTabsContainterN',window.parent.document).length!=0){
	//$('#fixedOp').css({'margin-top':'5px'})
}
*/

if($('#gTabsContainterN',window.parent.document).length!=0){
	$('#fixedOp').css({'background-position':'0 -1px'})
}

$('#fixedOp').prepend('<span id="stickToolbar" title="操作条放在下方"><span></span></span>').before('<div id="fixedOpPlaceHolder"></div>');

//hide ie focus dotted line
$('a').attr('hidefocus','true;');

$('a[target=newWin]').addClass('newWin').click(function(){
	openwin($(this).attr('href'),'newWin')
});

//button
$('button').each(function(){
		$(this).wrap('<span class="btnWrapper"></span>');	
}).click(function(){
	return false;//关闭浏览器默认的提交，因为有的浏览器自动提交，有些不。以后提交需要明确  trigger('submit')	
})

$('button').click(function(){
	if($(this).attr('data-url')){
		if($(this).attr('data-target')){
			var w=$(this).attr('data-w')==''?defaultWinWidth:$(this).attr('data-w');
			var h=$(this).attr('data-h')==''?defaultWinHeight:$(this).attr('data-h');
			openwin($(this).attr('data-url'),$(this).attr('data-target'),w,h)
		}else{
			location=$(this).attr('data-url');		
		}
	}
})

$('button.cancel').click(function(){
	window.close();	
})

$('.gTabs .tabUl li').not('.c').hoverClass('hover');

//按钮根据文字 给出 新增、删除等图标
$('button').each(function(){
    if($(this).text().indexOf('新增')!=-1||$(this).text().indexOf('添加')!=-1||$(this).text().indexOf('追加')!=-1||$(this).text().indexOf('新建')!=-1){
            $(this).wrapInner('<span class="add"></span>'); 
    }else if($(this).text().indexOf('删除')!=-1||$(this).text().indexOf('移除')!=-1||$(this).text().indexOf('清除')!=-1|| $(this).text().indexOf('作废')!=-1){
            $(this).wrapInner('<span class="del"></span>'); 
    }else if($(this).text().indexOf('保存')!=-1){
            $(this).wrapInner('<span class="save"></span>');        
    }else if($(this).text().indexOf('关闭')!=-1||$(this).text().indexOf('取消')!=-1){
            $(this).wrapInner('<span class="close"></span>');       
    }else if($(this).text().indexOf('修改')!=-1){
            $(this).wrapInner('<span class="edit"></span>');        
    }else if($(this).text().indexOf('发布')!=-1||$(this).text().indexOf('生效')!=-1){
            $(this).wrapInner('<span class="publish"></span>');     
    }else if($(this).text().indexOf('迁移')!=-1||$(this).text().indexOf('接单')!=-1||$(this).text().indexOf('派单')!=-1){
            $(this).wrapInner('<span class="move"></span>');        
    }else if($(this).text().indexOf('确认')!=-1||$(this).text().indexOf('确定')!=-1||$(this).text().indexOf('提交')!=-1){
            $(this).wrapInner('<span class="confirm"></span>');     
    }else if($(this).text().indexOf('签收')!=-1){
            $(this).wrapInner('<span class="sign"></span>');        
    }else if($(this).text().indexOf('转发')!=-1||$(this).text().indexOf('转出')!=-1 || $(this).text().indexOf('交接')!=-1 || $(this).text().indexOf('转建设')!=-1){
            $(this).wrapInner('<span class="forward"></span>');     
    }else if($(this).text().indexOf('恢复')!=-1||$(this).text().indexOf('收回')!=-1){
            $(this).wrapInner('<span class="undo"></span>');        
    }else if($(this).text().indexOf('下达')!=-1){
            $(this).wrapInner('<span class="xiada"></span>');       
    }else if($(this).text().indexOf('特送')!=-1){
            $(this).wrapInner('<span class="tesong"></span>');      
    }else if($(this).text().indexOf('收回')!=-1){
            $(this).wrapInner('<span class="shouhui"></span>');     
    }else if($(this).text().indexOf('审核')!=-1){
            $(this).wrapInner('<span class="zhuan"></span>');       
    }else if($(this).text().indexOf('退回')!=-1){
            $(this).wrapInner('<span class="back"></span>');        
    }else if($(this).text().indexOf('移交')!=-1){
            $(this).wrapInner('<span class="give"></span>');        
    }else if($(this).text().indexOf('结束')!=-1){
            $(this).wrapInner('<span class="end"></span>'); 
    }else if($(this).text().indexOf('结题')!=-1){
            $(this).wrapInner('<span class="end2"></span>');        
    }else if($(this).text().indexOf('复制')!=-1){
            $(this).wrapInner('<span class="copy"></span>');        
    }else if($(this).text().indexOf('执行')!=-1||$(this).text().indexOf('通过')!=-1||$(this).text().indexOf('启用')!=-1||$(this).text().indexOf('完成')!=-1){
            $(this).wrapInner('<span class="doit"></span>');        
    }else if($(this).text().indexOf('选择')!=-1){
            $(this).wrapInner('<span class="select"></span>');      
    }else if($(this).text().indexOf('下一步')!=-1||$(this).text().indexOf('审核')!=-1){
            $(this).wrapInner('<span class="next"></span>');        
    }else if($(this).text().indexOf('上一步')!=-1||$(this).text().indexOf('回退')!=-1){
            $(this).wrapInner('<span class="prev"></span>');        
    }else if($(this).text().indexOf('目录')!=-1||$(this).text().indexOf('库存')!=-1){
            $(this).wrapInner('<span class="folder"></span>');      
    }else if($(this).text().indexOf('模板')!=-1){
            $(this).wrapInner('<span class="template"></span>');    
    }else if($(this).text().indexOf('意见')!=-1){
            $(this).wrapInner('<span class="comment"></span>');     
    }else if($(this).text().indexOf('更名')!=-1){
            $(this).wrapInner('<span class="rename"></span>');      
    }else if($(this).text().indexOf('评估')!=-1){
            $(this).wrapInner('<span class="evaluate"></span>');    
    }else if($(this).text().indexOf('禁用')!=-1||$(this).text().indexOf('作废')!=-1){
            $(this).wrapInner('<span class="disable"></span>');     
    }else if($(this).text().indexOf('刷新')!=-1||$(this).text().indexOf('升级')!=-1){
            $(this).wrapInner('<span class="refresh"></span>');     
    }else if($(this).text().indexOf('打开')!=-1){
            $(this).wrapInner('<span class="open"></span>');     
    }else if($(this).text().indexOf('打印')!=-1){
            $(this).wrapInner('<span class="print"></span>');     
    }else if($(this).text().indexOf('配置')!=-1){
            $(this).wrapInner('<span class="config"></span>');     
    }else if($(this).text().indexOf('检出')!=-1){
            $(this).wrapInner('<span class="check"></span>');     
    }else if($(this).text().indexOf('暂停')!=-1){
            $(this).wrapInner('<span class="pause"></span>');     
    }
})


//button.wizard_next span.next

$('button.ajax').each(function(){
	if($(this).find('span').length!=0){
		$(this).find('span').addClass('ajax');
	}else{
		$(this).wrapInner('<span class="ajax"></span>');	
	}
})

//if($.browser.msie&&$.browser.version<7){
/*	$('button').mouseenter(function(){
		$(this).css({backgroundPosition:'0 0'});
	}).mouseleave(function(){
		$(this).css({backgroundPosition:'0 -90px'});
	})
*/
//}else{
	$('button').mouseenter(function(){
		if($(this).hasClass('ajax')){return;}
		$(this).css({backgroundPosition:'0 -90px',color:'#ffffff'});
		//$(this).stop().animate({backgroundPosition:'0 -90px',color:'#ffffff'},100,'swing');
	}).mouseleave(function(){
		if($(this).hasClass('ajax')){return;}
		$(this).css({backgroundPosition:'0 0',color:'#333333'});
		//$(this).stop().animate({backgroundPosition:'0 0',color:'#333333'},100,'swing');
	})
//}

$('.btnWrapper').mouseenter(function(){
	$(this).addClass('hover');
}).mouseleave(function(){
	$(this).removeClass('hover');
})

$('input.text').focus(function(){
	if($(this).attr('readonly')){return;} //readonly not focus
	$(this).addClass('textFocus');
}).blur(function(){
	$(this).removeClass('textFocus');
});

$('input[readonly]').addClass('readonly').focus(function(){
	$(this).blur();
})



//inital position /*离开，但是进入菜单的话 不隐藏！*/
$('.moreUpDiv,.moreDownDiv').css({'margin-left':'-'+($('.moreUpDiv ul').width()+1)+'px'});

$('.moreUp').mouseenter(function(){
	clearInterval(hoverTimer);
	hoverTimer=setTimeout(function(){
		$('.moreUpDiv').css({visibility:'visible'});
	},hoverDelay);	
}).mouseleave(function(){
	clearInterval(hoverTimer);
	hoverTimer=setTimeout(function(){
		$('.moreUpDiv').css({visibility:'hidden'});
	},hoverDelay)
})

$('.moreDown').mouseenter(function(){
	clearInterval(hoverTimer);
	hoverTimer=setTimeout(function(){
		$('.moreDownDiv').css({visibility:'visible'});
	},hoverDelay);	
}).mouseleave(function(){
	clearInterval(hoverTimer);
	hoverTimer=setTimeout(function(){
		$('.moreDownDiv').css({visibility:'hidden'});
	},hoverDelay)
})

$('.moreUpDiv,.moreDownDiv').mouseleave(function(){
	$(this).css({visibility:'hidden'});
}).mouseenter(function(){
	clearInterval(hoverTimer);
})

$('.linktext').css({color:'#217596'});

$('.linktext').hover(function(){
	$(this).css({color:'#f60',cursor:'hand'});
	
},function(){
	$(this).css({color:'#217596'});
	
})


//ie6 7? 这个笨蛋，parent relative 如果有宽度，子对象 absolute 以后，宽度有问题！
if(isIE67){
	//$('.moreUpDiv li').css({width:($('.moreUpDiv ul').width()-12)+'px'})
	$('.moreUpDiv,.moreDownDiv').each(function(){
		$(this).find('li').css({width:($(this).find('ul').width()-12)+'px'})
	})
}

if(isIE6){ //ie8 在 hover 这个问题上犯sb，只好 :hover
	$('.moreUpDiv ul li,.moreDownDiv ul li').mouseenter(function(){
		$(this).addClass('hover');
	}).mouseleave(function(){
		$(this).removeClass('hover');	
	})
}

/*forms------------------------------start*/

/*input.raido*/
$('.allRadios').each(function(){
	var realValue=$(this).find('input:first').attr('value');
	$(this).find('.radio').each(function(){
		if($(this).val()==realValue){
			$(this).addClass('radioChecked');
		}
	})
})

$('.allRadios label').click(function(){
	obj=$(this).find('input.radio');
	
	
	//只读
	if($(this).parents('.allRadios').hasClass('readonly')){
		return false;
	}
	
	var allRadios=obj.parents('.allRadios');
	allRadios.find('input.radio').removeClass('radioChecked');
	obj.addClass('radioChecked');
	allRadios.find('input:first').attr('value',obj.val())
	$(this).parents('.allRadios').trigger('gchange')
	
}).mouseover(function(){
	$(this).addClass('hover');
	$(this).find('input').addClass('radioHover');
}).mouseout(function(){
	$(this).removeClass('hover');
	$(this).find('input').removeClass('radioHover');
})

if(isIE67){//隐藏ie6-7 font-size:0 时的光标小点！
	$('input.radio').focus(function(){ 
		$(this).blur();	
	})
}

//$('input.required,textarea.required').after('<sup class="requiredSup" title="必填项">*</sup>');

$('input.required,textarea.required').each(function(){
	
	if($(this).parent().hasClass('allRadios')){
		$(this).	parent().append('<sup class="requiredSup" title="必填项">*</sup>');
	}else{
		$(this).	after('<sup class="requiredSup" title="必填项">*</sup>');	
	}
})

//gToggle ------------start
$('input.toggle').each(function(){
	$(this).	wrap('<span class="gToggle"></span>');
	$(this).parent().prepend('<span class="slider"><ul><li data-val="y" class="b">是</li><li data-val="n" class="g">否</li></ul></span><span class="aMaskDiv"></span>')
	$(this).attr('hidden','true');
})

$('.gToggle').each(function(){
	var obj=$(this).find('input');
	if(obj.val()==''){
		$(this).find('.slider').css({left:'-26px'});
		$(this).find('ul').hide()
	}else if(obj.val()==$(this).find('ul li:first').attr('data-val')){
		$(this).find('.slider').css({left:'0'});
		$(this).find('ul').show()
	}else if(obj.val()==$(this).find('ul li:nth-child(2)').attr('data-val')){
		$(this).find('.slider').css({left:'-52px'});
		$(this).find('ul').show()
	}
})

$('.gToggle').click(function(){
	$(this).find('ul').show()
	if($(this).find('input').val().toLowerCase()=='y'){
		$(this).find('.slider').stop().animate({left:'-52px'},300,'swing');
		$(this).find('input').val('n');	
	}else{
		$(this).find('.slider').stop().animate({left:0},300,'swing');
		$(this).find('input').val('y');
	}
	
	$(this).find('input').trigger('gchange');
})
//gToggle ------------end

$('.showAdvSearch').click(function(){
	$(this).parents('#searchDivContainer').find('.adv').toggle();
	$(this).toggleClass('showAdvSearchOpened');
})

var today=new Date();

$('input.date').each(function(j,k){
	if($(this).hasClass('now')){
		$(this).val(today.getFullYear()+'-'+(today.getMonth()+1<10?'0'+(today.getMonth()+1):today.getMonth()+1)+'-'+(today.getDate()<10?'0'+today.getDate():today.getDate()))	
	}
	$(this).datepicker({
	prevText: '上月',
	nextText: '下月',
	showAnim:'',
	dayNamesMin: ['日','一','二','三','四','五','六'],
	monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
	dateFormat:'yy-mm-dd',
	constrainInput:false,
	showMonthAfterYear:true,
	onClose:function(j,k){
		//$(this).trigger('blur');
		$(this).trigger('gchange');
	}
})//.attr('readonly','readonly');
}).blur(function(){
	if($(this).val()=='')return false;	
	var odate=Date.parse($(this).val());
	if(odate==null){
		$(this).val('请输入合法日期');
		var othis=$(this)		
		setTimeout(function(){
			othis.val('')
		},1000)		
	}else{
		$(this).val(odate.toString('yyyy-MM-dd'))
	}
})

/*
$('input.date').datepicker({
	prevText: '上月',
	nextText: '下月',
	dayNamesMin: ['日','一','二','三','四','五','六'],
	monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
	dateFormat:'yy-mm-dd',
	constrainInput:false,
	showMonthAfterYear:true,
	onClose:function(j,k){
		$(this).trigger('blur');
	}
})//.attr('readonly','readonly');
*/

$('input.datetime').each(function(){
	if($(this).hasClass('now')){
		$(this).val(today.getFullYear()+'-'+(today.getMonth()+1<10?'0'+(today.getMonth()+1):today.getMonth()+1)+'-'+(today.getDate()<10?'0'+today.getDate():today.getDate())+' '+(today.getHours()<10?'0'+today.getHours():today.getHours())+':'+(today.getMinutes()<10?'0'+today.getMinutes():today.getMinutes()))	
	}
	$(this).datetimepicker({
		prevText: '上月',
		nextText: '下月',
		showAnim:'',		
		dayNamesMin: ['日','一','二','三','四','五','六'],
		monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
		dateFormat:'yy-mm-dd',
		constrainInput:false,
		showMonthAfterYear:true,
		onClose:function(j,k){
			//$(this).trigger('blur');
			$(this).trigger('gchange');
		}
	})
}).blur(function(){
	if($(this).val()=='')return false;	
	var odate=Date.parse($(this).val());
	if(odate==null){
		$(this).val('请输入合法日期');
		var othis=$(this)		
		setTimeout(function(){
			othis.val('')
		},1000)		
	}else{
		$(this).val(odate.toString('yyyy-MM-dd HH:mm'))
	}
})

$('input.time').each(function(){
	if($(this).hasClass('now')){
		$(this).val((today.getHours()<10?'0'+today.getHours():today.getHours())+':'+(today.getMinutes()<10?'0'+today.getMinutes():today.getMinutes()))	
	}
	$(this).timepicker({})	
}).blur(function(){
	if($(this).val()=='')return false;	
	var odate=Date.parse($(this).val());
	if(odate==null){
		$(this).val('请输入合法日期');
		var othis=$(this)		
		setTimeout(function(){
			othis.val('')
		},1000)		
	}else{
		$(this).val(odate.toString('HH:mm'))
	}

})

$('input.chooser').click(function(){
	openwin($(this).attr('data-url'),'win1',$(this).attr('data-width'),$(this).attr('data-height'));
})



$('textarea').focus(function(){
	$(this).addClass('textFocus');	
}).blur(function(){
	$(this).removeClass('textFocus');	
})

$('form button.submit').click(function(){ 
	$(this).parents('form').trigger('submit');
})


//////////////////////////form validate start
$('form').submit(function(){

// return true; //临时测试	
	
var ok=true;
var info='';
var osubmit=$(this);
$(this).find(':input,label').removeClass('error');

$(this).find(':input:visible').each(function(i){
	obj=$(this);
	
	//把 打酱油的 placeholder 去掉（因为ie6-7没有placeholder，所以 :( ） 
	if(obj.hasClass('placeholder')&&obj.val()==obj.attr('placeholder')){//required
		obj.val('');
	}

	 if(obj.hasClass('required')&&obj.val()==''){//required
		info='<strong>'+obj.parents('td').prev().text().replace('：','').replace(':','')+'</strong>是必填项目';
		$('.ui-dialog').remove();
		$('<div class="popContent">'+info+'</div>').dialog({bgiframe:true,width:'300px'});
		obj.addClass('error').parents('td').prev().find('label').addClass('error');
		setTimeout(function(){
			$('.popContent').dialog('close')
		},2000)
		obj.trigger('focus');
		ok=false;
		return false;
	}
	else if(obj.val()!=''&&obj.hasClass('email')&&!(obj.val().match(/^\w+@[a-zA-Z_0-9]+\.[a-zA-Z]{2,3}$/))){//email
		info='<strong>'+obj.parents('td').prev().text().replace('：','').replace(':','')+'</strong>必须有效';
		$('.ui-dialog').remove();
		$('<div class="popContent">'+info+'</div>').dialog({bgiframe:true,width:'300px'});
		obj.addClass('error').parents('td').prev().find('label').addClass('error');
		setTimeout(function(){
			$('.popContent').dialog('close')
		},2000)
		obj.trigger('focus');
		ok=false;
		return false;
	}else if(obj.val()!=''&&obj.hasClass('phone')&&!(obj.val().match(/^[0-9\-]+$/))){//phone
		info='<strong>'+obj.parents('td').prev().text().replace('：','').replace(':','')+'</strong>必须有效';
		$('.ui-dialog').remove();
		$('<div class="popContent">'+info+'</div>').dialog({bgiframe:true,width:'300px'});
		obj.addClass('error').parents('td').prev().find('label').addClass('error');
		setTimeout(function(){
			$('.popContent').dialog('close')
		},2000)
		obj.trigger('focus');
		ok=false;
		return false;
	}else if(obj.val()!=''&&obj.hasClass('date')&&!(obj.val().match(/^[1-2]+[0-9]{3}-[0-1]+[0-9]+-[0-3]+[0-9]+$/))){//date
		info='<strong>'+obj.parents('td').prev().text().replace('：','').replace(':','')+'</strong>必须是有效日期';
		$('.ui-dialog').remove();
		$('<div class="popContent">'+info+'</div>').dialog({bgiframe:true,width:'300px'});
		obj.addClass('error').parents('td').prev().find('label').addClass('error');
		setTimeout(function(){
			$('.popContent').dialog('close')
		},2000)
		obj.trigger('focus');
		ok=false;
		return false;
	}else if(obj.val()!=''&&obj.hasClass('int')&&!(obj.val().match(/^-?[0-9]+$/))){//int
		info='<strong>'+obj.parents('td').prev().text().replace('：','').replace(':','')+'</strong>必须是整数';
		$('.ui-dialog').remove();
		$('<div class="popContent">'+info+'</div>').dialog({bgiframe:true,width:'300px'});
		obj.addClass('error').parents('td').prev().find('label').addClass('error');
		setTimeout(function(){
			$('.popContent').dialog('close')
		},2000)
		obj.trigger('focus');
		ok=false;
		return false;
	}else if(obj.val()!=''&&obj.hasClass('num')&&isNaN(obj.val())){//int
			info='<strong>'+obj.parents('td').prev().text().replace('：','').replace(':','')+'</strong>必须是数字';
			$('.ui-dialog').remove();
			$('<div class="popContent">'+info+'</div>').dialog({bgiframe:true,width:'300px'});
			obj.addClass('error').parents('td').prev().find('label').addClass('error');
			setTimeout(function(){
			$('.popContent').dialog('close')
		},2000)
		obj.trigger('focus');
			ok=false;
			return false;
		}
	
})

return ok;

})
//////////////////////////form validate end

/*forms------------------------------end*/


if(typeof dataJSON!='undefined'){
////////888///////
var valuewidth=[];


//console.log(dataJSON.cOrderby)

//构造 dataGird 内容
var tbHtml='<thead>';
$.each(dataJSON.thead,function(j,k){
	if(k.isCheckbox){
		tbHtml+='<th class="toggleCheckboxAll" width="1"><input class="checkbox" /></th>';
	}else{
		valuewidth[j] = k.valuewidth;
		if(!k.orderby){			
			tbHtml+='<th>'+k.name+'</th>';
		}else{		
			if(k.orderby==dataJSON.cOrderby){
				tbHtml+='<th class="sortable '+dataJSON.order+'" data-orderby="'+k.orderby+'">'+k.name+'</th>';
			}else{
				tbHtml+='<th class="sortable" data-orderby="'+k.orderby+'">'+k.name+'</th>';			
			}
		}
	}
})
tbHtml+='</thead><tbody>'

$.each(dataJSON.tbody,function(j,k){
	tbHtml+='<tr>';
	
	$.each(k,function(m,n){
		if(n.isCheckbox){
			tbHtml+='<td><input class="checkbox" data-id="'+n.id+'" /></td>';
		}else{
			var shortName=n.name;
			/*
			if(shortName.length>valuewidth[m]){
				shortName=shortName.substr(0,valuewidth[m])+'...'
			}
			*/
			var wStyle='';
			if(valuewidth[m]!='undefined'){
				wStyle=' style="width:'+valuewidth[m]+'px"';
			}
			
			if(n.link){
				// tbHtml+='<td><a href="'+n.link+'" title="'+n.name+'">'+shortName+'</a></td>';
				tbHtml+='<td><a class="onrowlimit" '+wStyle+' href="javascript:void(0)" mytarget="_blank" mylink="'+n.link+'" onclick="openwin($(this).attr(\'mylink\'),$(this).attr(\'mytarget\'),pub_width_large, pub_height_large)" title="'+n.name+'">'+shortName+'</a></td>';
			}else{
				tbHtml+='<td title="'+n.name+'"><div class="onrowlimit" '+wStyle+' >'+shortName+'</div></td>';
			}
		}
		
	})
	tbHtml+='</tr>';
})

tbHtml+='</tbody>'
	
$('.dataGrid').empty().append(tbHtml)



////////888///////
}


$('em.help').click(function(){
	$('<div class="popContent">'+$(this).attr('title')+'</div>').dialog({
		title:'提示信息：'
		})
})

$('#stickToolbar').click(function(){
	if(!$(this).find('span').hasClass('up')){
		$('#fixedOp').addClass('down');
		$('#fixedOpPlaceHolder').appendTo($('body'));
		$(this).find('span').addClass('up');
		$(this).attr('title','操作条放在上方');
	}else{
		$('#fixedOp').removeClass('down');
		$('#fixedOpPlaceHolder').prependTo($('body'));
		$(this).find('span').removeClass('up');
		$(this).attr('title','操作条放在下方');
	}
}).hoverClass('hover');

$('.accordinUl .showDetail').click(function(){
	//$(this).parent().find('.detailDiv').slideDown('fast')	
	if($(this).hasClass('opened')){
		$(this).removeClass('opened')	;
		$(this).parent().find('.detailDiv').slideUp('fast')	
	}else{
		$(this).addClass('opened');
		$(this).parent().find('.detailDiv').slideDown('fast')	
	}
}).disableSelection();

try{
	if(typeof navigationJSON!='undefined'){
		var ohtml='';
		$.each(navigationJSON,function(j,k){
			otarget=k.target?k.target:'mainIframe';
			if(k.link){
				ohtml+=' &raquo; <a href="'+k.link+'" target="'+otarget+'">'+k.name+'</a>';
			}else{
				ohtml+=' &raquo; <span>'+k.name+'</span>';
			}			
		})
	$('#navigation .realNavi',window.top.document).empty().append(ohtml);
	}else{
		$('#navigation .realNavi',window.top.document).empty();
	}
}catch(e){
	//
}

//$('table.viewGrid tr:first td').addClass('noBorder');
$('.pager input.text').focus(function(){$(this).select();})

$('.levelSel').each(function(){
	var val=$(this).find('input').val();
	$(this).find('li').each(function(){
		if($(this).attr('data-val')==val){
			$(this).stop().animate({backgroundColor:$(this).attr('data-col'),color:'#fff'},300,'swing')
		}	
	})
})

$('.levelSel li').each(function(){
	$(this).css({backgroundColor:$(this).attr('data-ori')});	
})

$('.levelSel li').click(function(){
	var oparent=$(this).parents('.levelSel');
	oparent.find('li').each(function(){
		$(this).css({backgroundColor:$(this).attr('data-ori'),color:'#000'});
	})
	$(this).stop().animate({backgroundColor:$(this).attr('data-col'),color:'#fff'},300,'swing')
	oparent.find('input').val($(this).attr('data-val'));
}).disableSelection();

/*
$('.levelSel li').mouseenter(function(){
	$(this).stop().animate({backgroundColor:$(this).attr('data-col'),color:'#fff'},300)	
}).mouseleave(function(){
	if($(this).hasClass('c')){return}
	$(this).stop().animate({backgroundColor:$(this).attr('data-ori'),color:'#000'},300)	
}).click(function(){
	var oparent=$(this).parents('.levelSel');
	oparent.find('li').removeClass('c').not($(this)).css({backgroundColor:$(this).attr('data-ori'),color:'#000'});
	$(this).addClass('c');	
	oparent.find('input').val($(this).attr('data-val'))
}).disableSelection();

*/

$('.levelSel5').each(function(){
	var val=$(this).find('input').val();
	
	$(this).find('li').each(function(){
		if($(this).attr('data-val')==val){
			$(this).addClass('c');
			var oparent=$(this).parents('.levelSel5');
			oparent.find('.info').text($(this).text());
		}	
	})
})

/*levelSel5*/
$('.levelSel5 li').each(function(){
	$(this).css({backgroundColor:$(this).attr('data-ori')});	
})

$('.levelSel5 li').mouseenter(function(){
	var oparent=$(this).parents('.levelSel5');
	oparent.find('.info').text($(this).text());
}).click(function(){
	var oparent=$(this).parents('.levelSel5');
	oparent.find('input').val($(this).attr('data-val'));
	oparent.find('li').removeClass('c');
	$(this).addClass('c');
}).disableSelection();
	


$('fieldset legend').hoverClass('hover').click(function(){
	$(this).parent().toggleClass('closed').find('.fsc').toggle();
}).disableSelection();

//fix firefox bug - tree 不出滚动条！！！-----------start
function fixFirefoxNoScrollBar(){
	if($.browser.mozilla){
			$('#leftTreeDiv').css({height:$(window).height()+'px'});
	}
}
fixFirefoxNoScrollBar();
$(window).resize(fixFirefoxNoScrollBar);
//fix firefox bug - tree 不出滚动条！！！-----------end

//#oTab
$('#oTab .tabTitle li').wrapInner('<span class="r"><span class="m"></span></span>');
$('#oTab .tabTitle li:first').addClass('c');
$('#oTab .tabContent li:first').show();

$('#oTab .tabTitle li').click(function(){
	$(this).siblings().removeClass('c').end().addClass('c');	
	if($(this).parents('#oTab').find('.tabContent').find('li').length==1){
		//一个 iframe 切换 location		
	}else{
		var oindex=$(this).index()+1;
		var tabContent=$(this).parents('#oTab').find('.tabContent');		
		tabContent.find('li:nth-child('+oindex+')').show().siblings().hide();
	}
})


//pager code by davidguoshuang at gmail.com  -start 翻页
try{
if(typeof pagerJSON!='undefined'&&$('#thisPager').length!=0){
///////////
var pagerHtml='<span class="pages"> 共 <strong>'+pagerJSON.pages+'</strong> 页 <strong>'+pagerJSON.items+'</strong> 条记录，每页显示 <input class="text" style="width: 1em;" value="'+pagerJSON.step+'"> </span>';

pagerHtml+='<a href="'+pagerJSON.url+'&page=1&step='+pagerJSON.step+'" class="page"><em>首页</em></a>'

if(pagerJSON.current>5){
	var arrayBefore=[];
	for(i=pagerJSON.current-4;i<pagerJSON.current;i++){
		arrayBefore.push(i);
	}
	pagerHtml+='<span class="extend">...</span>';
}else{
	var arrayBefore=[];
	for(i=1;i<pagerJSON.current;i++){
		arrayBefore.push(i);
	}
}

$.each(arrayBefore,function(j,k){
	pagerHtml+='<a href="'+pagerJSON.url+'&page='+k+'&step='+pagerJSON.step+'" class="page"><em>'+k+'</em></a>';
})
pagerHtml+='<span class="current"><em>'+pagerJSON.current+'</em></span>';
var arrayAfter=[];
var endPage=pagerJSON.pages<pagerJSON.current+4?pagerJSON.pages:pagerJSON.current+4;
for(i=pagerJSON.current+1;i<=endPage;i++){
	arrayAfter.push(i);
}
$.each(arrayAfter,function(j,k){
	pagerHtml+='<a href="'+pagerJSON.url+'&page='+k+'&step='+pagerJSON.step+'" class="page"><em>'+k+'</em></a>';
})

if(pagerJSON.current<pagerJSON.pages-4){
	pagerHtml+='<span class="extend">...</span>';
}

pagerHtml+='<a href="'+pagerJSON.url+'&page='+pagerJSON.pages+'&step='+pagerJSON.step+'" class="page"><em>末页</em></a>'
	
$('#thisPager').empty().append(pagerHtml)

$('#thisPager input.text').focus(function(){
	$(this).select();
})

$('#thisPager input.text').keypress(function(e){
	if(e.keyCode==13){
		if($(this).val().match(/^[0-9]+$/)){
			var onum=parseInt($(this).val(),10);
			location=pagerJSON.url+'&page='+pagerJSON.current+'&step='+onum;
		}else{
			$(this).val(pagerJSON.step)
		}
		return false;
	}
})

///////////
}	
}catch(e){}
//pager code by davidguoshuang at gmail.com  -end


//seacrh btn
$('#searchDivContainer #sbtn').click(function(){
	$(this).parents('form').trigger('submit');
	return false;
})


//

/*gTips*/
//$('body').append('<div id="gTips"><div class="indi"></div><div class="m"></div></div>')

/*input.emergency*/
$('input.emergency').attr('readonly','readonly').focus(function(){$(this).blur();})

$('input.emergency').mousemove(function(e){
	$(this).removeClass('emergency1 emergency2 emergency3 emergency4 emergency5')
	dis=e.pageX-$(this).position().left;
	
	if(dis>72){$(this).addClass('emergency5');$('#gTips').find('.m').text('5');}
	else if(dis>54){$(this).addClass('emergency4');$('#gTips').find('.m').text('4');}
	else if(dis>36){$(this).addClass('emergency3');$('#gTips').find('.m').text('3');}
	else if(dis>18){$(this).addClass('emergency2');$('#gTips').find('.m').text('2');}
	else {$(this).addClass('emergency1');$('#gTips').find('.m').text('1');}
	
	$('#gTips').css({left:e.pageX-$('#gTips').width()/2+'px',top:e.pageY-$('#gTips').height()-9+'px'}).fadeIn();
	
}).mouseout(function(){	
	$(this).removeClass('emergency1 emergency2 emergency3 emergency4 emergency5');
	$('#gTips').fadeOut();	
}).click(function(e){
	//alert('确定更改吗?');
	if(confirm('确定进行修改吗？'))
		{
		$(this).removeClass('emerClicked1 emerClicked2 emerClicked3 emerClicked4 emerClicked5')
		dis=e.pageX-$(this).position().left;
		
		if(dis>72){$(this).addClass('emerClicked5');$(this).val('5');}
		else if(dis>54){$(this).addClass('emerClicked4');$(this).val('4');}
		else if(dis>36){$(this).addClass('emerClicked3');$(this).val('3');}
		else if(dis>18){$(this).addClass('emerClicked2');$(this).val('2');}
		else {$(this).addClass('emerClicked1');$(this).val('1');}
		}

})

/*input.priority*/
$('input.priority').attr('readonly','readonly').focus(function(){$(this).blur();})

$('input.priority').mousemove(function(e){
	$(this).removeClass('priority1 priority2 priority3 priority4 priority5')
	dis=e.pageX-$(this).position().left;
	
	if(dis>72){$(this).addClass('priority5');$('#gTips').find('.m').text('5');}
	else if(dis>54){$(this).addClass('priority4');$('#gTips').find('.m').text('4');}
	else if(dis>36){$(this).addClass('priority3');$('#gTips').find('.m').text('3');}
	else if(dis>18){$(this).addClass('priority2');$('#gTips').find('.m').text('2');}
	else {$(this).addClass('priority1');$('#gTips').find('.m').text('1');}
	
	$('#gTips').css({left:e.pageX-$('#gTips').width()/2+'px',top:e.pageY-$('#gTips').height()-9+'px'}).fadeIn();
	
}).mouseout(function(){
	$(this).removeClass('priority1 priority2 priority3 priority4 priority5');
	$('#gTips').fadeOut();
}).click(function(e){
	if(confirm('确定进行修改吗？'))
	{
		$(this).removeClass('prioClicked1 prioClicked2 prioClicked3 prioClicked4 prioClicked5')
		dis=e.pageX-$(this).position().left;
		
		if(dis>72){$(this).addClass('prioClicked5');$(this).val('5');}
		else if(dis>54){$(this).addClass('prioClicked4');$(this).val('4');}
		else if(dis>36){$(this).addClass('prioClicked3');$(this).val('3');}
		else if(dis>18){$(this).addClass('prioClicked2');$(this).val('2');}
		else {$(this).addClass('prioClicked1');$(this).val('1');}		
	}

})


/*emergency priority*/
$('span.emergency').each(function(){
	level=$(this).text();
	$(this).addClass('e'+level);
})

$('span.priority').each(function(){
	level=$(this).text();
	$(this).addClass('p'+level);
})

//initial value

var emergencynum = $('input.emergency').val();
$('input.emergency').addClass('emerClicked'+emergencynum);

var prioritynum = $('input.priority').val();
$('input.priority').addClass('prioClicked'+prioritynum);




////////////////
})





//////////////////////////////////////
//////////////////////////////////////
//////////////////////////////////////

////////////window.load ----------------------------------------------------
$(window).load(function(){

//如果没有button的话 #fixedOp 干脆就不显示
if($('#fixedOp').find('button').length===0){
	$('#fixedOp').remove();
	$('#fixedOpPlaceHolder').remove();
}
	
if(isIE67){
	$('.dataGrid,.formGrid').attr('cellspacing',0);	
}

$('.dataGrid tbody tr').hoverClass('hover');


$('.tbtree tbody tr').live('mouseenter',function(){
	$(this).addClass('hover');
}).live('mouseleave',function(){
	$(this).removeClass('hover');
});

$('.tbtree2 tbody tr').live('mouseenter',function(){
	$(this).addClass('hover');
}).live('mouseleave',function(){
	$(this).removeClass('hover');
});


$('table.dataGrid th.sortable').wrapInner('<span class="thOrder"></span>').click(function(){
	//$(this).find('.thOrder').toggleClass('asc');	
	try{
		//console.log('提交的值：',$(this).attr('data-orderby'),$(this).find('.thOrder').hasClass('asc')?'asc':'desc')

		var oform=$('#form_view');
		oform.find('input[name=_sortfield]').val($(this).attr('data-orderby'));
		
		if($(this).hasClass('desc')){
			oform.find('input[name=_sorttag]').val('asc');
		}else{
			oform.find('input[name=_sorttag]').val('desc');
		}
		
		
		oform.find('input[name=step]').val($('#jumppagesize').val());
		//pub_query_go2page('/irm/module/irm/config/config/configlist/configlist_browse.action');
		$('#form_view').submit();
		
	}catch(e){
	}
}).disableSelection();

//input checkbox toggle
$('input.checkbox').live('click',function(){
	var obj=$(this);
	
	//只读
	if(obj.hasClass('readonly')){
		return false;
	}
	
	if(obj.hasClass('checkboxChecked')){
		obj.removeClass('checkboxChecked');
		obj.attr('value',0);
	}else{obj.addClass('checkboxChecked');
		obj.attr('value',1);
	}
	$(this).trigger('gchange');
	
}).live('mouseover',function(){
	$(this).addClass('checkboxHover');
}).live('mouseout',function(){
	$(this).removeClass('checkboxHover');
}).attr('readonly','readonly').each(function(j,k){
	
	if($(k).val()=="1"){
		$(k).addClass('checkboxChecked');
	}else{
		$(k).removeClass('checkboxChecked');
	}
})

$('.toggleCheckboxAll .checkbox').click(function(){
	var allCheckbox=$(this).parents('table').find('tbody tr').find('td:first').find('input.checkbox');
	if(!$(this).hasClass('checkboxChecked')){
		allCheckbox.removeClass('checkboxChecked').addClass('checkboxChecked');
		allCheckbox.attr('value',1);
	}else{
		allCheckbox.removeClass('checkboxChecked');
		allCheckbox.attr('value',0);
	}
})

$('.toggleCheckboxAllRight .checkbox').click(function(){
	var allCheckbox=$(this).parents('table').find('tbody tr').find('td:last').find('input.checkbox');
	if(!$(this).hasClass('checkboxChecked')){
		allCheckbox.removeClass('checkboxChecked').addClass('checkboxChecked');
		allCheckbox.attr('value',1);
	}else{
		allCheckbox.removeClass('checkboxChecked');
		allCheckbox.attr('value',0);
	}
})

$('h2.collapsable .t').click(function(){
	var obj=$(this).parent();
	obj.toggleClass('closed').next().toggleClass('hide');	
	var oStatus=obj.hasClass('closed')?'closed':'opened';

}).mouseenter(function(){
	$(this).addClass('thover');
}).mouseleave(function(){
	$(this).removeClass('thover');
})

$('h2.collapsable').hoverClass('chover')

//input.select ------------start--------------

$('input.select').each(function(){
	var defaultValue=$(this).attr('data-default');
	
	var oparent=$(this).parents('.selectSpan');
	var othis=$(this);
	if(!$(this).attr('data-options')){return;}
	var dataOptions=$(this).attr('data-options');
	dataValues=$(this).attr('data-values');
	if(!dataValues){
		var dataValues=$(this).attr('data-options');
	}
	dataOptions=dataOptions.split('||');
	dataValues=dataValues.split('||');	
	
	$.each(dataValues,function(j,k){
		if(k==defaultValue){
			//console.log(dataOptions[j])
			oparent.find('input:first').attr('value',dataValues[j]);
			othis.attr('value',dataOptions[j])
		}
	})
	
	/*2012-11-16 应为有 空选项 所以不再需要？ 
	if(typeof defaultValue=='undefined'||defaultValue==''){//普扫要求：select 不指定默认值的话为 第一个！与传统select 一样 -start
		oparent.find('input:first').attr('value',dataValues[0]);
		othis.attr('value',dataOptions[0])
	}//普扫要求：select 不指定默认值的话为 第一个！与传统select 一样 -end
	*/
	
	
	//console.log(dataOptions.indexOf(defaultValue))
	//oparent.find('input:first').attr('value',dataValues[sIndex-1])
	//$(this).attr('value',dataOptions[sIndex-1])
})

$('.selectSpan').each(function(){
	if($(this).hasClass('canInput')){
		//$(this).find('input.select').attr('readOnly',false);
	}else{
		$(this).find('input.select').attr('readonly','readonly');
	}
})

$('input.select').click(function(e){
	
	var oThis=$(this);
	
	//只读 select 
	if(oThis.parents('.selectSpan').hasClass('readonly')){
		return false;	
	}
	
	//if(!$(this).attr('data-options')){try{console.log('需要定义 data-options');}catch(e){alert('需要定义 data-options')}return;}	
	
	if($(this).attr('data-options')){ //如果有 data-options 的话（因为现在需要空的select）
		
	var dataValues=$(this).attr('data-values');
	if(!dataValues){dataValues=$(this).attr('data-options')}
	var dataOptions=$(this).attr('data-options').split('||');
	dataValues=dataValues.split('||');	
	/*
	if(dataOptions.length!=dataValues.length){try{console.log('data-options 和 data-values 数量应该一致');}catch(e){alert('data-options 和 data-values 数量应该一致');}return;}
	*/
	ohtml='<div id="selectDivContainer"><ul>';
	ohtml+='<li data-value=""></li>';
	$.each(dataOptions,function(j,k){
		ohtml+='<li data-value="'+dataValues[j]+'">'+k+'</li>'
	})	
	ohtml+='</ul></div>';
	//console.log(ohtml);
	$('#selectDivContainer').remove();
	$('body').append(ohtml);
	
	}
	////如果有 data-options 的话（因为现在需要空的select）
//$('#selectDivContainer').css({left:$(this).position().left+'px',top:$(this).position().top+'px','min-width':$(this).width()+23,marginTop:$(this).height()+1})

//$('#selectDivContainer').css({left:$(this).position().left+'px',top:$(this).position().top+'px','width':$(this).width()+23,marginTop:$(this).height()+1})
	
	//2012-12-10 change $window 2 $document by david
	if($('#selectDivContainer').height()+$(this).position().top>$(document).height()){
		$('#selectDivContainer').css({overflow:'auto',height:$(document).height()-$(this).position().top-24+'px'})
	}
	
	$('#selectDivContainer').css({left:$(this).position().left+'px',top:$(this).position().top+'px','min-width':$(this).width()+23,'width':'auto',marginTop:$(this).height()+1})
	
		

if(isIE67){
	$('#selectDivContainer').css({width:$(this).width()+23})
	$('#selectDivContainer li').css({width:$('#selectDivContainer').width()-8})
	$('#selectDivContainer li').hoverClass('hover');
}
	
$('#selectDivContainer li').click(function(){
		oThis.attr('value',$(this).text())
		oThis.prev().attr('value',$(this).attr('data-value'));
		$('#selectDivContainer').remove();
		//e.stopPropagation();	
		oThis.trigger('gchange');
	})
	e.stopPropagation();	
})


$(document).click(function(){
	$('#selectDivContainer').remove();
})
//input.select ------------end--------------


$(':input[placeholder]').focus(function() {
  var input = $(this);
  if (input.val() == input.attr('placeholder')) {
    input.val('');
    input.removeClass('placeholder');
  }
}).blur(function() {
  var input = $(this);
  if (input.val() == '' || input.val() == input.attr('placeholder')) {
    input.addClass('placeholder');
    input.val(input.attr('placeholder'));
  }
}).trigger('blur');

$('input.ani2width').focus(function(){
	$(this).stop(true,true);
	var w=$(this).width();
	$(this).attr('data-width',w);
	$(this).animate({width:w*1.5+'px'},200,'swing')
}).blur(function(){
	var w=$(this).attr('data-width');
	$(this).stop().animate({width:w+'px'},200,'swing')
})

$('#form_quicksearch input').keypress(function(e){
	if(e.keyCode==13){
		$('#form_quicksearch').trigger('submit');
	}
})

/*
.attachmentUl li a.doc {background-image:url(images/filetype/doc.png) }
.attachmentUl li a.xls {background-image:url(images/filetype/xls.png) }
.attachmentUl li a.ppt {background-image:url(images/filetype/xls.ppt) }
.attachmentUl li a.zip {background-image:url(images/filetype/zip.png) }
.attachmentUl li a.pdf {background-image:url(images/filetype/xls.png) }
.attachmentUl li a.video {background-image:url(images/filetype/film.png) }
.attachmentUl li a.htm {background-image:url(images/filetype/html.png) }
*/

$('.attachmentUl li a').each(function(){
	//console.log($(this).attr('href').indexOf('.'))	
	var href=$(this).attr('href');
	var ext=href.substring(href.lastIndexOf('.')+1)
	if(ext=='doc'||ext=='docx'){
		$(this).addClass('doc')	
	}else if(ext=='xls'){
		$(this).addClass('xls')	
	}else if(ext=='ppt'){
		$(this).addClass('ppt')	
	}else if(ext=='zip'||ext=='rar'){
		$(this).addClass('zip')	
	}else if(ext=='pdf'){
		$(this).addClass('pdf')	
	}else if(ext=='mp4'||ext=='mpeg'||ext=='rmvb'){
		$(this).addClass('video')	
	}else if(ext=='htm'||ext=='html'){
		$(this).addClass('htm')	
	}
})

$('#clearSearchStr').click(function(){
	//console.log($(this).parents('form'))//.reset();
	//$(this).parents('form')[0].reset()
	var oform=$(this).parents('form');
	oform.find('input:visible').val('')
	oform.find('.selectSpan').each(function(){
		$(this).find('input:hidden').val('')
	})
	//laji code
	oform.find('select').find('option:first').attr('selected','selected')
	
})

//firefox ie9 以后 form 回车 会触发内部的 button

$('input').keypress(function(e){
    if(e.keyCode==13){e.preventDefault();}
})


if(isIE67){
	if($(window).width()+45<$(document).width()){
		$('html').css({'overflow-x':'scroll'})		
	}
}

if(isIE7){
	if($('.dataGrid').width()>$(window).width()){
		$('#pageContainer').css({'overflow-x':'scroll'})		
	}
}


})
//////////////////////----------------------------------------------------