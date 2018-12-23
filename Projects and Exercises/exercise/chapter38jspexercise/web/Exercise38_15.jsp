<jsp:useBean id = "additionTutor" scope = "session" 
  class = "chapter38.AdditionTutor">
</jsp:useBean>

<html>
  <head>
    <title>
      Subtraction Quiz
    </title>
  </head>
  <body>
    <form method = "post" action = "Exercise38_15DisplayResult.jsp">
      <% additionTutor.refresh(); %>
      <table>
        <% for (int i = 0; i < 10; i++) { %>
        <tr>
          <td>
            <%= Math.max(additionTutor.number1[i], additionTutor.number2[i]) %> 
          </td>
          <td>
            - 
          </td>
          <td><%= Math.min(additionTutor.number1[i], additionTutor.number2[i]) %> 
          </td>
          <td>
            = 
          </td>
          <td>
            <input name = "<%= "result" + i%>" size = 2/>
          </td>
        </tr>  
        <% } %>
      </table> 
            <br />
      <input type = "submit" name = "Submit" 
      value = "Submit" /> Click the browser's Refresh button to get a new quiz
    </form>
  </body>
</html>