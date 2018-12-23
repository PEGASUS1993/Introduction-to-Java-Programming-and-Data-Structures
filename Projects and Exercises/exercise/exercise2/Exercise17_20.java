import java.io.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Exercise17_20 extends Application {
  private TextField tfFilename = new TextField();
  private Button btSave = new Button("Save the change");
  private TextArea taBits = new TextArea();
  
  public void start(Stage primaryStage) {
    BorderPane pane1 = new BorderPane();
    pane1.setLeft(new Label("Enter a file: "));
    pane1.setCenter(tfFilename);

    BorderPane pane = new BorderPane();
    pane.setTop(pane1);
    pane.setCenter(new ScrollPane(taBits));
    pane.setBottom(btSave);
    BorderPane.setAlignment(btSave, Pos.CENTER);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 120);
    primaryStage.setTitle("Exercise17_20"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
        
    taBits.setWrapText(true);
    tfFilename.setOnAction(e -> {
      try {
        FileInputStream in = new FileInputStream(tfFilename.getText().trim());

        String s = "";
        int value;
        while ((value = in.read()) != -1) {
          s += getBits(value);
        }

        in.close();
        taBits.setText(s);
      }
      catch (IOException ex) {
        ex.printStackTrace();
      }
    });
    
    btSave.setOnAction(e -> {
      try {
        BitOutputStream output = new BitOutputStream(new File(tfFilename.getText().trim()));
        output.writeBit(taBits.getText().trim());
        output.close();    
      } 
      catch (IOException ex) {
        ex.printStackTrace();
      }
    });
  }
  
  public static String getBits(int value) {
    String result = "";
    
    int mask = 1;
    for (int i = 7; i >= 0; i--) {
      int temp = value >> i;
      int bit = temp & mask;
      result = result + bit;
    }
    return result;
  }
  
  public static class BitOutputStream {
    private FileOutputStream output;
    private int value;
    private int count = 0;
    private int mask = 1; // The bits are all zeros except the last one
    
    public BitOutputStream(File file) throws IOException {
      output = new FileOutputStream(file);
    }
    
    public void writeBit(char bit) throws IOException {
      count++;
      value = value << 1;
      
      if (bit == '1') 
        value = value | mask;
      
      if (count == 8) {
        output.write(value);
        count = 0;
      }
    }
    
    public void writeBit(String bitString) throws IOException {
      for (int i = 0; i < bitString.length(); i++)
        writeBit(bitString.charAt(i));
    }
    
    /** Write the last byte and close the stream. If the last byte is not full, right-shfit with zeros */
    public void close() throws IOException {     
      if (count > 0) {
        value = value << (8 - count);
        output.write(value);
      }
      
      output.close();
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
