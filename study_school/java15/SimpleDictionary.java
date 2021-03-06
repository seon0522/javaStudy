package GUI;

//5월 6일 ~ 5월 10일
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//단어를 추가해도 프로그램을 종료하면 메모리의 데이터가 사라짐
//그래서 확장 - 데이터베이스에 저장. 프로그램 뜰 때 데이터베이스에서 읽어와서 맵을 구성
//DB에 docker을 이용해서 깜.프로그램의 라이브러리 등의 패키징해서 이미지화한 게 docker 


//key-value 인 맵에다가 저장. 
//simple가 생성될 때 자기가 페널이니까 밑의 버튼과 필드를 바로 붙이기
public class SimpleDictionary extends JPanel implements ActionListener{
/*	필요한 것 : 단어 입력 받을 수 있는 JTextField, 검색버튼, 추가버튼, 단어장 구현을 위한 자료구조 Map객체   */
	
	private JTextField inputField = new JTextField(30); //30컬럼짜리 텍스트필드
	private JButton searchBtn = new JButton("검색");
	private JButton addBtn = new JButton("추가");
	
	//Map 객체를 단어장 구현 사용하기
	//Key, Value 형으로 저장, key는 한글단어, value는 대응되는 영어단어
	private Map<String, String> words = new HashMap<>();//hashMap, TreeMap, linkedhashMap
	private static final String DIC_FILE_NAME = "doct.props";

	public SimpleDictionary() {
//		panel의 기본 레이아웃은 : FlowLayout - 물흐르듯 붙이고 자리부족하면 밑에 붙이고. 
		this.add(inputField);
		this.add(searchBtn);
		this.add(addBtn);
		
/*		setSize - 컴포넌트의 크기를 결정하는 메소드 
 * 		/ flowLayout에서 크기 지정 불가
		vs
		setPreferredSize -  메소드는 Dimension객체를 인자로 받으면서 
		해당 콤포넌트의 기본크기를 결정 / flowLayout에서 크기 지정 가능*/
		this.setPreferredSize(new Dimension(600, 50));
		
//		1. searchBtn, addBtn에 클릭 이벤트가 발생했을 때 처리할 리스너 지정하기
		searchBtn.addActionListener(this);
		addBtn.addActionListener(this);
		
		this.setPreferredSize(new Dimension(600, 50));
		
//		파일에 key=value 형태로 저장된 엔트리들을 읽어서, words를 구성하기
		buildDictionaryFromFile();
	}
	
	private void buildDictionaryFromFile() {
//		Properties는 key, value의 타입이 각각 String, String으로 고정된 일종의 map이다.
		Properties props = new Properties();
		
//		props.put("사과", "apple");
//		System.out.println(props.get("사과"));
		
//		파일에서 읽어서 props 객체의 <key, value> 쌍을 구성할 수 있다.
		File file = new File(DIC_FILE_NAME);
		System.out.println(file.getAbsolutePath());
		FileReader fReader = null;
		try {
		fReader = new FileReader(DIC_FILE_NAME);
		props.load(fReader); // 로드 해부면
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}finally {
//			닫기 필수 - 근데 close를 하면 또 예외 발생 그래서 try with 을 이용해 이용하면 쉬움 - try() catch{}  
			try {
				fReader.close();
			}catch(Exception ignore) {}
		}
		
		Set<Object> set = props.keySet();
		for(Object obj : set) {
			words.put((String)obj,(String)props.get(obj)); // words는 String으로 받는데 obj값을 반환하면 받을 수 있다.
		}
		
	}
	
	public static void main(String[] args) {
		
		File file = new File("doct.props");
		System.out.println(file.getAbsolutePath());
		
//		실제 보이게 하기 위해서는 프레임이 있어야 함.
		JFrame frame = new JFrame();
		SimpleDictionary dictPanel = new SimpleDictionary();
		frame.add(dictPanel); //이렇게만 한다고 보이지 않음
		frame.setTitle("나의 한영사전");
		
		frame.setVisible(true); //보이게 하기 위해
		frame.pack(); //선호되는 사이즈를 딱 맞춤
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //클린 후 작동되게
		frame.setResizable(false); //flow로 만들었으니까 밑으로 나가지 않도록 리사이즈 못 하게 막음
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("hello");
		
		
//		1.입력된 단어 추춸 - inputField에서 
		String key = inputField.getText();
		
//		1-1. 공백일 경우 - trim() - 공백제거
		if(key.trim().length() == 0) return;
		
		if(e.getSource() == searchBtn) {	
			System.out.println("searchBtn");
	
				System.out.println(key);
				System.out.println(e.getSource());
			 /*2. 단어와 대응되는 key로 맵 엔트리가 있는지 검사 -> dict.get(단어);
			 * 3. JOptionPane.showMessageDialog() 호출해서
			 * 단어와 대응되는 값이 있으면 대응되는 값을 보여주고
			 * 단어와 대응되는 값이 없으면(null반환) '단어를 찾을 수 없습니다.'출력
			 */
			String value = words.get(key);
			if(value != null) {
				JOptionPane.showMessageDialog(this, value, key, JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this, "단어를 찾을 수 없습니다."
											, key, JOptionPane.ERROR_MESSAGE);
			}

		}else if(e.getSource() == addBtn) {
			System.out.println("addBtn");
			 /* 2. JOptionPane.showinputDialog() 호출해서
			 * 3. 추가할 영어 단어 입력 받음
			 * 4. dict.put(입력필드에 입력된 단어, inputDialog에 입력된 단어);
			 */
			
			String value = JOptionPane.showInputDialog(this, key + "에 대응되는 영어 단력을 입력하세요");
			if(value.trim().length() == 0) return;
			words.put(key, value);
			addWordToFile(key, value);
			JOptionPane.showMessageDialog(this, value + "영어단어가 추가되었습니다." 
											,key, JOptionPane.INFORMATION_MESSAGE);
		}
//		4. inputField를 빈 문자열로 설정
		inputField.setText("");
	}
	
	private void addWordToFile(String key, String value) {
		
//		닫기 필수
		try(FileWriter fWriter = new FileWriter(DIC_FILE_NAME, true) ){
			// Exception이 발생할 수 있고, 다 쓰면 close 해 줘야 함
			fWriter.write(key + "=" + value + "\n"); //추가해야 하는데 덮어 씀. 안 됨..
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
}
