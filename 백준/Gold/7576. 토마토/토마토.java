import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		// declaration
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s = bf.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] board = new int[N][M];
		int[] dx = new int[] { 1, 0, -1, 0 };
		int[] dy = new int[] { 0, 1, 0, -1 };
		ArrayDeque<int[]> q = new ArrayDeque<>();
		int result = 0;

		// get input
		for (int i = 0; i < N; i++) {
			s = bf.readLine();
			st = new StringTokenizer(s);
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 1) { // 만약 익은 토마토면 queue에 저장
					q.add(new int[] { i, j, 0 }); // 3번째 elememt는 날짜를 저장
				}
			}
		}

		// bsf
		while (!q.isEmpty()) {
			int[] temp = q.pop();
			for (int dir = 0; dir < 4; dir++) {
				int x = temp[0] + dx[dir];
				int y = temp[1] + dy[dir];
				if (x < 0 || x >= N || y < 0 || y >= M)
					continue;
				if (board[x][y] != 0)
					continue;
				board[x][y] = 1;
				q.add(new int[] { x, y, temp[2] + 1 });
			}
			result = Math.max(result, temp[2]);
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 0) {
					System.out.println(-1);
					return;
				}
			}
		}

		System.out.println(result);

	}
}