import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	public static void main(String[] args) throws IOException {
		// 선언부
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		char[][] board = new char[N][N]; // input 받는 borad
		int[][] visited = new int[N][N]; // 방문여부 기록
		int[] dx = new int[] { 1, 0, -1, 0 }; // 탐색방향 저장용 배열
		int[] dy = new int[] { 0, 1, 0, -1 };
		ArrayDeque<int[]> q = new ArrayDeque<>();
		int result1 = 0; // non색약 결과
		int result2 = 0; // 색약결과

		// input
		for (int i = 0; i < N; i++) {
			String temp = bf.readLine();
			for (int j = 0; j < N; j++) {
				board[i][j] = temp.charAt(j);
			}
		}

		// non색약 bsf
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == 0) { // 방문하지 않았던 곳이면
					q.add(new int[] { i, j }); // queue에 추가
					visited[i][j] = 1;
					while (!q.isEmpty()) {
						int[] temp = q.pop();
						for (int dir = 0; dir < 4; dir++) { // 상하좌우 dx, dy 이용해서 체크
							int x = temp[0] + dx[dir];
							int y = temp[1] + dy[dir];
							if (x < 0 || x >= N || y < 0 || y >= N) { // x, y가 인덱스 벗어나면 넘어감
								continue;
							}
							if (visited[x][y] == 1 || board[x][y] != board[temp[0]][temp[1]]) {
								// 이미 방문했던 노드거나, 현재 체크하고 있는 색깔과 다르면 넘어간다
								continue;
							}
							visited[x][y] = 1;
							q.add(new int[] { x, y });
						}
					}
					result1++;
				}
			}
		}

		// visited 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visited[i][j] = 0;
			}
		}

		// board에서 초록색을 다 빨간색으로 바꿔버린다
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 'G') {
					board[i][j] = 'R';
				}
			}
		}

		// 색약 bsf
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == 0) { // 방문하지 않았던 곳이면
					q.add(new int[] { i, j }); // queue에 추가
					visited[i][j] = 1;
					while (!q.isEmpty()) {
						int[] temp = q.pop();
						for (int dir = 0; dir < 4; dir++) { // 상하좌우 dx, dy 이용해서 체크
							int x = temp[0] + dx[dir];
							int y = temp[1] + dy[dir];
							if (x < 0 || x >= N || y < 0 || y >= N) { // x, y가 인덱스 벗어나면 넘어감
								continue;
							}
							if (visited[x][y] == 1 || board[x][y] != board[temp[0]][temp[1]]) {
								// 이미 방문했던 노드거나, 현재 체크하고 있는 색깔과 다르면 넘어간다
								continue;
							}
							visited[x][y] = 1;
							q.add(new int[] { x, y });
						}
					}
					result2++;
				}
			}
		}

		System.out.println(result1);
		System.out.println(result2);

	}
}