import java.util.concurrent.*;

public class Exercise32_13BeforeJDK7 {
  /** Main method */
  public static void main(String[] args) {
    final int N = 10000;
        
    // Create matrix1
    double[] list1 = new double[N];
    for (int i = 0; i < list1.length; i++)
      list1[i] = (int)(Math.random() * 100);

    // Multiply two matrices and print the result
    for (int numberOfThreads = 1; numberOfThreads <= 4; numberOfThreads++) {
      long startTime = System.currentTimeMillis();
      double[] list = list1.clone();
      quickSort(numberOfThreads, list);
      long endTime = System.currentTimeMillis();
     // System.out.println("The list is " + Arrays.toString(list));
      System.out.println("Time with " + numberOfThreads + " threads: " + (endTime - startTime));
    } 
  }
 
  public static double[] quickSort(int numberOfThreads, double[] list) {    
    // Create a fixed thread pool with maximum three threads
    // ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
    
    ThreadPoolExecutor executor = new ThreadPoolExecutor(numberOfThreads, numberOfThreads, 1000,
       TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));    
    executor.execute(new Task(executor, list, 0, list.length - 1));
    System.out.println("active count " + executor.getActiveCount());

    while (executor.getActiveCount() > 0) {
      try {
        Thread.sleep(10);
      }
      catch (InterruptedException ex) {
        ex.printStackTrace();
      } 
    }
    
    System.out.println("active count " + executor.getActiveCount());

    return list;
  }
  
  static class Task implements Runnable {
    private int low, high;
    private double[] list;
    ExecutorService executor;
    
    Task(ExecutorService executor, double[] list, int low, int high) {
      this.executor = executor;
      this.list = list;
      this.low = low;
      this.high = high;
    }
    
    public void run() {
      if (high > low) {
        int pivotIndex = partition(list, low, high);
        executor.execute(new Task(executor, list, low, pivotIndex - 1));
        executor.execute(new Task(executor, list, pivotIndex + 1, high));
      }
    }
  }
  
  /** Partition the array list[first..last] */
  private static int partition(double[] list, int first, int last) {
    double pivot = list[first]; // Choose the first element as the pivot
    int low = first + 1; // Index for forward search
    int high = last; // Index for backward search

    while (high > low) {
      // Search forward from left
      while (low <= high && list[low] <= pivot)
        low++;

      // Search backward from right
      while (low <= high && list[high] > pivot)
        high--;

      // Swap two elements in the list
      if (high > low) {
        double temp = list[high];
        list[high] = list[low];
        list[low] = temp;
      }
    }

    while (high > first && list[high] >= pivot)
      high--;

    // Swap pivot with list[high]
    if (pivot > list[high]) {
      list[first] = list[high];
      list[high] = pivot;
      return high;
    }
    else {
      return first;
    }
  }
}
