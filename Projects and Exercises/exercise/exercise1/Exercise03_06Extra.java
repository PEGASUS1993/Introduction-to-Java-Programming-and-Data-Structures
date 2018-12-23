import java.util.Scanner;

public class Exercise03_06Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a temperature: ");
    double temperature = input.nextDouble();

    if (temperature < 30)
      System.out.println("too cold");
    else if (temperature >= 100)
      System.out.println("too hot");
    else 
      System.out.println("just right");
  }
}
