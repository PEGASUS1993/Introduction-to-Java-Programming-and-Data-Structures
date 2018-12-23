public class Exercise12_04 {
  public static void main(String[] args) {
    try {
      new NewLoan(7.5, 30, 100000);
      NewLoan m = new NewLoan(-1, 3, 3);
      new NewLoan(7.5, 30, 20000);
    }
    catch (Exception ex) {
      System.out.println(ex);
    }

    System.out.println("End of program");
  }
}

class NewLoan {
  private double annualInterestRate;
  private int numOfYears;
  private double loanAmount;

  /** Default constructor */
  public NewLoan() {
    this(7.5, 30, 100000);
  }

  /** Construct a NewLoan with specified annual interest rate,
      number of years and loan amount
    */
  public NewLoan(double annualInterestRate, int numOfYears,
    double loanAmount) {
    if (annualInterestRate <= 0)
      throw new IllegalArgumentException("Annual interest rate must be positive.");
    if (numOfYears <= 0)
      throw new IllegalArgumentException("Number of years must be positive.");
    if (loanAmount <= 0)
      throw new IllegalArgumentException("Loan amount must be positive.");
    setAnnualInterestRate(annualInterestRate);
    setNumOfYears(numOfYears);
    setLoanAmount(loanAmount);
  }

  /** Return annualInterestRate */
  public double getAnnualInterestRate() {
    return annualInterestRate;
  }

  /** Set a new annualInterestRate */
  public void setAnnualInterestRate(double annualInterestRate) {
    if (annualInterestRate <= 0)
      throw new IllegalArgumentException("Annual interest rate must be positive.");
    this.annualInterestRate = annualInterestRate;
  }

  /** Return numOfYears */
  public int getNumOfYears() {
    return numOfYears;
  }

  /** Set a new numOfYears */
  public void setNumOfYears(int numOfYears) {
    if (numOfYears <= 0)
      throw new IllegalArgumentException("Number of years must be positive.");
    this.numOfYears = numOfYears;
  }

  /** Return loanAmount */
  public double getLoanAmount() {
    return loanAmount;
  }

  /** Set a newloanAmount */
  public void setLoanAmount(double loanAmount) {
    if (loanAmount <= 0)
      throw new IllegalArgumentException("Loan amount must be positive.");
    this.loanAmount = loanAmount;
  }

  /** Find monthly payment */
  public double monthlyPayment() {
    double monthlyInterestRate = annualInterestRate / 1200;
    return loanAmount * monthlyInterestRate / (1 -
      (Math.pow(1 / (1 + monthlyInterestRate), numOfYears * 12)));
  }

  /** Find total payment */
  public double totalPayment() {
    return monthlyPayment() * numOfYears * 12;
  }
}
