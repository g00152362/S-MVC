/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-08-20 18:22:57 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class devicegroup_002ddetail_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
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
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("   <!-- Content Header (Page header) -->\r\n");
      out.write("   <section class=\"content-header\">\r\n");
      out.write("     <h3>\r\n");
      out.write("        <i class=\"glyphicon glyphicon-random\"></i>   Group Profile\r\n");
      out.write("     </h3>\r\n");
      out.write("   </section>\r\n");
      out.write("   <!-- display group information -->\r\n");
      out.write("      <!-- min boxes (Stat box) -->\r\n");
      out.write("\t<div class=\"container \"  style=\"width:100%;\">      \r\n");
      out.write("      <div class=\"row\">\r\n");
      out.write("        <div class=\"box\">\r\n");
      out.write(" \t\t\t<div class=\"box-body box-profile\">\r\n");
      out.write("\t\t\t\t<table id= \"table_stat\" class=\"table table-bordered \" data-align=\"center\">\r\n");
      out.write("\t\t     \t  <tr class=\"gray \">\r\n");
      out.write("\t\t\t\t    <td><span class=\"font_big font_green \" id=\"onlinenumber\">&nbsp;</span>   Online</td> \r\n");
      out.write("\t\t\t\t     <td ><span class=\"font_big font_red \" id=\"offlinenumber\">&nbsp;</span>   Offline</td> \r\n");
      out.write("\t\t\t\t     <td ><span class=\"font_big font_gray \"id=\"unregeisterednumber\">&nbsp;</span>   Unregistered</td>\r\n");
      out.write("\t\t\t\t    <td ><span class=\"font_blue \"id=\"description\">&nbsp;</span> </td>\t\t\t\t     \r\n");
      out.write("\t\t\t\t  </tr>\r\n");
      out.write("\t\t      </table>\r\n");
      out.write("\t\t  </div>\r\n");
      out.write("\t\t  </div>\r\n");
      out.write("      </div>\r\n");
      out.write("    <!-- -------table list----- -->\r\n");
      out.write(" \t\t<div class=\"row\">\r\n");
      out.write("   \t\t<div class=\"box box-primary\">\r\n");
      out.write("   \t\t<div class=\"box-body no-padding\"> \r\n");
      out.write("  \t\r\n");
      out.write("    \t<!-- list table -->\r\n");
      out.write("\t    <table id=\"gatewayListBygroupName\"\r\n");
      out.write("\t           data-toolbar=\"#toolbar\"\r\n");
      out.write("\t           data-search=\"true\"\r\n");
      out.write("\t           data-minimum-count-columns=\"2\"\r\n");
      out.write("\t           data-show-pagination-switch=\"true\"\r\n");
      out.write("\t           data-pagination=\"true\"\r\n");
      out.write("\t           data-id-field=\"serialNumber\"\r\n");
      out.write("\t           data-page-list=\"[10, 25, 50, 100, ALL]\"\r\n");
      out.write("\t           data-show-footer=\"false\"\r\n");
      out.write("\t           data-side-pagination=\"server\"\r\n");
      out.write("\t           data-url=\"/gateway/listBygroupName?name=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"\t           \r\n");
      out.write("\t           data-toolbar-align=\"left\"\r\n");
      out.write("\t           data-response-handler=\"responseHandler\">\r\n");
      out.write("\r\n");
      out.write("\t    \t</table>    \t\r\n");
      out.write("   \t  </div>\r\n");
      out.write("   \t  </div>\r\n");
      out.write("   \t  <!-- ./box -->\r\n");
      out.write("   \t  </div>\r\n");
      out.write(" </div>  \r\n");
      out.write("      \r\n");
      out.write("      \r\n");
      out.write("         \r\n");
      out.write("<script>\r\n");
      out.write("\r\n");
      out.write("function initGroupInfo(){\r\n");
      out.write("\t$.getJSON(\"deviceGroup/listName?name=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\",\r\n");
      out.write("\t\t\tfunction(data){\r\n");
      out.write("\t\t\t\tif(data.status != 200){\r\n");
      out.write("\t\t\t\t\talert(\"get data fail!\");\r\n");
      out.write("\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t       \t\t$('#onlinenumber').text(data.data.onlinenumber);\r\n");
      out.write("\t       \t\t$('#offlinenumber').text(data.data.offlinenumber);\r\n");
      out.write("\t       \t\t$('#unregeisterednumber').text(data.data.unregeisterednumber);\r\n");
      out.write("\t       \t\t$('#description').text(data.data.description);\r\n");
      out.write("\t\t}); \t\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function initTable() {\r\n");
      out.write("\t$('#gatewayListBygroupName').bootstrapTable({\r\n");
      out.write("\t     dataField: \"rows\",\r\n");
      out.write("     height: getHeight(),\r\n");
      out.write("\r\n");
      out.write(" columns: [\r\n");
      out.write("     {\r\n");
      out.write("         title: \"Name\",//标题\r\n");
      out.write("         field: \"deviceName\",//键名\r\n");
      out.write("         width: 180,\r\n");
      out.write("         valign: 'middle',             \r\n");
      out.write("//     editable: true\r\n");
      out.write("     },\r\n");
      out.write("     {\r\n");
      out.write("         field: \"serialNumber\",\r\n");
      out.write("         title: \"Series Number\",\r\n");
      out.write("         width: 180,\r\n");
      out.write("         formatter:'deviceEsnFormat',\r\n");
      out.write("         sortable: true,\r\n");
      out.write("         valign: 'middle',\r\n");
      out.write("         titleTooltip: \"设备序列号\"\r\n");
      out.write("     },\r\n");
      out.write("     {\r\n");
      out.write("         field: \"status\",\r\n");
      out.write("         title: \"Status\",\r\n");
      out.write("         width: 150,\r\n");
      out.write("         sortable: true,\r\n");
      out.write("         valign: 'middle',             \r\n");
      out.write("         formatter:'formatStatus'\r\n");
      out.write("       },         \r\n");
      out.write("     {\r\n");
      out.write("         field: \"groupName\",\r\n");
      out.write("         title: \"Group\",\r\n");
      out.write("         width: 180,\r\n");
      out.write("         valign: 'middle',             \r\n");
      out.write("         formatter:'groupFormat'               \r\n");
      out.write("      },\r\n");
      out.write("      {\r\n");
      out.write("          field: \"type\",\r\n");
      out.write("          title: \"Model\",\r\n");
      out.write("          valign: 'middle',              \r\n");
      out.write("          sortable: true,\r\n");
      out.write("//          editable: true,              \r\n");
      out.write("           width: 100\r\n");
      out.write("       },     \r\n");
      out.write("       {\r\n");
      out.write("           field: \"position\",\r\n");
      out.write("           title: \"Location\",\r\n");
      out.write("           valign: 'middle',               \r\n");
      out.write("//           editable: true,                   \r\n");
      out.write("              width:300\r\n");
      out.write("        },                    \r\n");
      out.write("        {\r\n");
      out.write("            field: \"softwareVersion\",\r\n");
      out.write("            valign: 'middle',                \r\n");
      out.write("            title: \"Software\",\r\n");
      out.write("            width: 60\r\n");
      out.write("         }      \r\n");
      out.write(" ]\r\n");
      out.write("});\r\n");
      out.write("\t\r\n");
      out.write("//\t$('#gatewayListBygroupName').bootstrapTable('load', \"/gateway/listBygroupName?name=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\");\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("$(function () {\r\n");
      out.write("\tinitGroupInfo();\r\n");
      out.write("\tinitTable();\r\n");
      out.write("\t//load device table\r\n");
      out.write("\r\n");
      out.write(" });\r\n");
      out.write("</script>\r\n");
      out.write("</body>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
