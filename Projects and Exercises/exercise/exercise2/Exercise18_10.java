import java.util.Scanner;

public class Exercise18_10 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a string: ");
    String s = input.nextLine();
    System.out.print("Enter a character: ");
    char ch = input.nextLine().charAt(0);
    int times = count(s, ch);
    System.out.println(ch + " appears " + times + 
      (times > 1 ? " times " : " time ") + "in " + s);
  }

  public static int count(String str, char a) {
    int result = 0;
    if (str.length() > 0)
      result = count(str.substring(1), a) +
        ((str.charAt(0) == a) ? 1 : 0);

    return result;
  }
}
