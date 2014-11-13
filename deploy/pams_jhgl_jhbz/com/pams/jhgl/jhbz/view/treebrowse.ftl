<#assign nums = data.plans?size>
[
<#list data.plans as aobj>
{leaf:${aobj.islast},name:'${aobj.title}',id:'${aobj.id}',level:'${aobj.tlevel}',cells:['${aobj.planbegintime}','${aobj.planendtime}','${aobj.headername}','${aobj.headerdeptname}','${aobj.creatername}','${aobj.version}','${aobj.state}','${aobj.planmodelid}']}<#if aobj_index &lt; nums - 1>,</#if>
</#list>
]
