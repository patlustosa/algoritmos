package graph;

import heap.HeapNode;

import java.util.Vector;

import unionFind.UnionFind;

public class KruskalsAlgorithm {

	public static Vector<Edge> computeMinimumSpanningTree(Graph g){
		//minimum spanning tree
		Vector<Edge> mst = new Vector<Edge>();

		int qntNodes = g.getNodes().length;
		
		//initializing unionFind
		//UnionFind<Node> uf = new UnionFind<Node>(g.getNodes());

		//main loop
		for(int i = 1; i < qntNodes; i++){			
//			//find minimum edge that crosses the frontier
//			Node newNode = heap.extractMin();
//			Edge min = heap.getNodeMinEdge(newNode);
//
//			//add new node to X
//			belongsToX[newNode.number] = true;
//
//			//add minimum edge to tree
//			mst.add(min);
//
//			//restore heap properties
//			restoreHeapProperties(newNode, heap);
		}

		return mst;
	}
	
}
