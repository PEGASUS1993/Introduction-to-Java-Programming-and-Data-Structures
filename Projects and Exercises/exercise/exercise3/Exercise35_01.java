import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Exercise35_01 extends Application { 
  private DBConnectionPane dBConnectionPane 
    = new DBConnectionPane();
  private Label lblConnectionStatus 
    = new Label("No connection now");
  private TextArea taDisplay = new TextArea();
        
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {      
    Button btBatchUpdate = new Button("Batch Update");
    Button btNonBatchUpdate = new Button("Non-Batch Update");
    HBox hBoxButtons = new HBox(10);
    hBoxButtons.getChildren().addAll(btBatchUpdate, 
      btNonBatchUpdate);
    hBoxButtons.setAlignment(Pos.CENTER);

    BorderPane borderPaneConnect = new BorderPane(); 
    Button btConnectDB = new Button("Connect to Database");
    borderPaneConnect.setCenter(lblConnectionStatus);
    borderPaneConnect.setRight(btConnectDB);
       
    BorderPane pane = new BorderPane();
    pane.setTop(borderPaneConnect);
    pane.setBottom(hBoxButtons);    
    pane.setCenter(taDisplay);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 320, 350);
    primaryStage.setTitle("Exercise35_01"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage   
    
    btConnectDB.setOnAction(e -> openDialog());
    btBatchUpdate.setOnAction(e -> {
      if (dBConnectionPane.getConnection() == null) {
        taDisplay.setText("Please connect to the database first");
        return;
      }
      new Thread(() ->  batchUpdate()).start();
    });
    btNonBatchUpdate.setOnAction(e -> {
      if (dBConnectionPane.getConnection() == null) {
        taDisplay.setText("Please connect to the database first");
        return;
      }
      new Thread(() ->  nonBatchUpdate()).start();
    });
  }
  
  private void openDialog() {
    BorderPane pane = new BorderPane();
    pane.setCenter(dBConnectionPane);
    Button btCloseDialog = new Button("Close Dialog");
    pane.setBottom(btCloseDialog);
    BorderPane.setAlignment(btCloseDialog, Pos.CENTER);
    
    Stage stage = new Stage();
    stage.setScene(new Scene(pane, 420, 180)); // Place the scene in the stage
    stage.setTitle("Connect to DB");
    stage.show(); // Display the stage   
    
    btCloseDialog.setOnAction(e -> {
      stage.hide();
      if (dBConnectionPane.getConnection() != null)
        lblConnectionStatus.setText("DB connected");
    });
  }

  private void nonBatchUpdate() {
    try {
      // Create the table
      Statement statement = dBConnectionPane.getConnection().createStatement();

      statement.executeUpdate("drop table Temp");
      statement.executeUpdate(
        "CREATE TABLE TEMP(NUM1 double, NUM2 double, NUM3 double)");

      long startTime = System.currentTimeMillis();
      for (int i = 1; i <= 5000; i++) {
        statement.executeUpdate("INSERT INTO TEMP VALUES(" +
          Math.random() * 1000 + ", " + Math.random() * 1000 + ", " +
          Math.random() + ")");
      }
      long endTime = System.currentTimeMillis();

      Platform.runLater(() -> {
        taDisplay.appendText("Non-batch update completed\n");
        taDisplay.appendText("The elapsed time is " +
          (endTime - startTime) + "\n");
        lblConnectionStatus.setText("Non-batch update succeeded");
      });
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void batchUpdate() {
    if (dBConnectionPane.getConnection() != null) {
      try {
        // Create the table
        Statement statement = dBConnectionPane.getConnection().createStatement();

        statement.executeUpdate("drop table Temp");
        statement.executeUpdate(
          "CREATE TABLE TEMP(NUM1 double, NUM2 double, NUM3 double)");

        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= 5000; i++) {
          statement.addBatch("INSERT INTO TEMP VALUES(" +
            Math.random()*1000 + ", " + Math.random()*1000 + ", " +
            Math.random() + ")");
        }

        statement.executeBatch();

        long endTime = System.currentTimeMillis();

        Platform.runLater(() -> {
          taDisplay.appendText("Batch update completed\n");
          taDisplay.appendText("The elapsed time is " +
            (endTime - startTime) + "\n");
          lblConnectionStatus.setText("Batch update succeeded");
        });
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
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
      cboDriver.setPrefWidth(350);
      cboDriver.getSelectionModel().selectFirst();
      gridPane.add(cboURL, 1, 1);
      cboURL.getSelectionModel().selectFirst();
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
}