import java.util.*;

public class Exercise05_07Extra {
  public static void main(String[] args) {
    System.out.print("Enter a positive integer: ");
    Scanner input = new Scanner(System.in);
    int n = input.nextInt();

    int number = n;
    int factor = 2;
    int coefficient = 1;
    while (factor <= number) {
      if (number % (factor * factor) == 0) {
        coefficient *= factor;
        number = number / (factor * factor);
      }
      else
        factor++;
    }

    System.out.print("sqrt(" + n + ") is ");
    if (n == 1)
      System.out.println(1);
    else {
      if (coefficient > 1)
        System.out.print(coefficient); 
  
      if  (coefficient > 1 && number > 1)
        System.out.print("*");
  
      if (number > 1)
        System.out.print("sqrt(" + number + ")");
    }
  }
}
