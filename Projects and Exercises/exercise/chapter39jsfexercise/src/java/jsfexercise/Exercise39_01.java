package jsfexercise;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "exercise39_01")
@RequestScoped
public class Exercise39_01 {
  public String getResponse() {
    String s = "<table border=\"1\">" +
      "<tr>" +
      "<td>Number</td>" + 
      "<td>Factorial</td>" +
      "</tr>";
    for (int i = 0; i <= 10; i++) {
      s += "<tr>"; 
      s += "<td>" + i + "</td>";
      s += "<td>" + computeFactorial(i) + "</td>";
      s += "</tr>";
    }
    
    return s;
  }
  
  private long computeFactorial(int n) {
    if (n == 0)
      return 1;
    else
      return n*computeFactorial(n - 1);
  }
}
