package jsfexercise;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "dowBean")
@RequestScoped

public class Exercise39_13 {

    private String errorStatus = "";
    
    private Integer day;
    private Integer month;
    private Integer year;
    
    private Map<String, Integer> dayMap;
    private Map<String, Integer> monthMap;
    
    private int[] lastDay = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    private String[] days = {"Saturday", "Sunday", "Monday", "Tuesday",
        "Wednesday", "Thursday", "Friday"};
    
    private String dayOfWeekOut;
    private String timeOut;
    
    public Exercise39_13() {
        dayMap = new LinkedHashMap();
        
        for(int i=1; i <= 31; i++)
            dayMap.put(""+i, i);
        
        monthMap = new LinkedHashMap();
        
        monthMap.put("January", 0);
        monthMap.put("Fabruary", 1);
        monthMap.put("March", 2);
        monthMap.put("April", 3);
        monthMap.put("May", 4);
        monthMap.put("June", 5);
        monthMap.put("July", 6);
        monthMap.put("August", 7);
        monthMap.put("September", 8);
        monthMap.put("October", 9);
        monthMap.put("November", 10);
        monthMap.put("December", 11);
        
    }
    
    public Map<String, Integer> getDayMap() {
        return dayMap;
    }
    
    public Map<String, Integer> getMonthMap() {
        return monthMap;
    }

    public void getDayOfWeek() {
        if(day > lastDay[month]) {
            setErrorStatus("Invalid day for given month!");
        }
        else if(year == null) {
            setErrorStatus("Need a year, bro!");
        }
        else {

            // Month adjusted for crap
            int m = month+1;
            int y = year;
            
            if(m < 2) {
                m += 12;
                y--;
            }
            
            // Century
            int j = y / 100;
            
            // Year of century
            int k = y % 100;
            
            // Zeller's congruence
            int h = (day + (26*(m + 1))/10 + k + k/4 + j/4 + 5*j) % 7;
            
            dayOfWeekOut = days[h];
            
            Date d = new Date(year-1900, month, day);
            
            if(d.after(new Date())) {
                timeOut = "Future";
            }
            else {
                timeOut = "Past";
            }
            
        }
    }
    
    public String getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(String errorStatus) {
        this.errorStatus = errorStatus;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDayOfWeekOut() {
        return dayOfWeekOut;
    }

    public void setDayOfWeekOut(String dayOfWeekOut) {
        this.dayOfWeekOut = dayOfWeekOut;
    }

    public String getTimeOut() {
        return timeOut;
    }
    
    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }
}
