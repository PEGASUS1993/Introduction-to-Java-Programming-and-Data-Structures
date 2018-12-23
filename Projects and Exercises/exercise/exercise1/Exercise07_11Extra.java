public class Exercise07_11Extra {
  public static void main(String[] args) {
    int number = (int)(Math.random() * 12) + 1;
    
    // or int number = (int)(System.currentTimeMillis() % 12 + 1);

    // or int number = (int)(Math.random() * 12) + 1;

    String[] monthName = {"Januaray", "Feburary", "March", "April", "May",
    		"June", "July", "August", "September", "October", "November", "December"};
    
    System.out.println("Month is " + monthName[number]);
  }
}
