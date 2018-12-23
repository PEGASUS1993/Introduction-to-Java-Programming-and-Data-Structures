import java.util.*;

public class Exercise20_12 {
  public static void main(String[] args) throws Exception {
    MyPriorityQueue<String> queue = new MyPriorityQueue<String>();
    queue.offer("red");
    queue.offer("green");
    queue.offer("yellow");
    
    MyPriorityQueue<String> queue1 = (MyPriorityQueue<String>)(queue.clone());
    
    System.out.print(queue1);
  }

  static class MyPriorityQueue<E> extends PriorityQueue<E> implements java.lang.Cloneable {
    public Object clone() throws CloneNotSupportedException {
      MyPriorityQueue<E> queue = new MyPriorityQueue<E>();
      
      for (E e: this) {
        queue.offer(e);
      }
      
      return queue;
    }
  }
}
