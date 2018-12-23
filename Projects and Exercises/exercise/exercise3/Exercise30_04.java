import java.util.stream.IntStream;

public class Exercise30_04 {
  public static void main(String[] args) {
    int[] numbers = new int[10]; // numbers array will store distinct values, maximum is 10

    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter " + numbers.length + " numbers: ");

    for (int i = 0; i < numbers.length; i++) {
      // Read an input
      numbers[i] = input.nextInt();
    }

    System.out.println("The number of distinct numbers is " +
      IntStream.of(numbers).distinct().mapToObj(e -> "" + e).sorted()
      .reduce("", (e1, e2) -> e1 + " " + e2));
  }
}
