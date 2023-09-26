import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	public static StringBuilder sb = new StringBuilder();
	
	public static int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
		PriorityQueue<Integer> minorpq = new PriorityQueue<>();
		
		for(int i = 0; i < N ; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num > 0)	pq.add(num);
			else		minorpq.add(num);
		}
		
		int answer = 0;
		
		while(pq.size() > 1) {
			int a = pq.poll();
			int b = pq.poll();
			
			answer += Math.max(a*b, a+b);
		}
		
		while(!pq.isEmpty()) {
			answer += pq.poll();
		}
		
		while(minorpq.size() > 1) {
			int a = minorpq.poll();
			int b = minorpq.poll();
			
			answer += Math.max(a*b, a+b);
		}
		while(!minorpq.isEmpty()) {
			answer += minorpq.poll();
		}
		System.out.println(answer);
	}
}
