import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[][][] board = new int[5][5][5]; // 입력받을 보드
	static int[] dx = new int[] { 1, -1, 0, 0, 0, 0 }; // 3차원 bfs 위한 델타
	static int[] dy = new int[] { 0, 0, 1, -1, 0, 0 };
	static int[] dz = new int[] { 0, 0, 0, 0, 1, -1 };
	static int min_dist = Integer.MAX_VALUE; // 최종 결과
	static int[] v = new int[5]; // 순열 만들기 위한 visited 배열

	public static void main(String[] args) {
		// 입력부
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					board[i][j][k] = sc.nextInt();
				}
			}
		}

		permutation(0, new int[5]);

		System.out.println(min_dist == Integer.MAX_VALUE ? -1 : min_dist);
	}

	// 1. 먼저 가능한 판의 모든 순서 고려한다
	public static void permutation(int cnt, int[] result) { // 판의 순서 고려한다
		if (cnt == 5) {
			rotate(0, result, new int[5][5][5]);
			return;
		}

		for (int i = 0; i < 5; i++) {
			if (v[i] != 1) {
				v[i] = 1;
				result[cnt] = i;
				permutation(cnt + 1, result);
				v[i] = 0;
			}
		}

	}

	// 2. 해당 판의 순서에 맞게, 모든 판들을 4방향으로 회전시키는 경우의 수를 고려한다
	public static void rotate(int cnt, int[] result, int[][][] board_new) {
		if (cnt == 5) {
			// 시작점, 끝점이 접근 가능한 경우만 고려한다
			if (board_new[0][0][0] == 1 && board_new[4][4][4] == 1)
				bfs(board_new);
			return;
		}

		// base case
		int[][] temp = new int[5][5];
		for (int dir = 0; dir < 4; dir++) {
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					if (dir == 0) { // 그대로
						temp[j][k] = board[result[cnt]][j][k];
					} else if (dir == 1) { // 시계방향
						temp[j][k] = board[result[cnt]][4 - k][j];
					} else if (dir == 2) { // upside down
						temp[j][k] = board[result[cnt]][4 - j][4 - k];
					} else if (dir == 3) { // 반시계방향
						temp[j][k] = board[result[cnt]][k][4 - j];
					}
				}
			}
			board_new[cnt] = temp;
			rotate(cnt + 1, result, board_new);
		}
	}

	// 3. 각 경우에 대해 최종 목적지까지 갈 수 있는지 고려한다
	public static void bfs(int[][][] board_new) {
		// bfs (visited에 거리 저장한다)
		ArrayDeque<int[]> q = new ArrayDeque<>();
		int[][][] visited = new int[5][5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					visited[i][j][k] = -1;
				}
			}
		}

		q.add(new int[] { 0, 0, 0 });
		visited[0][0][0] = 0;

		// 3차원 bfs
		while (!q.isEmpty())

		{
			int[] temp = q.poll();
			int dist = visited[temp[0]][temp[1]][temp[2]];
			for (int dir = 0; dir < 6; dir++) {
				int z = temp[0] + dz[dir];
				int x = temp[1] + dx[dir];
				int y = temp[2] + dy[dir];

				if (x < 0 || x >= 5 || y < 0 || y >= 5 || z < 0 || z >= 5) {
					continue;
				}
				if (visited[z][x][y] != -1 || board_new[z][x][y] == 0) {
					continue;
				}
				if (x == 4 && y == 4 && z == 4) {
					min_dist = Math.min(min_dist, dist + 1);
					q.clear();
					break;
				}
				visited[z][x][y] = dist + 1;
				q.add(new int[] { z, x, y });
			}
		}
	}
}
