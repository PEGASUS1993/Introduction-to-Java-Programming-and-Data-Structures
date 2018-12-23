import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Exercise16_11Extra extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    VBox vBoxType = new VBox(5);
    RadioButton rbAdd = new RadioButton("Add");
    rbAdd.setSelected(true);
    RadioButton rbSubtract = new RadioButton("Subtract");
    RadioButton rbMultiply = new RadioButton("Multiply");
    RadioButton rbDivide = new RadioButton("Divide");
    ToggleGroup group = new ToggleGroup();
    rbAdd.setToggleGroup(group);
    rbSubtract.setToggleGroup(group);
    rbMultiply.setToggleGroup(group);
    rbDivide.setToggleGroup(group);
    vBoxType.getChildren().addAll(new Label("Choose a type"), rbAdd, rbSubtract, 
      rbMultiply, rbDivide);
    
    VBox vBoxDifficultyLevel = new VBox(5);
    RadioButton rbLevel1 = new RadioButton("Numbers from 0 to 5");
    rbLevel1.setSelected(true);
    RadioButton rbLevel2 = new RadioButton("Numbers from 3 to 9");
    RadioButton rbLevel3 = new RadioButton("Numbers from 0 to 20");
    RadioButton rbLevel4 = new RadioButton("An two digits");    
    vBoxDifficultyLevel.getChildren().addAll(new Label("Choose a level"), rbLevel1, 
      rbLevel2, rbLevel3, rbLevel4);
    ToggleGroup group2 = new ToggleGroup();
    rbLevel1.setToggleGroup(group2);
    rbLevel2.setToggleGroup(group2);
    rbLevel3.setToggleGroup(group2);
    rbLevel4.setToggleGroup(group2);
    
    HBox hBox = new HBox(10);
    hBox.getChildren().addAll(vBoxType, vBoxDifficultyLevel);

    Label lblQuestion = new Label();
    TextField tfAnswer = new TextField();
    TextField tfCorrectCount = new TextField();
    TextField tfTimeSpent = new TextField();
    GridPane gridPane = new GridPane();
    gridPane.add(new Label("Question:"), 0, 0);
    gridPane.add(new Label("Answer:"), 1, 0);
    gridPane.add(lblQuestion, 0, 1);
    gridPane.add(tfAnswer, 1, 1);

    VBox vBox = new VBox(5);
    vBox.setAlignment(Pos.CENTER);

    Button btStart = new Button("Generate Question");
    Label lblStatus = new Label();
    HBox hBoxForButtons = new HBox(5);
    hBoxForButtons.setAlignment(Pos.BASELINE_RIGHT);
    hBoxForButtons.getChildren().addAll(btStart, lblStatus);
    vBox.getChildren().addAll(hBox, gridPane, hBoxForButtons);
    vBox.setAlignment(Pos.CENTER);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 350, 250);
    primaryStage.setTitle("Exercise16_11"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    btStart.setOnAction(e -> {
      if (rbLevel1.isSelected()) {
        number1 = (int)(Math.random() * 5);
        number2 = (int)(Math.random() * 5);
      }
      
      if (rbAdd.isSelected()) {
        lblQuestion.setText(number1 + " + " + number2 + " = ");
      }
    });

    tfAnswer.setOnAction(e -> {
      int answer = Integer.parseInt(tfAnswer.getText());
      if (number1 + number2 == answer) {
        lblStatus.setText("Correct!");
      }
      else {
        lblStatus.setText("The correct answer is " + (number1 + number2));
      }
    });
  }

  int number1 = 0, number2 = 1;
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
