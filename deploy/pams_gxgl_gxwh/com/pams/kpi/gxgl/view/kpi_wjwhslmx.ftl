<table class="repgGrid">
<tr>
<th width="40">序号</th>
<th width="150">人员</th>
<th>业务单号</th>
<th>业务节点</th>
<th>执行时长</th>
<th>超时时长</th>
<th>考核分</th>
</tr>
<#assign total = 0>
<#list data.zxscs as aobj>
<#assign total = total + aobj.zxsccskh?number>
<tr>
<td>${aobj_index+1}</td>
<td>${aobj.cname}</td>
<td>${aobj.cno}</td>
<td>${aobj.actname}</td>
<td>${aobj.zxsc?number?string("#.##")}</td>
<td>${aobj.zxsccs}</td>
<td><a href="javascript:void(0)" onclick="page_open('${aobj.runflowkey}')">${aobj.zxsccskh}</a></td>
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

<script>
function page_open(runflowkey)
{
	url = '${base}/module/pams/gxgl/gxwh/apply_readpagekeyframe.action?runflowkey=' + runflowkey;
	openwin(url,"about:blank",pub_width_mid,pub_height_mid);	
}		
</script>