public class Exercise22_06 {
  public static void main(String[] args) {
		// Find the first 47 Fibonacci numbers
    final int INDEX = 47;
    int[] numbers = new int[INDEX];
		numbers[0] = 0;
		numbers[1] = 1;
		for (int i = 2; i < INDEX; i++) {
			numbers[i] = numbers[i - 1] + numbers[i - 2];
		}

		System.out.println("\t\t\t40\t41\t42\t43\t44\t45");
		System.out.println("-----------------------------------------------");
		System.out.print("Listing 23.2 GCD1");

		long[] executionTime = new long[6];

		for (int i = 40; i <= 45; i++) {
	    long startTime = System.currentTimeMillis();
      gcd1(numbers[i], numbers[i + 1]);
      executionTime[i - 40] = System.currentTimeMillis() - startTime;
    }

		for (int i = 0; i <= 5; i++) {
      System.out.print("\t" + executionTime[i]);
    }

		System.out.print("\nListing 23.3 GCD2");

		for (int i = 40; i <= 45; i++) {
	    long startTime = System.currentTimeMillis();
			gcd2(numbers[i], numbers[i + 1]);
			executionTime[i - 40] = System.currentTimeMillis() - startTime;
		}

		for (int i = 0; i <= 5; i++) {
			System.out.print("\t" + executionTime[i]);
    }
  }

	/** From Listing 21.2: Find gcd for intergers m and n */
	public static int gcd1(int m, int n) {
		int gcd = 1;

		if (m == n) return m;

		for (int k = 1; k <= m / 2 && k <= n / 2; k++) {
			if (m % k == 0 && n % k == 0)
				gcd = k;
		}

		return gcd;
	}

	/** From Listing 21.3: Find gcd for intergers m and n */
	public static int gcd2(int m, int n) {
		if (m % n == 0)
			return n;
		else
			return gcd2(n, m % n);
	}
}
