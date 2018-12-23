import java.util.Scanner;

public class Exercise07_01Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    boolean[] isCovered = new boolean[99]; // Default false

    // Read all numbers and mark corresponding element covered
    int number = input.nextInt();
    while (number != 0) {
      if (number >= 0 && number <= 99) 
      	isCovered[number - 1] = true;
      number = input.nextInt();
    }

    // Check if all covered
    boolean allCovered = true; // Assume all covered
    for (int i = 0; i < 99; i++) 
      if (!isCovered[i]) {
        allCovered = false; // Find one number if not covered
        break;
      } 

    // Display result
    if (allCovered)
      System.out.println("The tickets cover all numbers.");
    else
      System.out.println("The tickets don't cover all numbers.");
  } 
}