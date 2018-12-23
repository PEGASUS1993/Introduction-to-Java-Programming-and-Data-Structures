import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Exercise16_29 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BorderPane pane = new BorderPane();

    CalendarPane calendarPane = new CalendarPane();
    pane.setCenter(calendarPane);

    Button btPrior = new Button("Prior");
    Button btNext = new Button("Next");
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(btPrior, btNext);
    pane.setBottom(hBox);
    hBox.setAlignment(Pos.CENTER);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 600, 300);
    primaryStage.setTitle("Exercise16_29"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    btPrior.setOnAction(e -> {
      int currentMonth = calendarPane.getMonth();
      if (currentMonth == 0) { // The previous month is 11 for Dec
        calendarPane.setYear(calendarPane.getYear() - 1);
        calendarPane.setMonth(11);
      }
      else {
        calendarPane.setMonth((currentMonth - 1) % 12);
      }
    });
    
    btNext.setOnAction(e -> {
      int currentMonth = calendarPane.getMonth();
      if (currentMonth == 11) // The next month is 0 for Jan
        calendarPane.setYear(calendarPane.getYear() + 1);

      calendarPane.setMonth((currentMonth + 1) % 12);
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

class CalendarPane extends BorderPane {
  private String[] monthName = {"January", "Feburary", "March", "April", "May",
    "June", "July", "August", "September", "October", "November", "December"};
  
  // The header label
  private Label lblHeader = new Label();

  // Maximum number of labels to display day names and days
  private Label[] lblDay = new Label[49];

  private Calendar calendar;
  private int month;  // The specified month
  private int year;  // The specified year

  public CalendarPane() {
    // Create labels for displaying days
    for (int i = 0; i < 49; i++) {
      lblDay[i] = new Label();
      lblDay[i].setTextAlignment(TextAlignment.RIGHT);
    }

    lblDay[0].setText("Sunday");
    lblDay[1].setText("Monday");
    lblDay[2].setText("Tuesday");
    lblDay[3].setText("Wednesday");
    lblDay[4].setText("Thursday");
    lblDay[5].setText("Friday");
    lblDay[6].setText("Saturday");

    GridPane dayPane = new GridPane();
    dayPane.setAlignment(Pos.CENTER);

    dayPane.setHgap(10);
    dayPane.setVgap(10);
    for (int i = 0; i < 49; i++) {
      dayPane.add(lblDay[i], i % 7, i / 7);
    }

    // Place header and calendar body in the pane
    this.setTop(lblHeader);
    BorderPane.setAlignment(lblHeader, Pos.CENTER);
    this.setCenter(dayPane);

    // Set current month and year
    calendar = new GregorianCalendar();
    month = calendar.get(Calendar.MONTH);
    year = calendar.get(Calendar.YEAR);
    updateCalendar();

    // Show calendar
    showHeader();
    showDays();
  }

  public void showHeader() {
    lblHeader.setText(monthName[month] + ", " + year);
  }

  /**
   * Display days
   */
  public void showDays() {
    // Get the day of the first day in a month
    int startingDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);

    // Fill the calendar with the days before this month
    Calendar cloneCalendar = (Calendar) calendar.clone();
    cloneCalendar.add(Calendar.DATE, -1); // Becomes preceding month
    int daysInPrecedingMonth = cloneCalendar.getActualMaximum(
            Calendar.DAY_OF_MONTH);

    for (int i = 0; i < startingDayOfMonth - 1; i++) {
      lblDay[i + 7].setTextFill(Color.LIGHTGRAY);
      lblDay[i + 7].setText(daysInPrecedingMonth
              - startingDayOfMonth + 2 + i + "");
    }

    // Display days of this month
    int daysInCurrentMonth = calendar.getActualMaximum(
            Calendar.DAY_OF_MONTH);
    for (int i = 1; i <= daysInCurrentMonth; i++) {
      lblDay[i - 2 + startingDayOfMonth + 7].setTextFill(Color.BLACK);
      lblDay[i - 2 + startingDayOfMonth + 7].setText(i + "");
    }

    // Fill the calendar with the days after this month
    int j = 1;
    for (int i = daysInCurrentMonth - 1 + startingDayOfMonth + 7;
            i < 49; i++) {
      lblDay[i].setTextFill(Color.LIGHTGRAY);
      lblDay[i].setText(j++ + "");
    }
  }

  /**
   * Set the calendar to the first day of the specified month and year
   */
  public void updateCalendar() {
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month);
    calendar.set(Calendar.DATE, 1);
  }

  /**
   * Return month
   */
  public int getMonth() {
    return month;
  }

  /**
   * Set a new month
   */
  public void setMonth(int newMonth) {
    month = newMonth;
    updateCalendar();
    showHeader();
    showDays();
  }

  /**
   * Return year
   */
  public int getYear() {
    return year;
  }

  /**
   * Set a new year
   */
  public void setYear(int newYear) {
    year = newYear;
    updateCalendar();
    showHeader();
    showDays();
  }
}
