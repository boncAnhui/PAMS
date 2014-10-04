<table class="repgGrid">
<tr>

<th width="40">序号</th>
<th width="300">统计单位</th>

<th >已发布总数</th>
<th >正常发布总数</th>
<th >正常发布节点总数</th>
<th >超时发布节点总数</th>
<th >节点超时比</th>
<th >超时发布总数</th>
<th >计划执行时长</th>
<th >实际执行时长</th>
<th >执行效率</th>
</tr>

<#assign sum_ayfbzs = 0>
<#assign sum_azcfbzs = 0>
<#assign sum_azcfbjdzs = 0>
<#assign sum_acsfbjdzs = 0>
<#assign sum_acsfbzs = 0>
<#assign sum_cszsc = 0>
<#assign sum_jhzxsc = 0>
<#assign sum_sjzxsc = 0>

<#assign rate_jdcsb = 0>
<#assign rate_zxjsl = 0>

<#list data.fbzs as afbzs>

<#assign azcfbzs = data.zcfbzs[afbzs_index]>
<#assign azcfbjdzs = data.zcfbjdzs[afbzs_index]>
<#assign acsfbjdzs = data.csfbjdzs[afbzs_index]>
<#assign acsfbzs = data.csfbzs[afbzs_index]>
<#assign ayfbsxzc = data.yfbsxzc[afbzs_index]>

<#if azcfbjdzs.num?number == 0 && acsfbjdzs.num?number == 0 >
	<#assign rate_jdcsb = 0>
<#else>
	<#assign rate_jdcsb = acsfbjdzs.num?number/(azcfbjdzs.num?number + acsfbjdzs.num?number)>
</#if>

<#if ayfbsxzc.jhsc?number == 0 && ayfbsxzc.sjsc?number == 0 >
	<#assign rate_zxjsl = 0>
<#else>
	<#assign rate_zxjsl =  ayfbsxzc.jhsc?number/ayfbsxzc.sjsc?number>
</#if>

<tr>
<td>${afbzs_index+1}</td>
<td><a href="${base}/module/pams/gxgl/rep/wcqk/bm/rep_main_wcqk.action?internal=${afbzs.internal}&begindate=${arg.begindate}&enddate=${arg.enddate}" target="_blank">${afbzs.cname}</a></td>
<td>${afbzs.num}</td>
<td>${azcfbzs.num}</td>
<td>${azcfbjdzs.num}</td>
<td>${acsfbjdzs.num}</td>

<td>${rate_jdcsb?string("#.0#%")}</td>
<td>${acsfbzs.num}</td>
<td>${ayfbsxzc.jhsc}</td>
<td>${ayfbsxzc.sjsc}</td>
<td>${rate_zxjsl?string("#.0#%")}</td>
</tr>

<#assign sum_ayfbzs = sum_ayfbzs + afbzs.num?number>
<#assign sum_azcfbzs = sum_azcfbzs + azcfbzs.num?number>
<#assign sum_azcfbjdzs = sum_azcfbjdzs + azcfbjdzs.num?number>
<#assign sum_acsfbjdzs = sum_acsfbjdzs + acsfbjdzs.num?number>
<#assign sum_acsfbzs = sum_acsfbzs + acsfbzs.num?number>

</#list>

<tr>
<td></td>
<td></td>
<td>${sum_ayfbzs}</td>
<td>${sum_azcfbzs}</td>
<td>${sum_azcfbjdzs}</td>
<td>${sum_acsfbjdzs}</td>
<td></td>
<td>${sum_acsfbzs}</td>
<td></td>
<td></td>
<td></td>
</tr>

</table>