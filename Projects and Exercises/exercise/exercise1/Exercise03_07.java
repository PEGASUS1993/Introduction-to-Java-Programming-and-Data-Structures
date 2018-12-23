/** Break down an amount into smaller units
 *  Display the non-zero denominations only, and display singular
 *  words for single units like 1 dollars, 1 penny, and display plural
 *  words for more than one unit like 2 dollars, 3 pennies.
 */
public class Exercise03_07 {
  // Main method
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    // Receive the amount entered from the keyboard
    System.out.print(
      "Enter an amount in double, for example 11.56: ");
    double amount = input.nextDouble();

    int remainingAmount = (int)(amount * 100);

    // Find the number of one dollars
    int numberOfOneDollars = remainingAmount / 100;
    remainingAmount = remainingAmount % 100;

    // Find the number of quarters in the remaining amount
    int numberOfQuarters = remainingAmount / 25;
    remainingAmount = remainingAmount % 25;

    // Find the number of dimes in the remaining amount
    int numberOfDimes = remainingAmount / 10;
    remainingAmount = remainingAmount % 10;

    // Find the number of nickels in the remaining amount
    int numberOfNickels = remainingAmount / 5;
    remainingAmount = remainingAmount % 5;

    // Find the number of pennies in the remaining amount
    int numberOfPennies = remainingAmount;

    // Display results
    if (amount < 0) {
       System.out.println("Your amount is negative");
       System.exit(1);
    }
    else if (amount < 0) {
       System.out.println("Your amount is zero");
       System.exit(2);
    }

    System.out.println("Your amount " + amount + " consists of ");

    if (numberOfOneDollars > 1)
      System.out.println(numberOfOneDollars + "\t dollars");
    else if (numberOfOneDollars == 1)
      System.out.println(numberOfOneDollars + "\t dollar");

    if (numberOfQuarters > 1)
      System.out.println(numberOfQuarters + "\t quarters");
    else if (numberOfQuarters == 1)
      System.out.println(numberOfQuarters + "\t quarter");

    if (numberOfDimes > 1)
      System.out.println(numberOfDimes + "\t dimes");
    else if (numberOfDimes == 1)
      System.out.println(numberOfDimes + "\t dime");

    if (numberOfNickels > 1)
      System.out.println(numberOfNickels + "\t nickels");
    else if (numberOfNickels == 1)
      System.out.println(numberOfNickels + "\t nickel");

    if (numberOfPennies > 1)
      System.out.println(numberOfPennies + "\t pennies");
    else if (numberOfPennies == 1)
      System.out.println(numberOfPennies + "\t penny");
  }
}
