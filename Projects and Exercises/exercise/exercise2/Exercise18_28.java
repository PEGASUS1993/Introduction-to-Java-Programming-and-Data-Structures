import java.io.*;

/** Comparison of recursive and non-recursive algorithms for
 *  finding a directory size. Funnily, the two are handling
 *  special types of files differently. For instance, if a
 *  folder contains system or hidden files, the result might
 *  be slightly different.
 */

public class Exercise18_28 {
  public static void main(String[] args) {
    System.out.print("Enter a file or a directory: ");
    java.util.Scanner input = new java.util.Scanner(System.in);
    String path = input.nextLine();

    try {
      // !!! uncomment all 4 lines in this block to see the timing !!!
      //long i = new java.util.Date().getSeconds();
      System.out.println("The size of \"" + path + "\" is " +
        getDirectorySize(new File(path)));
      //System.out.println("Calculations took " + (new java.util.Date().getSeconds() - i) + " seconds");

      System.out.println();

      //i = new java.util.Date().getSeconds();
      System.out.println("The size of \"" + path + "\" is " +
        getDirectorySize(new File(path), 30));
      //System.out.println("Calculations took " + (new java.util.Date().getSeconds() - i) + " seconds");
    }
    catch (Exception ex) {
      System.out.println(ex);
    }
  }

  /** non-recursive version */
  /** depth - the depth of folder nesting, or the number of sequential inclusions */
  public static long getDirectorySize(File file,
    int depth) throws FileNotFoundException {
    if (!file.exists()) {
      throw new FileNotFoundException(file + " not found");
    }

    long totalSize = 0;

    if (file.isFile()) {
      return file.length();
    }
    else {
      int i = 0;
      int index = 0;
      int[] indices = new int[depth];
      boolean switcher = false;
      File[] files = file.listFiles();
      while (!switcher) {
        for (i = indices[index]; i < files.length; i++) {
          if (files[i].isFile()) {
            totalSize += files[i].length();
            indices[index]++;
          }
          else {
            File[] filesAux = files[i].listFiles();
            if (filesAux.length != 0) {
              indices[index]++;
              index++;
              files = filesAux;
              indices[index] = 0;
              i = -1;
            }
            else {
              indices[index]++;
            }
          }
        }
        if (index == 0) {
          switcher = true;
        }
        else {
          index--;
          // this is a very costly operation
          // instead, it is possible to update an archive of length *depth*,
          // storing the current filelists up to the deepest level of inclusion
          files = new File(new File(files[i -
            1].getParent()).getParent()).listFiles();
        }
      }
    }
    return totalSize;
  }

  /** recursive version */
  public static long getDirectorySize(File file) throws java.io.
    FileNotFoundException {
    if (!file.exists()) {
      throw new java.io.FileNotFoundException(file + " not found");
    }
    if (file.isFile()) {
      return file.length();
    }
    else {
      File[] files = file.listFiles();
      long size = 0;
      for (int i = 0; i < files.length; i++) {
        size += getDirectorySize(files[i]);
      }

      return size;
    }
  }
}
