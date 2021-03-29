package GUI;

//Generic class
// Type parameter

public class GenericBox<T> {
	private T content;
//	private int size;

	public T getContent() {
		return content;
	}

//T가 뭘지는 개발자 알아서 해도 클래스 객체 만들때 받아서 그거에 맞출게  - 제네릭
//근데 T로 하면 개발자가 실수할지 알 수가 없음.
	public void setContent(T content) {
		this.content = content;
	}

}
