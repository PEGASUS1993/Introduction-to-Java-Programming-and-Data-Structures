public class QPSolver {

  public static enum QP_PARAM {
    MAXIMIZE, MINIMIZE
  };

  public static double[] dantzig(double[][] C, double[][] B, double[] a,
      double[] d) {
    return dantzig(C, B, a, d, false);
  }

  /*
   * The Dantzig algorithm comes from the book
   * "Quadratic Programming : Algorithms - Anomolies - Applications" by John
   * C.G. Boot ( Year : 1974, ISBN : None , ASIN : B0006BMI8K )
   * 
   * The algorithm creates a matrix consisting of the inputs. The format of
   * the matrix is as such :
   * 
   * +-------------+ | -B 0 I C | | C' I 0 0 | +-------------+
   * 
   * Where the top row has height N and the bottom row has height K all the
   * way across. The 0-matrix on the first row is an N x K matrix and the I is
   * a N x N identity matrix. C is a N x K matrix.
   * 
   * In the second row the C' is the transpose of the C matrix and therefore
   * has size K x N. The identity matrix has a size of K x K and the first
   * zero matrix is a K x N and the last 0 matrix is a K x K matrix.
   * 
   * Along with this matrix is a vector consisting of basic variables. The
   * basic variables are
   * 
   * called v_1, v_2, ... , v_N, and ?_(N+1) , ?_(N+1) , ... , ?_(N+K). The
   * values in this vector are :
   * 
   * +---+ | v |
   * +-----------------------------------------------------------+T | | = |
   * v_1 , v_2 , ... , v_N , ?_(N+1) , ?_(N+2) , ... , ?_(N+K) | | y |
   * +-----------------------------------------------------------+ +---+
   * 
   * Notation : p_i are the basic variables. ( The p_i corresponding to
   * z-variables are always non-negative ) t_i are the rows in the column
   * determined by the basic variable selected. ( Used in rules )
   * 
   * <b>Adding a Variable to the Basis</b> : If the tableau is in standard
   * form, that nonbasic z-variable (?_h) should enter the basis whose v_h has
   * (in absolute value) the largest negative p_h. If the tableau is
   * nonstandard, write (?_(k),v_k) for the nonbasic pair; then v_k should
   * enter the basis.
   * 
   * <b>Deleting a Variable from the Standard Basis</b> : Write ?_(h) for the
   * variable that enters the basis; then consider the ratios (p_i/t_i) for
   * all basic z-variables and for v_h. Delete the variable corresponding to
   * the smallest positive ratio.
   * 
   * <b>Deleting a Variable from a Nonstandard Basis</b> : Write v_k for the
   * variable the enters the basis; then consider the ratios (p_i/t_i) for all
   * basic variables and for v_h. Delete the variable corresponding to the
   * smallest positive ratio.
   */
  public static double[] dantzig(double[][] C, double[][] Q, double[] a,
      double[] d, boolean debug) {

    double DELTA = 0.0000000000000001;

    // Error checking

    // Make sure parameters are passed correctly
    if (a == null || C == null || Q == null || d == null) {
      return null;
    }

    // Dimension Checking

    // Has to be N x N
    if (Q.length == 0) {
      return null;
    }

    // If we have constraints
    if (C.length != 0) {
      // The width has to be the same as the
      // number of parameters in a
      if (C[0].length != a.length) {
        return null;
      }
    }

    // Must match for matrix operations
    if (a.length != Q.length || Q.length != Q[0].length
        || C.length != d.length) {
      return null;
    }

    // Begin Algorithm

    // Always starts in standard
    boolean isStandard = true;

    // Constants
    int k = C.length;
    int n = a.length;

    if (debug) {
      System.out.println("N : " + n);
      System.out.println("K : " + k);
    }

    int _mh = (n + k);
    int _mw = 2 * _mh;

//    if (debug) {
      System.out.println("_mh : " + _mh);
      System.out.println("_mw : " + _mw);
//    }

    // Temporary and other variables
    double[] basicVariables = new double[_mh]; // Holds the values of the
                          // Basic Variables
    int[] basicVar = new int[_mh]; // Basic variable mappings for swapping
    double[] x = new double[a.length]; // the return values are placed in
                      // here
    double[][] matrix = new double[_mh][_mw]; // The matrix defined in the
                          // class comments

    // Setup Basic Feasible Variables and their mappings

    // Add the v vector to the basic variables as
    // defined in class comments
    for (int i = 0; i < n; i++) {
      basicVar[i] = _mh + i;
      basicVariables[i] = -a[i];
    }

    // add y to the basic variables as defined
    // in class comments
    for (int i = 0; i < k; i++) {
      basicVar[n + i] = n + i;
      basicVariables[n + i] = d[i];
    }

    // Set up matrix

    // Top Left -B
    for (int row = 0; row < Q.length; row++) {
      for (int col = 0; col < Q[row].length; col++) {
        matrix[row][col] = -Q[row][col];
      }

      // Top identity matrix
      matrix[row][row + k + n] = 1.0d;
    }

    // Top right C and bottom left C'
    for (int row = 0; row < C.length; row++) {
      for (int col = 0; col < C[row].length; col++) {
        // Top Right C
        matrix[col][_mw - k + row] = -C[row][col];
        // Bottom Left C'
        matrix[row + n][col] = C[row][col];
      }
    }

    // Add bottom identity matrix
    for (int row = 0; row < k; row++) {
      matrix[row + n][n + row] = 1.0d;
    }

    // Temporary variables to keep track of what we are looking at
    int last_col = 0; // Last Column we used
    int last_v = 0;
    int v_h = 0; // Last v-variable we removed

    int removeNonBasic = 0;
    int selectedColumn = 0;

    // boolean loop = false;
    int iter = 0;
    while (true) {

      // Assume we are done
      boolean done = true;

      // Check to see if we are done
      for (int i = 0; i < basicVariables.length; i++) {
        if (basicVariables[i] < 0 && basicVar[i] >= _mh) {
          done = false;
          break;
        }
      }

      // If done then break out of while
      if (done) {
        break;
      }

      // debugging statements
      if (debug) {
        System.out.println("Standard Tableau : " + isStandard);
        printTableau(basicVar, basicVariables, matrix);
      }

      // Step 1 - Locate NonBasic Variable to
      // add to basic variables
      if (isStandard) {

        // always default to 0 ( forgot this once, disaster happened )
        int removeBasic = 0;
        // Iterate over the variables and find smallest value
        // ( Assumed not all positive due to check above )
        for (int i = 1; i < basicVariables.length; i++) {
          if (basicVariables[i] < basicVariables[removeBasic]) {
            removeBasic = i;
          }
        }

        // Map to ?-variables from v-variables
        v_h = basicVar[removeBasic];
        last_col = v_h;
        selectedColumn = v_h - _mh;
        
        // Need this incase going to nonstandard tableau

      } else {
        if (debug) {
          System.out.println("Last Entry : " + last_col);
        }

        // The current column is set to be the
        // corresponding column that goes with
        // the last v-variable removed from the
        // basis

        selectedColumn = last_v;
        v_h = last_col;

        // Is Standard
        isStandard = true;
      }
      
      
      System.out.println("v_h : " + v_h);
      System.out.println("Current iteration : " + iter );
      
      
      // BEGIN PROBLEM AREA 
      System.out.print("BasicVar : ");      
      
//      printMatrix(basicVar); // Function to print out vector
      System.out.print( basicVar[0] + "   ");
      for (int row = 1; row < basicVar.length; row++) {
        System.out.print( basicVar[row] + "   ");
      }
      System.out.println("}");
      System.out.flush();
      
      // END PROBLEM AREA 
      
      System.out.print("BasicVariables : ");
      printMatrix(basicVariables);

      if (debug) {
        System.out.println("Column Added : " + selectedColumn);
      }

      // Assume largest positive value
      double _tmp = Double.MAX_VALUE;


      // Step 2 - Locate basic variable to remove
      // from the basic variables
      for (int row = 0; row < _mh; row++) {
        
        // If zero then we can ignore
        if (matrix[row][selectedColumn] > DELTA
            || matrix[row][selectedColumn] < -DELTA) {
          
          
          // Is a basic-z variable or v_h
          if (basicVar[row] < _mh || basicVar[row] == (v_h)) {

            // Get p_i/t_i
            double t = basicVariables[row];
            t /= matrix[row][selectedColumn];

            // if p_i/t_i is less than the old value and greater
            // than 0
            if (t < _tmp && t > 0) {
              _tmp = t;
              // Save row to swap
              removeNonBasic = row;
            }
          }
        }
      }

      // Corresponds to the last v-variable that
      // exits the basis or the v-variable that
      // corresponds to the ?-variable that left
      // the basis
      last_v = basicVar[removeNonBasic];
      if (last_v < _mh) // If ?-variable that leaves
      {
        last_v += _mh;
      }

      if (debug) {
        System.out.println("Basic Variable Remove : " + removeNonBasic);
      }

      // Honestly why we do all this is beyond me but I think I am
      // supposed to
      // even though it never says in the book but it looks like they do
      // it in
      // the examples given

      // Divide the row removeNonBasic in the matrix by the value in
      // matrix[removeNonBasic][sel_col]
      _tmp = matrix[removeNonBasic][selectedColumn]; // Get divisor
      basicVariables[removeNonBasic] /= _tmp; // divide out basicVariable
      for (int col = 0; col < _mw; col++) {
        matrix[removeNonBasic][col] /= _tmp; // divide out matrix row
      }

      // Clear the column sel_col using elementary row operations
      // except on row removeNonBasic
      for (int row = 0; row < _mh; row++) {

        // Ignore row that we are using to
        // remove entries in column sel_col
        if (row != removeNonBasic) {

          // If already zero then ignore
          if (matrix[row][selectedColumn] > DELTA
              || matrix[row][selectedColumn] < -DELTA) {

            // Alter basic variable
            double mul = matrix[row][selectedColumn]; // Constant
            double t = mul * basicVariables[removeNonBasic];
            basicVariables[row] -= t;

            // apply operation to the row in matrix
            for (int col = 0; col < _mw; col++) {
              t = mul * matrix[removeNonBasic][col];
              matrix[row][col] -= t;
            }
          }
        }
      }

      // swap basic and non-basic variables
      basicVar[removeNonBasic] = selectedColumn;

      // check to see if standard
      for (int row = 0; row < _mh; row++) {
        if (basicVar[row] == selectedColumn + n + k) {
          isStandard = false;
        }
      }
      iter++;
    }

    if (debug) {
      printTableau(basicVar, basicVariables, matrix);
    }

    // Save values into x vector and then return
    for (int row = 0; row < _mh; row++) {
      if (basicVar[row] < n) {
        x[basicVar[row]] = basicVariables[row];
      }
    }

    return x;
  }
  
  private static void runAndPrint(double[] a, double[][] Q, double[] x) {
    System.out.print("Solution = ");
    printMatrix(x);
    System.out.print("Objective Function = ");
    printValue(a, Q, x);
  }

  private static void printValue(double[] a, double[][] Q, double[] x) {
    double sum = 0;
    for (int i = 0; i < a.length; i++) {
      sum += a[i] * x[i];
    }

    for (int row = 0; row < a.length; row++) {
      double _tmp = 0;
      for (int col = 0; col < Q[0].length; col++) {
        _tmp += Q[row][col] * x[col];
      }

      sum -= 0.5 * _tmp * x[row];

    }

    System.out.println(sum);

  }

  public static void printMatrix(double[][] M) {
    for (int row = 0; row < M.length; row++) {
      printMatrix(M[row]);
    }
  }

  public static void printMatrix(double[] V) {
    for (int row = 0; row < V.length; row++) {
      System.out.print(V[row] + "   ");
    }
    System.out.println();
  }

  // Problem Function
  public static void printMatrix(int[] V) {
    for (int row = 0; row < V.length; row++) {
      System.out.print(V[row] + "   ");
    }
    System.out.println("}");
  }

  public static void printMatrix(int[][] M) {
    for (int row = 0; row < M.length; row++) {
      printMatrix(M[row]);
    }
  }

  private static void printTableau(int[] mappings, double[] bv,
      double[][] matrix) {
    // Dimensions
    int h = matrix.length;
    int w = matrix[0].length;

    // Print header
    System.out.print("B.V \t V.B.V \t|\t");
    for (int col = 0; col < w; col++) {
      if (col < w / 2) {
        System.out.print("?_" + (col + 1) + "\t ");
      } else {
        System.out.print("v_" + (col + 1 - w / 2) + "\t ");
      }
    }

    System.out.println();

    System.out.print("----------------+-------");
    for (int col = 0; col < w; col++) {
      System.out.print("--------");
    }

    System.out.println();

    // Print Values
    for (int row = 0; row < h; row++) {
      // ?
      if (mappings[row] < w / 2) {
        System.out.print("?_" + (mappings[row] + 1) + "\t" + bv[row]
            + "\t|\t");
      } else {
        System.out.print("v_" + (mappings[row] - w / 2 + 1) + "\t"
            + bv[row] + "\t|\t");
      }

      for (int col = 0; col < w; col++) {
        System.out.print(matrix[row][col] + "\t");
      }

      System.out.println();
    }
    System.out.println();
  }

  public static void main(String[] args) {

    boolean debug = false;

    double[] a;
    double[][] Q;
    double[][] C;
    double[] d;

    a = new double[] { 3, 4, 1 };
    Q = new double[][] { { 1, 2, -1 }, { 2, 4, -2 }, { -1, -2, 1 } };
    C = new double[][] { { 1, 2, 1 }};
    d = new double[] { 4};
    runAndPrint(a, Q, QPSolver.dantzig(C, Q, a, d, true));
    // Answer 8.5

    System.out.println("");

    a = new double[] { 5, 8 };
    Q = new double[][] { { 2, 0 }, { 0, 4 } };
    C = new double[][] { { 3, 2 } };
    d = new double[] { 6 };
    runAndPrint(a, Q, QPSolver.dantzig(C, Q, a, d, debug));
    // Answer 11.5

    System.out.println("");

    a = new double[] { 15, 30 };
    Q = new double[][] { { 4, -4 }, { -4, 8 } };
    C = new double[][] { { 1, 2 } };
    d = new double[] { 30 };
    runAndPrint(a, Q, QPSolver.dantzig(C, Q, a, d, debug));
    // Answer 270

    System.out.println("");

    a = new double[] { -2, 35, 47 };
    Q = new double[][] { { 5, -2, -1 }, { -2, 4, 3 }, { -1, 3, 5 } };
    C = new double[][] {};
    d = new double[] {};
    runAndPrint(a, Q, QPSolver.dantzig(C, Q, a, d, debug));
    // Answer 249

    System.out.println("");

    a = new double[] { -8, -16 };
    Q = new double[][] { { 2, 0 }, { 0, 8 } };
    C = new double[][] { { 1, 1 }, { 1, 0 } };
    d = new double[] { 5, 3 };
    runAndPrint(a, Q, QPSolver.dantzig(C, Q, a, d, debug));
    // Answer 0

  }
}
