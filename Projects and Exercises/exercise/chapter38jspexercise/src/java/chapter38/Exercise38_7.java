package chapter38;

import java.sql.*;

public class Exercise38_7 {

  private static final String CONTENT_TYPE = "text/html";
  PreparedStatement pstmtCheckPassword;
  PreparedStatement pstmtExercise;
  PreparedStatement pstmtGetName;

  public Exercise38_7() {
    try {
      initializeJdbc();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**Initialize database connection*/
  private void initializeJdbc() {
    try {
      // Explicitly load a MySQL driver
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver loaded");

      // Establish a connection
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
      System.out.println("Database connected");

      // Create prepared statements
      pstmtCheckPassword = conn.prepareStatement("select count(*) from Account where username = ? "
              + "and password = ?");
      pstmtExercise = conn.prepareStatement("update Account set password = ? where username = ?");
      pstmtGetName = conn.prepareStatement("select name from Account where username = ?");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public boolean checkPassword(String username, String password) {
    try {
      pstmtCheckPassword.setString(1, username);
      pstmtCheckPassword.setString(2, password);
      ResultSet resultSet = pstmtCheckPassword.executeQuery();

      resultSet.next();
      if (resultSet.getInt(1) < 1) {
        return false;
      } else {
        return true;
      }
    } catch (Exception ex) {
      ex.printStackTrace();
      return false;
    }
  }

  public void updatePassword(String username, String newPassword) {
    try {
      pstmtExercise.setString(1, newPassword);
      pstmtExercise.setString(2, username);
      pstmtExercise.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  public String getName(String username) {
    try {
      pstmtGetName.setString(1, username);
      ResultSet resultSet = pstmtGetName.executeQuery();

      resultSet.next();
      String name = resultSet.getString(1);
      return name;
    } catch (SQLException ex) {
      ex.printStackTrace();
      return null;
    }
  }
}