import java.util.*;

public class Exercise11_03Extra {
  public static void main(String[] args) {
    List<Flight> flights = new ArrayList<>();
    flights.add(new Flight("US230", 
        new GregorianCalendar(2014, 4, 5, 5, 5, 0),
        new GregorianCalendar(2014, 4, 5, 6, 15, 0)));
    flights.add(new Flight("US235", 
        new GregorianCalendar(2014, 4, 5, 6, 55, 0),
        new GregorianCalendar(2014, 4, 5, 7, 45, 0)));
    flights.add(new Flight("US237", 
        new GregorianCalendar(2014, 4, 5, 9, 35, 0),
        new GregorianCalendar(2014, 4, 5, 12, 55, 0)));
    
    Itinerary itinerary = new Itinerary(flights);
    System.out.println(itinerary.getTotalTravelTime());
    System.out.println(itinerary.getTotalFlightTime());
  }
}

class Flight {
  private String flightNo;
  private Calendar departureTime;
  private Calendar arrivalTime;
  
  public Flight(String flightNo, Calendar departureTime, 
      Calendar arrivalTime) {
    this.flightNo = flightNo;
    this.departureTime = departureTime;
    this.arrivalTime = arrivalTime;
  }
  
  public int getFlightTime() {
    return (int)(arrivalTime.getTimeInMillis() - departureTime.getTimeInMillis()) 
       / (1000 * 60);
  }
  
  public Calendar getDepartureTime() {
    return departureTime;
  }

  public Calendar getArrivalTime() {
    return arrivalTime;
  }
  
  public void setArrivalTime(Calendar arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public void setDepartureTime(Calendar departureTime) {
    this.departureTime = departureTime;
  }
}

class Itinerary {
  private List<Flight> flights;
  
  public Itinerary(List<Flight> flights) {
    this.flights = flights;
  }
  
  public int getTotalTravelTime() {
    int totalTime = getTotalFlightTime();
    for (int i = 0; i < flights.size() - 1; i++) {
      long time = flights.get(i + 1).getDepartureTime().getTimeInMillis() -
        flights.get(i).getArrivalTime().getTimeInMillis();
      totalTime += (int)time / (1000 * 60);
    }
    return totalTime;
  }
  
  public int getTotalFlightTime() {
    int flightTime = 0;
    for (Flight flight: flights)
      flightTime += flight.getFlightTime();
    return flightTime;
  }
}