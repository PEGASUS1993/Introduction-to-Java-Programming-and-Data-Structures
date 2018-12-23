public class Exercise05_16Extra {
  public static void main(String[] args) {
    int countPlayer1 = 0;
    int countPlayer2 = 0;

    for (int i = 0; i <= 1000000; i++) {
      int count = 0;
      while (count <= 1 && count >= -1) {
        // Generate scissor, rock, paper
        int computerNumber = (int)(Math.random() * 3);
  
        // Prompt the user to enter scissor, rock, or paper
//        java.util.Scanner input = new java.util.Scanner(System.in);
//        System.out.print("scissor (0), rock (1), paper (2): ");
        int userNumber = (int)(Math.random() * 3); // input.nextInt();
  
        // Check the guess
        switch (computerNumber) {
        case 0:
          if (userNumber == 0) {
//            System.out.println("It is a draw");
          }
          else if (userNumber == 1) {
//            System.out.println("You won");
            count++;
          }
          else if (userNumber == 2) {
//            System.out.println("You lost");
            count--;
          }
          break;
        case 1:
          if (userNumber == 0) {
//            System.out.println("You lost");
            count--;
          }
          else if (userNumber == 1) {
//            System.out.println("It is a draw");
          }
          else if (userNumber == 2) {
//            System.out.println("You won");
            count++;
          }
          break;
        case 2:
          if (userNumber == 0) {
//            System.out.println("You won");
            count++;
          }
          else if (userNumber == 1) {
//            System.out.println("You lost");
            count--;
          }
          else if (userNumber == 2) {
//            System.out.println("It is a draw");
          }
          break;
        }
      }
  
      if (count > 1) {
//        System.out.println("You won more than one time");
        countPlayer1++;
      }
      else {
//        System.out.println("The computer won more than one time");
        countPlayer2++;
      }
    }
    
    System.out.println("Player1 won " + countPlayer1 + " times");
    System.out.println("Player2 won " + countPlayer2 + " times");
  }
}
