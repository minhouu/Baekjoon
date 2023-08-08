import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static class Node {
		char val;
		Node nxt;
		Node pre;

		public Node() {

		}

		public Node(char val) {
			this.val = val;
		}

		public void traverse() {
			Node cur = this.nxt;
			StringBuilder sb = new StringBuilder();
			while (cur != null) {
				if(cur.val != '|')
				sb.append(cur.val);
				cur = cur.nxt;
			}
			System.out.println(sb.toString());
		}

		public void insert(Node addr, Node neo) {
			addr.pre.nxt = neo;
			neo.pre = addr.pre;
			addr.pre = neo;
			neo.nxt = addr;
		}

		public void tailInsert(Node tail, Node neo) {
			tail.nxt = neo;
			neo.pre = tail;
			neo.nxt = null;
		}

		public void delete(Node addr) {
			if (addr.nxt != null) {
				addr.nxt.pre = addr.pre;
			}
			addr.pre.nxt = addr.nxt;

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		Node nodeHead = new Node();
		Node cur = new Node();
		nodeHead.nxt = cur;
		cur.pre = nodeHead;

		String s = bf.readLine();
		for (char c : s.toCharArray()) {
			cur.val = c;
			Node neo = new Node();
			cur.nxt = neo;
			neo.pre = cur;
			cur = neo;
		}
		cur.val = '|';

		int N = Integer.parseInt(bf.readLine());
		for (int i = 0; i < N; i++) {
			String input = bf.readLine();
//			nodeHead.traverse();
//			System.out.println("=======================");
			if (input.charAt(0) == 'L') {
				if (cur.pre != nodeHead) {
					Node neo = new Node(cur.val);
					nodeHead.insert(cur.pre, neo);
					nodeHead.delete(cur);
					cur = neo;
				}
			} else if (input.charAt(0) == 'D') {
				if (cur.nxt != null) {
					Node neo = new Node(cur.val);
					if (cur.nxt.nxt == null) {
						nodeHead.tailInsert(cur.nxt, neo);

					} else {
						nodeHead.insert(cur.nxt.nxt, neo);
					}
					nodeHead.delete(cur);
					cur = neo;
				}
			} else if (input.charAt(0) == 'B') {
				if (cur.pre != nodeHead) {
					nodeHead.delete(cur.pre);
				}
			} else {
				Node neo = new Node(input.charAt(2));
				nodeHead.insert(cur, neo);
				continue;
			}
		}
		nodeHead.traverse();
	}
}