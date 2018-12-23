import java.util.Scanner;

public class Exercise02_03Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the friction force in Newtons: ");
    double f = input.nextDouble();
    System.out.print("Enter the object’s mass in kg: ");
    double m = input.nextDouble();
    System.out.print("Enter the object’s acceleration in m/s^2: ");
    double a = input.nextDouble();
    final double g = 9.8;
    double u = (f - m * a) / (m * g);
    System.out.print("The coefficient for friction is " + u);
  }
}
