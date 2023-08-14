import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// declaration
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] board = new int[N][M];
		int[][] visited = new int[N][M];
		int[] dx = new int[] { 1, 0, -1, 0 };
		int[] dy = new int[] { 0, 1, 0, -1 };
		ArrayDeque<int[]> q = new ArrayDeque<>();
		int MAX = 0;
		int COUNT = 0;

		// get input
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				board[i][j] = sc.nextInt();
			}
		}

		// search 2-dimension array
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 1 && visited[i][j] == 0) { // 그림 있고, 방문하지 않았으면 bsf
					visited[i][j] = 1;
					int size = 0;
					q.add(new int[] { i, j });
					while (!q.isEmpty()) {
						int[] std = q.pop();
						size++;
						for (int dir = 0; dir < 4; dir++) {
							int x = std[0] + dx[dir];
							int y = std[1] + dy[dir];
							if (x < 0 || x >= N || y < 0 || y >= M)
								continue;
							if (visited[x][y] == 1 || board[x][y] == 0)
								continue;
							visited[x][y] = 1;
							q.add(new int[] { x, y });
						}
					}
					MAX = Math.max(MAX, size);
					COUNT++;
				}
			}
		}
		System.out.println(COUNT);
		System.out.println(MAX);
	}
}