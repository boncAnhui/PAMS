<h2><span class="title rep_event_dh_ldbm">电话统计（部门分组）</span><span class="more"><select id="select_dh_ldbm" name="_ctype" onchange="page_load_event_dh_ldbm()"><option value="Column3D">&nbsp;数值方式&nbsp;</option><option value="Pie3D">&nbsp;百分比方式&nbsp;</option></select></span></h2>
<div class="p8" id="rep_event_dh_ldbm">
</div>

<script>
function page_load_event_dh_ldbm()
{
	begindate = mform.begindate.value;
	enddate = mform.enddate.value;
	
	type = select_dh_ldbm.value;
	
	url = "${base}/chart/chart_main.action";
	url += "?_chartname=event.dh.ldbm";
	url += "&_div=dh_ldbm";
	url += "&_type=" + type;　　
	
	url += "&random=" + Math.random();
	url += "&begindate=" + begindate;
	url += "&enddate=" + enddate;

	$('#rep_event_dh_ldbm').load(url);
}

$(document).ready(function()
{
	page_load_event_dh_ldbm();
});
</script>