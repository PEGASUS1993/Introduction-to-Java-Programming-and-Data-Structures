package chapter37;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Exercise37_01 extends HttpServlet {
  private static final String CONTENT_TYPE = "text/html";
  /**Process the HTTP Get request*/
  public void doGet(HttpServletRequest request, HttpServletResponse
    response) throws ServletException, IOException {
    response.setContentType(CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head><title>Exercise37_01</title></head>");
    out.println("<body>");
    out.println("<table border=\"1\">");
    out.println("<tr>");
    out.println("<td>Number</td>");
    out.println("<td>Factorial</td>");
    out.println("</tr>");
    for (int i = 0; i <= 10; i++) {
      out.println("<tr>");
      out.println("<td>" + i + "</td>");
      out.println("<td>" + computeFactorial(i) + "</td>");
      out.println("</tr>");
    }

    out.println("</body></html>");
  }

  private long computeFactorial(int n) {
    if (n == 0)
      return 1;
    else
      return n*computeFactorial(n - 1);
  }
}