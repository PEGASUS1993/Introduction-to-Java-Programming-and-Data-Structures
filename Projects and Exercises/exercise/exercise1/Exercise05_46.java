import java.util.Scanner;

public class Exercise05_46 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a string: ");
    String s = input.nextLine();
    
    String result = "";
    for (int i = 0; i < s.length(); i++) {
      result = s.charAt(i) + result;
    }
  
    System.out.println("The reversed string is " + result);
  }
}