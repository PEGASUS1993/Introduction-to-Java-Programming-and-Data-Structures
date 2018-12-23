public class Exercise07_06Extra {
  public static void main(String[] args) {
    // Check command-line arguments
    if (args.length != 1) {
      System.out.println(
        "Usage: java Exercise09_13 string");
      System.exit(1);
    }

    String s = args[0];

    if (isPalindrome(s)) {
      System.out.println(s + " is a palindrome");
    }
    else {
      System.out.println(s + " is not a palindrome");
    }
  }

  /** Check if a string is a palindrome */
  public static boolean isPalindrome(String s) {
    // The index of the first character in the string
    int low = 0;

    // The index of the last character in the string
    int high = s.length() - 1;

    while (low < high) {
      if (!equals(s.charAt(low), s.charAt(high)))
        return false; // Not a palindrome

      low++;
      high--;
    }

    return true; // The string is a palindrome
  }

  /** Return true if two letters are the same regardless case */
  public static boolean equals(char c1, char c2) {
    return lowercase(c1) == lowercase(c2);
  }

  public static char lowercase(char c) {
    if (c >= 'A' && c <= 'Z') {
      int offset = (int)'a' - (int)'A';
      char lowercase = (char)((int)c + offset);
      return lowercase;
    }
    else
      return c;
  }
}
