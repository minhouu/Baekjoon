import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] d = new int[] { 1, -1 };
		if (K <= N) {
			System.out.println(N - K);
			return;
		}
		int[] visited = new int[200001];
		for (int i = 0; i < 200001; i++) {
			visited[i] = -1;
		}
		ArrayDeque<Integer> q = new ArrayDeque<>(); // 1, -1 움직일 때 사용하는 q

		// bsf
		// N을 먼저 넣어준다
		q.add(N);
		visited[N] = 0;
		while (true) {
			ArrayDeque<Integer> q2 = new ArrayDeque<>();// 해당 턴에서 순간이동으로 접근 가능한 곳 저장하는 q
			// q(1, -1이동)한 얘들에 대해서
			while (!q.isEmpty()) { // q가 빌 때까지 순간이동으로 접근가능한 곳 visited에  체크
				int temp = q.pop();
				q2.add(temp);
				int dist = visited[temp];
				
				while (temp < K * 2 && temp != 0) {
					if (temp == K) { // 일치하면 return
						System.out.println(dist);
						return;
					}
					if (visited[temp] != -1) { // 이미 방문했으면 continue
						temp *= 2;
						continue;
					}
					q2.add(temp);
					visited[temp] = dist;
					temp *= 2;
				}
			}
			
			
			// 2. 해당 턴에 순간이동 가능한 모든 위치에서 -1, 1 탐색하고 q에 저장
			while (!q2.isEmpty()) {
				int temp = q2.pop();
				for (int dir = 0; dir < 2; dir++) {
					int x = temp + d[dir];
					if (x == K) {
						System.out.println(visited[temp] + 1);
						return;
					}
					
					if (x < 0 || x > K * 2) { // 범위벗어나면 넘어감
						continue;
					}
					if (visited[x] != -1) { // 방문했으면 넘어감
						continue;
					}
					q.add(x);
					visited[x] = visited[temp] + 1;
				}
			}
		}
	}
}
