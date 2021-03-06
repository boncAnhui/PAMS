<#assign apage = {"currentpage":data.page.pageNo,"pagesize":data.page.pageSize,"maxpage":data.page.totalPages,"size":data.page.result?size,"hasnext":data.page.hasNext,"haspre":data.page.hasPre,"rowcount":data.page.totalCount,"sorttag":data.page.order,"sortfield":data.page.orderBy,"result":data.page.result}>
<#assign vo = data.vo>
<#import "com/ray/app/query/view/page_macros.ftl" as pub_macros>
<script type="text/javascript">
var navigationJSON=[ {name:'计划管理',link:'${base}/module/irm/portal/portal/portal/portal_browse.action?ccate=jhgl'}, {name:'计划编制'}];

var dataJSON={thead:<@pub_macros.displayheaderjson vo = vo arg = arg />, tbody:<@pub_macros.displaylistjson apage = apage vo = vo />};

var tbtreepadding=10;  //树缩进距离
var tbtreetds=11;//表格的列数

<#list vo.searchoptions as option>
var c_${option.field} = ${option_index + 1};
</#list>

function tbtreeAjax(url,id){
	var ohtml='';
    $.ajax({
    	url:url,
        data:{supid:id},
        cache:false,
		async:false,
        success:function(d)
        {
        	console.log(d);
        	eval('var d='+d);
       		$.each(d,function(j,k)
       		{	
       			var tdHtml='';		

				if(k.leaf)
				{
					tdHtml+='<tr data-parent="'+id+'" class="file" data-id="'+k.id+'">';
					tdHtml+='<td style="padding-left:'+tbtreepadding*k.level+'px"><span class="toggle"></span>';
					tdHtml+='<span class="f"><a href="javascript:void(0);" onclick="page_readpageframe(\'' +k.id+'\')">'+k.name+'</a></span></td>';					
				}
				else
				{
					tdHtml+='<tr data-parent="'+id+'" class="file" data-id="'+k.id+'">';
					tdHtml+='<td style="padding-left:'+tbtreepadding*k.level+'px">';
					tdHtml+='<span class="f"><a href="javascript:void(0);" onclick="page_readpageframe(\'' +k.id+'\')">'+k.name+'</a></span></td>';
				}					

				tdHtml+='<td>' + k.cells.planbegintime + '</td>';
				tdHtml+='<td>' + k.cells.planendtime + '</td>';
				tdHtml+='<td>' + k.cells.realbegintime + '</td>';
				tdHtml+='<td>' + k.cells.realendtime + '</td>';
				tdHtml+='<td>' + k.cells.periodnum + '</td>';
				tdHtml+='<td>' + k.cells.planperiodnum + '</td>';
				tdHtml+='<td>' + k.cells.realperiodnum + '</td>';
				tdHtml+='<td>' + (k.cells.planperiodnum - k.cells.planperiodnum) + '</td>';								
				tdHtml+='<td>' + k.cells.headername + '</td>';
				tdHtml+='<td>' + k.cells.headerdeptname + '</td>';
				tdHtml+='<td>' + k.cells.creatername + '</td>';
				tdHtml+='<td>' + k.cells.version + '</td>';	
				tdHtml+='<td>' + k.cells.state + '</td>';		
				tdHtml+='<td>' + k.cells.planmodelid + '</td>';				
				
				tdHtml+='<td><a title="新建下级计划" href="javascript:void(0);" onclick="page_insert(\''+k.id+'\')" class="tbtree-add"></a></td>';	
				tdHtml+='<td><a title="启动流程" href="javascript:void(0);" onclick="page_startflow(\''+k.id+'\',\''+k.cells.flowdefid+'\')" class="tbtree-play"></a></td>';								
				tdHtml+='<td><input class="checkbox"></td></tr>';
				
				ohtml+=tdHtml;	
			})
        }
    })
	return ohtml;
}

//构造 dataGird 内容
var tbHtml='';


$.each(dataJSON.tbody,function(j,k)
{
	console.log("islast:" + k[c_islast].name);
	
	if(k[c_islast].name==1)
	{
		tbHtml+='<tr data-parent="R0" class="folder" data-id="'+k[c_id].name+'">';
	}
	else
	{
		tbHtml+='<tr data-parent="R0" class="file" data-id="'+k[c_id].name+'">';
	}

	var title;
	if((k[c_title].name).length>20)
	{
		title='<span title="' + k[c_title].name + '">' + k[c_title].name.substring(0,20) + '...</span>';
	}
	else
	{
		title=k[c_title].name;
	}
	
	var thisToggle='';
	
	if(k[c_islast].name==1)
	{	
		thisToggle='<span class="toggle"></span>';
	}
	
	console.log("thisToggle:" + thisToggle);	
	
	tbHtml+='<td style="padding-left:'+tbtreepadding*1+'px">' + thisToggle;
	tbHtml+='<a href="javascript:void(0);" onclick="page_readpageframe(\'' +k[c_id].name+'\')">'+title+'</a></td>';
	tbHtml+='<td>' + k[c_planbegintime].name + '</td>';
	tbHtml+='<td>' + k[c_planendtime].name + '</td>';
	tbHtml+='<td>' + k[c_realbegintime].name + '</td>';	
	tbHtml+='<td>' + k[c_realendtime].name + '</td>';
	tbHtml+='<td>' + k[c_periodnum].name + '</td>';
	tbHtml+='<td>' + k[c_planperiodnum].name + '</td>';
	tbHtml+='<td>' + k[c_realperiodnum].name + '</td>';
	tbHtml+='<td>' + (k[c_planperiodnum].name - k[c_realperiodnum].name) + '</td>';	
	tbHtml+='<td>' + k[c_headername].name + '</td>';
	tbHtml+='<td>' + k[c_headerdeptname].name + '</td>';
	tbHtml+='<td>' + k[c_creatername].name + '</td>';
	tbHtml+='<td>' + k[c_version].name + '</td>';	
	tbHtml+='<td>' + k[c_state].name + '</td>';		
	tbHtml+='<td>' + k[c_planmodelid].name + '</td>';

	tbHtml+='<td><a title="新建下级计划" href="javascript:void(0);" onclick="page_insert(\''+k[c_id].name+'\')" class="tbtree-add"></a></td>';	
	tbHtml+='<td><a title="启动流程" href="javascript:void(0);" onclick="page_startflow(\''+k[c_id].name+'\',\''+k[c_flowdefid].name+'\')" class="tbtree-play"></a></td>';
	tbHtml+='<td><input class="checkbox"></td>';
	tbHtml+='</tr>';

});



var allChildren=[];

function getallchild(pid)
{
	$(".tbtree2 tr").each(function()
	{
		var o = $(this);		
		if (o.attr("data-parent")== pid)
		{
			allChildren.push(o);
			if(o.hasClass('file'))
			{
				return;
			}	
			getallchild(o.attr("data-id"));
		}		
	})
}


$(function()
{
	$('.tbtree2 tbody').empty().append(tbHtml);
	
	$('.tbtree2 .toggle').live('click',function(e)
	{
		var otr=$(this).parents('tr');
		if($(this).hasClass('expanded'))
		{
			allChildren=[];
			getallchild(otr.attr('data-id'))
			//console.log(allChildren)
			$.each(allChildren,function()
			{
				$(this).hide();
				$(this).find('.toggle').removeClass('expanded');
			})
			$(this).removeClass('expanded');
		}
		else
		{
			if(otr.attr('data-ajaxed'))
			{
				$(this).parents('table').find('tr').each(function()
				{
					if($(this).attr('data-parent')==otr.attr('data-id'))
					{
						$(this).show();
					}				
				})
				$(this).addClass('expanded');
			}
			else
			{
				console.log("data-id:" + otr.attr('data-id'));
				otr.after(tbtreeAjax('apply_treebrowse.action',otr.attr('data-id')))
				otr.attr('data-ajaxed','done');
				$(this).addClass('expanded');
			}
		
		}
		return false;
	})	
	
});
		
</script>

<div id="pageContainer">
<div id="searchDivContainer">
<@pub_macros.displaysearchitem vo = vo arg = arg apage = apage />
</div>

<table class="tbtree2" style="width:100%;">
<thead>
	<th>计划名称</th>
	<th>计划开始时间</th>
	<th>计划结束时间</th>
	<th>实际开始时间</th>
	<th>实际结束时间</th>
	<th>标准时长</th>
	<th>计划时长</th>
	<th>实际时长</th>
	<th>超时统计</th>	
	<th>负责人</th>
	<th>负责部门</th>
	<th>编制人</th>
	<th>版本</th>
	<th>状态</th>
	<th>模板标识</th>	
	<th>&nbsp;</th>
	<th>&nbsp;</th>
	<th>&nbsp;</th>	
</thead>
<tbody>
</tbody>
</table>

<div class="pager" id="thisPager">
<@pub_macros.displaynavigation apage = apage vo = vo arg = arg></@pub_macros.displaynavigation>
</div>

<form id="aform" method="post">
<input type="hidden" id="supid" name="supid" value="R0">

</form>

<form method="post" name="form_view" id="form_view" action="${base}${vo.formaction}">
<input type="hidden" name="_searchname" value="${_searchname}">
<input type="hidden" name="page" value="${page}">
<input type="hidden" name="_sortfield" value="${_sortfield}" />
<input type="hidden" name="_sorttag" value="${_sorttag}" />
<input type="hidden" name="step" value="${step}" />

</form>

</div>
<script type="text/javascript">
$("#bt_input").click(function() {page_input("R0")});
$("#bt_del").click(function() {page_del()});

// 新增
function page_input(supid)
{
	url = "${base}/module/pams/jhgl/jhbz/apply_input.action?supid=" + supid;
	openwin(url, 'input',pub_width_large,pub_height_large);	
}

function page_del()
{
	var oids=[];
	$('.dataGrid tbody .checkbox').each(function(j,k)
	{		
		if($(this).val()==1)
		{
			oids.push($(this).attr('data-id'))	
		}		
	})
	
	if(oids.length<=0)
	{
		alert("请先选择要删除的数据！");
		return;
	}
	
	if(confirm("确实要删除选中的记录?"))
	{
		window.location = "${base}/module/pams/jhgl/jhbz/apply_delete.action?ids=" + oids;
	}
}

function page_insert(supid)
{	
	var isrole = 'true';
	if(isrole)
	{
		openwin('${base}/module/pams/jhgl/jhbz/apply_input.action?supid='+supid,'input',pub_width_large,pub_height_large);	
	}
	else
	{
		alert("您没有任务下达权限，如有需要请与系统管理员联系！");
	}
}

function page_readpageframe(id)
{
	url = '${base}/module/pams/jhgl/jhzx/apply_readpageframe.action?id='+id;
	console.log("url:" + url);
	openwin(url,'readpageframe',pub_width_large,pub_height_large);		
}

function page_startflow(id, flowdefid)
{
	url = '${base}/module/pams/jhgl/jhbz/apply_startflow.action?id='+id+'&flowdefid='+flowdefid;
	console.log("url:" + url);
	openwin(url,'startflow',pub_width_large,pub_height_large);	
}

</script>