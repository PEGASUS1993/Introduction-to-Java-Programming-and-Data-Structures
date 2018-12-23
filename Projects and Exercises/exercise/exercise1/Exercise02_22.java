public class Exercise02_22 {
  // Main method
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);
    // Receive the amount entered from the keyboard
    System.out.print(
      "Enter an amount in integer, for example 1156 \nfor 11 dollars and 56 cents: ");
    int amount = input.nextInt();

    int remainingAmount = amount;

    // Find the number of one dollars
    int numOfOneDollars = remainingAmount / 100;
    remainingAmount = remainingAmount % 100;

    // Find the number of quaters in the remaining amount
    int numOfQuarters = remainingAmount / 25;
    remainingAmount = remainingAmount % 25;

    // Find the number of dimes in the remaining amount
    int numOfDimes = remainingAmount / 10;
    remainingAmount = remainingAmount % 10;

    // Find the number of nickels in the remaining amount
    int numOfNickels = remainingAmount / 5;
    remainingAmount = remainingAmount % 5;

    // Find the number of pennies in the remaining amount
    int numOfPennies = remainingAmount;

    // Display results
    System.out.println("Your amount " + amount + " consists of ");
    System.out.println(numOfOneDollars + " dollars");
    System.out.println(numOfQuarters + " quarters");
    System.out.println(numOfDimes + " dimes");
    System.out.println(numOfNickels + " nickels");
    System.out.println(numOfPennies + " pennies");
  }
}
