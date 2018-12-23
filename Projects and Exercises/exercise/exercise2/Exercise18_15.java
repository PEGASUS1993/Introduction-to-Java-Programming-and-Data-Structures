import java.util.Scanner;

public class Exercise18_15 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a string: ");
    String s = input.nextLine();
    System.out.print("Enter a character: ");
    char ch = input.nextLine().charAt(0);
    int times = count(s, ch);
    System.out.println(ch + " appears " + times + (times > 1 ? " times " : " time ") + "in " + s);
  }

  public static int count(String str, char a) {
    return count(str, a, str.length() - 1);
  }

  public static int count(String str, char a, int high) {
    int result = 0;
    if (high >= 0)
      result = count(str, a, high - 1) +
        ((str.charAt(high) == a) ? 1 : 0);

    return result;
  }
}
