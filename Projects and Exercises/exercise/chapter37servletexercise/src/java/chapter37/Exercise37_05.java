package chapter37;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Exercise37_05 extends HttpServlet {
  /** Process the HTTP Get request */
  public void doGet(HttpServletRequest request, HttpServletResponse
    response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    // Obtain parameters from the client
    double loanAmount =
      Double.parseDouble(request.getParameter("loanAmount"));
    double annualInterestRate = Double.parseDouble
      (request.getParameter("annualInterestRate"));
    int numOfYears = Integer.parseInt(request.getParameter("numOfYears"));

    Loan loan = new Loan(annualInterestRate, numOfYears, loanAmount);

    out.println("Loan Amount: " + loanAmount + "<br>");
    out.println("Annual Interest Rate: " + annualInterestRate + "<br>");
    out.println("Number of Years: " + numOfYears + "<br>");
    out.println("<font color=\"#FF0000\">");
    out.println("Monthly Payment: " + loan.monthlyPayment() + "<br>");
    out.println("Total Payment: " + loan.totalPayment() + "<br>");
    out.println("</font>");
  }
}