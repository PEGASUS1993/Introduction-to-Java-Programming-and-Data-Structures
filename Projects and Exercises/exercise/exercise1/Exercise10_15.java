import java.util.Scanner;

public class Exercise10_15 {
  public static void main(String[] args) {
    double[][] points = new double[5][2];
    
    Scanner input = new Scanner(System.in);
    System.out.print("Enter " + points.length + " points: ");
    for (int i = 0; i < points.length; i++) {
      points[i][0] = input.nextDouble();
      points[i][1] = input.nextDouble();
    }
     
    MyRectangle2D boundingRectangle = getRectangle(points);
    
    System.out.println("The bounding rectangle's center (" + boundingRectangle.getX() + ", " + 
      boundingRectangle.getY() + "), width " + boundingRectangle.getWidth() + ", height " +
      boundingRectangle.getHeight());
  }

  public static MyRectangle2D getRectangle(double[][] points) {
    double minX = minX(points);
    double minY = minY(points);
    double maxX = maxX(points);
    double maxY = maxY(points);
    
    return new MyRectangle2D( (minX + maxX) / 2, (minY + maxY) / 2, maxX - minX, maxY - minY); 
  }
  
  private static double minX(double[][] points) {
    double minX = points[0][0];
    
    for (int i = 0; i < points.length; i++)
      if (minX > points[i][0])
        minX = points[i][0];
    
    return minX;
  }
  
  private static double maxX(double[][] points) {
    double maxX = points[0][0];
    
    for (int i = 0; i < points.length; i++)
      if (maxX < points[i][0])
        maxX = points[i][0];
    
    return maxX;
  }
  
  private static double minY(double[][] points) {
    double minY = points[0][1];
    
    for (int i = 0; i < points.length; i++)
      if (minY > points[i][1])
        minY = points[i][1];
    
    return minY;
  }
  
  private static double maxY(double[][] points) {
    double maxY = points[0][1];
    
    for (int i = 0; i < points.length; i++)
      if (maxY < points[i][1])
        maxY = points[i][1];
    
    return maxY;
  }
}
