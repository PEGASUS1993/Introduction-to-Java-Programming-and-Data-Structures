public class Exercise05_15Extra {
  public static void main(String[] args) {
    int countUser = 0;
    int countComputer = 0;

    while (countUser < 2 && countComputer < 2) {
      // Generate scissor, rock, paper
      int computerNumber = (int)(Math.random() * 3);

      // Prompt the user to enter scissor, rock, or paper
      java.util.Scanner input = new java.util.Scanner(System.in);
      System.out.print("scissor (0), rock (1), paper (2): ");
      int userNumber = input.nextInt();

      // Check the guess
      switch (computerNumber) {
      case 0: // scissor
        if (userNumber == 0) {
          System.out.println("It is a draw");
          countUser = 0; countComputer = 0;
        }
        else if (userNumber == 1) {
          System.out.println("You won");
          countUser++; countComputer = 0;
        }
        else if (userNumber == 2) {
          System.out.println("You lost");
          countUser = 0; countComputer++;
        }
        break;
      case 1: // rock
        if (userNumber == 0) {
          System.out.println("You lost");
          countUser = 0; countComputer++;
        }
        else if (userNumber == 1) {
          System.out.println("It is a draw");
          countUser = 0; countComputer = 0;
        }
        else if (userNumber == 2) {
          System.out.println("You won");
          countUser++; countComputer = 0;
        }
        break;
      case 2: // paper
        if (userNumber == 0) {
          System.out.println("You won");
          countUser++; countComputer = 0;
        }
        else if (userNumber == 1) {
          System.out.println("You lost");
          countUser = 0; countComputer++;
        }
        else if (userNumber == 2) {
          System.out.println("It is a draw");
          countUser = 0; countComputer = 0;
        }
        break;
      }
    }

    if (countUser == 2) {
      System.out.println("You won more two consectutive times");
    }
    else {
      System.out.println("The computer won two consecutive times");
    }
  }
}