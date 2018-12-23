public class Exercise10_23 {
  public static void main(String[] args) {
    MyString2 s = new MyString2(new char[] {'a', 'b', 'c'});

    char[] chars = MyString2.valueOf(true).toChars();
    for (int i = 0; i < chars.length; i++) {
      System.out.print(chars[i]);
    }
  }
}

class MyString2 {
  private char[] chars;

  public MyString2(char[] chars) {
    this.chars = new char[chars.length];
    System.arraycopy(chars, 0, this.chars, 0, chars.length);
  }

  public int compare(MyString2 s) {
    return 1;
  }

  public MyString2 toUppercase() {
    char[] temp = new char[chars.length];
    for (int i = 0; i < chars.length; i++) {
      temp[i] = Character.toUpperCase(chars[i]);
    }

    return new MyString2(temp);
  }

  public static MyString2 valueOf(boolean d) {
    if (d)
      return new MyString2(new char[]{'t', 'r', 'u', 'e'});
    else
      return new MyString2(new char[]{'f', 'a', 'l', 's', 'e'});
  }

  public MyString2 substring(int begin) {
    char[] temp = new char[chars.length - begin];
    for (int i = begin; i < chars.length; i++) {
      temp[i - begin] = chars[i];
    }
    return new MyString2(temp);
  }

  public char[] toChars() {
    return chars;
  }
}
