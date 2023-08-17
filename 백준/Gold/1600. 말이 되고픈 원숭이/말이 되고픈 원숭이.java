import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		// board 입력받는 부분
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s = bf.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int K = Integer.parseInt(st.nextToken());
		s = bf.readLine();
		st = new StringTokenizer(s);
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] board = new int[N][M];
		for (int i = 0; i < N; i++) {
			s = bf.readLine();
			st = new StringTokenizer(s);
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 선언부
		int[][][] dist = new int[K + 1][N][M]; // 0~k번 뛰어넘을 때마다 다른 배열에 저장
		ArrayDeque<int[]> q = new ArrayDeque<>();
		int[] dx = new int[] { 1, 0, -1, 0 };
		int[] dy = new int[] { 0, 1, 0, -1 };
		int[] hx = new int[] { 2, 1, -1, -2, -2, -1, 1, 2 };
		int[] hy = new int[] { 1, 2, 2, 1, -1, -2, -2, -1 };

		// dist -1로 초기화
		for (int i = 0; i < K + 1; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					dist[i][j][k] = -1;
				}
			}
		}
		if (N == 1 && M == 1) {
			System.out.println(0);
			return;
		}

		// bsf
		q.add(new int[] { 0, 0, 0 }); // k를 얼마나썼는지 저장
		dist[0][0][0] = 0;
		while (!q.isEmpty()) {
			int[] temp = q.pop();
			int distance = dist[temp[2]][temp[0]][temp[1]];
			// 4방향 탐색
			for (int dir = 0; dir < 4; dir++) {
				int x = temp[0] + dx[dir];
				int y = temp[1] + dy[dir];
				if (x == N - 1 && y == M - 1) { // 탈출조건
					System.out.println(distance + 1);
					return;
				}
				if (x < 0 || x >= N || y < 0 || y >= M) { // 범위 나갈때
					continue;
				}
				if (board[x][y] == 1 || dist[temp[2]][x][y] != -1) {
					// 벽이거나, 이미 해당 차원에서 방문한 적 있을 때
					continue;
				}
				dist[temp[2]][x][y] = distance + 1;
				q.add(new int[] { x, y, temp[2] });
			}

			// 나이트 탐색
			if (temp[2] < K) { // 아직 나이트 움직임을 쓸 수 있으면
				for (int dir = 0; dir < 8; dir++) {
					int x = temp[0] + hx[dir];
					int y = temp[1] + hy[dir];
					if (x == N - 1 && y == M - 1) { // 탈출조건
						System.out.println(distance + 1);
						return;
					}
					if (x < 0 || x >= N || y < 0 || y >= M) { // 범위 나갈때
						continue;
					}
					if (board[x][y] == 1 || dist[temp[2] + 1][x][y] != -1) {
						// 벽이거나, 이미 다음 차원에서 방문한 적 있을 때
						continue;
					}
					dist[temp[2] + 1][x][y] = distance + 1;
					q.add(new int[] { x, y, temp[2] + 1 });
				}
			}
		}
		System.out.println(-1);
		return;
	}
}