<!-- GuessNumber.jsp -->
<%@ page import = "chapter38.GuessNumberBean" %>
<jsp:useBean id = "guessNumberBean" 
  class = "chapter38.GuessNumberBean" scope = "session" >
</jsp:useBean>
<jsp:setProperty name = "guessNumberBean" property = "*" />
<html>
  <head>
    <title>
      GuessNumber
    </title>
  </head>
  <body>
  <h3>Guess a number from 1 to 1000</h3>
  <form method = "post" action = "Exercise38_20.jsp">
    Guess a number: <input name = "guess" value =
      <% if (guessNumberBean.isGuessValid()) { %>
        <%= guessNumberBean.getGuess() %>
      <% } else %>
        ""
       /><br /><br />
    <input type = "submit" name = "Submit" value = "Guess" />
    <input type = "reset" value = "Reset" /><br /><br />
    <%= guessNumberBean.getResponse() %>
  </form>
  </body>
</html>

