public class Exercise02_10 {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    System.out.print(
      "Enter the amount of water in kilograms: ");
    double mass = input.nextDouble();

    System.out.print("Enter the initial temperature: ");
    double initialTemperature = input.nextDouble();

    System.out.print(
      "Enter the final temperature: ");
    double finalTemperature = input.nextDouble();

    double energy =
      mass * (finalTemperature - initialTemperature) * 4184;

    System.out.print("The energy needed is " + energy);
  }
}
