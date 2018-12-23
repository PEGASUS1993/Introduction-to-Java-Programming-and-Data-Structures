public class Exercise06_05Extra {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter a string: ");
    String s = input.nextLine();
    System.out.println("The new string is " + title(s));
  }
  
  public static String title(String s) {
    StringBuilder sb = new StringBuilder(s);
    
    for (int i = 0; i < sb.length(); i++) {
      char ch = sb.charAt(i);
      if ((i == 0 && Character.isLowerCase(ch)) || (i > 0 && sb.charAt(i - 1) == ' ' && Character.isLowerCase(ch))) 
        sb.replace(i, i + 1, Character.toUpperCase(ch) + "");
    }
    
    return sb.toString();
  }
}
