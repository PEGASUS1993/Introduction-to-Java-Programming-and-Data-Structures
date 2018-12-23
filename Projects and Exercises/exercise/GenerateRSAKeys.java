import java.io.*;
import java.security.*;

public class GenerateRSAKeys {
  public static final int KEYSIZE = 512;

  /** Usage java GenerateRSAKeys publickeyfile privatekeyfile **/
  public static void main(String[] args) throws Exception {
    KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA");
    SecureRandom random = new SecureRandom();
    pairgen.initialize(KEYSIZE, random);
    KeyPair keyPair = pairgen.generateKeyPair();
    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
        args[0]));
    out.writeObject(keyPair.getPublic());
    out.close();
    out = new ObjectOutputStream(new FileOutputStream(args[1]));
    out.writeObject(keyPair.getPrivate());
    out.close();
  }
}
