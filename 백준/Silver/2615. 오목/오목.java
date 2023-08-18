import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		int[][] board = new int[19][19];
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				board[i][j] = sc.nextInt();
			}
		}

		// 선언부
		int[][] visited = new int[19][19];
		// 각각 상하, 좌우, 좌상향대각선, 우상향대각선 체크하는 방향
		int[][] dx = new int[][] { { 1, -1 }, { 0, 0 }, { 1, -1 }, { -1, 1 } };
		int[][] dy = new int[][] { { 0, 0 }, { 1, -1 }, { 1, -1 }, { 1, -1 } };
		ArrayDeque<int[]> q = new ArrayDeque<>();
		int cnt = 0;

		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (board[i][j] != 0) { // 0이 아닌 요소에 대하여 4방향 길이검사
					visited = new int[19][19];
					visited[i][j] = 1;
					// 4방향으로 bsf
					for (int dir = 0; dir < 4; dir++) {
						cnt = 0; // 길이 세는 변수 (q에서 몇 번 pop이 되는지로 길이 센다)
						q.add(new int[] { i, j });
						while (!q.isEmpty()) {
							int[] temp = q.pop();
							cnt++;
							for (int k = 0; k < 2; k++) {
								int x = temp[0] + dx[dir][k];
								int y = temp[1] + dy[dir][k];
								if (x < 0 || x >= 19 || y < 0 || y >= 19) { // 범위 벗어난 경우
									continue;
								}
								if (visited[x][y] == 1 || board[x][y] != board[i][j]) { // 이미 방문했거나, 숫자가 다른 경우
									continue;
								}
								visited[x][y] = 1;
								q.add(new int[] { x, y });
							}
						}

						// 만약 길이가 5면, 프로그램 종료
						if (cnt == 5) {
							System.out.println(board[i][j]);
							if (dir == 3) { // 우상의 경우 board[i][j]가 맨 오른쪽 점이므로, 맨 왼쪽점으로 옮겨서 출력
								System.out.println((i + 4 + 1) + " " + (j - 4 + 1));
								return;
							}
							System.out.println((i + 1) + " " + (j + 1));
							return;
						}
					}
				}
			}
		}
		// 오목이 없으면
		System.out.println(0);
	}
}