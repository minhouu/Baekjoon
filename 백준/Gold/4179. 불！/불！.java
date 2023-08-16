import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s = bf.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[][] board = new int[R][C]; // fire의 시간당 위치 저장
		int[][] visited = new int[R][C]; // 방문확인
		int[][] dist = new int[R][C]; // 지훈이 위치
		int[] J = new int[3]; // 지훈이 위치 저장
		int[] dx = new int[] { 1, 0, -1, 0 };
		int[] dy = new int[] { 0, 1, 0, -1 };
		ArrayDeque<int[]> q = new ArrayDeque<>();

		// get input
		for (int i = 0; i < R; i++) {
			s = bf.readLine();
			for (int j = 0; j < C; j++) {
				char temp = s.charAt(j);
				if (temp == '#') {
					board[i][j] = -1;
					dist[i][j] = -1;
					continue;
				}
				if (temp == 'F') {
					q.add(new int[] { i, j, 0 });
					// 불의 위치를 q에 더한다(지훈이 빼고 먼저 bsf통해 불의 시간당 위치를 구할 것)
					dist[i][j] = -1; // 불이 시작되는 위치는 지훈이가 못가는 위치기때문에 -1
					continue;
				}
				if (temp == 'J') {
					J = new int[] { i, j, 0 };
					// 지훈이 위치를 따로 저장해둔다 (J)
					continue;
				}
			}
		}

		// 불 bsf
		// visited 시작점에서 1로 바꿔줌
		if (!q.isEmpty()) {
			int[] init = q.peek();
			visited[init[0]][init[1]] = 1;
		}

		while (!q.isEmpty()) {
			int[] temp = q.pop();
			board[temp[0]][temp[1]] = temp[2];
			for (int dir = 0; dir < 4; dir++) {
				int x = temp[0] + dx[dir];
				int y = temp[1] + dy[dir];
				if (x < 0 || x >= R || y < 0 || y >= C)
					continue;
				if (board[x][y] != 0 || visited[x][y] != 0)
					continue;
				visited[x][y] = 1;
				q.add(new int[] { x, y, temp[2] + 1 });
			}
		}

//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				System.out.print(board[i][j] + " ");
//			}
//			System.out.println();
//		}
//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				System.out.print(visited[i][j] + " ");
//			}
//			System.out.println();
//		}
//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				System.out.print(dist[i][j] + " ");
//			}
//			System.out.println();
//		}

		// 지훈이 bsf 위해서 visited 초기화
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				visited[i][j] = 0;
			}
		}

		// 지훈이 bsf
		q.add(J);
		if (!q.isEmpty()) {
			int[] init = q.peek();
			visited[init[0]][init[1]] = 1;
		}

		while (!q.isEmpty()) {
			int[] temp = q.pop();
			dist[temp[0]][temp[1]] = temp[2];
			// 탈출조건 체크
			if (temp[0] == 0 || temp[0] == R - 1 || temp[1] == 0 || temp[1] == C - 1) {
				System.out.println(temp[2] + 1);
				return;
			}
			for (int dir = 0; dir < 4; dir++) {
				int x = temp[0] + dx[dir];
				int y = temp[1] + dy[dir];
				if (x < 0 || x >= R || y < 0 || y >= C) {
					continue;
				}

				if (visited[x][y] == 1 || board[x][y] == -1 || dist[x][y] == -1) {
					continue;
				}
				if (board[x][y] != 0) {
					if (board[x][y] <= temp[2] + 1) {
						continue;
					}
				}
				visited[x][y] = 1;
				q.add(new int[] { x, y, temp[2] + 1 });
			}
		}
		System.out.println("IMPOSSIBLE");
	}
}