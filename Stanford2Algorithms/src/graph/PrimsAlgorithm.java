package graph;

import heap.Heap;
import heap.HeapNode;

import java.util.Vector;


public class PrimsAlgorithm {
	
	private static boolean[] belongsToX;
		
	public static Vector<Edge> computeMinimumSpanningTree(Graph g){
		return computeMinimumSpanningTreeNodeHeap(g);
	}
	
	/**
	 * Compute the minimum spanning tree using a heap that stores the nodes of the graph.
	 * Running time: O(m log n) with better constants than Edge Heap 
	 * @param g graph
	 * @return edges that form the minimum spanning tree
	 */
	public static Vector<Edge> computeMinimumSpanningTreeNodeHeap(Graph g){
		//minimum spanning tree
		Vector<Edge> mst = new Vector<Edge>();

		//defining X
		int qntNodes = g.getNodes().length;
		belongsToX = new boolean[qntNodes];

		//initializing X
		belongsToX[0] = true;
		
		//initializing heap
		HeapNode heap = new HeapNode(qntNodes);
		restoreHeapProperties(g.getNodes()[0], heap);

		//main loop
		for(int i = 1; i < qntNodes; i++){			
			//find minimum edge that crosses the frontier
			Node newNode = heap.extractMin();
			Edge min = heap.getNodeMinEdge(newNode);
			
			//add new node to X
			belongsToX[newNode.number] = true;

			//add minimum edge to tree
			mst.add(min);
			
			//restore heap properties
			restoreHeapProperties(newNode, heap);
		}
				
		return mst;
	}

	/**
	 * Restore heap properties of an Node Heap.
	 * @param newNode new node added to X.
	 * @param heap node heap.
	 */
	private static void restoreHeapProperties(Node newNode, HeapNode heap) {
		Vector<Edge> edges = newNode.edges;
		
		//I need to analize all nodes that share an edge with newNode and only them.  
		for(Edge e : edges){
			Node otherNode = (e.n1 == newNode) ? e.n2 : e.n1;
			
			if(!belongsToX[otherNode.number]){ //newNode in X and otherNode in V-X. 
				if(heap.getNodeInfo(otherNode) == -1){ //node didn't exist on heap. Add it.
					heap.setNodeMinEdge(otherNode, e);
					heap.add(otherNode);
				}else{ 												  //node already exists on heap
					if(heap.getNodeMinEdge(otherNode).cost > e.cost){ //new edge costs less then previous. Delete and re-add the node.
						heap.delete(otherNode);
						
						heap.setNodeMinEdge(otherNode, e);
						heap.add(otherNode);
					}
				}
			}
		}
	}

	/**
	 * Compute the minimum spanning tree using a heap that stores the edges of the graph.
	 * I am not storing the indices of the edges in the heap, so I always have to lookup ( O(n) ) the edge in the heap when I need to delete it.
	 * Because of that, the running time is O(mn). If I have used another data structure for this, as I did in the NodeHeap, 
	 * the running time would be O(mlogn) but with worse constants than the NodeHeap.
	 * @param g graph
	 * @return edges that form the minimum spanning tree
	 */
	
	public static Vector<Edge> computeMinimumSpanningTreeEdgeHeap(Graph g){
		//minimum spanning tree
		Vector<Edge> mst = new Vector<Edge>();

		//defining X
		int qntNodes = g.getNodes().length;
		belongsToX = new boolean[qntNodes];

		//initializing X
		belongsToX[0] = true;
		
		//initializing heap
		Heap<Edge> heap = new Heap<Edge>(new EdgeComparator());
		restoreHeapProperties(g.getNodes()[0], heap);

		//main loop
		for(int i = 1; i < qntNodes; i++){			
			//find minimum edge that crosses the frontier
			Edge min = heap.extractMin();

			//add new node to X
			Node newNode = min.n1;
			if(belongsToX[min.n1.number] && !belongsToX[min.n2.number]){
				newNode = min.n2;
			}
			belongsToX[newNode.number] = true;

			//add minimum edge to tree
			mst.add(min);
			
			//restore heap properties
			restoreHeapProperties(newNode, heap);
		}
				
		return mst;
	}

	/**
	 * Restore heap properties of an Edge Heap.
	 * @param newNode new node added to X.
	 * @param heap edge heap.
	 */
	private static void restoreHeapProperties(Node newNode, Heap<Edge> heap) {
		Vector<Edge> edges = newNode.edges;
		
		//I need to analize all edges with newNode and only them. 
		for(Edge e : edges){
			if((e.n1 == newNode && belongsToX[e.n2.number]) || (e.n2 == newNode && belongsToX[e.n1.number])){
				//both edges in X. need to delete it from heap because it is not a crossing edge.
				heap.delete(e);
			}else {
				//one node in X and other in V-X. put it in the heap because it is a crossing edge.
				heap.add(e);
			}
		}
	}
	
	/**
	 * Compute the minimum spanning tree without any additional data structures. The naive implementation.
	 * Every time I add a new edge to X, I need to visit all edges of the graph and find the minimum one that crosses the frontier. 
	 * @param g graph
	 * @return edges that form the minimum spanning tree
	 */
	
	public static Vector<Edge> computeMinimumSpanningTreeNaive(Graph g) {
		
		//minimum spanning tree
		Vector<Edge> mst = new Vector<Edge>();
		
		//defining X
		int qntNodes = g.getNodes().length;
		belongsToX = new boolean[qntNodes];
		
		//initializing X
		belongsToX[0] = true;
		
		//main loop
		for(int i = 1; i < qntNodes; i++){
			Edge min = null;
			
			//find minimum edge that crosses the frontier
			for(Edge e : g.getEdges()){
				if(min == null || e.cost < min.cost){ // e is better than minimum
					if((belongsToX[e.n1.number] && !belongsToX[e.n2.number]) || (!belongsToX[e.n1.number] && belongsToX[e.n2.number])){ //e is in the frontier
						min = e;
					}
				}
			}
			
			//add new node to X
			Node newNode = min.n1;
			if(belongsToX[min.n1.number] && !belongsToX[min.n2.number]){
				newNode = min.n2;
			}
			belongsToX[newNode.number] = true;
			
			//add minimum edge to tree
			mst.add(min);
		}
		
		return mst;
	}
	
}
