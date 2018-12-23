<HTML>
<HEAD>
<TITLE>
Factorial JSP
</TITLE>
</HEAD>
<BODY>

  <table border="1">
    <tr>
      <td>Number</td>
      <td>Factorial</td>
    </tr>
      <%  for (int i = 0; i <= 10; i++) { %>
      <tr>
      <td><%= i %></td>
      <td><%= computeFactorial(i) %></td>
      </tr>
      <%  } %>
  </table>

<%! private long computeFactorial(int n) {
      if (n == 0)
        return 1;
      else
        return n * computeFactorial(n - 1);
    }
%>
</BODY>
</HTML>
