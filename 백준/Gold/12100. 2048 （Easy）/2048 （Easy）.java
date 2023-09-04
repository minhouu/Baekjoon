import java.util.Scanner;

public class Main {
	static int N;
	static int[][] board;
	static int result = 2;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = sc.nextInt();
			}
		}

		permutation(0, new int[5]);

		System.out.println(result);
	}

	public static void permutation(int cnt, int[] dir) {
		if (cnt >= 5) {
			// 보드 복사
			int[][] temp_board = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					temp_board[i][j] = board[i][j];
				}
			}

			// 이동 실행
			for (int i = 0; i < 5; i++) {
				move(temp_board, dir[i]);
			}

			// 최대값 구하기
			int max = 2;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (temp_board[i][j] > max) {
						max = temp_board[i][j];
					}
				}
			}
			result = Math.max(result, max);
			return;
		}

		for (int i = 1; i <= 4; i++) {
			dir[cnt] = i;
			permutation(cnt + 1, dir);
		}
	}

	public static void move(int[][] temp_board, int dir) {
		// 위쪽 방향
		if (dir == 1) {
			for (int j = 0; j < N; j++) {
				// temp 배열에 해당 방향으로 진행시킨 결과를 넣넌다
				int[] temp = new int[N];
				int idx = 0;
				int prev = -1;
				for (int i = 0; i < N; i++) {
					if (temp_board[i][j] == 0) {
						continue;
					}
					if (temp_board[i][j] == prev) {
						prev = -1;
						temp[idx - 1] = temp[idx - 1] * 2;
						continue;
					}
					prev = temp_board[i][j];
					temp[idx] = temp_board[i][j];
					idx++;
				}

				// temp 배열의 결과 temp_board에 업데이트
				for (int i = 0; i < N; i++) {
					temp_board[i][j] = temp[i];
				}
			}
			return;
		}

		else if (dir == 2) {
			for (int i = 0; i < N; i++) {
				// temp 배열에 해당 방향으로 진행시킨 결과를 넣넌다
				int[] temp = new int[N];
				int idx = N - 1;
				int prev = -1;
				for (int j = N - 1; j >= 0; j--) {
					if (temp_board[i][j] == 0) {
						continue;
					}
					if (temp_board[i][j] == prev) {
						prev = -1;
						temp[idx + 1] = temp[idx + 1] * 2;
						continue;
					}
					prev = temp_board[i][j];
					temp[idx] = temp_board[i][j];
					idx--;

				}

				// temp 배열의 결과 temp_board에 업데이트
				for (int j = 0; j < N; j++) {
					temp_board[i][j] = temp[j];
				}
			}
			return;
		}

		else if (dir == 3) {
			for (int j = N - 1; j >= 0; j--) {
				// temp 배열에 해당 방향으로 진행시킨 결과를 넣넌다
				int[] temp = new int[N];
				int idx = N - 1;
				int prev = -1;
				for (int i = N - 1; i >= 0; i--) {
					if (temp_board[i][j] == 0) {
						continue;
					}
					if (temp_board[i][j] == prev) {
						prev = -1;
						temp[idx + 1] = temp[idx + 1] * 2;
						continue;
					}
					prev = temp_board[i][j];
					temp[idx] = temp_board[i][j];
					idx--;
				}

				// temp 배열의 결과 temp_board에 업데이트
				for (int i = 0; i < N; i++) {
					temp_board[i][j] = temp[i];
				}

			}
			return;
		}

		else if (dir == 4) {
			for (int i = 0; i < N; i++) {
				// temp 배열에 해당 방향으로 진행시킨 결과를 넣넌다
				int[] temp = new int[N];
				int idx = 0;
				int prev = -1;
				for (int j = 0; j < N; j++) {
					if (temp_board[i][j] == 0) {
						continue;
					}
					if (temp_board[i][j] == prev) {
						prev = -1;
						temp[idx - 1] = temp[idx - 1] * 2;
						continue;
					}
					prev = temp_board[i][j];
					temp[idx] = temp_board[i][j];
					idx++;

				}

				// temp 배열의 결과 temp_board에 업데이트
				for (int j = 0; j < N; j++) {
					temp_board[i][j] = temp[j];
				}
			}
			return;
		}
	}
}
