import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] board = new int[505][505];
	static int N;
	static int M;
	static int[][][] blocks = {
			// 청록색 블록
			{ { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 } },
			// 노랑색 블록
			{ { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } },
			// 주황색 블록
			{ { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 2 } }, { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 } },
			{ { 1, 0 }, { 1, 1 }, { 1, 2 }, { 0, 2 } }, { { 1, 0 }, { 1, 1 }, { 1, 2 }, { 0, 0 } },

			{ { 0, 1 }, { 1, 1 }, { 2, 1 }, { 0, 0 } }, { { 0, 1 }, { 1, 1 }, { 2, 1 }, { 2, 0 } },
			{ { 0, 0 }, { 1, 0 }, { 2, 0 }, { 0, 1 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 2, 1 } },
			// 초록색 블록
			{ { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 2 } }, { { 1, 0 }, { 1, 1 }, { 0, 1 }, { 0, 2 } },
			{ { 0, 0 }, { 1, 0 }, { 1, 1 }, { 2, 1 } }, { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 2, 0 } },
			// 자주색 블록
			{ { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 1 } }, { { 1, 0 }, { 1, 1 }, { 1, 2 }, { 0, 1 } },
			{ { 0, 0 }, { 1, 0 }, { 2, 0 }, { 1, 1 } }, { { 0, 1 }, { 1, 1 }, { 2, 1 }, { 1, 0 } } };

	public static void main(String[] args) throws IOException {
		// 입력부
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s = bf.readLine();
		StringTokenizer st = new StringTokenizer(s);
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			s = bf.readLine();
			st = new StringTokenizer(s);
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int result = 0;
		// 모든 칸에 대해서 search
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 각 점 i, j에 대해서 blocks의 19개 방향 다 테스트
				result = Math.max(result, bruteForce(i, j));
			}
		}

		System.out.println(result);
	}

	public static int bruteForce(int x, int y) {
		int max = 0;
		for (int i = 0; i < 19; i++) {
			int sum = 0;
			for (int j = 0; j < 4; j++) {
				sum += board[x + blocks[i][j][0]][y + blocks[i][j][1]];
			}
			max = Math.max(max, sum);
		}
		return max;
	}
}
