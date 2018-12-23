import java.util.stream.IntStream;

public class Exercise30_08 {
  public static void main(String[] args) {
    int[] numbers = new int[10];

    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter ten numbers: ");

    for (int i = 0; i < numbers.length; i++) {
      // Read and store numbers in an array if it is new
      numbers[i] = input.nextInt();
    }

    int[] result = eliminateDuplicates(numbers);
    
    System.out.println(
      "The number of distinct values is " + result.length);
    for (int i = 0; i < result.length; i++)
      System.out.print(result[i] + " ");
  }
    
  public static int[] eliminateDuplicates(int[] numbers) {  
    return IntStream.of(numbers).distinct().toArray();
  }
}
