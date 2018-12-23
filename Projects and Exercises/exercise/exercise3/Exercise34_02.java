import java.sql.*;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Pos;

public class Exercise34_02 extends Application {
  private String[] dataName;
  private double[] data;
 
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    initializeDB();

    ChartModel chartModel = new ChartModel();
    chartModel.setChartData(dataName, data);

    PieChart pieChart = new PieChart();
    BarChart barChart = new BarChart();
    
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(pieChart, barChart);
    hBox.setAlignment(Pos.CENTER);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(hBox, 640, 250);
    primaryStage.setTitle("Exercise34_02"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    pieChart.setModel(chartModel);
    barChart.setModel(chartModel);
  }
  
  private void initializeDB() {
    try {
      Connection conn = DriverManager.getConnection
//        ("dbc:odbc:exampleMDBDataSource", "", "" );
        ("jdbc:mysql://localhost/javabook", "scott", "tiger");
      System.out.println("Database connected\n");
      
      // Connect to the sample database
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(
        "select deptId, count(*) from Student where deptId is not null group by deptId;");

      // Count rows
      int count = 0;
      while (rs.next()) {
        count++;
      }

      dataName = new String[count];
      data = new double[count];

      // We have to obtain the result set again
      rs = stmt.executeQuery(
        "select deptId, count(*) from Student where deptId is not null group by deptId;");
      int i = 0;
      while (rs.next()) {
        dataName[i] = rs.getString(1);
        data[i] = rs.getInt(2);
        i++;
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
