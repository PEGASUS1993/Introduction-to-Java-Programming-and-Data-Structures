import java.util.Scanner;

public class Exercise18_05Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("Enter the number of rows in the square matrix: ");
    int size = input.nextInt();

    int[][] m = new int[size][size];

    System.out.print("Enter the matrix row by row: ");

    for (int i = 0; i < m.length; i++)
      for (int j = 0; j < m[i].length; j++)
        m[i][j] = input.nextInt();

    Location maxLocation = new Location(1, 1, -1);
    for (int i = 0; i < m.length; i++)
      for (int j = 0; j < m[i].length; j++) {
        Location temp = getSize(i, j, m);
        if (maxLocation.size < temp.size)  
          maxLocation = temp;
      }
    
    System.out.println("The size of a maximum square submatrix is " 
      + maxLocation.size);
    
    System.out.println("The upper-left corner of the first largest "
    		+ "block is at (" + maxLocation.x + ", " + maxLocation.y +")");
  }

  public static Location getSize(int i, int j, int[][] m)  {
    int n = m.length;
    if (i == n - 1 || j == n - 1) {
      return new Location(i, j, m[i][j]);
    }
    
    if (m[i][j] == 1)
      return new Location(i, j, 1 + Math.min(getSize(i, j + 1, m).size, 
        Math.min(getSize(i + 1, j + 1, m).size, 
        		getSize(i + 1, j, m).size)));
    else 
      return new Location(i, j, 0);
  }
  
  public static class Location {
	public int x;
	public int y;
	public int size;
	  
	public Location(int x, int y, int size) {
	  this.x = x;
	  this.y = y;
	  this.size = size;
	}
  }
}