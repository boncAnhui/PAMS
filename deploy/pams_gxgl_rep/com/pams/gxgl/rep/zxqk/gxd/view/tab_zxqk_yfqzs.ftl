<table class="repgGrid">
<tr>
<th width="40">序号</th>
<th width="150">人员</th>
<th>业务单号</th>
<th>业务节点</th>
<th>执行时长（天）</th>
<th>执行时长（小时）</th>
<th>超时时长</th>
</tr>
<#assign creatername_old = "">
<#assign cno_old = "">

<#assign total = 0>
<#list data.zxscs as aobj>
<tr>
<td>${aobj_index + 1}</td>
<td><#if creatername_old == "${aobj.creatername}"><#else>${aobj.creatername}</#if></td>
<td><#if cno_old == "${aobj.cno}"><#else><a href="${base}/module/app/system/workflow/ui/flowstat.action?runflowkey=${aobj.runflowkey}" target="_blank">${aobj.cno}</a></#if></td>
<td>${aobj.actcname}</td>
<td>${aobj.zxsc?number?string("#.##")}</td>
<td>${((aobj.zxsc?number)*24)?string("#.##")}</td>
<td>${aobj.zxsccs}</td>
</tr>
<#assign creatername_old = aobj.creatername>
<#assign cno_old = aobj.cno>
</#list>

<tr>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
</table>
