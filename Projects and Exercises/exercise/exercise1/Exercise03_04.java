public class Exercise03_04 {
  public static void main(String[] args) {
    int number = (int)(Math.random() * 12) + 1;
    
    // or int number = (int)(System.currentTimeMillis() % 12 + 1);

    // or int number = (int)(Math.random() * 12) + 1;

    if (number == 1) 
      System.out.println("Month is Januaray");
    else if (number == 2) 
      System.out.println("Month is Feburary");
    else if (number == 3) 
      System.out.println("Month is March");
    else if (number == 4) 
      System.out.println("Month is April");
    else if (number == 5) 
      System.out.println("Month is May");
    else if (number == 6) 
      System.out.println("Month is June");
    else if (number == 7) 
      System.out.println("Month is July");
    else if (number == 8) 
      System.out.println("Month is August");
    else if (number == 9) 
      System.out.println("Month is September");
    else if (number == 10) 
      System.out.println("Month is October");
    else if (number == 11) 
      System.out.println("Month is November");
    else // if (number == 12) 
      System.out.println("Month is December");
  }
}
