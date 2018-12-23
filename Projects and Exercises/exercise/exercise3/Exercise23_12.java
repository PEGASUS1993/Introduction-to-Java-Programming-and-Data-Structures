public class Exercise23_12 {
  public static void main(String[] args) {
    int[] list = new int[100];
    
    for (int i = 0; i < list.length; i++)
      list[i] = (int)(Math.random() * 1000);
    
    radixSort(list, 3);
    
    for (int i = 0; i < list.length; i++)
      System.out.print(list[i] + " ");
  }
  
  /** Sort the int array list. numberOfDigits is the number of digits
   * in the largest number in the array */
  public static void radixSort(int[] list, int numberOfDigits) {
    java.util.ArrayList<Integer>[] buckets = new java.util.ArrayList[10];
    for (int i = 0; i < buckets.length; i++) {
      buckets[i] = new java.util.ArrayList<Integer>();
    }

    for (int position = 0; position <= numberOfDigits; position++) {
      // Clear buckets
      for (int i = 0; i < buckets.length; i++) {
        buckets[i].clear();
      }      
      
      // Distribute the elements from list to buckets
      for (int i = 0; i < list.length; i++) {
        int key = getKey(list[i], position);
        buckets[key].add(list[i]);
      }

      // Now move the elements from the buckets back to list
      int k = 0; // k is an index for list
      for (int i = 0; i < buckets.length; i++) {
        for (int j = 0; j < buckets[i].size(); j++)
          list[k++] = buckets[i].get(j);
      }
    }
  }

  /** Return the digit at the specified position. 
   * The last digit's position is 0. */
  public static int getKey(int number, int position) {
    int result = 1;
    for (int i = 0; i < position; i++)
      result *= 10;

    return (number / result) % 10;
  }
}
