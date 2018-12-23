import java.util.Scanner;

public class Exercise02_13 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter monthly saving amount: ");
    double monthlyDeposit = input.nextDouble();
    
    double currentValue = monthlyDeposit;

    // First month value
    currentValue = currentValue * (1 + 0.00417);
    System.out.println("After the first month, the account value is " + currentValue);

    // Second month value
    currentValue = (currentValue + monthlyDeposit) * (1 + 0.05 / 12);
    System.out.println("After the second month, the account value is " + currentValue);

    // Third month value
    currentValue = (currentValue + monthlyDeposit) * (1 + 0.05 / 12);
    System.out.println("After the third month, the account value is " + currentValue);

    // Fourth month value
    currentValue = (currentValue + monthlyDeposit) * (1 + 0.05 / 12);

    // Fifth month value
    currentValue = (currentValue + monthlyDeposit) * (1 + 0.05 / 12);

    // Sixth month value
    currentValue = (currentValue + monthlyDeposit) * (1 + 0.05 / 12);

    System.out.println("After the sixth month, the account value is " + currentValue);
  }
}
