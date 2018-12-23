import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise32_12BeforeJDK7 {
  public static void main(String[] args) {
    final int N = 5000000;
        
    // Create a list
    double[] list = new double[N];
    for (int i = 0; i < list.length; i++)
      list[i] = Math.random() * 100000;

    for (int numberOfThreads = 1; numberOfThreads <= 4; numberOfThreads++) {
      long startTime = System.currentTimeMillis();
      System.out.println("\nThe maximal number is " + max(numberOfThreads, list));
      long endTime = System.currentTimeMillis();
      System.out.println("Time with " + numberOfThreads + " threads: " + (endTime - startTime));
    }
  }
 
  public static double max(int numberOfThreads, double[] list) {    
    // Create a fixed thread pool with the specified number of threads
    ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
    
    // Submit runnable tasks to the executor
    int numberOfElementsInEachThread = list.length / numberOfThreads;
    
    Task[] tasks = new Task[numberOfThreads];
    
    int i = 0;
    for ( ; i < numberOfThreads - 1; i++) {
      tasks[i] = new Task(i * numberOfElementsInEachThread, (i + 1) * numberOfElementsInEachThread, list);
      executor.execute(tasks[i]);
    }
    tasks[i] = new Task(i * numberOfElementsInEachThread, list.length, list);
    executor.execute(tasks[i]);
    
    // Shut down the executor
    executor.shutdown();

    // Wait until all tasks are finished
    while (!executor.isTerminated()) {
      try {
        Thread.sleep(1);
      }
      catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }
    
    double result = tasks[0].getMax();
    
    for (int k = 1; k < tasks.length; k++)
      if (result < tasks[k].getMax())
        result = tasks[k].getMax();

    return result;
  }
  
  static class Task implements Runnable {
    private int startRow, endRow;
    private double[] list;
    private double max;
    
    Task(int startRow, int endRow, double[] list) {
      this.startRow = startRow;
      this.endRow = endRow;
      this.list = list;
    }
    
    public void run() {
      max = list[startRow];
      for (int i = startRow; i < endRow; i++)
        if (max < list[i])
          max = list[i];  
    }
    
    double getMax() {
      return max;
    }
  }
}
