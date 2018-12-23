public class Exercise03_14 {
  public static void main(String[] args) {
    // Obtain the random number 0 or 1
    int number = (int)(Math.random() * 2);

    // Prompt the user to enter a guess
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Guess head or tail? " +
      "Enter 0 for head and 1 for tail: ");
    int guess = input.nextInt();

    // Check the guess
    if (guess == number)
      System.out.println("Correct guess");
    else if (number == 0)
      System.out.println("Sorry, it is a head");
    else
      System.out.println("Sorry, it is a tail");
  }
}
