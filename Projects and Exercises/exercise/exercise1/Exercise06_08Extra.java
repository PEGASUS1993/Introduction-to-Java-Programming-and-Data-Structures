public class Exercise06_08Extra {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter a string s: ");
    String s = input.nextLine();
    System.out.print("Enter an integer b: ");
    int b = input.nextInt();

    System.out.println("The hashCode for " + s + " is " + 
      hashCode(s, b));
  }
  
  public static int hashCode(String s, int b) {
    int hashCode = 0;
    for (int i = 0; i < s.length(); i++) {
      hashCode = hashCode * b + s.charAt(i);
    }
    
    return hashCode;
  }
}
