import java.io.File;
import java.util.Scanner;

public class Exercise18_29 {
	public static void main(String[] args) {
		// Prompt the user to enter a directory
		System.out.print("Enter a directory: ");
		Scanner input = new Scanner(System.in);
		String directory = input.nextLine();

		// Display the size
		System.out.println(getNumberOfFiles(new File(directory)) + " files");
	}

	public static long getNumberOfFiles(File file) {
		long numberOfFiles = 0; // Store the total size of all files

		if (file.isDirectory()) {
			File[] files = file.listFiles(); // All files and subdirectories
			for (int i = 0; i < files.length; i++) {
				numberOfFiles += getNumberOfFiles(files[i]); // Recursive call
			}
		}
		else { // Base case
			numberOfFiles++;
		}

		return numberOfFiles;
	}
}
