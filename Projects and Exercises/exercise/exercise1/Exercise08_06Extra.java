public class Exercise08_06Extra {
  public static void main(String[] args) {
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

    // Key to the questions
    char[] keys = {'D', 'B', 'D', 'C', 'C', 'D', 'A', 'E', 'A', 'D'};
    int[] correctCounts = new int[answers.length];

    // Grade all answers
    for (int i = 0; i < answers.length; i++) {
      // Grade one student
      for (int j = 0; j < answers[i].length; j++) {
        if (answers[i][j] == keys[j])
          correctCounts[i]++;
      }
    }

    System.out.println("Max is " + max(correctCounts));    
    System.out.println("Min is " + min(correctCounts));    
    System.out.println("Mean is " + Deviation.mean(correctCounts));
    System.out.println("Deviation is " + Deviation.deviation(correctCounts));
  }
  
  public static int max(int[] list) {
    int result = list[0];
    for (int i = 0; i < list.length; i++) {
      if (result < list[i])
        result = list[i];
    }
    return result;
  }

  public static int min(int[] list) {
    int result = list[0];
    for (int i = 0; i < list.length; i++) {
      if (result > list[i])
        result = list[i];
    }
    return result;
  }
}
