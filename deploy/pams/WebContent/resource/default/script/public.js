// 常量定义 

pub_maxyear = 2100;
pub_minyear = 1900;

pub_year = 0;
pub_month = 1;
pub_date = 2;
pub_hour = 3;
pub_minute = 4;
pub_second = 5;

// 缺省界面(16v9)
pub_width_mini = 160;
pub_height_mini = 90;

pub_width_small = 320;
pub_height_small = 180;

pub_width_mid = 480;
pub_height_mid = 270;

pub_width_large = 640;
pub_height_large = 360;

pub_width_extend = 800
pub_height_extend = 450;

// 16v9界面
pub_width_mini_16v9 = 160;
pub_height_mini_16v9 = 90;

pub_width_small_16v9 = 320;
pub_height_small_16v9 = 180;

pub_width_mid_16v9 = 480;
pub_height_mid_16v9 = 270;

pub_width_large_16v9 = 640;
pub_height_large_16v9 = 360;

pub_width_extend_16v9 = 800
pub_height_extend_16v9 = 450;

// 5v3界面
pub_width_mini_5v3 = 200;
pub_height_mini_5v3 = 120;

pub_width_small_5v3 = 400;
pub_height_small_5v3 = 240;

pub_width_mid_5v3 = 600;
pub_height_mid_5v3 = 360;

pub_width_large_5v3 = 800;
pub_height_large_5v3 = 480;

pub_width_extend_5v3 = 1000;
pub_height_extend_5v3 = 600;

// 5v2界面
pub_width_mini_5v2 = 200;
pub_height_mini_5v2 = 80;

pub_width_small_5v2 = 400;
pub_height_small_5v2 = 160;

pub_width_mid_5v2 = 600;
pub_height_mid_5v2 = 240;

pub_width_large_5v2 = 800;
pub_height_large_5v2 = 320;

pub_width_extend_5v2 = 1000;
pub_height_extend_5v2 = 400;

// 新加6v2界面
pub_width_small_6v2 = 800;
pub_height_small_6v2 = 400;

pub_width_large_6v2 = 980;
pub_height_large_6v2 = 500;

pub_width_extend_6v2 = 1090;
pub_height_extend_6v2 = 580;

pub_webroot = "/ray_nwpn_itsm";
pub_workflow_webroot = "/ray_nwpn_itsm";

// 数值运算小数精确位数 货币
pub_precision_money = 2;

// 数值运算小数精确位数 计量
pub_precision_weight = 3;

// 公用函数定义

// 计算指定年月最大天数
function pub_days_max(cyear, cmonth) {
	var days = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

	if ((cyear % 400 == 0) || (cyear % 4 == 0 && cyear % 100 != 0)) {
		days[1] = 29;
	}
	return days[cmonth - 1];
}

// 货币大小写转换
function pub_chinese(num) {

	num = num / 1000.0;

	if (!/^\d*(\.\d*)?$/.test(num))
		throw (new Error(-1, "Number is wrong!"));

	var AA = new Array("零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖");

	var BB = new Array("", "拾", "佰", "仟", "萬", "億", "点", "");

	var a = ("" + num).replace(/(^0*)/g, "").split("."), k = 0, re = "";

	for ( var i = a[0].length - 1; i >= 0; i--) {
		switch (k) {
		case 0:
			re = BB[7] + re;
			break;
		case 4:
			if (!new RegExp("0{4}\\d{" + (a[0].length - i - 1) + "}$")
					.test(a[0]))
				re = BB[4] + re;
			break;
		case 8:
			re = BB[5] + re;
			BB[7] = BB[5];
			k = 0;
			break;
		}
		if (k % 4 == 2 && a[0].charAt(i) == "0" && a[0].charAt(i + 2) != "0")
			re = AA[0] + re;

		if (a[0].charAt(i) != 0)
			re = AA[a[0].charAt(i)] + BB[k % 4] + re;
		k++;

	}
	if (a.length > 1) {
		re += BB[6];
		for ( var i = 0; i < a[1].length; i++) {
			re += AA[a[1].charAt(i)];
			if (i == 2)
				break;

		}
		if (a[1].charAt(0) == "0" && a[1].charAt(1) == "0") {
		}
	} else {
	}
	return re;
}

// 窗口
function pub_openunresizablewindow(page, width, height) {
	var pheight = screen.height;
	var pwidth = screen.width;
	var newwidth = width;
	var newheight = height;
	var x = (pwidth - newwidth) / 2 - 10;
	var y = (pheight - newheight) / 2 - 25;
	mywin = window
			.open(
					page,
					'_blank',
					'height='
							+ newheight
							+ ', width='
							+ newwidth
							+ ', left='
							+ x
							+ ', top='
							+ y
							+ ', toolbar=no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no');
	mywin.focus();
}

// 通用查询排序函数
function page_sort(action, tag, field) {
	if (document.form_view._sortfield.value != field) {
		tag = "desc";
	} else {
		if (tag == "desc") {
			tag = "asc";
		} else {
			tag = "desc";
		}
	}

	document.form_view.action = action;
	document.form_view._sorttag.value = tag;
	document.form_view._sortfield.value = field;
	document.form_view.submit();
}

// 通用查询 翻页函数
function pub_query_selectpage(pagenum) {
	document.all.form_view._currentpage.value = pagenum;
}

function pub_query_go2page(url) {
	document.all.form_view.action = url;
	document.all.form_view.submit();
}

function pub_query_jump2page(pagenum, url) {
	if (event.keyCode != 13) {
		return;
	}

	document.all.form_view._currentpage.value = pagenum;
	document.all.form_view.action = url;
	document.all.form_view.submit();
}

function pub_void() {
}

// 弹出式窗口
function pub_popwin(url, width, height, title, path) {
	gPopWin(url, width, height, title, path);
}

// 日期函数
function pub_date_format_value(cdate) {
	year = cdate.getYear();
	month = cdate.getMonth() + 1;
	day = cdate.getDate();

	if (month < 10) {
		month = "0" + month;
	}

	if (day < 10) {
		day = "0" + day;
	}

	adate = year + "-" + month + "-" + day;
	return adate;
}

// 字符串转换为日期
function pub_string_to_date(cvalue) {
	fcvalue = cvalue.replace(/-/g, "/");
	adate = new Date(Date.parse(fcvalue));
	return adate;
}

// 计算指定时差日期
function pub_date_add(cdate, field, nums) {
	adate = new Date(cdate.getYear(), cdate.getMonth(), cdate.getDate());

	if (field == "D") {
		adate = new Date(adate.getYear(), adate.getMonth(), adate.getDate()
				+ nums);
		return adate;
	}

	if (field == "M") {
		adate = new Date(adate.getYear(), adate.getMonth() + (nums), adate
				.getDate());
		return adate;
	}

	if (field == "Y") {
		adate = new Date(adate.getYear() + nums, adate.getMonth(), adate
				.getDate());
		return adate;
	}
}

// 获得天的开始日期
function pub_day_begin_date(cdate, nums) {
	adate = new Date(cdate.getYear(), cdate.getMonth(), cdate.getDate() + nums);
	return adate;
}

// 获得天的开始日期
function pub_day_end_date(cdate, nums) {
	adate = new Date(cdate.getYear(), cdate.getMonth(), cdate.getDate() + nums);
	return adate;
}

// 获得周的开始日期
function pub_week_begin_date(cdate, nums) {
	adate = new Date(cdate.getYear(), cdate.getMonth(), cdate.getDate()
			- cdate.getDay() + 1);
	adate = new Date(adate.getYear(), adate.getMonth(), adate.getDate()
			+ (nums * 7));
	return adate;
}

// 获得周的结束日期
function pub_week_end_date(cdate, nums) {
	adate = new Date(cdate.getYear(), cdate.getMonth(), cdate.getDate()
			+ (6 - cdate.getDay()) + 1);
	adate = new Date(adate.getYear(), adate.getMonth(), adate.getDate()
			+ (nums * 7));
	return adate;
}

// 获得月的开始日期
function pub_month_begin_date(cdate, nums) {
	adate = new Date(cdate.getYear(), cdate.getMonth(), 1);
	adate = new Date(adate.getYear(), adate.getMonth() + (nums), adate
			.getDate());
	return adate;
}

// 获得月的结束日期
function pub_month_end_date(cdate, nums) {
	adate = new Date(cdate.getYear(), cdate.getMonth() + (nums), 1);
	maxday = pub_max_day_of_month(adate.getYear(), adate.getMonth());
	adate = new Date(adate.getYear(), adate.getMonth(), maxday);
	return adate;
}

// 获得季度的开始日期
function pub_quarter_begin_date(cdate, nums) {
	adate = new Date(cdate.getYear(), get_quarter_begin_month(cdate), 1);
	adate = new Date(adate.getYear(), adate.getMonth() + (nums * 3), 1);
	return adate;
}

// 获得季度的结束日期
function pub_quarter_end_date(cdate, nums) {
	quarter_end_month = get_quarter_begin_month(cdate) + 2;
	adate = new Date(cdate.getYear(), quarter_end_month, 1);
	adate = new Date(adate.getYear(), adate.getMonth() + (nums * 3), 1);

	max_day = pub_max_day_of_month(adate.getYear(), adate.getMonth());
	adate = new Date(adate.getYear(), adate.getMonth(), max_day);
	return adate;
}

// 获得半年的开始日期
function pub_halfyear_begin_date(cdate, nums) {
	cmonth = 0;
	if (cdate.getMonth() >= 6) {
		cmonth = 6;
	}

	adate = new Date(cdate.getYear(), cmonth + (nums * 6), 1);
	return adate;
}

// 获得半年的结束日期
function pub_halfyear_end_date(cdate, nums) {
	cmonth = 5;
	if (cdate.getMonth() > 5) {
		cmonth = 11;
	}

	adate = new Date(cdate.getYear(), cmonth + (nums * 6), 1);

	max_day = pub_max_day_of_month(adate.getYear(), adate.getMonth());
	adate = new Date(adate.getYear(), adate.getMonth(), max_day);

	return adate;
}

// 获得年的开始日期
function pub_year_begin_date(cdate, nums) {
	cmonth = 0;
	if (cdate.getMonth() >= 12) {
		cmonth = 12;
	}

	adate = new Date(cdate.getYear(), cmonth + (nums * 12), 1);
	return adate;
}

// 获得年的结束日期
function pub_year_end_date(cdate, nums) {
	cmonth = 12;
	if (cdate.getMonth() > 12) {
		cmonth = 0;
	}

	adate = new Date(cdate.getYear(), cmonth + (nums * 12), 1);

	max_day = pub_max_day_of_month(adate.getYear(), adate.getMonth());
	adate = new Date(adate.getYear(), adate.getMonth(), max_day);

	return adate;
}

// 计算本月的最大天数
function pub_max_day_of_month(cyear, cmonth) {
	var days = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	if (((cyear % 4 == 0) && (cyear % 100 != 0)) || (cyear % 400 == 0)) {
		days[1] = "29";
	}
	return days[cmonth];
}

// 计算本季度的开始月份
function get_quarter_begin_month(cdate) {
	now_month = cdate.getMonth();

	var quarter_begin_month = 0;

	if (now_month < 3) {
		quarter_begin_month = 0;
	}
	if (2 < now_month && now_month < 6) {
		quarter_begin_month = 3;
	}
	if (5 < now_month && now_month < 9) {
		quarter_begin_month = 6;
	}
	if (now_month > 8) {
		quarter_begin_month = 9;
	}
	return quarter_begin_month;
}

// 第三方外部功能函数
function gPopWin(url, width, height, title, path) {
	window.top.popWinIframePath = path;

	if (gPopWin.length < 4) {
		alert('必须有 url,width,height,title 四个参数');
		return false;
	}
	;

	wh = window.top.$('body').height();
	ww = window.top.$('body').width();
	x = ww / 2 - width / 2;
	y = wh / 2 - height / 2;
	window.top.$('body .maskDiv').remove();
	ohtml = '<div class="maskDiv" style="opacity:0.7;filter:alpha(opacity=70);z-index:102"></div><div id="gPopWin" style="padding:8px !important;padding:0;width:'
			+ width
			+ 'px;height:'
			+ height
			+ 'px;left:'
			+ x
			+ 'px;top:'
			+ y
			+ 'px;"><table style="margin:0 !important;margin:8px;"><tr class="title"><td><div class="close" title="关闭"></div><div class="t">'
			+ title
			+ '</div></td></tr><tr class="ct"><td><iframe src="'
			+ url
			+ '" width="100%" height="100%" frameborder="0"></iframe></td></tr></table><div class="maskIframe" style="position:absolute;height:100%;width:100%;background: blue;z-index:104;filter:alpha(opacity=0);opacity:0;top:40px;display:none;"></div></div>';
	// console.log(window.top.$('body').find(''))

	window.top.$('body').append(ohtml);
	// console.log(window.top.$('#gPopWin .close').html());
	window.top.$('#gPopWin .close').mouseover(function() {
		$(this).addClass('closeHover');
	}).mouseout(function() {
		$(this).removeClass('closeHover');
	}).click(function() {
		window.top.$('#gPopWin').remove();
		window.top.$('.maskDiv').remove();
	})
	// window.top.$('#gPopWin
	// .close').mouseover(function(){$(this).addClass('closeHover');}).mouseout(function(){$(this).removeClass('closeHover');});
	// window.top.$('#gPopWin
	// .close').click(function(){if(confirm("本功能为特殊情况下使用，您确定关闭窗口吗?")){window.top.$('#gPopWin').remove();window.top.$('.maskDiv').remove();}});
	window.top.$('#gPopWin').draggable({
		scroll : false
	});
	// {containment:'window'}
}

// 第三方外部功能函数
function gPopWinNoClose(url, width, height, title, path) {
	window.top.popWinIframePath = path;

	if (gPopWin.length < 4) {
		alert('必须有 url,width,height,title 四个参数');
		return false;
	}
	;

	wh = window.top.$('body').height();
	ww = window.top.$('body').width();
	x = ww / 2 - width / 2;
	y = wh / 2 - height / 2;
	window.top.$('body .maskDiv').remove();
	ohtml = '<div class="maskDiv" style="opacity:0.7;filter:alpha(opacity=70);z-index:102"></div><div id="gPopWin" style="padding:8px !important;padding:0;width:'
			+ width
			+ 'px;height:'
			+ height
			+ 'px;left:'
			+ x
			+ 'px;top:'
			+ y
			+ 'px;"><table style="margin:0 !important;margin:8px;"><tr class="title"><td><div class="t">'
			+ title
			+ '</div></td></tr><tr class="ct"><td><iframe src="'
			+ url
			+ '" width="100%" height="100%" frameborder="0"></iframe></td></tr></table><div class="maskIframe" style="position:absolute;height:100%;width:100%;background: blue;z-index:104;filter:alpha(opacity=0);opacity:0;top:40px;display:none;"></div></div>';
	// console.log(window.top.$('body').find(''))

	window.top.$('body').append(ohtml);
	// console.log(window.top.$('#gPopWin .close').html());
	// window.top.$('#gPopWin
	// .close').mouseover(function(){$(this).addClass('closeHover');}).mouseout(function(){$(this).removeClass('closeHover');}).click(function(){window.top.$('#gPopWin').remove();window.top.$('.maskDiv').remove();})
	window.top.$('#gPopWin').draggable({
		scroll : false
	});
	// {containment:'window'}
}

function check_form(obj) {
	formcheck = obj.parents('form');
	// ////////////////////////form validate start

	var ok = true;
	var info = '';
	var osubmit = formcheck;
	$('#submitErrorDiv').remove();
	formcheck.find(':input').removeClass('error');
	submitDiv = formcheck.find('.submitDiv');
	submitDiv.before('<div id="submitErrorDiv" style="display:none;"></div>')

	formcheck
			.find(':input')
			.each(
					function(i) {
						obj = $(this);
						if (obj.hasClass('required') && obj.val() == '') {// required
							info = '<strong>'
									+ obj.parents('td').prev().text().replace(
											'：', '').replace(':', '')
									+ '</strong>是必填项目';
							$('#submitErrorDiv').html(info).slideDown(300,
									'easeOutBounce');
							obj.addClass('error');
							ok = false;
							return false;
						} else if (obj.val() != ''
								&& obj.hasClass('email')
								&& !(obj.val()
										.match(/^\w+@[a-zA-Z_0-9]+\.[a-zA-Z]{2,3}$/))) {// email
							info = '<strong>'
									+ obj.parents('td').prev().text().replace(
											'：', '').replace(':', '')
									+ '</strong>必须有效';
							$('#submitErrorDiv').html(info).slideDown(300,
									'easeOutBounce');
							obj.addClass('error');
							ok = false;
							return false;
						} else if (obj.val() != '' && obj.hasClass('phone')
								&& !(obj.val().match(/^[0-9\-]+$/))) {// phone
							info = '<strong>'
									+ obj.parents('td').prev().text().replace(
											'：', '').replace(':', '')
									+ '</strong>必须有效';
							$('#submitErrorDiv').html(info).slideDown(300,
									'easeOutBounce');
							obj.addClass('error');
							ok = false;
							return false;
						} else if (obj.val() != ''
								&& obj.hasClass('date')
								&& !(obj.val()
										.match(/^[1-2]+[0-9]{3}-[0-1]+[0-9]+-[0-3]+[0-9]+$/))) {// date
							info = '<strong>'
									+ obj.parents('td').prev().text().replace(
											'：', '').replace(':', '')
									+ '</strong>必须是有效日期';
							$('#submitErrorDiv').html(info).slideDown(300,
									'easeOutBounce');
							obj.addClass('error');
							ok = false;
							return false;
						} else if (obj.val() != '' && obj.hasClass('int')
								&& !(obj.val().match(/^-?[0-9]+$/))) {// int
							info = '<strong>'
									+ obj.parents('td').prev().text().replace(
											'：', '').replace(':', '')
									+ '</strong>必须是整数';
							$('#submitErrorDiv').html(info).slideDown(300,
									'easeOutBounce');
							obj.addClass('error');
							ok = false;
							return false;
						} else if (obj.val() != '' && obj.hasClass('int')
								&& !(obj.val().match(/^-?[0-9\.]+$/))) {// number
							info = '<strong>'
									+ obj.parents('td').prev().text().replace(
											'：', '').replace(':', '')
									+ '</strong>必须是整数';
							$('#submitErrorDiv').html(info).slideDown(300,
									'easeOutBounce');
							obj.addClass('error');
							ok = false;
							return false;
						}

					})

	return ok;

	// ////////////////////////form validate end
}

// 判断后面时间大于前面时间
function comparetotime(begintime, endtime) {
	var btime = new Date(begintime.replace("-", "/")).getTime();
	var etime = new Date(endtime.replace("-", "/")).getTime();
	if (etime - btime >= 0) {
		return true;
	} else {
		return false;
	}
}

// 锁定表头和列
function FixTable(TableID, FixColumnNumber, width, height) {
	// / <param name="TableID" type="String">
	// / 要锁定的Table的ID
	// / </param>
	// / <param name="FixColumnNumber" type="Number">
	// / 要锁定列的个数
	// / </param>
	// / <param name="width" type="Number">
	// / 显示的宽度
	// / </param>
	// / <param name="height" type="Number">
	// / 显示的高度
	// / </param>
	if ($("#" + TableID + "_tableLayout").length != 0) {
		$("#" + TableID + "_tableLayout").before($("#" + TableID));
		$("#" + TableID + "_tableLayout").empty();
	} else {
		$("#" + TableID).after(
				"<div id='" + TableID
						+ "_tableLayout' style='overflow:hidden;height:"
						+ height + "px; width:" + width + "px;'></div>");
	}
	$(
			'<div id="' + TableID + '_tableFix"></div>' + '<div id="' + TableID
					+ '_tableHead"></div>' + '<div id="' + TableID
					+ '_tableColumn"></div>' + '<div id="' + TableID
					+ '_tableData"></div>').appendTo(
			"#" + TableID + "_tableLayout");
	var oldtable = $("#" + TableID);
	var tableFixClone = oldtable.clone(true);
	tableFixClone.attr("id", TableID + "_tableFixClone");
	$("#" + TableID + "_tableFix").append(tableFixClone);
	var tableHeadClone = oldtable.clone(true);
	tableHeadClone.attr("id", TableID + "_tableHeadClone");
	$("#" + TableID + "_tableHead").append(tableHeadClone);
	var tableColumnClone = oldtable.clone(true);
	tableColumnClone.attr("id", TableID + "_tableColumnClone");
	$("#" + TableID + "_tableColumn").append(tableColumnClone);
	$("#" + TableID + "_tableData").append(oldtable);
	$("#" + TableID + "_tableLayout table").each(function() {
		$(this).css("margin", "0");
	});
	var HeadHeight = $("#" + TableID + "_tableHead thead").height();
	HeadHeight += 2;
	$("#" + TableID + "_tableHead").css("height", HeadHeight);
	$("#" + TableID + "_tableFix").css("height", HeadHeight);
	var ColumnsWidth = 0;
	var ColumnsNumber = 0;
	$("#" + TableID + "_tableColumn tr:last td:lt(" + FixColumnNumber + ")")
			.each(function() {
				ColumnsWidth += $(this).outerWidth(true);
				ColumnsNumber++;
			});
	ColumnsWidth += 2;
	if ($.browser.msie) {
		switch ($.browser.version) {
		case "7.0":
			if (ColumnsNumber >= 3)
				ColumnsWidth--;
			break;
		case "8.0":
			if (ColumnsNumber >= 2)
				ColumnsWidth--;
			break;
		}
	}
	$("#" + TableID + "_tableColumn").css("width", ColumnsWidth);
	$("#" + TableID + "_tableFix").css("width", ColumnsWidth);
	$("#" + TableID + "_tableData").scroll(
			function() {
				$("#" + TableID + "_tableHead").scrollLeft(
						$("#" + TableID + "_tableData").scrollLeft());
				$("#" + TableID + "_tableColumn").scrollTop(
						$("#" + TableID + "_tableData").scrollTop());
			});
	$("#" + TableID + "_tableFix").css({
		"overflow" : "hidden",
		"position" : "relative",
		"z-index" : "50",
		"background-color" : "Silver"
	});
	$("#" + TableID + "_tableHead").css({
		"overflow" : "hidden",
		"width" : width - 17,
		"position" : "relative",
		"z-index" : "45",
		"background-color" : "Silver"
	});
	$("#" + TableID + "_tableColumn").css({
		"overflow" : "hidden",
		"height" : height - 17,
		"position" : "relative",
		"z-index" : "40",
		"background-color" : "Silver"
	});
	//console.log(width,121212121)
	
	$("#" + TableID + "_tableData").css({
		"overflow" : "scroll",
		"width" : width,
		"height" : height,
		"position" : "relative",
		"z-index" : "35"
	});
	
//console.log($("#" + TableID + "_tableHead").width());
//console.log($("#" + TableID + "_tableFix table").width())
	
	if ($("#" + TableID + "_tableHead").width() > $(
			"#" + TableID + "_tableFix table").width()) {
		$("#" + TableID + "_tableHead").css("width",
				$("#" + TableID + "_tableFix table").width());
		//console.log(width,$("#" + TableID + "_tableFix table").width() + 17)
		$("#" + TableID + "_tableData").css("width",
				$("#" + TableID + "_tableFix table").width() + 17);
	}
	
	if ($("#" + TableID + "_tableColumn").height() > $(
			"#" + TableID + "_tableColumn table").height()) {
		$("#" + TableID + "_tableColumn").css("height",
				$("#" + TableID + "_tableColumn table").height());
		$("#" + TableID + "_tableData").css("height",
				$("#" + TableID + "_tableColumn table").height() + 17);
	}
	$("#" + TableID + "_tableFix").offset(
			$("#" + TableID + "_tableLayout").offset());
	$("#" + TableID + "_tableHead").offset(
			$("#" + TableID + "_tableLayout").offset());
	$("#" + TableID + "_tableColumn").offset(
			$("#" + TableID + "_tableLayout").offset());
	$("#" + TableID + "_tableData").offset(
			$("#" + TableID + "_tableLayout").offset());
}