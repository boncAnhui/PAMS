<fieldset><legend>调度业务情况</legend>
	<ul class="fsc">
		<li>告警总量 (<#if data.tjzl.gjzl!=0><a href="javascript:void(0)" onclick="open_alarm('${data.map.begintime}','${data.map.endtime}','${data.map.type}')">${data.tjzl.gjzl}</a><#else>${data.tjzl.gjzl}</#if>)</li>
		<li>缺陷总量 (<#if data.tjzl.qxzl!=0><a href="javascript:void(0)" onclick="open_defect('${data.map.begintime}','${data.map.endtime}','${data.map.type}')">${data.tjzl.qxzl}</a><#else>${data.tjzl.qxzl}</#if>)</li>
	</ul>
</fieldset>	
<fieldset><legend>运行业务情况</legend>
	<ul class="fsc">
		<li>应急预案执行总量 (<#if data.tjzl.yjzl!=0><a href="javascript:void(0)" onclick="open_yjya('${data.map.begintime}','${data.map.endtime}','${data.map.type}')">${data.tjzl.yjzl}</a><#else>${data.tjzl.yjzl}</#if>)</li>
	</ul>
</fieldset>	
<fieldset><legend>检修业务情况</legend>
	<ul class="fsc">
		<li>巡检总量 (<#if data.tjzl.xjzl!=0><a href="javascript:void(0)" onclick="open_audit('${data.map.begintime}','${data.map.endtime}','${data.map.type}')">${data.tjzl.xjzl}</a><#else>${data.tjzl.xjzl}</#if>)</li>
		<li>检修计划总量 (<#if data.tjzl.jxzl!=0><a href="javascript:void(0)" onclick="open_maintance('${data.map.begintime}','${data.map.endtime}','${data.map.type}')">${data.tjzl.jxzl}</a><#else>${data.tjzl.jxzl}</#if>)</li>
		<li>工作票总量 (<#if data.tjzl.bgzl!=0><#if data.map.type!='fyyxt'><a href="javascript:void(0)" onclick="open_change('${data.map.begintime}','${data.map.endtime}','${data.map.type}')">${data.tjzl.bgzl}</a><#else>0</#if><#else>${data.tjzl.bgzl}</#if>)</li>
		<li>可用性总量 (<#if data.tjzl.kyxzl!=0><#if data.map.type!='fyyxt'><a href="javascript:void(0)" onclick="open_stopapp('${data.map.begintime}','${data.map.endtime}','${data.map.type}')">${data.tjzl.kyxzl}</a><#else>${data.tjzl.kyxzl}</#if><#else>${data.tjzl.kyxzl}</#if>)</li>
	</ul>
</fieldset>	
<fieldset><legend>客服业务情况</legend>
	<ul class="fsc">
		<li>事件总量 (<#if data.tjzl.sjzl!=0><a href="javascript:void(0)" onclick="open_event('${data.map.begintime}','${data.map.endtime}','${data.map.type}')">${data.tjzl.sjzl}</a><#else>${data.tjzl.sjzl}</#if>)</li>
		<li>热线电话总量 (<#if data.tjzl.dhzl!=0><#if data.map.type!='yyxt'><a href="javascript:void(0)" onclick="open_phonerecord('${data.map.begintime}','${data.map.endtime}','${data.map.type}')">${data.tjzl.dhzl}</a><#else>0</#if><#else>${data.tjzl.dhzl}</#if>)</li>
		<li>备品备件使用总量 (<#if data.tjzl.bpbjzl!=0><a href="javascript:void(0)" onclick="open_equip('${data.map.begintime}','${data.map.endtime}','${data.map.type}')">${data.tjzl.bpbjzl}</a><#else>${data.tjzl.bpbjzl}</#if>)</li>
	</ul>
</fieldset>	
<fieldset><legend>系统建设</legend>
	<ul class="fsc">
		<li>建转运系统总量 (<#if data.tjzl.jzyzl!=0><a href="javascript:void(0)" onclick="open_ctooperation('${data.map.begintime}','${data.map.endtime}','${data.map.type}')">${data.tjzl.jzyzl}</a><#else>${data.tjzl.jzyzl}</#if>)</li>
	</ul>
</fieldset>	