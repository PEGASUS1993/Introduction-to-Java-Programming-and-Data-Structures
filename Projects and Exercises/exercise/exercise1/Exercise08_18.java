public class Exercise08_18 {
  public static void main(String[] args) {    
    int[][] m = {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}};
    
    shuffle(m);
    
    for (int i = 0; i < m.length; i++) {
      for (int j = 0; j < m[i].length; j++)
        System.out.print(m[i][j] + " ");
      System.out.println();
    }
  }
  
  public static void shuffle(int[][] m) {
    for (int i = 0; i < m.length; i++) {
      int row = (int)(Math.random() * m.length);

      // Swap m[row] with m[i]
      int[] temp = m[i];
      m[i] = m[row]; 
      m[row] = temp;
    }
  }
}
