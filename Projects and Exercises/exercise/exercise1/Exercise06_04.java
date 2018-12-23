public class Exercise06_04 {
  public static void main(String[] args) {
    System.out.print("Enter an integer: ");
    java.util.Scanner input = new java.util.Scanner(System.in);
    int number = input.nextInt();
    reverse(number);
  }

  public static void reverse(int number) {
    while (number != 0) {
      int remainder = number % 10;
      System.out.print(remainder);
      number = number / 10;
    }

    System.out.println();
  }
}
