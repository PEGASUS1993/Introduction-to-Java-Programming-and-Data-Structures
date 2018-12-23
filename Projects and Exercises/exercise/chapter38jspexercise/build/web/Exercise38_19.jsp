<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id = "backend"
             class = "chapter38.Exercise38_19" scope = "session" >
</jsp:useBean>

<jsp:setProperty name = "backend" property = "*" />

<%! String message = "";%>

<%
            if (request.getParameter("submit") != null) {
                if (request.getParameter("submit").toString().equalsIgnoreCase("view")) {
                    backend.view(request);
                } else if (request.getParameter("submit").toString().equalsIgnoreCase("insert")) {
                    message = backend.insert(request) + " rows affected.";
                } else if (request.getParameter("submit").toString().equalsIgnoreCase("update")) {
                    message = backend.update(request) + " rows affected.";
                } else if (request.getParameter("submit").toString().equalsIgnoreCase("reset")) {
                    backend.reset();
                }
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exercise38_19</title>
    </head>
    <body>
        <table>
            <tr>
                <td>
                    <fieldset>
                        <legend>Staff Information</legend>
                        <form action='Exercise38_19.jsp' method='post'>
                            <p>
                                <label >ID: </label><input name="id" value = <jsp:getProperty name = "backend" property = "id" /> ><br /><br />
                                <label >First Name: </label><input name="firstname" value = <jsp:getProperty name = "backend" property = "firstname" /> >
                                <label for='mi'>Middle Initial: </label><input type='text' size = "1" name='mi' value = <jsp:getProperty name = "backend" property = "mi" /> >
                                <label for='lastname'>Last Name: </label><input type='text' name='lastname' value = <jsp:getProperty name = "backend" property = "lastname" /> ><br /><br />
                                <label for='address'>Address: </label><input type='text' name='address' value = <jsp:getProperty name = "backend" property = "address" /> ><br /><br />
                                <label for='city'>City: </label><input type='text' name='city' value = <jsp:getProperty name = "backend" property = "city" /> >
                                <label for='state'>State: </label><input type='text' name='state' value = <jsp:getProperty name = "backend" property = "state" /> ><br /><br />
                                <label for='telephone'>Telephone: </label><input type='text' name='telephone' value = <jsp:getProperty name = "backend" property = "telephone" /> >
                                <label for='email'>Email: </label><input type='text' name='email' value = <jsp:getProperty name = "backend" property = "email" /> ><br />

                            </fieldset>
                            </td>
                            </tr>
                            <tr align="center">
                                <td>
                                    <button name='submit' value='view' type='submit'>View</button>
                                    <button name='submit' value='insert' type='submit'>Insert</button>
                                    <button name='submit' value='update' type='submit'>Update</button>
                                    <button name='submit' value='reset' type='submit'>Reset</button>
                                </td>
                            </tr>
                            <tr align="center">
                                <td>
                                    <%= message%>
                                </td>
                            </tr>
                        </form>
            </tr>
        </table>
    </body>
</html>

