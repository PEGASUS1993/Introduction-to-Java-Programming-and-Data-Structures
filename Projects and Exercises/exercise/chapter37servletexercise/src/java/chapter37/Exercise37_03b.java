package chapter37;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Exercise37_03b extends HttpServlet {
  private static final String CONTENT_TYPE = "text/html";
  private long count = 0;
  private RandomAccessFile inout = null;

  public void init() throws ServletException {
    System.out.println("init called");
    try {
      inout = new RandomAccessFile("Exercise37_03.dat", "rw");
      if (inout.length() == 0)
        count = 0;
      else
        count = inout.readLong();
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /**Process the HTTP Get request*/
  public void doGet(HttpServletRequest request, HttpServletResponse
    response) throws ServletException, IOException {
    response.setContentType(CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head><title>Exercise24_3b</title></head>");
    out.println("<body>");
    count++;
    out.println("You are visitor number " + count + "<br>");
    out.println("Host name: " + request.getRemoteHost() + "<br>");
    out.println("IP address: " + request.getRemoteAddr() + "<br>");
    out.println("</body></html>");
  }

  /**Clean up resources*/
  public void destroy() {
    System.out.println("destroy is invoked!");
    try {
      inout.seek(0);
      inout.writeLong(count);
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
    finally {
      try {
        inout.close();
      }
      catch (Exception ex) {
      }
    }
  }
}
