package stanfordAlgo1;

import graph.DijkstraAlgorithm;
import graph.Edge;
import graph.Graph;
import graph.Node;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class W5Q1 {

	public static void main(String args[]) throws IOException{
		
		Scanner in = new Scanner(new File("src/stanfordAlgo1/dijkstra.txt"));
        
		int qntNodes = 200;
		
		Node[] nodes = new Node[qntNodes];
		Vector<Edge> edges = new Vector<Edge>();
		
		for(int i = 0; i < qntNodes; i++){
			Node n = new Node(i);
			nodes[i] = n;
		}
		
		for(int i = 0; i < qntNodes; i++){
			Node n1 = nodes[in.nextInt() - 1];
			
			while(in.hasNext() && !in.hasNextInt()){
				String[] split = in.next().split(",");
				
				Node n2 = nodes[Integer.parseInt(split[0])-1];
				int cost = Integer.parseInt(split[1]);
				
				Edge edge = new Edge(n1, n2, cost);
				edges.add(edge);
				
				n1.addEdge(edge);
				n2.addEdge(edge);
			}
			
		}

		Edge[] edgesArray = edges.toArray(new Edge[1]);
		Graph g = new Graph(nodes, edgesArray);
		
		System.out.println(DijkstraAlgorithm.bestPath(g, nodes[0], nodes[6]));
		in.close();
		
	}
	
}
