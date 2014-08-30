<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
	<style type="text/css">
		* {margin: 0; padding: 0;}
		body {font: 14px/22px "Microsoft YaHei", arial, serif;}
		a, a:link, a:visited {color: #ccc; text-decoration: none;}

		.content {margin: 50px auto; width: 180px; height: auto;}
		.content > ul {list-style: none;}
		.content .menu-one > li {width: 180px; height: auto; overflow: hidden; border-top: 1px solid #888;}
		.content .menu-one > li.firstChild {border: 0;}
		.content .menu-one .header {height: 35px; background: #666; line-height: 34px; text-indent: 15px; cursor: pointer;}
		.content .menu-one .header:hover,
		.content .menu-one .menuOne-current {background: #777;}
		.content .menu-one .header > span {display: block;}
		.content .menu-one .header .txt {float: left; color: #fff;}
		.content .menu-one .header .arrow {float: right; width: 35px; height: 35px; background: url(arrow-d.png) no-repeat center center;}
		.content .menu-two {display: none; width: 180px; height: auto;}
		.content .menu-two li {width:180px; height:35px; background: #eee; border-top: 1px solid #ccc; line-height: 34px; text-indent: 40px;}
		.content .menu-two li.firstChild {border: 0;}
		.content .menu-two li a {display: block; color: #888;}
		.content .menu-two li:hover,
		.content .menu-two li.menuTwo-current {background: #fff;}

		.content .menu-show .header {background: #777;}
		.content .menu-show .header .arrow {background-image: url(arrow-u.png);}
	</style>
</head>
<body>


<div class="content">
		<ul class="menu-one">
			<li class="firstChild">
				<div class="header">
					<span class="txt">系统功能</span>
					<span class="arrow"></span>
				</div>
				<ul class="menu-two">
					<li class="firstChild"><a href="/pams/module/app/system/workflow/define/define_main.action" target="mainIframe">流程定义</a></li>
				</ul>
			</li>
			<li>
				<div class="header">
					<span class="txt">主流程</span>
					<span class="arrow"></span>
				</div>
				<ul class="menu-two">
					<li class="firstChild"><a href="/pams/module/pams/hbgl/main/apply_browsegroupsall.action?_searchname=hbgl.main.browsegroupall&flowsno=HBGL" target="mainIframe">全体工作</a></li>
				</ul>
			</li>
			<li>
				<div class="header">
					<span class="txt">信息收集</span>
					<span class="arrow"></span>
				</div>
				<ul class="menu-two">
					<li class="firstChild"><a href="/pams/module/pams/hbgl/xxsj/apply_browsewait.action?_searchname=hbgl.xxsj.browsewait&flowsno=HBGL_XXSJ" target="mainIframe">待办工作</a></li>
					<li><a href="/pams/module/pams/hbgl/xxsj/apply_browsehandle.action?_searchname=hbgl.xxsj.browsehandle&flowsno=HBGL_XXSJ" target="mainIframe">已处理工作</a></li>
					<li><a href="/pams/module/pams/hbgl/xxsj/apply_browsegroupall.action?_searchname=hbgl.xxsj.browsegroupall&flowsno=HBGL_XXSJ" target="mainIframe">全体工作</a></li>
				</ul>
			</li>
			<li>
				<div class="header">
					<span class="txt">用户申请</span>
					<span class="arrow"></span>
				</div>
				<ul class="menu-two">
					<li class="firstChild"><a href="/pams/module/pams/hbgl/yhsq/apply_browserelay.action?_searchname=hbgl.yhsq.browserelay&flowsno=HBGL_XXSJ" target="mainIframe">收件箱</a></li>
					<li><a href="/pams/module/pams/hbgl/yhsq/apply_browsewait.action?_searchname=hbgl.yhsq.browsewait&flowsno=HBGL_YHSQ" target="mainIframe">待办工作</a></li>
					<li><a href="/pams/module/pams/hbgl/yhsq/apply_browsehandle.action?_searchname=hbgl.yhsq.browsehandle&flowsno=HBGL_YHSQ" target="mainIframe">已处理工作</a></li>
					<li><a href="/pams/module/pams/hbgl/yhsq/apply_browsegroupall.action?_searchname=hbgl.yhsq.browsegroupall&flowsno=HBGL_YHSQ" target="mainIframe">全体工作</a></li>
				</ul>
			</li>

		</ul>
	</div>

<script type="text/javascript" src="/pams/themes/default/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		var aMenuOneLi = $(".menu-one > li");
		var aMenuTwo = $(".menu-two");
		$(".menu-one > li > .header").each(function (i) {
			$(this).click(function () {
				if ($(aMenuTwo[i]).css("display") == "block") {
					$(aMenuTwo[i]).slideUp(300);
					$(aMenuOneLi[i]).removeClass("menu-show")
				} else {
					for (var j = 0; j < aMenuTwo.length; j++) {
						$(aMenuTwo[j]).slideUp(300);
						$(aMenuOneLi[j]).removeClass("menu-show");
					}
					$(aMenuTwo[i]).slideDown(300);
					$(aMenuOneLi[i]).addClass("menu-show")
				}
			});
		});
	});
</script>



</body>
</html>