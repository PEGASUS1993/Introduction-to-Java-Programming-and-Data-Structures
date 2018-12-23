import java.util.Scanner;

public class Exercise06_21 {
  public static void main(String[] args) {       
    Scanner input = new Scanner(System.in);
    // Prompt the user to enter a string
    System.out.print("Enter a string: ");
    String s = input.nextLine();
    
    System.out.print(s + " is translated to ");
    for (int i = 0; i < s.length(); i++) {
      if (Character.isLetter(s.charAt(i)))
        System.out.print(getNumber(Character.toUpperCase(s.charAt(i))));
      else
        System.out.print(s.charAt(i));
    }
  }
  
  public static int getNumber(char uppercaseLetter) {
    int number = 0;
    switch (uppercaseLetter) {
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
    }

    return number;
  }
}
