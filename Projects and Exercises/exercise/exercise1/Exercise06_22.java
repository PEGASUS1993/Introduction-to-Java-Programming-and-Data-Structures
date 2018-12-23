public class Exercise06_22 {
  // Main method
  public static void main(String[] args) {
    System.out.print("Enter a positive number: ");
    java.util.Scanner input = new java.util.Scanner(System.in);
    double number = input.nextDouble();
    
    System.out.println(
      "The square root for " + number + " is " + SquareRoot.sqrt(number));
  }
}

// This class contains sqrt method
class SquareRoot {
  // Find the square root of the value
  public static double sqrt(double num) {
    double nextGuess = 1.0;
    double lastGuess;

    do {
      lastGuess = nextGuess;
      nextGuess = (lastGuess + (num / lastGuess)) * 0.5;
    } 
    while (Math.abs(nextGuess - lastGuess) >= 0.00001);

    return nextGuess;
  }
}
