import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s = bf.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] start = new int[2];
		int[] end = new int[2];
		int[] dx = new int[] { -1, 0, 1, 0 };
		int[] dy = new int[] { 0, 1, 0, -1 };

		// 2차원 배열 형태 입력받음
		char[][] board = new char[N][M];
		for (int i = 0; i < N; i++) {
			s = bf.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = s.charAt(j);
				if (board[i][j] == '0') {
					start[0] = i;
					start[1] = j;
				}
				if (board[i][j] == '1') {
					end[0] = i;
					end[1] = j;
				}
			}
		}

		// 3차원 배열로 visited 선언
		int[][][] visited = new int[64][N][M];
		for (int i = 0; i < 64; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					visited[i][j][k] = -1;
				}
			}
		}

		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.add(new int[] { start[0], start[1], 0 });
		visited[0][start[0]][start[1]] = 0;
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int dist = visited[temp[2]][temp[0]][temp[1]];
			int dec = temp[2];
			int[] bitmask = new int[6];
			for (int i = 5; i >= 0; i--) {
				bitmask[i] = dec & 1;
				dec >>= 1;
			}

			for (int dir = 0; dir < 4; dir++) {
				int x = temp[0] + dx[dir];
				int y = temp[1] + dy[dir];
				// 인덱스 벗어날 때
				if (x < 0 || x >= N || y < 0 || y >= M) {
					continue;
				}

				// 이미 해당 차원에서 방문했을 때 혹은 벽일 때
				if (visited[temp[2]][x][y] != -1 || board[x][y] == '#') {
					continue;
				}

				// 탈출조건
				if (board[x][y] == '1') {
					System.out.println(dist + 1);
					return;
				}

				// 나머지 경우
				int toDec = 0;
				for (int i = 5, bi = 1; i >= 0; i--) {
					if (bitmask[i] == 1) {
						toDec += bi;
					}
					bi <<= 1;
				}

				// 만약 열쇠를 줍는 상황이면
				if (board[x][y] >= 'a' && board[x][y] <= 'f') {
					int key = board[x][y] - 'a';
					// 해당 열쇠가 없는 상황일 때 bitmask의 10진수 값인 toDec에 반영
					if (bitmask[key] == 0) {
						int bit = 64 >> (key + 1);
						toDec += bit;
					}
				}

				// 문을 열어야 하는 상황이면
				if (board[x][y] >= 'A' && board[x][y] <= 'F') {
					// 키가 없으면 continue;
					if (bitmask[board[x][y] - 'A'] != 1) {
						continue;
					}
				}

				// q에 넣어서 bfs
				q.add(new int[] { x, y, toDec });
				visited[toDec][x][y] = dist + 1;
			}
		}

		System.out.println(-1);
	}
}
