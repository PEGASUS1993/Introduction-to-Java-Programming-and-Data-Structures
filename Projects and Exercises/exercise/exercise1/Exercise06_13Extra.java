import java.util.Scanner;

public class Exercise06_13Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the current year tuition: ");
    double tuition = input.nextDouble();
    
    System.out.printf("%9s%20s\n", "Rate", "Number of Years");
    for (double rate = 0.03; rate <= 0.1; rate += 0.01) {
      System.out.printf("%8.1f%%%20d\n", rate * 100, getNumberOfYears(tuition, rate));
    }
  }

  public static int getNumberOfYears(double tuition, double rate) {
    int year = 0;
    double newTuition = tuition;
    while (newTuition < 2 * tuition) {
      newTuition = newTuition * (1 + rate);
      year++;
    }
    return year;
  }
}
