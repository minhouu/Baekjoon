import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int w = sc.nextInt();
		int L = sc.nextInt();
		int[] trucks = new int[n]; // 트럭 무게 저장
		ArrayList<int[]> on = new ArrayList<>(); // 남은 거리, 트럭 무게 함께 저장하는 list
		int pointer = 0; // 다음에 넣을 트럭 위치 저장
		int time = 0; // 걸린 시간
		int sum_w = 0; // 현재 다리에 올라간 트럭 무게 총합

		for (int i = 0; i < n; i++) {
			trucks[i] = sc.nextInt();
		}

		while (pointer < n) {
			// 시간 증가시킨다
			time++;

			// on을 업데이트 한다(한 칸씩 앞으로 간다)
			for (int i = on.size() - 1; i >= 0; i--) {
				// 만약 남은 거리가 0이 되면, sum_w에서 해당 트럭의 무게를 빼고, on에서 삭제한다
				if (--on.get(i)[0] == 0) {
					sum_w -= on.get(i)[1];
					on.remove(i);
				}
			}

			// 만약 pointer 위치의 트럭이 다리 위에 올라갈 수 있으면
			if (sum_w + trucks[pointer] <= L) {
				// sum_w에 트럭 무게 추가하고
				sum_w += trucks[pointer];
				// list에 추가
				on.add(new int[] { w, trucks[pointer] });
				pointer++;
			}
		}
		
		System.out.println(time + w);

	}
}
