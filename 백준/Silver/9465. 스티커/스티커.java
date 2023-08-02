import java.util.Scanner;

public class Main {
	// 바텀업도 반드시가야지~
	static int MAX = 100001;
	static int[][] arr = new int[2][MAX];
	static int[][] memo = new int[MAX][3];
	static int N;

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
			// initialize memo with 0
			for (int i = 0; i <= N; i++) {
				for (int j = 0; j < 3; j++) {
					memo[i][j] = 0;
				}
			}

			// memo(column, status);
			// c번째 열 까지 뗀 스티커의 가치의 합
			// status
			// 0 -> 전 열에서 스티커 안 뗐을 때
			// 1 -> 전 열에서 위쪽 스티커 떼어서, c번쩨 열에서 1번째 줄 쓸 수 없을 때
			// 2 -> 전 열에서 밑에쪽 스티커 떼어서, c번째 열에서 2번째 줄 쓸 수 없을 때
			// memo(n, status)의 최대값을 구하면 된다.
			for (int i = 0; i < N; i++) {
				memo[i + 1][0] = Math.max(memo[i + 1][0], Math.max(memo[i][0], Math.max(memo[i][1], memo[i][2])));
				memo[i + 1][1] = Math.max(memo[i + 1][1], Math.max(memo[i][0] + arr[0][i], memo[i][2] + arr[0][i]));
				memo[i + 1][2] = Math.max(memo[i + 1][2], Math.max(memo[i][0] + arr[1][i], memo[i][1] + arr[1][i]));
			}
			System.out.println(Math.max(memo[N][0], Math.max(memo[N][1], memo[N][2])));
		}
		sc.close();
	}

}