<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   
<%@ page import = "chapter38.Exercise38_11" %>
<jsp:useBean id = "formatSource" class = "chapter38.Exercise38_11"
   scope = "request"></jsp:useBean>
<html>
      <head>
         <title>Syntax-Highlighted Code</title>
         <meta http-equiv = "Content-Type" content = "text/html" charset = windows-1252>
         <style type = "text/css">
            body {font-family: "Courier New", sans-serif; font-size: 100%; color: black}
            .keyword {color: #000080; font-weight: bold}
            .comment {color: #008000}
            .literal {color: #0000ff}
         </style>
      </head>
      
      <body>
         <pre>
              <%
              String javaSourceText = request.getParameter("program");
              javaSourceText = javaSourceText.replaceAll(">", "&gt;");
              javaSourceText = javaSourceText.replaceAll("<", "&lt;");
              String formattedCode = formatSource.translateToHTML(javaSourceText);
              out.print(formattedCode);
              %>
        </pre>
    </body>
</html>
