import java.util.Iterator;

public class Exercise24_14 {
  public static void main(String[] args) {
    Iterator<Integer> iterator = new PrimeIterator(1000);
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }
  
  static class PrimeIterator implements java.util.Iterator<Integer> {
    private int limit;
    private int current = 1;
    
    public PrimeIterator(int limit) {
      this.limit = limit;
    }
    
    @Override 
    public Integer next() {
      return current;
    }
    
    static boolean isPrime(int number) {
      for (int divisor = 2; divisor < number; divisor++)
        if (number % divisor == 0)
          return false;
      return true;
    }
    
    @Override 
    public boolean hasNext() {
      current++;
      
      while (true) {
        if (isPrime(current))
          break;
        current++;
      }
      
      if (current >= limit)
        return false;
      else 
        return true;
    }
    
    @Override 
    public void remove() {
      throw new UnsupportedOperationException
        ("Method not supported");
    }
  }
  
}
