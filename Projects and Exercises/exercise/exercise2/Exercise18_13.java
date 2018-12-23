import java.util.Scanner;

public class Exercise18_13 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter 8 integers: ");
    
    int[] list = new int[8];
    for (int i = 0; i < list.length; i++)
      list[i] = input.nextInt();
    
    System.out.println("The largest element is " + largest(list));
  }

  public static int largest(int[] list) {
    return largest(list, list.length - 1);
  }

  public static int largest(int[] list, int high) {
    if (high == 0) {
      return list[0];
    }
    else {
      return Math.max(largest(list, high - 1), list[high]);
    }
  }
}
