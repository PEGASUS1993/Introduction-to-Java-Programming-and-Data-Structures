import java.io.*;

public class Exercise17_17 {
  public static void main(String[] args) throws Exception {
    BitOutputStream output = new BitOutputStream(new File("Exercise17_17.dat"));
    output.writeBit("010000100100001001101");
    output.close();    
    System.out.println("Done");
  }
  
  public static class BitOutputStream {
    private FileOutputStream output;
    private int value;
    private int count = 0;
    private int mask = 1; // The bits are all zeros except the last one
    
    public BitOutputStream(File file) throws IOException {
      output = new FileOutputStream(file);
    }
    
    public void writeBit(char bit) throws IOException {
      count++;
      value = value << 1;
      
      if (bit == '1') 
        value = value | mask;
      
      if (count == 8) {
        output.write(value);
        count = 0;
      }
    }
    
    public void writeBit(String bitString) throws IOException {
      for (int i = 0; i < bitString.length(); i++)
        writeBit(bitString.charAt(i));
    }
    
    /** Write the last byte and close the stream. If the last byte is not full, right-shfit with zeros */
    public void close() throws IOException {     
      if (count > 0) {
        value = value << (8 - count);
        output.write(value);
      }
      
      output.close();
    }   
  }
}
