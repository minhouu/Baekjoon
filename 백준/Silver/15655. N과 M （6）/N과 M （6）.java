import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int[] list;
	
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
	}
	
	public static void help(int cnt, int start, int[] result) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i = start; i < N; i++) {
			result[cnt] = list[i];
			help(cnt + 1, i + 1, result);
		}
	}
}
