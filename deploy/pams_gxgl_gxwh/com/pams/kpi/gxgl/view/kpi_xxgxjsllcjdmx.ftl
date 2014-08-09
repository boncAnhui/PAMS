<table class="repgGrid">
<tr>
<th width="50">序号</th>
<th width="100">流程节点</th>
<th width="100">标准时长</th>
<th width="100">执行时长</th>
</tr>
<#assign total_bzsc = 0>
<#assign total_zxsc = 0>
<#list data.xxgxjsllcjdmxs as aobj>
<#assign total_bzsc = total_bzsc + aobj.bzsc?number>
<#assign total_zxsc = total_zxsc + aobj.zxsc?number>
<tr>
<td >${aobj_index+1}</td>
<td>${aobj.actname}</td>
<td>${aobj.bzsc}</td>
<td>${aobj.zxsc?number?string("#.##")}</td>
</tr>
</#list>

<tr>
<td>总计</td>
<td></td>
<td>${total_bzsc}</td>
<td>${total_zxsc?string("#.00")}</td>
</tr>
</table>