import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main {
	static char[][] board;
	static int[][] visited;
	static int[] dx = new int[] { -1, 0, 1, 0 };
	static int[] dy = new int[] { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		board = new char[12][6];
		visited = new int[12][6];
		for (int i = 0; i < 12; i++) {
			String s = bf.readLine();
			for (int j = 0; j < 6; j++) {
				board[i][j] = s.charAt(j);
			}
		}
		boolean isScored = true;
		int score = 0;

		while (isScored) {
			isScored = false;
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (visited[i][j] == 0 && board[i][j] != '.') {
						bfs(i, j);
					}
				}
			}

			loop: for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (visited[i][j] == -1) {
						isScored = true;
						break loop;
					}
				}
			}
			
			
			if (isScored) {
				score++;
				updateBoard();
			}
		}

		System.out.println(score);
	}

	public static void bfs(int i, int j) {
		char selected_color = board[i][j];
		ArrayDeque<int[]> q = new ArrayDeque<>();
		ArrayList<int[]> list = new ArrayList<>();
		q.add(new int[] {i, j});
		list.add(new int[] {i, j});
		int cnt = 1;
		visited[i][j] = 1;
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			for (int dir = 0; dir < 4; dir++) {
				int x = temp[0] + dx[dir];
				int y = temp[1] + dy[dir];
				
				if (x < 0 || x >= 12 || y < 0 || y >= 6) {
					continue;
				}
				if (board[x][y] != selected_color) {
					continue;
				}
				if (visited[x][y] != 0) {
					continue;
				}
				visited[x][y] = 1;
				q.add(new int[] { x, y });
				list.add(new int[] { x, y });
				cnt++;
			}
		}

		if (cnt >= 4) {
			for (int pos = 0; pos < cnt; pos++) {
				visited[list.get(pos)[0]][list.get(pos)[1]] = -1;
			}
		}
		
//		for (int p = 0; p < 12; p++) {
//			for (int k = 0; k < 6; k++) {
//				System.out.print(visited[p][k] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println(" --- ");
	}

	public static void updateBoard() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				if (visited[i][j] == -1) {
					board[i][j] = '.';
				}
			}
		}

		for (int j = 0; j < 6; j++) {
			char[] temp = new char[12];
			for (int i = 0; i < 12; i++) {
				temp[i] = '.';
			}
			int idx = 11;
			for (int i = 11; i >= 0; i--) {
				if (board[i][j] != '.') {
					temp[idx] = board[i][j];
					idx--;
				}
			}
			for (int i = 0; i < 12; i++) {
				board[i][j] = temp[i];
			}
		}
		
		visited = new int[12][6];
	}
}
