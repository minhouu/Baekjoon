import java.util.Scanner;

public class Main {
	static int N;
	static int[] S;
	static StringBuilder sb;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		while (N != 0) {
			// 입력부
			S = new int[N];
			for (int i = 0; i < N; i++) {
				S[i] = sc.nextInt();
			}
			sb = new StringBuilder();
			comb(0, 0, new int[6]);
			System.out.println(sb);
			N = sc.nextInt();
		}	
	}

	public static void comb(int cnt, int start, int[] result) {
		if (cnt == 6) {
			for (int i = 0; i < 6; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i < N; i++) {
			result[cnt] = S[i];
			comb(cnt + 1, i + 1, result);
		}
	}
}