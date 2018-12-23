import java.io.*;
import java.security.*;
import javax.crypto.*;

public class EncryptUsingRSAKey {
  /** Usage java EncryptUsingRSAKey publickeyfile sourcefile encryptedfile **/
  public static void main(String[] args) throws Exception {
    KeyGenerator keygen = KeyGenerator.getInstance("AES");
    SecureRandom random = new SecureRandom();
    keygen.init(random);
    SecretKey key = keygen.generateKey();

    // wrap with RSA public key
    ObjectInputStream keyIn = new ObjectInputStream(
        new FileInputStream(args[0]));
    Key publicKey = (Key) keyIn.readObject();
    keyIn.close();

    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.WRAP_MODE, publicKey);
    byte[] wrappedKey = cipher.wrap(key);
    DataOutputStream out = new DataOutputStream(new FileOutputStream(args[2]));
    out.writeInt(wrappedKey.length);
    out.write(wrappedKey);

    InputStream in = new FileInputStream(args[1]);
    cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, key);
    crypt(in, out, cipher);
    in.close();
    out.close();
    /**
     * ObjectInputStream keyIn = new ObjectInputStream(new
     * FileInputStream(args[0])); Key key = (Key)keyIn.readObject();
     * 
     * Cipher cipher = Cipher.getInstance("RSA");
     * cipher.init(Cipher.ENCRYPT_MODE, key);
     * 
     * InputStream in = new FileInputStream(args[1]); OutputStream out = new
     * FileOutputStream(args[2]);
     * 
     * crypt(in, out, cipher); in.close(); out.close();
     **/
  }

  public static void crypt(InputStream in, OutputStream out, Cipher cipher)
      throws IOException, GeneralSecurityException {
    int blockSize = cipher.getBlockSize();
    int outputSize = cipher.getOutputSize(blockSize);
    byte[] inBytes = new byte[blockSize];
    byte[] outBytes = new byte[outputSize];

    int inLength = 0;

    boolean more = true;
    while (more) {
      inLength = in.read(inBytes);
      if (inLength == blockSize) {
        int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
        out.write(outBytes, 0, outLength);
        // System.out.println(outLength);
      } else
        more = false;
    }
    if (inLength > 0)
      outBytes = cipher.doFinal(inBytes, 0, inLength);
    else
      outBytes = cipher.doFinal();
    // System.out.println(outBytes.length);
    out.write(outBytes);
  }
}
