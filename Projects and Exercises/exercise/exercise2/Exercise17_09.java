import java.io.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise17_09 extends Application {
  // Specify the size of five string fields in the record
  final static int NAME_SIZE = 32;
  final static int STREET_SIZE = 32;
  final static int CITY_SIZE = 20;
  final static int STATE_SIZE = 2;
  final static int ZIP_SIZE = 5;
  final static int RECORD_SIZE =
    (NAME_SIZE + STREET_SIZE + CITY_SIZE + STATE_SIZE + ZIP_SIZE);

  // Access address.dat using RandomAccessFile
  private RandomAccessFile raf;

  // Text fields
  private TextField tfName = new TextField();
  private TextField tfStreet = new TextField();
  private TextField tfCity = new TextField();
  private TextField tfState = new TextField();
  private TextField tfZip = new TextField();

  // Buttons
  private Button btAdd = new Button("Add");
  private Button btFirst = new Button("First");
  private Button btNext = new Button("Next");
  private Button btPrevious = new Button("Previous");
  private Button btLast = new Button("Last");
  private Button btUpdate = new Button("Update");

  public Exercise17_09() {
    // Open or create a random access file
    try {
      raf = new RandomAccessFile("address.dat", "rw");
    }
    catch(IOException ex) {
      ex.printStackTrace();
      System.exit(1);
    }
  }
  
  @Override
  public void start(Stage primaryStage) {
    tfState.setPrefColumnCount(2);
    tfZip.setPrefColumnCount(4);
    tfCity.setPrefColumnCount(12);
    
    // Pane p1 for holding labels Name, Street, and City
    GridPane p1 = new GridPane();
    p1.setAlignment(Pos.CENTER);
    p1.setHgap(5);
    p1.setVgap(5);
    p1.add(new Label("Name"), 0, 0);
    p1.add(new Label("Street"), 0, 1);
    p1.add(new Label("City"), 0, 2);
    p1.add(tfName, 1, 0);
    p1.add(tfStreet, 1, 1);
    
    HBox p2 = new HBox(5);
    p2.getChildren().addAll(tfCity, new Label("State"), tfState, 
      new Label("Zip"), tfZip);
    p1.add(p2, 1, 2);

    // Add buttons to a pane
    HBox p3 = new HBox(5);
    p3.getChildren().addAll(btAdd, btFirst, btNext, btPrevious,
      btLast, btUpdate);
    p3.setAlignment(Pos.CENTER);
    
    // Add p1 and p3 to a border pane
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(p1);
    borderPane.setBottom(p3);

    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 400, 120);
    primaryStage.setTitle("Exercise17_09"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    // Display the first record if exists
    try {
      if (raf.length() > 0) readAddress(0);
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
    
    btAdd.setOnAction(e -> {
      try {
        writeAddress(raf.length());
      }
      catch (Exception ex) {
        
      }
    }); 
    btFirst.setOnAction(e -> {
      try {
        if (raf.length() > 0) readAddress(0);
      }
      catch (IOException ex) {   
      }
    });
    btNext.setOnAction(e -> {
      try {
      long currentPosition = raf.getFilePointer();
      if (currentPosition < raf.length())
        readAddress(currentPosition);
      }
      catch (IOException ex) {
        
      }
    });
    btPrevious.setOnAction(e -> {
      try {
        long currentPosition = raf.getFilePointer();
        if (currentPosition -  2 * RECORD_SIZE > 0)
          // Why 2 * 2 * RECORD_SIZE? See the follow-up remarks
          readAddress(currentPosition - 2 * 2 * RECORD_SIZE);
        else
          readAddress(0);
      }
      catch (IOException ex) {
        
      }
    });
    btLast.setOnAction(e -> {
      try {
        long lastPosition = raf.length();
        if (lastPosition > 0)
          // Why 2 * RECORD_SIZE? See the follow-up remarks
          readAddress(lastPosition - 2 * RECORD_SIZE);
      }
      catch (IOException ex) {
      }
    });
    btUpdate.setOnAction(e -> {
      try {
            writeAddress(raf.getFilePointer() - 2 * RECORD_SIZE);
      }
      catch (IOException ex) {
        
      }
    });
  }

  /** Write a record at the end of the file */
  public void writeAddress(long position) {
    try {
      raf.seek(position);
      FixedLengthStringIO1.writeFixedLengthString(
        tfName.getText(), NAME_SIZE, raf);
      FixedLengthStringIO1.writeFixedLengthString(
        tfStreet.getText(), STREET_SIZE, raf);
      FixedLengthStringIO1.writeFixedLengthString(
        tfCity.getText(), CITY_SIZE, raf);
      FixedLengthStringIO1.writeFixedLengthString(
        tfState.getText(), STATE_SIZE, raf);
      FixedLengthStringIO1.writeFixedLengthString(
        tfZip.getText(), ZIP_SIZE, raf);
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /** Read a record at the specified position */
  public void readAddress(long position) throws IOException {
    raf.seek(position);
    String name = FixedLengthStringIO1.readFixedLengthString(
      NAME_SIZE, raf);
    String street = FixedLengthStringIO1.readFixedLengthString(
      STREET_SIZE, raf);
    String city = FixedLengthStringIO1.readFixedLengthString(
      CITY_SIZE, raf);
    String state = FixedLengthStringIO1.readFixedLengthString(
      STATE_SIZE, raf);
    String zip = FixedLengthStringIO1.readFixedLengthString(
      ZIP_SIZE, raf);

    tfName.setText(name);
    tfStreet.setText(street);
    tfCity.setText(city);
    tfState.setText(state);
    tfZip.setText(zip);
  }


  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}

class FixedLengthStringIO1 {
  /** Read fixed number of characters from a DataInput stream */
  public static String readFixedLengthString(int size,
      DataInput in) throws IOException {
    // Declare an array of characters
    char[] chars = new char[size];

    // Read fixed number of characters to the array
    for (int i = 0; i < size; i++)
      chars[i] = in.readChar();

    return new String(chars);
  }

  /** Write fixed number of characters to a DataOutput stream */
  public static void writeFixedLengthString(String s, int size,
      DataOutput out) throws IOException {
    char[] chars = new char[size];

    // Fill in string with characters
    s.getChars(0, s.length(), chars, 0);

    // Fill in blank characters in the rest of the array
    for (int i = Math.min(s.length(), size); i < chars.length; i++)
      chars[i] = ' ';

    // Create and write a new string padded with blank characters
    out.writeChars(new String(chars));
  }
}
