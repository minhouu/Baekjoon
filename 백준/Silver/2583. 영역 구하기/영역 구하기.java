import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	public static StringBuilder sb = new StringBuilder();
	
	public static int[] dx = {0,1,0,-1};
	public static int[] dy = {-1,0,1,0};
	
	public static int INF = Integer.MAX_VALUE;
	
	public static class Pos{
		int x;
		int y;
		
		public Pos() {}
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N][M];
		
		for(int i = 0 ; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for(int x = x1; x < x2; x++) {
				for(int y= y1; y < y2; y++) {
					board[y][x] = -1;
				}
			}
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int count = 1;
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				if(board[y][x] != 0)continue;
				
				Queue<Pos> qu = new ArrayDeque<>();
				qu.add(new Pos(x,y));
				int size = 0;
				while(!qu.isEmpty()) {
					Pos cur = qu.poll();
					
					if(board[cur.y][cur.x] != 0)continue;
					board[cur.y][cur.x] = count;
					size++;
					for(int i = 0; i < 4; i++) {
						int mx = dx[i] + cur.x;
						int my = dy[i] + cur.y;
						
						if(mx >= M || my >= N || mx < 0 || my < 0 || board[my][mx] != 0)continue;
						qu.add(new Pos(mx,my));
					}
				}
				count++;
				pq.add(size);
			}
		}
		
		while(!pq.isEmpty()) {
			sb.append(pq.poll()).append(" ");
		}
		System.out.println(count-1);
		System.out.println(sb.toString().trim());
		
	}
}
