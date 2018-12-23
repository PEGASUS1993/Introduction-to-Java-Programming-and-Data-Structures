import java.util.Scanner;

public class Exercise18_25 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a string: ");
    String s = input.nextLine();
    System.out.println("The permutation for " + s + " is");
    displayPermuation(s);
  }

  public static void displayPermuation(String s) {
    displayPermuation("", s);
  }

  public static void displayPermuation(String s1, String s2) {
    if (s2.length() > 0) {
      for (int i = 0; i < s2.length(); i++) {
        displayPermuation(s1 + s2.charAt(i),
          s2.substring(0, i) + s2.substring(i + 1));
      }
    }
    else
      System.out.println(s1);
  }
}
