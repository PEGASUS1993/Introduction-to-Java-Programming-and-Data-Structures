public class Exercise04_09 {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    // Enter an ASCII character
    System.out.print("Enter a character: ");
    char ch = input.next().charAt(0);

    // Display result
    System.out.println("The Unicode for the character " + ch + " is "
      + (int)ch);
  }
}