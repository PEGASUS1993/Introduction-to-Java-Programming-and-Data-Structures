import java.text.NumberFormat;
import java.util.Locale;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise36_06 extends Application {
  // Text fields for US dollars, Canadian dollars, German marks
  // and British pounds
  private TextField tfUSDollars = new TextField();
  private TextField tfCanadianDollars = new TextField();
  private TextField tfEuro = new TextField();
  private TextField tfBritishPounds = new TextField();

  // Text fields for excahnge rates
  private TextField tfCanadianDollarsRate = new TextField();
  private TextField tfEuroRate = new TextField();
  private TextField tfBritishPoundsRate = new TextField();

  // Button to convert currencies
  private Button btConvert = new Button("Convert");

  // Number formater
  NumberFormat nfCanada = NumberFormat.getCurrencyInstance(Locale.CANADA);
  NumberFormat nfEuro = NumberFormat.getCurrencyInstance(Locale.GERMAN);
  NumberFormat nfUK = NumberFormat.getCurrencyInstance(Locale.UK);
 
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BorderPane pane = new BorderPane();
    pane.setLeft(new Label("US Dollars"));
    pane.setCenter(tfUSDollars);
    pane.setRight(btConvert);
    
    GridPane gridPane = new GridPane();
    gridPane.add(new Label("Exchange Rate"), 1, 0);
    gridPane.add(new Label("Converted Amount"), 2, 0);
    gridPane.add(new Label("Canadian Dollars"), 0, 1);
    gridPane.add(new Label("Euro"), 0, 2);
    gridPane.add(new Label("British Pounds"), 0, 3);
    gridPane.add(tfCanadianDollarsRate, 1, 1);
    gridPane.add(tfEuroRate, 1, 2);
    gridPane.add(tfBritishPoundsRate, 1, 3);
    gridPane.add(tfCanadianDollars, 2, 1);
    gridPane.add(tfEuro, 2, 2);
    gridPane.add(tfBritishPounds, 2, 3);
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(new Label("Enter Dollar Amount"), 
      pane, new Label("Display Exchange"), gridPane);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 400, 200);
    primaryStage.setTitle("Exercise36_06"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    btConvert.setOnAction(e -> process());
    
    tfUSDollars.setAlignment(Pos.BOTTOM_RIGHT); 
    tfCanadianDollars.setAlignment(Pos.BOTTOM_RIGHT); 
    tfEuro.setAlignment(Pos.BOTTOM_RIGHT); 
    tfBritishPounds.setAlignment(Pos.BOTTOM_RIGHT); 
    tfCanadianDollarsRate.setAlignment(Pos.BOTTOM_RIGHT); 
    tfEuroRate.setAlignment(Pos.BOTTOM_RIGHT); 
    tfBritishPoundsRate.setAlignment(Pos.BOTTOM_RIGHT); 
    tfCanadianDollars.setEditable(false);
    tfEuro.setEditable(false);
    tfBritishPounds.setEditable(false);
  }

  private void process() {
    double USDollars = new Double(tfUSDollars.getText().trim()).doubleValue();
    double rateCanada = new Double(tfCanadianDollarsRate.getText().trim()).doubleValue();
    double rateGermany = new Double(tfEuroRate.getText().trim()).doubleValue();
    double rateBritan = new Double(tfBritishPoundsRate.getText().trim()).doubleValue();

    tfCanadianDollars.setText(nfCanada.format(USDollars*rateCanada));
    tfEuro.setText(nfEuro.format(USDollars*rateGermany));
    tfBritishPounds.setText(nfUK.format(USDollars*rateBritan));
  }
    
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}