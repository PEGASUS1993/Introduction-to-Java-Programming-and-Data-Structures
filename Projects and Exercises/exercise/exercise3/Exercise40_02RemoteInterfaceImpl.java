import java.rmi.*;
import java.rmi.server.*;

public class Exercise40_02RemoteInterfaceImpl
    extends UnicastRemoteObject
    implements Exercise40_02RemoteInterface {

  public Exercise40_02RemoteInterfaceImpl() throws
    RemoteException {

  }

  public double monthlyPayment(double loanAmount,
    double annualInterestRate, int numberOfYears) throws RemoteException {
    double monthlyInterestRate = annualInterestRate / 1200;
    return loanAmount * monthlyInterestRate / (1 -
      (Math.pow(1 / (1 + monthlyInterestRate), numberOfYears * 12)));

  }

  public double totalPayment(double loanAmount,
    double annualInterestRate, int numberOfYears) throws RemoteException {
    double monthlyInterestRate = annualInterestRate / 1200;
    return numberOfYears * 12* loanAmount * monthlyInterestRate / (1 -
      (Math.pow(1 / (1 + monthlyInterestRate), numberOfYears * 12)));

  }

  /**
   * Holds value of property annualInterestRate.
   */
  private double annualInterestRate;

  /**
   * Getter for property annualInterestRate.
   * @return Value of property annualInterestRate.
   */
  public double getAnnualInterestRate() {
    return this.annualInterestRate;
  }

  /**
   * Setter for property annualInterestRate.
   * @param annualInterestRate New value of property annualInterestRate.
   */
  public void setAnnualInterestRate(double annualInterestRate) {
    this.annualInterestRate = annualInterestRate;
  }

  /**
   * Holds value of property loanAmount.
   */
  private double loanAmount;

  /**
   * Getter for property amount.
   * @return Value of property amount.
   */
  public double getLoanAmount() {
    return this.loanAmount;
  }

  /**
   * Setter for property amount.
   * @param amount New value of property amount.
   */
  public void setLoanAmount(double loanAmount) {
    this.loanAmount = loanAmount;
  }

  /**
   * Holds value of property numberOfYears.
   */
  private int numberOfYears;

  /**
   * Getter for property numberOfYears.
   * @return Value of property numberOfYears.
   */
  public int getNumberOfYears() {
    return this.numberOfYears;
  }

  /**
   * Setter for property numberOfYears.
   * @param numberOfYears New value of property numberOfYears.
   */
  public void setNumberOfYears(int numberOfYears) {
    this.numberOfYears = numberOfYears;
  }
}
