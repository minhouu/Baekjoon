import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// 선언부
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		ArrayDeque<Character> stack = new ArrayDeque<>();
		int result = 0;
		
		// input string에 대해서 순회
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') { // 여는 괄호는 바로 stack에 push
				stack.push(s.charAt(i));
			}
			else { // 닫는 괄호일 때
				if (s.charAt(i - 1) == ')') { // ')'가 연속되어 왔으면(레이저가 아니면) 이건 쇠막대기가 끝난 것
					stack.pop();
					result++; // 잘리고 남은 쇠막대기 한 덩이 스근하게 넣어준다
					continue;
				} 
				stack.pop(); // 레이저이면 ('('와 ')'가 붙어서 왔으면) 
				result += stack.size(); // stack의 사이즈만큼 result에 넣어준다
			}
		}
		System.out.println(result);
	}
}