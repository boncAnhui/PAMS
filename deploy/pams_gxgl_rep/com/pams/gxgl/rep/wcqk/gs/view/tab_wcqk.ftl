<table class="repgGrid">
<tr>

</tr>
<tr>
<th width="100">已发布总数</th>
<th width="100">正常发布总数</th>
<th width="100">正常发布节点总数</th>
<th width="100">超时发布节点总数</th>
<th width="100">节点超时比</th>
<th width="100">超时发布总数</th>
<th width="100">信息共享及时性总数</th>
<th width="100">超时总时长</th>
<th width="100">计划执行时长</th>
<th width="100">实际执行时长</th>
<th width="100">执行及时率</th>
</tr>

<#list data.fbzs as afbzs>

<#assign azcfbzs = data.zcfbzs[afbzs_index]>
<#assign azcfbjdzs = data.zcfbjdzs[afbzs_index]>
<#assign acsfbjdzs = data.csfbjdzs[afbzs_index]>
<#assign acsfbzs = data.csfbzs[afbzs_index]>
<#assign ayfbsxzc = data.yfbsxzc[afbzs_index]>
<tr>

<td>${afbzs.num}</td>
<td>${azcfbzs.num}</td>
<td>${azcfbjdzs.num}</td>
<td>${acsfbjdzs.num}</td>

<td></td>
<td>${acsfbzs.num}</td>
<td></td>
<td></td>
<td>${ayfbsxzc.jhsc}</td>
<td>${ayfbsxzc.sjsc}</td>
<td></td>
</tr>



</#list>

</table>