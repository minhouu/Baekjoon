import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int[] list;
	static int[] visited;
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
		// 중복 막기위한 변수
		// 만약 이 for문에서 i-1번째 원소와 i번째 원소가 같다면
		// 해당 수열은 중복된 수열이 된다
		// 그런 경우 continue해주는 조건 추가
		int temp = -1;
		for (int i = start; i < N; i++) {
			if (temp == list[i]) {
				continue;
			}
			result[cnt] = list[i];
			help(cnt + 1, i + 1, result);
			temp = list[i];
		}

	}
}