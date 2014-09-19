<table class="repgGrid">
<tr>

</tr>
<tr>

<th width="40">序号</th>
<th width="60">人员</th>
<th >未发布总数</th>
<th >正常执行总数</th>
<th >超时执行总数</th>
<th >已执行节点数</th>
<th >节点超期总数</th>

</tr>

<#assign sum_awfbzs = 0>
<#assign sum_azczxzs = 0>
<#assign sum_acszxzs = 0>
<#assign sum_ayzxjdzs = 0>
<#assign sum_ajdcszs = 0>


<#list data.wfbzs as awfbzs>

<#assign azczxzs = data.zczxzs[awfbzs_index]>
<#assign acszxzs = data.cszxzs[awfbzs_index]>
<#assign ayzxjdzs = data.yzxjdzs[awfbzs_index]>
<#assign ajdcszs = data.jdcszs[awfbzs_index]>

<tr>
<td>${awfbzs_index+1}</td>
<td>${awfbzs.cname}</td>
<td><a href="${base}/module/pams/gxgl/rep/zxqk/gxd/rep_main_zxqk_yfqzs.action?ownerctx=${awfbzs.loginname}&begindate=${arg.begindate}&enddate=${arg.enddate}" target="_blank">${awfbzs.num}</a></td>
<td>${azczxzs.num}</td>
<td>${acszxzs.num}</td>
<td>${ayzxjdzs.num}</td>
<td>${ajdcszs.num}</td>

</tr>

<#assign sum_awfbzs = sum_awfbzs + awfbzs.num?number>
<#assign sum_azczxzs = sum_azczxzs + azczxzs.num?number>
<#assign sum_acszxzs = sum_acszxzs + acszxzs.num?number>
<#assign sum_ayzxjdzs = sum_ayzxjdzs + ayzxjdzs.num?number>
<#assign sum_ajdcszs = sum_ajdcszs + ajdcszs.num?number>


</#list>

<tr>
<td>合计:</td>
<td></td>
<td>${sum_awfbzs}</td>
<td>${sum_azczxzs}</td>
<td>${sum_acszxzs}</td>
<td>${sum_ayzxjdzs}</td>
<td>${sum_ajdcszs}</td>
</tr>

</table>