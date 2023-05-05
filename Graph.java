package algorithms;

import java.util.ArrayList;

// Use Adjacency List to represent a graph
public final class Graph {
	
	private static final int MAX_NUM_OF_VERTICES = 1000;
	
	public int numVertices;
	public int numEdges;
	public boolean isDirected;
	public ArrayList<Integer> outDegrees = new ArrayList<>();
	public ArrayList<EdgeNode> edges = new ArrayList<>();
	
	public static void main(String[] args) {
		Graph graph = new Graph(6, false);
		graph.insertEdge(0, 5, false);
		graph.insertEdge(0, 4, false);
		graph.insertEdge(0, 1, false);
		graph.readGraph();
	} 
		
	public Graph(int numVertices, boolean isDirected) {
		this.numVertices = numVertices;
		this.isDirected = isDirected;
		
		// We use a large number, MAX_NUM_OF_VERTICES, so that the arrayList won't be re-sized.
		for (int i = 0; i < MAX_NUM_OF_VERTICES; i++) {
			this.outDegrees.add(0);
		}
		for (int i = 0; i < MAX_NUM_OF_VERTICES; i++) {
			// This null is like the end of a LinkedList.
			this.edges.add(null);
		}
	}
	
	public void insertEdge(int fromId, int toId, boolean isDirected) {
		if (this.edges.get(fromId) == null) {
			this.edges.set(fromId, new EdgeNode(toId, 0, null));
		} else {
			EdgeNode edgeNode = this.edges.get(fromId);
			EdgeNode newNode = new EdgeNode(toId, 0, edgeNode);
			this.edges.set(fromId, newNode);
		}
		
		this.outDegrees.set(fromId, this.outDegrees.get(fromId) + 1);
		if (!isDirected) {
			insertEdge(toId, fromId, true);
		} else {
			this.numEdges += 1;
		} 
	}
	
	public void readGraph() {
		for (int i = 0; i < this.numVertices; i++) {
			System.out.println("Current verticeId is " + i);
			System.out.println("--------------------------------");
			EdgeNode edgeNode = this.edges.get(i);
			while (edgeNode != null) {
				System.out.println("one neighbor id is " + edgeNode.verticeId);
				edgeNode = edgeNode.nextNode;
			}
		}
	}
	
	public final class EdgeNode{
		public int verticeId;
		public int weight;
		public EdgeNode nextNode;
		
		public EdgeNode(int verticeId, int edgeWeight, EdgeNode nextNode) {
			this.verticeId = verticeId;
			this.weight= edgeWeight;
			this.nextNode = nextNode;
		}
	}
	

}
