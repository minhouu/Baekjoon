import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		int[][] board = new int[N][M];
		int[] dx = new int[] { -1, 0, 1, 0 };
		int[] dy = new int[] { 0, 1, 0, -1 };
		int result = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				board[i][j] = sc.nextInt();
			}
		}

		int[] pos = new int[] { r, c, d };
		loop: while (true) {
			if (board[pos[0]][pos[1]] == 0) {
				board[pos[0]][pos[1]] = -1;
				result++;
			}
			// 기존 방향(d) 기준으로 반시계방향 탐색
			for (int dir = pos[2] + 3; dir >= pos[2]; dir--) {
				int x = pos[0] + dx[dir % 4];
				int y = pos[1] + dy[dir % 4];
				if (x < 0 || x >= N || y < 0 || y >= M) {
					continue;
				}
				if (board[x][y] == 0) {
					pos = new int[] { x, y, dir };
					continue loop;
				}
			}

			// 움직일 수 없을 때
			int backDir = (pos[2] + 2) % 4;
			int x = pos[0] + dx[backDir];
			int y = pos[1] + dy[backDir];
			if (board[x][y] == 1) {
				break;
			}
			pos = new int[] { x, y, pos[2] };
		}
		System.out.println(result);
	}
}
