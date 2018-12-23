import java.util.Scanner;

public class Exercise20_22 {
  /** Main method */
  public static void main(String[] args) {
    // Create a Scanner
    Scanner input = new Scanner(System.in);
    System.out.print("Enter number of disks: ");
    int n = input.nextInt();

    // Find the solution recursively
    System.out.println("The moves are:");
    moveDisks(n, 'A', 'B', 'C');
  }

  private static java.util.Stack<Record> stack = new java.util.Stack<Record>();
  
  /** The method for finding the solution to move n disks
      from fromTower to toTower with auxTower */
  public static void moveDisks(int n, char fromTower,
      char toTower, char auxTower) {
    stack.push(new Record(false, n, fromTower, toTower, auxTower));   
    while (!stack.isEmpty()) {    
      Record record = stack.pop();
      n = record.n;
      fromTower = record.fromTower;
      toTower = record.toTower;
      auxTower = record.auxTower;
      if (record.isLast)
        System.out.println("Move disk " + n + " from " +
            record.fromTower + " to " + record.toTower);
      else {
        if (n == 1) 
          System.out.println("Move disk " + n + " from " +
              record.fromTower + " to " + record.toTower);
        else {
          stack.push(new Record(false, n - 1, auxTower, toTower, fromTower));
          stack.push(new Record(true, n, fromTower, toTower, auxTower));
          stack.push(new Record(false, n - 1, fromTower, auxTower, toTower));
        }
      }
    }
  }
  
  public static class Record {
    boolean isLast = false; // isLast indicates that this is the last disk in the fromTower.
    int n;
    char fromTower;
    char toTower;
    char auxTower;
    
    Record(boolean isLast, int n, char fromTower, char toTower, char auxTower) {
      this.isLast = isLast;
      this.n = n;
      this.fromTower = fromTower;
      this.toTower = toTower;
      this.auxTower = auxTower;
    }
  }
}
