package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE HTML>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"utf-8\" />\r\n");
      out.write("<title>任务管理系统</title>\r\n");
      out.write("<link rel=\"shortcut icon\" href=\"");
      out.print(request.getContextPath());
      out.write("/themes/default/images/favicon.ico\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/themes/default/jquery-1.7.2.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/themes/default/gex.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/themes/default/main.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/themes/default/md5.js\"></script> \r\n");
      out.write("<style type=\"text/css\">@import url(");
      out.print(request.getContextPath());
      out.write("/themes/default/main.css);</style>\r\n");
      out.write("<style type=\"text/css\">@import url(");
      out.print(request.getContextPath());
      out.write("/themes/default/index.css);</style>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("if(window.top!=window.self)\r\n");
      out.write("{\r\n");
      out.write("\twindow.top.location=location;\t\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("jQuery(function($){\r\n");
      out.write("///////////////////\r\n");
      out.write("\r\n");
      out.write("function innitialLogin(){\r\n");
      out.write("\t$('#loginDiv').css({left:($('html').width()-$('#loginDiv').width())/2+'px',top:($('html').height()-$('#loginDiv').height())/2+'px'})\r\n");
      out.write("}\r\n");
      out.write("innitialLogin();\r\n");
      out.write("\r\n");
      out.write("$(window).resize(function(){\r\n");
      out.write("\tinnitialLogin();\t\r\n");
      out.write("})\r\n");
      out.write("\r\n");
      out.write("//drop down animation\r\n");
      out.write("$('#loginDiv').css({top:'-'+$('#loginDiv').height()+'px',opacity:0})\r\n");
      out.write("$('#loginDiv').animate({top:($('html').height()-$('#loginDiv').height())/2+'px',opacity:1},300,'easeInOutBack')\r\n");
      out.write("\r\n");
      out.write("$('#loginDiv').draggable();\r\n");
      out.write("$('.skinMask').addClass('skin'+(parseInt(Math.random()*9)+1));\r\n");
      out.write("$('input').focusClass('focus')\r\n");
      out.write("\r\n");
      out.write("if($.cookie('usrname')){\r\n");
      out.write("\t$('#ousr').val($.cookie('usrname'))\t;\r\n");
      out.write("\t$('#opws').trigger('focus');\r\n");
      out.write("}else{\r\n");
      out.write("\t$('#ousr').trigger('focus');\t\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("$('form').submit(function(){\r\n");
      out.write("\t\r\n");
      out.write("\t$.cookie('usrname',$('#ousr').val(),{expires:30})\r\n");
      out.write("})\r\n");
      out.write("\r\n");
      out.write("$('#osubmit').click(function(){\r\n");
      out.write("\tif($('#opws').val()==''){\r\n");
      out.write("\t\t$('.error').show();\t\r\n");
      out.write("\t\t//$('#opws').tigger('focus');\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\tvar pass =  hex_md5($('#opws').val()); \r\n");
      out.write("\t$('#opws').val(pass);\r\n");
      out.write("\r\n");
      out.write("\t$('form').submit();\t\r\n");
      out.write("})\r\n");
      out.write("\r\n");
      out.write("/*ie6 enter key submit*/\r\n");
      out.write("$('#opws').keypress(function(e){\r\n");
      out.write("\tif(e.keyCode==13){\r\n");
      out.write("\t\t$('#osubmit').trigger('click');\r\n");
      out.write("\t}\r\n");
      out.write("})\r\n");
      out.write("\r\n");
      out.write("//临时代码\r\n");
      out.write("$('.error').hide();\t\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("//preload skins\r\n");
      out.write("function preload(arrayOfImages) {\r\n");
      out.write("    $(arrayOfImages).each(function(){\r\n");
      out.write("        $('<img/>')[0].src = this;\r\n");
      out.write("    });\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/*\r\n");
      out.write("preload([\r\n");
      out.write("    '");
      out.print(request.getContextPath());
      out.write("/themes/default/images/skin1.jpg',\r\n");
      out.write("   \t'");
      out.print(request.getContextPath());
      out.write("/themes/default/images/skin2.jpg',\r\n");
      out.write("    '");
      out.print(request.getContextPath());
      out.write("/themes/default/images/skin3.jpg',\r\n");
      out.write("\t'");
      out.print(request.getContextPath());
      out.write("/themes/default/images/skin4.jpg',\r\n");
      out.write("\t'");
      out.print(request.getContextPath());
      out.write("/themes/default/images/skin5.jpg',\r\n");
      out.write("\t'");
      out.print(request.getContextPath());
      out.write("/themes/default/images/skin6.jpg',\r\n");
      out.write("\t'");
      out.print(request.getContextPath());
      out.write("/themes/default/images/skin7.jpg',\r\n");
      out.write("\t'");
      out.print(request.getContextPath());
      out.write("/themes/default/images/skin8.jpg',\r\n");
      out.write("\t'");
      out.print(request.getContextPath());
      out.write("/themes/default/images/skin9.jpg',\r\n");
      out.write("\t'");
      out.print(request.getContextPath());
      out.write("/themes/default/images/skin10.jpg',\r\n");
      out.write("\t'");
      out.print(request.getContextPath());
      out.write("/themes/default/images/skin11.jpg',\r\n");
      out.write("\t'");
      out.print(request.getContextPath());
      out.write("/themes/default/images/skin12.jpg',\r\n");
      out.write("\t'");
      out.print(request.getContextPath());
      out.write("/themes/default/images/skin13.jpg'\r\n");
      out.write("]);\r\n");
      out.write("*/\r\n");
      out.write("\r\n");
      out.write("///////////////////\r\n");
      out.write("})\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div id=\"loginDiv\">\r\n");
      out.write("    <form action=\"");
      out.print(request.getContextPath());
      out.write("/login.action\" method=\"post\">\r\n");
      out.write("    <div class=\"formDiv\">\r\n");
      out.write("    <p><label for=\"ousr\">用户名：<input name=\"usr\" id=\"ousr\" /></label></p>\r\n");
      out.write("    <p><label for=\"opws\">密　码：<input name=\"pws\"  type=\"password\" id=\"opws\"/></label><input type=\"submit\" id=\"osubmit\" value=\"\" /> <strong class=\"error\">登陆失败！</strong></p>\r\n");
      out.write("    </div></form>\r\n");
      out.write("    <div id=\"copyright\">\r\n");
      out.write("    \r\n");
      out.write("<div class=\"copyright1\" >\r\n");
      out.write("\t<span style=\"color:#666;\">相关下载：</span>\r\n");
      out.write("\t<a href=\"");
      out.print(request.getContextPath());
      out.write("/file/install_flash_player_11_active_x.exe\">Flash插件（IE）</a> &nbsp;  \r\n");
      out.write("\t<a href=\"");
      out.print(request.getContextPath());
      out.write("/file/install_flash_player_11_plugin.exe\">Flash插件（其它）</a> &nbsp;\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<p>2014 <sup>&copy;</sup> 版权所有</p>    \r\n");
      out.write("    \r\n");
      out.write("    \r\n");
      out.write("    \r\n");
      out.write("    \r\n");
      out.write("    </div>    \r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
