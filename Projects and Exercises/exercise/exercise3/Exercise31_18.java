import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise31_18 extends Application {
  private TextField tfNumber1 = new TextField();
  private TextField tfNumber2 = new TextField();
  private TextField tfResult = new TextField();
    
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {      
    Menu menuOperation = new Menu("Operation");
    Menu menuExit = new Menu("Exit");
   
    MenuItem menuItemAdd = new MenuItem("Add");
    MenuItem menuItemSubtract = new MenuItem("Subtract");
    MenuItem menuItemMultiply = new MenuItem("Multiply");
    MenuItem menuItemDivide = new MenuItem("Divide");
    menuOperation.getItems().addAll(menuItemAdd, menuItemSubtract,
      menuItemMultiply, menuItemDivide);
    
    MenuItem menuItemClose = new MenuItem("Close");
    menuExit.getItems().add(menuItemClose);
    
    menuItemAdd.setAccelerator(
      KeyCombination.keyCombination("Ctrl+A"));
    menuItemSubtract.setAccelerator(
      KeyCombination.keyCombination("Ctrl+S"));
    menuItemMultiply.setAccelerator(
      KeyCombination.keyCombination("Ctrl+M"));
    menuItemDivide.setAccelerator(
      KeyCombination.keyCombination("Ctrl+D"));
    
    HBox hBox1 = new HBox(5);
    tfNumber1.setPrefColumnCount(2);
    tfNumber2.setPrefColumnCount(2);
    tfResult.setPrefColumnCount(2);
    hBox1.getChildren().addAll(new Label("Number 1:"), tfNumber1,
      new Label("Number 2:"), tfNumber2, new Label("Result:"), 
      tfResult);
    hBox1.setAlignment(Pos.CENTER);
            
    HBox hBox2 = new HBox(5);
    Button btAdd = new Button("Add");
    Button btSubtract = new Button("Subtract");
    Button btMultiply = new Button("Multiply");
    Button btDivide = new Button("Divide");
    hBox2.getChildren().addAll(btAdd, btSubtract, btMultiply, btDivide);
    hBox2.setAlignment(Pos.CENTER);
    
    VBox vBox = new VBox(10);
    vBox.getChildren().addAll(hBox1, hBox2);
    Scene scene = new Scene(vBox, 300, 250);  
    primaryStage.setTitle("Exercise31_18"); // Set the window title
    primaryStage.setScene(scene); // Place the scene in the window
    primaryStage.show(); // Display the window
    
    // Handle menu actions
    menuItemAdd.setOnAction(e -> perform('+'));
    menuItemSubtract.setOnAction(e -> perform('-'));
    menuItemMultiply.setOnAction(e -> perform('*'));
    menuItemDivide.setOnAction(e -> perform('/'));
    menuItemClose.setOnAction(e -> System.exit(0));
    
    // Handle button actions
    btAdd.setOnAction(e -> perform('+'));
    btSubtract.setOnAction(e -> perform('-'));
    btMultiply.setOnAction(e -> perform('*'));
    btDivide.setOnAction(e -> perform('/'));
    
    ContextMenu contextMenu = new ContextMenu();
    contextMenu.getItems().add(menuOperation);
    contextMenu.getItems().add(menuExit);
    
    hBox1.setOnMousePressed(
      e -> contextMenu.show(hBox1, e.getScreenX(), e.getScreenY()));
  }

  private void perform(char operator) {
    double number1 = Double.parseDouble(tfNumber1.getText());
    double number2 = Double.parseDouble(tfNumber2.getText());
    
    double result = 0;
    switch (operator) {
      case '+': result = number1 + number2; break;
      case '-': result = number1 - number2; break;
      case '*': result = number1 * number2; break;
      case '/': result = number1 / number2; break;
    }
    
    tfResult.setText(result + "");
  };

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
