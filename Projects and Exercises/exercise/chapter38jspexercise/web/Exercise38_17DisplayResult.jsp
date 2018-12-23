<jsp:useBean id = "capital" scope = "application" 
  class = "chapter38.StateCapital">
</jsp:useBean>

<html>
  <head>
    <title>
      State Capitals
    </title>
  </head>
  <body>
    <form method = "post" action = "Exercise38_17.jsp">
      
      <% if (capital.nextCapital().equals(request.getParameter("capital"))) { %>
      Yes. The capital of <%= capital.nextState() %> is <%= capital.nextCapital() %>.
      <% } else { %>
      No. The capital of <%= capital.nextState() %> is <%= capital.nextCapital() %>.
      <% } %>

      <% capital.moveToNext(); %>

      </br>
      <input type = "submit" name = "Submit" 
      value = "Next" /> 
    </form>
  </body>
</html>