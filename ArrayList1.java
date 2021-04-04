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
//		���� �뷮���� �߰��Ǵ� ���� ������ �� ������ if�� ������ϰ�
		if(size >= capacity) {
		/*������ �� ������
		 * arr�迭�� ũ�⸦ �÷��� �Ѵ�.
		 * */
			increaseCapacity();
		}
		
		arr[size++] = value;
	}
	
	public void add(int idx, T value) {
//		�뷮 ���X - �� ���ֱ� ������ ���������� �� �� ���� ����
//		�뷮�� �̹� �� �������� �뷮�� 50% �ø��� �ڵ� ����
		
		if(size == capacity) {
//			�뷮�� �ø���
			increaseCapacity();
		}
		
//		�� �ڿ� �ִ� ���Һ��� ���������� �� ĭ�� �δ�.
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
	
//	�츮�� �ϼ��ؼ� ����꿡
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
		
//		������Ƽ�� Ÿ���� �������� ����, int�� �����ϰ� ������ integer�� 
//		ArrayList<Integer> list = new ArrayList<Integer>();
//		ArrayList1 list = new ArrayList1();
		
		
//		add, get, size, remove
		
//		for(int i = 0; i < 10; i++) {
//			list.add(i);
//		}
		
//		list.add(3,100);
//		System.out.println(list);
		
		ArrayList1<String> list2= new ArrayList1<>();
		list2.add("ȫ�浿");
		list2.add(1, "s");
		
		list2.add("�ƽ���");
		list2.add(1, "t������");
		
		list2.remove(2);
		
		System.out.println(list2);
		
		ArrayList1<Student> list3 = new ArrayList1<Student>();
		list3.add(new Student("���̿�",142));
		list3.add(new Student("�̸���",123));
		list3.add(new Student("������",173));
		
		System.out.println(list3);
	}
	
}

