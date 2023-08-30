import java.util.Scanner;

public class Main {
	static int N;
	static int M;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		int[] result = new int[M];
		help(0, 1, result);

	}

	public static void help(int cnt, int start, int[] result) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}
		for (int i = start; i <= N; i++) {
			result[cnt] = i;
			help(cnt+1, i, result);
		}
		
	}
}