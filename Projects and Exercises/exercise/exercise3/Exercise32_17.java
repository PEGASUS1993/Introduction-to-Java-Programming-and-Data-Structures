import java.util.concurrent.*;
import java.util.ArrayList;

public class Exercise32_17 {
  public static void main(String[] args) {
    // Create a list
    final int N = 2000;
    double[][] matrix1 = new double[N][N];
    for (int i = 0; i < matrix1.length; i++)
      for (int j = 0; j < matrix1[i].length; j++)
        matrix1[i][j] = 1;

    double[][] matrix2 = new double[N][N];
    for (int i = 0; i < matrix2.length; i++)
      for (int j = 0; j < matrix2[i].length; j++)
        matrix2[i][j] = 1;
    
    long startTime = System.currentTimeMillis();
    double[][] result = parallelMultiplyMatrix(matrix1, matrix2);
    long endTime = System.currentTimeMillis();
    System.out.println("The number of processors is " + 
      Runtime.getRuntime().availableProcessors()); 
    System.out.println("Time is " + (endTime - startTime) 
      + " milliseconds"); 
    
    startTime = System.currentTimeMillis();
    result = multiplyMatrix(matrix1, matrix2);
    endTime = System.currentTimeMillis();
    System.out.println("Sequential time is " + (endTime - startTime) 
      + " milliseconds"); 
  }
  
  public static double[][] multiplyMatrix(double[][] a, double[][] b) {
    double[][] result = new double[a.length][b[0].length];
    for (int i = 0; i < result.length; i++)
      for (int j = 0; j < result[0].length; j++) 
        for (int k = 0; k < a[0].length; k++)
          result[i][j] += a[i][k] * b[k][j];
    
    return result;
  }
  
  public static double[][] parallelMultiplyMatrix( 
      double[][] a, double[][] b) {
    double[][] result = new double[a.length][b[0].length];
    RecursiveAction task = new SumTask(a, b, result);
    ForkJoinPool pool = new ForkJoinPool();
    pool.invoke(task);
    return result;
  }
 
  private static class SumTask extends RecursiveAction {
    private double[][] a;
    private double[][] b;
    private double[][] c;

    public SumTask(double[][] a, double[][] b, double[][] c) {
      this.a = a;
      this.b = b;
      this.c = c;
    }

    @Override
    public void compute() {
      ArrayList<RecursiveAction> tasks = new ArrayList<RecursiveAction>();
      for (int i = 0; i < c.length; i++)
        for (int j = 0; j < c[0].length; j++)
          tasks.add(new MultiplyOneRow(i, j));
      
      invokeAll(tasks);
    }
    
    public class MultiplyOneRow extends RecursiveAction {
      int i;
      int j;
      
      public MultiplyOneRow(int i, int j) {
        this.i = i;
        this.j = j;
      }
      
      @Override
      public void compute() {
        c[i][j] = 0;
        for (int k = 0; k < a[0].length; k++)
          c[i][j] += a[i][k] * b[k][j];
      }
    }
  }
}
