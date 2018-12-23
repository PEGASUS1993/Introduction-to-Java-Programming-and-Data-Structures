public class Exercise10_01 {
  public static void main(String[] args) {
    MyTime time1 = new MyTime();
    System.out.println(time1.getHour() + ":" +
      time1.getMinute() + ":" + time1.getSecond());

    MyTime time2 = new MyTime(555550000);
    System.out.println(time2.getHour() + ":" +
      time2.getMinute() + ":" + time2.getSecond());

    MyTime time3 = new MyTime(5, 23, 55);
    System.out.println(time3.getHour() + ":" +
      time3.getMinute() + ":" + time3.getSecond());
  }
}

class MyTime {
  private int hour;
  private int minute;
  private int second;

  public MyTime() {
    this(System.currentTimeMillis());
  }

  public MyTime(long elapsedTime) {
    setTime(elapsedTime);
  }

  public MyTime(int hour, int minute, int second) {
    this.hour = hour;
    this.minute = minute;
    this.second = second;
  }

  public int getHour() {
    return hour;
  }

  public int getMinute() {
    return minute;
  }

  public int getSecond() {
    return second;
  }
  
  public void setTime(long elapsedTime) {
    // Obtain the total seconds since the midnight, Jan 1, 1970
    long totalSeconds = elapsedTime / 1000;

    // Compute the current second in the minute in the hour
    second = (int)(totalSeconds % 60);

    // Obtain the total minutes
    long totalMinutes = totalSeconds / 60;

    // Compute the current minute in the hour
    minute = (int)(totalMinutes % 60);

    // Obtain the total hours
    int totalHours = (int)(totalMinutes / 60);

    // Compute the current hour
    hour = (int)(totalHours % 24);
  }
}
