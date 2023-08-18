import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// 입력부
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] board = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = sc.nextInt();
			}
		}

		ArrayDeque<int[]> q = new ArrayDeque<>();
		int[] dx = new int[] { 1, 0, -1, 0 };
		int[] dy = new int[] { 0, 1, 0, -1 };
		int[][] visited = new int[N][N];
		int color = 1; // 대륙간 색깔 저장
		int min_length = Integer.MAX_VALUE; // 결과값

		// 1. 각 대륙을 다른 색으로 칠한다
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 각 대륙에 대해 bfs하면서 색을 다르게 칠한다(1, 2, 3, ...)
				if (board[i][j] != 0 && visited[i][j] == 0) {
					q.add(new int[] { i, j });
					board[i][j] = color;
					visited[i][j] = 1;
					while (!q.isEmpty()) {
						int[] temp = q.pop();
						for (int dir = 0; dir < 4; dir++) {
							int x = temp[0] + dx[dir];
							int y = temp[1] + dy[dir];
							if (x < 0 || x >= N || y < 0 || y >= N) {
								continue;
							}
							if (visited[x][y] == 1 || board[x][y] == 0) {
								continue;
							}
							q.add(new int[] { x, y });
							visited[x][y] = 1;
							board[x][y] = color;
						}
					}
					// 한 대륙 색칠이 끝나면 color를 바꿔준다(+1)
					color++;
				}

			}
		}
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(board[i][j] + " ");
//			}
//			System.out.println();
//		}

		// 2. 각 대륙의 칸들에서 0과 접한 것 찾고, 해당 칸에서 다른 섬으로 갈 수 있는 가장 짧은 다리를 찾는다
		for (int c = 1; c <= color; c++) { // 각 컬러마다 순회
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == c) {
						// 0과 접한 곳이 있는지 체크
						boolean isShore = false;
						for (int dir = 0; dir < 4; dir++) {
							int x = i + dx[dir];
							int y = j + dy[dir];
							if (x < 0 || x >= N || y < 0 || y >= N) {
								continue;
							}
							if (board[x][y] == 0) {
								isShore = true;
								break;
							}
						}

						// 해당 점이 끝 점일 때, 다른 섬으로 가는 다리의 최단거리 찾는 bfs 실행한다
						if (isShore) {

							// visited -1 로 초기화. 거리 저장하는 배열로 사용한다
							visited = new int[N][N];
							for (int a = 0; a < N; a++) {
								for (int b = 0; b < N; b++) {
									visited[a][b] = -1;
								}
							}
							
							// bfs
							q.add(new int[] { i, j });
							visited[i][j] = 0;
							while (!q.isEmpty()) {
								int[] temp = q.pop();
								int distance = visited[temp[0]][temp[1]];
								for (int dir = 0; dir < 4; dir++) {
									int x = temp[0] + dx[dir];
									int y = temp[1] + dy[dir];
									if (x < 0 || x >= N || y < 0 || y >= N) {
										continue;
									}
									// 탈출조건 : 다른 color 도착하면 멈춘다
									if (board[x][y] != c && board[x][y] != 0) {
										min_length = Math.min(min_length, distance);
										q.clear();
										break;
									}
									
									// 이미 방문한 곳이거나, 나와 색깔이 같은 곳이면 continue
									if (visited[x][y] != -1 || board[x][y] == c) {
										continue;
									}
									q.add(new int[] {x, y});
									visited[x][y] = distance + 1;
								}
							}
						}
					}
				}
			}
		}
		System.out.println(min_length);
	}
}