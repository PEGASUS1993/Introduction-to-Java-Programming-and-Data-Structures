public class Exercise06_05 {
  public static void main(String[] args) {
    // Prompt the user to enter three double values
    java.util.Scanner input = new java.util.Scanner(System.in);

    // Enter three numbers
    System.out.print("Enter three values: ");
    double num1 = input.nextDouble();
    double num2 = input.nextDouble();
    double num3 = input.nextDouble();
        
    // Invoke the displaySortedNumbers method to display the 
    // numbers in increasing order
    displaySortedNumbers(num1, num2, num3);
  }

  public static void displaySortedNumbers(
      double num1, double num2, double num3) {
    // Write the code to implement this method
    if (num1 > num2) {
      double temp = num1;
      num1 = num2;
      num2 = temp;
    }

    if (num2 > num3) {
      double temp = num2;
      num2 = num3;
      num3 = temp;
    }

    if (num1 > num2) {
      double temp = num1;
      num1 = num2;
      num2 = temp;
    }

    System.out.println("The sorted numbers are "
      + num1 + " " + num2 + " " + num3);
  }
} 
