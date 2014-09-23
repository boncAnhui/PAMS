<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">
<script type="text/javascript" src="${base}/themes/default/jquery.limit-1.2.source.js"></script>

<script type="text/javascript">
var navigationJSON=[{name:'业务知识',link:'portal5.htm'},{name:'知识资料',link:'k3.htm'}];

var pagerJSON={current:1,items:68,pages:15,step:10,url:'k3-right.php?a=1',orderby:'daobaoqixian',order:'desc'}

$(function(){
/////////////////////////////////////////
$("#bt_apply").click(function() {page_apply()});
$("#bt_callback").click(function() {page_callback()});
$("#bt_comment").click(function() {page_comment()});
$("#bt_update").click(function() {page_update()});
$("#bt_close").click(function() {page_close()});
/////////////////////////////////////////
})


$(function()
{
/////////////////////////////////////////
$('textarea').each(function(){
	if(!isNaN($(this).attr('maxlength'))){
		$(this).after('<span class="textLimit" title="字数限制为：'+$(this).attr('maxlength')+'"><div class="numDiv"><span></span><span class="num"></span></div></span>');
		var textLeft=$(this).parent().find('.textLimit span.num')
		$(this).limit($(this).attr('maxlength'),textLeft);
		//textLeft.after('个字符').before('还剩')
	}
})

$('#commentUl .del').live('click',function(){
	
	if(window.confirm('你确认要删除这个评论吗？')){
		var oid=$(this).parent().attr('data-id');
		var othis=$(this);
		var oparent=othis.parent();
		$.ajax({
			url:'knowledge_deletecomment.action',
			data:{id:oid},
			success:function(d){
				if(d=='done'){//后台确认已经删除	
				    			
					oparent.animate({opacity:0},'fast','swing',function(){						
						oparent.empty().css({opacity:1,color:'#c00'}).append('该评论已经被删除！');
						setTimeout(function(){
							oparent.remove()
						},1000)							
					})
				}else{ //删除失败
					oparent.find('.error').remove();
					oparent.append(' <span class="error" style="color:#c00">删除操作失败：该评论已经被删除。</span>');
				}
				
			}	
		})
	}
})
	

/////////////////////////////////////////
})

function page_comment()
{
	$("#aform").attr("action","knowledge_createcomment.action?cclassid=${arg.cclassid}&id=${arg.id}");
    $("#aform").trigger('submit');
}

function page_update()
{
   	window.location = "knowledge_locate.action?cclassid=${arg.cclassid}&id=${arg.id}";
}

function page_close()
{    
	window.close();
   //	window.location = "knowledge_browse.action?cclassid=${arg.cclassid}";
}

//签收
function page_apply()
{
	window.parent.location = "knowledge_apply.action?id=${arg.id}&cclassid=${arg.cclassid}&runactkey=${arg.runactkey}";
}
// 收回操作
function page_callback() 
{
	url = "${base}/module/app/system/workflow/ui/callback.action";
	url += "?runactkey=${arg.runactkey}";
	url += "&tableid=${arg.tableid}";

	openwin(url,"callback",pub_width_mid,pub_height_mid);	
}

</script>
</head>
<body>

<div id="fixedOp">


<#if arg.iscallback == true>
	<button id="bt_callback">收回</button>
</#if>

<#if arg.isapply == true>
<button id="bt_apply">签收</button>
<#else>
	<#if arg.isupdate == true>
	<button id="bt_update">修改</button>
	</#if>
</#if>

</div>

<style>
textarea {	}
.textLimit {position:relative;background:red;}
.textLimit .numDiv {position:absolute;right:0;margin-right:20px;top:-30px;}
.textLimit .numDiv span {display:inline;white-space:nowrap;font-size:18px;color:#666;font-size:700;font-style:italic}
</style>

<form id="aform" method="post">
<div id="pageContainer">
<h1>${data.knowledge.title}</h1>
<div class="meta" align=center>作者：${data.knowledge.mauthor} 阅读：${arg.views}次     ${data.knowledge.createtime}</div>

<div><strong>摘要：</strong><p style="padding:10px;border:solid 1px #ccc;background:#eef;">${data.knowledge.summary}</p></div>

<div><strong>关键字：</strong><a href="#1">${data.knowledge.keyword}</a> </div>


<!--HTML start-->

<#if data.knowledge.content != null || data.knowledge.content != "">

	<div><strong>正文：</strong></div>
	<div id="realContent" style="border:solid 1px #ccc;padding:8px;margin:10px 0;height:10em;">
	${data.knowledge.content}
	</div>
</#if>

<div style="padding:10px;"><strong>附件：</strong>
<ul class="attachmentUl">
<#list data.fileattachments as aobj>
	<a target="_blank" class="attachment" href="${base}/module/pams/gxgl/wjwh/fileattachment_downloadbyid.action?id=${aobj.id}">${aobj.sfilename}</a><br>
</#list>
</ul>
</div>
<!--HTML end-->

<h2 id="comment" class="collapsable blue"><span class="t">评论</span></h2>
<div class="commentDiv">

<ul id='commentUl' style="margin-left:20px;">
	<#list data.knowledgecomment as aobj>
	<li data-id="${aobj.id}" style="padding:10px" ><span class="date">${aobj.sugdate}</span>  ${aobj.sugperson} 说:   ${aobj.kcomment}<#if arg.isdeletecomment == true> <span class="del" style="color:#c00;font-weight:700;font-family:verdana;cursor:pointer" title="删除">X</span></#if></li>
	</#list>
</ul>

<textarea style="height:4em;" name="kcomment" id="kcomment" maxlength="500"></textarea>
<#if arg.iscomment == true>
<button id="bt_comment">提交</button>
</#if>
</div>

<table style="width:100%;border:dotted 1px #ccc;border-left:0;border-right:0;margin-top:8px;" cellspacing="5">
<tr>
<td width="15%">文档分类：</td>
<td colspan="3">${arg.cclassallname}</td>
</tr>
<tr>
<td width="15%">主作者：</td>
<td width="35%">${data.knowledge.mauthor}</td>
<td width="15%">次作者：</td>
<td width="35%">${data.knowledge.sauthor}</td>
</tr>
<tr>
<td>创建者：</td><td>${data.knowledge.createuser}</td>
<td>创建时间：</td><td>${data.knowledge.createtime}</td>
</tr>
<tr>
<td>主作者部门：</td><td>${data.knowledge.mauthordept}</td>
<td>安全级别：</td><td>${data.knowledge.slevel}</td>
</tr>
</table>

</div>
</form>


</body>
</html>