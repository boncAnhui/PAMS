<table class="repgGrid">
<tr>
<th width="40">序号</th>
<th width="100">部门</th>
<th width="100">人员</th>
<th width="100">超时(小时)</th>
<th width="100">考核分</th>
<th>说明</th>
</tr>
<#assign total = 0>
<#list data.zxscs as aobj>
<#assign total = total + aobj.zxsccskh?number>
<tr>
<td>${aobj_index+1}</td>
<td>${aobj.deptname}</td>
<td>${aobj.cname}</td>
<td>${aobj.zxsccskh}</td>
<td>
<a href="${base}/module/pams/kpi/gxgl/kpi_main_zxscmx.action?ownerctx=${aobj.ownerctx}&begindate=${arg.begindate}&enddate=${arg.enddate}">${aobj.zxsccskh}</a>
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