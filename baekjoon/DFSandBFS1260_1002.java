package baekjoon;

import java.util.Scanner;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/*
 * (1) '연결된 노드가 여러 개일 때 숫자가 가장 작은 노드부터 방문한다' 조건 고려 안함
 * (2) '한 간선이 여러 번 주어질 수도 있는데, 간선이 하나만 있는 것으로 생각하면 된다' 조건 고려 안함
 * (3) nextLine()이 \n까지 읽어버려서 엔터를 한 번 더 입력해야하는 문제 발생
 * (4) 위 문제로 시간 많이 소요 but edge 입력 개수는 M개라고 문제에 명시되어 있었음
 * (4) linkedlist[endV].add(startV); 추가해보았음
 */

class TraversalSolver {
	private int vertex;
	private int[] visitedArray;
	private LinkedList<Integer>[] linkedlist;
	
	@SuppressWarnings("unchecked")
	public TraversalSolver(int vertex, int edge, Scanner sc) {
		this.vertex = vertex;
		visitedArray = new int[vertex+1];
		linkedlist = new LinkedList[vertex+1];
		
		for(int i = 1 ; i < vertex+1 ; i++) {
			visitedArray[i] = 0;
			linkedlist[i] = new LinkedList<Integer>();
		}
		for(int i = 0 ; i < edge ; i++) {
			String edgeStr = sc.nextLine();
			if(!edgeStr.isEmpty()) {
				String[] vertexTemp = edgeStr.split(" ");
				int startV = Integer.parseInt(vertexTemp[0]);
				int endV = Integer.parseInt(vertexTemp[1]);
				linkedlist[startV].add(endV);
				linkedlist[endV].add(startV);
			}
		}
		for(int i = 1 ; i < vertex+1 ; i++) {
			if(!linkedlist[i].isEmpty())
				Collections.sort(linkedlist[i]);
		}
	}
	
	public void DFS_recursive(int vertex) {
		markVisited(vertex);
		for(int v : linkedlist[vertex]) {
			if(!isVisited(v)) {
				System.out.print(" "+v);
				DFS_recursive(v);
			}
		}
	}
	
	public void DFS_iterate() {
		// TODO
	}
	
	public void BFS(int vertex) {
		clearVisitedArray();
		Queue<Integer> queue = new LinkedList<>();
		queue.add(vertex);
		markVisited(vertex);
		while(!queue.isEmpty()) {
			int v = queue.remove();
			for(int i : linkedlist[v]) {
				if(!isVisited(i)) {
					queue.add(i);
					System.out.print(" "+i);
					markVisited(i);
				}
			}
		}
	}
	
	public void clearVisitedArray() {
		for(int i = 1 ; i < vertex+1 ; i++)
			visitedArray[i] = 0;
	}
	
	public boolean isVisited(int v) {
		if(visitedArray[v] == 0)
			return false;
		return true;
	}
	
	public void markVisited(int v) {
		visitedArray[v] = 1;
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
