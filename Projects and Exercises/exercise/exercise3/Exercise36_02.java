import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise36_02 extends Application {
  private Locale[] availableLocales = Locale.getAvailableLocales();
  private String[] availableTimeZones = TimeZone.getAvailableIDs();
  private DateFormat dateFormat = DateFormat.getDateInstance();
  private DateFormat timeFormat = DateFormat.getTimeInstance();
  private Date date = new Date();
  private TextField tfDate = new TextField(dateFormat.format(date));
  private TextField tfTime = new TextField(timeFormat.format(date));
  private ComboBox<String> cboLocale = new ComboBox<>();
  private ComboBox<String> cboTimeZone = new ComboBox<>();
  private ComboBox<String> cboDateStyle = new ComboBox<>();
  private ComboBox<String> cboTimeStyle = new ComboBox<>();

  private Locale locale = Locale.US;
  private String timeZone = TimeZone.getDefault().getID();

  private int dateStyle = DateFormat.FULL;
  private int timeStyle = DateFormat.FULL;
 
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Initialize cboLocales with all available locales
    setAvailableLocales();

    // Initialize cboTimeZones with all available time zones
    setAvailableTimeZones();

    cboDateStyle.getItems().addAll(
      "Full", "LONG", "MEDIUM", "SHORT");
    cboTimeStyle.getItems().addAll(
      "Full", "LONG", "MEDIUM", "SHORT");
    
    HBox hBox1 = new HBox(5);
    hBox1.getChildren().addAll(
      new Label("Date:"), tfDate, new Label("Time:"), tfTime);

    HBox hBox2 = new HBox(5);
    hBox2.getChildren().addAll(
      new Label("Locale:"), cboLocale, 
      new Label("Time Zone:"), cboTimeZone);

    HBox hBox3 = new HBox(5);
    hBox3.getChildren().addAll(
      new Label("Date Style:"), cboDateStyle, 
      new Label("Time Style:"), cboTimeStyle);
    hBox3.setAlignment(Pos.CENTER);
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(hBox1, hBox2, hBox3);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 400, 200);
    primaryStage.setTitle("Exercise36_02"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    cboLocale.setOnAction(e -> {
      locale = availableLocales[cboLocale.getSelectionModel().getSelectedIndex()];
      process();
    });
    
    cboTimeZone.setOnAction(e -> {
      timeZone = availableTimeZones[cboTimeZone.getSelectionModel().getSelectedIndex()];
      process();
    });

    cboDateStyle.setOnAction(e -> {
      dateStyle = cboDateStyle.getSelectionModel().getSelectedIndex();
      process();
    });
        
    cboTimeStyle.setOnAction(e -> {
      timeStyle = cboTimeStyle.getSelectionModel().getSelectedIndex();
      process();
    });
  }

  private void process() {
    dateFormat = DateFormat.getDateInstance(dateStyle, locale);
    timeFormat = DateFormat.getTimeInstance(timeStyle, locale);
    dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
    timeFormat.setTimeZone(TimeZone.getTimeZone(timeZone));

    tfDate.setText(dateFormat.format(new Date()));
    tfTime.setText(timeFormat.format(new Date()));
  }

  private void setAvailableLocales() {
    for (int i = 0; i < availableLocales.length; i++) 
      cboLocale.getItems().add(availableLocales[i]
       .getDisplayName() + " " + availableLocales[i].toString());

    cboLocale.getSelectionModel().selectFirst();
  }

  private void setAvailableTimeZones() {
    // Sort time zones
    Arrays.sort(availableTimeZones);
    for (int i = 0; i < availableTimeZones.length; i++) {
      cboTimeZone.getItems().add(availableTimeZones[i]);
    }
    cboTimeZone.getSelectionModel().selectFirst();
  }
    
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}