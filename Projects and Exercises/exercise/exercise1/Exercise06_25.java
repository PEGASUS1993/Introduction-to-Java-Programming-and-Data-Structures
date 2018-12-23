public class Exercise06_25 {
  public static void main(String[] args) {
    System.out.print("Enter time in milliseconds: ");
    java.util.Scanner input = new java.util.Scanner(System.in);
    long totalMilliseconds = input.nextLong();
    
    System.out.println(convertMillis(totalMilliseconds));
  }

  public static String convertMillis(long millis) {
    // Obtain the total seconds since the midnight, Jan 1, 1970
    long totalSeconds = millis / 1000;

    // Compute the current second in the minute in the hour
    int seconds = (int)(totalSeconds % 60);

    // Obtain the total minutes
    long totalMinutes = totalSeconds / 60;

    // Compute the current minute in the hour
    int minutes = (int)(totalMinutes % 60);

    // Obtain the total hours
    long hours = totalMinutes / 60;

    // Display results
    return hours + ":" + minutes + ":" + seconds;
  }
}
