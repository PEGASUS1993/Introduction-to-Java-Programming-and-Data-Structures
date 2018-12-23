import java.util.Scanner;

public class Exercise03_31 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the exchange rate from dollars to RMB: ");
    double rate = input.nextDouble();

    System.out.print("Enter 0 to convert dollars to RMB and 1 vice versa: ");
    int conversionType = input.nextInt();

    if (conversionType == 0) {
      System.out.print("Enter the dollar amount: ");
      double dollars = input.nextDouble();
      double RMB = dollars * rate;
      System.out.println("$" + dollars + " is " + RMB + " Yuan");
    } 
    else if (conversionType == 1) {
      System.out.print("Enter the RMB amount: ");
      double RMB = input.nextDouble();
      double dollars = RMB / rate;
      System.out.println(RMB + " Yuan" + " is " + "$" + dollars);
    } 
    else {
      System.out.println("Incorrect input");
    }
  }
}
