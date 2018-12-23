public class Exercise05_25 {
  public static void main(String[] args) {
    double pi = 1;
    int sign = 1;
    
    for (int i = 2; i <= 100000; i++) {
      sign = -sign;
      pi += sign / (2 * i - 1.0);
       
      if (i % 10000 == 0)
        System.out.println("i: " + i + " The PI is " + 4 * pi);
    }
  }
}

/** Version 2
public class Exercise05_25 {
  public static void main(String[] args) {
    double pi = 0;
    double term;
    int sign = 1;

    for (int i = 1; i <= 100000; i++) {
      term = sign * 4.0 / (2 * i - 1);
      pi += term;
      sign = -1 * sign;

      if (i == 9999 || i == 10000 || i == 20000 || i == 30000 || i == 40000 ||
          i == 50000 || i == 60000 || i == 70000 || i == 80000 ||
          i == 90000 || i == 100000)
      System.out.println("The PI is " + pi + " for i = " + i);
    }
  }
}
*/

/** Version 3 

public class Exercise05_25 {
  public static void main(String[] args) {
    double pi = 0;

    for (int i = 1; i <= 100000; i += 2) {
      pi += 1.0 / (2 * i - 1) - 1.0 / (2 * i + 1);

      if (i == 10001 || i == 20000 || i == 30000 || i == 40000 ||
          i == 50000 || i == 60000 || i == 70000 || i == 80000 ||
          i == 90000 || i == 100000)
      System.out.println("The PI is " + 4 * pi + " for i = " + i);
    }
  }
}

*/
