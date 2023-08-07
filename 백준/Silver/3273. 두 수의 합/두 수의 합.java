import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[(int)1e6+1];
		int result = 0;
		
		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			arr[sc.nextInt()] = 1;
		}
		
		int X = sc.nextInt();
		for (int i = 1; i < (int)1e6 + 1; i++) {
			if (arr[i] == 0) {
				continue;
			}
			
			if (X-i > 0 && X-i < 1e6 && arr[X-i] == 1) {
				result++;
			}
		}
		System.out.println(result/2);
		sc.close();
	}
}