// Exercise33_04Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads
import java.io.*;
import java.net.*;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Exercise33_04Server extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();
  protected RandomAccessFile raf;
  protected String countFile = "webcount.dat"; // File to store count
  protected static int count;
  
  private void openFile() {
    try {
      // Determine whether webcount.dat already exists
      if (new File(countFile).exists()) {
        raf = new RandomAccessFile(countFile, "rw");
        count = raf.readInt(); // Obtain count
      }
      else {
        raf = new RandomAccessFile(countFile, "rw");
        count = 0;
      }
    }
    catch(IOException ex) {
      ex.printStackTrace(); 
    }
  }
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 200, 200);
    primaryStage.setTitle("Exercise33_04Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    openFile();
    new Thread(() -> connectToClient()).start();
  }   
  
  public void connectToClient() {
    try {
      // Create a server socket
      ServerSocket serverSocket = new ServerSocket(8000);
      Platform.runLater(() ->
        ta.appendText("Exercise33_04Server started at " + new Date() + '\n'));

      int i = 0;
      while (true) {
        Socket connectToClient = serverSocket.accept();
        final int count = i;
        Platform.runLater(() -> {
          ta.appendText("Starting thread " + count + "\n");
          ta.appendText("Client IP " +
            connectToClient.getInetAddress() + "\n");
        });
        WebVisitHandler thread =
          new WebVisitHandler(connectToClient);
        thread.start();
        i++;
      }
    }
    catch(IOException ex) {
      ta.appendText(ex + "\n");
    }
  }
  
  public synchronized int increaseCount() {
    try {
      count++;
      raf.seek(0);
      raf.writeInt(count);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    
    return count;
  }

  class WebVisitHandler extends Thread {
    private Socket connectToClient;

    public WebVisitHandler(Socket c) {
      connectToClient = c;
    }

    public void run() {
      try {
        PrintWriter osToClient =
          new PrintWriter(connectToClient.getOutputStream(), true);

        int newCount = increaseCount();

        // Send count to the client
        osToClient.println(newCount);
      }
      catch (IOException ex) {
        ta.appendText(ex + "\n");
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
}
