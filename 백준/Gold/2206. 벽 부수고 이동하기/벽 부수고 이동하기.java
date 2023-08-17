import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		// 선언부
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s = bf.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] board = new int[N][M];
		int[][][] dist = new int[2][N][M];
		int[] dx = new int[] { 1, 0, -1, 0 };
		int[] dy = new int[] { 0, 1, 0, -1 };
		ArrayDeque<int[]> q = new ArrayDeque<>();
		boolean isPossible = false;

		for (int i = 0; i < N; i++) {
			s = bf.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = s.charAt(j) - '0';
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dist[0][i][j] = -1;
				dist[1][i][j] = -1;
			}
		}
		
		if (N == 1 && M == 1) {
			System.out.println(1);
			return;
		}

		// bsf
		q.add(new int[] { 0, 0, 0 }); // 3번째 요소 : 벽 부수기 썼는지 체크하는 변수
		dist[0][0][0] = 1;
		while (!q.isEmpty() && !isPossible) {
			int[] temp = q.pop();
			for (int dir = 0; dir < 4; dir++) {
				int x = temp[0] + dx[dir];
				int y = temp[1] + dy[dir];
				if (x == N - 1 && y == M - 1) {
					System.out.println(dist[temp[2]][temp[0]][temp[1]] + 1);
					return;
				}
				if (x < 0 || x >= N || y < 0 || y >= M) { // 범위 밖으로 나가면 continue
					continue;
				}
				
				if (board[x][y] == 1 && temp[2] == 0 && dist[1][x][y] == -1) {
					// (x,y)가 벽이고, 아직 벽을 부수지 않았으며, 벽을 부순 후를 체크하는 배열에서 아직 방문하지 않았으면
					q.add(new int[] {x, y, 1});
					dist[1][x][y] = dist[0][temp[0]][temp[1]] + 1;
				}
				else if (board[x][y] == 0 && dist[temp[2]][x][y] == -1) {
					// (x, y)가 벽이 아니면, (벽을 부순 후든 아니든) 각자 기록하던 배열에서 쭉 dist를 기록해나가고 q에 추가한다.
					q.add(new int[] {x, y, temp[2]});
					dist[temp[2]][x][y] = dist[temp[2]][temp[0]][temp[1]] + 1;
				}
//				System.out.println("벽부수기전 dist");
//				for (int i = 0; i < N; i++) {
//					for (int j = 0; j < M; j++) {
//						System.out.print(dist[0][i][j] + " ");
//					}
//					System.out.println();
//				}
//				System.out.println("벽부수고나서 dist");
//				for (int i = 0; i < N; i++) {
//					for (int j = 0; j < M; j++) {
//						System.out.print(dist[1][i][j] + " ");
//					}
//					System.out.println();
//				}
			}
		}
		System.out.println(-1);
		return;
	}
}