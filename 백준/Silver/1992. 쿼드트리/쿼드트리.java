import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][] board;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			String s = bf.readLine();
			for (int j = 0; j < N; j++) {
				board[i][j] = s.charAt(j) - '0';
			}
		}

		help(0, 0, N);
		System.out.println(sb.toString());

	}

	public static void help(int x, int y, int len) {
		// base case
		if (len == 1) {
			sb.append(board[x][y]);
			return;
		}
		int sum = 0;
		for (int i = x; i < x + len; i++) {
			for (int j = y; j < y + len; j++) {
				sum += board[i][j];
			}
		}
		
		if (sum == 0 || sum == len * len) {
			sb.append(board[x][y]);
			return;
		}

		// 2. recursive case
		sb.append("(");
		// 2 - 1. 탐색 구간을 4개로 나눈다
		for (int i = x; i < x + len; i += len / 2) { 
			for (int j = y; j < y + len; j += len / 2) {
				help(i, j, len/2);
			}
		}
		sb.append(")");
	}
}
