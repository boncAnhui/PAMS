<table class="repgGrid">
<tr>
<th width="50">序号</th>
<th width="100">单据号</th>
<th width="100">获取时间</th>
<th width="100">发布时间</th>
<th width="100">标准时长</th>
<th width="100">执行时长</th>
<th width="100">考核分</th>
</tr>
<#assign total = 0>
<#list data.xxgxjsllcmxs as aobj>
<#assign total = total + aobj.cskh?number>
<tr>
<td >${aobj_index+1}</td>
<td>${aobj.cno}</td>
<td><#if aobj.obtaintime!=''>${aobj.obtaintime?date?string('yyyy-MM-dd hh:mm')}</#if></td>
<td><#if aobj.publishtime!=''>${aobj.publishtime?date?string('yyyy-MM-dd hh:mm')}</#if></td>
<td>${aobj.bzsc?number?string('#.00')}</td>
<td>${aobj.zxsc?number?string('#.00')}</td>
<td><a href="${base}/module/pams/kpi/gxgl/kpi_main_xxgxjsllcjdmx.action?cno=${aobj.cno}&begindate=${arg.begindate}&enddate=${arg.enddate}">${aobj.cskh?number?string('#.00')}</a></td>
</tr>
</#list>

<tr>
<td>合计：</td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td>${total}</td>
</tr>
</table>