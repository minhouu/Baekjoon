import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayDeque<Integer> cards = new ArrayDeque<>();
		int N = sc.nextInt();
		for (int i = 1; i <= N; i++) {
			cards.add(i);
		}
		while (cards.size()>1) {
			cards.pop();
			cards.add(cards.pop());
		}
		System.out.println(cards.pop());
	}
}