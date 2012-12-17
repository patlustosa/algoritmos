package heap;

import graph.Edge;
import graph.EdgeComparator;

public class HeapTest {

	public static void main(String args[]){
		
		Edge e25 = new Edge(null,null,25);
		Edge e17 = new Edge(null,null,17);
		
		Heap<Edge> heap = new Heap<Edge>(new EdgeComparator());
		heap.add(new Edge(null, null,10));
		heap.add(new Edge(null, null,15));
		heap.add(new Edge(null, null,12));
		heap.add(new Edge(null, null,5));
		heap.add(e25);
		heap.add(e17);
		System.out.println(heap.extractMin().getCost()); //5
		System.out.println(heap.extractMin().getCost()); //10
		heap.add(new Edge(null, null,20));
		heap.add(new Edge(null, null,11));
		heap.add(new Edge(null, null,21));
		heap.add(new Edge(null, null,35));
		heap.delete(e25);
		heap.add(new Edge(null, null,14));
		heap.delete(e17);
		
	}
	
}
