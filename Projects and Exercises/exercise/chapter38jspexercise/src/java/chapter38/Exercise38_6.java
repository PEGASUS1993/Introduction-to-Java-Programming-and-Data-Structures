package chapter38;

import java.sql.*;

public class Exercise38_6 {

  private PreparedStatement pstmtGetResult;
  private Connection conn;

  public Exercise38_6() {
    initializeJdbc();
  }

  /**Initialize database connection*/
  private void initializeJdbc() {
    try {
      // Explicitly load a MySQL driver
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver loaded");

      // Establish a connection
      conn = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
      System.out.println("Database connected");

      pstmtGetResult = conn.prepareStatement("select name, score from csci4990 where ssn = ?");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public String[] getNameScore(String ssn, String table) {
    try {
      Statement statement = conn.createStatement();
      ResultSet rs = statement.executeQuery("select name, score from "
              + table + " where ssn = '" + ssn + "'");

      if (rs == null) {
        return null;
      } else {
        while (rs.next()) {
          String[] result = new String[2];
          result[0] = rs.getString(1);
          result[1] = Double.toString(rs.getDouble(2));
          return result;
        }
        return null;
      }
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }
}
