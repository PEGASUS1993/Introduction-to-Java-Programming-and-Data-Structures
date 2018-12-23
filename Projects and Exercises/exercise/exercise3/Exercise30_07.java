import java.util.stream.DoubleStream;

public class Exercise30_07 {
  // Main method
  public static void main(String[] args) {
    double[] numbers = new double[10];
    
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter ten double numbers: ");

    for (int i = 0; i < numbers.length; i++) 
      numbers[i] = input.nextDouble();

    System.out.println("The min is " + min(numbers));
  }

  public static double min(double[] list) {
    return DoubleStream.of(list).min().getAsDouble();
  }
}
