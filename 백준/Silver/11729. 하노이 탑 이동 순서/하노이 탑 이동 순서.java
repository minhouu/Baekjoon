import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		System.out.println((int) (Math.pow(2, N) - 1));
		help(N, 1, 2, 3);
		System.out.println(sb.toString());
	}
	
	public static void help(int n, int from, int mid, int to) {
		// base case
		if (n == 1) {
			sb.append(from);
			sb.append(" ");
			sb.append(to);
			sb.append("\n");
			return;
		}
		// recursive case
		help(n-1, from, to, mid);
		sb.append(from);
		sb.append(" ");
		sb.append(to);
		sb.append("\n");
		help(n-1, mid, from, to);
	}
}