public class Exercise07_34 {
  public static void main(String[] args) {
    // Prompt the user to enter a string
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter a string: ");
    String s = input.nextLine();
    System.out.println("Sorted string is " + sort(s));
  }

  public static String sort(String s) {
    char[] buffer = s.toCharArray();
    java.util.Arrays.sort(buffer);
    return new String(buffer);
  }
}
