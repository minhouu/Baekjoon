import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine()); // 탑의 개수
		ArrayDeque<Integer> twrs = new ArrayDeque<>(); // 탑 정보 저장할 배열
		ArrayDeque<int[]> temp = new ArrayDeque<>(); // 수신 위치가 정해지지 않은 타워들 임시저장
		int[] output = new int[N]; //
		String input = bf.readLine(); // 탑들 받아오기

		StringTokenizer st = new StringTokenizer(input);
		for (int i = 0; i < N; i++) {
			twrs.push(Integer.parseInt(st.nextToken()));
		}

		int idx = N - 1; // output에 넣을 위치 저장
		int preVal = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int num = twrs.pop(); 
			if (num < preVal) {
				temp.push(new int[] { num, idx }); // 탑 높이와 idx정보 함께 저장
			} else {
				while (!temp.isEmpty() && temp.peek()[0] < num) {
					int[] pop = temp.pop();
					output[pop[1]] = idx + 1;
				}
				temp.push(new int[] { num, idx });
			}
			preVal = num;
			idx--;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(output[i]);
			sb.append(" ");
		}
		System.out.println(sb);
	}
}