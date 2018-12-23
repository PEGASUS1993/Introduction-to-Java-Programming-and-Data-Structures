public class Exercise07_04Extra {
  public static void main(String[] args) {
    // Prompt the user to enter a string
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter a string: ");
    String s = input.nextLine();

    int[] counts = count(s);

    for (int i = 0; i < 10; i++) {
      if (counts[i] > 0) 
        System.out.println("Digit " + i + " occurs " + counts[i] +
          (counts[i] > 1 ? " times" : " time"));
    }
  }

  public static int[] count(String s) {
    int[] counts = new int[10];

    for (int i = 0; i < s.length(); i++) {
      if (Character.isDigit(s.charAt(i))) {
        counts[s.charAt(i) - '0']++;
      }
    }

    return counts;
  }
}
