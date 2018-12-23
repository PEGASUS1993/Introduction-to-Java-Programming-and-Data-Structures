import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Polygon;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.*;
import javafx.geometry.Point2D;

import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercise21_03Extra extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        MapPane map = new MapPane();
        Scene scene = new Scene(map, 1200, 800);

        primaryStage.setTitle("USMap"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        map.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                map.enlarge(); // Enlarge the map
            }
            else if (e.getCode() == KeyCode.DOWN) {
                map.shrink(); // Shrink the map
            }
        });
        map.requestFocus();
    }

    class MapPane extends BorderPane {
        private Group group = new Group();
        private Text blue = new Text();
        private SimpleIntegerProperty blueCounts = new SimpleIntegerProperty();
        private Text red = new Text();
        private SimpleIntegerProperty redCounts = new SimpleIntegerProperty();
        private HBox hBox = new HBox(blue, red);
        private ArrayList<Integer> electoralVotes = new ArrayList<>();

        MapPane() {
            // Load coordinates from a file
            ArrayList<ArrayList<Point2D>> points = getPoints();

            // Add points to the polygon list
            for (int i = 0; i < points.size(); i++) {
                Polygon polygon = new Polygon();
                // Add points to the polygon list
                for (int j = 0; j < points.get(i).size(); j++)
                    polygon.getPoints().addAll(points.get(i).get(j).getX(),
                            -points.get(i).get(j).getY());
                polygon.setFill(Color.WHITE);
                polygon.setStroke(Color.BLACK);
                polygon.setStrokeWidth(1 / 14.0);

                final int n = i;
                polygon.setOnMouseClicked(e -> {
                    if (e.getButton() == MouseButton.PRIMARY) {
                        if (polygon.getFill() == Color.BLUE) {
                            blueCounts.set(blueCounts.getValue()-electoralVotes.get(n));
                            redCounts.set(redCounts.getValue()+electoralVotes.get(n));
                        } else if (polygon.getFill() == Color.WHITE) {
                            redCounts.set(redCounts.getValue()+electoralVotes.get(n));
                        }
                        polygon.setFill(Color.RED);
                    }
                    else if (e.getButton() == MouseButton.SECONDARY) {
                        if (polygon.getFill() == Color.RED) {
                            redCounts.set(redCounts.getValue()-electoralVotes.get(n));
                            blueCounts.set(blueCounts.getValue()+electoralVotes.get(n));
                        } else if (polygon.getFill() == Color.WHITE) {
                            blueCounts.set(blueCounts.getValue()+electoralVotes.get(n));
                        }
                        polygon.setFill(Color.BLUE);
                    }
                    else {
                        if (polygon.getFill() == Color.BLUE) {
                            blueCounts.set(blueCounts.getValue()-electoralVotes.get(n));
                        } else if (polygon.getFill() == Color.RED) {
                            redCounts.set(redCounts.getValue()-electoralVotes.get(n));
                        }
                        polygon.setFill(Color.WHITE);
                    }
                });

                group.getChildren().add(polygon);
            }

            group.setScaleX(14);
            group.setScaleY(14);

            blue.setFont(Font.font(28));
            blue.setFill(Color.BLUE);
            blue.textProperty().bind(new SimpleStringProperty("Blue: ").concat(blueCounts.asString()));
            red.setFont(Font.font(28));
            red.setFill(Color.RED);
            red.textProperty().bind(new SimpleStringProperty("Red: ").concat(redCounts.asString()));
            hBox.setSpacing(20);
            hBox.setAlignment(Pos.CENTER);
            this.setCenter(group);
            this.setBottom(hBox);
        }

        public void enlarge() {
            group.setScaleX(1.1 * group.getScaleX());
            group.setScaleY(1.1 * group.getScaleY());
        }

        public void shrink() {
            group.setScaleX(0.9 * group.getScaleX());
            group.setScaleY(0.9 * group.getScaleY());
        }

        private ArrayList<ArrayList<Point2D>> getPoints() {
            ArrayList<ArrayList<Point2D>> points = new ArrayList<>();
            // Get a table with all the states as keys and their electoral vote as a value
            HashMap<String, Integer> electoralTable = getElectoralTable();

            try (Scanner input = new Scanner(new java.net.URL("https://" +
                    "liveexample.pearsoncmg.com/data/usmap.txt")
                    .openStream())) {
                while (input.hasNext()) {
                    String s = input.nextLine();
                    if (Character.isAlphabetic(s.charAt(0))) {
                        points.add(new ArrayList<>()); // For a new state
                        electoralVotes.add(electoralTable.get((String)s)); // Add electoral vote number for the corresponded state
                    }
                    else {
                        Scanner scanAString = new Scanner(s); // Scan one point
                        double y = scanAString.nextDouble();
                        double x = scanAString.nextDouble();
                        points.get(points.size() - 1).add(new Point2D(x, y));
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            return points;
        }

        // Get electoral vote for each state
        private HashMap<String, Integer> getElectoralTable() {
            HashMap<String, Integer> electoralTable = new HashMap<>();
            Pattern p = Pattern.compile(">(\\w+ ?\\w*)<");
            Pattern n = Pattern.compile(">(\\d+)<");
            Matcher m1, m2;
            try (Scanner input = new Scanner(new URL("http://state.1keydata.com/state-electoral-votes.php").openStream())) {
                while (input.hasNext()) {
                    m1 = p.matcher(input.nextLine());
                    if (m1.find()) {
                        m2 = n.matcher(input.nextLine());
                        if (m2.find()) {
                            electoralTable.put(m1.group(1), Integer.parseInt(m2.group(1)));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return electoralTable;
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
