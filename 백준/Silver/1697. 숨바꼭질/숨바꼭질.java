import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		int[] arr = new int[(int) 100002];
		ArrayDeque<Integer> q = new ArrayDeque<>();

		if (x == y) {
			System.out.println(0);
			return;
		}
		
		q.add(x);
		while (!q.isEmpty()) {
			int temp = q.pop();
//			for (int i = 0; i < y + 2; i++) {
//				System.out.print(arr[i] + " ");
//			}
			for (int i = 0; i < 3; i++) {
				int nxt;
				if (i == 0) {
					nxt = temp - 1;
				}
				else if (i == 1) {
					nxt = temp + 1;
				}
				else {
					nxt = temp * 2;
				}
				
				if (nxt == x || nxt > 100000 || nxt < 0 || arr[nxt] != 0) {
					continue;
				}
				
				if (nxt == y) {
					System.out.println(arr[temp] + 1);
					return;
				}
				
				arr[nxt] = arr[temp] + 1;
				q.add(nxt);
			}
		}
	}
}