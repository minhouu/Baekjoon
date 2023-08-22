import java.util.Scanner;

public class Main {
	static int count = 0;
	static int[][] board;
	static int r;
	static int c;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		N = 2 << (N - 1); // 배열 길이로 활용하기 위해서 int길이로 변환
		r = sc.nextInt();
		c = sc.nextInt();
		help(0, 0, N);

	}

	public static void help(int x, int y, int len) { // x, y : 체크할 배열의 시작점 좌표, len : 배열의 한 변의 길이
		// base case
		if (len == 1) { // len == 1이면 찾은 것. count는 배열의 해당 위치에 들어갈 값 - 1이 들어가있으므로 count 출력
			System.out.println(count);
			return;
		}

		// recursive case
		// 순서대로 몇 사분면에 있는지 확인해준다
		int half = len / 2;
		int half_r = x + half;
		int half_c = y + half;
		if (r < half_r && c < half_c) { // 1번째(4사분면)
			help(x, y, half);
			return;
		}
		if (r < half_r && c >= half_c) { // 2번째(1사분면)
			count += half * half;
			help(x, half_c, half);
			return;
		}
		if (r >= half_r && c < half_c) { // 3번째(3사분면)
			count += half * half * 2;
			help(half_r, y, half);
			return;
		}
		if (r >= half_r && c >= half_c) { // 4번째(2사분면)
			count += half * half * 3;
			help(half_r, half_c, half);
			return;
		}
		
	}
}
