import java.util.Scanner;

public class Exercise03_12 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    System.out.print("Enter a three-digit integer: ");
    int number = input.nextInt();

    if (number / 100 == number % 10)
      System.out.println(number + " is a palindrome");
    else 
      System.out.println(number + " is not a palindrome");
  }
}
