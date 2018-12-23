import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise35_02 extends Application {
  private Button btFirst = new Button("First");
  private Button btNext = new Button("Next");
  private Button btPrior = new Button("Prior");
  private Button btLast = new Button("Last");
  private Button btInsert = new Button("Insert");
  private Button btDelete = new Button("Delete");
  private Button btUpdate = new Button("Update");
  private TextField tfLastName = new TextField();
  private TextField tfFirstName = new TextField();
  private TextField tfMi = new TextField();
  private TextField tfStreet = new TextField();
  private TextField tfCity = new TextField();
  private TextField tfState = new TextField();
  private TextField tfZip = new TextField();
  private TextField tfTelephone = new TextField();
  private TextField tfEmail = new TextField();
  private Label lblStatus = new Label();
  
  // Result set
  private ResultSet resultSet;

  // Current row number
  private int currentRowNumber;

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    VBox vBox = new VBox(5);
    
    HBox hBox2 = new HBox(5);
    hBox2.getChildren().addAll(new Label("Last Name"), tfLastName,
      new Label("First Name"), tfFirstName, new Label("MI"), tfMi);
    tfLastName.setPrefColumnCount(8);
    tfFirstName.setPrefColumnCount(8);
    tfMi.setPrefColumnCount(1);

    HBox hBox3 = new HBox(5);
    hBox3.getChildren().addAll(new Label("Street"), tfStreet);
    HBox hBox4 = new HBox(5);
    hBox4.getChildren().addAll(new Label("City"), tfCity,
      new Label("State"), tfState, new Label("ZIP"), tfZip);   
    tfState.setPrefColumnCount(2);
    tfZip.setPrefColumnCount(5);
    
    HBox hBox5 = new HBox(5);
    hBox5.getChildren().addAll(new Label("Telephone"), tfTelephone);
 
    HBox hBox1 = new HBox(5);
    hBox1.getChildren().addAll(new Label("Email"), tfEmail);
    vBox.getChildren().addAll(hBox2, hBox3, hBox4, hBox5, hBox1);
    
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(btFirst, btNext, btPrior, btLast, 
      btInsert, btDelete, btUpdate);
    hBox.setAlignment(Pos.CENTER);
    
    BorderPane pane = new BorderPane();
    pane.setTop(hBox);
    pane.setCenter(vBox);
    pane.setBottom(lblStatus);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 450, 200);
    primaryStage.setTitle("Exercise35_02"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    initializeDB();
    
    btFirst.setOnAction(e -> first());
    btNext.setOnAction(e -> next());
    btPrior.setOnAction(e -> previous());
    btLast.setOnAction(e -> last());
    btInsert.setOnAction(e -> insert());
    btUpdate.setOnAction(e -> updateRecord());
    btDelete.setOnAction(e -> clear());    
  }
   
  private void first() {
    try {
      if (resultSet.first())
        showRecord();
      else
        lblStatus.setText("There is no row in the result set");
    }
    catch (Exception ex) {
      lblStatus.setText(ex.toString());
    }
  }
    
  private void next() {
    try {
      if (resultSet.isLast())
        lblStatus.setText("This is already the last row");
      else {
        resultSet.next();
        showRecord();
      }
    }
    catch (Exception ex) {
      lblStatus.setText(ex.toString());
    }
  }

  private void previous() {
    try {
      if (resultSet.isFirst())
        lblStatus.setText("This is already the first row");
      else {
        resultSet.previous();
        showRecord();
      }
    }
    catch (Exception ex) {
      lblStatus.setText(ex.toString());
    }
  }

  private void last() {
    try {
      if (resultSet.isLast())
        lblStatus.setText("This is already the last row");
      else {
        resultSet.last();
        showRecord();
      }
    }
    catch (Exception ex) {
      lblStatus.setText(ex.toString());
    }
  }
    
  /**Initialize the database connection, create statement, and result set */
  private void initializeDB() {
    try {
      // Load the driver
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver loaded");
      
      // Connect to the local InterBase database
      Connection connection = DriverManager.getConnection
//        ("dbc:odbc:exampleMDBDataSource", "", "" );
        ("jdbc:mysql://localhost/javabook", "scott", "tiger");
      System.out.println("Database connected");

      // Create a statement
      Statement statement = connection.createStatement
        (ResultSet.TYPE_SCROLL_SENSITIVE,
         ResultSet.CONCUR_UPDATABLE);

      // Get result set
      resultSet = statement.executeQuery("select * from Address");

      // Show the first record in the result set
      resultSet.first();
      showRecord();
    }
    catch (Exception ex) {
      lblStatus.setText(ex.toString());
    }
  }

  /**Load the record into text fields*/
  private void loadToTextField(ResultSet rs) throws SQLException {
    if (rs.next()) {
      tfLastName.setText(rs.getString(2));
      tfFirstName.setText(rs.getString(3));
      tfMi.setText(rs.getString(4));
      tfStreet.setText(rs.getString(5));
      tfCity.setText(rs.getString(6));
      tfState.setText(rs.getString(7));
      tfTelephone.setText(rs.getString(8));
      lblStatus.setText("Record found");
    }
    else
      lblStatus.setText("Record not found");
  }

  /**Delete text fields*/
  private void clear() {
    tfEmail.setText(null);
    tfLastName.setText(null);
    tfFirstName.setText(null);
    tfMi.setText(null);
    tfStreet.setText(null);
    tfCity.setText(null);
    tfState.setText(null);
    tfTelephone.setText(null);
  }

  private void showRecord() throws Exception {
    tfFirstName.setText(resultSet.getString("firstName"));
    tfLastName.setText(resultSet.getString("lastName"));
    tfMi.setText(resultSet.getString("mi"));
    tfStreet.setText(resultSet.getString("Street"));
    tfCity.setText(resultSet.getString("City"));
    tfState.setText(resultSet.getString("State"));
    tfTelephone.setText(resultSet.getString("Telephone"));
    tfZip.setText(resultSet.getString("zip"));
    tfEmail.setText(resultSet.getString("email"));

    currentRowNumber = resultSet.getRow();
    lblStatus.setText("Current row number: " + currentRowNumber);
  }
  
  /**Insert a new record to the database*/
  private void insert() {
    try {
      // Update the fields
      updateRecord();

      // Insert the row
      resultSet.insertRow();

      // Move the cursor back to the position before the insertion
      resultSet.moveToCurrentRow();
      
      lblStatus.setText("Inserstion succeeded");
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  /**Update fields in the record*/
  protected void updateRecord() {
    try {
      // Gather data from the UI and update the database fields
      resultSet.updateString("firstName",
        tfFirstName.getText().trim());
      resultSet.updateString("MI", tfMi.getText().trim());
      resultSet.updateString("lastName", tfLastName.getText().trim());
      resultSet.updateString("Street", tfStreet.getText().trim());
      resultSet.updateString("City", tfCity.getText().trim());
      resultSet.updateString("zip", tfZip.getText().trim());
      resultSet.updateString("Telephone", tfTelephone.getText().trim());
      resultSet.updateString("email", tfEmail.getText().trim());
      
      lblStatus.setText("Update succeeded");
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
  
  private void delete() {
    try {
      resultSet.deleteRow();
      lblStatus.setText("Deletion succeeded");
    }
    catch (Exception ex) {
      lblStatus.setText(ex.toString());
    }
  }
  
  /**
   * The main method is only needed for the EmailE with limited
   * avaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
