import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int answer = 0;
		
		int lo = 0;
		int hi = 0;
//		System.out.println(Arrays.toString(nums));
		while(lo < N) {
			int sum = 0;
			for(int i = lo; i <= hi; i++) {
				sum+= nums[i];
			}
//			System.out.println(lo + " == " + hi + " >> " + sum);
			
			if(sum == M) {
				answer++;
				lo++;
			}else if(sum > M) {
				lo++;
			}else {
				if(hi + 1 == N) {
					break;
				}
				hi++;
			}
		}
		
		System.out.println(answer);
	}
}
