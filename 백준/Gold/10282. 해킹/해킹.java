import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	public static StringBuilder sb = new StringBuilder();
	
	public static int[] dx = {0,1,0,-1};
	public static int[] dy = {-1,0,1,0};
	
	public static int INF = Integer.MAX_VALUE;
	
	public static class Pos implements Comparable<Pos>{
		int to;
		int distance;
		
		public Pos() {}
		public Pos(int to, int distance) {
			this.to = to;
			this.distance = distance;
		}
		@Override
		public int compareTo(Pos o) {
			return this.distance - o.distance;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			sb = new StringBuilder();
			
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			ArrayList<Pos>[] route = new ArrayList[n+1];
			for(int i = 0; i <= n; i++)route[i] = new ArrayList<>();
			
			for(int i = 0 ; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int sec = Integer.parseInt(st.nextToken());
				
				
				route[b].add(new Pos(a,sec));
			}
			
			
			PriorityQueue<Pos> pq = new PriorityQueue<>();
			int[] dp = new int[n+1];
			Arrays.fill(dp, INF);
			dp[c] = 0;
			boolean[] visited = new boolean[n+1];

			
			pq.add(new Pos(c,0));
			int count = 0;
			
			while(!pq.isEmpty()) {
				Pos cur = pq.poll();
				
				if(visited[cur.to])continue;
				visited[cur.to] = true;
				count++;
				for(Pos next : route[cur.to]) {
					if(dp[next.to] > dp[cur.to] + next.distance) {
						dp[next.to] = dp[cur.to] + next.distance;
						pq.add(new Pos(next.to,dp[next.to]));
						
					}
				}
				
			}
			
			System.out.println(count + " " + Arrays.stream(dp).reduce(0,(x,v)->{
				if(v == INF) return x;
				return Math.max(x, v);
			}));
			
		}
		
		
	}
}
