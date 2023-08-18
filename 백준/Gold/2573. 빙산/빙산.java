import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
	static int M;
	static int N;
	static int[] dx = new int[] { 1, 0, -1, 0 };
	static int[] dy = new int[] { 0, 1, 0, -1 };

	public static void main(String[] args) {
		// 입력부
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		int[][] board = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				board[i][j] = sc.nextInt();
			}
		}

		// 선언부
		ArrayDeque<int[]> q = new ArrayDeque<>();
		int time = 0; // 걸린시간 저장
		int sum = 1; // visited 배열 확인용
		int[][] visited; // 1. 녹는 정도 기록
		
		// 시작 전 빙하가 하나인지 체크
		if (isSeperated(board)) {
			System.out.println(0);
			return;
		}

		// 빙산이 2개로 쪼개지기 전까지, 시간이 다 되기 전까지 반복
		while (sum > 0) {
			visited = new int[N][M];
			// 0이 아닌 부분마다 4방향 체크
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (board[i][j] != 0) { // 0이 아닌 모든 노드에서
						for (int dir = 0; dir < 4; dir++) {
							int x = i + dx[dir];
							int y = j + dy[dir];
							if (x < 0 || x >= N || y < 0 || y >= M) {
								continue;
							}
							if (board[x][y] == 0) {
								visited[i][j]++;
							}
						}
						// 해당 노드 q에 저장
						q.add(new int[] { i, j });
					}
				}
			}

			// visited 배열 기반으로 board 업데이트
			while (!q.isEmpty()) {
				int[] temp = q.pop();
				int x = temp[0];
				int y = temp[1];
				if (board[x][y] < visited[x][y]) {
					board[x][y] = 0;
					continue;
				}
				board[x][y] -= visited[x][y];
			}

			// 2개인지 여부 체크
			time++;
			if (isSeperated(board)) {
				System.out.println(time);
				return;
			}
			
			// visited 배열 확인하여 비지 않았는지 체크
			sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					sum += visited[i][j];
				}
			}

		}
		System.out.println(0);
	}

	public static boolean isSeperated(int[][] board) {
		int[][] visited = new int[N][M];
		int rep = 0;
		ArrayDeque<int[]> q = new ArrayDeque<>();
		
		// bsf
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] != 0 && visited[i][j] == 0) {
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
							if (visited[x][y] == 1 || board[x][y] == 0) {
								continue;
							}
							visited[x][y] = 1;
							q.add(new int[] { x, y });
						}
					}
					// 1번의 bsf 끝날때마다 rep증가
					rep++;
				}
			}
		}
		// 2개 이상으로 나누어져 있으면 true
		if (rep > 1) {
			return true;
		}
		return false;
	}
}
