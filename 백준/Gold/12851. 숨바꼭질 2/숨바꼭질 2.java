import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] dist = new int[N > K ? N + 1 : K * 2 + 1]; // 거리 저장
		int[] visited = new int[N > K ? N + 1 : K * 2 + 1]; // 해당 idx에 최단거리로 온 녀석들 갯수 저장
		ArrayDeque<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < visited.length; i++) {
			dist[i] = -1;
		}

		// bfs를 하며, 탈출 조건을 수정
		// visited 배열에 해당 위치에 몇 번 접근했는지 체크
		// dist 배열이 가지는 기존 값보다 크면(더 오래 걸려서 접근하면) continue해준다
		q.add(N);
		
		// dist에는 거리(걸린 시간) 저장
		dist[N] = 0;
		
		// visited에는 방문한 횟수 저장
		visited[N] = 1;
		
		while (!q.isEmpty()) {
			int temp = q.poll();
			int distance = dist[temp] + 1;
			for (int dir = 1; dir <= 3; dir++) {
				int x = temp;
				if (dir == 1) {
					x -= 1;
				} else if (dir == 2) {
					x += 1;
				} else {
					x *= 2;
				}

				// 범위 벗어났을 때
				if (x < 0 || x >= visited.length) {
					continue;
				}

				// 이미 방문했는데, x가 걸린 시간이 더 길 때
				if (dist[x] != -1 && dist[x] < distance) {
					continue;
				}

				visited[x]++;
				dist[x] = distance;
				q.add(x);
			}
		}
		System.out.println(dist[K]);
		System.out.println(visited[K]);

	}
}