public class Exercise06_18 {
  public static void main(String[] args) {
    // Prompt the user to enter a password
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter a string for password: ");
    String s = input.nextLine();

    if (isValidPassword(s)) {
      System.out.println("Valid Password");
    }
    else {
      System.out.println("Invalid Password");
    }
  }

  /** Check if a string is a valid password */
  public static boolean isValidPassword(String s) {
    // Only letters and digits?
    for (int i = 0; i < s.length(); i++) {
      if (!Character.isLetter(s.charAt(i)) && 
          !Character.isDigit(s.charAt(i)))
        return false;
    }
    
    // Check length
    if (s.length() < 8)
      return false;
    
    // Count the number of digits
    int count = 0;
    for (int i = 0; i < s.length(); i++) {
      if (Character.isDigit(s.charAt(i)))
        count++;
    }
    
    if (count >= 2)
      return true;
    else 
      return false;
  }
}
