import java.util.Scanner;

public class Exercise02_14 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    // Prompt the user to enter weight in pounds
    System.out.print("Enter weight in pounds: ");
    double weight = input.nextDouble();
    
    // Prompt the user to enter height in inches
    System.out.print("Enter height in inches: ");
    double height = input.nextDouble();
    
    double bmi = weight * 0.45359237 / (height * 0.0254 * height * 0.0254);
    
    System.out.print("BMI is " + bmi);
  }
}
