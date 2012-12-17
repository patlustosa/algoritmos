package graph;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import unionFind.UnionFind;

public class KruskalsAlgorithm {

	private Graph graph;
	Vector<Edge> mst;
	UnionFind unionFind;
	
	public KruskalsAlgorithm(Graph g){
		this.graph = g;
	}
	
	public Vector<Edge> computeMinimumSpanningTree(){
		runKruskal(1);
		return this.mst;
	}
	
	public UnionFind computeComponents(int qntComponents){
		runKruskal(qntComponents);
		return this.unionFind;
	}
	
	private void runKruskal(int qntComponents){
		//minimum spanning tree
		mst = new Vector<Edge>();

		//initializing unionFind
		int qntNodes = graph.getNodes().length;
		unionFind = new UnionFind(qntNodes);

		//ordering edges
		List<Edge> sortedEdges = Arrays.asList(graph.getEdges());
		Collections.sort(sortedEdges, new EdgeComparator());

		//creating iterator of ordered edges
		Iterator<Edge> iterator = sortedEdges.iterator();

		int qntLoop = qntNodes - qntComponents + 1;
		
		//main loop
		for(int i = 1; i < qntLoop; i++){			

			//find min edge that doesn´t form loops
			Edge e = iterator.next(); 
			while(unionFind.connected(e.n1.getNumber(), e.n2.getNumber())){
				e = iterator.next();
			}

			//connect nodes of chosen edge
			unionFind.union(e.n1.getNumber(), e.n2.getNumber());

			//add edge to mst
			mst.add(e);

		}

	}
	
}
