import java.util.Scanner;

public class Exercise04_18 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter two characters: ");
    String s = input.nextLine();
    
    if (s.charAt(0) == 'M')
      System.out.print("Mathematics ");
    else if (s.charAt(0) == 'C')
      System.out.print("Computer Science ");
    else if (s.charAt(0) == 'I')
      System.out.print("Information Technology ");
    else {
      System.out.println("Invalid input: Wrong major code");
      System.exit(1);
    }

    if (s.charAt(1) == '1')
      System.out.println("Freshman");
    else if (s.charAt(1) == '2')
      System.out.println("Sophomore");
    else if (s.charAt(1) == '3')
      System.out.println("Junior");
    else if (s.charAt(1) == '4')
      System.out.println("Senior");
    else {
      System.out.println("Invalid input: wrong status code");
      System.exit(2);
    }
  }
}

