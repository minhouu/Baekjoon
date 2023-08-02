import java.util.Scanner;

public class Main {
	static int[] arr = new int[101]; // 동전들 저장
	static int[][] memo = new int[101][10001]; // n, k쌍 저장
	static int IMPOSSIBLE = 20000;
	static int K;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= K; j++) {
				memo[i][j] = -1;
			}
		}
		
		int result = dp(0, K);
		if (result >= IMPOSSIBLE) {
			System.out.print(-1);
		}
		else {
			System.out.println(result);
		}
		sc.close();
	}

	public static int dp(int n, int k) {
		if (n == N) {
			return (k == 0 ? 0 : IMPOSSIBLE);
		}
		if (memo[n][k] != -1) {
			return memo[n][k];
		}
		int result = dp(n + 1, k);
		if (k >= arr[n])
			result = Math.min(result, dp(n, k - arr[n]) + 1);
		memo[n][k] = result;
		return result;
	}
	

}