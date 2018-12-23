import java.io.Serializable;

public class WeightedEdge extends Edge
    implements Comparable<WeightedEdge>, Serializable {
  public double weight; // The weight on edge (u, v)

  /** Create a weighted edge on (u, v) */
  public WeightedEdge(int u, int v, double weight) {
    super(u, v);
    this.weight = weight;
  }

  /** Compare two edges on weights */
  public int compareTo(WeightedEdge edge) {
    if (weight > edge.weight) {
      return 1;
    }
    else if (weight == edge.weight) {
      return 0;
    }
    else {
      return -1;
    }
  }
  
  @Override
  public String toString() {
    return u + ", " + v + ", " + weight;
  }
}
