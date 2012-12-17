package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapJava<T> {
	
	PriorityQueue<T> pq;
	
	public HeapJava(Comparator<T> c){
		this.pq = new PriorityQueue<T>(30,c);
	}
	
	public void add(T t){
		pq.add(t);
	}
	
	public T extractMin(){
		return pq.poll();
	}
	
	public T min(){
		return pq.peek();
	}
	
	public void delete(T t){
		pq.remove(t);
	}	
	
	
	
}