import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		visited = new int[N + 1];
		help(new int[M + 1], 0);
		sc.close();

	}

	public static void help(int[] result, int len) {
		if (len == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (visited[i] != 1) {
				result[len] = i;
				visited[i] = 1;
				help(result, len + 1);
				visited[i] = 0;
			}
//			System.out.println("포문" + len + " " + i);
			
		}
	}
}
