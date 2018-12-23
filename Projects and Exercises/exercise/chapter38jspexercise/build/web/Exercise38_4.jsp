<HTML>
<HEAD>
<TITLE>
Exercise38_4.jsp
</TITLE>
</HEAD>
<BODY>
<%@ page import = "chapter38.ComputeTax" %>
<%
  // Obtain parameters from the client
  double taxableIncome =
    Double.parseDouble(request.getParameter("taxableIncome"));
  int filingStatus = Integer.parseInt(request.getParameter("filingStatus"));

  out.println("Taxable Income: " + taxableIncome + "<br>");
  out.println("Filing Status: " + filingStatus + "<br>");
  out.println("<font color=\"#FF0000\">");
  out.println("Tax: " + ComputeTax.computeTax(filingStatus, taxableIncome));
  out.println("</font>");
%>
</BODY>
</HTML>
