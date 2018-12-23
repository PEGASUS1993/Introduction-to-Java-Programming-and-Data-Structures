public class Exercise03_20 {
  // Main method
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);
    // Enter the temperature in Fahrenheit
    System.out.print("Enter the temperature in Fahrenheit: "); 
    double fahrenheit = input.nextDouble();

    if (fahrenheit < -58 || fahrenheit > 41) {
      System.out.println("Temperature must be between -58°F and 41°F");
      System.exit(1);
    }

    // Enter the wind speed miles per hour
    System.out.print("Enter the wind speed miles per hour: ");
    double speed = input.nextDouble();
    
    if (speed < 2) {
      System.out.println("Speed must be greater than or equal to 2");
      System.exit(2);
    }

    // Compute wind chill index
    double windChillIndex = 35.74 + 0.6215 * fahrenheit - 35.75 *
      Math.pow(speed, 0.16) + 0.4275 * fahrenheit * 
      Math.pow(speed, 0.16);
      
    // Display the result
    System.out.println("The wind chill index is " + windChillIndex);
  }
}
