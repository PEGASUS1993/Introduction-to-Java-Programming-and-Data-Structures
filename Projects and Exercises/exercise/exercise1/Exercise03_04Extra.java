import java.util.Scanner;

public class Exercise03_04Extra {
  public static void main(String[] args) {
    // 1. Generate two random single-digit integers
    int number1 = (int)(Math.random() * 10);
    int number2 = (int)(Math.random() * 10);

    // 2. If number1 < number2, swap number1 with number2
    if (number1 < number2) {
      int temp = number1;
      number1 = number2;
      number2 = temp;
    }

    // 3. Prompt the student to answer "what is number1 – number2?"
    System.out.print
      ("What is " + number1 + " - " + number2 + "? ");
    Scanner input = new Scanner(System.in);
    int answer = input.nextInt();

    // 4. Grade the answer and display the result
    if (number1 - number2 == answer) {
      int choice = (int)(Math.random() * 3);
      switch (choice) {
        case 0: System.out.println("excellent"); break;
        case 1: System.out.println("correct"); break;
        case 2: System.out.println("way to go"); break;
      }
    }
    else {
      int choice = (int)(Math.random() * 3);
      switch (choice) {
        case 0:  System.out.println("incorrect"); break;
        case 1: System.out.println("wrong"); break;
        case 2: System.out.println("not right"); break;
      }
      System.out.println(number1 + " - " + number2 + 
        " should be " + (number1 - number2)); 
    }
  }
}
