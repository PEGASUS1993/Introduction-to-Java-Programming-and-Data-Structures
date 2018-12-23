public class Exercise03_03Extra {
  public static void main(String[] args) {
    final double RADIUS = 5;
    
    double angle = Math.random() * 2 * Math.PI;
    
    double x = RADIUS * Math.random() * Math.cos(angle);
    double y = RADIUS * Math.sin(angle);
    double distance = Math.pow(x * x + y * y, 0.5);
    System.out.println("The point is (" + x + ", " + y + ") and its distance to the center is " + distance);
  }
}