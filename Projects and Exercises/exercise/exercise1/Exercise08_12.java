import java.util.Scanner;

public class Exercise08_12 {
	public static void main(String[] args) {
		// Create a Scanner
		Scanner input = new Scanner(System.in);

		// Prompt the user to enter filing status
		// Prompt the user to enter filing status
		System.out.print(
			"(0-single filer, 1-married jointly,\n" +
			"2-married separately, 3-head of household)\n" +
			"Enter the filing status: ");
		int status = input.nextInt();

		// Prompt the user to enter taxable income
		System.out.print("Enter the taxable income: ");
		double income = input.nextDouble();

		// Compute and display the result
		if (status >= 0 && status <= 3)
  		System.out.println("Tax is " +
  			(int)(computeTax(status, income) * 100) / 100.0);
		else
		  System.out.println("Error: invalid input");
	}

	public static double computeTax(int status, double income) {
		double[] rates = {0.10, 0.15, 0.25, 0.28, 0.33, 0.35};
		
		int[][] brackets = {
				  {8350, 33950, 82250, 171550, 372950},   // Single filer
				  {16700, 67900, 137050, 20885, 372950}, // Married jointly
				  {8350, 33950, 68525, 104425, 186475}, // Married separately
				  {11950, 45500, 117450, 190200, 372950} // Head of household
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
