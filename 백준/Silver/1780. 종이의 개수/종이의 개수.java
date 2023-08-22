import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static HashMap<Integer, Integer> map = new HashMap<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int[][] board = new int[N][N];
		for (int i = 0; i < N; i++) {
			String s = bf.readLine();
			StringTokenizer st = new StringTokenizer(s);
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		map.put(1, 0);
		map.put(-1, 0);
		map.put(0, 0);

		help(board);
		System.out.println(map.get(-1));
		System.out.println(map.get(0));
		System.out.println(map.get(1));

	}

	public static void help(int[][] board) {
		// 1. base case
		// 1 - 1. board 크기가 1일 때 return
		if (board.length == 1) {
			map.put(board[0][0], map.get(board[0][0]) + 1);
			return;
		}
		
		// 1 - 2. board가 모두 같은 값을 가지고 있는지 확인
		// board 길이 선언
		int len = board.length;

		// board의 요소가 1로만, -1로만, 0으로만 이루어졌는지 확인한다
		// use hashmap
		HashMap<Integer, Integer> temp = new HashMap<>();
		temp.put(1, 0);
		temp.put(-1, 0);
		temp.put(0, 0);
		// 1, -1, 0 갯수기록
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				int num = board[i][j];
				temp.put(num, temp.get(num) + 1);
			}
		}
		
		// 1, -1, 0 으로만 이루어진 경우 return
		for (int i = -1; i <= 1; i++) {
			if (temp.get(i) == len * len) {
				map.put(i, map.get(i) + 1);
				return;
			}
		}

		// 2. recursive case
		int interval = board.length / 3;
		for (int r_head = 0; r_head < board.length; r_head += interval) { // 행의 시작점
			for (int c_head = 0; c_head < board.length; c_head += interval) { // 열의 시작점
				int[][] new_board = new int[interval][interval];
				for (int i = r_head; i < r_head + interval; i++) {
					for (int j = c_head; j < c_head + interval; j++) {
						new_board[i - r_head][j - c_head] = board[i][j]; // 1/3짜리 새 보드들을 help에 넣어서 재귀 호출
						
					}
				}
				help(new_board);
			}
		}

	}
}
