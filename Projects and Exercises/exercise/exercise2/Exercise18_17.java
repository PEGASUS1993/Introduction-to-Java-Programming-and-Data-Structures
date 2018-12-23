import java.util.Scanner;

public class Exercise18_17 {
  public static void main(String[] args) {
    System.out.print("Enter a string: ");
    Scanner input = new Scanner(System.in);
    String s = input.nextLine();
    char[] items = s.toCharArray();
    
    System.out.print("Enter a character: ");
    char ch = input.nextLine().trim().charAt(0);
    
    System.out.println(ch + " appears " +
      count(items, ch) + " times");
  }

  public static int count(char[] chars, char ch) {
    return count(chars, ch, chars.length - 1);
  }

  public static int count(char[] chars, char ch, int high) {
    if (high >= 0) {
      return count(chars, ch, high - 1) +
        ((chars[high] == ch) ? 1 : 0);
    }
    else
      return 0;
  }
}
