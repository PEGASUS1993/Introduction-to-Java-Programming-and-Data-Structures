public class Exercise22_03 {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    System.out.print("Enter a string s1: ");
    String s1 = input.nextLine();
    System.out.print("Enter a string s2: ");
    String s2 = input.nextLine();
    
    int index = match1(s1, s2);
    if (index >= 0)
      System.out.println("matched at index " + index);
    else
      System.out.println("unmatched");   
  }

	/**
	 * The worst-case complexity is O(n), where n is s.length()
	 *
	 * @param s String
	 * @param pattern String
	 * @return int
	 */
  public static int match(String s, String pattern) {
	int k = 0;
	for (int i = 0; i < s.length(); i++) {
	  if (k == pattern.length())
	    return i - pattern.length();
	  else {
        if (s.charAt(i) == pattern.charAt(k))
          k++;
		else
		  k = 0;
      }
	}

    if (k == pattern.length())
      return s.length() - pattern.length();
    else
  	  return -1;
  }
  
	/**
	 * This is a brute-force approach, O(|s|*|pattern|))
	 */
  public static int match1(String s, String pattern) {
	for (int i = 0; i < s.length(); i++) {
	  // Check if pattern matches si, si+1, ...
	  int k = 0;		
	  for ( ; k < pattern.length(); k++) {
		if (s.charAt(i + k) != pattern.charAt(k))
		  break;	
	  }
	  
	  if (k == pattern.length())
		return i;  	  
	}
	
	return -1;
  }
}
