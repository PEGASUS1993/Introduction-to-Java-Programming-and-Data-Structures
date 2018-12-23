import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

public class Exercise18_19 extends Application {
	private int order = -1;

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		SierpinskiTrianglePane trianglePane = new SierpinskiTrianglePane();

		Button btDown = new Button("-");
		Button btUp = new Button("+");
		HBox hBox = new HBox(5);
		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(btDown, btUp);

		btUp.setOnAction(e -> trianglePane.setOrder(trianglePane.getOrder() + 1));
		btDown.setOnAction(e -> trianglePane.setOrder(trianglePane.getOrder() - 1));

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(trianglePane);
		borderPane.setBottom(hBox);

		// Create a scene and place it in the stage
		Scene scene = new Scene(borderPane, 200, 210);
		primaryStage.setTitle("Exercise18_19"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

		trianglePane.widthProperty().addListener(ov -> trianglePane.paint());
		trianglePane.heightProperty().addListener(ov -> trianglePane.paint());

		scene.setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.PRIMARY) {
				trianglePane.setOrder(trianglePane.getOrder() + 1);
			} else if (e.getButton() == MouseButton.SECONDARY) {
				trianglePane.setOrder(trianglePane.getOrder() - 1);
			}
		});

		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP) {
				trianglePane.setOrder(trianglePane.getOrder() + 1);
			} else if (e.getCode() == KeyCode.DOWN) {
				trianglePane.setOrder(trianglePane.getOrder() - 1);
			}
		});
	}

	/** Pane for displaying triangles */
	static class SierpinskiTrianglePane extends Pane {
		private int order = 0;

		public int getOrder() {
			return order;
		}

		/** Set a new order */
		public void setOrder(int order) {
			if (order < 0)
				order = 0;
			this.order = order;
			paint();
		}

		SierpinskiTrianglePane() {
		}

		protected void paint() {
			// Select three points in proportion to the panel size
			Point2D p1 = new Point2D(getWidth() / 2, 10);
			Point2D p2 = new Point2D(10, getHeight() - 10);
			Point2D p3 = new Point2D(getWidth() - 10, getHeight() - 10);

			this.getChildren().clear(); // Clear the pane before redisplay

			displayTriangles(order, p1, p2, p3);
		}

		private void displayTriangles(int order, Point2D p1, Point2D p2, Point2D p3) {
			if (order < 0)
				return;

			if (order == 0) {
				// Draw a triangle to connect three points
				Polygon triangle = new Polygon();
				triangle.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
				triangle.setStroke(Color.BLACK);
				triangle.setFill(Color.WHITE);

				this.getChildren().add(triangle);
			} else {
				// Get the midpoint on each edge in the triangle
				Point2D p12 = p1.midpoint(p2);
				Point2D p23 = p2.midpoint(p3);
				Point2D p31 = p3.midpoint(p1);

				// Recursively display three triangles
				displayTriangles(order - 1, p1, p12, p31);
				displayTriangles(order - 1, p12, p2, p23);
				displayTriangles(order - 1, p31, p23, p3);
			}
		}
	}

	/**
	 * The main method is only needed for the IDE with limited JavaFX support.
	 * Not needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
