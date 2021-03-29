package GUI;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("날짜를 입력하시오");
		int date = input.nextInt();
		
//		assert는 거르는 거 인데 default로 실행 안 함, 옵션을 줘서 실행 시켜야함
		
		assert (date >= 1 && date <= 31) : "잘못된 날짜" + date;
		
//		assert를 통과했다면 실행
		System.out.printf("입력된 날자는 %d 입니다.\n", date);
		
	}
}
