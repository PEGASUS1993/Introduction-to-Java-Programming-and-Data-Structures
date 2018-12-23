public class Exercise10_24 {
  public static void main(String[] args) {
    MyCharacter c = new MyCharacter('A');
    System.out.println(c.isDigit());
    System.out.println(MyCharacter.isDigit('t'));    
  }
}

class MyCharacter {
  private char value;

  public MyCharacter(char value) {
    this.value = value;
  }

  public char charValue() {
    return value;
  }

  public int compareTo(MyCharacter anotherCharacter) {
    return value - anotherCharacter.value;
  }

  public boolean equals(MyCharacter anotherCharacter) {
    return value == anotherCharacter.value;
  }

  public boolean isDigit() {
    return isDigit(value);
  }

  public static boolean isDigit(char ch) {
    return ch <= '9' && ch >= '0';
  }

  public static boolean isLetter(char ch) {
    return (ch <= 'z' && ch >= 'a') ||
      (ch <= 'Z' && ch >= 'A');
  }

  public static boolean isLetterOrDigit(char ch) {
    return isLetter(ch) || isDigit(ch);
  }

  public static boolean isLowerCase(char ch) {
    return (ch <= 'z' && ch >= 'a');
  }

  public static boolean isUpperCase(char ch) {
    return (ch <= 'Z' && ch >= 'A');
  }

  public static char toUpperCase(char ch) {
    if (isLowerCase(ch))
      return (char)(ch - 'a' + 'A');
    else
      return ch;
  }

  public static char toLowerCase(char ch) {
    if (isUpperCase(ch))
      return (char)(ch - 'A' + 'a');
    else
      return ch;
  }
}
