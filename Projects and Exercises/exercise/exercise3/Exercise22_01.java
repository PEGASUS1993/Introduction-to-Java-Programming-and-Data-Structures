public class Exercise22_01 {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter a string: ");
    String s = input.nextLine();
    System.out.println("Maximum consecutive substring is " + maxConsecutiveSortedSubstring(s));
  }

  /**
   * The worst-case complexity is O(n^2), where n is s.length(). Can you improve
   * the algorithm?
   */
  public static String maxConsecutiveSortedSubstring(String s) {
    int[] maxConsecutiveLength = new int[s.length()];
    int current = 0;
    for (int i = 1; i < s.length(); i++) {
      if (s.charAt(i) <= s.charAt(i - 1)) { // next character is smaller
        current = i;
      } else {
        // s[current], s[current+1], ..., s[i-1] is a maximal increasing
        // sequence
        for (int j = i - 1; j >= current; j--)
          maxConsecutiveLength[j]++;
      }
    }

    // maxConsecutiveLength[i] denotes the length of a max sequence starting at
    // index i
    int currentMaxLength = maxConsecutiveLength[0];
    int index = 0;
    for (int i = 0; i < s.length(); i++) {
      if (maxConsecutiveLength[i] > currentMaxLength) {
        currentMaxLength = maxConsecutiveLength[i];
        index = i;
      }
    }

    return s.substring(index, index + currentMaxLength + 1);
  }

  /* O(n) version, 9/21/2009 */
  public static String maxConsecutiveSortedSubstring1(String s) {
    int currentMaxLength = 1;
    int lastIndexOfMaxConsecutiveSortedSubstring = 0;

    int possibleMaxLength = 1;
    for (int i = 1; i < s.length(); i++) {
      if (s.charAt(i) > s.charAt(i - 1)) {
        if (lastIndexOfMaxConsecutiveSortedSubstring == i - 1) {
          // Add s(i) into the max consecutive substring
          currentMaxLength++;
          lastIndexOfMaxConsecutiveSortedSubstring++;
        } else {
          possibleMaxLength++;
          if (possibleMaxLength > currentMaxLength) {
            currentMaxLength = possibleMaxLength;
            lastIndexOfMaxConsecutiveSortedSubstring = i;
            possibleMaxLength = 1;
          }
        }
      }
    }

    return s.substring(lastIndexOfMaxConsecutiveSortedSubstring
        - currentMaxLength + 1, lastIndexOfMaxConsecutiveSortedSubstring + 1);
  }
}
