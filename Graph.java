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
		Graph graph = new Graph(8, false);
		graph.insertEdge(0, 1, false);
		graph.insertEdge(0, 2, false);
		graph.insertEdge(6, 1, false);
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
		EdgeNode newEdgeNode = new EdgeNode(toId, 0, this.edges.get(fromId));
		this.edges.set(fromId, newEdgeNode);
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
				System.out.println("its weight is " + edgeNode.weight);
				edgeNode = edgeNode.nextNode;
			}
		}
	}
	
	private final class EdgeNode{
		private int verticeId;
		private int weight;
		private EdgeNode nextNode;
		
		private EdgeNode(int verticeId, int edgeWeight, EdgeNode nextNode) {
			this.verticeId = verticeId;
			this.weight= edgeWeight;
			this.nextNode = nextNode;
		}
	}
	

}
