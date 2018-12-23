import java.util.Scanner;

public class Exercise05_09Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a string: ");
    String s = input.nextLine();
    
    int count = 0;
    for (int i = 0; i < s.length() - 1; i++)
      if (s.charAt(i) == s.charAt(i + 1))
        count++;

    System.out.println("The number of consecutive repeating characters is "
      + count);
  }
}