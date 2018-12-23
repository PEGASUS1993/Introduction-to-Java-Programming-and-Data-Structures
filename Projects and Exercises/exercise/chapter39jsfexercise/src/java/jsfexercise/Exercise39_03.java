package jsfexercise;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "exercise39_03")
@RequestScoped
public class Exercise39_03 {
  private double taxableIncome;
  private int filingStatus;
  
  /** Creates a new instance of Exercise44_3 */
  public Exercise39_03() {
  }
  
  public double getTaxableIncome() {
    return taxableIncome;
  }
  
  public void setTaxableIncome(double taxableIncome) {
    this.taxableIncome = taxableIncome;
  }
  
  public int getFilingStatus() {
    return filingStatus;
  }
  
  public void setFilingStatus(int filingStatus) {
    this.filingStatus = filingStatus;
  }
      
  public String getResponse() {
    if (taxableIncome == 0)
      return "";
    
    String s = "Taxable Income: " + taxableIncome + "<br>" + 
      "Filing Status: " + filingStatus + "<br>" +
      "<font color=\"#FF0000\">" +
      "Tax: " + ComputeTax.computeTax(filingStatus, taxableIncome) +
      "</font>";
    
    return s;
  }
}
