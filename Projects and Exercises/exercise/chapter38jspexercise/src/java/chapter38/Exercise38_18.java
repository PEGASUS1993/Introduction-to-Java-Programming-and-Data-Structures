package chapter38;

import java.text.*;
import java.math.*;

public class Exercise38_18 {
  private int number;

  /** Return number property */
  public int getNumber() {
    return number;
  }

  /** Set number property */
  public void setNumber(int newValue) {
    number = newValue;
  }

  /** Obtain factorial */
  public String getFactorial() {   
    BigInteger result = BigInteger.ONE;
    for (int i = 1; i <= number; i++)
      result = result.multiply(new BigInteger(i+""));

    return result.toString();
  }
}