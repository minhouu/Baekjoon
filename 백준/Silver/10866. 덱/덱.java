import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static class deque {
		int[] arr = new int[(int) 3e4];
		int head = arr.length / 2;
		int tail = head + 1;

		void push_front(int X) {
			arr[head] = X;
			head--;
		}

		void push_back(int X) {
			arr[tail] = X;
			tail++;
		}

		int empty() {
			if (tail == head + 1) {
				return 1;
			}
			return 0;
		}

		int pop_front() {
			if (this.empty() == 1) {
				return -1;
			}
			return arr[++head];
		}

		int pop_back() {
			if (this.empty() == 1) {
				return -1;
			}
			return arr[--tail];
		}

		int size() {
			return tail - head - 1;
		}

		int front() {
			if (this.empty() == 1) {
				return -1;
			} 
			return arr[head+1];
		}

		int back() {
			if (this.empty() == 1) {
				return -1;
			}
			return arr[tail-1];
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		deque dq = new deque();
		int N = Integer.parseInt(bf.readLine());
		for (int i = 0; i < N; i++) {
			String order = bf.readLine();
			if (order.contains("push_front")) {
				dq.push_front(Integer.parseInt(order.substring(11)));
//				System.out.println("dq 푸쉬프론트 : " + dq.arr[dq.head+1]);
			}
			else if (order.contains("push_back")) {
				dq.push_back(Integer.parseInt(order.substring(10)));
//				System.out.println("dq 푸쉬백 : " +  dq.arr[dq.tail-1]);
			}
			else if (order.contains("pop_front")) {
				System.out.println(dq.pop_front());
			}
			else if (order.contains("pop_back")) {
				System.out.println(dq.pop_back());
			}
			else if (order.contains("size")) {
//				System.out.println(dq.head);
//				System.out.println(dq.tail);
				System.out.println(dq.size());
			}
			else if (order.contains("empty")) {
				System.out.println(dq.empty());
			}
			else if (order.contains("front")) {
				System.out.println(dq.front());
			}
			else if (order.contains("back")) {
				System.out.println(dq.back());
			}
		}
	}
}