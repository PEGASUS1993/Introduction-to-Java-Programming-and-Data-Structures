public class Exercise07_09 {
  // Main method
  public static void main(String[] args) {
    double[] numbers = new double[10];
    
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter ten double numbers: ");

    for (int i = 0; i < numbers.length; i++) 
      numbers[i] = input.nextDouble();

    System.out.println("The minimal value is " + min(numbers));
  }

  public static double min(double[] list) {
    double min = list[0];

    for (int i = 1; i < list.length; i++)
      if (min > list[i]) 
        min = list[i];

    return min;
  }
}
