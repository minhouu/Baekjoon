import java.util.Scanner;

public class Main {
	static long C;
	static long A;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt();
		long B = sc.nextInt();
		C = sc.nextInt();
		System.out.println(help(B));
	}

	public static long help(long B) {
		// base case
		if (B == 1) {
			return A % C;
		}

		// recursive case
		long val = help(B / 2);
		val = val * val % C;
		if (B % 2 == 0)
			return val;// B가 2의 배수면
		return val * A % C;
	}
}