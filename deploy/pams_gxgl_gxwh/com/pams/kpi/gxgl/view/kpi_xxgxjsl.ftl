<table class="repgGrid">
<tr>
<th width="50">序号</th>
<th width="100">部门</th>
<th width="100">人员</th>
<th width="100">超时考核</th>
<th>说明</th>
</tr>
<#assign total = 0>
<#list data.xxgxjsls as aobj>
<#assign total = total + aobj.cskh?number>
<tr>
<td >${aobj_index+1}</td>
<td>${aobj.deptname}</td>
<td>${aobj.cname}</td>
<td><a href="${base}/module/pams/kpi/gxgl/kpi_main_xxgxjsllcmx.action?loginname=${aobj.loginname}">${aobj.cskh?number?string("#.00")}</a></td>
<td></td>
</tr>
</#list>

<tr>
<td>合计：</td>
<td></td>
<td></td>
<td>${total}</td>
<td></td>
</tr>
</table>