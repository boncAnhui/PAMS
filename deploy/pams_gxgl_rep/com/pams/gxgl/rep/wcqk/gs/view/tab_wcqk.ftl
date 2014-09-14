<table class="repgGrid">
<tr>
<th width="300">统计单位</th>

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
<td><a href="${base}/module/pams/gxgl/rep/wcqk/bm/rep_main_wcqk.action?internal=${afbzs.internal}&begindate=${arg.begindate}&enddate=${arg.enddate}">${afbzs.cname}</a></td>
<td>${azcfbzs.num}</td>
<td>${azcfbjdzs.num}</td>
<td>${acsfbjdzs.num}</td>
<td>${acsfbzs.num}</td>
<td>${ayfbsxzc.num}</td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>



</#list>

</table>