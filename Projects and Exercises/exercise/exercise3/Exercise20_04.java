import java.util.*;
import javafx.geometry.Point2D;

/** This program works, but the design is not good. Redesign it */
public class Exercise20_04 {
  public static void main(String[] args) {
    Point2D[] points = new Point2D[100];
    for (int i = 0; i < points.length; i++) {
      points[i] = new Point2D(Math.random() * 100, Math.random() * 100);
    }

    Arrays.sort(points, Comparator.comparing(Point2D::getY).thenComparing(Point2D::getX));
    for (int i = 0; i < 5; i++) {
      System.out.printf("%.2f, %.2f\n", points[i].getX(), points[i].getY());
    }
  }
}
