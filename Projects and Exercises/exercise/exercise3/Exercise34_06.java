import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Exercise34_06 extends Application { 
  private ComboBox<String> cboTableName = new ComboBox<>();
  private TextArea taResult = new TextArea();
  private Button btShowContents = new Button("Show Contents");
  private Label lblStatus = new Label();
  
  // Statement for executing queries
  private Statement stmt;
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {   
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(new Label("Table Name"), 
      cboTableName, btShowContents);
    hBox.setAlignment(Pos.CENTER);
    
    BorderPane pane = new BorderPane();
    pane.setCenter(new ScrollPane(taResult));
    pane.setTop(hBox);
    pane.setBottom(lblStatus);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 420, 80);
    primaryStage.setTitle("Exercise34_06"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage  
    
    initializeDB();
    
    btShowContents.setOnAction(e -> showContents());    
  }

  private void initializeDB() {
    try {
      // Load the JDBC driver
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver loaded");

      // Establish a connection
      Connection connection = DriverManager.getConnection
        ("jdbc:mysql://localhost/javabook", "scott", "tiger");
//    ("jdbc:oracle:thin:@liang.armstrong.edu:1521:ora9i",
//     "scott", "tiger");
      System.out.println("Database connected");

      // Create a statement
      stmt = connection.createStatement();
      
      DatabaseMetaData dbMetaData = connection.getMetaData();

      ResultSet rsTables = dbMetaData.getTables(null, null, null,
                                                new String[] {"TABLE"});
      System.out.print("User tables: ");
      while (rsTables.next()) {
        cboTableName.getItems().add(rsTables.getString("TABLE_NAME"));
      }

      cboTableName.getSelectionModel().selectFirst();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void showContents() {
    String tableName = cboTableName.getValue();

    try {
      String queryString = "select * from " + tableName;

      ResultSet resultSet = stmt.executeQuery(queryString);

      ResultSetMetaData rsMetaData = resultSet.getMetaData();
      for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
        taResult.appendText(rsMetaData.getColumnName(i) + "    ");
      }
      taResult.appendText("\n");

      // Iterate through the result and print the student names
      while (resultSet.next()) {
        for (int i = 1; i <= rsMetaData.getColumnCount(); i++)
          taResult.appendText(resultSet.getObject(i) + "     ");
        taResult.appendText("\n");
      }
    }
    catch (SQLException ex) {
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
