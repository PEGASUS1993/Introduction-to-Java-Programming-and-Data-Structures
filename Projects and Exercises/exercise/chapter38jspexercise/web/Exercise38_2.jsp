<HTML>
<HEAD>
<TITLE>
Exercise38_2.jsp
</TITLE>
</HEAD>
<BODY>

  <table border="1">
    <tr><th colspan="10">Multiplication Table</th></tr>
    <tr><td width="50" height="17"></td>
    <% for (int j = 1; j <= 9; j++) { %>
      <td width="50" height="17">  <%= j %> </td>
    <% } %>
    </tr>

    <% for (int i = 1; i <= 9; i++) { %>
      <tr>
        <td width="50" height="17"> <%= i %> </td>
      <%for (int j = 1; j <= 9; j++) {%>
      <%-- Display the product and align properly --%>
        <td width="50" height="17"> <%= i * j %> </td>
     <% } %>
     <tr>
   <% } %>
  </table>
</BODY>
</HTML>
