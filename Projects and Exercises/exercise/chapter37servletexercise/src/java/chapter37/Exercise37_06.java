package chapter37;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Exercise37_06 extends HttpServlet {
  private static final String CONTENT_TYPE = "text/html";

  /**Process the HTTP Get request*/
  public void doGet(HttpServletRequest request, HttpServletResponse
    response) throws ServletException, IOException {
    response.setContentType(CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head><title>Exercise37_06</title></head>");
    out.println("<body>");

    String course = request.getParameter("course");
    String ssn = request.getParameter("ssn");

    BufferedReader in = null;

    boolean found = false;
    try {
      in = new BufferedReader(new FileReader(
        "c:\\exercise\\" + course + ".txt"));

      String line;
      while ( (line = in.readLine()) != null) {
        StringTokenizer st = new StringTokenizer(line, "#\n\r\t");

        String name = st.nextToken();
        String ssnFromFile = st.nextToken();
        String score = st.nextToken();

        System.out.println(name + " " + ssnFromFile + " " + score);
        if (ssn.equals(ssnFromFile)) {
          out.print(name + " " + score);
          found = true;
          break;
        }
      }

      if (!found) {
        out.print(ssn + " not found in " + course);
      }
      out.println("</body></html>");
    }
    catch (Exception ex) {
      out.println(ex.toString());
      ex.printStackTrace();
    }
    finally {
      try {
        if (in != null) in.close();
        if (out != null) out.close();
      }
      catch (Exception ex) { }
    }
  }

  /**Clean up resources*/
  public void destroy()
  {
  }
}
