import java.util.Scanner;

public class Exercise07_28 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);    
    System.out.print("Enter ten integers: ");
    final int N = 10;
    int[] numbers = new int[N];
    for (int i = 0; i < N; i++)
      numbers[i] = input.nextInt();
    
    for (int i = 0; i < N; i++) 
      for (int j = i + 1; j < N; j++)
        System.out.println(numbers[i] + " " + numbers[j]);
  }
}
