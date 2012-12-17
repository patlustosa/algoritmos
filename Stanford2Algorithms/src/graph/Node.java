package graph;

import java.util.Vector;

public class Node{
	int number;
	Vector<Edge> edges;
	
	public Node(int n){
		number = n;
		this.edges = new Vector<Edge>();
	}
	
	public void addEdge(Edge e){
		this.edges.add(e);
	}
	
	public int getNumber(){
		return this.number;
	}
	
	public Vector<Edge> getEdges(){
		return this.edges;
	}

	public Node getParent() {
		// TODO Auto-generated method stub
		return null;
	}
}

//class NodeComparator implements Comparator<Node>{
//
//	@Override
//	public int compare(Node node0, Node node1) {
//		return new Integer(node0.minEdge.cost).compareTo(node1.minEdge.cost);
//	}
//	
//}