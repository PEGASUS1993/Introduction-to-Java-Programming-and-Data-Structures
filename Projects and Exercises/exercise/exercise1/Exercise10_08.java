public class Exercise10_08 {
  public static void main(String[] args) {
    int[][] bracketsFor2001 = {
      {27050, 65550, 136750, 297350}, // Single filer
      {45200, 109250, 166500, 297350}, // married filing jointly
      {22600, 54625, 83250, 148675}, // married filing separately
      {36250, 93650, 151650, 297350} // head of household
    };

    double[] ratesFor2001 = {0.15, 0.275, 0.305, 0.355, 0.391};

    int[][] bracketsFor2002 = {
      {6000, 27950, 67700, 141250, 307050}, // Single filer
      {12000 , 46700, 112850, 171950, 307050}, // married filing jointly
      {6000, 23350, 56425, 85975, 153525}, // married filing separately
      {10000, 37450, 96700, 156600, 307050} // head of household
    };

    double[] ratesFor2002 = {0.1, 0.15, 0.27, 0.30, 0.35, 0.386};

    Tax taxFor2001 = new Tax(0, bracketsFor2001, ratesFor2001, 50000);
    System.out.println("2001 Tax Table");
    System.out.println("taxable\tSingle\tMarried\tMarried\tHead of");
    System.out.println("Income\tSingle\tJoint\tSeparate\ta House");
    for (int taxableIncome = 50000; taxableIncome <= 60000; taxableIncome += 1000) {
      taxFor2001.setTaxableIncome(taxableIncome);
      taxFor2001.setFilingStatus(0);
      int taxForStatus0 = (int)taxFor2001.findTax();
      taxFor2001.setFilingStatus(1);
      int taxForStatus1 = (int)taxFor2001.findTax();
      taxFor2001.setFilingStatus(2);
      int taxForStatus2 = (int)taxFor2001.findTax();
      taxFor2001.setFilingStatus(3);
      int taxForStatus3 = (int)taxFor2001.findTax();
      System.out.println(taxableIncome + "\t" + taxForStatus0 + "\t" +
        taxForStatus1 + "\t" + taxForStatus2 + "\t" +
        taxForStatus3);
    }

    Tax taxFor2002 = new Tax(0, bracketsFor2002, ratesFor2002, 50000);
    System.out.println("2002 Tax Table");
    System.out.println("taxable\tSingle\tMarried\tMarried\tHead of");
    System.out.println("Income\tSingle\tJoint\tSeparate\ta House");
    for (int taxableIncome = 50000; taxableIncome <= 60000; taxableIncome += 1000) {
      taxFor2002.setTaxableIncome(taxableIncome);
      taxFor2002.setFilingStatus(0);
      int taxForStatus0 = (int)taxFor2002.findTax();
      taxFor2002.setFilingStatus(1);
      int taxForStatus1 = (int)taxFor2002.findTax();
      taxFor2002.setFilingStatus(2);
      int taxForStatus2 = (int)taxFor2002.findTax();
      taxFor2002.setFilingStatus(3);
      int taxForStatus3 = (int)taxFor2002.findTax();
      System.out.println(taxableIncome + "\t" + taxForStatus0 + "\t" +
        taxForStatus1 + "\t" + taxForStatus2 + "\t" +
        taxForStatus3);
    }
  }
}

class Tax {
  public final static int SINGLE_FILER = 1;
  public final static int MARRIED_JOINTLY = 2;
  public final static int MARRIED_SEPARATELY = 3;
  public final static int HEAD_OF_HOUSEHOLD = 4;

  private int filingStatus = SINGLE_FILER;

  private int[][] brackets = {
    {27050, 65550, 136750, 297350}, // Single filer
    {45200, 109250, 166500, 297350}, // married filing jointly
    {22600, 54625, 83250, 148675}, // married filing separately
    {36250, 93650, 151650, 297350} // head of household
  };

  private double[] rates = {0.15, 0.275, 0.305, 0.355, 0.391};
  private double taxableIncome = 100000;

  public Tax() {
  }

  public Tax(int filingStatus, int[][] brackets, double[] rates,
    double taxableIncome) {
    this.filingStatus = filingStatus;
    this.brackets = brackets;
    this.rates = rates;
    this.taxableIncome = taxableIncome;
  }

  public void setBrackets(int[][] brackets) {
    this.brackets = brackets;
  }

  public void setRates(double[] rates) {
    this.rates = rates;
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

  public double findTax() {
    double tax = 0;

    // Compute tax in the first bracket
    if (taxableIncome <= brackets[filingStatus][0])
      return tax = taxableIncome * rates[0];
    else
      tax = brackets[filingStatus][0] * rates[0];

    int i;

    // Compute tax in the possible 2nd, 3rd, 4th, and 5th brackets
    for (i = 1; i < brackets[0].length; i++) {
      if (taxableIncome > brackets[filingStatus][i])
        tax += (brackets[filingStatus][i] - brackets[filingStatus][i - 1]) *
          rates[i];
      else {
        tax += (taxableIncome - brackets[filingStatus][i - 1]) * rates[i];
        break;
      }
    }

    // Compute tax in the possible last bracket
    if (i == brackets[0].length && taxableIncome > brackets[filingStatus][i - 1])
      tax += (taxableIncome - brackets[filingStatus][i - 1]) * rates[i];

    return tax;
  }
}
