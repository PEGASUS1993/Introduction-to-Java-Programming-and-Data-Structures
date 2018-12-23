import java.util.Scanner;

public class Exercise04_08Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a floating point number: ");
    double v = input.nextDouble();
    
    String s = "" + v;
    int k = s.indexOf(".");
    if (k < 0)
      System.out.println("The number " + v + " has no decimal point");
    else 
      System.out.println("The number of digits before the decimal point in " + v 
        + " is " + s.substring(0, k).length());
  }
}