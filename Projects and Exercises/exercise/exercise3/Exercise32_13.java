import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class Exercise32_13 {
  public static void main(String[] args) {
    final int SIZE = 700000;
    Integer[] list1 = new Integer[SIZE];
    Integer[] list2 = new Integer[SIZE];

    for (int i = 0; i < list1.length; i++) {
      list1[i] = new Integer((int)(Math.random() * 10000000));
      list2[i] = new Integer((int)(Math.random() * 10000000));
    }
    
    long startTime = System.currentTimeMillis();
    Exercise32_13.<Integer>parallelMergeSort(list1); // Invoke parallel merge sort
    long endTime = System.currentTimeMillis();
    System.out.println("\nParallel time with "
      + Runtime.getRuntime().availableProcessors() + 
      " processors is " + (endTime - startTime) + " milliseconds");

    startTime = System.currentTimeMillis();
    Exercise23_02.<Integer>mergeSort(list2); // MergeSort is in Listing 24.5
    endTime = System.currentTimeMillis();
    System.out.println("\nSequential time is " + 
      (endTime - startTime) + " milliseconds");
  }

  public static <E extends Comparable<E>> void parallelMergeSort(E[] list) {
    RecursiveAction mainTask = new SortTask(list);
    ForkJoinPool pool = new ForkJoinPool();
    pool.invoke(mainTask);
  }

  private static class SortTask<E extends Comparable<E>> extends RecursiveAction {
    private final int THRESHOLD = 500;
    private E[] list;

    SortTask(E[] list) {
      this.list = list;
    }

    @Override
    protected void compute() {
      if (list.length < THRESHOLD)
        java.util.Arrays.sort(list);
      else {
        // Obtain the first half
        E[] firstHalf = (E[])(new Comparable[list.length / 2]);
        System.arraycopy(list, 0, firstHalf, 0, list.length / 2);

        // Obtain the second half
        int secondHalfLength = list.length - list.length / 2;
        E[] secondHalf = (E[])new Comparable[secondHalfLength];
        System.arraycopy(list, list.length / 2, 
          secondHalf, 0, secondHalfLength);

        // Recursively sort the two halves
        invokeAll(new SortTask(firstHalf), 
          new SortTask(secondHalf));

        // Merge firstHalf with secondHalf into list
        Exercise23_02.merge(firstHalf, secondHalf, list);
      }
    }
  }
}
