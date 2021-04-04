package GUI;

import java.util.ArrayList;

public class ArrayList1<T> {
	
	private Object[] arr;
	private int capacity = 10;
	private int size = 0;
	
	public ArrayList1() {
		arr = new Object[capacity];
	}
	
	private void increaseCapacity() {
		capacity = capacity + capacity /2;
		Object[] temp = new Object[capacity];
		
		for(int j = 0; j < size; j++) {
			temp[j] = arr[j];
		}
		
		arr = temp;
	}
	
	public void add(T value) {
//		현재 용량으로 추가되는 값을 수용할 수 있으면 if문 실행안하고
		if(size >= capacity) {
		/*수용할 수 없으면
		 * arr배열의 크기를 늘려야 한다.
		 * */
			increaseCapacity();
		}
		
		arr[size++] = value;
	}
	
	public void add(int idx, T value) {
//		용량 고려X - 꽉 차있기 때문에 오른쪽으로 더 갈 수가 없음
//		용량이 이미 꽉 차있으면 용량을 50% 늘리고 코드 실행
		
		if(size == capacity) {
//			용량을 늘리자
			increaseCapacity();
		}
		
//		맨 뒤에 있는 원소부터 오른쪽으로 한 칸씩 민다.
//		size : 10 / 9 / 3 /
		for(int i = size-1; i >= idx; i--) {
			arr[i+1] = arr[i];
		}
		arr[idx] = value;
		size++;
	}
	
	public void remove() {
		if(size >0 ) {
			size--;
		}
	}
	
//	우리가 완성해서 깃허브에
	public void remove(int idx) {

		for(int i = idx; i < size; i++) {
			arr[i] = arr[i+1];
		}
		size--;
	}
	
	public int size() {		
		return size;
	}
	
	public T get(int idx) {
		return (T)arr[idx];
	}
	
	public String toString() {
		if(size == 0) return "[]";
		String result = "[";
		for(int i = 0; i < size-1; i++){
			result += arr[i] + ",";
			if((i+1) % 10 == 0) {
				 result +="\n";
			}
		}
		result += arr[size-1];
		result += "]";
		return result;
	}
	
	public static void main(String[] args) {	
		
//		autoboxing/ 
		
//		프리미티브 타입은 지원하지 않음, int를 적용하고 싶으면 integer로 
//		ArrayList<Integer> list = new ArrayList<Integer>();
//		ArrayList1 list = new ArrayList1();
		
		
//		add, get, size, remove
		
//		for(int i = 0; i < 10; i++) {
//			list.add(i);
//		}
		
//		list.add(3,100);
//		System.out.println(list);
		
		ArrayList1<String> list2= new ArrayList1<>();
		list2.add("홍길동");
		list2.add(1, "s");
		
		list2.add("아쉽네");
		list2.add(1, "t성춘향");
		
		list2.remove(2);
		
		System.out.println(list2);
		
		ArrayList1<Student> list3 = new ArrayList1<Student>();
		list3.add(new Student("하이요",142));
		list3.add(new Student("이몽룡",123));
		list3.add(new Student("성춘향",173));
		
		System.out.println(list3);
	}
	
}

