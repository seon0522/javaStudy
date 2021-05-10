package GUI;
//5�� 3�� 
//ù��° �ð�

//�츮�� ����� �� �ִ� ���� ��ɵ�
import java.util.Collections;
import java.util.Comparator;
//���ϰ� ��밡���� api -> �̸� �̿��� ����Ʈ�� �ٲ� �� �ִ�.
import java.util.Arrays;

import java.util.List;

public class CollectionsAPITest {
	private String name;
	
	public CollectionsAPITest(String name) {
		this.name = name;
	}
	
	//static or instance method? - instance ������ �� ������ ��ü�� �����ϱ� ���ؼ�
	public String getName() {
		return name;
	}

	
	
	public static void main(String[] args) {
		/*		Comparable - ������ Ÿ�� Ŭ������ ���� ������ �� ������
		 * 
				Comparator - ������ Ÿ�� Ŭ������ ���� ������ �� ������
				EX) String, Integer
				ComparatorŬ������ �����ؼ� ���� ����� �˷���� ��. or 
				������ Ÿ�� Ŭ������ ������ �� ������,�������� �ʰ� 
				���Ĺ�� �����ϰ��� �� �� comparatorŬ���� ����
		*/	
		
		String[] sample = {"i", "walk", "the","line"};
		List<String> list = Arrays.asList(sample); //�迭�� ����Ʈ�� ����
		
		//Collection�� sort�޼���� List Ÿ���� ���ڷ� ������.
		System.out.println("���� �� : " + list);
		Collections.sort(list);
		System.out.println("�������� ���� �� : " +list);
		
		//Collection.sort(list STring�ָ�  ������ ��)
//		������������ �����ϰ� ���� ��
		Collections.sort(list, new MyStringComparator()); 
		System.out.println("�������� ���� �� : " + list);
		
	
		
	}
	
}
