import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise18_35 extends Application {
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		HPane pane = new HPane();
		TextField tfOrder = new TextField();
		tfOrder.setOnAction(e -> pane.setOrder(Integer.parseInt(tfOrder.getText())));
		tfOrder.setPrefColumnCount(4);
		tfOrder.setAlignment(Pos.BOTTOM_RIGHT);

		// Pane to hold label, text field, and a button
		HBox hBox = new HBox(10);
		hBox.getChildren().addAll(new Label("Enter an order: "), tfOrder);
		hBox.setAlignment(Pos.CENTER);

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		borderPane.setBottom(hBox);

		// Create a scene and place it in the stage
		Scene scene = new Scene(borderPane, 200, 210);
		primaryStage.setTitle("Exercise18_35"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

		pane.widthProperty().addListener(ov -> pane.paint());
		pane.heightProperty().addListener(ov -> pane.paint());

	}

	/** Pane for displaying triangles */
	static class HPane extends Pane {
		private int order = 0;

		/** Set a new order */
		public void setOrder(int order) {
			this.order = order;
			paint();
		}

		protected void paint() {
			// (xCenter, yCenter) is the center of the H shape
			double xCenter = getWidth() / 2;
			double yCenter = getHeight() / 2;
			// Length of the a line
			double length = Math.min(getWidth(), getHeight()) / 2;

			this.getChildren().clear(); // Clear the pane before redisplay

			displayHTree(order, xCenter, yCenter, length);
		}

		private void displayHTree(int order, double xCenter, double yCenter, double length) {
			// Locate four endpoints
			Point2D p1 = new Point2D(xCenter - length / 2, yCenter - length / 2);
			Point2D p2 = new Point2D(xCenter + length / 2, yCenter - length / 2);
			Point2D p3 = new Point2D(xCenter - length / 2, yCenter + length / 2);
			Point2D p4 = new Point2D(xCenter + length / 2, yCenter + length / 2);

			// Draw three lines in an H-shape
			getChildren().add(new Line(p1.getX(), p1.getY(), p3.getX(), p3.getY()));
			getChildren().add(new Line(p2.getX(), p2.getY(), p4.getX(), p4.getY()));
			getChildren().add(new Line(xCenter - length / 2, yCenter, xCenter + length / 2, yCenter));

			if (order > 0) {
				// Recursively display H-tree
				displayHTree(order - 1, p1.getX(), p1.getY(), length / 2);
				displayHTree(order - 1, p2.getX(), p2.getY(), length / 2);
				displayHTree(order - 1, p3.getX(), p3.getY(), length / 2);
				displayHTree(order - 1, p4.getX(), p4.getY(), length / 2);
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
