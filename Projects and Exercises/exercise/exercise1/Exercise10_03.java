public class Exercise10_03 {
  public static void main(String[] args) {
    MyInteger n1 = new MyInteger(5);
    System.out.println("n1 is even? " + n1.isEven());
    System.out.println("n1 is prime? " + n1.isPrime());
    System.out.println("15 is prime? " + MyInteger.isPrime(15));

    char[] chars = {'3', '5', '3', '9'};
    System.out.println(MyInteger.parseInt(chars));

    String s = "3539";
    System.out.println(MyInteger.parseInt(s));
    
    MyInteger n2 = new MyInteger(24);
    System.out.println("n2 is odd? " + n2.isOdd());
    System.out.println("45 is odd? " + MyInteger.isOdd(45));
    System.out.println("n1 is equal to n2? " + n1.equals(n2));
    System.out.println("n1 is equal to 5? " + n1.equals(5));
  }
}

class MyInteger {
  private int value;

  public int getValue() {
    return value;
  }

  public MyInteger(int value) {
    this.value = value;
  }

  public boolean isPrime() {
    return isPrime(value);
  }

  public static boolean isPrime(int num) {	
    if ((num == 1) || (num == 2)) {
      return true;
    }

    for (int i = 2; i <= num / 2; i++) {
      if (num % i == 0) {
        return false;
      }
    }

    return true;
  }

  public static boolean isPrime(MyInteger o) {
    return isPrime(o.getValue());
  }

  public boolean isEven() {
    return isEven(value);
  }
  
  public boolean isOdd() {
	return isOdd(value); 
  }
  
  public static boolean isEven(int n) {
    return n % 2 == 0;
  }
  
  public static boolean isOdd(int n) {
	return n % 2 != 0;
  }

  public static boolean isEven(MyInteger n) {
    return isEven(n.getValue());
  }

  public boolean equals(int anotherNum) {
    return value == anotherNum;
  }

  public boolean equals(MyInteger o) {
    return value == o.getValue();
  }

  public static int parseInt(char[] numbers) {
    // numbers consists of digit characters.
    // For example, if numbers is {'1', '2', '5'}, the return value
    //  should be 125. Please note that
    // numbers[0] is '1'
    // numbers[1] is '2'
    // numbers[2] is '5'

    int result = 0;
    for (int i = 0; i < numbers.length; i++) {
      result = result * 10 + (numbers[i] - '0');
    }

    return result;
  }

  // You may mention this when you covered Ch8
  public static int parseInt(String s) {
    // s consists of digit characters.
    // For example, if s is "125", the return value
    //  should be 125.
    int result = 0;
    for (int i = 0; i < s.length(); i++) {
      result = result * 10 + (s.charAt(i) - '0');
    }

    return result;
  }
}
