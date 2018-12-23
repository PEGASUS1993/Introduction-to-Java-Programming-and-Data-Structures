
public class Exercise27_13 {

}

class Date1 {
  private int year;
  private int month;
  private int day;
  
  public Date1(int year, int month, int day) {
    this.year = year;
    this.month = month;
    this.day = day;
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
  
  public boolean equals(Object o) {
    return year == ((Date1)o).year && 
        month == ((Date1)o).month && day == ((Date1)o).day; 
  }
  
  public int hashCode() {
    return year * 10000 + month * 100 + day; 
  }
}
