import java.util.Scanner;

public class Exercise05_48 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a string: ");
    String s = input.nextLine();

    for (int i = 1; i < s.length(); i += 2)
      System.out.print(s.charAt(i));
  
    System.out.println();
  }
}