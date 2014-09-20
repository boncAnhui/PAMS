<table class="repgGrid">
<tr>
<th width="40">序号</th>
<th width="300">公司</th>
<th width="100">考核总分</th>
</tr>
<#assign total = 0>
<#list data.jsls as aobj>
<#assign total = total + aobj.zxsccskh?number>
<tr>
<td>${aobj_index+1}</td>
<td>${aobj.orgcname}</td>
<td>
<a href="${base}/module/pams/gxgl/kpi/jsl/rep_main_jsl_ry.action?internal=${aobj.internal}&begindate=${arg.begindate}&enddate=${arg.enddate}">${aobj.zxsccskh}</a>
</td>
</tr>
</#list>

<tr>
<td></td>
<td></td>
<td>${total}</td>
</tr>
</table>