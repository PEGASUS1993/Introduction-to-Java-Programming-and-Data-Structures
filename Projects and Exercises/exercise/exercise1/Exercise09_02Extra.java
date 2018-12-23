import java.util.Scanner;

import javafx.geometry.Point2D;

public class Exercise09_02Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    double[][] points = new double[3][2];    
    System.out.print("Enter x1, y1, x2, y2, x3, y3: ");
    for (int i = 0; i < points.length; i++)
      for (int j = 0; j < points[i].length; j++) 
        points[i][j] = input.nextDouble();
    
    Point2D p1 = new Point2D(points[0][0], points[0][1]);
    Point2D p2 = new Point2D(points[1][0], points[1][1]);
    Point2D p3 = new Point2D(points[2][0], points[2][1]);

    Point2D result = getCenterPoint(p1, p2, p3);
        
    if (result == null) {
      System.out.println("The three points are on the same line");
    }
    else {
      System.out.println("The intersecting point is at (" + 
          result.getX() + ", " +  result.getY() + ")");
    }
  }
  
  public static Point2D getCenterPoint(
      Point2D p1, Point2D p2, Point2D p3) {
    Point2D p11 = p2.midpoint(p3);
    Point2D p22 = p1.midpoint(p3);
    return Exercise09_01Extra.getIntersectingPoint(p1, p11, p2, p22);
  }
}
