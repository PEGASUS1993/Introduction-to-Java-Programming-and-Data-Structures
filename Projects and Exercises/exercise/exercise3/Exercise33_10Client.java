import java.io.*;
import java.net.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise33_10Client extends Application {
  // Text field for chat
  private TextField tf = new TextField();

  // Text field for name
  private TextField tfName = new TextField("Enter a name");

  // Text area to display contents
  private TextArea ta = new TextArea();

  // Socket
  private Socket socket;

  // IO streams
  private DataOutputStream dout;
  private DataInputStream din;

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);

    GridPane gridPane = new GridPane();
    gridPane.add(new Label("Name"), 0, 0);
    gridPane.add(new Label("Enter text"), 0, 1);
    gridPane.add(tf, 1, 0);
    gridPane.add(tfName, 1, 1);

    BorderPane pane = new BorderPane();
    pane.setTop(gridPane);
    pane.setCenter(new ScrollPane(ta));
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 200, 200);
    primaryStage.setTitle("Exercise33_10Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    tf.setOnAction(e -> process()); // Register listener
        
    try {
        // Create a socket to connect to the server
        socket = new Socket("localhost", 8000);

        // Create an input stream to receive data from the server
        din = new DataInputStream(socket.getInputStream());

        // Create an output stream to send data to the server
        dout = new DataOutputStream(socket.getOutputStream());

        // Start a new thread for receiving messages
        new Thread(() -> run()).start();
    }
    catch (IOException ex) {
        ex.printStackTrace();
    }
  }
  
  public void run(){
      try{
          while(true){
              // Get message
              String text = din.readUTF();

              // Display to the text area
              ta.appendText(text + '\n');
          }
      } catch (IOException ex) {
          ex.printStackTrace();
      }
  }
  
  private void process() {
    try {
      // Get the text from the text field
      String string = tfName.getText().trim() + ": " + tf.getText().trim();

      // Send the text to the server
      dout.writeUTF(string);

      // Clear jtf
      tf.setText("");
    }
    catch (IOException ex) {
      System.err.println(ex);
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
