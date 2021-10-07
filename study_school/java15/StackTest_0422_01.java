package GUI;

import java.util.*;

//4�� 22��
public class StackTest_0422_01 {
	public static void main(String[] args) {
    	/* 1. ( ���ÿ� �ְ�  ) �� ������ pop �ؼ� ������ ����
    	 * 2. �־��� ���� �� ó������ ��, ������ empty�� �ȴ�. (�߸��� ���� ���, empty�� ��ȯ ����)
    	 * EX)
    	 * ( ( (  ) ) => �߸� �� ��, �� ���� ó�� �Ŀ� empty�� �ƴϰ� ��
    	 * (   ) ) => �߸� �� ��, pop���� �� ����Ǵ� ���Ұ� ���� ���
    	 * 
    	 * 2 * (3 + 2) / ( 3 + 1 ) / 2 * 5 - 1 + 10 
    	 */
		

//	      1. ���� �Է¹ޱ�
	        Scanner input = new Scanner(System.in);
	        System.out.println("������ �Է��� �ּ���");
	        String exp = input.nextLine();
	        
//	      2. �Է¹��� ������ ���� �������� �ڸ��� - exp
//	      - ��ū(token)�̶�? Ư���� �����ڷ� �и��Ǵ� ���ڿ��� ������� - StringTokenizer �� �и� ����  
	        StringTokenizer st = new StringTokenizer(exp);
	        
//	      3. ���� ��ȣ�� stack�� push, �ݴ� ��ȣ�� stack�� pop
	        Stack<String> stack = new Stack<>();
	        
//	      hasMoreTokens() - StringTokenizerŬ���� ��ü���� �о���� token�� ������ true, ������ false
//	      nextToken() - ���� ��ū�� �о����
//	      equals() - ()�ȿ� ���ڿ��� ��ġ�ϴ��� Ȯ��
//	      hasMoreElements() - Ŀ�� �ٷ� �տ� ����Ÿ�� ����ִ����� üũ�ϴ� ��
	      //���� Ŀ���� 0�̶�� ù��°ĭ�� ����Ű�� ������ ����Ÿ�� �ϳ��� ����ִٸ� true�� ����
	        
	        
//	        3-1. �ݴ� ��ȣ�� �� ���� ��� - pop�� �ߴµ� empty�� �ȵ�.
//	        3-2. ���� ��ȣ�� �� ���� ��� - ��� ��ū ó���� �����µ���, empty�� �� �� ���
//	        
	        while(st.hasMoreElements()) {
	 
	        	String nextVal = st.nextToken();
	        	
	        	if(nextVal.equals("(")) {
	        		stack.push(nextVal);
	        	}else if(nextVal.equals(")")){
//	        		3-1 
	        		if(stack.isEmpty()) {
	        			System.out.println("�߸��� ���Դϴ�.");
	        			return;
	        		}
	        		stack.pop();
	        	}

	        }
	        
//	        3-2
	        if(stack.isEmpty() != true) {
	        	System.out.println("�߸��� ���Դϴ�.");
	        	return;
	        }
	        
	        System.out.println("��ȿ�� ���Դϴ�.");
	}
	
}
