<table class="repgGrid">
<tr>
<th width="40">序号</th>
<th width="100">部门</th>
<th width="100">超时(小时)</th>
<th width="100">考核分</th>
<th>说明</th>
</tr>
<#assign total = 0>
<#list data.xxgxjsls as aobj>
<#assign total = total + aobj.cskh?number>
<tr>
<td>${aobj_index+1}</td>
<td>${aobj.deptname}</td>
<td>${aobj.bzsc}</td>
<td>
<a href="${base}/module/pams/kpi/gxgl/kpi_main_xxgxjsllcmx.action?ownerdept=${aobj.odept}&begindate=${arg.begindate}&enddate=${arg.enddate}">${aobj.cskh}</a>
</td>
<td></td>
</tr>
</#list>

<tr>
<td>合计：</td>
<td></td>
<td></td>
<td>${total}</td>
<td></td>
<td></td>
</tr>
</table>