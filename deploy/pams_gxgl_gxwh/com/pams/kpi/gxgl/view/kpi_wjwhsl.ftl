<table class="repgGrid">
<tr>
<th width="40">序号</th>
<th width="100">部门</th>
<th width="100">人员</th>
<th width="100">文件数量</th>
<th>说明</th>
</tr>
<#assign total = 0>
<#list data.wjwhsls as aobj>
<#assign total = total + aobj.filenums?number>
<tr>
<td>${aobj_index+1}</td>
<td>${aobj.deptname}</td>
<td>${aobj.creatername}</td>
<td>${aobj.filenums}</td>
<td>
<a href="${base}/module/pams/gxgl/gxwh/apply_readpageframe.action?temp=temp&runactkey=${aobj.id}">${aobj.memo}</a>
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