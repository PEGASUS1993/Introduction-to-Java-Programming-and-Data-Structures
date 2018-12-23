import java.util.*;

public class Exercise21_15 {

  public static void main(String[] args) {
    Set<Integer> set = new HashSet<>();
    
    int number1 = (int) (Math.random() * 10);
    int number2 = (int) (Math.random() * 10);

    // Create a Scanner
    Scanner input = new Scanner(System.in);
    System.out.print(
            "What is " + number1 + " + " + number2 + "? ");
    int answer = input.nextInt();
    set.add(answer);
      
    while (number1 + number2 != answer) {
      System.out.print(
              "Wrong. Try again. What is " + number1 + " + " + number2 + "? ");
      answer = input.nextInt();
      
      if (set.contains(answer)) {
        System.out.println("You already entered " + answer);
      }
      else {
        set.add(answer);
      }
    }
    
    System.out.println("You got it!");
  }
}
