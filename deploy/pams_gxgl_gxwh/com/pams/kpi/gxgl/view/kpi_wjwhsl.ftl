<table class="repgGrid">
<tr>
<th width="40">序号</th>
<th width="100">部门</th>
<th width="100">人员</th>
<th width="100">共享单数量</th>
</tr>
<#assign total = 0>
<#list data.wjwhsls as aobj>
<#assign total = total + aobj.nums?number>
<tr>
<td>${aobj_index+1}</td>
<td>${aobj.deptname}</td>
<td>${aobj.createname}</td>
<td>
<a href="${base}/module/pams/kpi/gxgl/kpi_main_wjwhlist.action?creater=${aobj.creater}&begindate=${arg.begindate}&enddate=${arg.enddate}">${aobj.nums}</a></td>

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