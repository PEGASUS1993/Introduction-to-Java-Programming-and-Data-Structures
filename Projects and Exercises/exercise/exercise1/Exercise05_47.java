import java.util.Scanner;

public class Exercise05_47 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the first 12-digit of an ISBN number as a string: ");
    String s = input.nextLine();

    if (s.length() != 12) {
      System.out.println(s + " is an invalid input");
      System.exit(1);
    }
  
    // Calculate checksum
    int sum = 0;
    for (int i = 0; i < s.length(); i++)
      if (i % 2 == 0)
        sum += (s.charAt(i) - '0');
      else
        sum += (s.charAt(i) - '0') * 3;
  
    int checksum = 10 - sum % 10;
  
    System.out.println("The ISBN number is " + s +
        (checksum == 10 ? "0" : checksum));
  }
}
