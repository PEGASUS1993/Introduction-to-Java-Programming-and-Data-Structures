import java.util.Scanner;

public class Exercise05_03Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the number of seconds: ");
    int numberOfSeconds = input.nextInt();
    
    long currentTime = System.currentTimeMillis() / 1000;
    long endTime = currentTime + numberOfSeconds * 1000;
    while (currentTime <= endTime) {
      if ((endTime - currentTime) % 1000 == 0) {
        if (endTime - currentTime > 1000)
          System.out.println((endTime - currentTime) / 1000 + " seconds remaining");        
        else if (endTime - currentTime == 1000)
          System.out.println("1 second remaining");
      }
      
      currentTime += 1;
    }

    System.out.println("Stopped");
  }
}
