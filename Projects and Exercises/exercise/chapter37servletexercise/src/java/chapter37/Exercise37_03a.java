package chapter37;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Exercise37_03a extends HttpServlet {
  private static final String CONTENT_TYPE = "text/html";
  private int count = 0;

  /**Process the HTTP Get request*/
  public void doGet(HttpServletRequest request, HttpServletResponse
    response) throws ServletException, IOException {
    response.setContentType(CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head><title>Exercise37_03a</title></head>");
    out.println("<body>");
    count++;
    out.println("You are visitor number " + count + "<br>");
    out.println("Host name: " + request.getRemoteHost() + "<br>");
    out.println("IP address: " + request.getRemoteAddr() + "<br>");
//    out.println("Query string: " + request.getQueryString() + "<p>");
//    out.println("Character encoding: " + request.getCharacterEncoding() + "<p>");
//    out.println("Content type: " + request.getContentType() + "<p>");
//    out.println("Locale: " + request.getLocale() + "<p>");
//    out.println("Method: " + request.getMethod() + "<p>");
//    out.println("Path info: " + request.getPathInfo() + "<p>");
//    out.println("Request URL: " + request.getRequestURI() + "<p>");
    out.println("</body></html>");
  }
}
