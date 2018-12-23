import java.io.File;
import java.util.Scanner;
import java.util.Stack;

public class Exercise20_20 {
  public static void main(String[] args) {
    // Prompt the user to enter a directory or a file
    System.out.print("Enter a directory or a file: ");    
    Scanner input = new Scanner(System.in);
    String directory = input.nextLine();
    
    // Display the size
    System.out.println(getSize(new File(directory)) + " bytes");
  }

  public static long getSize(File file) {
    long size = 0; // Store the total size of all files
    
    Stack<File> stack = new Stack<File>();
    stack.push(file);
    
    while (!stack.empty()) {
      File t = stack.pop();  
      if (t.isDirectory()) {
        File[] files = t.listFiles(); // All files and subdirectories
        for (File f: files) {
          stack.push(f); // Same as queue.add(f)
        }
      }
      else { 
        size += t.length();
      }
    }

    return size;
  }
}
