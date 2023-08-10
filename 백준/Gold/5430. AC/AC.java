import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		StringBuilder output = new StringBuilder();
		for (int test_case = 0; test_case < T; test_case++) {
			// 선언부
			ArrayDeque<Integer> dq = new ArrayDeque<>(); // 배열
			String cal = bf.readLine();// 함수 문자열(RDRD)
			int N = Integer.parseInt(bf.readLine()); // 배열길이
			boolean isReversed = false; // R된 여부 (뒤쪽에서 뽑아야 하는지 여부)

			// 배열 parsing
			String s;
			String[] arr = new String[0];
			if (N != 0) { // 배열길이가 0이 아니면
				s = bf.readLine();
				arr = s.substring(1, s.length() - 1).split(","); // 배열형식의 string을 string 배열로 변환
			} else {
				bf.readLine();
			}

			// N과 배열 길이 다를 때
			if (arr.length != N) {
				output.append("error\n");
				continue;
			}

			// 배열 dq로 넣음
			for (int i = 0; i < arr.length; i++) {
				dq.add(Integer.parseInt(arr[i]));
			}

			// 함수 실행
			boolean flag = false; // error여부 판단
			for (int i = 0; i < cal.length(); i++) {
				char func = cal.charAt(i);
				if (func == 'R') { // R이 들어오면 reversed 바꾼다
					isReversed = !isReversed;
					continue;
				} else if (func == 'D') {
					if (dq.size() == 0) {
						flag = true;
						break;
					}

					if (isReversed) { // reversed되면
						if (dq.pollLast() == null) { // 뒤에서 뽑고 null이면 에러 뱉는다
							flag = true;
							break;
						}
					} else {
						if (dq.pollFirst() == null) {
							flag = true;
							break;
						}
					}
				} else {
					flag = true;
					break;
				}
			}

			// string builder에 넣기
			if (flag)
				output.append("error\n");
			else {
				if (isReversed) {
					// R 명령이 on인 상태이면 deque를 뒤집어준다
					ArrayDeque<Integer> temp = new ArrayDeque<>();
					while (dq.isEmpty() != true) {
						temp.push(dq.pop());
					}
					dq = temp;
				}

				output.append("[");
				while (dq.isEmpty() != true) {
					output.append(dq.pop());
					if (dq.isEmpty() != true) {
						output.append(",");
					}
				}
				output.append("]\n");
			}

		}
		System.out.println(output + "\n");
	}
}