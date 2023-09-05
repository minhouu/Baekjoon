import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// 입력부
		Scanner sc = new Scanner(System.in);
		int[][] gears = new int[4][8]; // 2차원배열로 gears 받는다
		for (int i = 0; i < 4; i++) {
			String s = sc.next();
			for (int j = 0; j < 8; j++) {
				gears[i][j] = s.charAt(j) - '0';
			}
		}
		int[] pointers = new int[] { 102, 102, 102, 102 }; // 맨 왼쪽의 idx 정보 저장
		int[] dx = new int[] { -1, 1 };
		ArrayDeque<Integer> q = new ArrayDeque<>();

		int K = sc.nextInt();
		for (int tc = 0; tc < K; tc++) {
			int N = sc.nextInt() - 1; // 회전시키는 톱니바퀴 위치
			int[] visited = new int[4]; // 각 톱니바퀴들이 어떤 방향으로 회전해야하는지 저장

			q.add(N);
			visited[N] = sc.nextInt();
			while (!q.isEmpty()) {
				int temp = q.poll();
				for (int i = 0; i < 2; i++) {
					int x = temp + dx[i];
					if (x < 0 || x >= 4) {
						continue;
					}
					if (visited[x] != 0) {
						continue;
					}
					int temp_tooth = gears[temp][i == 0 ? pointers[temp] % 8 : (pointers[temp] + 4) % 8];
					int x_tooth = gears[x][i == 0 ? (pointers[x] + 4) % 8 : (pointers[x]) % 8];
					if (x_tooth != temp_tooth) {
						q.add(x);
						visited[x] = -1 * visited[temp];
					}
				}
			}

			for (int i = 0; i < 4; i++) {
				if (visited[i] != 0) {
					pointers[i] += -1 * visited[i];
				}
			}

		}

		int result = 0;
		for (int i = 0; i < 4; i++) {
			if (gears[i][(pointers[i] + 2) % 8] == 1) {
				result += (int) 1 << i;
			}
		}
		System.out.println(result);
	}
}
