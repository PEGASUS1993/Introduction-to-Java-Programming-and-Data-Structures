import java.util.*;

public class Exercise24_01 {
  public static void main(String[] args) {
    new Exercise24_01();
  }

  public Exercise24_01() {
//    String[] name1 = {"Tom", "George", "Peter", "Jean", "Jane"};
//    String[] name2 = {"Tom", "George", "Michael", "Michelle", "Daniel"};
//    String[] name3 = {"Tom", "Peter"};
    
    Scanner input = new Scanner(System.in);
    String[] name1 = new String[5];
    String[] name2 = new String[5];
    String[] name3 = new String[2];
    System.out.print("Enter five strings for array name1 separated by space: ");
    for (int i = 0; i < 5; i++) {
      name1[i] = input.next();
    }
    
    System.out.print("Enter five strings for array name2 separated by space: ");
    for (int i = 0; i < 5; i++) {
      name2[i] = input.next();
    }

    System.out.print("Enter two strings for array name3 separated by space: ");
    for (int i = 0; i < 2; i++) {
      name3[i] = input.next();
    }
    
    MyList<String> list1 = new MyArrayList<>(name1);   
    MyList<String> list2 = new MyArrayList<>(name2);   
    System.out.println("list1:" + list1);
    System.out.println("list2:" + list2);
    list1.addAll(list2);
    System.out.println("After addAll:" + list1 + "\n");
    
    list1 = new MyArrayList<>(name1);
    list2 = new MyArrayList<>(name2);   
    System.out.println("list1:" + list1);
    System.out.println("list2:" + list2);
    list1.removeAll(list2);
    System.out.println("After removeAll:" + list1 + "\n");
    
    list1 = new MyArrayList<>(name1);
    list2 = new MyArrayList<>(name2);   
    System.out.println("list1:" + list1);
    System.out.println("list2:" + list2);
    list1.retainAll(list2);
    System.out.println("After retainAll:" + list1 + "\n");
    
    list1 = new MyArrayList<>(name1);
    list2 = new MyArrayList<>(name2);   
    System.out.println("list1:" + list1);
    System.out.println("list2:" + list2);
    list1.retainAll(list2);
    System.out.println("list1 contains all list2? " + list1.containsAll(list2) + "\n");
    
    list1 = new MyArrayList<>(name1);
    list2 = new MyArrayList<>(name3);
    System.out.println("list1:" + list1);
    System.out.println("list2:" + list2);
    System.out.println("list1 contains all list2? " + list1.containsAll(list2) + "\n");
    
    Object[] name4 = list1.toArray();
    System.out.print("name4: ");
    for (Object e: name4) {
      System.out.print(e + " ");
    }
    
    String[] name5 = new String[list1.size()];
    String[] name6 = list1.toArray(name5);
    System.out.print("\nname6: ");
    for (Object e: name6) {
      System.out.print(e + " ");
    }
  }

  public interface MyList<E> extends java.util.Collection<E> {
    /** Add a new element at the specified index in this list */
    public void add(int index, E e);

    /** Return the element from this list at the specified index */
    public E get(int index);

    /** Return the index of the first matching element in this list.
     *  Return -1 if no match. */
    public int indexOf(Object e);

    /** Return the index of the last matching element in this list
     *  Return -1 if no match. */
    public int lastIndexOf(E e);

    /** Remove the element at the specified position in this list
     *  Shift any subsequent elements to the left.
     *  Return the element that was removed from the list. */
    public E remove(int index);

    /** Replace the element at the specified position in this list
     *  with the specified element and returns the new set. */
    public E set(int index, E e);
    
    @Override /** Add a new element at the end of this list */
    public default boolean add(E e) {
      add(size(), e);
      return true;
    }

    @Override /** Return true if this list contains no elements */
    public default boolean isEmpty() {
      return size() == 0;
    }

    @Override /** Remove the first occurrence of the element e 
     *  from this list. Shift any subsequent elements to the left.
     *  Return true if the element is removed. */
    public default boolean remove(Object e) {
      if (indexOf(e) >= 0) {
        remove(indexOf(e));
        return true;
      }
      else
        return false;
    }

    @Override
    public default boolean containsAll(Collection<?> c) {
      for (Object e: c)
        if (!this.contains(e))
          return false;
      
      return true;
    }
    
    /** Adds the elements in otherList to this list.
     * Returns true if this list changed as a result of the call */
    @Override
    public default boolean addAll(Collection<? extends E> c) {
      for (E e: c)
        this.add(e);
      
      return c.size() > 0;
    }
  
    /** Removes all the elements in otherList from this list 
      * Returns true if this list changed as a result of the call */
    @Override
    public default boolean removeAll(Collection<?> c) {
      boolean changed = false;
      for (Object e: c) {
        if (remove(e))
          changed = true;
      }
        
      return changed;
    }  
    
    /** Retains the elements in this list that are also in otherList 
      * Returns true if this list changed as a result of the call */
    @Override
    public default boolean retainAll(Collection<?> c) {
      boolean changed = false;
      for (int i = 0; i < this.size(); ) {
        if (!c.contains(this.get(i))) {
          this.remove(get(i));
          changed = true;
        }
        else
          i++;
      }
        
      return changed;
    }

    @Override
    public default Object[] toArray() {
      // Left as an exercise
      Object[] result = new Object[size()];
      for (int i = 0; i < size(); i++) {
        result[i] = this.get(i);
      }
      return result;
    }

    @Override
    public default <T> T[] toArray(T[] a) {
      // Left as an exercise
      for (int i = 0; i < size(); i++) {
        a[i] = (T)this.get(i);
      }
      return a;
    }
  }

  public class MyArrayList<E> implements MyList<E> {
    public static final int INITIAL_CAPACITY = 16;
    private E[] data = (E[])new Object[INITIAL_CAPACITY];
    private int size = 0; // Number of elements in the list

    /** Create an empty list */
    public MyArrayList() {
    }

    /** Create a list from an array of objects */
    public MyArrayList(E[] objects) {
      for (int i = 0; i < objects.length; i++)
        add(objects[i]); // Warning: don’t use super(objects)! 
    }

    @Override /** Add a new element at the specified index */
    public void add(int index, E e) {   
      ensureCapacity();

      // Move the elements to the right after the specified index
      for (int i = size - 1; i >= index; i--)
        data[i + 1] = data[i];

      // Insert new element to data[index]
      data[index] = e;

      // Increase size by 1
      size++;
    }

    /** Create a new larger array, double the current size + 1 */
    private void ensureCapacity() {
      if (size >= data.length) {
        E[] newData = (E[])(new Object[size * 2 + 1]);
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
      }
    }

    @Override /** Clear the list */
    public void clear() {
      data = (E[])new Object[INITIAL_CAPACITY];
      size = 0;
    }

    @Override /** Return true if this list contains the element */
    public boolean contains(Object e) {
      for (int i = 0; i < size; i++)
        if (e.equals(data[i])) return true;

      return false;
    }

    @Override /** Return the element at the specified index */
    public E get(int index) {
      checkIndex(index);
      return data[index];
    }

    private void checkIndex(int index) {
      if (index < 0 || index >= size)
        throw new IndexOutOfBoundsException
          ("Index: " + index + ", Size: " + size);
    }
    
    @Override /** Return the index of the first matching element 
     *  in this list. Return -1 if no match. */
    public int indexOf(Object e) {
      for (int i = 0; i < size; i++)
        if (e.equals(data[i])) return i;

      return -1;
    }

    @Override /** Return the index of the last matching element 
     *  in this list. Return -1 if no match. */
    public int lastIndexOf(E e) {
      for (int i = size - 1; i >= 0; i--)
        if (e.equals(data[i])) return i;

      return -1;
    }

    @Override /** Remove the element at the specified position 
     *  in this list. Shift any subsequent elements to the left.
     *  Return the element that was removed from the list. */
    public E remove(int index) {
      checkIndex(index);
      
      E e = data[index];

      // Shift data to the left
      for (int j = index; j < size - 1; j++)
        data[j] = data[j + 1];

      data[size - 1] = null; // This element is now null

      // Decrement size
      size--;

      return e;
    }

    @Override /** Replace the element at the specified position 
     *  in this list with the specified element. */
    public E set(int index, E e) {
      checkIndex(index);
      E old = data[index];
      data[index] = e;
      return old;
    }

    @Override
    public String toString() {
      StringBuilder result = new StringBuilder("[");

      for (int i = 0; i < size; i++) {
        result.append(data[i]);
        if (i < size - 1) result.append(", ");
      }

      return result.toString() + "]";
    }

    /** Trims the capacity to current size */
    public void trimToSize() {
      if (size != data.length) { 
        E[] newData = (E[])(new Object[size]);
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
      } // If size == capacity, no need to trim
    }

    @Override /** Override iterator() defined in Iterable */
    public java.util.Iterator<E> iterator() {
      return new ArrayListIterator();
    }
   
    private class ArrayListIterator 
        implements java.util.Iterator<E> {
      private int current = 0; // Current index 

      @Override
      public boolean hasNext() {
        return (current < size);
      }

      @Override
      public E next() {
        return data[current++];
      }

      @Override
      public void remove() {
        MyArrayList.this.remove(current);
      }
    }
    
    @Override /** Return the number of elements in this list */
    public int size() {
      return size;
    }
  }
}
