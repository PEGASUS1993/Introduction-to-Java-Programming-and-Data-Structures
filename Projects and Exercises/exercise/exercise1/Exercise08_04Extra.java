public class Exercise08_04Extra {
  public static void main(String args[]) {
    // Students' answers to the questions
    char[][] answers = {
      {'A', 'B', 'A', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
      {'D', 'B', 'A', 'B', 'C', 'A', 'E', 'E', 'A', 'D'},
      {'E', 'D', 'D', 'A', 'C', 'B', 'E', 'E', 'A', 'D'},
      {'C', 'B', 'A', 'E', 'D', 'C', 'E', 'E', 'A', 'D'},
      {'A', 'B', 'D', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
      {'B', 'B', 'E', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
      {'B', 'B', 'A', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
      {'E', 'B', 'E', 'C', 'C', 'D', 'E', 'E', 'A', 'D'}};

    int[][] counts = new int[10][5];

    // Count A's, B's, C's, D's, and E's for each answer
    for (int i = 0; i < answers.length; i++) {
      for (int j = 0; j < answers[i].length; j++) {
        counts[j][answers[i][j] - 'A']++;
      }
    }

    for (int j = 0; j < 10; j++) {
      System.out.println("\nFor question #" + (j + 1));
      for (int k = 0; k < 5; k++) 
        System.out.printf("%s %c%3s%.1f%c\n", "Percentage of", 
          (char)('A' + k), "s: ", counts[j][k] * 100 / 8.0, '%');
    }
  }
}
