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
		int x;
		int y;
		int chance;
		
		public Pos() {}
		public Pos(int x, int y, int chance) {
			this.x = x;
			this.y = y;
			this.chance= chance;
		}
		@Override
		public int compareTo(Pos o) {
			return this.chance - o.chance;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int t = 0;
		while(true) {
			t++;
			int N  = Integer.parseInt(br.readLine());
			if(N == 0)break;
			
			int[][] board= new int[N][N];
			int[][] dp= new int[N][N];
			boolean[][] visited= new boolean[N][N];
			
			for(int i = 0 ;i < N ; i++) {
				board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				Arrays.fill(dp[i], INF);
			}
			
			PriorityQueue<Pos> pq = new PriorityQueue<>();
			
			pq.add(new Pos(0,0,board[0][0]));
			dp[0][0] = board[0][0];
			
			while(!pq.isEmpty()) {
				Pos cur = pq.poll();
				
				if(visited[cur.y][cur.x])continue;
				visited[cur.y][cur.x] = true;
				
				for(int i = 0; i < 4; i++) {
					int mx = dx[i] + cur.x;
					int my = dy[i] + cur.y;
					
					if(mx < 0 || mx >= N || my < 0 || my >= N || visited[my][mx]) continue;
					
					if(dp[my][mx] > dp[cur.y][cur.x] + board[my][mx]) {
						dp[my][mx] = dp[cur.y][cur.x] + board[my][mx];
						pq.add(new Pos(mx,my,dp[my][mx]));
					}
				} 
			}
			System.out.println("Problem "+ t + ": "+dp[N-1][N-1]);
		}
	}
}
