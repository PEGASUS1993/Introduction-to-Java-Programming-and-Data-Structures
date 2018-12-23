import java.util.Scanner;

public class Exercise07_37 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the number of balls to drop: ");
    int numberOfBalls = input.nextInt();
    
    System.out.print("Enter the number of slots: ");
    int numberOfSlots = input.nextInt();
    
    int[] slots = new int[numberOfSlots];
    
    for (int i = 0; i < numberOfBalls; i++) {
      slots[onePath(numberOfSlots)]++;
    }

    printHistogram(slots);
  }
  
  /** Return the slot position of the ball for a path and also print 
   * the path */
  public static int onePath(int numberOfSlots) {
    int position = 0;
    
    for (int i = 0; i < numberOfSlots - 1; i++)
      if (Math.random() < 0.5) {
        System.out.print("L");
      }
      else {
        System.out.print("R");
        position++;        
      }
    
    System.out.println();
    
    return position;
  }
  
  /** Print the histogram for the slots */
  public static void printHistogram(int[] slots) {
    int maxSlotHeight = max(slots);
    
    for (int h = maxSlotHeight; h > 0; h--) {
      for (int i = 0; i < slots.length; i++)
        if (slots[i] < h)
          System.out.print(" "); // Print nothing
        else
          System.out.print("O"); // Print a ball       
      
      System.out.println();
    }
  }
  
  /** Get the max element in slots */
  public static int max(int[] slots) {
    int result = slots[0];
    
    for (int i = 1; i < slots.length; i++) {
      if (result < slots[i])
        result = slots[i];
    }
    
    return result;
  }
}
