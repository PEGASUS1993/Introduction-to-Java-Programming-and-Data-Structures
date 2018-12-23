public class Exercise27_09 { 
  public static void main(String[] args) {
    String s = "4.5";
    
    System.out.println(hashCodeForString(s));
  }
  
  public static int hashCodeForString(String s) {
    int b = 31;
    int code = 0;
  
    for (int i = 0; i < s.length(); i++)
      code = code * b + s.charAt(i);
  
    return code;
  }
}