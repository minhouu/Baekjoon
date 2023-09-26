import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	public static StringBuilder sb = new StringBuilder();
	
	public static int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		long N = Long.parseLong(br.readLine());
		
		long lo = -1;
		long hi = N;
		long mid = (lo + hi) / 2;
		
		while(lo + 1< hi) {
			mid = (lo + hi) / 2;
			
			if(Math.pow(mid, 2) >= N) {
				hi = mid;
			}else {
				lo = mid;
			}
		}
		System.out.println(hi);
	}
}
