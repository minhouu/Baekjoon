import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 총 input 숫자 갯수
		int head = 1; // 현재 몇 번째 자연수가 들어갈 수 있는지 보여주는 head
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		StringBuilder output = new StringBuilder(); // output 담는 스트링빌더
		for (int i = 0; i < N; i++) {
			int input = sc.nextInt();
			if (input == head) {
				head++;
				output.append("+\n");
				output.append("-\n");
			} else if (input > head) {
				for (int j = head; j < input; j++) { // input값 전까지 stack에 넣어준다
					stack.push(j);
					output.append("+\n");
				}
				head = input + 1; // input에 해당하는 값은 넣었다 뺐다고 치고, output에 기록, head 업뎃
				output.append("+\n");
				output.append("-\n");
			} else {
				int stackVal = stack.pop();
				if (input == stackVal) {
					output.append("-\n");
					continue;
				}
				System.out.println("NO");
				return;
			}
		}
		System.out.print(output);
		sc.close();
		return;
	}
}