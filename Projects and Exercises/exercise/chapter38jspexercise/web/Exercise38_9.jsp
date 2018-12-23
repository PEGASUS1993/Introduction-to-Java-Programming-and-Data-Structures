<HTML>
<HEAD>
<TITLE>
Exercise38_9.jsp
</TITLE>
</HEAD>
<BODY>
Here are the cookies from your browser
<%
    Cookie[] cookies = request.getCookies();
    for (int i = 0; i < cookies.length; i++)
      out.println(cookies[i].getName() + "'s value is " +
        cookies[i].getValue() + "<br>");
%>
</BODY>
</HTML>
