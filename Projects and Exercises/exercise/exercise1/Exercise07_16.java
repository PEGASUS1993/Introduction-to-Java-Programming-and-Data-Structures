public class Exercise07_16 {
  public static void main(String[] args) {
    int[] list = new int[100000];
    for (int i = 0; i < list.length; i++) {
      list[i] = (int)(Math.random() * 1000000);
    }

    int key = (int)(Math.random() * 1000000);
    long startTime = System.nanoTime();
    System.out.println(LinearSearch.linearSearch(list, key));
    long endTime = System.nanoTime();
    long executionTime = endTime - startTime;
    System.out.println("Execution time for linear search is " +
                       executionTime);

    java.util.Arrays.sort(list);
    startTime = System.nanoTime();
    System.out.println(java.util.Arrays.binarySearch(list, key));
    endTime = System.nanoTime();
    executionTime = endTime - startTime;
    System.out.println("Execution time for binary search is " +
                       executionTime);
  }
}
