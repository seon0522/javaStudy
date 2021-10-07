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
//		친구들 전화번호를 저장하는 map객체를 생서앟라
//		<이름, 전화번호>
		
		String[] name = {"홍길동","일지매","성춘향","이몽룡"};
		String[] phone = {"010-1234-4567","010-1234-2432","010-1234-3512","010-1234-8352"};
		
		Map<String, String> phoneBook = new HashMap<>();
		for(int i = 0; i < name.length; i++) {
			phoneBook.put(name[i], phone[i]);
		}
		
		Scanner input = new Scanner(System.in);
		while(true) {
			System.out.print("친구 이름: ");
			String nameValue = input.nextLine();
			if(nameValue.equals("")) break;
			String phoneValue = phoneBook.get(nameValue);
			System.out.println(nameValue + "의 전화번호는 " + phoneValue);
		}
		
	}
	
	public static void test1() {
		/*Map 객체, HashMap 객체를 생성하자
		 * 맵은 <Key, Value>의 쌍으로 저장하고
		 * Kdy 값을 통해 인출한다.
		 * Map이라는 놈이 generic 인터페이스이고
		 * 이걸 구현한 HashMap, TreeMap, LinkedHAshMap 클래스들은 제네릭 클래스라는 것
		 * 이 의미는
		 * 저 객체들을 생성할 때 타입을 지정해 줘야 함.
		 * */
		
//		ArrayList<Integer> list = new ArrayList<>();
//		ArrayList<String> list2 = new ArrayList<>();
//		ArrayList<Student> list3 = new ArrayList<>();
		
//		Map에 <학번, 학생객체> 이렇게 저장할 예정.
		Map<String, Student_map> map = new HashMap<>();
		
		map.put("2000101" , new Student_map(2000101 , "홍길동"));
		map.put("2000102" , new Student_map(2000102 , "일지매"));
		map.put("2000103" , new Student_map(2000103 , "이몽룡"));
		map.put("2000104" , new Student_map(2000104 , "홍길동"));
		map.put("2000105" , new Student_map(2000105 , "일지매"));
		map.put("2000106" , new Student_map(2000106 , "이몽룡"));
		
//		키 값을 가지고 hashCode()를 달라고 함
		Student_map value = map.get("2000101");
		System.out.println(value.getName());
		
		map.put("2000103", new Student_map(2000103, "이삼룡"));
		
		value = map.get("2000103");
		System.out.println(value.getName());
		
		
		/*map이라는 자료구조에 들어가 있는 모든 원소들을 다 읽을 수 있는 방법은
		 * 1. map에 원소의 모든 키 집합을 요청 후 집합의 각 원소(키 값)를 가지고 map에게 값을 요청하는 방법
		 *key값을 String으로 저장 했으니까.
		 */
		
//		keySet은 키만 가지고 있음
//		Set<원소타입>
		Set<String> keyset = map.keySet();
		
		Iterator<String> iter = keyset.iterator();
		while(iter.hasNext()) { //있으면 true, 없으면 false
			String key = iter.next();
			Student_map val = map.get(key);
			System.out.println("key:" + key + ", value:" + val);
		}
		
		/*
		 * 2. map에 <Key, Value> 쌍으로 저장된 엔트리 집합을 요청 후 집합의 하나씩 읽어 오는 방법 
		 */
		//map에서 엔트리타입은 <key, Value>의 쌍으로 구성된 객체
//		객체라 함은 이 객체를 정의하는 클래스가 있다는 이미
//		entry<String, Student_map>타입의 엔트리
		
//		set< entry < String, Student_map >>이런 타입의 엔트리 // set은 인터페이스고 타입은 엔트리 타입이라는 것
		//Entry는 Map이라는 인터페이스에 구현되어 있음.
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
		return "[학번 :" + hakbun + ", 이름 :" + name + "]";
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
