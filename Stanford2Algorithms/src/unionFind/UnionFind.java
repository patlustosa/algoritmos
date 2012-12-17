package unionFind;

public class UnionFind{

	int[] id;
	
	public UnionFind(int size){
		this.id = new int[size];
		
		for(int i = 0; i < id.length; i++){
			this.id[i] = i;
		}
 	}
	
	public void union(int a, int b){
		int ida = id[a];
		int idb = id[b];
		
		for(int i = 0; i < id.length; i++){
			if(id[i] == ida){
				id[i] = idb;
			}
		}
	}
	
	public int find(int a){
		return id[a];
	}
	
	public boolean connected(int a, int b){
		return id[a] == id[b];
	}
	
}
