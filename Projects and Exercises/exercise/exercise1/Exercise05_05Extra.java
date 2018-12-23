public class Exercise05_05Extra {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    System.out.print("Enter the number of sides: ");
    int n = input.nextInt();
    
    System.out.print("Enter the radius of the bounding circle: ");
    double radius = input.nextDouble();
  
    double angle = 0;
    
    System.out.print("The coordinates of the points on the polygon are ");
    for (int i = 0; i < n; i++) {
      double x = radius * Math.cos(angle);
      double y = radius * Math.sin(angle);
      System.out.println("(" + x + ", " + y + ")");
      angle += 2 * Math.PI / n;
    }
  }
}
