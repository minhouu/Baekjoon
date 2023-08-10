import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		ArrayDeque<Integer> num = new ArrayDeque<>();
		for (int i = N; i > 0; i--) {
			num.push(i);
		}
		
		int M = sc.nextInt();
		int result = 0;
		for (int i = 0; i < M; i++) {
			int input = sc.nextInt();
			Iterator<Integer> it = num.iterator();
			int pos = 1;
			int final_pos = -1;
			while (it.hasNext() == true) {
				if (it.next() == input) {
					final_pos = pos;
					break;
				}
				pos++;
			}
			if (final_pos <= (int) Math.ceil(num.size()/2.0)) {
				while (num.peek() != input) {
					result++;
					num.add(num.pop());
				}
				num.pop();
			}
			else {
				while (num.peekLast() != input) {
					result++;
					num.push(num.pollLast());
				}
				result++;
				num.push(num.pollLast());
				num.pop();
			}
			
		}
		System.out.println(result);
	}
}