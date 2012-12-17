package graph;

import heap.HeapNode;

import java.util.Collections;
import java.util.Vector;


public class DijkstraAlgorithm {

	Graph graph;
	Node from;
	Edge[] pathEdge;
	int[] pathCost;
	
	public DijkstraAlgorithm(Graph g){
		this.graph = g;
	}
	
	public void bestPath(Node from){
		bestPathNaive(from);
	}
	
	private void bestPathNaive(Node from){
		
		this.from = from;
		int qntNodes = graph.getNodes().length;
		
		//create X and puts the source node in it
		boolean[] belongsToX = new boolean[qntNodes];
		belongsToX[from.number] = true;
		
		Edge[] edges = graph.getEdges();
		
		//initialize array of pathEdges and pathCosts
		pathEdge = new Edge[qntNodes];
		pathCost = new int[qntNodes];
		pathCost[from.number] = 0;
		
		//main loop
		while(true){
			Edge min = null;
			int minValue = Integer.MAX_VALUE;
			
			//find the edge in frontier with minimum path cost 
			for(Edge e : edges){
				if((belongsToX[e.n1.number] && !belongsToX[e.n2.number]) 
				|| (!belongsToX[e.n1.number] && belongsToX[e.n2.number])){ //one node in X and other node in V-X: node in the frontier
					//get parent
					Node parentNode = e.n1;
					if(!belongsToX[e.n1.number]){
						parentNode = e.n2;
					}
					
					//calculate the cost of path to e
					int ePathCost =  e.cost + pathCost[parentNode.number];
					
					if(ePathCost < minValue){ // e is better than previous min
						min = e;
						minValue = ePathCost;
					}
				}
			}
			
			//if didnt find an edge with one node in X and other node in V-X, the search is over.
			if(min == null){
				break;
			}
			
			//add new node to X
			Node newNode = min.n1;
			if(!belongsToX[min.n2.number]){
				newNode = min.n2;
			}
			belongsToX[newNode.number] = true;
			
			//set the cost of newNode path
			pathCost[newNode.number] = minValue;
			
			//min is the edge from which i get to newNode. Need to set this so that I can trace the path back when I find the destination
			pathEdge[newNode.number] = min;
		}
	}
	
	private void bestPathHeap(Node from){
		
		this.from = from;
		int qntNodes = graph.getNodes().length;
		
		//create X and puts the source node in it
		boolean[] belongsToX = new boolean[qntNodes];
		belongsToX[from.number] = true;
		
		Edge[] edges = graph.getEdges();
		
		//initialize array of pathEdges and pathCosts
		pathEdge = new Edge[qntNodes];
		pathCost = new int[qntNodes];
		pathCost[from.number] = 0;
		
		//initialize heap
		HeapNode heap = new HeapNode(qntNodes);
		restoreHeapProperties(from);
		
		//main loop
		while(true){ //TODO rever essa condicao
			Edge min = null;
			int minValue = Integer.MAX_VALUE;
			
			//extractMin from heap: find the edge in frontier with minimum path cost 
			Node newNode = heap.extractMin();
			
			//add new node to X
			belongsToX[newNode.number] = true;
			
			//set the cost of newNode path
			pathCost[newNode.number] = minValue; //TODO find min value
			
			//min is the edge from which i get to newNode. Need to set this so that I can trace the path back when I find the destination
			pathEdge[newNode.number] = min; //TODO find min edge
			
			//restore heap properties
			restoreHeapProperties(newNode);
		}
	}
	
	
	private void restoreHeapProperties(Node newNode) {
		// TODO Auto-generated method stub
		
	}

	public Vector<Edge> getEdgesOnPath(Node destination){
		Node currentNode = destination;
		
		Vector<Edge> result = new Vector<Edge>();
		while(!currentNode.equals(from)){
			Edge e = pathEdge[currentNode.number];
			result.add(e);
			currentNode = e.n2.equals(currentNode) ? e.n1 : e.n2;
		}
		//I add the edges from destination to source. So I need to reverse the list.
		Collections.reverse(result);
		
		return result;
	}
	
	public int getPathCost(int destination){
		return pathCost[destination];
	}
	
}
