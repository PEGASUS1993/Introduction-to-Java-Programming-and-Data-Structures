import java.util.Scanner;
import javafx.geometry.Point2D;

public class Exercise09_01Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    double[][] p = new double[4][2];    
    System.out.print("Enter x1, y1, x2, y2, x3, y3, x4, y4: ");
    for (int i = 0; i < p.length; i++)
      for (int j = 0; j < p[i].length; j++) 
        p[i][j] = input.nextDouble();

    Point2D p1 = new Point2D(p[0][0], p[0][1]);
    Point2D p2 = new Point2D(p[1][0], p[1][1]);
    Point2D p3 = new Point2D(p[2][0], p[2][1]);
    Point2D p4 = new Point2D(p[3][0], p[3][1]);
    
    Point2D result = getIntersectingPoint(p1, p2, p3, p4);
        
    if (result == null) {
      System.out.println("The two lines are parallel");
    }
    else {
      System.out.println("The intersecting point is at (" + 
        result.getX() + ", " +  result.getY() + ")");
    }
  }
  
  public static Point2D getIntersectingPoint(
      Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
    double x1 = p1.getX();
    double y1 = p1.getY();
    double x2 = p2.getX();
    double y2 = p2.getY();
    double x3 = p3.getX();
    double y3 = p3.getY();
    double x4 = p4.getX();
    double y4 = p4.getY();
    
    double[][] a = new double[2][2];
    double[] b = new double[2];
    
    a[0][0] = (y1 - y2);
    a[0][1] = -(x1 - x2);
    a[1][0] = (y3 - y4);
    a[1][1] = -(x3 - x4);
    b[0] = (y1 - y2) * x1 - (x1 -x2) * y1;
    b[1] = (y3 - y4) * x3 - (x3 -x4) * y3;

    double[] point = Exercise08_30.linearEquation(a, b);
    if (point == null) return null;
    return new Point2D(point[0], point[1]);
  }
}
