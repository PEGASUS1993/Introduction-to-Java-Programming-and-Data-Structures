import java.util.*;

public class Exercise03_10 {
  public static void main(String[] args) {
    // 1. Generate two random single-digit integers
    int number1 = (int)(Math.random() * 100);
    int number2 = (int)(Math.random() * 100);

    // 2. Prompt the student to answer "what is number1 + number2?"
    System.out.print("What is " + number1 + " + " + number2 + "? ");
    Scanner input = new Scanner(System.in);
    int answer = input.nextInt();

    // 4. Grade the answer and display the result
    String replyString;
    if (number1 + number2 == answer)
      replyString = "You are correct!";
    else
      replyString = "Your answer is wrong.\n" + number1 + " + "
        + number2 + " should be " + (number1 + number2);
    
    System.out.println(replyString);
  }
}
