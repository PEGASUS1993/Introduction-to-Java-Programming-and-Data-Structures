public class Exercise05_04Extra {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    while (true) {
      System.out.println("Main menu");
      System.out.println("1: Addition");
      System.out.println("2: Subtraction");
      System.out.println("3: Multiplication");
      System.out.println("4: Division");
      System.out.println("5: Exit");
      System.out.print("Enter a choice: ");
      int choice = input.nextInt();
  
      int number1 = (int)(Math.random() * 10);
      int number2 = (int)(Math.random() * 10);
      int answer;
  
      if (choice == 1) {
        System.out.print("What is " + number1 + " + " + number2 + "? ");
        answer = input.nextInt();
        if (number1 + number2 == answer)
          System.out.println("Correct");
        else
          System.out.println("Your answer is wrong. The correct answer is " 
            + (number1 + number2));
      }
      else if (choice == 2) {
        if (number1 < number2) { // Swap number1 with number2
          int temp = number1;
          number1 = number2;
          number2 = temp;
        }
  
        System.out.print("What is " + number1 + " - " + number2 + "? ");
        answer = input.nextInt();
        if (number1 - number2 == answer)
          System.out.println("Correct");
        else
          System.out.println("Your answer is wrong. The correct answer is " 
            + (number1 - number2));
      }
      else if (choice == 3) {
        System.out.print("What is " + number1 + " * " + number2 + "? ");
        answer = input.nextInt();
        if (number1 * number2 == answer)
          System.out.println("Correct");
        else
          System.out.println("Your answer is wrong. The correct answer is " 
            + (number1 * number2));
      }
      else if (choice == 4) {
        while (number2 == 0) 
          number2 = (int)(Math.random() * 10);
  
        System.out.println("What is " + number1 + " / " + number2 + "? ");
        answer = input.nextInt();
        if (number1 / number2 == answer)
          System.out.println("Correct");
        else
          System.out.println("Your answer is wrong. The correct answer is " 
              + (number1 / number2));
      }
      else if (choice == 5)
        break;
    }
  }
}
