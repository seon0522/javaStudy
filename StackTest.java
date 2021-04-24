package GUI;

import java.util.Stack;

public class StackTest {
public static void main(String[] args) {
	
	//	stack : LIFO(last In First Out)
	
	/*
	 * Generic : Ŭ���� ���ο��� ����� ������ Ÿ���� ������ ���� �ƴϰ�
	 * �� Ŭ������ ��ü�� ������ �� ������ Ÿ���� ������ �� �ֵ���,
	 * ����� ������ Ÿ���� �Ķ���ͷ� �޾Ƽ� ��ü�� �����ϴ� ��. - Ÿ�� �Ķ����(type Parameter)
	 * 
	 * �� integer ���?
	 * Generic�� ������Ƽ�� Ÿ���� �� �Ǳ� ������ integer�� ���
	 * 
	 *   String�� ���� ���� ����� ��üŸ��(���� ������ Ÿ��)
	 * 
	 * */
	
//	(3 + 4) * (2 - 10) / 2   <- ������ stack�� üũ 
	/*
	 * stack���� (�ְ�, )������ pop �ϸ鼭
	 * �� ó���� �� �ȿ� �ƹ��͵� ���� �� ok
	 * �ƴϸ� �߸��� ���̶�� �� stack�� �̿��� ���� �� ����
	 * 
	 * */
	
	
	Stack<Integer> stack = new Stack();
	for(int i = 0; i < 10; i++) {
		stack.push(i + 1);
	}
	
	System.out.println(stack);
	
	while(!stack.isEmpty()) {
		Integer val = stack.pop();
		System.out.println(val);
	}
}
}
