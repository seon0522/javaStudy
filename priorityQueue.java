package jse;

import java.util.*;

//20210415
public class priorityQueue {
	public static void main(String[] args) {
//		test1();
		
		MyQueue queue = new MyQueue();
		queue.offer(new Task("작업1", 3));
		queue.offer(new Task("작업2", 2));
		queue.offer(new Task("작업3", 5));
		queue.offer(new Task("작업4", 1));
		queue.offer(new Task("작업5", 4));
		
		for(int i = 0; i < 5; i++) System.out.println(queue.poll());
	}
	
//	우선순위 큐는 priority를 가지고 있고
//	원소들은 비교가 가능한 원소여야 함 왜? priority자체가 내부적으로 정렬을 하고 하나식 꺼내 쓰기 때문에
//	근데 정렬할 방법이 없으면 그 원소는 넣을 수 없음
//	1. 정렬 방법을 알려주는 게 compare인터페이스를 구현하는 것
//	compare의 compareTo를 구현한다는 것이고 방법은 내가 비교하는 값을 가지고 비교
	
//	왜 처음부터 Task의 객체를 넣을 때 compare만 넣을 수 있게 제한을 두지 않았을까?
//	2. compare 인터페이스를 구현하지 객체를 주더라도 비교할 수 있는 방법이 있으니까
//	라이브러리 들고와서 사용하거나 하는 방법이 있으니까
//	compareTo를 구현하고 있지만 인터페이스 상속을 받고 있지 않으니까 에러가 나는거야. 모르니까!
//	
	
	private static void test1() {
		/*
		 * 우선순위큐 객체를 생성하고
		 * Task 인스턴스를 삽입, 인출 해보자
		 * 사용자가 만든 클래스의 정렬을 어떻게 알지?
		 * 정렬이 안 된다. 
		 * 1. compare를 이용해서 알려주는 방법
		 * 2. Task 자체로 비교 가능하게 하는 건
		 * */
		
//		우선순위큐 : 기본적으로 오름차순으로 인출
//		Queue<Task> queue = new PriorityQueue<>();
		Queue<Task> queue = new PriorityQueue<>(new TaskComparator()); // 알려주는 것, 내림차순, 제공안하면
		queue.offer(new Task("작업1", 3)); //오름차순
		queue.offer(new Task("작업2", 2));
		queue.offer(new Task("작업3", 5));
		queue.offer(new Task("작업4", 1));
		queue.offer(new Task("작업5", 4));
//		4,2,1,5,3
		
		while(queue.isEmpty() == false){
			Task task = queue.poll();
			
			System.out.println(task);
		}	
	}
}


//comparable - gener

// Task를 안 주면 comparable object를 비교 
// <task> 주면 task를 비교 

//비교를 해야하고, 그러면 comparable을구현해야 한다. 

class Task implements Comparable<Task>{
	String desc; // 설명
	int prioriry = 5; //이 작업의 우선순위
	
	public String toString() {
		return "[desc: " + desc+ ", prioriry:" + prioriry +"]";
	}
	
	public Task(String de, int prior){
		this.desc = de;
		this.prioriry = prior;
	}

	@Override
	public int compareTo(Task arg0) {
		// TODO Auto-generated method stub
//		이 객체의 값이 크면 양수, 같으면 0, 작으면 음수를 반환
//		return (-1) * this.prioriry - arg0.prioriry;
		return this.prioriry - arg0.prioriry;
	}
}

class MyQueue{
//	10개 넘어가는 지 체크 안함
	Task[] tasks = new Task[10];
	int idx = 0;
	int pidx = 0;
	
//	offer
	public void offer(Task value) {
		tasks[idx++] = value;
		//세로운 객체가 들어올 때 마다 선택 정렬 sorting 한다.
		
		for(int i = idx - 1; i >= 0; i--) {
			int max = i; //제일 마지막 원소가 최대값 가정
			for(int j = 0; j < i; j++) {
				if(tasks[j].compareTo(tasks[max]) > 0) {
					max = j;
				}
			}
			
			Task tmp = tasks[max];
			tasks[max] = tasks[i];
			tasks[i] = tmp;
		}
		
	}
	
//	poll
	public Task poll() { 
		
		return tasks[pidx++];	
	}
	
}

// 비교해주는 것 comparator
class TaskComparator implements Comparator<Task>{
	
	@Override
	public int compare(Task arg0, Task arg1) {
		// TODO Auto-generated method stub
		return arg0.prioriry - arg1.prioriry;
	}
}
