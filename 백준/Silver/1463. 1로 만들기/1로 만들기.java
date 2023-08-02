import java.util.Scanner;

// top down
public class Main {
	static int[] memo = new int[1000001];

	public static void main(String[] args) {
		// 점화식 (f = n을 만드는 최소 횟수)
		// f(n) min {
		// 0 (n == 1)
		// f(n-1) + 1
		// f(n/2) + 1 (N == 2n)
		// f(n/3) + 1 (N = 3n)}

		// f(n-1)보다, f(n/2)보다, f(n/3)보다 1번만 더 연산하면 f(n)이 된다는 의미
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		System.out.print(toOne(N));
		sc.close();
		return;
	}

	public static int toOne(int n) {
		// n이 1이라면
		if (n == 1)
			return 0;
		// toOne(n)이 이미 계산되어 있다면
		if (memo[n] != 0)
			return memo[n];
		
		// toOne(n) 계산하기
		// result는 toOne(n-1) + 1, toOne(n/2) + 1, toOne(n/3) + 1 중에 최소값이다
		if (n%6 == 0) {
			memo[n] = Math.min(toOne(n / 3) + 1, toOne(n/2) + 1);
		}
		
		else if (n % 3 == 0) {
			memo[n] = Math.min(toOne(n / 3) + 1, toOne(n - 1) + 1);
		}

		else if (n % 2 == 0) {
			memo[n] = Math.min(toOne(n / 2) + 1, toOne(n - 1) + 1);
			return memo[n];
		}
		else {
			memo[n] = toOne(n - 1) + 1;
		}
		
		return memo[n];
	}

}