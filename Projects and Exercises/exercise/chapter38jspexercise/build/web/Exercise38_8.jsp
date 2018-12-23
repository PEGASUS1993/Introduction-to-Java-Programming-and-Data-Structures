<!-- Exercise38_8.jsp -->
<HTML>
<HEAD>
<TITLE>
Exercise38_8.jsp
</TITLE>
</HEAD>
<BODY>
<%
    Cookie cookieColor = new Cookie("color", "red");
    cookieColor.setMaxAge(2*24*60*60);
    response.addCookie(cookieColor);
    Cookie cookieRadius = new Cookie("radius", "5.5");
    cookieRadius.setMaxAge(2*24*60*60);
    response.addCookie(cookieRadius);
    Cookie cookieCount = new Cookie("count", "2");
    cookieRadius.setMaxAge(0);
    response.addCookie(cookieCount);
%>
The cookies have been added to your browser.
</BODY>
</HTML>

