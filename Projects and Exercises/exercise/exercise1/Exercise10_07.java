public class Exercise10_07 {
  // Create ten accounts
  private Account[] accounts = new Account[10];

  public Exercise10_07() {
    for (int i = 0; i < accounts.length; i++) {
      accounts[i] = new Account();
      accounts[i].setId(i);
      accounts[i].setBalance(100);
    }

    continueATM:while (true) {
      System.out.print("Enter an id: ");
      id = input.nextInt();
      if (id < 1 || id > 10) {
        System.out.println("Please enter a correct id");
        continue;
      }

      while (true) {
        int choice = getAChoice();

        switch (choice) {
        case 1:
          System.out.println("The balance is " +
            accounts[id].getBalance());
          break;
        case 2:
          withdraw();
          break;
        case 3:
          deposit();
          break;
        case 4:
          // You can rewrite the code without using the continue. 
          // To do so, introduce a boolean variable to control one 
          // customer session
          continue continueATM; 
        }
      }
    }
  }

  private static java.util.Scanner input = new java.util.Scanner(System.in);

  int id;

  public static void main(String[] args) {
    new Exercise10_07();
  }

  public void withdraw() {
    System.out.print("Enter an amount to withdraw: ");
    int amount = input.nextInt();
    if (amount <= accounts[id].getBalance()) {
      accounts[id].withdraw(amount);
    }
    else {
      System.out.print("The amount is too large, ignored");
    }
  }

  public void deposit() {
    System.out.print("Enter an amount to deposit: ");
    int amount = input.nextInt();
    if (amount >= 0) {
      accounts[id].deposit(amount);
    }
    else {
      System.out.print("The amount is negative, ignored");
    }
  }

  public int getAChoice() {
    int choice;

    while (true) {
      System.out.println("\nMain menu");
      System.out.println("1: check balance");
      System.out.println("2: withdraw");
      System.out.println("3: deposit");
      System.out.println("4: exit");
      System.out.print("Enter a choice: ");
      choice = input.nextInt();
      if (choice < 1 || choice > 4) {
        System.out.println("Wrong choice, try again: ");
      }
      else {
        break;
      }
    }

    return choice;
  }
}
