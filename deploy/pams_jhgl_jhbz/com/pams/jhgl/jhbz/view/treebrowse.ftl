<#assign nums = data.plans?size>
[
<#list data.plans as aobj>
{leaf:${aobj.islast},name:'${aobj.title}',id:'${aobj.id}',level:'${aobj.tlevel}',cells:{planbegintime:'${aobj.planbegintime}',planendtime:'${aobj.planendtime}',planperiodnum:'${aobj.planperiodnum}',periodnum:'${aobj.periodnum}',headername:'${aobj.headername}',headerdeptname:'${aobj.headerdeptname}',creatername:'${aobj.creatername}',version:'${aobj.version}',state:'${aobj.state}',planmodelid:'${aobj.planmodelid}',flowdefid:'${aobj.flowdefid}',actdefid:'${aobj.actdefid}'}}<#if aobj_index &lt; nums - 1>,</#if>
</#list>
]
