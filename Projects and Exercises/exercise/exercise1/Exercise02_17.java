public class Exercise02_17 {
  // Main method
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);
    // Enter the temperature in Fahrenheit
    System.out.print("Enter the temperature in Fahrenheit between -58°F and 41°F: ");
    double fahrenheit = input.nextDouble();

    // Enter the wind speed miles per hour
    System.out.print("Enter the wind speed miles per hour " + 
      "(must be greater than or equal to 2): ");
    double speed = input.nextDouble();
    
    // Compute wind chill index
    double windChillIndex = 35.74 + 0.6215 * fahrenheit - 35.75 *
      Math.pow(speed, 0.16) + 0.4275 * fahrenheit * 
      Math.pow(speed, 0.16);
      
    // Display the result
    System.out.println("The wind chill index is " + windChillIndex);
  }
}
