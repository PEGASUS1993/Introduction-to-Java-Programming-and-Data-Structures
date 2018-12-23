public class Exercise06_07Extra {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter a string: ");
    String s = input.nextLine();
    System.out.println("The new string is " + swapCase(s));
  }
  
  public static String swapCase(String s) {
    StringBuilder sb = new StringBuilder(s);
    for (int i = 0; i < sb.length(); i++) {
      char ch = sb.charAt(i);
      if (Character.isUpperCase(ch)) 
        sb.replace(i, i + 1, Character.toLowerCase(ch) + "");
      else if (Character.isLowerCase(ch))   
        sb.replace(i, i + 1, Character.toUpperCase(ch) + "");
    }
    
    return sb.toString();
  }
}
