import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Exercise16_05Extra extends Application {
  private double paneWidth = 240;
  private double paneHeight = 200;

  private Button bt1 = new Button("1");
  private Button bt2 = new Button("2");
  private Button bt3 = new Button("3");
  private Button bt4 = new Button("4");
  private Button bt5 = new Button("5");
  private Button bt6 = new Button("6");
  private Button bt7 = new Button("7");
  private Button bt8 = new Button("8");
  private Button bt9 = new Button("9");
  private Button bt0 = new Button("0");
  private Button btAdd = new Button("+");
  private Button btSubtract = new Button("-");
  private Button btMultiply = new Button("*");
  private Button btDivide = new Button("/");
  private Button btRemainder = new Button("%");
  private Button btSqrt = new Button("sqrt");
  private Button btDecimal = new Button(".");
  private Button btEqual = new Button("=");
  private TextField tf = new TextField();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    GridPane pane = new GridPane();
    pane.setHgap(5);
    pane.setVgap(5);

    pane.add(new Button("MC"), 0, 0);
    pane.add(bt7, 1, 0);
    pane.add(bt8, 2, 0);
    pane.add(bt9, 3, 0);
    pane.add(btDivide, 4, 0);
    pane.add(btSqrt, 5, 0);
    
    pane.add(new Button("MR"), 0, 1);
    pane.add(bt4, 1, 1);
    pane.add(bt5, 2, 1);
    pane.add(bt6, 3, 1);
    pane.add(btMultiply, 4, 1);
    pane.add(btRemainder, 5, 1);

    pane.add(new Button("MS"), 0, 2);
    pane.add(bt1, 1, 2);
    pane.add(bt2, 2, 2);
    pane.add(bt3, 3, 2);
    pane.add(btSubtract, 4, 2);
    pane.add(new Button("1/x"), 5, 2);

    pane.add(new Button("M+"), 0, 3);
    pane.add(bt0, 1, 3);
    pane.add(new Button("+/-"), 2, 3);
    pane.add(btDecimal, 3, 3);
    pane.add(btAdd, 4, 3);
    pane.add(btEqual, 5, 3);
    
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(new Button("Back"), new Button("CE"),
      new Button("C"));
    hBox.setAlignment(Pos.BASELINE_RIGHT);
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(tf, hBox, pane);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise16_05"); // Set the stage title
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
