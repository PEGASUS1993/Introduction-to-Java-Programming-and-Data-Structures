import java.util.*;
import java.sql.*;

public class Exercise35_06 {
  private Statement stmt;

  public static void main(String[] args) {
    new Exercise35_06();
  }

  /** Initialize global variables */
  public Exercise35_06() {
    try {
      initializeJdbc();

      populateData();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void populateData() throws Exception {
    java.net.URL url = new java.net.URL(
        "http://cs.armstrong.edu/liang/data/Salary.txt");
    Scanner input = new Scanner(url.openStream());

    while (input.hasNext()) {
      String line = input.nextLine();
      String[] items = line.split(" ");

      String queryString = "insert into Salary values(" + "'" + items[0]
        + "', " + "'" + items[1] + "', " + "'" + items[2] + "', " + items[3] + ");";
      
      stmt.addBatch(queryString);
    }

    stmt.executeBatch();
    
    // Close the file
    input.close();
  }

  /** Initialize database connection */
  private void initializeJdbc() {
    try {
      // Load the driver
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver loaded");
      
      // Declare driver and connection string
      // String connectionString = "jdbc:odbc:exampleMDBDataSource";
      // For MySQL
      String connectionString = "jdbc:mysql://localhost/javabook";
      // For Oracle
      // String connectionString = "jdbc:oracle:" +
      // "thin:scott/tiger@liang.armstrong.edu:1521:orcl";
      // Connect to the sample database
      Connection conn = DriverManager.getConnection(connectionString, "scott",
          "tiger");

      // Create a statement to insert questions
      stmt = conn.createStatement();
      stmt.executeUpdate("delete from Salary;");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
