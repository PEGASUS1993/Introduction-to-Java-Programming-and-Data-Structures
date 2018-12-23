import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class Exercise35_04 extends Application { 
  private ComboBox<String> cboTableName = new ComboBox<>();
  private TableView tableView = new TableView();
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
    pane.setCenter(tableView);
    pane.setTop(hBox);
    pane.setBottom(lblStatus);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 420, 180);
    primaryStage.setTitle("Exercise35_04"); // Set the stage title
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

      populateTableView(resultSet, tableView);
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  private void populateTableView(ResultSet rs, TableView tableView) {
    ObservableList<ObservableList> data = FXCollections.observableArrayList();
    try {
      /**
       * ********************************
       * TABLE COLUMN ADDED DYNAMICALLY * ********************************
       */
      for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
        //We are using non property style for making dynamic table
        final int j = i;
        TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));

//                col.setCellValueFactory(TextFieldTableCell.forTableColumn());
        col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
          public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
            if (param == null || param.getValue() == null || param.getValue().get(j) == null) {
              return null;
            }
            return new SimpleStringProperty(param.getValue().get(j).toString());
          }
        });

        tableView.getColumns().addAll(col);
        System.out.println("Column [" + i + "] ");
      }

      /**
       * ******************************
       * Data added to ObservableList * ******************************
       */
      while (rs.next()) {
        //Iterate Row
        ObservableList<String> row = FXCollections.observableArrayList();
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
          //Iterate Column
          row.add(rs.getString(i));
        }
        System.out.println("Row [1] added " + row);
        data.add(row);

      }

      //FINALLY ADDED TO TableView
      tableView.setItems(data);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error on Building Data");
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
