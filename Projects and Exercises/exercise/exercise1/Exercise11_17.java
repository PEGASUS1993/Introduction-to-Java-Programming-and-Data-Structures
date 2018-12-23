import java.util.ArrayList;
import java.util.Scanner;

public class Exercise11_17 {
  public static void main(String[] args) {
    System.out.print("Enter an integer m: ");
    Scanner input = new Scanner(System.in);
    int m = input.nextInt();
    
    ArrayList<Integer> list = new ArrayList<>();
    int number = m;
    int factor = 2;
    while (factor <= number) {
      if (number % factor == 0) {
        list.add(factor);
        number = number / factor;
      }
      else
        factor++;
    }

    int n = 1;
    int i = 0;
    while (i < list.size() - 1) {
      if (list.get(i) != list.get(i + 1)) {
        n *= list.get(i);
        i += 1;
      }
      else
        i += 2;
    }

    if (i == list.size() - 1)
      n *= list.get(i);

    System.out.println("The smallest number n for m * n to be a perfect square is " + n);
    System.out.println("m * n is " + m * n);
  }
}