import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] men = new int[6];
		int[] women = new int[6];

		for (int i = 0; i < N; i++) {
			int sex = sc.nextInt();
			int grade = sc.nextInt();
			if (sex == 0) {
				women[grade - 1]++;
				continue;
			}
			men[grade - 1]++;
		}
		int result = 0;
		for (int i = 0; i < 6; i++) {
			result += Math.ceil(men[i] / (double) K);
			result += Math.ceil(women[i] / (double) K);

		}
		System.out.println(result);
	}
}