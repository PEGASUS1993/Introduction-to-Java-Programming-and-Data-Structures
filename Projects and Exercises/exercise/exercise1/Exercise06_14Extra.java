import java.util.Scanner;

public class Exercise06_14Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter string s: ");
    String s = input.nextLine();

    System.out.print("Enter string s1: ");
    String s1 = input.nextLine();

    if (isPrefix(s, s1))
      System.out.println(s1 + " is a prefix of " + s);
    else
      System.out.println(s1 + " is not a prefix of " + s);
  }
  

  /** Return true if s1 is a prefix of s */
  public static boolean isPrefix(String s, String s1)
  {
    if (s1.length() > s.length())
      return false;
  
    for (int i = 0; i < s1.length(); i++)
      if (s.charAt(i) != s1.charAt(i))
        return false;
  
    return true;
  }
}

