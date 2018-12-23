import java.util.*;

public class Exercise12_03 {
  public static void main(String[] args) {
    int[] data = new int[100];
    
    // Initialize array
    for (int i = 0; i < 100; i++)
      data[i] = (int)(Math.random() * 10000);

    Scanner input = new Scanner(System.in);
    
    System.out.print("Enter an index: ");
    int index = input.nextInt();

    try {
      System.out.println("The element is " + data[index]);
    }
    catch (Exception ex) {
      System.out.println("Out of Bounds");
    }
  }
}
