import java.util.Scanner;

public class Exercise08_31 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    double[][] p = new double[4][2];    
    System.out.print("Enter x1, y1, x2, y2, x3, y3, x4, y4: ");
    for (int i = 0; i < p.length; i++)
      for (int j = 0; j < p[i].length; j++) 
        p[i][j] = input.nextDouble();

    double[] result = getIntersectingPoint(p);
        
    if (result == null) {
      System.out.println("The two lines are parallel");
    }
    else {
      System.out.println("The intersecting point is at (" + 
        result[0] + ", " +  result[1] + ")");
    }
  }
  
  public static double[] getIntersectingPoint(double[][] p) {
    double x1 = p[0][0];
    double y1 = p[0][1];
    double x2 = p[1][0];
    double y2 = p[1][1];
    double x3 = p[2][0];
    double y3 = p[2][1];
    double x4 = p[3][0];
    double y4 = p[3][1];
    
    double[][] a = new double[2][2];
    double[] b = new double[2];
    
    a[0][0] = (y1 - y2);
    a[0][1] = -(x1 - x2);
    a[1][0] = (y3 - y4);
    a[1][1] = -(x3 - x4);
    b[0] = (y1 - y2) * x1 - (x1 -x2) * y1;
    b[1] = (y3 - y4) * x3 - (x3 -x4) * y3;

    return Exercise08_30.linearEquation(a, b);
  }
}
