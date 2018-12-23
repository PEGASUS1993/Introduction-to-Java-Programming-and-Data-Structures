/*
 * Exercise37_13.java
 *
 * Created on April 26, 2007, 2:24 PM
 */

package chapter37;

import java.lang.Comparable;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;

public class Exercise37_14 extends HttpServlet {
  private PreparedStatement pstmtIncreaseYes, pstmtIncreaseNo;
  private Connection conn;
  private Statement statement;
  
  /** Initialize variables */
  public void init() throws ServletException {
    initializeJdbc();
  }
  
  /** Initialize database connection */
  private void initializeJdbc() {
    try {
      // Explicitly load a MySQL driver
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver loaded");
      
      // Establish a connection
      conn = DriverManager.getConnection
        ("jdbc:mysql://localhost/javabook" , "scott", "tiger");
      
      // Create a Statement
      pstmtIncreaseYes = conn.prepareStatement(
        "update Poll set yesCount = yesCount + 1");
      pstmtIncreaseNo = conn.prepareStatement(
        "update Poll set noCount = noCount + 1 ");
      
      statement = conn.createStatement();   
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    
    String yesOrNo = request.getParameter("yesorno");
    try {
      if (yesOrNo.equals("Yes"))
        pstmtIncreaseYes.executeUpdate();
      // statement.executeUpdate("update Poll set yesCount = yesCount + 1");
      else
        pstmtIncreaseNo.executeUpdate();
      //statement.executeUpdate("update Poll set noCount = noCount + 1");
      
      ResultSet result = statement.executeQuery("select yesCount from Poll");
      result.next();
      out.println("The current Yes count is " + result.getInt(1) + "<br>");
      
      result = statement.executeQuery("select noCount from Poll");
      result.next();
      out.println("The current No count is " + result.getInt(1));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
