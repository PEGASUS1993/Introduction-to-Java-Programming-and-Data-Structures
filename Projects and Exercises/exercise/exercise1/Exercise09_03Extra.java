import java.util.Scanner;

import javafx.geometry.Point2D;

public class Exercise09_03Extra {
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
    double area = getTriangleArea(p1, p2, p3);
        
    if (area == 0) {
      System.out.println("The three points are on the same line");
    }
    else {
      System.out.println("The area of the triangle is " + area);
    }
  }
  
  public static double getTriangleArea(
      Point2D p1, Point2D p2, Point2D p3) {
    double s1 = p1.distance(p2);
    double s2 = p2.distance(p3);
    double s3 = p3.distance(p1);
    double s = (s1 + s2 + s3) / 2;
    double area = s * (s - s1) * (s - s2) * (s - s3);
    
    return Math.sqrt(area);
  }
}
