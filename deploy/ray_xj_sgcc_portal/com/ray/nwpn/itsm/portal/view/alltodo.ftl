<h2><span class="t">待办业务</span>
<span class="more">
<!--
<a href="javascript:redirctmoduleurl('${base}/menu!otherLink.action','${base}/module/irm/portal/portal/portal/portal_browseAllToDo.action?_searchname=portal.browsealltodo')">更多›</a>
-->
</span>
</h2>
<div class="h2sec">
<ul class="glist">

<#list data.alltodo as aobj>
<li>
	<span class="date">${aobj.actcname}&nbsp; &nbsp;${aobj.sendercname}&nbsp; &nbsp;<#if aobj.sendtime!="">${aobj.sendtime?datetime("yyyyy-MM-dd HH:mm")}</#if></span>
	<span class="cat">
	<#if aobj.tableid='InfoShare'>[共享管理]
		</span>
		<!-- <a href="${base}/module/pams/gxgl/gxwh/apply_locateframe.action?runactkey=${aobj.runactkey}"> -->
		<a href="javascript:openwin('${base}/module/pams/gxgl/gxwh/apply_readpageframe.action?runactkey=${aobj.runactkey}','waitwork',pub_width_large,pub_height_large,null)">
		<#if aobj.title?length gt 100>${aobj.title?substring(0,99)}...<#else>${aobj.title}</#if></a>
	</#if>
		
</li>

</#list>

</div>