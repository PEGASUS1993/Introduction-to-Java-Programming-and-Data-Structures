public class Exercise24_04 {
  public static void main(String[] args) {
    final int LIMIT = 50;
    int count = 0;
    GenericStack<Integer> stack = new GenericStack<Integer>();

    // Repeatedly find prime numbers
    for (int number = 2; count < LIMIT; number++)
      if (isPrime(number)) {
        stack.push(number);
        count++; // Increase the prime number count
      }

    // Print the first 50 prime numbers in decreasing order
    System.out.println("The first 50 prime numbers are \n");
    final int NUMBER_PER_LINE = 10;

    while (!stack.isEmpty()) {
      System.out.print(stack.pop() + " ");

      if (stack.getSize() % NUMBER_PER_LINE == 0)
        System.out.println(); // advance to the new line
    }
  }

  public static boolean isPrime(int number) {
    // Assume the number is prime
    boolean isPrime = true;

    // Exercise03_21 if number is prime
    for (int divisor = 2; divisor <= number / 2; divisor++) {
      //If true, the number is not prime
      if (number % divisor == 0) {
        // Set isPrime to false, if the number is not prime
        isPrime = false;
        break; // Exit the for loop
      }
    }

    return isPrime;
  }
  
  public static class GenericStack<E> {
    private java.util.ArrayList<E> list = new java.util.ArrayList<E>();

    public int getSize() {
      return list.size();
    }

    public E peek() {
      return list.get(getSize() - 1);
    }

    public void push(E o) {
      list.add(o);
    }

    public E pop() {
      E o = list.get(getSize() - 1);
      list.remove(getSize() - 1);
      return o;
    }

    public boolean isEmpty() {
      return list.isEmpty();
    }

    @Override
    public String toString() {
      return "stack: " + list.toString();
    }
  }

}
