import java.util.Scanner;

public class Exercise02_02Extra {
  public static void main(String[] args) {
    System.out.print("Enter the ball travel time in seconds: ");
    Scanner input = new Scanner(System.in);
    double t = input.nextDouble();
    final double g = 9.8;
    double d = g * t * t / 2;
    System.out.print("The height of the building is " + d + " meters ");
  }
}
