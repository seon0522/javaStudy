package GUI;

import java.util.PriorityQueue;
import java.util.Queue;

public class PQTTest {
	public static void main(String[] args) {
		pqttest1();
	}
	
	public static void pqttest1() {
		Queue<String> q = new PriorityQueue<>();
		
//		��������
		q.offer("PineApple");
		q.offer("Banana");
		q.offer("Carrot");
		q.offer("Cherry");
		q.offer("Strawberry");
		
//		������, ������ �ƴϾ�
		System.out.println(q.peek());
		System.out.println(q.peek());
		System.out.println(q.element());
		System.out.println("#############");
		
		while(q.size() > 0) {
//			System.out.println(q.poll());
			System.out.println(q.remove());
		}
		
		System.out.println(q.peek());
		
	}
}
