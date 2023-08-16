import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
	static int[] dx = new int[] { 1, 0, -1, 0 };
	static int[] dy = new int[] { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 0; tc < T; tc++) {
			// 선언부
			int M = sc.nextInt();
			int N = sc.nextInt();
			int K = sc.nextInt();
			int result = 0;
			int[][] arr = new int[N][M];
			int[][] visited = new int[N][M];
			ArrayDeque<int[]> q = new ArrayDeque<>();

			// input 받음
			for (int i = 0; i < K; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				arr[y][x] = 1;
			}

			// bfs
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == 1 && visited[i][j] == 0) {
						q.add(new int[] { i, j });
						visited[i][j] = 1;
						while (!q.isEmpty()) {
							int[] temp = q.pop();
							for (int dir = 0; dir < 4; dir++) {
								int x = temp[0] + dx[dir];
								int y = temp[1] + dy[dir];
								if (x < 0 || x >= N || y < 0 || y >= M) {
									continue;
								}
								if (visited[x][y] == 1 || arr[x][y] == 0) {
									continue;
								}
								visited[x][y] = 1;
								q.add(new int[] {x, y});
							}
						}
						result++;
					}
				}
			}
			System.out.println(result);
		}
	}
}