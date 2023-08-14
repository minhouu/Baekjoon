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
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] board = new int[N][M];
		int[][] visited = new int[N][M];
		int[] dx = new int[] { 1, 0, -1, 0 };
		int[] dy = new int[] { 0, 1, 0, -1 };
		ArrayDeque<int[]> q = new ArrayDeque<>();
		int dist = Integer.MAX_VALUE;

		// get input
		for (int i = 0; i < N; i++) {
			s = bf.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = s.charAt(j) - '0';
			}
		}

		// bsf
		q.add(new int[] { 0, 0, 1 }); // temp[2]는 시작점부터의 거리 저장한다
		visited[0][0] = 1;
		while (!q.isEmpty()) {
			int[] temp = q.pop();

			if (temp[0] == N - 1 && temp[1] == M - 1) { // 만약 탐색 중 끝에 도달하면
				dist = Math.min(dist, temp[2]); // dist값과 비교해서 최소값을 저장한다
				continue;
			}
			for (int dir = 0; dir < 4; dir++) { // bsf
				int x = temp[0] + dx[dir];
				int y = temp[1] + dy[dir];
				if (x < 0 || x >= N || y < 0 || y >= M)
					continue;
				if (visited[x][y] == 1 || board[x][y] == 0)
					continue;
				visited[x][y] = 1;
				q.add(new int[] { x, y, temp[2] + 1 });
			}
		}
		System.out.println(dist);

	}

}