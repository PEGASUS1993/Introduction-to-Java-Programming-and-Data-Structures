import java.util.Scanner;

public class Exercise13_01Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in); 
    System.out.print("Enter time1 (hour minute second): ");
    int hour, minute, second;
    hour = input.nextInt();
    minute = input.nextInt();
    second = input.nextInt();
    Time time1 = new Time(hour, minute, second);
    System.out.println(time1);
    System.out.println("Elapsed seconds in time1: " + time1.getSeconds());
    
    System.out.print("\nEnter time2 (elapsed time): ");
    long seconds = input.nextLong();
    Time time2 = new Time(seconds);
    System.out.println(time2);
    System.out.println("Elapsed seconds in time2: " + time2.getSeconds());
    
    System.out.println("\ntime1.compareTo(time2)? " + time1.compareTo(time2));

    Time time3 = (Time)time1.clone();
    System.out.println("\ntime1.compareTo(time3)? " + time1.compareTo(time3));
  }
}

class Time implements Comparable<Time>, Cloneable {
  private long seconds;
  
  public Time() {
    seconds = System.currentTimeMillis() / 1000;
  }
  
  public Time(int hour, int minute, int second) {
    seconds = hour * 3600 + minute * 60 + second;
  }
  
  public Time(long seconds) {
    this.seconds = seconds;
  }
  
  public long getSeconds() {
    return seconds;
  }
  
  public int getHour() {
    return (int)(seconds / 3600 % 24);
  }
  
  public int getMinute() {
    return (int)(seconds / 60 % 60);
  }
  
  public int getSecond() {
    return (int)(seconds % 60);
  }
  
  public String toString() {
    return getHour() + (getHour() > 1 ? " hours " : " hour ") +
      getMinute() + (getMinute() > 1 ? " minutes " : " minute ") +  
      getSecond() + (getSecond() > 1 ? " seconds " : " second ");
  }
  
  public int compareTo(Time t) {
    return (int)(this.seconds - t.seconds);
  }
  
  public Object clone() {
    try {
      return super.clone();
    }
    catch (CloneNotSupportedException ex) {
      ex.printStackTrace();
    }
    
    return null;
  }
}