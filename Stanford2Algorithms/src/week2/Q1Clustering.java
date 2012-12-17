package week2;

import graph.ClusteringAlgorithm;
import graph.Edge;
import graph.Graph;
import graph.Node;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Q1Clustering {
	
		
	public static void main(String args[]) throws IOException{
		
		Scanner in = new Scanner(new File("src/Week2/clustering1.2.txt"));
        
		int qntNodes = in.nextInt();
		int qntEdges = qntNodes*(qntNodes-1)/2;
		
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
		
		System.out.println(ClusteringAlgorithm.maxSpacing(g, 4));
		in.close();
		
	}
	
	
}
