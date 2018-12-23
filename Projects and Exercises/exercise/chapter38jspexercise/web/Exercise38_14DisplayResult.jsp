<jsp:useBean id = "additionTutor" scope = "session" 
  class = "chapter38.AdditionTutor">
</jsp:useBean>

<html>
  <head>
    <title>
      Addition Quiz Answer
    </title>
  </head>
  <body>
  <table>
    <% int correctCount = 0;
        
          for (int i = 0; i < 10; i++) { 
        String s = request.getParameter("result" + i);
        
        int result;
        try {
          result = Integer.parseInt(s); 
        }
        catch (Exception ex) {
          result = 0;
        } %>
        <tr>
         <td><%= additionTutor.number1[i] %> </td> <td>+</td> <td><%= additionTutor.number2[i] %></td> <td> =</td> 
          <td> <%= result %> </td>

          <td>
         <% if (result == additionTutor.number1[i] + additionTutor.number2[i]) {
              correctCount++; %>
            Correct 
         <% } else {%>
            Wrong
         <% } %></td>
         </tr>
    <% } %>
    </table>
    The total correct count is <%= correctCount %>
  </body>
</html>