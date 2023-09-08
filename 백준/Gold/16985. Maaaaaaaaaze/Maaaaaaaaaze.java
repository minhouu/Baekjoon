import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[][][] board = new int[5][5][5];
	static int[] dx = new int[] { 1, -1, 0, 0, 0, 0 };
	static int[] dy = new int[] { 0, 0, 1, -1, 0, 0 };
	static int[] dz = new int[] { 0, 0, 0, 0, 1, -1 };
	static int min_dist = Integer.MAX_VALUE;
	static int[] v = new int[5];

	public static void main(String[] args) {
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

	public static void permutation(int cnt, int[] result) {
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

	public static void rotate(int cnt, int[] result, int[][][] board_new) {
		if (cnt == 5) {
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
					} else if (dir == 1) {
						temp[j][k] = board[result[cnt]][4 - k][j];
					} else if (dir == 2) {
						temp[j][k] = board[result[cnt]][4 - j][4 - k];
					} else if (dir == 3) {
						temp[j][k] = board[result[cnt]][k][4 - j];
					}
				}
			}
			board_new[cnt] = temp;
			rotate(cnt + 1, result, board_new);
		}
	}

	public static void bfs(int[][][] board_new) {
		// bfs
		ArrayDeque<int[]> q = new ArrayDeque<>();
		int[][][] visited = new int[5][5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					visited[i][j][k] = -1;
				}
			}
		}

		if (board_new[0][0][0] == 1) {
			q.add(new int[] { 0, 0, 0 });
			visited[0][0][0] = 0;
		}
		while (!q.isEmpty()) {
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
