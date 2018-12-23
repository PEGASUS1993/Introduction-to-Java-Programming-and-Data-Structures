import java.util.Scanner;

public class Exercise18_16 {
  public static void main(String[] args) {
    System.out.print("Enter a string: ");
    Scanner input = new Scanner(System.in);
    String s = input.nextLine();
    char[] items = s.toCharArray();
    System.out.println("The number of uppercase letters is " +
      count(items));
  }

  public static int count(char[] chars) {
    return count(chars, chars.length - 1);
  }

  public static int count(char[] chars, int high) {
    if (high >= 0) {
      return count(chars, high - 1) +
        (Character.isUpperCase(chars[high]) ? 1 : 0);
    }
    else
      return 0;
  }
}
