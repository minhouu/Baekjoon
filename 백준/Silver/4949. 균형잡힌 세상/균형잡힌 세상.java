import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		while (s.equals(".") != true) {
			
			ArrayDeque<Character> brackets = new ArrayDeque<>();
			boolean flag = false;
			for (int i = 0; i < s.length(); i++) {
				char ele = s.charAt(i);
				if (ele == '(' || ele == '[') {
					brackets.push(ele);
					continue;
				}
				if (ele == ')') {
					if (brackets.isEmpty() != true && brackets.peek() == '(') {
						brackets.pop();
						continue;
					}
					else {
						flag = true;
						break;
					}
				}
				if (ele == ']') {
					if(brackets.isEmpty() != true && brackets.peek() == '[') {
						brackets.pop();
						continue;
					}
					else {
						flag = true;
						break;
					}
				}
			}
			if (brackets.isEmpty() == true && flag == false) {
				System.out.println("yes");
			}
			else {
				System.out.println("no");
			}
			s = sc.nextLine();
		}
		
		
		
	}
}