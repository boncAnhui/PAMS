<table class="repgGrid">
<tr>

</tr>
<tr>
<th width="40">序号</th>
<th width="60">人员</th>
<th >已发起总数</th>
<th >已发布总数</th>
<th >未发布总数</th>
<th >正常发布总数</th>
<th >超时发布总数</th>
<th >发布文件总数</th>
<th >超时率</th>
<th >正常执行总数</th>
<th >超时执行总数</th>
<th >超时率</th>
</tr>

<#assign sum_ayfqzs = 0>
<#assign sum_ayfbzs = 0>
<#assign sum_awfbzs = 0>
<#assign sum_ayfbzs_zcfbzs = 0>
<#assign sum_ayfbzs_csfbzs = 0>
<#assign sum_ayfbzs_wjzs = 0>
<#assign sum_awfbzs_zczs = 0>
<#assign sum_awfbzs_cszs = 0>

<#list data.yfqzs as ayfqzs>
<#assign ayfbzs = data.yfbzs[ayfqzs_index]>
<#assign awfbzs = data.wfbzs[ayfqzs_index]>
<#assign ayfbzs_zcfbzs = data.yfbzs_zcfbzs[ayfqzs_index]>
<#assign ayfbzs_csfbzs = data.yfbzs_csfbzs[ayfqzs_index]>
<#assign ayfbzs_wjzs = data.yfbzs_wjzs[ayfqzs_index]>
<#assign awfbzs_zczs = data.wfbzs_zczs[ayfqzs_index]>
<#assign awfbzs_cszs = data.wfbzs_cszs[ayfqzs_index]>

<tr>
<td>${ayfqzs_index+1}</td>
<td>${ayfqzs.cname}</td>
<td><a href="${base}/module/pams/gxgl/rep/zxqk/gxd/rep_main_zxqk_yfqzs.action?ownerctx=${ayfqzs.loginname}&begindate=${arg.begindate}&enddate=${arg.enddate}" target="_blank">${ayfqzs.num}</a></td>
<td><a href="${base}/module/pams/gxgl/rep/zxqk/gxd/rep_main_zxqk_yfbzs.action?ownerctx=${ayfqzs.loginname}&begindate=${arg.begindate}&enddate=${arg.enddate}" target="_blank">${ayfbzs.num}</a></td>
<td><a href="${base}/module/pams/gxgl/rep/zxqk/gxd/rep_main_zxqk_wfbzs.action?ownerctx=${ayfqzs.loginname}&begindate=${arg.begindate}&enddate=${arg.enddate}" target="_blank">${awfbzs.num}</a></td>
<td><a href="${base}/module/pams/gxgl/rep/zxqk/gxd/rep_main_zxqk_yfbzs_zcfbzs.action?ownerctx=${ayfqzs.loginname}&begindate=${arg.begindate}&enddate=${arg.enddate}" target="_blank">${ayfbzs_zcfbzs.num}</a></td>
<td><a href="${base}/module/pams/gxgl/rep/zxqk/gxd/rep_main_zxqk_yfbzs_csfbzs.action?ownerctx=${ayfqzs.loginname}&begindate=${arg.begindate}&enddate=${arg.enddate}" target="_blank">${ayfbzs_csfbzs.num}</a></td>
<td>${ayfbzs_wjzs.num}</td>
<td></td>
<td><a href="${base}/module/pams/gxgl/rep/zxqk/gxd/rep_main_zxqk_wfbzs_zczs.action?ownerctx=${ayfqzs.loginname}&begindate=${arg.begindate}&enddate=${arg.enddate}" target="_blank">${awfbzs_zczs.num}</a></td>
<td><a href="${base}/module/pams/gxgl/rep/zxqk/gxd/rep_main_zxqk_wfbzs_cszs.action?ownerctx=${ayfqzs.loginname}&begindate=${arg.begindate}&enddate=${arg.enddate}" target="_blank">${awfbzs_cszs.num}</a></td>
<td></td>
</tr>

<#assign sum_ayfqzs = sum_ayfqzs + ayfqzs.num?number>
<#assign sum_ayfbzs = sum_ayfbzs + ayfbzs.num?number>
<#assign sum_awfbzs = sum_awfbzs + awfbzs.num?number>
<#assign sum_ayfbzs_zcfbzs = sum_ayfbzs_zcfbzs + ayfbzs_zcfbzs.num?number>
<#assign sum_ayfbzs_csfbzs = sum_ayfbzs_csfbzs + ayfbzs_csfbzs.num?number>
<#assign sum_ayfbzs_wjzs = sum_ayfbzs_wjzs + ayfbzs_wjzs.num?number>
<#assign sum_awfbzs_zczs = sum_awfbzs_zczs + awfbzs_zczs.num?number>
<#assign sum_awfbzs_cszs = sum_awfbzs_cszs + awfbzs_cszs.num?number>

</#list>

<tr>
<td></td>
<td></td>
<td>${sum_ayfqzs}</td>
<td>${sum_ayfbzs}</td>
<td>${sum_awfbzs}</td>
<td>${sum_ayfbzs_zcfbzs}</td>
<td>${sum_ayfbzs_csfbzs}</td>
<td>${sum_ayfbzs_wjzs}</td>
<td></td>
<td>${sum_awfbzs_zczs}</td>
<td>${sum_awfbzs_cszs}</td>
<td></td>
</tr>

</table>