import java.util.*;

public class Exercise22_27 {
  public static void main(String[] args) {
    final java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter the number of objects: ");
    final int[] items = new int[input.nextInt()];
    System.out.print("Enter the weight of the objects: ");
    for (int i = 0; i < items.length; i++) {
      items[i] = input.nextInt();
    }
    
    ArrayList<ArrayList<Integer>> permutations 
      = generatePermuation(items);
       
    ArrayList<Bin> containers = findBest(permutations);
    
    // Display results
    for (int i = 0; i < containers.size(); i++) {
      System.out.println("Container " + (i + 1) 
        + " contains objects with weight " + containers.get(i));
    }
    
    System.out.println("The optimal number of bins is " + containers.size());
  }
   
  public static ArrayList<Bin> findBest(ArrayList<ArrayList<Integer>> permutations) {
    int numberOfBins = Integer.MAX_VALUE;
    ArrayList<Bin> bestBinList = null;
    
    for (ArrayList<Integer> list: permutations) {
      ArrayList<Bin> binList = firstFit(list);
      if (binList.size() < numberOfBins) {
        numberOfBins = binList.size();
        bestBinList = binList;
      }
    }
    
    return bestBinList;    
  }
  
  public static ArrayList<ArrayList<Integer>> 
      generatePermuation(int[] items) {
    ArrayList<ArrayList<Integer>> permutations 
      = new ArrayList<ArrayList<Integer>>();
    
    if (items.length == 1) {
      ArrayList<Integer> list = new ArrayList<Integer>();
      list.add(items[0]);
      permutations.add(list);
      return permutations;
    }
    else {
      for (int i = 0; i < items.length; i++) {
        int[] tempItems = new int[items.length - 1];
        System.arraycopy(items, 0, tempItems, 0, i);
        System.arraycopy(items, i + 1, tempItems, i, items.length - i - 1);
        ArrayList<ArrayList<Integer>> tempPermutations = generatePermuation(tempItems);
        for (ArrayList<Integer> list: tempPermutations) {
          list.add(items[i]);
          permutations.add(list);
        }
      }
    }
    
    return permutations;
  }
  
  public static ArrayList<Bin> firstFit(ArrayList<Integer> list) {   
    int[] items = new int[list.size()];
    for (int i = 0; i < items.length; i++)
      items[i] = list.get(i);
    
    return firstFit(items);
  }
  
  public static ArrayList<Bin> firstFit(int[] items) {   
    ArrayList<Bin> containers = new ArrayList<Bin>();
    
    for (int weight: items) {
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
    
    return containers;
  }
  
  static class Bin {   
    private ArrayList<Integer> objects = new ArrayList<Integer>();
    private int maxWeight = 10;
    private int totalWeight = 0;
    
    public Bin() {
    }
    
    public Bin(int maxWeight) {
      this.maxWeight = maxWeight;
    }
    
    public boolean addItem(int weight) {
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
    
    public int getTotalWeight() {
      return totalWeight;
    }
    
    public int getMaxWeight() {
      return maxWeight;
    }   
    
    @Override
    public String toString() {
      String output = "";        
      for (Integer weight: objects)
        output += weight + " ";

      return output;
    }
  }
}
