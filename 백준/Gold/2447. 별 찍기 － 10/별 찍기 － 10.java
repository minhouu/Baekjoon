import java.util.Scanner;

public class Main {
	static char[][] board;

	public static void main(String[] args) {
		// 선언부
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		board = new char[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = ' ';
			}
		}
		StringBuilder sb = new StringBuilder();
		help(0, 0, N);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(board[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void help(int x, int y, int len) {
		// base case
		if (len == 3) {
			for (int i = x; i < x + 3; i++) {
				for (int j = y; j < y + 3; j++) {
					if (i == x + 1 && j == y + 1) {
						board[i][j] = ' ';
						continue;
					}
					board[i][j] = '*';
				}
			}
			return;
		}

		// recursive case
		int oneThird = len / 3;
		for (int i = x; i < x + len; i += oneThird) {
			for (int j = y; j < y + len; j += oneThird) {
				if (i == x + oneThird && j == y + oneThird) {
					continue;
				}
				help(i, j, oneThird);
			}
		}
	}
}
