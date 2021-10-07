package jse;

import java.util.*;

//20210415
public class priorityQueue {
	public static void main(String[] args) {
//		test1();
		
		MyQueue queue = new MyQueue();
		queue.offer(new Task("�۾�1", 3));
		queue.offer(new Task("�۾�2", 2));
		queue.offer(new Task("�۾�3", 5));
		queue.offer(new Task("�۾�4", 1));
		queue.offer(new Task("�۾�5", 4));
		
		for(int i = 0; i < 5; i++) System.out.println(queue.poll());
	}
	
//	�켱���� ť�� priority�� ������ �ְ�
//	���ҵ��� �񱳰� ������ ���ҿ��� �� ��? priority��ü�� ���������� ������ �ϰ� �ϳ��� ���� ���� ������
//	�ٵ� ������ ����� ������ �� ���Ҵ� ���� �� ����
//	1. ���� ����� �˷��ִ� �� compare�������̽��� �����ϴ� ��
//	compare�� compareTo�� �����Ѵٴ� ���̰� ����� ���� ���ϴ� ���� ������ ��
	
//	�� ó������ Task�� ��ü�� ���� �� compare�� ���� �� �ְ� ������ ���� �ʾ�����?
//	2. compare �������̽��� �������� ��ü�� �ִ��� ���� �� �ִ� ����� �����ϱ�
//	���̺귯�� ���ͼ� ����ϰų� �ϴ� ����� �����ϱ�
//	compareTo�� �����ϰ� ������ �������̽� ����� �ް� ���� �����ϱ� ������ ���°ž�. �𸣴ϱ�!
//	
	
	private static void test1() {
		/*
		 * �켱����ť ��ü�� �����ϰ�
		 * Task �ν��Ͻ��� ����, ���� �غ���
		 * ����ڰ� ���� Ŭ������ ������ ��� ����?
		 * ������ �� �ȴ�. 
		 * 1. compare�� �̿��ؼ� �˷��ִ� ���
		 * 2. Task ��ü�� �� �����ϰ� �ϴ� ��
		 * */
		
//		�켱����ť : �⺻������ ������������ ����
//		Queue<Task> queue = new PriorityQueue<>();
		Queue<Task> queue = new PriorityQueue<>(new TaskComparator()); // �˷��ִ� ��, ��������, �������ϸ�
		queue.offer(new Task("�۾�1", 3)); //��������
		queue.offer(new Task("�۾�2", 2));
		queue.offer(new Task("�۾�3", 5));
		queue.offer(new Task("�۾�4", 1));
		queue.offer(new Task("�۾�5", 4));
//		4,2,1,5,3
		
		while(queue.isEmpty() == false){
			Task task = queue.poll();
			
			System.out.println(task);
		}	
	}
}


//comparable - gener

// Task�� �� �ָ� comparable object�� �� 
// <task> �ָ� task�� �� 

//�񱳸� �ؾ��ϰ�, �׷��� comparable�������ؾ� �Ѵ�. 

class Task implements Comparable<Task>{
	String desc; // ����
	int prioriry = 5; //�� �۾��� �켱����
	
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
//		�� ��ü�� ���� ũ�� ���, ������ 0, ������ ������ ��ȯ
//		return (-1) * this.prioriry - arg0.prioriry;
		return this.prioriry - arg0.prioriry;
	}
}

class MyQueue{
//	10�� �Ѿ�� �� üũ ����
	Task[] tasks = new Task[10];
	int idx = 0;
	int pidx = 0;
	
//	offer
	public void offer(Task value) {
		tasks[idx++] = value;
		//���ο� ��ü�� ���� �� ���� ���� ���� sorting �Ѵ�.
		
		for(int i = idx - 1; i >= 0; i--) {
			int max = i; //���� ������ ���Ұ� �ִ밪 ����
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

// �����ִ� �� comparator
class TaskComparator implements Comparator<Task>{
	
	@Override
	public int compare(Task arg0, Task arg1) {
		// TODO Auto-generated method stub
		return arg0.prioriry - arg1.prioriry;
	}
}
