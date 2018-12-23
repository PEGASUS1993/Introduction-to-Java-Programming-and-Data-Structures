<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ page import = "chapter38.Exercise38_7" %>
<jsp:useBean id = "db" class = "chapter38.Exercise38_7"
    scope = "request"></jsp:useBean>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

    <h1>JSP Page</h1>
    <%
    // Obtain username and password
    String username = request.getParameter("username").trim();
    String oldPassword = request.getParameter("oldPassword").trim();
    String newPassword = request.getParameter("newPassword").trim();
    String confirmNewPassword = request.getParameter("confirmNewPassword").trim();


      if (!db.checkPassword(username, oldPassword)) {
        out.println("Your old password is incorrect. Please try again<p>");
        out.println("<form method=\"post\" action=\"Exercise38_7.jsp\">");
        out.println("<p>Username <input type=\"text\" name=\"username\">&nbsp;" +
"<p>Old Password <input type=\"password\" name=\"oldPassword\">&nbsp;" +
"<p>New Password <input type=\"password\" name=\"newPassword\">&nbsp;" +
"<p>Confirm New Password <input type=\"password\" name=\"confirmNewPassword\">&nbsp;" +
"<p><input type=\"submit\" name=\"Submit\" value=\"Submit\">" +
"   <input type=\"reset\" value=\"Reset\"></p>" +
"</form>");
        return;
      }

      if (newPassword.equals(confirmNewPassword)) {
        db.updatePassword(username, newPassword);

        out.println("Hello, " + db.getName(username) + ", your password has been updated!");
      }
      else {
        out.println("Your new password is not confirmed correctly. Please try again<p>");
        out.println("<form method=\"post\" action=\"Exercise38_7.jsp\">");
        out.println("<p>Username <input type=\"text\" name=\"username\">&nbsp;" +
"<p>Old Password <input type=\"password\" name=\"oldPassword\">&nbsp;" +
"<p>New Password <input type=\"password\" name=\"newPassword\">&nbsp;" +
"<p>Confirm New Password <input type=\"password\" name=\"confirmNewPassword\">&nbsp;" +
"<p><input type=\"submit\" name=\"Submit\" value=\"Submit\">" +
"   <input type=\"reset\" value=\"Reset\"></p>" +
"</form>");
        return;
      }
      %>
    </body>
</html>
