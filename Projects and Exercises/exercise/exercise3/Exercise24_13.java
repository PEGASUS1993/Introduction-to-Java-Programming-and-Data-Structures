import java.util.Iterator;

public class Exercise24_13 {
  public static void main(String[] args) {
    Iterator<Integer> iterator = new FibonacciIterator(1000000);
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }
  
  static class FibonacciIterator implements java.util.Iterator<Integer> {
    private int limit;
    private int f0 = 1;
    private int f1 = 1;
    
    public FibonacciIterator(int limit) {
      this.limit = limit;
    }
    
    @Override 
    public Integer next() {
      int temp = f0;
      f0 = f1;
      f1 = temp + f0; 
      
      
      return f0;
    }
    
    @Override 
    public boolean hasNext() {
      if (f1 > limit)
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
