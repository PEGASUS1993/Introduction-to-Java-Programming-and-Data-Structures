import java.util.stream.Stream;

public class Exercise30_12 {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter a number: ");
    int value = input.nextInt();

    System.out.println("The sum of digits for " + value + " is "
        + sumDigits(value));
  }

  public static int sumDigits(long n) {
    return Stream
        .of(Exercise30_10.toCharacterArray((n + "").toCharArray()))
        .mapToInt(ch -> (int) (ch - '0')).sum();
  }
}
