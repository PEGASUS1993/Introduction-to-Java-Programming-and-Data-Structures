<!-- Exercise38_10.jsp -->
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<HTML>
<HEAD>
<TITLE>
Exercise38_10.jsp
</TITLE>
</HEAD>
<BODY>
<%@ page import = "java.io.*" %>
<%
    String country = request.getParameter("country");

    out.println("<img src = \"http://cs.armstrong.edu/liang/image/" + country + ".gif" 
      + "\" align=left>");
    

    // Read description from a file and send it to the browser
    java.util.Scanner in = new java.util.Scanner(
            new File("c:\\book\\" + country + ".txt"));

    // Text line from the text file for flag description
    String line;

    // Read a line from the text file and send it to the browser
    while (in.hasNext()) {
      out.println(in.nextLine());
    }
%>
</BODY>
</HTML>