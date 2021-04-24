package GUI;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class DequeTest {
	public static void main(String[] args) {
		/*
		 * Deque는 인터페이스
		 * 이를 구현한 클래스는 
		 * ArrayDeque.
		 * interface라서 객체를 생성할 수 없음
		 * 
		 * */
		
//		<integer>Queue에 담는 원소가 integer이라는 말
//		만약 queue를 사용하면
		/*
		 * queue: a, b 메소드 있다면
		 * arrayDeque : a, b, c, d,e 메소드 가능
		 * 
		 * 
		 * q.a, q.b < - O 
		 * q.c <- X
		 * 
		 * */
		
//		deque에 있는 건 못 쓰고 queue에만 있는 건 못 씀.?
		
/* 		1. 큐에 임의 수 10개 넣고
 * 		2. 안에 어떤 순서로 들어가 있는지 보고
 * 		3. 하나씩 인출 해보자 <= FIFO 순으로 나오는지 확인
 */
		
		Deque<Integer> queue = new ArrayDeque<>();
		
		for(int i = 0; i < 10; i++) {
//			queue.add(i + 1);
			queue.offer(i + 1);
		}
		
		System.out.println(queue);
		
//		poll 메소드로 하나씩 인출, FIFO 순으로 나와야 한다.
		
//		while(queue.size() > 0) {
//			Integer value = queue.poll(); //queueSize 1씩 감소
//			System.out.println(value);
//			System.out.println(	queue);
//		}
		
		
//		queue.isEmpty() == false가 queue.size() > 0
		while( !queue.isEmpty() ) {
			Integer val = queue.poll();
			System.out.println(val);
		}
		while( !queue.isEmpty() ) {
			Integer val = queue.remove();
			System.out.println(val);
		}
		
		System.out.println("큐의 상태: ");
		System.out.println(queue);
		
//		가지고 오면 삭제, 큐, stack
		
		
	}
}
