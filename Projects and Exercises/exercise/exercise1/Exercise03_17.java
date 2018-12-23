public class Exercise03_17 {
  public static void main(String[] args) {
    // Generate scissor, rock, paper
    int computerNumber = (int)(Math.random() * 3);

    // Prompt the user to enter scissor, rock, or paper
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("scissor (0), rock (1), paper (2): ");
    int userNumber = input.nextInt();

    // Check the guess
    switch (computerNumber) {
      case 0:
        if (userNumber == 0)
          System.out.print("The computer is scissor. You are scissor too. It is a draw");
        else if (userNumber == 1)
          System.out.print("The computer is scissor. You are rock. You won");
        else if (userNumber == 2)
          System.out.print("The computer is scissor. You are paper. You lost");
        break;
      case 1:
        if (userNumber == 0)
          System.out.print("The computer is rock. You are scissor. You lost");
        else if (userNumber == 1)
          System.out.print("The computer is rock. You are rock too. It is a draw");
        else if (userNumber == 2)
          System.out.print("The computer is rock. You are paper. You won");
        break;
      case 2:
        if (userNumber == 0)
          System.out.print("The computer is paper. You are scissor. You won");
        else if (userNumber == 1)
          System.out.print("The computer is paper. You are rock. You lost");
        else if (userNumber == 2)
          System.out.print("The computer is paper. You are paper too. It is a draw");
        break;
      }
  }
}
