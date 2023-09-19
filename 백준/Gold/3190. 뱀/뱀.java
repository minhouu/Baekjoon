import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[][] board = new int[N][N]; // 0 : 그냥 빈 칸, 2 : 사과, 1 : 뱀 위치
		int time = 1;
		int[] dx = new int[] { -1, 0, 1, 0 };
		int[] dy = new int[] { 0, 1, 0, -1 };

		// 사과 위치 배열에 기록
		for (int i = 0; i < K; i++) {
			board[sc.nextInt()-1][sc.nextInt()-1] = 2;
		}

		// 명령들 저장할 배열 선언
		int L = sc.nextInt();
		int[] orders = new int[10001];
		for (int i = 0; i < L; i++) {
			int t = sc.nextInt();
			String d = sc.next();
			if (d.equals("D")) {
				orders[t] = 1;
				continue;
			}
			orders[t] = 2;
		}

		// 뱀 이동
		ArrayDeque<int[]> snake = new ArrayDeque<>();
		snake.add(new int[] { 0, 0, 1 });
		board[0][0] = 1;
		while (true) {

			// 머리 다음칸에 위치시킨다
			int[] prev_head = snake.peek();
			int x = prev_head[0] + dx[prev_head[2]];
			int y = prev_head[1] + dy[prev_head[2]];

			// 벽을 만날 때
			if (x < 0 || x >= N || y < 0 || y >= N) {
				break;
			}
			// 자기 몸을 만날 때
			if (board[x][y] == 1) {
				break;
			}

			// snake head 업데이트
			snake.push(new int[] { x, y, snake.peek()[2] });

			// 사과를 먹지 않을 때
			if (board[x][y] != 2) {
				int[] temp = snake.pollLast(); // deque에서 꼬리 삭제
				board[temp[0]][temp[1]] = 0;
			}

			board[x][y] = 1;

			// 만약 방향을 바꿔야 한다면
			if (orders[time] != 0) {
				int[] temp_head = snake.poll();
				int od = temp_head[2] + 4;
				temp_head[2] = orders[time] == 1 ? (od + 1) % 4 : (od - 1) % 4;
				snake.push(temp_head);
			}

//			// 디버깅용 출력
//			System.out.println("time : " + time);
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(board[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("-----------------");

			time++;

		}

		System.out.println(time);
	}
}
