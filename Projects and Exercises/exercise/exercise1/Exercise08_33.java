import java.util.Scanner;

public class Exercise08_33 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    double[][] p = new double[4][2];    
    System.out.print("Enter x1, y1, x2, y2, x3, y3, x4, y4: ");
    for (int i = 0; i < p.length; i++)
      for (int j = 0; j < p[i].length; j++) 
        p[i][j] = input.nextDouble();

    double[][] points = new double[4][2];
    points[0][0] = p[0][0]; points[0][1] = p[0][1];
    points[1][0] = p[2][0]; points[1][1] = p[2][1];
    points[2][0] = p[1][0]; points[2][1] = p[1][1];
    points[3][0] = p[3][0]; points[3][1] = p[3][1];    
    double[] intersectingPoint = Exercise08_31.getIntersectingPoint(points);
            
    double[] areas = new double[4];
    double[][] trianglePoints = new double[3][2];   
    System.out.print("Intersecting point: (");
    System.out.printf("%.4f%2s%.4f%1s\n", intersectingPoint[0], ", ",
      intersectingPoint[1], ")");
    
    trianglePoints[0][0] = p[0][0]; trianglePoints[0][1] = p[0][1];
    trianglePoints[1][0] = p[1][0]; trianglePoints[1][1] = p[1][1];
    trianglePoints[2][0] = intersectingPoint[0]; trianglePoints[2][1] = intersectingPoint[1];    
    areas[0] = Exercise08_32.getTriangleArea(trianglePoints);
    
    trianglePoints[0][0] = p[2][0]; trianglePoints[0][1] = p[2][1];
    trianglePoints[1][0] = p[1][0]; trianglePoints[1][1] = p[1][1];   
    areas[1] = Exercise08_32.getTriangleArea(trianglePoints);

    trianglePoints[0][0] = p[2][0]; trianglePoints[0][1] = p[2][1];
    trianglePoints[1][0] = p[3][0]; trianglePoints[1][1] = p[3][1];   
    areas[2] = Exercise08_32.getTriangleArea(trianglePoints);

    trianglePoints[0][0] = p[0][0]; trianglePoints[0][1] = p[0][1];
    trianglePoints[1][0] = p[3][0]; trianglePoints[1][1] = p[3][1];    
    areas[3] = Exercise08_32.getTriangleArea(trianglePoints);

    System.out.print("The areas are ");
    java.util.Arrays.sort(areas);
    for (int i = 0; i < areas.length; i++)
      System.out.printf("%.4f ", areas[i]);
  }
}
