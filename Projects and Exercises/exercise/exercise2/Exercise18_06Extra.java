import java.util.Scanner;

public class Exercise18_06Extra {
  public static void main(String[] args) {
    int[] list = new int[6];
    Scanner input = new Scanner(System.in);
    System.out.print("Enter 6 integers: ");
    for (int i = 0; i < 6; i++)
      list[i] = input.nextInt();
    cube(list);
    for (int i = 0; i < 6; i++)
      System.out.print(list[i] + " ");
  }

  public static void cube(int[] list) {
    cube(list, 0, list.length - 1);
  }
  
  public static void cube(int[] list, int low, int high) {
	if (low <= high) {
      list[low] = list[low] * list[low] * list[low];
      cube(list, low + 1, high);
	}
  }
}
