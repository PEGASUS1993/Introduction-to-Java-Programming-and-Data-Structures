public class Exercise10_27 {
  public static void main(String[] args) {
    MyStringBuilder1 s1 = new MyStringBuilder1("Welcome to");
    MyStringBuilder1 s2 = new MyStringBuilder1(" Java");

    System.out.println(s1.length());
    System.out.println(s1.charAt(3));
    System.out.println(s1.toLowerCase().toString());
    s1.append(s2);
    System.out.println(s1.toString());
    System.out.println(s1.substring(1, 4));
  }
}

class MyStringBuilder1 {
  private int size = 0; // size is not necessary. Remove it and modify the code.
  private int capacity = 0;
  private char[] buffer;

  public MyStringBuilder1() {
  }

  public MyStringBuilder1(char[] chars) {
    buffer = new char[chars.length];
    size = capacity = chars.length;
    System.arraycopy(chars, 0, buffer, 0, chars.length);
  }

  public MyStringBuilder1(String s) {
    capacity = size = s.length();
    buffer = new char[capacity];

    for (int i = 0; i < s.length(); i++)
      buffer[i] = s.charAt(i);
  }

  private void increaseCapacity(int newCapacity) {
    char[] temp = new char[newCapacity];
    System.arraycopy(buffer, 0, temp, 0, size);
    capacity = newCapacity;
    buffer = temp;
  }

  public MyStringBuilder1 append(MyStringBuilder1 s) {
    if (capacity < size + s.length()) {
      increaseCapacity(2 * (size + s.length()));
    }

    for (int i = 0; i < s.length(); i++)
      buffer[i + size] = s.charAt(i);

    size += s.length();

    return this;
  }

  public MyStringBuilder1 append(int i) {
    return append(new MyStringBuilder1(String.valueOf(i)));
  }

  public int length() {
    return size;
  }

  public char charAt(int index) {
    return buffer[index];
  }

  public MyStringBuilder1 toLowerCase() {
    for (int i = 0; i < size; i++)
      buffer[i] = Character.toLowerCase(buffer[i]);

    return this;
  }

  public MyStringBuilder1 substring(int begin, int end) {
    char[] result = new char[end - begin];
    for (int i = 0; i < result.length; i++)
      result[i] = this.buffer[begin + i];

    return new MyStringBuilder1(result);
  }

  @Override
  public String toString() {
    char[] temp = new char[size];
    for (int i = 0; i < size; i++)
      temp[i] = buffer[i];

    return new String(temp);
  }

  // Exercise 8.30:
  public MyStringBuilder1 insert(int offset, MyStringBuilder1 s) {
    if (capacity < size + s.length()) {
      increaseCapacity(2 * (size + s.length()));
    }

    String temp = s.toString();
    for (int i = 0; i < temp.length(); i++)
      buffer[size - 1 + temp.length() - i] = buffer[size - 1 - i];

    for (int i = 0; i < temp.length() ; i++)
      buffer[i + offset] = temp.charAt(i);

    size += temp.length();

    return this;
  }

  public MyStringBuilder1 reverse() {
    for (int i = 0; i < size / 2; i++) {
      char temp = buffer[i];
      buffer[i] = buffer[size - 1 - i];
      buffer[size - 1 - i] = temp;
    }

    return this;
  }

  public MyStringBuilder1 substring(int begin) {
    return substring(begin, size);
  }

  public MyStringBuilder1 toUpperCase() {
    for (int i = 0; i < size; i++)
      buffer[i] = Character.toUpperCase(buffer[i]);

    return this;
  }
}
