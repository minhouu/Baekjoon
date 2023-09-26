import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	public static StringBuilder sb = new StringBuilder();
	
	public static int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+1];
		Arrays.fill(dp, INF);
		String[] route = new String[N+1];
		
		route[1] = "1";
		dp[1] = 0;
		
		for(int i = 1; i <= N; i++) {
			if(dp[i] == INF)continue;
			if(i * 3 <= N && dp[i * 3] > dp[i] + 1) {
				dp[i * 3] = dp[i] + 1;
				route[i*3] =   i*3 + " " + route[i];
			}
			if(i * 2 <= N && dp[i * 2] > dp[i] + 1) {
				dp[i * 2] = dp[i] + 1;
				route[i*2] = i*2 + " " + route[i];
			}
			if(i + 1 <= N && dp[i + 1] > dp[i] + 1) {
				dp[i + 1] = dp[i] + 1;
				route[i+1] = (i+1) + " " + route[i];
			}
		}
		System.out.println(dp[N]);
		System.out.println(route[N].trim());
		System.out.println(sb.toString().trim());
	}
}
