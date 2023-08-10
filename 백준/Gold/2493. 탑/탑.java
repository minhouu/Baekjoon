import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine()); // 탑의 개수
		StringTokenizer st = new StringTokenizer(bf.readLine()); // 탑들 전부 문자열로 받아옴
		ArrayDeque<int[]> stack = new ArrayDeque<>();
		stack.push(new int[] { (int) (1e8 + 1), 0 }); // 최대값 이상의 초기값 stack에 넣음 
		// 자기보다 이전에 높이가 높은 탑이 없으면(이것과 비교할때는 반드시 작기 때문에), 0의 값을 가지게 해줌
		StringBuilder output = new StringBuilder();
		
		
		for (int i = 0; i < N; i++) {
			int h = Integer.parseInt(st.nextToken()); // 높이 입력받음
			while (stack.peek()[0] < h) { // 자기보다 낮은 것들 다 제거
				stack.pop();
			}
			output.append(stack.peek()[1]); // 자기보다 높은 stack의 head값이 가리키는 top의 위치가 된다
			output.append(" ");
			stack.push(new int[] {h, i+1});
		}
		System.out.println(output);
	}
}