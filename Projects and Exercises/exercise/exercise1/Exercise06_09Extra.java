import java.util.Scanner;

public class Exercise06_09Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter total seconds: ");
    long totalSeconds = input.nextLong();
    
    System.out.println("The hours, minutes, and seconds for total seconds " +
      totalSeconds + " is " + format(totalSeconds));
  }

  public static String format(long seconds) {
    int hour = (int)(seconds / 3600 % 24);
    int minute = (int)(seconds / 60 % 60);
    int second = (int)(seconds % 60);
    
    return (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + ":" + 
      (second < 10 ? "0" + second : second);
  }
}
