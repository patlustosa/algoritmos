package heap;

import graph.Edge;
import graph.EdgeComparator;
import graph.Node;

import java.util.Vector;


public class HeapNode {

	private Vector<Node> elmts;
	private int next;
	private EdgeComparator comparator;
	
	int nodesInfo[];
	Edge nodesMinEdges[];
	
	public HeapNode(int qntNodes){
		this.elmts = new Vector<Node>();
		this.next = 0;
		this.comparator = new EdgeComparator();
		
		this.nodesInfo = new int[qntNodes];
		this.nodesMinEdges = new Edge[qntNodes];
		
		for(int i = 0; i < qntNodes; i++){
			nodesInfo[i] = -1;
		}
	}
	
	public void add(Node t){
		elmts.add(t);
		nodesInfo[t.getNumber()] = next;
		
		this.bubbleUp(next);
		next++;
	}
	
	private void bubbleUp(int childIndex){
		while(childIndex > 0){
			int parentIndex = (childIndex - 1)/2;
			
			Node parent = elmts.elementAt(parentIndex);
			Node child = elmts.elementAt(childIndex);
			
			if(this.compare(parent, child) > 0){//parent is bigger then child
				//swap elements
				this.setElementAt(parent, childIndex);
				this.setElementAt(child, parentIndex);
				
				childIndex = parentIndex;
			}else{
				//parent is smaller then child. heap property restored
				break;
			}
		}
	}
	
	private int compare(Node parent, Node child) {
		return comparator.compare(nodesMinEdges[parent.getNumber()], nodesMinEdges[child.getNumber()]);
	}

	public Node extractMin(){
		Node min = this.min();
		
		//delete root and move the last element to be new root
		Node last = removeLast();
		if(next > 0){
			this.setElementAt(last, 0);	
		}
		
		bubbleDown(0);
		
		return min;
	}
	
	private void bubbleDown(int parentIndex){
		//if there is no child, i don't proceed
		while(parentIndex*2 + 1 < next){
			//get the smallest child. Children in i*2+1 e i*2+2
			int childIndex = parentIndex*2 + 1;
			if(childIndex+1 < next && this.compare(elmts.elementAt(childIndex), elmts.elementAt(childIndex+1)) > 0){
				childIndex++;
			}
			
			Node parent = elmts.elementAt(parentIndex);
			Node child = elmts.elementAt(childIndex);

			if(this.compare(parent, child) > 0){//parent is bigger then child
				//swap elements
				this.setElementAt(parent, childIndex);
				this.setElementAt(child, parentIndex);

				parentIndex = childIndex;
			}else{
				//parent is smaller then child. heap property restored. 
				break;
			}
		}
	}
	
	public Node min(){
		return this.elmts.elementAt(0);
	}
	
	public void heapify(Vector<Node> elmts){
		//TODO
	}
	
	public void delete(Node t){	
		int i = nodesInfo[t.getNumber()];
		
		if(i < 0){ //no such element exists
			return;
		}
		
		if(i == next-1){ //the element to be deleted is the last one. just delete it. dont need to bubble up and down
			removeLast();
		}
		else{
			//delete element with index i and move the last element to i
			Node last = removeLast();
			this.setElementAt(last, i);
			
			//bubble i up and down
			bubbleUp(i);
			bubbleDown(i);
		}
		
	}

	private Node removeLast() {
		Node last = elmts.lastElement();
		next--;
		elmts.remove(next);
		nodesInfo[last.getNumber()] = -1;
		return last;
	}	
	
	private void setElementAt(Node n, int index){
		this.elmts.setElementAt(n, index);
		nodesInfo[n.getNumber()] = index;
	}
	
	public void setNodeInfo(Node node, int info){
		nodesInfo[node.getNumber()] = info;
	}
	
	public int getNodeInfo(Node node){
		return nodesInfo[node.getNumber()];
	}
	
	public void setNodeMinEdge(Node node, Edge e){
		nodesMinEdges[node.getNumber()] = e;
	}
	
	public Edge getNodeMinEdge(Node node){
		return nodesMinEdges[node.getNumber()];
	}
	
}
