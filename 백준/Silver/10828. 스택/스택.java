import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static class stack {
		int[] arr = new int[(int) 1e5 + 1];
		int pos = 0;

		public void push(int n) {
			arr[pos] = n;
			pos++;
		}

		public int pop() {
			if (pos == 0) {
				return -1;
			}
			pos--;
			return arr[pos];
		}

		public int size() {
			return pos;
		}

		public int empty() {
			if (pos == 0) {
				return 1;
			}
			return 0;
		}

		public int top() {
			if (pos == 0) {
				return -1;
			}
			return arr[pos - 1];
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stack st = new stack();
		int N = Integer.parseInt(bf.readLine());
		for (int i = 0; i < N; i++) {
			String input = bf.readLine();
			if (input.contains("push")) {
				st.push(Integer.parseInt(input.substring(5)));
			} else if (input.contains("pop")) {
				System.out.println(st.pop());
			} else if (input.contains("size")) {
				System.out.println(st.size());
			} else if (input.contains("empty")) {
				System.out.println(st.empty());
			} else if (input.contains("top")) {
				System.out.println(st.top());
			}
		}
	}
}