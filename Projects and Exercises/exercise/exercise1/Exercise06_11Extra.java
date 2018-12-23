import java.util.Scanner;

public class Exercise06_11Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("Enter a numerator: ");
    int numerator = input.nextInt();
    System.out.print("Enter a denominator: ");
    int denominator = input.nextInt();
   
    if (numerator < denominator) {
      System.out.println(numerator + " / " + denominator + " is a proper fraction"); 
    }
    else if (numerator % denominator == 0) {
      System.out.print(numerator + " / " + denominator + " is an improper fraction "); 
      System.out.println("and it can be reduced to " + numerator / denominator); 
    }
    else {
      System.out.print(numerator + " / " + denominator + " is an improper fraction "); 
      int gcd = gcd(numerator, denominator);
      numerator /= gcd;
      denominator /= gcd;      
      System.out.println("and its mixed fraction is " + numerator / denominator + " + " +  
          numerator % denominator + " / " + denominator); 
    }
  }
  
  /** Return the gcd of two integers */
  public static int gcd(int n1, int n2) {
    int gcd = 1; // Initial gcd is 1
    int k = 1; // Possible gcd
    
    while (k <= n1 && k <= n2) {
      if (n1 % k == 0 && n2 % k == 0)
        gcd = k; // Update gcd
      k++;
    }

    return gcd; // Return gcd
  }  
}


