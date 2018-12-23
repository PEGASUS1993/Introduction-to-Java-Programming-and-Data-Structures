import java.util.Scanner;

public class Exercise08_15 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    double[][] points = new double[5][2];    
    System.out.print("Enter five points: ");
    for (int i = 0; i < points.length; i++)
      for (int j = 0; j < points[i].length; j++) 
        points[i][j] = input.nextDouble();
        
    if (sameLine(points)) {
      System.out.println("The five points are on the same line");
    }
    else {
      System.out.println("The five points are not on the same line");
    }
  }
  
  public static boolean sameLine(double[][] points) {
    for (int i = 2; i < points.length; i++) {
      if (!Exercise06_39.onTheSameLine(points[0][0], points[0][1], 
          points[1][0], points[1][1], points[i][0], points[i][1])) {
        return false;
      }
    }

    return true;
  }
}
