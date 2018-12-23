import java.util.ArrayList;

public class Exercise13_08 {
  public static void main(String[] args) {
    MyStack1 stack = new MyStack1();
    stack.push("S1");
    stack.push("S2");
    stack.push("S");

    MyStack1 stack2 = (MyStack1) (stack.clone());
    stack2.push("S1");
    stack2.push("S2");
    stack2.push("S");

    System.out.println(stack.getSize());
    System.out.println(stack2.getSize());
  }
}

class MyStack1 implements Cloneable {
  private ArrayList<Object> list = new ArrayList<>();

  public boolean isEmpty() {
    return list.isEmpty();
  }

  public int getSize() {
    return list.size();
  }

  public Object peek() {
    return list.get(getSize() - 1);
  }

  public Object pop() {
    Object o = list.get(getSize() - 1);
    list.remove(getSize() - 1);
    return o;
  }

  public void push(Object o) {
    list.add(o);
  }

  /** Override the toString in the Object class */
  public String toString() {
    return "stack: " + list.toString();
  }

  public Object clone() {
    try {
      MyStack1 c = (MyStack1) super.clone();
      c.list = (ArrayList<Object>) this.list.clone();
      return c;
    } catch (CloneNotSupportedException ex) {
      return null;
    }
  }
}
