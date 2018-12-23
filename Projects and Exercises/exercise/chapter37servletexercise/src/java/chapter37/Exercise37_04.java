package chapter37;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Exercise37_04 extends HttpServlet {
  /** Process the HTTP Get request */
  public void doGet(HttpServletRequest request, HttpServletResponse
    response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    request.getSession();
    // Obtain parameters from the client
    double taxableIncome =
      Double.parseDouble(request.getParameter("taxableIncome"));
    int filingStatus = Integer.parseInt(request.getParameter("filingStatus"));

    out.println("Taxable Income: " + taxableIncome + "<br>");
    out.println("Filing Status: " + filingStatus + "<br>");
    out.println("<font color=\"#FF0000\">");
    out.println("Tax: " + ComputeTax.computeTax(filingStatus, taxableIncome));
    out.println("</font>");

    out.close();
  }
}
