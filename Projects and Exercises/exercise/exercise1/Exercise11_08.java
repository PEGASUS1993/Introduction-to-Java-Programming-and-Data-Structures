public class Exercise11_08 {
  public static void main (String[] args) {
    Account1.setAnnualInterestRate(5.5);

    Account1 account = new Account1("George", 1122, 1000);
    account.deposit(30);
    account.deposit(40);
    account.deposit(50);
    
    account.withdraw(5);
    account.withdraw(4);
    account.withdraw(2);
    
    System.out.println("Name: " + account.getName());
    System.out.println("Annual interest rate: " + Account1.getAnnualInterestRate());
    System.out.println("Balance: " + account.getBalance());
    
    java.util.ArrayList<Transaction> list = account.getTransactions();
    
    System.out.printf("%-35s%-15s%-15s%-15s\n", "Date", "Type", "Amount", "Balance");
    
    for (int i = 0; i < list.size(); i++) {
      Transaction transaction = (Transaction)(list.get(i));
      System.out.printf("%-35s%-15s%-15s%-15s\n", transaction.getDate(), 
          transaction.getType(), transaction.getAmount(), transaction.getBalance());
    }
  }
}

class Account1 {
  private int id;
  private String name;
  private double balance;
  private static double annualInterestRate;
  private java.util.Date dateCreated;
  private java.util.ArrayList<Transaction> transactions = new java.util.ArrayList<>();

  public Account1() {
    dateCreated = new java.util.Date();
  }

  public Account1(String name, int id, double balance) {
    this.id = id;
    this.name = name;
    this.balance = balance;
    dateCreated = new java.util.Date();
  }
  
  public int getId() {
    return this.id;
  }

  public double getBalance() {
    return balance;
  }

  public java.util.ArrayList<Transaction> getTransactions() {
    return transactions;
  }

  public String getName() {
    return name;
  }
  
  public static double getAnnualInterestRate() {
    return annualInterestRate;
  }

  public void setId(int id) {
    this.id =id;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public static void setAnnualInterestRate(double annualInterestRate) {
    Account1.annualInterestRate = annualInterestRate;
  }

  public double getMonthlyInterest() {
    return balance * (annualInterestRate / 1200);
  }

  public java.util.Date getDateCreated() {
    return dateCreated;
  }

  public void withdraw(double amount) {
    balance -= amount;
    transactions.add(new Transaction('W', amount, balance, ""));
  }

  public void deposit(double amount) {
    balance += amount;
    transactions.add(new Transaction('D', amount, balance, ""));
  }
}

class Transaction {
  private java.util.Date date;
  private char type;
  private double amount;
  private double balance;
  private String description;
  
  public Transaction(char type, double amount, double balance,
      String description) {
    date = new java.util.Date();
    this.type = type;
    this.amount = amount;
    this.balance = balance;
    this.description = description;
  }

  public java.util.Date getDate() {
    return date;
  }
  
  public char getType() {
    return type;
  }

  public double getAmount() {
    return amount;
  }
  
  public double getBalance() {
    return balance;
  }
  
  public String getDescription() {
    return description;
  }
}
