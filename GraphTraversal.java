package algorithms;

import algorithms.Graph;
import algorithms.Graph.EdgeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

public class GraphTraversal {
	
	private Graph graph;
	private Queue<Integer> verticeQueue = new LinkedList<>();
	private ArrayList<Boolean> discovered = new ArrayList<>();
	private ArrayList<Boolean> processed = new ArrayList<>();
	private ArrayList<Integer> parent = new ArrayList<>();
	
	public static void main(String[] args) {
		Graph graph = new Graph(10, false);
		graph.insertEdge(0, 5, false);
		graph.insertEdge(0, 4, false);
		graph.insertEdge(0, 1, false);
		graph.insertEdge(1, 4, false);
		graph.insertEdge(1, 2, false);
		graph.insertEdge(2, 3, false);
		graph.insertEdge(3, 4, false);
		graph.insertEdge(6, 8, false);
		graph.insertEdge(7, 9, false);
		GraphTraversal graphTraversal = new GraphTraversal(graph);
		System.out.println("The graph has " + graphTraversal.findNumConnectedComponent() + " separated component.");
	}
	
	public GraphTraversal(Graph graph) {
		this.graph = graph;
		for (int i = 0; i < graph.numVertices; i++) {
			discovered.add(false);
			processed.add(false);
			parent.add(i);
		}
		
	}
	
	public Integer findNumConnectedComponent() {
		Integer numConnectedComponent = 0;
		for (int i = 0; i < graph.numVertices; i++) {
			if (!discovered.get(i)) {
				numConnectedComponent++;
				bfs(i);
			}
		}
		return numConnectedComponent;
	}
	
	// Print the path from start vertice to the end vertice.
	public void findPath(int start, int end) {
		System.out.println("current vertice is " + end);
		int parentInt = this.parent.get(end);
		if (parentInt == end) {
			if (parentInt != start) {
				System.out.println("No available route");
			}
			return;
		}
		findPath(start, parentInt);
	}
	
	// Breadth First Traversal
	public void bfs(int start) {
		if (start > graph.numVertices - 1) {
			return;
		}

		verticeQueue.offer(start);
		discovered.set(start, true);
		while(!verticeQueue.isEmpty()) {
			int currVertice = verticeQueue.poll();
			processVerticeEarly(currVertice);
			processed.set(currVertice, true);
			EdgeNode edgeNode = graph.edges.get(currVertice);
			while (edgeNode != null) {
				processEdge(currVertice, edgeNode, graph.isDirected);
				if (!discovered.get(edgeNode.verticeId)) {
					verticeQueue.offer(edgeNode.verticeId);	
					discovered.set(edgeNode.verticeId, true);
				}
				edgeNode = edgeNode.nextNode;
			}
			processVerticeLate(currVertice);
		}
		
	}
	
	private void processVerticeEarly(int currVertice) {
		System.out.println("This is vertice " + currVertice);
		return;
	}
	
	private void processEdge(int currVertice, EdgeNode edgeNode, Boolean isDirected) {
		int edgeVertice = edgeNode.verticeId;
		if (!discovered.get(edgeVertice)) {
			parent.set(edgeVertice, currVertice);
			System.out.println("process tree edge between " + currVertice + " and " + edgeVertice);
		} else if (!processed.get(edgeVertice) || !isDirected) {
			System.out.println("process back edge between " + currVertice + " and " + edgeVertice);
		}
		return;
	}
	
	private void processVerticeLate(int verticeId) {
		System.out.println("---------------Finished processing vertice " + verticeId);
		return;
	}

}
