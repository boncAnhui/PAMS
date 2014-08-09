<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">
<script type="text/javascript" src="${base}/themes/default/jquery.limit-1.2.source.js"></script>
<script type="text/javascript">
var navigationJSON=[{name:'业务知识',link:'portal5.htm'},{name:'新增知识'}];

jQuery(function(){
///////////////////

<#include "/com/ray/xj/sgcc/irm/system/attach/attach/view/pub_attach_upload.ftl">

  $("#bt_save").click(function() {page_save()});
  
  $("#mauthor").val("${arg.username}");
  $("#mauthorid").val("${arg.loginname}");
  $("#mauthordept").val("${arg.deptname}");
  $("#mauthordeptid").val("${arg.deptid}");
  $("#cclassallname").val("${arg.cclassallname}");
///////////////////


})

function page_save()
{
	alert("hello");
	$.ajax({
	url: "${base}/module/irm/knowledge/knowledge/knowledge/knowledge_knowledgesaveform.action",
	  data:$('form').serialize(),
	  type: 'post',
	  traditional: true,
	  success: function() 
	  {
			alert('新增知识文档成功！');
	  },
	  error: function() 
	  {
			alert('新增知识文档失败！');  
	  }
	});
    
}

</script>
</head>
<body>
<div id="fixedOp">
<button id="bt_save">保存</button>
</div>

<div id="pageContainer">

<h1 class="tabh1"><span class="icon_ccsb"></span>新建知识文档</h1>

<form id="aform" method="post">
<input type="hidden" id="attachid" name="attachid">
<input type="hidden" id="attachname" name="attachname">
<input name="cclassid" id="cclassid" type="hidden" value="${arg.cclassid}" />



<#include "/com/ray/xj/sgcc/irm/knowledge/knowledge/knowledge/view/knowledgeform.ftl">
</form>   
</div>
</body>
</html>
