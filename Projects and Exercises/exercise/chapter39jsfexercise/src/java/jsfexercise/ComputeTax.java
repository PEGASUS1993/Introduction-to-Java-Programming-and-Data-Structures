package jsfexercise;

// ComputeTax.java: Compute tax payments
import javax.swing.JOptionPane;

public class ComputeTax {
  public static void main(String[] args) {
    // Prompt the user to enter filing status
    String statusString = JOptionPane.showInputDialog(null,
      "Enter the filing status:\n" +
      "(0-single filer, 1-married jointly,\n" +
      "2-married separately, 3-head of household)",
      "Example 5.8 Input", JOptionPane.QUESTION_MESSAGE);
    int status = Integer.parseInt(statusString);

    // Prompt the user to enter taxable income
    String incomeString = JOptionPane.showInputDialog(null,
      "Enter the taxable income:",
      "Example 5.8 Input", JOptionPane.QUESTION_MESSAGE);
    double income = Double.parseDouble(incomeString);

    // Compute and display the result
    JOptionPane.showMessageDialog(null, "Tax is " +
      (int)(computeTax(status, income) * 100) / 100.0,
      "Example 5.8 Output", JOptionPane.INFORMATION_MESSAGE);

    System.exit(0);
  }

  public static double computeTax(int status, double income) {
    double[] rates = {0.10, 0.15, 0.27, 0.30, 0.35, 0.386};

    int[][] brackets = {
      {6000, 27950, 67700, 141250, 307050}, // Single filer
      {12000, 46700, 112850, 171950, 307050}, // Married jointly
      {6000, 23350, 56425, 85975, 153525}, // Married separately
      {10000, 37450, 96700, 156600, 307050} // Head of household
    };

    double tax = 0; // Tax to be computed

    // Compute tax in the first bracket
    if (income <= brackets[status][0])
      return tax = income * rates[0]; // Done
    else
      tax = brackets[status][0] * rates[0];

    // Compute tax in the 2nd, 3rd, 4th, and 5th brackets, if needed
    for (int i = 1; i < brackets[0].length; i++) {
      if (income > brackets[status][i])
        tax += (brackets[status][i] - brackets[status][i - 1]) *
          rates[i];
      else {
        tax += (income - brackets[status][i - 1]) * rates[i];
        return tax; // Done
      }
    }

    // Compute tax in the last (i.e., 6th) bracket
    return  tax += (income - brackets[status][4]) * rates[5];
  }
}