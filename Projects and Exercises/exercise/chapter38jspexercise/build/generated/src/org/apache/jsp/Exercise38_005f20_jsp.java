package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import chapter38.GuessNumberBean;

public final class Exercise38_005f20_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<!-- GuessNumber.jsp -->\n");
      out.write("\n");
      chapter38.GuessNumberBean guessNumberBean = null;
      synchronized (session) {
        guessNumberBean = (chapter38.GuessNumberBean) _jspx_page_context.getAttribute("guessNumberBean", PageContext.SESSION_SCOPE);
        if (guessNumberBean == null){
          guessNumberBean = new chapter38.GuessNumberBean();
          _jspx_page_context.setAttribute("guessNumberBean", guessNumberBean, PageContext.SESSION_SCOPE);
          out.write('\n');
        }
      }
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.introspect(_jspx_page_context.findAttribute("guessNumberBean"), request);
      out.write("\n");
      out.write("<html>\n");
      out.write("  <head>\n");
      out.write("    <title>\n");
      out.write("      GuessNumber\n");
      out.write("    </title>\n");
      out.write("  </head>\n");
      out.write("  <body>\n");
      out.write("  <h3>Guess a number from 1 to 1000</h3>\n");
      out.write("  <form method = \"post\" action = \"Exercise38_20.jsp\">\n");
      out.write("    Guess a number: <input name = \"guess\" value =\n");
      out.write("      ");
 if (guessNumberBean.isGuessValid()) { 
      out.write("\n");
      out.write("        ");
      out.print( guessNumberBean.getGuess() );
      out.write("\n");
      out.write("      ");
 } else 
      out.write("\n");
      out.write("        \"\"\n");
      out.write("       /><br /><br />\n");
      out.write("    <input type = \"submit\" name = \"Submit\" value = \"Guess\" />\n");
      out.write("    <input type = \"reset\" value = \"Reset\" /><br /><br />\n");
      out.write("    ");
      out.print( guessNumberBean.getResponse() );
      out.write("\n");
      out.write("  </form>\n");
      out.write("  </body>\n");
      out.write("</html>\n");
      out.write("\n");
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
