package GUI;

import java.util.Stack;

public class StackTest {
public static void main(String[] args) {
	
	//	stack : LIFO(last In First Out)
	
	/*
	 * Generic : 클래스 내부에서 사용할 데이터 타입이 정해진 것이 아니고
	 * 그 클래스의 객체를 생성할 때 데이터 타입을 결정할 수 있도록,
	 * 사용할 데이터 타입을 파라미터로 받아서 객체를 생성하는 것. - 타입 파라미터(type Parameter)
	 * 
	 * 왜 integer 사용?
	 * Generic은 프리미티브 타입이 안 되기 때문에 integer을 사용
	 * 
	 *   String가 제일 먼저 사용한 객체타입(참조 데이터 타입)
	 * 
	 * */
	
//	(3 + 4) * (2 - 10) / 2   <- 수식을 stack로 체크 
	/*
	 * stack에서 (넣고, )만나면 pop 하면서
	 * 다 처리할 때 안에 아무것도 없을 때 ok
	 * 아니면 잘못된 식이라는 걸 stack을 이용해 만들 수 있음
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
