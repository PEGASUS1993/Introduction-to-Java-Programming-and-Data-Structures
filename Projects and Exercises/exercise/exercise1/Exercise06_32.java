public class Exercise06_32 {
  public static void main(String[] args) {
    int winCount = 0;

    for (int i = 0; i < 10000; i++) {
      int dice = getDice();
      if (dice == 7 || dice == 11) {
        winCount++;
      }
      else if (dice == 2 || dice == 3 || dice == 12) {
        //System.out.println("You lose");
      }
      else {
        int point = dice;
        //System.out.println("point is " + point);
        do {
          dice = getDice();
        } while (dice != 7 && dice != point);

        if (dice == 7) {
          //System.out.println("You lose");
        }
        else {
          winCount++;
        }
      }
    }

    System.out.println(winCount);
  }

  // Get a dice
  public static int getDice() {
    int i1 = 1 + (int)(Math.random() * 6);
    int i2 = 1 + (int)(Math.random() * 6);

    // System.out.println("You rolled " + i1 + " + " + i2 + " = " + (i1 + i2));
    return i1 + i2;
  }
}
