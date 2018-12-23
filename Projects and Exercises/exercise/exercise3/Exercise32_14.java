import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class Exercise32_14 {
  public static void main(String[] args) {
    final int SIZE = 7000000;
    int[] list1 = new int[SIZE];
    int[] list2 = new int[SIZE];

    for (int i = 0; i < list1.length; i++)
      list1[i] = list2[i] = (int)(Math.random() * 10000000);

    long startTime = System.currentTimeMillis();
    parallelQuickSort(list1); // Invoke parallel merge sort
    long endTime = System.currentTimeMillis();
    System.out.println("\nParallel time with "
      + Runtime.getRuntime().availableProcessors() + 
      " processors is " + (endTime - startTime) + " milliseconds");

    startTime = System.currentTimeMillis();
    QuickSort.quickSort(list2); 
    endTime = System.currentTimeMillis();
    System.out.println("\nSequential time is " + 
      (endTime - startTime) + " milliseconds");
  }

  public static void parallelQuickSort(int[] list) {
    RecursiveAction mainTask = new SortTask(list, 0, list.length - 1);
    ForkJoinPool pool = new ForkJoinPool();
    pool.invoke(mainTask);
  }

  private static class SortTask extends RecursiveAction {
    private final int THRESHOLD = 500;
    private int[] list;
    private int first, last;
    
    SortTask(int[] list, int first, int last) {
      this.list = list;
      this.first = first;
      this.last = last;
    }

    @Override
    protected void compute() {
      if (list.length < THRESHOLD)
        java.util.Arrays.sort(list);
      else {
        if (last > first) {
          int pivotIndex = QuickSort.partition(list, first, last);
  
          // Recursively sort the two parts
          invokeAll(new SortTask(list, first, pivotIndex - 1), 
            new SortTask(list, pivotIndex + 1, last));
        }
      }
    }
  }
}
