import java.util.Scanner;

public class Main {
	static int N;
	static int[][] eggs;
	static int result = 0; //
	static int[] visited;

	public static void main(String[] args) {
		// 입력부
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		eggs = new int[N][2];
		visited = new int[N];
		for (int i = 0; i < N; i++) {
			eggs[i][0] = sc.nextInt(); // 내구도(방어력)
			eggs[i][1] = sc.nextInt(); // 무게(공격력)
		}

		// 모든 순서를 고려한다
		help(0, eggs, 0);
		System.out.println(result);
	}

	public static void help(int pos, int[][] status, int cnt) { // pos : 현재위치, status : 현재 계란 상황, cnt : 깨진 계란 개수
		if (pos == N) { // 오른쪽으로 쭉 가면
			result = Math.max(result, cnt);
			return;
		}

		if (status[pos][0] <= 0) { // 만약 pos가 이미 깨진 계란 위치라면
			help(pos + 1, status, cnt);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (i == pos) {
				continue;
			}
			if (status[i][0] > 0) {
				// 선언부
				int temp = 0; // 이 회차에서 깨진 계란 수 저장
				int prev_pos_0 = status[pos][0]; // status의 이전 값 저장
				int prev_pos_1 = status[pos][1];
				int prev_i_0 = status[i][0];
				int prev_i_1 = status[i][1];

				// 계란 깨기 시행
				status[pos][0] -= status[i][1];
				status[i][0] -= status[pos][1];
				if (status[pos][0] <= 0) {
					temp++;
				}
				if (status[i][0] <= 0) {
					temp++;
				}

				// 재귀호출
				help(pos + 1, status, cnt + temp);

				// status 원상복귀
				status[pos][0] = prev_pos_0;
				status[pos][1] = prev_pos_1;
				status[i][0] = prev_i_0;
				status[i][1] = prev_i_1;
				continue;
			}
			help(pos + 1, status, cnt);
		}

	}
}
