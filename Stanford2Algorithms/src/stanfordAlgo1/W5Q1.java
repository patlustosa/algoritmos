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
			String line = in.nextLine();
			Scanner lineScanner = new Scanner(line);
			
			Node n1 = nodes[lineScanner.nextInt() - 1];
			
			while(lineScanner.hasNext()){
				String[] split = lineScanner.next().split(",");
				
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
		
		//input 7,37,59,82,99,115,133,165,188,197
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(g);
		dijkstra.bestPath(nodes[0]);
		System.out.print(dijkstra.getPathCost(6) + ",");
		System.out.print(dijkstra.getPathCost(36) + ",");
		System.out.print(dijkstra.getPathCost(58) + ",");
		System.out.print(dijkstra.getPathCost(81) + ",");
		System.out.print(dijkstra.getPathCost(98) + ",");
		System.out.print(dijkstra.getPathCost(114) + ",");
		System.out.print(dijkstra.getPathCost(132) + ",");
		System.out.print(dijkstra.getPathCost(164) + ",");
		System.out.print(dijkstra.getPathCost(187) + ",");
		System.out.print(dijkstra.getPathCost(196) + ",");
		//output 2599,2610,2947,2052,2367,2399,2029,2442,2505,3068
		
//		Vector<Edge> resultEdges = dijkstra.getEdgesOnPath(nodes[6]);
//		int count = 0;
//		for(Edge e : resultEdges){
//			count += e.getCost();
//		}
		
		in.close();
		
	}
	
}
