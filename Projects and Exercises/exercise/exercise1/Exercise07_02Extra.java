import java.util.Scanner;

public class Exercise07_02Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the coordinates of six points: ");
    double x[] = new double[6];
    double y[] = new double[6];
    for (int i = 0; i < x.length; i++) {
      x[i] = input.nextDouble();
      y[i] = input.nextDouble();
    }
    
    double total = 0;
    for (int i = 1; i < x.length - 1; i++) {
      total += getArea(x[0], y[0], x[i], y[i], x[i + 1], y[i + 1]);
    }
    
    System.out.println("The total area is " + total);
  }
  
  public static double getArea(double x1, double y1, double x2, double y2, double x3, double y3) {
    double s1 = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 -y2));
    double s2 = Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 -y3));
    double s3 = Math.sqrt((x3 - x2) * (x3 - x2) + (y3 - y2) * (y3 -y2));
    
    double s = (s1 + s2 + s3) / 2;
    return Math.sqrt(s * (s - s1) * (s - s2) * (s - s3));
  }
}
