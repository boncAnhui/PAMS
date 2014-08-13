<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">
<title>流程跟踪　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　</title>
<body>
	&nbsp;&nbsp;<a href="javascript:void(0)" onclick="page_viewprogress()">查看流程图</a>

<form action="" method="post">
<table width="100%" cellspacing="10" class="dataGrid" id="otable">
<thead>
<th>流程节点</th>
<th>处理人</th>
<th>到达时间</th>
<th>签收时间</th>
<th>完成时间</th>
<th>指标等待时长</th>
<th>实际等待时长</th>
<th>指标工作时长</th>
<th>实际工作时长</th>

</thead>
<tbody>
<#list data.bean_datas as aobj>
<tr>
<td>${aobj.cname}</td>
<td></td>
<td>${aobj.createtime}</td>
<td>${aobj.applytime}</td>
<td>${aobj.completetime}</td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
</#list>
</tbody>
</table>
</form>	
</body>
</html>