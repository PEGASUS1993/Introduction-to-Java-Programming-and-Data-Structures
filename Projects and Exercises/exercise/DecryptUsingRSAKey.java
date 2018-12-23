import java.io.*;
import java.security.*;
import javax.crypto.*;

public class DecryptUsingRSAKey {
  /** Usage java DecryptUsingRSAKey privatekeyfile encryptedfile originalfile **/
  public static void main(String[] args) throws Exception {
    DataInputStream in = new DataInputStream(new FileInputStream(args[1]));
    int length = in.readInt();
    byte[] wrappedKey = new byte[length];
    in.read(wrappedKey, 0, length);

    // unwrap with RSA private key
    ObjectInputStream keyIn = new ObjectInputStream(
        new FileInputStream(args[0]));
    Key privateKey = (Key) keyIn.readObject();
    keyIn.close();

    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.UNWRAP_MODE, privateKey);
    Key key = cipher.unwrap(wrappedKey, "AES", Cipher.SECRET_KEY);

    OutputStream out = new FileOutputStream(args[2]);
    cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, key);

    crypt(in, out, cipher);
    in.close();
    out.close();
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
