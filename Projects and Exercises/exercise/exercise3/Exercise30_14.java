import java.util.stream.Stream;

public class Exercise30_14 {
  public static void main(String[] args) {
    // Prompt the user to enter a string
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter a string: ");
    String s = input.nextLine();

    // Prompt the user to enter a character
    System.out.print("Enter a character: ");
    char ch = input.nextLine().charAt(0);

    int count = count(s, ch);
    System.out.println(count);
  }

  public static int count(String s, char ch) {
    return (int)Stream
        .of(Exercise30_10.toCharacterArray(s.toCharArray()))
        .filter(e -> e == ch).count();
  }
}
