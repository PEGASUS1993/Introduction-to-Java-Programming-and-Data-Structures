import java.util.Scanner;

public class Exercise05_36 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Prompt the user to enter an integer
    System.out.print("Enter the first 9-digit of an ISBN number as integer: ");
    int number = input.nextInt();

    // Calculate checksum
    int t = number;
    int i = 9;
    int sum = 0;

    while (t != 0) {
      sum += (t % 10) * i;
      i--;
      t = t / 10;
    }

    int checksum = sum % 11;

    System.out.print("The ISBN number is ");

    // Display leading zeros
    int temp = 100000000;
    while (number / temp == 0) {
      System.out.print("0");
      temp = temp / 10;
    }

    System.out.print(number);

    if (checksum == 10)
      System.out.print("X");
    else
      System.out.print(checksum);
  }
}
