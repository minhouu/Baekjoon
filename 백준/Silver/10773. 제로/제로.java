import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// 선언부
		Scanner sc = new Scanner(System.in);
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		int N = sc.nextInt(); // 총 input 수
		int result = 0; // 출력할 결과
		
		// 모든 명령 순회하면서
		for (int i = 0; i < N; i++) {
			int num = sc.nextInt();
			if (num == 0) { // 만약 num == 0 이면 stack의 head 제거
				stack.pop();
				continue;
			}
			stack.push(num); // 나머지 경우 stack에 push
		}
		
		// 합 출력
		while (!stack.isEmpty()) {
			result += stack.pop();
		}
		System.out.println(result);
	}
}
