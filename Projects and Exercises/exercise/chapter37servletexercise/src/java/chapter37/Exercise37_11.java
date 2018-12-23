package chapter37;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Exercise37_11 extends HttpServlet {
  private static final String CONTENT_TYPE = "text/html";
  /**Initialize global variables*/
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }
  /**Process the HTTP Get request*/
  public void doGet(HttpServletRequest request, HttpServletResponse
    response) throws ServletException, IOException {
    response.setContentType(CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head><title>Exercise37_11</title></head>");
    out.println("<body>");

    out.println("Here are the cookies from your browser<p>");
    Cookie[] cookies = request.getCookies();
    for (int i = 0; i < cookies.length; i++)
      out.println(cookies[i].getName() + "'s value is " +
        cookies[i].getValue() + "<br>");
    out.println("</body></html>");
  }

  /**Clean up resources*/
  public void destroy() {
  }
}
