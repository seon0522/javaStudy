package GUI;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.*;
import java.util.Set;


//2021 04 26

public class Map_Test {
	public static void main(String[] args) {
		test1();
		test2();
	}
	
	public static void test2() {
//		ģ���� ��ȭ��ȣ�� �����ϴ� map��ü�� �����۶�
//		<�̸�, ��ȭ��ȣ>
		
		String[] name = {"ȫ�浿","������","������","�̸���"};
		String[] phone = {"010-1234-4567","010-1234-2432","010-1234-3512","010-1234-8352"};
		
		Map<String, String> phoneBook = new HashMap<>();
		for(int i = 0; i < name.length; i++) {
			phoneBook.put(name[i], phone[i]);
		}
		
		Scanner input = new Scanner(System.in);
		while(true) {
			System.out.print("ģ�� �̸�: ");
			String nameValue = input.nextLine();
			if(nameValue.equals("")) break;
			String phoneValue = phoneBook.get(nameValue);
			System.out.println(nameValue + "�� ��ȭ��ȣ�� " + phoneValue);
		}
		
	}
	
	public static void test1() {
		/*Map ��ü, HashMap ��ü�� ��������
		 * ���� <Key, Value>�� ������ �����ϰ�
		 * Kdy ���� ���� �����Ѵ�.
		 * Map�̶�� ���� generic �������̽��̰�
		 * �̰� ������ HashMap, TreeMap, LinkedHAshMap Ŭ�������� ���׸� Ŭ������� ��
		 * �� �ǹ̴�
		 * �� ��ü���� ������ �� Ÿ���� ������ ��� ��.
		 * */
		
//		ArrayList<Integer> list = new ArrayList<>();
//		ArrayList<String> list2 = new ArrayList<>();
//		ArrayList<Student> list3 = new ArrayList<>();
		
//		Map�� <�й�, �л���ü> �̷��� ������ ����.
		Map<String, Student_map> map = new HashMap<>();
		
		map.put("2000101" , new Student_map(2000101 , "ȫ�浿"));
		map.put("2000102" , new Student_map(2000102 , "������"));
		map.put("2000103" , new Student_map(2000103 , "�̸���"));
		map.put("2000104" , new Student_map(2000104 , "ȫ�浿"));
		map.put("2000105" , new Student_map(2000105 , "������"));
		map.put("2000106" , new Student_map(2000106 , "�̸���"));
		
//		Ű ���� ������ hashCode()�� �޶�� ��
		Student_map value = map.get("2000101");
		System.out.println(value.getName());
		
		map.put("2000103", new Student_map(2000103, "�̻��"));
		
		value = map.get("2000103");
		System.out.println(value.getName());
		
		
		/*map�̶�� �ڷᱸ���� �� �ִ� ��� ���ҵ��� �� ���� �� �ִ� �����
		 * 1. map�� ������ ��� Ű ������ ��û �� ������ �� ����(Ű ��)�� ������ map���� ���� ��û�ϴ� ���
		 *key���� String���� ���� �����ϱ�.
		 */
		
//		keySet�� Ű�� ������ ����
//		Set<����Ÿ��>
		Set<String> keyset = map.keySet();
		
		Iterator<String> iter = keyset.iterator();
		while(iter.hasNext()) { //������ true, ������ false
			String key = iter.next();
			Student_map val = map.get(key);
			System.out.println("key:" + key + ", value:" + val);
		}
		
		/*
		 * 2. map�� <Key, Value> ������ ����� ��Ʈ�� ������ ��û �� ������ �ϳ��� �о� ���� ��� 
		 */
		//map���� ��Ʈ��Ÿ���� <key, Value>�� ������ ������ ��ü
//		��ü�� ���� �� ��ü�� �����ϴ� Ŭ������ �ִٴ� �̹�
//		entry<String, Student_map>Ÿ���� ��Ʈ��
		
//		set< entry < String, Student_map >>�̷� Ÿ���� ��Ʈ�� // set�� �������̽��� Ÿ���� ��Ʈ�� Ÿ���̶�� ��
		//Entry�� Map�̶�� �������̽��� �����Ǿ� ����.
		Set<Entry <String, Student_map>> entryset = map.entrySet();
		Iterator<Entry <String, Student_map>> eIter = entryset.iterator();
		
//		Iterator<> eIter = entryset.iterator();
		while(eIter.hasNext()) {
			Entry<String, Student_map> entryObj = eIter.next();
			String key = entryObj.getKey();
			Student_map val = entryObj.getValue();
			System.out.println("key: " + key + ", value: " + val);
		}
	}
}

class Student_map{
	private int hakbun;
	private String name;
	
	public String toString() {
		return "[�й� :" + hakbun + ", �̸� :" + name + "]";
	}
	
	
	public Student_map(int hakbun, String name) {
		super();
		this.hakbun = hakbun;
		this.name = name;
	}
	
	public int getHakbun() {
		return hakbun;
	}
	public void setHakbun(int hakbun) {
		this.hakbun = hakbun;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	

	
}
