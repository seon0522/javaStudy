package GUI;

import java.util.*;

//4월 22일
public class StackTest_0422_01 {
	public static void main(String[] args) {
    	/* 1. ( 스택에 넣고  ) 를 만나면 pop 해서 스택을 빼기
    	 * 2. 주어진 식을 다 처리했을 때, 스택이 empty가 된다. (잘못된 식일 경우, empty를 반환 안함)
    	 * EX)
    	 * ( ( (  ) ) => 잘못 된 식, 이 식을 처리 후에 empty이 아니게 됨
    	 * (   ) ) => 잘못 된 식, pop했을 때 인출되는 원소가 없는 경우
    	 * 
    	 * 2 * (3 + 2) / ( 3 + 1 ) / 2 * 5 - 1 + 10 
    	 */
		

//	      1. 수식 입력받기
	        Scanner input = new Scanner(System.in);
	        System.out.println("수식을 입력해 주세요");
	        String exp = input.nextLine();
	        
//	      2. 입력받은 수식을 공백 기준으로 자르기 - exp
//	      - 토큰(token)이란? 특정한 구분자로 분리되는 문자열의 구성요소 - StringTokenizer 로 분리 가능  
	        StringTokenizer st = new StringTokenizer(exp);
	        
//	      3. 여는 괄호면 stack에 push, 닫는 괄호면 stack에 pop
	        Stack<String> stack = new Stack<>();
	        
//	      hasMoreTokens() - StringTokenizer클래스 객체에서 읽어들일 token이 있으면 true, 없으면 false
//	      nextToken() - 다음 토큰을 읽어들임
//	      equals() - ()안에 문자열과 일치하는지 확인
//	      hasMoreElements() - 커서 바로 앞에 데이타가 들어있는지를 체크하는 것
	      //현재 커서가 0이라면 첫번째칸을 가리키기 때문에 데이타가 하나라도 들어있다면 true를 리턴
	        
	        
//	        3-1. 닫는 괄호가 더 많을 경우 - pop을 했는데 empty가 안됨.
//	        3-2. 여는 괄호가 더 많을 경우 - 모든 토큰 처리가 끝났는데도, empty가 안 될 경우
//	        
	        while(st.hasMoreElements()) {
	 
	        	String nextVal = st.nextToken();
	        	
	        	if(nextVal.equals("(")) {
	        		stack.push(nextVal);
	        	}else if(nextVal.equals(")")){
//	        		3-1 
	        		if(stack.isEmpty()) {
	        			System.out.println("잘못된 식입니다.");
	        			return;
	        		}
	        		stack.pop();
	        	}

	        }
	        
//	        3-2
	        if(stack.isEmpty() != true) {
	        	System.out.println("잘못된 식입니다.");
	        	return;
	        }
	        
	        System.out.println("유효한 식입니다.");
	}
	
}
