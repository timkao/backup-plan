package algorithms;

import algorithms.Graph;
import algorithms.Graph.EdgeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

public class GraphTraversal {
	
	private static Queue<Integer> verticeQueue = new LinkedList<>();
	private static ArrayList<Boolean> discovered = new ArrayList<>();
	private static ArrayList<Boolean> processed = new ArrayList<>();
	private static ArrayList<Integer> parent = new ArrayList<>();
	
	public static void main(String[] args) {
		Graph graph = new Graph(6, true);
		graph.insertEdge(0, 5, false);
		graph.insertEdge(0, 4, false);
		graph.insertEdge(0, 1, false);
		graph.insertEdge(1, 4, false);
		graph.insertEdge(1, 2, false);
		graph.insertEdge(2, 3, false);
		graph.insertEdge(3, 4, false);
		bfs(graph, 0);
		findPath(0, 3, parent);
	}
	
	public static void findPath(int start, int end, List<Integer> parent) {
		System.out.println("current vertice is " + end);
		int parentInt = parent.get(end);
		if (parentInt == end) {
			if (parentInt != start) {
				System.out.println("No available route");
			}
			return;
		}
		findPath(start, parentInt, parent);
	}
	
	// Breadth First Traversal
	public static void bfs(Graph graph, int start) {
		if (start > graph.numVertices - 1) {
			return;
		}

		for (int i = 0; i < graph.numVertices; i++) {
			discovered.add(false);
			processed.add(false);
			parent.add(i);
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
	
	private static void processVerticeEarly(int currVertice) {
		System.out.println("This is vertice " + currVertice);
		return;
	}
	
	private static void processEdge(int currVertice, EdgeNode edgeNode, Boolean isDirected) {
		int edgeVertice = edgeNode.verticeId;
		if (!discovered.get(edgeVertice)) {
			parent.set(edgeVertice, currVertice);
			System.out.println("process tree edge between " + currVertice + " and " + edgeVertice);
		} else if (!processed.get(edgeVertice) || !isDirected) {
			System.out.println("process back edge between " + currVertice + " and " + edgeVertice);
		}
		return;
	}
	
	private static void processVerticeLate(int verticeId) {
		System.out.println("---------------Finished processing vertice " + verticeId);
		return;
	}

}
