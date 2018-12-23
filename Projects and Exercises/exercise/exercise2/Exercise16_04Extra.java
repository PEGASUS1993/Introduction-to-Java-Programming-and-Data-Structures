import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Exercise16_04Extra extends Application {
  private double paneWidth = 520;
  private double paneHeight = 240;

  private String singleFilerScheme =
      "Single Filers\nTaxable Income\t\tRate\n" +
      "Up to $27,050\t\t15%\n"+
      "$27,051 - $65,550\t27.5%\n" +
      "$65,551 - $136,750\t30.5%\n" +
      "$136,751 - $297,350\t35.5%\n" +
      "$297,351 or more\t39.1%";

  private String marriedJointlyFilerScheme =
    "Married Jointly Fliers\nTaxable Income\t\tRate\n" +
    "Up to $45,200\t\t15%\n"+
    "$45,201 - $109,250\t27.5%\n" +
    "$109,251 - $166,500\t30.5%\n" +
    "$166,501 - $297,350\t35.5%\n" +
    "$297,351 or more\t39.1%";

  private String marriedSeparatelyFilerScheme =
    "Married Separately Fliers\nTaxable Income\t\tRate\n" +
    "Up to $22,600\t\t15%\n"+
    "$22,601 - $54,655\t27.5%\n" +
    "$54,656 - $83,250\t30.5%\n" +
    "$83,251 - $148,675\t35.5%\n" +
    "$148,676 or more\t39.1%";

  private String headOfHouseFilerScheme =
    "Head of Household Fliers\nTaxable Income\t\tRate\n" +
    "Up to $36,250\t\t15%\n"+
    "$36,251 - $93,650\t27.5%\n" +
    "$93,651 - $151,650\t30.5%\n" +
    "$151,651 - $297,350\t35.5%\n" +
    "$297,351 or more\t39.1%";

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    RadioButton singleFiler = new RadioButton("Single filers");
    singleFiler.setSelected(true);
    RadioButton marriedJointly = new RadioButton("Married filing jointly or qualifying widow(er)");
    RadioButton marriedSeparately = new RadioButton("Married filing separately");
    RadioButton headOfHousehold = new RadioButton("Head of Household");
    ToggleGroup group = new ToggleGroup();
    singleFiler.setToggleGroup(group);  
    marriedJointly.setToggleGroup(group);
    marriedSeparately.setToggleGroup(group);
    headOfHousehold.setToggleGroup(group);
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(singleFiler, marriedJointly, marriedSeparately,
      headOfHousehold);

    HBox hBox = new HBox(5);
    Label lblDescription = new Label(singleFilerScheme);
    hBox.getChildren().addAll(vBox, lblDescription);
    
    GridPane pane = new GridPane();
    pane.setHgap(5);
    pane.setVgap(5);
    TextField tfTaxableIncome = new TextField();
    TextField tfTax = new TextField();
    tfTaxableIncome.setPrefColumnCount(8);
    tfTax.setPrefColumnCount(8);
    tfTax.setEditable(false);
    pane.setAlignment(Pos.CENTER);
    pane.add(new Label("Taxable income"), 0, 0);
    pane.add(tfTaxableIncome, 1, 0);
    pane.add(new Label("Tax"), 0, 1);
    pane.add(tfTax, 1, 1);
    Button btComputeTax = new Button("Compute Tax");
    pane.add(btComputeTax, 1, 2);
    
    VBox vBoxAll = new VBox(5);
    vBoxAll.getChildren().addAll(new Label("Select Tax Status"), hBox, pane);


    // Create a scene and place it in the stage
    Scene scene = new Scene(vBoxAll, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise16_04"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    singleFiler.setOnAction(e -> lblDescription.setText(singleFilerScheme));
    marriedJointly.setOnAction(e -> lblDescription.setText(marriedJointlyFilerScheme));
    marriedSeparately.setOnAction(e -> lblDescription.setText(marriedSeparatelyFilerScheme));
    headOfHousehold.setOnAction(e -> lblDescription.setText(headOfHouseFilerScheme));
    
    btComputeTax.setOnAction(e -> {
      Tax tax = new Tax(); // Programming Exercise 10.8
      int status = 0;
      if (singleFiler.isSelected())
        status = 0;
      else if (marriedJointly.isSelected()) 
        status = 1;
      else if (marriedSeparately.isSelected())
        status = 2;
      else if (headOfHousehold.isSelected())
        status = 3;        
      tax.setFilingStatus(status);
      tax.setTaxableIncome(Double.parseDouble(tfTaxableIncome.getText() + ""));
      tfTax.setText(tax.findTax() + "");
    });    
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
