package graph;

public class Graph {

	private Node[] nodes;
	private Edge[] edges;
	
	public Graph(Node[] nodes, Edge[] edges){
		this.setNodes(nodes);
		this.setEdges(edges);
	}
	
	public Node[] getNodes() {
		return nodes;
	}

	public void setNodes(Node[] nodes) {
		this.nodes = nodes;
	}

	public Edge[] getEdges() {
		return edges;
	}

	public void setEdges(Edge[] edges) {
		this.edges = edges;
	}
}
