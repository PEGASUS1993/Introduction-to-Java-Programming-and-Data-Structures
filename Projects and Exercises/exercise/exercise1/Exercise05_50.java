public class Exercise05_50 {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    System.out.print("Enter a string: ");
    String s = input.nextLine();

    int total = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
        total++;
    }

    System.out.println("The number of uppercase letters is " +
      total);
  }
}