public class Exercise06_15 {
  public static void main(String[] args) {
    System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", 
      "taxable", "Single", "Married", "Married", "Head of");
    System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", 
      "Income", "Single", "Joint", "Separate", "a House");
    for (int taxableIncome = 50000; taxableIncome <= 60000;
      taxableIncome += 50) {
      System.out.printf("%-15d%-15d%-15d%-15d%-15d\n", 
        taxableIncome,
        Math.round(computeTax(0, taxableIncome)),
        Math.round(computeTax(1, taxableIncome)),
        Math.round(computeTax(2, taxableIncome)),
        Math.round(computeTax(3, taxableIncome)));
    }
  }

  /** for 2002 
  public static double computeTax(double income,
    int r1, int r2, int r3, int r4, int r5) {
    double tax = 0;

    if (income <= r1)
      tax = income * 0.10;
    else if (income <= r2)
      tax = r1 * 0.10 + (income - r1) * 0.15;
    else if (income <= r3)
      tax = r1 * 0.10 + (r2 - r1) * 0.15 + (income - r2) * 0.27;
    else if (income <= r4)
      tax = r1 * 0.10 + (r2 - r1) * 0.15 +
        (r3 - r2) * 0.27 + (income - r3) * 0.30;
    else if (income <= r5)
      tax = r1 * 0.10 + (r2 - r1) * 0.15 + (r3 - r2) * 0.27 +
        (r4 - r3) * 0.30 + (income - r4) * 0.35;
    else
      tax = r1 * 0.10 + (r2 - r1) * 0.15 + (r3 - r2) * 0.27 +
        (r4 - r3) * 0.30 + (r5 - r4) * 0.35 + (income - r5) * 0.386;

    return tax;
  }

  public static double computeTax(int status, double income) {
    switch (status) {
      case 0: return
        computeTax(income, 6000, 27950, 67700, 141250, 307050);
      case 1: return
        computeTax(income, 12000, 46700, 112850, 171950, 307050);
      case 2: return
        computeTax(income, 6000, 23350, 56425, 85975, 153525);
      case 3: return
        computeTax(income, 10000, 37450, 96700, 156600, 307050);
      default: return 0;
    }
  }
  */
  
  /** For 2009 */
  public static double computeTax(int status, double income) {
    switch (status) {
      case 0: return
        computeTax(income, 8350, 33950, 82250, 171550, 372950);
      case 1: return
        computeTax(income, 16700, 67900, 137050, 208850, 372950);
      case 2: return
        computeTax(income, 8350, 33950, 68525, 104425, 186475);
      case 3: return
        computeTax(income, 11950, 45500, 117450, 190200, 372950);
      default: return 0;
    }
  }
  
  public static double computeTax(double income,
    int r1, int r2, int r3, int r4, int r5) {
    double tax = 0;

    if (income <= r1)
      tax = income * 0.10;
    else if (income <= r2)
      tax = r1 * 0.10 + (income - r1) * 0.15;
    else if (income <= r3)
      tax = r1 * 0.10 + (r2 - r1) * 0.15 + (income - r2) * 0.25;
    else if (income <= r4)
      tax = r1 * 0.10 + (r2 - r1) * 0.15 +
        (r3 - r2) * 0.27 + (income - r3) * 0.28;
    else if (income <= r5)
      tax = r1 * 0.10 + (r2 - r1) * 0.15 + (r3 - r2) * 0.25 +
        (r4 - r3) * 0.28 + (income - r4) * 0.33;
    else
      tax = r1 * 0.10 + (r2 - r1) * 0.15 + (r3 - r2) * 0.25 +
        (r4 - r3) * 0.28 + (r5 - r4) * 0.33 + (income - r5) * 0.35;

    return tax;
  }
}
