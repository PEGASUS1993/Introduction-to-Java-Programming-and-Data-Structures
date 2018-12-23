public class Exercise03_05Extra {
  public static void main(String[] args) {
    int cardNumber = (int) (Math.random() * 52);
    int suit = cardNumber / 13;
    int rank = cardNumber % 13;

    System.out.print("The card is ");
    if (rank == 0) {
      System.out.print("Ace of ");
    }
    else if (rank >= 1 && rank <= 9) {
      System.out.print(rank + " of ");
    }
    else if (rank == 10) {
      System.out.print("Jack of ");
    }
    else if (rank == 11) {
      System.out.print("Queen of ");
    }
    else if (rank == 12) {
      System.out.print("King of ");
    }

    if (suit == 0) {
      System.out.println("Spades");
    }
    else if (suit == 1) {
      System.out.println("Hearts");
    }
    else if (suit == 2) {
      System.out.println("Diamonds");
    }
    else {
      System.out.println("Clubs");
    }
  }
}