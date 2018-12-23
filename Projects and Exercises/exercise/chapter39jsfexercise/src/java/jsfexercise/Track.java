package jsfexercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named(value = "track")
@ApplicationScoped
public class Track {
  private Map<String, Integer> map = new HashMap<>();

  public void add(String ipAddress) {
    map.put(ipAddress, map.containsKey(ipAddress)
      ? map.get(ipAddress) + 1 : 1);
  }

  public int getCount(String ipAddress) {
    return map.containsKey(ipAddress) ? map.get(ipAddress) : 0;
  }

  public Map<String, Integer> getMap() {
    return map;
  }

  public String getAllCount() {
    int totalCount = 0;
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      totalCount += entry.getValue();
    }
    // = map.entrySet().stream().mapToInt(e -> e.getValue()).sum(); 
    // Seems to have a synchronization issue

    String result = "";
    List<Map.Entry<String, Integer>> entries
      = new ArrayList<>(map.entrySet());
    // Seems to have a synchronization issue, Java EE does not support lambda at this time?
    Collections.sort(entries, new MyComparator());

    result += "<table><tr><th>IP Address</th><th>Count</th></tr>";
    for (Map.Entry<String, Integer> entry : entries) {
      result += "<tr><th>" + entry.getKey() + "</th><th>"
        + entry.getValue() + "</th></tr>";
    }
    result += "</table>";

    return "Total count is " + totalCount + result;
  }

  public class MyComparator implements Comparator<Map.Entry<String, Integer>> {

    public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
      return entry1.getValue().compareTo(entry2.getValue());
    }
  }
}
