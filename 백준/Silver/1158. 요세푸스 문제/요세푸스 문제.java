import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int num = 1;
		ArrayDeque<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			q.add(num++);
		}
		
		System.out.print("<");
		while (!q.isEmpty()) {
			for (int j = 0; j < K-1; j++) {
				q.add(q.poll());
			}
			int temp = q.poll();
			if (q.isEmpty()) {
				System.out.print(temp);
				continue;
			}
			System.out.print(temp + ", ");
			
		}
		System.out.print(">");
	}
}
