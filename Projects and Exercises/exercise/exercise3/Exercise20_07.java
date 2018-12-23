import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise20_07 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    HangmanPane pane = new HangmanPane();
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise20_07"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    pane.requestFocus();
  }

  class HangmanPane extends Pane {
    double ballRadius = 10;
    double leftAngle = 120;
    double rightAngle = 60;
    double angle = 90; // Start from the middle
    double angleDelta = 1; // Swing interval
    double delay = 100;

    Timeline animation = new Timeline(
      new KeyFrame(Duration.millis(200), e -> paint()));

    public HangmanPane() {
      animation.setCycleCount(Timeline.INDEFINITE);
          
      setNewHiddenWord();
      paint();
            
      this.setOnKeyPressed( e -> {
        if (e.getCode() == KeyCode.ENTER) {
          // Start a new game
          setNewHiddenWord();
        } else {
          char letter = e.getText().charAt(0);
          System.out.println(letter);
          processAGuessedLetter(letter);

          if (numberOfMisses == 7)
            animation.play();
          else
            animation.stop();
        }

        paint();
      });
    }

    protected void paint() {   
      this.getChildren().clear();
      
      if (isFinished) {
        // Display the guessed word
        this.getChildren().add(new Text(120, 210, "The word is: " + guessedWord.toString()));
        this.getChildren().add(new Text(120, 230, "To continue the game, press ENTER"));
      } else {
        // Display the guessed word
        this.getChildren().add(new Text(120, 210, "Guess a word: " + guessedWord.toString()));

        if (numberOfMisses > 0)
          this.getChildren().add(new Text(120, 230, "Missed letters: " + missedLetters.toString()));
      }

      Arc arc = new Arc(60, 240, 40, 20, 0, 180); // Draw the base
      arc.setFill(Color.WHITE);
      arc.setStroke(Color.BLACK);

      Line line1 = new Line(20 + 40, 220, 20 + 40, 20); // Draw the pole
      Line line2 = new Line(20 + 40, 20, 20 + 40 + 100, 20); // Draw the hanger
      this.getChildren().addAll(arc, line1, line2);
      
      if (numberOfMisses == 7) {
        if (angle < rightAngle)
          angleDelta = 1; // Swing to the left
        else if (angle > leftAngle)
          angleDelta = -1; // Swing to the right

        angle += angleDelta;
      }

      if (numberOfMisses < 1) return; // Skip the rest

      double x1 = 20 + 40 + 100;
      double y1 = 20;
      double radius = 20;
      double x = x1 + radius * Math.cos(Math.toRadians(angle));
      double y = y1 + radius * Math.sin(Math.toRadians(angle));
      Line line3 = new Line(20 + 40 + 100, 20, x, y); // Draw the hanger
      this.getChildren().add(line3);
      
      if (numberOfMisses < 2) return; // Skip the rest
      
      // Draw the circle
      double length = 20 + 20;
      x = x1 + (int) (length * Math.cos(Math.toRadians(angle)));
      y = y1 + (int) (length * Math.sin(Math.toRadians(angle)));
      Circle circle = new Circle(x, y, radius); // Draw the hanger
      circle.setFill(Color.WHITE);
      circle.setStroke(Color.BLACK);
      this.getChildren().add(circle);
          
      if (numberOfMisses < 3) return;
      
      // Draw the left arm
      length = distance(x1, y1, 20 + 40 + 100 - (int) (radius * Math.cos(Math
          .toRadians(45))), 40 + radius
          + (int) (radius * Math.sin(Math.toRadians(45))));
      double angle1 = Math.toDegrees(Math.asin(radius
          * Math.cos(Math.toRadians(45)) / length));
      double x2 = x1 + (int) (length * Math.cos(Math.toRadians(angle + angle1)));
      double y2 = y1 + (int) (length * Math.sin(Math.toRadians(angle + angle1)));

      length = (int) distance(x1, y1, 20 + 40 + 100 - 60, 40 + radius + 60);
      angle1 = Math.toDegrees(Math.asin(60 / length));
      double x3 = x1 + (int) (length * Math.cos(Math.toRadians(angle + angle1)));
      double y3 = y1 + (int) (length * Math.sin(Math.toRadians(angle + angle1)));
      Line line4 = new Line(x2, y2, x3, y3);
      this.getChildren().add(line4);
      
      if (numberOfMisses < 4) return; 
      
      // Draw the right arm
      length = distance(x1, y1, 20 + 40 + 100 + (int) (radius * Math.cos(Math
          .toRadians(45))), 40 + radius
          + (int) (radius * Math.sin(Math.toRadians(45))));
      angle1 = -Math.toDegrees(Math.asin(radius * Math.cos(Math.toRadians(45))
          / length));
      x2 = x1 + (int) (length * Math.cos(Math.toRadians(angle + angle1)));
      y2 = y1 + (int) (length * Math.sin(Math.toRadians(angle + angle1)));

      length = (int) distance(x1, y1, 20 + 40 + 100 + 60, 40 + radius + 60);
      angle1 = -Math.toDegrees(Math.asin(60 / length));
      x3 = x1 + (int) (length * Math.cos(Math.toRadians(angle + angle1)));
      y3 = y1 + (int) (length * Math.sin(Math.toRadians(angle + angle1)));
      Line line5 = new Line(x2, y2, x3, y3);
      this.getChildren().add(line5);

      if (numberOfMisses < 5) return;
      
      // Draw the body
      length = 40 + 20;
      x2 = x1 + (int) (length * Math.cos(Math.toRadians(angle)));
      y2 = y1 + (int) (length * Math.sin(Math.toRadians(angle)));

      length = 40 + 20 + 60;
      x3 = x1 + (int) (length * Math.cos(Math.toRadians(angle)));
      y3 = y1 + (int) (length * Math.sin(Math.toRadians(angle)));
      Line line6 = new Line(x2, y2, x3, y3);   
      this.getChildren().add(line6);

      if (numberOfMisses < 6) return;
      
      // Draw the left leg
      length = distance(x1, y1, 20 + 40 + 100 - 40, 40 + radius + 80 + 40);
      angle1 = Math.toDegrees(Math.asin(40.0 / length));
      double x4 = x1 + (int) (length * Math.cos(Math.toRadians(angle + angle1)));
      double y4 = y1 + (int) (length * Math.sin(Math.toRadians(angle + angle1)));
      Line line7 = new Line(x3, y3, x4, y4);
      this.getChildren().add(line7);

      if (numberOfMisses < 7) return;
      
      // Draw the right leg
      angle1 = -Math.toDegrees(Math.asin(40.0 / length));
      x4 = x1 + (int) (length * Math.cos(Math.toRadians(angle + angle1)));
      y4 = y1 + (int) (length * Math.sin(Math.toRadians(angle + angle1)));
      Line line8 = new Line(x3, y3, x4, y4);
      this.getChildren().add(line8);
    }

    String[] words = { "write", "program", "that", "receive", "positive",
        "excellent", "linger", "violin", "strange", "holiday", "twilight",
        "school", "teacher", "tutor", "mother"};
    String hiddenWord;
    StringBuilder guessedWord = new StringBuilder();
    double numberOfCorrectLettersGuessed = 0, numberOfMisses = 0;
    StringBuilder missedLetters = new StringBuilder();
    boolean isFinished = false;

    void setNewHiddenWord() {
      angle = 90; // Start from the middle

      int index = (int)(Math.random() * words.length);
      hiddenWord = words[index];

      guessedWord.setLength(0);
      for (double i = 0; i < hiddenWord.length(); i++)
        guessedWord.append('*');

      missedLetters.setLength(0);
      numberOfCorrectLettersGuessed = 0;
      numberOfMisses = 0;
      isFinished = false;
    }

    void processAGuessedLetter(char letter) {
      if (guessedWord.indexOf(letter + "") >= 0) {
        // System.out.println("\t" + letter + " is already in the word");
      } else if (hiddenWord.indexOf(letter) < 0) {
        // System.out.println("\t" + letter + " is not in the word");

        if (missedLetters.indexOf(letter + "") < 0) {
          numberOfMisses++;
          missedLetters.append(letter);

          if (numberOfMisses == 7) {
            isFinished = true;
            guessedWord = new StringBuilder(hiddenWord);
          }
        }
      } else {
        int k = hiddenWord.indexOf(letter);
        while (k >= 0) {
          guessedWord.setCharAt(k, letter);
          numberOfCorrectLettersGuessed++;
          k = hiddenWord.indexOf(letter, k + 1);

          if (numberOfCorrectLettersGuessed == hiddenWord.length()) {
            isFinished = true;
            paint();
          }
        }
      }
    }
  }
  
  /** Compute the distance between two points (x1, y1) and (x2, y2) */
  public static double distance(double x1, double y1, double x2, double y2) {
    return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
