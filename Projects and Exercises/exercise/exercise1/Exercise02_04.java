public class Exercise02_04 {
  public static void main(String[] args) {
    // Prompt the input
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter a number in pounds: ");
    double pounds = input.nextDouble();
    double kilograms = pounds * 0.454;

    System.out.println(pounds + " pounds is " + kilograms + " kilograms");
  }
}
