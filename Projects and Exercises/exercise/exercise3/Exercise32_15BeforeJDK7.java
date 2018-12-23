import java.util.concurrent.*;

public class Exercise32_15BeforeJDK7 {
  /** Main method */
  public static void main(String[] args) {
    
    Runtime runtime = Runtime.getRuntime();
    System.out.println(runtime.availableProcessors());
      
    final int N = 10;
    
    // Create matrix1
    double[][] matrix1 = new double[N][N];
    for (int i = 0; i < matrix1.length; i++)
      for (int j = 0; j < matrix1[i].length; j++) {
        matrix1[i][j] = 1;
      }
    
    // Create matrix2
    double[][] matrix2 = new double[N][N];
    for (int i = 0; i < matrix1.length; i++)
      for (int j = 0; j < matrix1[i].length; j++) {
        matrix2[i][j] = 1;
      }

    // Multiply two matrices and print the result
    for (int numberOfThreads = 1; numberOfThreads <= 4; numberOfThreads++) {
      long startTime = System.currentTimeMillis();
      double[][] resultMatrix = multiplyMatrix(numberOfThreads , matrix1, matrix2);
      long endTime = System.currentTimeMillis();
      System.out.println("\nTime with " + numberOfThreads + " threads: " + (endTime - startTime));
      System.out.println("\nThe multiplication of the matrices is ");
      printResult(matrix1, matrix2, resultMatrix, '*');
    }
  }
 
  /** The method for multiplying two matrices */
  public static double[][] multiplyMatrix(int numberOfThreads,
      double[][] m1, double[][] m2) {    
    double[][] result = new double[m1.length][m2[0].length];
    
    // Create a fixed thread pool with the specified number of threads
    ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
    
    // Submit runnable tasks to the executor
    int rowsInEachThread = m1.length / numberOfThreads;
    
    int i = 0;
    for ( ; i < numberOfThreads - 1; i++) {
      executor.execute(new Task(i * rowsInEachThread, (i + 1) * rowsInEachThread, m1, m2, result));
    }
    executor.execute(new Task(i * rowsInEachThread, m1.length, m1, m2, result)); // The resulting rows for the last thread
    
    // Shut down the executor
    executor.shutdown();

    // Wait until all tasks are finished
    while (!executor.isTerminated()) {
      try {
        Thread.sleep(50);
      }
      catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }

    return result;
  }
  
  static class Task implements Runnable {
    private int startRow, endRow;
    private double[][] m1;
    private double[][] m2;
    private double[][] result;
    
    Task(int startRow, int endRow, double[][] m1, double[][] m2,
        double[][] result) {
      this.startRow = startRow;
      this.endRow = endRow;
      this.m1 = m1;
      this.m2 = m2;
      this.result = result;
    }
    
    public void run() {
      for (int i = startRow; i < endRow; i++)
        for (int j = 0; j < result.length; j++)
          for (int k = 0; k < result[0].length; k++)
             result[i][j] += m1[i][k] * m2[k][j];     
    }
  }

  /** Print result */
  public static void printResult(
      double[][] m1, double[][] m2, double[][] m3, char op) {
    for (int i = 0; i < m1.length; i++) {
      for (int j = 0; j < m1[0].length; j++)
        System.out.print(" " + m1[i][j]);

      if (i == m1.length / 2)
        System.out.print( "  " + op + "  " );
      else
        System.out.print( "     " );

      for (int j = 0; j < m2[0].length; j++)
        System.out.print(" " + m2[i][j]);

      if (i == m1.length / 2)
        System.out.print( "  =  " );
      else
        System.out.print( "     " );

      for (int j = 0; j < m3[0].length; j++)
        System.out.print(" " + m3[i][j]);

      System.out.println();
    }
  }
}
