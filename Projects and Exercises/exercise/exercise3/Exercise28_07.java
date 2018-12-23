import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class Exercise28_07 {
	public static void main(String[] args) {
		String[] vertices = { "Seattle", "San Francisco", "Los Angeles",
				"Denver", "Kansas City", "Chicago", "Boston", "New York",
				"Atlanta", "Miami", "Dallas", "Houston" };

		int[][] edges = { { 0, 1 }, { 1, 0 }, { 1, 2 }, { 1, 3 }, { 2, 1 },
				{ 2, 3 }, { 2, 4 }, { 2, 10 }, { 3, 1 }, { 3, 2 }, { 3, 4 },
				{ 3, 5 }, { 4, 2 }, { 4, 3 }, { 4, 5 }, { 4, 7 }, { 4, 8 },
				{ 4, 10 }, { 5, 3 }, { 5, 4 }, { 5, 6 }, { 5, 7 }, { 6, 5 },
				{ 6, 7 }, { 7, 4 }, { 7, 5 }, { 7, 6 }, { 7, 8 }, { 8, 4 },
				{ 8, 7 }, { 8, 9 }, { 8, 10 }, { 8, 11 }, { 9, 8 }, { 9, 11 },
				{ 10, 2 }, { 10, 4 }, { 10, 8 }, { 10, 11 }, { 11, 8 },
				{ 11, 9 }, { 11, 10 } };

		UnweightedGraphFindCycle<String> graph = new UnweightedGraphFindCycle<>(
				vertices, edges);

		System.out.println("Find a cycle " + graph.getACycle());

		Integer[] vertices1 = { 0, 1, 2, 3 };

		int[][] edges1 = { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 0 }, { 1, 3 },
				{ 2, 0 }, { 3, 0 } };

		UnweightedGraphFindCycle<Integer> graph1 = new UnweightedGraphFindCycle<>(
				vertices1, edges1);

		System.out.println("Find a cycle " + graph1.getACycle());
	}
}

class UnweightedGraphFindCycle<V> extends UnweightedGraph<V> {
	/** Construct an empty graph */
	public UnweightedGraphFindCycle() {
	}

	/** Construct a graph from vertices and edges stored in arrays */
	public UnweightedGraphFindCycle(V[] vertices, int[][] edges) {
		super(vertices, edges);
	}

	/** Construct a graph from vertices and edges stored in List */
	public UnweightedGraphFindCycle(List<V> vertices, List<Edge> edges) {
		super(vertices, edges);
	}

	/** Construct a graph for integer vertices 0, 1, 2 and edge list */
	public UnweightedGraphFindCycle(List<Edge> edges, int numberOfVertices) {
		super(edges, numberOfVertices);
	}

	/** Construct a graph from integer vertices 0, 1, and edge array */
	public UnweightedGraphFindCycle(int[][] edges, int numberOfVertices) {
		super(edges, numberOfVertices);
	}

	public List<List<Edge>> cloneEdges() {
		List<List<Edge>> neigborCopy = new ArrayList<>();

		for (int i = 0; i < neighbors.size(); i++) {
			List<Edge> edges = new ArrayList<>();
			for (Edge e : neighbors.get(i)) {
				edges.add(e);
			}
			neigborCopy.add(edges);
		}

		return neigborCopy;
	}

	/*
	 * WRONG Input: G = (V, E) with a starting vertex u Output: A list
	 * representing a cycle of vertices
	 * 
	 * push u into the stack; mark u visited;
	 * 
	 * while (stack is not empty) { pop a vertex from the stack, say x; for
	 * (every neighbor of x, say y) { if (y has been visited) A cycle is found.
	 * This cycle consists of two paths. one path consists of y, parent[y],
	 * parent[parent[y]], up to the root. the other consists of x, parent[x],
	 * parent[parent[x]], up to the root. (x, y) is an edge else { push y into
	 * the stack; set x as the parent for y; mark y visited; } }
	 */

	/*
	 * CORRECT Input: G = (V, E) Output: A list that consists of the vertices in
	 * a cycle
	 * 
	 * List<Integer> getCycle() { while (there are unvisited vertices) { pick
	 * one, say v; push v to the stack; mark x visited; while (the stack is not
	 * empty) { peek a vertex from the stack, say x; Let y be the next neighbor
	 * for x; if (y has already been visited) { add all vertices from x up to y
	 * in the tree along the path into the list; return the list; } else {
	 * parent[y] = x; push y into the stack; mark y visited; } else { pop a
	 * vertex from the stack; } } }
	 * 
	 * return an empty list; }
	 */
	public List<Integer> getACycle() {
		List<Integer> allVertices = new ArrayList<>();
		for (int i = 0; i < vertices.size(); i++) {
			allVertices.add(i);
		}

		List<List<Edge>> neighbors = cloneEdges();

		List<Integer> searchOrder = new ArrayList<>();
		int[] parent = new int[vertices.size()];
		for (int i = 0; i < parent.length; i++)
			parent[i] = -1; // Initialize parent[i] to -1

		// Mark visited vertices
		boolean[] isVisited = new boolean[vertices.size()];

		while (allVertices.size() > 0) {
			int v = allVertices.get(0);

			Stack<Integer> stack = new Stack<>();
			stack.push(v);
			searchOrder.add(v);
			allVertices.remove(new Integer(v));
			isVisited[v] = true; // Vertex x visited

			while (!stack.isEmpty()) {
				int x = stack.peek();
				if (neighbors.get(x).size() == 0) {
					stack.pop();
					continue;
				} else {
					// Find the next unvisited neighbor of x
					for (int i = neighbors.get(x).size() - 1; i >= 0; i--) {
						Edge e = neighbors.get(x).get(i);
						if (!isVisited[e.v]) {
							parent[e.v] = x; // The parent of vertex e.v is x
							stack.push(e.v); // Add a new neighbor to the stack
							isVisited[e.v] = true; // Vertex x visited
							searchOrder.add(e.v);
							allVertices.remove(new Integer(e.v));
							neighbors.get(x).remove(i);
							break;
						} else if (e.v != parent[x]) {
							// A path is found
							List<Integer> list = new ArrayList<>();

							list.add(e.v);
							while (x != e.v && x != -1) {
								list.add(x);
								x = parent[x];
							}

							return list;
						} else {
							neighbors.get(x).remove(i);
						}
					}
				}
			}
		}

		return null;
	}
}