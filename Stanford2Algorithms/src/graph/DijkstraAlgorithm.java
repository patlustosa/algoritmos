package graph;

import java.util.Vector;


public class DijkstraAlgorithm {

	public static int bestPath(Graph g, Node from, Node to){
		
		int qntNodes = g.getNodes().length;
		
		//create X and puts the source node in it
		boolean[] belongsToX = new boolean[qntNodes];
		belongsToX[from.number] = true;
		
		//initialize currentNode with the source node
		Node currentNode = from;
		
		Edge[] edges = g.getEdges();
		
		//initialize array of pathEdges and pathCosts
		Edge[] pathEdge = new Edge[qntNodes];
		int[] pathCost = new int[qntNodes];
		pathCost[from.number] = 0;
		
		//while the current node is not the destination node, keep expanding the search
		while(!currentNode.equals(to)){
			Edge min = null;
			int minValue = Integer.MAX_VALUE;
			
			for(Edge e : edges){
				if((belongsToX[e.n1.number] && !belongsToX[e.n2.number]) 
				|| (!belongsToX[e.n1.number] && belongsToX[e.n2.number])){ //one node in X and other node in V-X
					
					Node parentNode = min.n1;
					if(!belongsToX[min.n1.number]){
						parentNode = min.n2;
					}
					
					//calculate the cost of path to e
					int ePathCost =  e.cost + pathCost[parentNode.number];
					
					if(ePathCost < minValue){ // e is better than previous min
						min = e;
						minValue = ePathCost;
					}
				}
			}
			
			//add new node to X
			Node newNode = min.n1;
			if(!belongsToX[min.n2.number]){
				newNode = min.n2;
			}
			belongsToX[newNode.number] = true;
			
			//set the cost of newNode path
			pathCost[newNode.number] = minValue;
			
			//parent of newNode is currentNode. Need to set this so that I can trace the path back when I find the destination
			pathEdge[newNode.number] = min;
			
			//set current node
			currentNode = newNode;	
		}
		
		//create path result
		Vector<Edge> result = new Vector<Edge>();
		while(!currentNode.equals(from)){
			Edge e = pathEdge[currentNode.number];
			result.add(e);
			currentNode = e.n2.equals(currentNode) ? e.n1 : e.n2;
		}
		
		return pathCost[to.number];
	}
	
}
