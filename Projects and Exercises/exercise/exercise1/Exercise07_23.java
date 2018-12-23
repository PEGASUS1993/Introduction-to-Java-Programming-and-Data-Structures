public class Exercise07_23 {
  public static void main(String[] args) {
    // Declare a constant value for the number of lockers
    final int NUMBER_OF_LOCKER = 100;

    // Create an array to store the status of each array
    // The first student closed all lockers, each lockers[i] is false
    boolean[] lockers = new boolean[NUMBER_OF_LOCKER];
    
    // Each student changes the lockers
    for (int j = 1; j <= NUMBER_OF_LOCKER; j++) {
      // Student Sj changes every jth locker
      // starting from the lockers[j - 1].
      for (int i = j - 1; i < NUMBER_OF_LOCKER; i += j) {
        lockers[i] = !lockers[i];
      }
    }

    // Find which one is open
    for (int i = 0; i < NUMBER_OF_LOCKER; i++) {
      if (lockers[i])
        System.out.println("Locker " + (i + 1) + " is open");
    }
  }
}
