public class A {
  public static void main(String[] args) {
    String[] strings = {"New York", "Boston", "Atlanta"};
    TestMain.main(strings);
  }  
}

class TestMain {
  public static void main(String[] args) {
    for (int i = 0; i < args.length; i++)
      System.out.println(args[i]);
  }
}
