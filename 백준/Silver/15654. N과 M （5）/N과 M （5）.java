import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int[] list;
	static int[] visited;

	public static void main(String[] args) {
		// 입력부
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		list = new int[N];
		visited = new int[N];
		for (int i = 0; i < N; i++) {
			list[i] = sc.nextInt();
		}
		Arrays.sort(list);
		help(0, new int[M]);
	}

	public static void help(int cnt, int[] result) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i] == 0) {
				visited[i] = 1;
				result[cnt] = list[i];
				help(cnt + 1, result);
				visited[i] = 0;
			}
		}
	}
}