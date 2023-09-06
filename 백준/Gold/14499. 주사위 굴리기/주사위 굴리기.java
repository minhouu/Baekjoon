import java.util.Scanner;

public class Main {
	// 주사위 정보 저장
	static public class Dice {
		int r;
		int l;
		int head;
		int u;
		int d;
		int bottom;

		public Dice(int r, int l, int head, int u, int d, int bottom) {
			super();
			this.r = r;
			this.l = l;
			this.head = head;
			this.u = u;
			this.d = d;
			this.bottom = bottom;
		}
	}

	static int[][] board;
	static int N;
	static int M;
	static int x;
	static int y;
	static Dice dice;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		x = sc.nextInt();
		y = sc.nextInt();
		int k = sc.nextInt();
		board = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				board[i][j] = sc.nextInt();
			}
		}

		dice = new Dice(0, 0, 0, 0, 0, 0);
		dice.bottom = board[x][y];
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < k; i++) {
			int dir = sc.nextInt();
			if (dir == 1) {
				if (y + 1 >= M) {
					continue;
				}
				y++;
			} else if (dir == 2) {
				if (y - 1 < 0) {
					continue;
				}
				y--;
			} else if (dir == 3) {
				if (x - 1 < 0) {
					continue;
				}
				x--;
			} else if (dir == 4) {
				if (x + 1 >= N) {
					continue;
				}
				x++;
			}
			help(dir);
			sb.append(dice.head + "\n");
		}
		System.out.println(sb.toString());

	}

	public static void help(int dir) {
		Dice temp = new Dice(0, 0, 0, 0, 0, 0);
		switch (dir) {
		case 1:
			temp.head = dice.l;
			temp.bottom = dice.r;
			temp.u = dice.u;
			temp.d = dice.d;
			temp.r = dice.head;
			temp.l = dice.bottom;
			dice = temp;
			break;
		case 2:
			temp.head = dice.r;
			temp.bottom = dice.l;
			temp.u = dice.u;
			temp.d = dice.d;
			temp.r = dice.bottom;
			temp.l = dice.head;
			dice = temp;
			break;
		case 3:
			temp.head = dice.d;
			temp.bottom = dice.u;
			temp.u = dice.head;
			temp.d = dice.bottom;
			temp.r = dice.r;
			temp.l = dice.l;
			dice = temp;
			break;
		case 4:
			temp.head = dice.u;
			temp.bottom = dice.d;
			temp.u = dice.bottom;
			temp.d = dice.head;
			temp.r = dice.r;
			temp.l = dice.l;
			dice = temp;
			break;
		}

		if (board[x][y] == 0) {
			board[x][y] = dice.bottom;
			return;
		}
		dice.bottom = board[x][y];
		board[x][y] = 0;
	}
}
