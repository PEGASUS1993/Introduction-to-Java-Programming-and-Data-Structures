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
    <form method = "post" action = "Exercise38_17DisplayResult.jsp">
      
What is the capital of <%= capital.nextState() %>? 
<input name = "capital" size = 20/>
      <input type = "submit" name = "Submit" 
      value = "Submit" /> 
    </form>
  </body>
</html>