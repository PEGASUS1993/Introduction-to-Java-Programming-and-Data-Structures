public class Exercise05_06Extra {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter a genome string: ");
    String genome = input.nextLine();

    boolean found = false;
    int start = -1;
    for (int i = 0; i < genome.length() - 2; i++) {
      String triplet = genome.substring(i, i + 3);
      if (triplet.equals("ATG"))
        start = i + 3;
      else if ((triplet.equals("TAG") || triplet.equals("TAA") || triplet
          .equals("TGA")) && start != -1) {
        // A possible gene is found
        String gene = genome.substring(start, i);
        if (gene.length() % 3 == 0) {
          // A gene is found and display the gene
          found = true;
          System.out.println(gene);
          start = -1; // Start to find the next gene in the genome
        }
      }
    }

    if (!found)
      System.out.println("no gene is found");
  }
}
