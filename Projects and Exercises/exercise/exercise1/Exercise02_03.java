public class Exercise02_03 {
  public static void main(String[] args) {
    // Enter foot
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter a value for feet: ");
    double feet = input.nextDouble();

    double meter = feet * 0.305;

    System.out.println(feet + " feet is " + meter + " meters");
  }
}
