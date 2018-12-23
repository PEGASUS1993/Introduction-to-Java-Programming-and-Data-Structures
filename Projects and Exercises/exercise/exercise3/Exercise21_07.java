import java.util.*;

public class Exercise21_07 {
  public static void main(String[] args) {
    // Set text in a string
    String text = "Good morning. Have a good class. " +
      "Have a good visit. Have fun!";

    // Create a hash map to hold words and key and count as value
    HashMap<String, Integer> map = new HashMap<>();

    String[] tokens = text.split("[\\s+\\p{P}]");
    for (String key: tokens) {
      key = key.toLowerCase();
      if (key.length() > 0) 
        map.put(key, map.containsKey(key) ? map.get(key) + 1 : 1);
    }

    List<Map.Entry<String, Integer>> entries = 
      new ArrayList<>(map.entrySet());
    entries.sort((entry1, entry2) ->    
      entry1.getValue().compareTo(entry2.getValue()) == 0 ?
         entry1.getKey().compareTo(entry2.getKey()) :
         entry1.getValue().compareTo(entry2.getValue())
    );
    for (Map.Entry<String, Integer> entry: entries) {
      System.out.println(entry.getKey() + "\t" + entry.getValue());
    }
  }
}