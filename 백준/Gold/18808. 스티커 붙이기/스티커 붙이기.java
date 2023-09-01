import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int K;
	static int[][] board;
	static int[][][] stickers;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		board = new int[N][M];
		stickers = new int[K][][];
		for (int i = 0; i < K; i++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			stickers[i] = new int[n][m];
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < m; c++) {
					stickers[i][r][c] = sc.nextInt();
				}
			}
		}

		for (int st = 0; st < K; st++) { // 모든 스티커에 대하여
			boolean isPossible = false;
			for (int dir = 0; dir < 4; dir++) { // 4개의 회전 방향에 대해
				for (int i = 0; i < N; i++) { // 모든 좌표에서
					for (int j = 0; j < M; j++) {
						// 각 좌표에서 스티커를 놓을 수 있는지 체크
						isPossible = check(new int[] { i, j }, stickers[st], dir);

						// 만약 해당 위치에 스티커를 놓을 수 있다면 board에 해당 스티커를 표시한다
						if (isPossible) {
							break;
						}
					}
					if (isPossible)
						break;
				}
				if (isPossible)
					break;
			}
		}

		// 스티커가 붙은 칸의 수를 센다
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 1) {
					sum++;
				}
			}
		}
		System.out.println(sum);

	}

	public static boolean check(int[] head, int[][] sticker, int dir) {
		// head : 체크할 board의 왼쪽 가장 위 좌표
		// sticker : 스티커 형태의 2차원배열
		// dir : 방향

		// 1. 스티커의 dir(방향)에 따라 적절하게 temp_sticker 배열의 형태를 결정한다.
		int[][] temp_sticker = sticker;

		if (dir != 0) {
			if (dir == 1) {
				temp_sticker = new int[sticker[0].length][sticker.length];
				for (int i = 0; i < sticker[0].length; i++) {
					for (int j = 0; j < sticker.length; j++) {
						temp_sticker[i][j] = sticker[sticker.length - j - 1][i];
					}
				}
			}

			else if (dir == 2) {
				temp_sticker = new int[sticker.length][sticker[0].length];
				for (int i = 0; i < sticker.length; i++) {
					for (int j = 0; j < sticker[0].length; j++) {
						temp_sticker[i][j] = sticker[sticker.length - 1 - i][sticker[0].length - 1 - j];
					}
				}
			}

			else if (dir == 3) {
				temp_sticker = new int[sticker[0].length][sticker.length];
				for (int i = 0; i < sticker[0].length; i++) {
					for (int j = 0; j < sticker.length; j++) {
						temp_sticker[i][j] = sticker[j][sticker[0].length - 1 - i];
					}
				}
			}
		}
		
		
		
		// 스티커가 board의 경계를 넘어가면 무시
		if (head[0] + temp_sticker.length > N || head[1] + temp_sticker[0].length > M) {
			return false;
		}
			

		// 2. head의 위치부터 시작하여, 해당 위치에 sticker가 들어갈 수 있는지 확인한다.
		for (int i = head[0], r = 0; i < head[0] + temp_sticker.length; i++, r++) {
			for (int j = head[1], c = 0; j < head[1] + temp_sticker[0].length; j++, c++) {
				if (temp_sticker[r][c] == 1) {
					if (board[i][j] != 0) {
						return false;
					}
				}
			}
		}
		
		// 3. board에 칠한다
		for (int i = head[0], r = 0; i < head[0] + temp_sticker.length; i++, r++) {
			for (int j = head[1], c = 0; j < head[1] + temp_sticker[0].length; j++, c++) {
				if (temp_sticker[r][c] == 1) {
					board[i][j] = 1; 
 				}
			}
		}
		return true;
	}
}
