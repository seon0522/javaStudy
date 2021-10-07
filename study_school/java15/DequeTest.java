package GUI;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class DequeTest {
	public static void main(String[] args) {
		/*
		 * Deque�� �������̽�
		 * �̸� ������ Ŭ������ 
		 * ArrayDeque.
		 * interface�� ��ü�� ������ �� ����
		 * 
		 * */
		
//		<integer>Queue�� ��� ���Ұ� integer�̶�� ��
//		���� queue�� ����ϸ�
		/*
		 * queue: a, b �޼ҵ� �ִٸ�
		 * arrayDeque : a, b, c, d,e �޼ҵ� ����
		 * 
		 * 
		 * q.a, q.b < - O 
		 * q.c <- X
		 * 
		 * */
		
//		deque�� �ִ� �� �� ���� queue���� �ִ� �� �� ��.?
		
/* 		1. ť�� ���� �� 10�� �ְ�
 * 		2. �ȿ� � ������ �� �ִ��� ����
 * 		3. �ϳ��� ���� �غ��� <= FIFO ������ �������� Ȯ��
 */
		
		Deque<Integer> queue = new ArrayDeque<>();
		
		for(int i = 0; i < 10; i++) {
//			queue.add(i + 1);
			queue.offer(i + 1);
		}
		
		System.out.println(queue);
		
//		poll �޼ҵ�� �ϳ��� ����, FIFO ������ ���;� �Ѵ�.
		
//		while(queue.size() > 0) {
//			Integer value = queue.poll(); //queueSize 1�� ����
//			System.out.println(value);
//			System.out.println(	queue);
//		}
		
		
//		queue.isEmpty() == false�� queue.size() > 0
		while( !queue.isEmpty() ) {
			Integer val = queue.poll();
			System.out.println(val);
		}
		while( !queue.isEmpty() ) {
			Integer val = queue.remove();
			System.out.println(val);
		}
		
		System.out.println("ť�� ����: ");
		System.out.println(queue);
		
//		������ ���� ����, ť, stack
		
		
	}
}
