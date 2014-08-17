<#if data.jsonobj??>
${data.jsonobj}
<#else>
[
    <#list data.subjects as subject>
	{name:'${subject.cname}',pname:'${subject.pname}',visable:"${subject.visable!}",url:'${base}${subject.url}',status:'opened',id:'${subject.id}',type:'${subject.ctype}'}<#if subject_has_next>,</#if>
	</#list>
]
</#if>