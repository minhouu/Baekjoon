import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력받는 부분
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int N = Integer.parseInt(bf.readLine());
			String s = bf.readLine();
			StringTokenizer st = new StringTokenizer(s);
			int[] arr = new int[N+1]; // 다음 학생 정보 저장하는 배열
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			int result = 0;
			
			
			int[] visited = new int[N+1]; // -1 : 방문안함, 0 : 팀에 들어감, > 1 :  팀에 못 들어감
			for (int i = 0; i <= N; i++) {
				visited[i] = -1;
			}
			
			
			for (int i = 1; i <= N; i++) {
				if (visited[i] == -1) { // 아직 방문하지 않은 노드일 때
					int temp = i; // 시작점
					while(true) {
						if (visited[temp] != -1) { 
							// i가 나오거나(순환이라는 뜻), 0이 나오면(다른 순환노드를 마주쳤다는 뜻)
							// 혹은 i 외의 다른 숫자(팀에 들어가지 못한 다른 노드를 만나면)
							while(visited[temp] != 0 && visited[temp] == i) { 
								// 다른 순환노드나 팀에 들어가지 못한 숫자를 마주쳤다면 그냥 넘어가고
								// i로 이루어진 순환노드라면 새로 그룹으로 체크해준다(0으로 만들어준다)
								visited[temp] = 0; // 그룹에 넣어준다
								temp = arr[temp];
							}
							break;
						}
						visited[temp] = i; // 순환 체크 전까지는 i를 넣는다
						temp = arr[temp];
					}
				}
			}
			
			for(int i = 1; i <= N; i++) {
				if (visited[i] != 0) {
					result++;
				}
			}
			System.out.println(result);
		}
	}
}