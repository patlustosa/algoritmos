package week1;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class Q1Scheduling {
	
	public static long process(Vector<Job> jobs){
		Collections.sort(jobs);
		
		long weightedCompTimesSum = 0;
		int time = 0;
		
		for(Job j : jobs){
			time += j.length;
			weightedCompTimesSum += j.weight * time; 
		}
		return weightedCompTimesSum;
	}

	public static void main(String args[]) throws IOException{
		
		Scanner in = new Scanner(new File("src/Week1/jobs.txt"));
        
		int n = in.nextInt();
		Vector<Job> jobs = new Vector<Job>();
 		
		for(int i = 1; i <= n; i++){
			Job j = new Job(in.nextInt(), in.nextInt(), false); //  if true, questao 2
															   //  if false, questao 1 
			jobs.add(j);
		}
		
		System.out.println(process(jobs));
		in.close();
		
	}
}

class Job implements Comparable<Job>{
	public int weight;
	public int length;
	boolean optimal;
	
	public Job(int w, int l, boolean optimal){
		this.weight = w;
		this.length = l;
		this.optimal = optimal;
	}
	
	public double function(){
		if(optimal){
			return (double)weight / (double)length;
		}else{
			return weight - length;	
		}
	}

	@Override
	public int compareTo(Job job) {
		if(!optimal && job.function() == this.function()){
			return Double.compare(job.weight, this.weight);
		}
		return Double.compare(job.function(), this.function()); //ao contrario para ordenar do maior para o menor
	}
}