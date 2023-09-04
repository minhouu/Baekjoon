import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static ArrayList<int[]> house = new ArrayList<>();
	static ArrayList<int[]> chicken = new ArrayList<>();
	static int MIN_SUM = Integer.MAX_VALUE;

	public static void main(String[] args) {
		// 입력부
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int temp = sc.nextInt();
				// 치킨집 좌표 저장
				if (temp == 2) {
					chicken.add(new int[] { i, j });
					continue;
				}
				// 집 좌표 저장
				if (temp == 1) {
					house.add(new int[] { i, j });
				}
			}
		}
		
		help(0, 0, new int[M]);
		System.out.println(MIN_SUM);

	}

	public static void help(int cnt, int start, int[] result) {
		// base case
		if (cnt == M) {
			int sum = 0;
			// 모든 집들의 치킨거리 구한다
			for (int i = 0; i < house.size(); i++) {
				int min_dist = Integer.MAX_VALUE;
				// 모든 치킨집 중 가장 가까운 거리 구함(min_dist)
				for (int ch = 0; ch < M; ch++) {
					int x = chicken.get(result[ch])[0];
					int y = chicken.get(result[ch])[1];
					int dist = Math.abs(x - house.get(i)[0]) + Math.abs(y - house.get(i)[1]);
					min_dist = Math.min(min_dist, dist);
				}
				// sum에 min_dist 더해준다
				sum += min_dist;
			}
			// 최종 결과 update
			MIN_SUM = Math.min(MIN_SUM, sum);
			return;
		}
		
		// recursive case
		// M개의 치킨집을 선택하는 모든 조합에 대해서
		for (int i = start; i < chicken.size(); i++) {
			result[cnt] = i;
			help(cnt + 1, i + 1, result);
		}
	}
}
