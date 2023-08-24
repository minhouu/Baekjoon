import java.util.Scanner;

public class Main {
	static char[][] board;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		board = new char[n][2 * n - 1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2 * n - 1; j++) {
				board[i][j] = ' ';
			}
		}
		
		help(0, 0, n);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2 * n - 1; j++) {
				sb.append(board[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void help(int x, int y, int len) {
		if (len == 3) {
			board[x][y + 2] = '*';
			board[x+1][y+1] = '*';
			board[x+1][y+3] = '*';
			for (int i = 0; i < 5; i++) {
				board[x+2][y+i] = '*';
			}
			return;
		}
		
		help(x, y + len/2, len/2);
		help(x + len/2, y, len/2);
		help(x + len/2, y + len, len/2);
	}
}
