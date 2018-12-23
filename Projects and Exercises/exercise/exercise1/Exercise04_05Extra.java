import java.util.Scanner;

public class Exercise04_05Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a string: ");
    
    String s = input.nextLine();
    
    if (s.length() > 0)
      System.out.println("The last character is " + s.charAt(s.length() - 1));
    else 
      System.out.println("An empty string");
  }

}
