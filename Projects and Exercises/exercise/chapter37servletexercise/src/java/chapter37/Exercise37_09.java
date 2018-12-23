package chapter37;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Exercise37_09 extends HttpServlet {
  private static final String CONTENT_TYPE = "text/html";

  /**Process the HTTP Post request*/
  public void doPost(HttpServletRequest request,
    HttpServletResponse response)
    throws ServletException, IOException {
    // Write HTML back to a browser
    response.setContentType(CONTENT_TYPE);

    // Obtain a print stream for sending HMTL to the browser
    PrintWriter out = response.getWriter();

    // Obtain username and password
    String url = request.getParameter("url").trim();
    String username = request.getParameter("username").trim();
    String password = request.getParameter("password").trim();
    String table = request.getParameter("table").trim();

    try {
      // Connect to the sample database
      Connection connection = DriverManager.getConnection(url, username, password);

      // Create a statement
     Statement stmt = connection.createStatement();

     // Select the columns from the Student table
     ResultSet rset = stmt.executeQuery("select * from " + table);

     // Display result
     out.println("<table border=\"1\">");
     int numOfColumns = rset.getMetaData().getColumnCount();

     // Display column name
     out.println("<tr>");
     for (int i = 1; i <= numOfColumns; i++)
       out.println("<td width=\"50\" height=\"17\">" +
         rset.getMetaData().getColumnName(i) + "</td>");
     out.println("</tr>");

     while (rset.next()) {
       out.println("<tr>");
       for (int i = 1; i <= numOfColumns; i++) {
         out.println("<td width=\"50\" height=\"17\">" +
          rset.getString(i) + "</td>");
       }
       out.println("</tr>");
     }

     // close the connection
     connection.close();
     out.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
