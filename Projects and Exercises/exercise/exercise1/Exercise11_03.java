public class Exercise11_03 {
  public static void main(String[] args) {
    CheckingAccount checking = new CheckingAccount(1, 35);
    SavingsAccount savings = new SavingsAccount(2, 25);
    checking.withdraw(10);
    savings.withdraw(10);
    
    System.out.println(checking.getBalance());
    System.out.println(savings.getBalance());
  }
}

class CheckingAccount extends Account {
  protected int overdraftLimit = 5000;

  public CheckingAccount(int id, double balance) {
    super(id, balance);
  }
  
  @Override
  public String toString() {
    return "Checkings";
  }
}

class SavingsAccount extends Account {
  protected int overdraftLimit = 5000;

  public SavingsAccount(int id, double balance) {
    super(id, balance);
  }
  
  @Override
  public String toString() {
    return "Saving";
  }
}
