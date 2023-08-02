import java.util.Scanner;

public class Main {
	// declare static variables
	static int MAX = 100000;
	static int[][] arr = new int[2][MAX];
	static int[][] memo = new int[MAX][3];
	static int N;
	
	public static int dp(int c, int s) {
		// base case
		if (c == N) {
			return 0;
		}
		if (memo[c][s] != -1) {
			return memo[c][s];
		}
		
		//recursive case
		if (s == 0) {
			int result = dp(c + 1, 0);
			result = Math.max(result, Math.max(dp(c + 1, 1) + arr[0][c], dp(c + 1, 2) + arr[1][c]));
			memo[c][s] = result;
		}
		if (s == 1) {
			int result = Math.max(dp(c + 1, 0), dp(c + 1, 2) + arr[1][c]);
			memo[c][s] = result;

		}
		if (s == 2) {
			int result = Math.max(dp(c + 1, 0), dp(c + 1, 1) + arr[0][c]);
			memo[c][s] = result;

		}
		return memo[c][s];
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			N = sc.nextInt();
			// takes input
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			// initialize memo with -1
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < 3; j++) {
					memo[i][j] = -1;
				}
			}

			// dp(column, status);
			// c번째 열 까지 뗀 스티커의 가치의 합
			// status
			// 0 -> 전 열에서 스티커 안 뗐을 때
			// 1 -> 전 열에서 위쪽 스티커 떼어서, c번쩨 열에서 1번째 줄 쓸 수 없을 때
			// 2 -> 전 열에서 밑에쪽 스티커 떼어서, c번째 열에서 2번째 줄 쓸 수 없을 때
			// dp(n, status)의 최대값을 구하면 된다.

			System.out.println(dp(0, 0));
		}
		sc.close();
	}
}