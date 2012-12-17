package week2;

import graph.ClusteringAlgorithm;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Q2Clustering {

	public static void main(String args[]) throws IOException{
		
		Scanner in = new Scanner(new File("src/Week2/clustering2.txt"));
        
		int qntNodes = in.nextInt();
		int b = in.nextInt();
		
		int[] nodes = new int[qntNodes];
		int[] count1 = new int[qntNodes];
		Vector<Vector<Integer>> nodesGroupedBy1Count = new Vector<Vector<Integer>>();
		
		for(int i = 0; i < 24; i++){
			nodesGroupedBy1Count.add(new Vector<Integer>());
		}

		for(int i = 0; i < qntNodes; i++){
			int n = 0;
			int count = 0;
			
			for(int j = 0; j < b; j++){
				int a = in.nextInt();
				n = 2*n + a;
				if(a == 1){
					count++;
				}
			}
			
			count1[i] = count;
			nodes[i] = n;
			nodesGroupedBy1Count.elementAt(count).add(i);
		}

		System.out.println(ClusteringAlgorithm.runKruskalVariant(nodesGroupedBy1Count, nodes));
		in.close();
		
	}
}
