import java.util.Scanner;

public class Exercise03_01Extra {
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
      System.out.println("and its mixed fraction is " + numerator / denominator + " + " +  
          numerator % denominator + " / " + denominator); 
    }
  }
}

