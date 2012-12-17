package heap;

import java.util.Comparator;
import java.util.Vector;

public class Heap<T> {

	private Vector<T> elmts;
	int next;
	Comparator<T> comparator;
	
	public Heap(Comparator<T> c){
		this.elmts = new Vector<T>();
		this.next = 0;
		this.comparator = c;
	}
	
	public void add(T t){
		elmts.add(t);
		this.bubbleUp(next);
		next++;
	}
	
	private void bubbleUp(int childIndex){
		while(childIndex > 0){
			int parentIndex = (childIndex - 1)/2;
			
			T parent = elmts.elementAt(parentIndex);
			T child = elmts.elementAt(childIndex);
			
			if(comparator.compare(parent, child) > 0){//parent is bigger than child
				//swap elements
				elmts.setElementAt(parent, childIndex);
				elmts.setElementAt(child, parentIndex);
				
				childIndex = parentIndex; //continue bubble up with parent.
			}else{
				//parent is smaller than child. heap property restored. can stop bubble up.
				break;
			}
		}
	}
	
	public T extractMin(){
		T min = this.min();
		
		//delete root and move the last element to be new root
		T last = removeLast();
		if(next > 0){
			this.elmts.setElementAt(last, 0);	
		}
		
		bubbleDown(0);
		
		return min;
	}
	
	private void bubbleDown(int parentIndex){
		//if there is no child, i don't proceed
		while(parentIndex*2 + 1 < next){
			//get the smallest child. Children in i*2+1 e i*2+2
			int childIndex = parentIndex*2 + 1;
			if(childIndex+1 < next && comparator.compare(elmts.elementAt(childIndex), elmts.elementAt(childIndex+1)) > 0){
				childIndex++;
			}
			
			T parent = elmts.elementAt(parentIndex);
			T child = elmts.elementAt(childIndex);

			if(comparator.compare(parent, child) > 0){//parent is bigger than child
				//swap elements
				elmts.setElementAt(parent, childIndex);
				elmts.setElementAt(child, parentIndex);

				parentIndex = childIndex; //continue bubble down with child
			}else{
				//parent is smaller than child. heap property restored. can stop bubble down.
				break;
			}
		}
	}
	
	public T min(){
		return this.elmts.elementAt(0);
	}
	
	public void heapify(Vector<T> elmts){
		//TODO
	}
	
	public void delete(T t){	
		int i = this.elmts.indexOf(t);
		
		if(i < 0){ //no such element exists
			return;
		}
		
		if(i == next-1){ //the element to be deleted is the last one. just delete it. don't need to bubble up and down
			removeLast();
		}
		else{
			//delete element with index i and move the last element to i
			T last = removeLast();
			this.elmts.setElementAt(last, i);
			
			//bubble i up and down
			bubbleUp(i);
			bubbleDown(i);
		}
		
	}

	private T removeLast() {
		T last = elmts.lastElement();
		next--;
		elmts.remove(next);
		return last;
	}	
	
}
