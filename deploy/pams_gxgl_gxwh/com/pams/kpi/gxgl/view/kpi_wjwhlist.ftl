<table class="repgGrid">
<tr>
<th width="40">序号</th>
<th width="100">申请人</th>
<th width="100">共享单名称</th>
<th width="100">共享文件数量</th>
</tr>
<#assign total = 0>
<#list data.wjwhsls as aobj>
<#assign total = total + aobj.filenums?number>
<tr>
<td>${aobj_index+1}</td>
<td>${aobj.creatername}</td>
<td>
${aobj.memo}
</td>
<td>
${aobj.filenums}
</td>

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