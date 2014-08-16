<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">

<script   type="text/javascript" src="${base}/wfb/blueflowui/javascript/common.js"></script>
<script type="text/javascript">
function OpenFlowForm(oDestination,oDestination1) {
    oField1 = oDestination;
    oField2 = oDestination1; 
    wffclass = window.open("${base}/module/app/system/workflow/bform/bform_selectbform.action", "wffclass", "resizable=yes,dependent=yes,scrollbars=yes,left=90,top=45,width=" + screen.width * .7 + ",height=" + screen.height * .5);    
    wffclass.focus();
}
function OpenFlowClass(oDestination,oDestination1) {
    oField1 = oDestination;
    oField2 = oDestination1; 
    wfclass = window.open("${base}/module/app/system/workflow/bflowclass/bflowclass_selectbflowclass.action", "wfclass", "resizable=yes,dependent=yes,scrollbars=yes,left=90,top=45,width=" + screen.width * .7 + ",height=" + screen.height * .5);    
    wfclass.focus();
}

jQuery(function($){
/////////////////////

$('#showTable tbody tr').live('click',function(){
	$('#showTable tbody tr').removeClass('selectedTr');
	$(this).addClass('selectedTr');
})

/////////////////////
})
</script>
<style>
#showTable tr.selectedTr td {background: #69c;color:#fff;}
</style>
</head>
<body>
<form id="flowForm">

<table width="100%" id="showTable" class="dataGrid">
<thead>
<tr>

	<th nowrap="nowrap">任务名称</th>
	<th nowrap="nowrap">任务类型</th>
	<th nowrap="nowrap">是否必需</th>
	<th nowrap="nowrap" style="display: none;">说明</th>

</tr>
</thead>
<tbody>
</tbody>
</table>
</form>


</body>

</html>

