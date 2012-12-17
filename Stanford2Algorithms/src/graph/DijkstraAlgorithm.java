package graph;

import java.util.Vector;


public class DijkstraAlgorithm {

	public Vector<Edge> bestPath(Graph g, Node from, Node to){
		
		//TODO qual o resultado certo???
		Vector<Edge> result = new Vector<Edge>();
		
		//create X and puts the source node in it
		boolean[] belongsToX = new boolean[g.getNodes().length];
		belongsToX[from.number] = true;
		
		//initialize currentNode with the source node
		Node currentNode = from;
		
		Edge[] edges = g.getEdges();
		
		//while the current node is not the destination node, keep expanding the search
		while(!currentNode.equals(to)){
			Edge min = null;
			for(Edge e : edges){
				if((belongsToX[e.n1.number] && !belongsToX[e.n2.number]) 
				|| (!belongsToX[e.n1.number] && belongsToX[e.n2.number])){ //one node in X and other node in V-X
					if(min == null || e.cost < min.cost){ // e is better than previous min
						min = e;
					}
				}
			}
			
			//add new node to X
			Node newNode = min.n1;
			if(!belongsToX[min.n2.number]){
				newNode = min.n2;
			}
			belongsToX[newNode.number] = true;
			
			//set current node
			currentNode = newNode;
			
			//put min in result
			//TODO nao devo colocar min em result.. devo colocar isso no n— de alguma forma pra ele saber seguir o caminho de volta.
			result.add(min);
			
		}
		
		
		return result;
	}
	
}
