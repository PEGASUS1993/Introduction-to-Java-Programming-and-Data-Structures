public class Exercise05_32 {
  public static void main(String[] args) {
    // Generate the first digit
    int first = (int)(Math.random() * 10);

    // Generate the second digit
    int second = (int)(Math.random() * 10);;

    while (first == second) {
      second = (int)(Math.random() * 10);
    }

    // Prompt the user to enter a guess
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter your two digits lottery pick: ");

    int guess = input.nextInt();

    // Check the guess
    if (guess / 10 == first && guess % 10 == second) {
      System.out.println("Exact match: you win $10,000");
    }
    else if (guess % 10 == first && guess / 10 == second) {
      System.out.println("Match all digits: you win $3,000");
    }
    else if (guess % 10 == first || guess % 10 == second
      || guess / 10 == first || guess / 10 == second) {
      System.out.println("Match one digit: you win $1,000");
    }
    else {
      System.out.println("Sorry, no match");
    }
    
    System.out.println("Lottery is " + first + second);
  }
}
