import java.io.*;

public class DirectorySize {
  public static void main(String[] args) {
		System.out.print("Enter a file or a directory: ");
		java.util.Scanner input = new java.util.Scanner(System.in);
		String s = input.nextLine();
		try {
      System.out.println(directorySize(new File(s)));
    }
		catch (IOException ex) {
			System.out.println(ex.toString());
		}
  }


	public static long directorySize(File file)
		  throws java.io.FileNotFoundException{
		if (!file.exists())
			throw new java.io.FileNotFoundException(file + " not found");
		if (file.isFile()) {
			return file.length();
		}
		else {
			File[] files = file.listFiles();
			long size = 0;
			for (int i = 0; i < files.length; i++)
				size += directorySize(files[i]);

			return size;
		}
	}
}
