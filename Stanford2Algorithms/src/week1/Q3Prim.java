package week1;

import graph.Edge;
import graph.Graph;
import graph.KruskalsAlgorithm;
import graph.Node;
import graph.PrimsAlgorithm;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;


public class Q3Prim {

	private static long calculateMinimumSpanningTreeSize(Graph g){
		Vector<Edge> mst = PrimsAlgorithm.computeMinimumSpanningTree(g);
	
		long total = 0;
		for(Edge e : mst){
			total += e.getCost();
		}
		
		return total;
	}

	public static void main(String args[]) throws IOException{
		
		Scanner in = new Scanner(new File("src/Week1/edges.txt"));
        
		int qntNodes = in.nextInt();
		int qntEdges = in.nextInt();
		
		Node[] nodes = new Node[qntNodes];
		Edge[] edges = new Edge[qntEdges];
		
		for(int i = 0; i < qntNodes; i++){
			Node n = new Node(i);
			nodes[i] = n;
		}
		
		for(int i = 0; i < qntEdges; i++){
			Node n1 = nodes[in.nextInt() - 1];
			Node n2 = nodes[in.nextInt() - 1];
			
			Edge e = new Edge(n1, n2, in.nextInt());
			edges[i] = e;
			
			n1.addEdge(e);
			n2.addEdge(e);
		}
		
		Graph g = new Graph(nodes, edges);
		
		System.out.println(calculateMinimumSpanningTreeSize(g));
		in.close();
		
	}
	
}
