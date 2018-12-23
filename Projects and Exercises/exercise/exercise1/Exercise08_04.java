public class Exercise08_04 {
  /** Main method */
  public static void main(String[] args) {
    // Declare, create, and initialized array
    double[][] workHours = {
      {2, 4, 3, 4, 5, 8, 8},
      {7, 3, 4, 3, 3, 4, 4},
      {3, 3, 4, 3, 3, 2, 2},
      {9, 3, 4, 7, 3, 4, 1},
      {3, 5, 4, 3, 6, 3, 8},
      {3, 4, 4, 6, 3, 4, 4},
      {3, 7, 4, 8, 3, 8, 4},
      {6, 3, 5, 9, 2, 7, 9}};

    // Create an array to store total weekly hours
    int[] weeklyHours = new int[workHours.length];
    for (int i = 0; i < workHours.length; i++)
      for (int j = 0; j < workHours[i].length; j++)
        weeklyHours[i] += workHours[i][j];

    int[] indexList = new int[weeklyHours.length];

    // Sort weeklyHours
    sortAndKeepIndex(weeklyHours, indexList);

    // Display result
    for (int i = weeklyHours.length - 1; i >= 0; i--)
      System.out.println("Employee " + indexList[i] + ": " +
        weeklyHours[i]);
  }

  /** The method for sorting the numbers */
  static void sortAndKeepIndex(int[] list, int[] indexList) {
    int currentMax;
    int currentMaxIndex;

    // Initialize indexList
    for (int i = 0; i < indexList.length; i++)
      indexList[i] = i;

    for (int i = list.length - 1; i >= 1; i--) {
      // Find the maximum in the list[0..i]
      currentMax = list[i];
      currentMaxIndex = i;

      for (int j = i - 1; j >= 0; j--) {
        if (currentMax < list[j]) {
          currentMax = list[j];
          currentMaxIndex = j;
        }
      }

      // Swap list[i] with list[currentMaxIndex] if necessary;
      if (currentMaxIndex != i) {
        list[currentMaxIndex] = list[i];
        list[i] = currentMax;

        // Swap the index in indexList too
        int temp = indexList[i];
        indexList[i] = indexList[currentMaxIndex];
        indexList[currentMaxIndex] = temp;
      }
    }
  }
}
