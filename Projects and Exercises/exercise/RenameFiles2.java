// Reanme Exercise01_01 to Exercise01_01 
import java.io.File;

public class RenameFiles2 {
  public static void main(String[] args) {
	System.out.println("Usage: java RenameLiveLabExerciseFiles * ");
	
	for (int i = 0; i < args.length; i++) {
	  if (args[i].matches("Exercise[\\d]_.*")) {
		String s = args[i];
		String newName = s.substring(0, s.indexOf('_') - 1) 
		  + "0" + s.substring(s.indexOf('_') - 1) ;
		
		File file = new File(args[i]);
		file.renameTo(new File(newName));
	  }
	} 
  }
}
