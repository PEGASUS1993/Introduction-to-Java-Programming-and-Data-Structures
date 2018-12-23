import java.util.Scanner;

public class Exercise04_15 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a letter: ");
    String s = input.nextLine();
    char ch = s.charAt(0);

    int number = 0;
    switch (Character.toUpperCase(ch)) {
      case 'A':
      case 'B':
      case 'C': number = 2; break;
      case 'D':
      case 'E':
      case 'F': number = 3; break;
      case 'G':
      case 'H':
      case 'I': number = 4; break;
      case 'J':
      case 'K':
      case 'L': number = 5; break;
      case 'M':
      case 'N':
      case 'O': number = 6; break;
      case 'P':
      case 'Q':
      case 'R':
      case 'S': number = 7; break;
      case 'T':
      case 'U':
      case 'V': number = 8; break;
      case 'W':
      case 'X':
      case 'Y':
      case 'Z': number = 9; break;
      default: System.out.println(ch + "is an invalid input ");
        System.exit(1);
    }

    System.out.println("The corresponding number is " + number);
  }
}


