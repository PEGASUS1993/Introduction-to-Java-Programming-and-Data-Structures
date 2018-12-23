import java.util.Scanner;

public class Exercise08_34 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    double[][] p = new double[6][2];    
    System.out.print("Enter " + p.length + " points: ");
    for (int i = 0; i < p.length; i++)
      for (int j = 0; j < p[i].length; j++) 
        p[i][j] = input.nextDouble();
    
    double[] point = getRightmostLowestPoint(p);
    
    System.out.println("The rightmost lowest point is (" + 
      point[0] + ", " + point[1] + ")");
  }
  
  public static double[] getRightmostLowestPoint(double[][] p) {
    int rightMostIndex = 0;
    double rightMostX = p[0][0];
    double rightMostY = p[0][1];
    
    for (int i = 1; i < p.length; i++) {
      if (rightMostY > p[i][1]) {
        rightMostY = p[i][1];
        rightMostIndex = i;
      }
      else if (rightMostY == p[i][1] && rightMostX < p[i][0]) {
        rightMostX = p[i][0];
        rightMostIndex = i;
      }
    }
  
    return new double[]{p[rightMostIndex][0], p[rightMostIndex][1]};
  }
}
