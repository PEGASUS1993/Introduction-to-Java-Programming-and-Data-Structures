import java.util.Scanner;

public class Exercise04_19 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print(
      "Enter the first 9 digits of an ISBN as integer: ");
    String number = input.nextLine();

    if (number.length() != 9) {
      System.out.println("You need to enter exactly 9 digits");
      System.exit(1);
    }
      
    // Calculate checksum (You may write a loop to simplify it in Ch 5
    int checksum = ((number.charAt(0) - '0') * 1 +
        (number.charAt(1) - '0') * 2 +
        (number.charAt(2) - '0') * 3 +
        (number.charAt(3) - '0') * 4 +
        (number.charAt(4) - '0') * 5 +
        (number.charAt(5) - '0') * 6 +
        (number.charAt(6) - '0') * 7 +
        (number.charAt(7) - '0') * 8 +
        (number.charAt(8) - '0') * 9) % 11;
      
    System.out.print("The ISBN-10 number is " + number);

    if (checksum == 10)
      System.out.println("X");
    else
      System.out.println(checksum);
  }
}
