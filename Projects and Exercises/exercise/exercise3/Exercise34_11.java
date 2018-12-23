import java.sql.Connection;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Exercise34_11 {
  public static void main(String[] args) throws Exception {
    Class.forName("com.mysql.jdbc.Driver");
    System.out.println("Driver loaded");

    Connection connection = DriverManager.getConnection(
        "jdbc:mysql://localhost/javabook", "scott", "tiger");

    PreparedStatement insertStatement = connection
        .prepareStatement("insert into Babyname(year, name, gender, count) values(?, ?, ?, ?)");

    int boyNum, girlNum;
    String boyName, girlName;
    Scanner data;

    for (int year = 2001; year <= 2010; year++) {
      // Set year for following entries into database
      insertStatement.setInt(1, year);

      data = new Scanner(new URL(
          "http://www.cs.armstrong.edu/liang/data/babynamesranking" + year + ".txt").openStream());

      while (data.hasNext()) {
        data.next(); // Skip rank. It is not saved in the table.
        boyName = data.next();
        boyNum = data.nextInt();
        girlName = data.next();
        girlNum = data.nextInt();

        // Set insert statement to current boy info and add to batch
        insertStatement.setString(2, boyName);
        insertStatement.setString(3, "M");
        insertStatement.setInt(4, boyNum);
        insertStatement.execute();

        // Set insert statement to current girl info and add to batch
        insertStatement.setString(2, girlName);
        insertStatement.setString(3, "F");
        insertStatement.setInt(4, girlNum);
        insertStatement.execute();
      } 
    } 
  }
} 
