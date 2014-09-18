<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">
</head>
<body>
<table class="dataGrid">
<thead>
	<th width="1"><input class="checkbox"></th>
	<th>姓名</th>
	<th>职务</th>
	<th>电话</th>
	<th>邮件</th>
</thead>
<tbody>
	<#list data.users as obj>
	<tr>
	<td><input class="checkbox" data-id="${obj.id}" data-cname="${obj.cname}" data-ctype="PERSON" data-internal="${obj.id}"></td>
	<td>${obj.cname}</td>
	<td>${obj.position}</td>
	<td>${obj.phone}</td>
	<td>${obj.email}</td>
	</tr>
	</#list>
</tbody>
</table>

</body>
</html>