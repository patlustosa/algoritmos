package unionFind;

import java.util.Vector;

public class UnionFind<T>{

	Vector<T> objects;
	
	public UnionFind(Vector<T> ts){
		this.objects = ts;
	}
	
	public void union(UnionFindObject<T> a, UnionFindObject<T> b){
		
	}
	
	public T find(UnionFindObject<T> a){
		return a.getParent();
	}
	
	
}
