public class Exercise04_07 {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter the radius of the bounding circle: ");
    double radius = input.nextDouble();
    
    System.out.println("The coordinates of five points on the pentagon are");

    double angle = Math.PI / 2 - 2 * Math.PI / 5;
    double x = radius * Math.cos(angle);
    double y = radius * Math.sin(angle);
    System.out.printf("(%.2f, %.2f)\n", x, y);
    
    angle += 2 * Math.PI / 5;
    x = radius * Math.cos(angle);
    y = radius * Math.sin(angle);
    System.out.printf("(%.2f, %.2f)\n", x, y);

    angle += 2 * Math.PI / 5;
    x = radius * Math.cos(angle);
    y = radius * Math.sin(angle);
    System.out.printf("(%.2f, %.2f)\n", x, y);

    angle += 2 * Math.PI / 5;
    x = radius * Math.cos(angle);
    y = radius * Math.sin(angle);
    System.out.printf("(%.2f, %.2f)\n", x, y);

    angle += 2 * Math.PI / 5;
    x = radius * Math.cos(angle);
    y = radius * Math.sin(angle);
    System.out.printf("(%.2f, %.2f)\n", x, y);
  }
}