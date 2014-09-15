<table class="repgGrid">
<tr>

</tr>
<tr>
<th width="300">部门</th>
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

<#assign sum_ayfbzs = 0>
<#assign sum_azcfbzs = 0>
<#assign sum_azcfbjdzs = 0>
<#assign sum_acsfbjdzs = 0>
<#assign sum_acsfbzs = 0>
<#assign sum_cszsc = 0>
<#assign sum_jhzxsc = 0>
<#assign sum_sjzxsc = 0>

<#list data.fbzs as afbzs>

<#assign azcfbzs = data.zcfbzs[afbzs_index]>
<#assign azcfbjdzs = data.zcfbjdzs[afbzs_index]>
<#assign acsfbjdzs = data.csfbjdzs[afbzs_index]>
<#assign acsfbzs = data.csfbzs[afbzs_index]>
<#assign ayfbsxzc = data.yfbsxzc[afbzs_index]>
<tr>
<td>${afbzs.cname}</td>
<td><a href="${base}/module/pams/gxgl/rep/zxqk/gxd/rep_main_zxqk_yfqzs.action?ownerctx=${afbzs.loginname}&begindate=${arg.begindate}&enddate=${arg.enddate}" target="_blank">${afbzs.num}</a></td>
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

<#assign sum_ayfbzs = sum_ayfbzs + afbzs.num?number>
<#assign sum_azcfbzs = sum_azcfbzs + azcfbzs.num?number>
<#assign sum_azcfbjdzs = sum_azcfbjdzs + azcfbjdzs.num?number>
<#assign sum_acsfbjdzs = sum_acsfbjdzs + acsfbjdzs.num?number>
<#assign sum_acsfbzs = sum_acsfbzs + acsfbzs.num?number>

</#list>

<tr>
<td></td>
<td>${sum_ayfbzs}</td>
<td>${sum_azcfbzs}</td>
<td>${sum_azcfbjdzs}</td>
<td>${sum_acsfbjdzs}</td>
<td>${sum_acsfbzs}</td>
</tr>

</table>