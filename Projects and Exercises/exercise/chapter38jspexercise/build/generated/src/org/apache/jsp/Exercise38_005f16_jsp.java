package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Exercise38_005f16_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      chapter38.NumberSet set = null;
      synchronized (session) {
        set = (chapter38.NumberSet) _jspx_page_context.getAttribute("set", PageContext.SESSION_SCOPE);
        if (set == null){
          set = new chapter38.NumberSet();
          _jspx_page_context.setAttribute("set", set, PageContext.SESSION_SCOPE);
          out.write('\n');
        }
      }
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("  <head>\n");
      out.write("    <title>\n");
      out.write("      Guess Birth Date\n");
      out.write("    </title>\n");
      out.write("  </head>\n");
      out.write("  <body>\n");
      out.write("    <form method = \"post\" action = \"Exercise38_16DisplayResult.jsp\">            \n");
      out.write("      <table>\n");
      out.write("        <caption>Check the boxes if your birthday is in these sets</caption>\n");
      out.write("        <tr>\n");
      out.write("          ");
 for (int k = 0; k < 5; k++) { 
      out.write("\n");
      out.write("          <td>\n");
      out.write("            <table style = \"border: thin solid red 0.2em; margin-right: 0.5em\">\n");
      out.write("              ");
 for (int i = 0; i < 4; i++) { 
      out.write("\n");
      out.write("              <tr>\n");
      out.write("                ");
 for (int j = 0; j < 4; j++) { 
      out.write("        \n");
      out.write("                <td>\n");
      out.write("                  ");
      out.print( set.dates[k][i][j] );
      out.write("\n");
      out.write("                </td>\n");
      out.write("                ");
 } 
      out.write(" \n");
      out.write("              </tr>\n");
      out.write("              ");
 } 
      out.write(" \n");
      out.write("            </table> \n");
      out.write("          </td>\n");
      out.write("          ");
 } 
      out.write(" \n");
      out.write("        </tr>\n");
      out.write("     \n");
      out.write("        <tr>\n");
      out.write("          ");
 for (int i = 0; i < 5; i++) { 
      out.write("\n");
      out.write("          <td>\n");
      out.write("            <input type = \"checkbox\" name = \"");
      out.print( "isInSet" + i );
      out.write("\" /> \n");
      out.write("          </td>\n");
      out.write("          ");
 } 
      out.write("\n");
      out.write("        </tr>\n");
      out.write("      </table>\n");
      out.write("      <input type = \"submit\" name = \"Submit\" \n");
      out.write("      value = \"Find Date\" /> \n");
      out.write("    </form>\n");
      out.write("  </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
