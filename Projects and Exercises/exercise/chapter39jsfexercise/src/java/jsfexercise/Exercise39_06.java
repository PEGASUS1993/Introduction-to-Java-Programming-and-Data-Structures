package jsfexercise;

import java.math.BigInteger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Exercise39_06 {
  public String getResponse() {
    String s = "<table border=\"1\">" +
      "<tr>" +
      "<td>Number</td>" + 
      "<td>Factorial</td>" +
      "</tr>";
    for (int i = 20; i <= 30; i++) {
      s += "<tr>"; 
      s += "<td>" + i + "</td>";
      s += "<td>" + factorial(new BigInteger(i + "")) + "</td>";
      s += "</tr>";
    }
    
    return s;
  }
  
	public static BigInteger factorial(BigInteger i) {
		if (i.equals(BigInteger.ZERO))
			return BigInteger.ONE;
		else
			return i.multiply(factorial(i.subtract(BigInteger.ONE)));
	}
}
