import java.io.*;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class GenerateSymmetricKey {
  public static void main(String[] args) throws Exception {
    KeyGenerator keygen = KeyGenerator.getInstance("AES");
    SecureRandom random = new SecureRandom();
    keygen.init(random);
    SecretKey key = keygen.generateKey();
    
    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[0]));
    out.writeObject(key);
    out.close();
  }
}
