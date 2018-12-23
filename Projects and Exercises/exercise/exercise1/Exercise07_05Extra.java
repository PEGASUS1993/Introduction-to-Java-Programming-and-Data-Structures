public class Exercise07_05Extra {
  public static void main(String[] args) {
    // Prompt the user to enter a string
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter the first string: ");
    String first = input.nextLine();

    System.out.print("Enter the second string: ");
    String second = input.nextLine();
    System.out.println(
      first + " and " + second + " are " +
      (isAnagram(first, second) ? "anagram." : "not anagram."));
  }

  public static boolean isAnagram(String s1, String s2) {
    String newS1 = Exercise07_34.sort(s1);
    String newS2 = Exercise07_34.sort(s2);

    if (newS1.length() != newS2.length()) return false;

    for (int i = 0; i < newS1.length(); i++) {
      if (newS1.charAt(i) != newS2.charAt(i))
        return false;
    }

    return true;
  }
}
