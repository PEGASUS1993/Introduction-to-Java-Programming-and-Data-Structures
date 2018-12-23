<HTML>
<HEAD>
<TITLE>
Exercise38_3.jsp
</TITLE>
</HEAD>
<BODY>
<%	    String lastName = request.getParameter("lastName");
	    String firstName = request.getParameter("firstName");
	    String mi = request.getParameter("mi");
	    String gender = request.getParameter("gender");
	    String major = request.getParameter("major");
	    String[] minors = request.getParameterValues("minor");
	    String tennis = request.getParameter("tennis");
	    String golf = request.getParameter("golf");
	    String pingPong = request.getParameter("pingPong");
	    String remarks = request.getParameter("remarks");

	    out.println("Last Name: <b>" + lastName + "</b> First Name: <b>"
	      + firstName + "</b> MI: <b>" + mi + "</b><br>");
	    out.println("Gender: <b>" + gender + "</b><br>");
	    out.println("Major: <b>" + major + "</b> Minor: <b>");

	    if (minors != null)
	      for (int i = 0; i < minors.length; i++)
	        out.println(minors[i] + " ");

	    out.println("</b><br> Tennis: <b>" + tennis + "</b> Golf: <b>" +
	      golf + "</b> PingPong: <b>" + pingPong + "</b><br>");
	    out.println("Remarks: <b>" + remarks + "</b>");
%>
</BODY>
</HTML>
