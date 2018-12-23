import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class Exercise34_03 extends Application { 
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new DBConnectionPane(), 420, 80);
    primaryStage.setTitle("DB Connection"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage   
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}

class DBConnectionPane extends BorderPane {
  private Connection connection;
  private Label lblConnectionStatus = new Label("No connection");

  private Button btConnect = new Button("Connect to DB");
  private ComboBox<String> cboDriver = new ComboBox<>(
    FXCollections.observableArrayList(
      "com.mysql.jdbc.Driver", "sun.jdbc.odbc.JdbcOdbcDriver",
      "oracle.jdbc.driver.OracleDriver"));
  private ComboBox<String> cboURL = new ComboBox<>(
    FXCollections.observableArrayList(
      "jdbc:mysql://localhost/javabook",
      "jdbc:odbc:exampleMDBDataSource",
      "jdbc:oracle:thin:@liang.armstrong.edu:1521:ora9i"));

  private TextField tfUsername = new TextField();
  private PasswordField pfPassword = new PasswordField();

  /** Creates new form DBConnectionPanel */
  public DBConnectionPane() {
    cboDriver.setEditable(true);
    cboURL.setEditable(true);

    GridPane gridPane = new GridPane();
    gridPane.add(new Label("JDBC Drive"), 0, 0);
    gridPane.add(new Label("Database URL"), 0, 1);
    gridPane.add(new Label("Username"), 0, 2);
    gridPane.add(new Label("Password"), 0, 3);
    gridPane.add(cboDriver, 1, 0);
    gridPane.add(cboURL, 1, 1);
    gridPane.add(tfUsername, 1, 2);
    gridPane.add(pfPassword, 1, 3);
    gridPane.add(btConnect, 1, 4);
    GridPane.setHalignment(btConnect, HPos.RIGHT);
    
    this.setTop(lblConnectionStatus);
    this.setCenter(gridPane);

    btConnect.setOnAction(e -> connectDB());
  }

  private void connectDB() {
    // Get database information from the user input
    String driver = cboDriver.getValue();
    String url = cboURL.getValue();
    String username = tfUsername.getText().trim();
    String password = new String(pfPassword.getText());

    // Connection to the database
    try {
      Class.forName(driver);
      connection = DriverManager.getConnection(
        url, username, password);
      lblConnectionStatus.setText("Connected to " + url);
    }
    catch (java.lang.Exception ex) {
      ex.printStackTrace();
    }
  }

  /** Return connection */
  public Connection getConnection() {
    return connection;
  }
}
