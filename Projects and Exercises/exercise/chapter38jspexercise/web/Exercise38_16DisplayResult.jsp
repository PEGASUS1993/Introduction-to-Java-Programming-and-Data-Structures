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
 <% int date = 0;
    for (int i = 0; i < 5; i++) {
      if (request.getParameter("isInSet" + i) != null) {
        date += set.dates[i][0][0];
      }
    }
 %>

 Your birth date is <%= date %>
  </body>
</html>