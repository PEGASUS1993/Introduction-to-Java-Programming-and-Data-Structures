import java.util.Scanner;

public class Exercise08_32 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    double[][] points = new double[3][2];    
    System.out.print("Enter x1, y1, x2, y2, x3, y3: ");
    for (int i = 0; i < points.length; i++)
      for (int j = 0; j < points[i].length; j++) 
        points[i][j] = input.nextDouble();

    double area = getTriangleArea(points);
        
    if (area == 0) {
      System.out.println("The three points are on the same line");
    }
    else {
      System.out.println("The area of the triangle is " + area);
    }
  }
  
  public static double getTriangleArea(double[][] p) {
    double s1 = distance(p[0][0],  p[0][1], p[1][0], p[1][1]);
    double s2 = distance(p[0][0],  p[0][1], p[2][0], p[2][1]);
    double s3 = distance(p[2][0],  p[2][1], p[1][0], p[1][1]);
    double s = (s1 + s2 + s3) / 2;
    double area = s * (s - s1) * (s - s2) * (s - s3);
    
    return Math.sqrt(area);
  }
  
  public static double distance(double x1, double y1, double x2, double y2) {
    double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    return d;
  }
}
