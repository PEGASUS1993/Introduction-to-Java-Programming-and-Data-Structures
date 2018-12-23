public class Exercise10_01International {
  public static void main(String[] args) {

  }
}

class MyDateTime {
  private int year;
  private int month;
  private int day;
  private int hour;
  private int minute;
  private int second;
  
  MyDateTime() {    
  }
  
  MyDateTime(long elapeTime) {   
  }
    
  MyDateTime(int year, int month, int day, 
      int hour, int minute, int second) {
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.minute = minute;
    this.second = second;
  }
  
  public int getYear() {
    return year;
  }

  public int getMonth() {
    return month;
  }
  
  public int getDay() {
    return day;
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
  
  public void setYear(int year) {
    this.year = year;
  }
  
  public void setMonth(int month) {
    this.month = month;
  }
  
  public void setDay(int day) {
    this.day = day;
  }  
  
  public void setHour(int hour) {
    this.hour = hour;
  }
  
  public void setMinute(int minute) {
    this.minute = minute;
  }
  
  public void setSecond(int second) {
    this.second = second;
  }  
}
