public class Exercise05_08Extra {
  public static void main(String[] args) {
    String vehiclePlateNumber = "";
    for (int i = 0; i < 7; i++) {
      char ch = Math.random() < 0.5 ? 
          (char)('A' + (int)(Math.random() * 26)) : (char)('0' + (int)(Math.random() * 10));
      vehiclePlateNumber += ch;
    }
    
    System.out.println("A random vehicle plate number: "
      + vehiclePlateNumber);
  }
}