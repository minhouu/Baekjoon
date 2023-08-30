import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int[] list;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		list = new int[N];
		for (int i = 0; i < N; i++) {
			list[i] = sc.nextInt();
		}
		Arrays.sort(list);
		help(0, 0, new int[M]);
		System.out.println(sb.toString());
	}

	public static void help(int cnt, int start, int[] result) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
			return;
		}

		for (int i = start; i < N; i++) {
			result[cnt] = list[i];
			help(cnt + 1, i, result);
		}
	}
}
