package baekjoon;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

class TraversalSolver {
	int vertex;
	int edge;
	int[] visitedArray;
	LinkedList<Integer>[] linkedlist;
	
	public TraversalSolver(int vertex, int edge, Scanner sc) {
		this.vertex = vertex;
		this.edge = edge;
		
		visitedArray = new int[vertex+1];
		linkedlist = new LinkedList[vertex+1];
		
		for(int i = 1 ; i < vertex+1 ; i++) {
			visitedArray[i] = 0;
			linkedlist[i] = new LinkedList<Integer>();
		}
		
		for(int i = 0 ; i < edge ; i++) {
			String edgeStr = sc.nextLine();
			if(!edgeStr.isEmpty()) {
				String[] temp = edgeStr.split(" ");
				int startV = Integer.parseInt(temp[0]);
				int endV = Integer.parseInt(temp[1]);
				linkedlist[startV].add(endV);
			}
		}
	}
	
	public void DFS_recursive(int vertex) {
		visitedArray[vertex] = 1;
		for(int i : linkedlist[vertex]) {
			if(visitedArray[i] == 0) {
				System.out.print(" "+i);
				DFS_recursive(i);
			}
		}
	}
	
	public void DFS_iterate() {
		
	}
	
	public void BFS(int vertex) {
		clearVisitedArray();
		Queue<Integer> queue = new LinkedList<>();
		queue.add(vertex);
		visitedArray[vertex] = 1;
		while(!queue.isEmpty()) {
			int v = queue.remove();
			for(int i : linkedlist[v]) {
				if(visitedArray[i] == 0) {
					queue.add(i);
					System.out.print(" "+i);
					visitedArray[i] = 1;
				}
			}
		}
	}
	
	public void clearVisitedArray() {
		for(int i = 1 ; i < vertex+1 ; i++) {
			visitedArray[i] = 0;
		}
	}
}

public class DFSandBFS1260_1002 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		String[] strArray = str.split(" ");
		int vertex = Integer.parseInt(strArray[0]);
		int edge = Integer.parseInt(strArray[1]);
		int initV = Integer.parseInt(strArray[2]);
		
		TraversalSolver ts = new TraversalSolver(vertex, edge, sc);
		
		System.out.print(initV);
		ts.DFS_recursive(initV);
		
		System.out.println();
		
		System.out.print(initV);
		ts.BFS(initV);
		
	}

}
