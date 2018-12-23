public class Exercise07_14 {
  public static void main(String[] args) {
    System.out.print("Enter five integers: ");
    java.util.Scanner input = new java.util.Scanner(System.in);
    
    int[] numbers = new int[5];
    for (int i = 0; i < numbers.length; i++)
      numbers[i] = input.nextInt();

    System.out.println("The gcd of these five integers is " +
      gcd(numbers));
  }

  public static int gcd(int... numbers) {
    int g = numbers[0];

    for (int i = 1; i < numbers.length; i++)
      g = gcd(g, numbers[i]);

    return g;
  }
  
  /** Return the gcd of two integers */
  public static int gcd(int n1, int n2) {
    int gcd = 1; // Initial gcd is 1
    int k = 2;   // Possible gcd

    while (k <= n1 && k <= n2) {
      if (n1 % k == 0 && n2 % k == 0)
        gcd = k; // Update gcd
      k++;
    }
  
    return gcd; // Return gcd
  }
}
