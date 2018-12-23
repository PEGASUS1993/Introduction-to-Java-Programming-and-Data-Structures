public class Exercise04_25 {
  public static void main(String[] argds) {
    char ch1 = (char)('A' + (int)(Math.random() * 26));
    char ch2 = (char)('A' + (int)(Math.random() * 26));
    char ch3 = (char)('A' + (int)(Math.random() * 26));
    char ch4 = (char)('0' + (int)(Math.random() * 10));
    char ch5 = (char)('0' + (int)(Math.random() * 10));
    char ch6 = (char)('0' + (int)(Math.random() * 10));
    char ch7 = (char)('0' + (int)(Math.random() * 10));
    String vehiclePlateNumber = "" + ch1 + ch2 + ch3 + ch4 + ch5 + ch6 + ch7;
    
    System.out.println("A random vehicle plate number: "
      + vehiclePlateNumber);
  }
}
