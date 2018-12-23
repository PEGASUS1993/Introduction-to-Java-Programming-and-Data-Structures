import java.sql.*;

public class Exercise34_09 {
  public static void main(String[] args) {
    try {
      // Load the JDBC driver
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver loaded");

      String connectionString = "jdbc:mysql://localhost/javabook";
      Connection conn = DriverManager.getConnection(connectionString, 
        "scott", "tiger");

      Statement stmt = conn.createStatement();
      ResultSet resultSet = stmt.executeQuery("select * from Student1;");
      
      PreparedStatement stmt2 = 
        conn.prepareStatement("insert into Student2 values (?, ?, ?, ?);");
     
      while (resultSet.next()) {
        stmt2.setString(1, resultSet.getString(1));
        stmt2.setString(2, resultSet.getString(2));
        String fullname = resultSet.getString(3);
        
        // Reverse the name in order to extract the last name correctly.
        String temp = new StringBuffer(fullname.trim()).reverse().toString();
        
        String[] tokens = temp.split("[ .+]", 2);
        stmt2.setString(4, new StringBuffer(tokens[0].trim()).reverse().toString());
        stmt2.setString(3, new StringBuffer(tokens[1].trim()).reverse().toString());       
        
        stmt2.executeUpdate();
      }
      
      conn.close();
      
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
