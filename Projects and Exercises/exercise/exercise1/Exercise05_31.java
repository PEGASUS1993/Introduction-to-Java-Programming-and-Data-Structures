import java.util.Scanner;

public class Exercise05_31 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the initial deposit amount: ");
		double deposit = input.nextDouble();

		System.out.print("Enter annual percentage yield: ");
		double annualInterestRate = input.nextDouble();
		double monthlyInterestRate = annualInterestRate / 1200;

		System.out.print("Enter maturity period (number of months): ");
		double numberOfMonths = input.nextInt();

		System.out.printf("%5s%20s\n", "Month", "CD Value");
		double currentValue = deposit;
		for (int i = 1; i <= numberOfMonths; i++) {
			currentValue = currentValue * (1 + monthlyInterestRate);
			System.out.printf("%5d%20.2f\n", i, currentValue);
		}
	}
}
