package GUI;

//Generic class
// Type parameter

public class GenericBox<T> {
	private T content;
//	private int size;

	public T getContent() {
		return content;
	}

//T�� ������ ������ �˾Ƽ� �ص� Ŭ���� ��ü ���鶧 �޾Ƽ� �װſ� �����  - ���׸�
//�ٵ� T�� �ϸ� �����ڰ� �Ǽ����� �� ���� ����.
	public void setContent(T content) {
		this.content = content;
	}

}
