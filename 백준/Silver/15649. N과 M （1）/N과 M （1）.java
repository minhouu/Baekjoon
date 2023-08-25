import java.util.Scanner;

public class Main {
	static int N;
	static int M;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		help(new int[M],  0);
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
			boolean isUsed = false;
			for (int j = 0; j <= len; j++) {
				if (result[j] == i) {
					isUsed = true;
					break;
				}
			}
			if (isUsed) {
				continue;
			}
//			System.out.println("포문" + len + " " + i);
			result[len] = i;
			help(result, len + 1);
			result[len] = 0;
		}
	}
}
