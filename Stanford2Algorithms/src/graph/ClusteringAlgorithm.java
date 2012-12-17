package graph;

import java.util.Vector;

import unionFind.UnionFind;

public class ClusteringAlgorithm {

	//week2, q1
	public static int maxSpacing(Graph g, int k){
		KruskalsAlgorithm kruskal = new KruskalsAlgorithm(g);
		UnionFind uf = kruskal.computeComponents(k);
		
		int maxSpacing = Integer.MAX_VALUE;
		
		Edge[] edges = g.getEdges();
		int qntEdges = edges.length;
		
		for(int i = 0; i < qntEdges; i++){
			Edge e = edges[i];
			if(!uf.connected(e.n1.getNumber(), e.n2.getNumber())){
				maxSpacing = Math.min(maxSpacing, e.getCost());
			}
		}
		
		return maxSpacing;
	}
	
	//week2, q2
	public static int runKruskalVariant(Vector<Vector<Integer>> nodesGroupedBy1Count, int[] nodes){
		
		Vector<BasicEdge> edges = getOrderedEdges(nodesGroupedBy1Count, nodes);
		
		//initializing unionFind
		UnionFind unionFind = new UnionFind(nodes.length);
		
		//initilize components count
		int componentsCount = nodes.length;
		
		//main loop
		for(BasicEdge e : edges){			

			//if edge doesn´t form loop, connect components
			if(!unionFind.connected(e.n1, e.n2)){
				//connect nodes of chosen edge
				unionFind.union(e.n1, e.n2);
				
				//count
				componentsCount--;
			}
		}
		
		return componentsCount;
	}
	
	private static Vector<BasicEdge> getOrderedEdges(Vector<Vector<Integer>> nodesGroupedBy1Count, int[] nodes){
		Vector<BasicEdge> edges = new Vector<BasicEdge>();
		
		addEdgesWithCountDifference(nodesGroupedBy1Count, nodes, edges, 0);
		addEdgesWithCountDifference(nodesGroupedBy1Count, nodes, edges, 1);
		addEdgesWithCountDifference(nodesGroupedBy1Count, nodes, edges, 2);
		
		return edges;
	}
	
	private static void addEdgesWithCountDifference(Vector<Vector<Integer>> nodesGroupedBy1Count, int nodes[], Vector<BasicEdge> edges, int distance){
		for(int i = 0; i < nodesGroupedBy1Count.size() - distance; i++){
			Vector<Integer> nodes1 = nodesGroupedBy1Count.elementAt(i);
			Vector<Integer> nodes2 = nodesGroupedBy1Count.elementAt(i+distance);
			
			for(int j = 0; j < nodes1.size(); j++){
				for(int k = 0; k < nodes2.size(); k++){
					int n1 = nodes1.elementAt(j);
					int n2 = nodes2.elementAt(k);
					
					if(n1 != n2 && dist(nodes[n1]^nodes[n2], 2)){
						edges.add(new BasicEdge(n1,n2));
					}
				}
			}
		}
	}
	
	private static boolean dist(int v, int ones)
	{
	   if (v == 0)
	      return true;
	   else if (ones == 0)
	      return false;
	   else
	   {
	      return ClusteringAlgorithm.dist(v & (v - 1), --ones);
	   }
	}
	
}
