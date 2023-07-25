import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numSwitch = sc.nextInt();

		int[] switches = new int[numSwitch];
		for (int i = 0; i < numSwitch; i++) {
			switches[i] = sc.nextInt();
		}
		int numStu = sc.nextInt();
		int[][] students = new int[numStu][2];
		for (int i = 0; i < numStu; i++) {
			students[i][0] = sc.nextInt();
			students[i][1] = sc.nextInt();
		}
		sc.nextLine();
		sc.close();

		for (int i = 0; i < numStu; i++) {
			if (students[i][0] == 1) { // 남학생이면
				for (int j = students[i][1]; j <= numSwitch; j += students[i][1]) {
					switches[j - 1] ^= 1;
				}
			}
			if (students[i][0] == 2) { // 여학생이면
				switches[students[i][1] - 1] ^= 1;
				for (int j = students[i][1] - 1, k = students[i][1] + 1; j >= 1 && k <= numSwitch; j--, k++) {
					if (switches[j - 1] != switches[k - 1]) {
						break;
					}
					switches[j - 1] ^= 1;
					switches[k - 1] ^= 1;
				}
			}
		}

		for (int i = 0, num = 1; i < numSwitch; i++, num++) {
			System.out.print(switches[i] + " ");
			if (num == 20) {
				System.out.print("\n");
				num = 0;
			}
		}
	}
}