import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static ArrayList<int[]> cctv;
	static int[][] board;
	static int cctv_cnt;
	static int[] dx = new int[] { -1, 0, 1, 0 };
	static int[] dy = new int[] { 0, 1, 0, -1 };
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) {
		// 입력부
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		board = new int[N][M];
		cctv = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				board[i][j] = sc.nextInt();
				if (board[i][j] >= 1 && board[i][j] <= 5) { // cctv들 저장
					cctv.add(new int[] { i, j });
				}
			}
		}
		cctv_cnt = cctv.size();

		help(0, new int[cctv_cnt]);
		System.out.println(result);
	}

	public static void help(int cnt, int[] dir) {
		// base case
		if (cnt == cctv_cnt) {
			int[][] board_temp = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					board_temp[i][j] = board[i][j];
				}
			}

			for (int i = 0; i < cctv_cnt; i++) {
				// 모든 cctv에 대해서 각 방향에 맞게 bfs해서 감시구역을 채워준다
				int a = cctv.get(i)[0];
				int b = cctv.get(i)[1];
				for (int d = 0; d < 4; d++) {
					if (board_temp[a][b] == 1) { // 1번은 고른 방향만 탐색
						if (d != dir[i]) {
							continue;
						}
					}
					if (board_temp[a][b] == 2) {
						if (d % 2 != dir[i] % 2) { // 2번은 고른 방향, 반대 방향 탐색
							continue;
						}
					}
					if (board_temp[a][b] == 3) {
						if (!(d % 4 == dir[i] % 4 || (d + 1) % 4 == dir[i] % 4)) { // dir[i] 방향과 그 오른쪽 방향만 가능
							continue;
						}
					}
					if (board_temp[a][b] == 4) {
						if (d != dir[i] && d % 2 == dir[i] % 2) { // 한 방향 빼고 다 되게
							continue;
						}
					}
					int x = a + dx[d];
					int y = b + dy[d];
					while (x >= 0 && y >= 0 && x < N && y < M && board_temp[x][y] != 6) { // 범위 벗어나거나 6 되면 멈춤
						if (board_temp[x][y] == 0) { // 0일때는 -1로 체크한다
							board_temp[x][y] = -1;
						}
						x += dx[d];
						y += dy[d];
					}
				}
			}
			
			int blind = 0; // 해당 경우 사각지대의 갯수 센다
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (board_temp[i][j] == 0) {
						blind++;
					}
				}
			}
			// 전체 result 업데이트
			result = Math.min(result, blind);
			return;
		}
		
		
		// recursive case
		int x = cctv.get(cnt)[0]; // 선택된 cctv 좌표
		int y = cctv.get(cnt)[1];
		if (board[x][y] == 5) { // 5라서 방향 설정 필요 없을 때
			help(cnt + 1, dir);
			return;
		}
		if (board[x][y] == 2) { // 2는 2방향만 선택하면 됨
			for (int i = 0; i < 2; i++) {
				dir[cnt] = i;
				help(cnt + 1, dir);
			}
			return;
		}
		for (int i = 0; i < 4; i++) {
			dir[cnt] = i;
			help(cnt + 1, dir);
		}
		return;
	}
}
