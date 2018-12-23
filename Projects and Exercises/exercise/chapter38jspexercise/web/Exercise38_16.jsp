<jsp:useBean id = "set" scope = "session"
  class = "chapter38.NumberSet">
</jsp:useBean>

<html>
  <head>
    <title>
      Guess Birth Date
    </title>
  </head>
  <body>
    <form method = "post" action = "Exercise38_16DisplayResult.jsp">            
      <table>
        <caption>Check the boxes if your birthday is in these sets</caption>
        <tr>
          <% for (int k = 0; k < 5; k++) { %>
          <td>
            <table style = "border: thin solid red 0.2em; margin-right: 0.5em">
              <% for (int i = 0; i < 4; i++) { %>
              <tr>
                <% for (int j = 0; j < 4; j++) { %>        
                <td>
                  <%= set.dates[k][i][j] %>
                </td>
                <% } %> 
              </tr>
              <% } %> 
            </table> 
          </td>
          <% } %> 
        </tr>
     
        <tr>
          <% for (int i = 0; i < 5; i++) { %>
          <td>
            <input type = "checkbox" name = "<%= "isInSet" + i %>" /> 
          </td>
          <% } %>
        </tr>
      </table>
      <input type = "submit" name = "Submit" 
      value = "Find Date" /> 
    </form>
  </body>
</html>