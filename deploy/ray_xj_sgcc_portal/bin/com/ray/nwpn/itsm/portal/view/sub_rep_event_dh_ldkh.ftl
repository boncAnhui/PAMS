<h2><span class="title rep_event_dh_ldkh">电话统计（客户分组）</span><span class="more"><select id="select_dh_ldkh" name="_ctype" onchange="page_load_event_dh_ldkh()"><option value="Column3D">&nbsp;数值方式&nbsp;</option><option value="Pie3D">&nbsp;百分比方式&nbsp;</option></select></span></h2>
<div class="p8" id="rep_event_dh_ldkh">
</div>

<script>
function page_load_event_dh_ldkh()
{
	begindate = mform.begindate.value;
	enddate = mform.enddate.value;
	
	type = select_dh_ldkh.value;
	
	url = "${base}/chart/chart_main.action";
	url += "?_chartname=event.dh.ldkh";
	url += "&_div=dh_ldkh";
	url += "&_type=" + type;　　
	
	url += "&random=" + Math.random();
	url += "&begindate=" + begindate;
	url += "&enddate=" + enddate;

	$('#rep_event_dh_ldkh').load(url);
}

$(document).ready(function()
{
	page_load_event_dh_ldkh();
});
</script>