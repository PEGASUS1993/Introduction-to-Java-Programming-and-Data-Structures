public class Exercise12_10 {
  public static void main(String[] args) { 
    try {
      int[] list = new int[20000000];
    }
    catch (Error ex) {
      ex.printStackTrace();
      System.out.println("You are running out of memory.");
    }
    
    System.out.println("GO");
    
    System.out.println("Wait");
  }
}
