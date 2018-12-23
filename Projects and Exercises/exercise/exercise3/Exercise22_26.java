import java.util.ArrayList;
import java.util.Arrays;

public class Exercise22_26 {
  public static void main(String[] args) {
    final java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter the number of objects: ");
    final double[] items = new double[input.nextInt()];
    System.out.print("Enter the weight of the objects: ");
    for (int i = 0; i < items.length; i++) {
      items[i] = input.nextInt();
    }
    
    Arrays.sort(items);
    ArrayList<Bin> containers = new ArrayList<Bin>();
    
    for (double weight: items) {
      boolean isAddedInAnExistingBin = false;
      for (int i = 0; i < containers.size(); i++) {
        if (containers.get(i).addItem(weight)) {
          isAddedInAnExistingBin = true;
          break;
        }
      }
      
      // Add the item to a new bin
      if (!isAddedInAnExistingBin) {
        Bin bin = new Bin(10);
        bin.addItem(weight);
        containers.add(bin);
      }
    }
    
    // Display results
    for (int i = 0; i < containers.size(); i++) {
      System.out.println("Container " + (i + 1) + " contains objects with weight " + containers.get(i));
    }
  }
  
  static class Bin {   
    private ArrayList<Double> objects = new ArrayList<Double>();
    private double maxWeight = 10;
    private double totalWeight = 0;
    
    public Bin() {
    }
    
    public Bin(double maxWeight) {
      this.maxWeight = maxWeight;
    }
    
    public boolean addItem(double weight) {
      if (totalWeight + weight <= maxWeight) {
        objects.add(weight);
        totalWeight += weight;
        return true;
      } else {
        return false;
      }
    }
    
    public int getNumberOfObjects() {
      return objects.size();
    }
    
    @Override
    public String toString() {
      String output = "";        
      for (Double weight: objects)
        output += weight + " ";

      return output;
    }
  }
}
