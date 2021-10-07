package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;

public class TextAPIConverter extends JFrame implements ActionListener {

	private JButton converBtn;
	private JButton cancelBtn;
	private JTextArea textIn;
	private JTextArea textOut;
	
	private final String ClientId = "";
	private final String ClientPasswd =  "";
	
	public TextAPIConverter() {
//		�ڵ� �ٹٲ�
		textIn = new JTextArea(20, 24);
		textOut = new JTextArea(20, 24);
		
		textIn.setLineWrap(true);
		textOut.setLineWrap(true);
		textOut.setEditable(false);
		
		JPanel textAreaJPanel = new JPanel(new GridLayout(1,2,20,20));
		textAreaJPanel.add(textIn);
		textAreaJPanel.add(textOut);
		
		converBtn = new JButton("��ȯ");
		cancelBtn = new JButton("���");
		
		converBtn.addActionListener((ActionListener) this);
		cancelBtn.addActionListener(this);
		
		JPanel btnJPanel = new JPanel();
		btnJPanel.add(converBtn);
		btnJPanel.add(cancelBtn);
		
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.add(textAreaJPanel,BorderLayout.CENTER);
		mainPanel.add(btnJPanel, BorderLayout.SOUTH);
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		this.add(mainPanel);
		this.pack();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.setTitle("�ڹ� ������");
		this.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		��ȯ��ư�� Ŭ���Ǿ��ٸ�
			���� textArea(textin)�� �ؽ��� �о
			����� ��ȯ�ϰ� �� ��ȯ�� ����� 
			���� textArea(textout)�� append
		
		��� ��ư�� Ŭ�� �Ǿ��ٸ�
			���� textarea(textout)�� �� ���ڿ��� ����
		*/
		
		if(e.getSource() == converBtn) {
//			String str = textIn.getText();
//			String result = toEnglish(str);
//			textOut.setText(result);
			textOut.setText("");
			String result = toEnglish(textIn.getText());
			textOut.append(result);
			System.out.println("��ȯ");
			
		}else if(e.getSource() == cancelBtn){
			textOut.setText("");
			System.out.println("���");
			
		}else {
			System.out.println("�� �� ");
		}
	}
	
	private String toEnglish(String Korean) {
/*			korean ���ڿ��� ����� ��ȯ�ؼ� ��ȯ..
 * 			�ؽ�Ʈ -> text
 * 			���� => english
 * */
		
		String result = Korean;
		
//		������ �ٲٴ� ���� �ƴ϶� �ּҰ��� �ٲ���
//		result = Korean.replace("�ؽ�Ʈ", "text");
//		result = result.replace("����", "english");
        String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
        String text;
        try {
            text = URLEncoder.encode(Korean, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("���ڵ� ����", e);
        }

        Map<String, String>requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", ClientId);
        requestHeaders.put("X-Naver-Client-Secret", ClientPasswd);

        result = post(apiURL, requestHeaders, text);
        try {
        	result = URLDecoder.decode(result, "UTF-8");
        }catch (Exception e){
        	
        }
        System.out.println(result);
        
        String sd = result.substring(result.indexOf("translatedText") + "translatedText".length() + 3, result.indexOf("engineType")-3);
        
		return sd;
	}
	
	
	
	private static String post(String apiUrl, Map<String, String> requestHeaders, String text){
        HttpURLConnection con = connect(apiUrl);
        String postParams = "source=ko&target=en&text=" + text; //�������: �ѱ��� (ko) -> �������: ���� (en)
        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postParams.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // ���� ����
                return readBody(con.getInputStream());
            } else {  // ���� ����
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API ��û�� ���� ����", e);
        } finally {
            con.disconnect();
        }
    }
	
	private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL�� �߸��Ǿ����ϴ�. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("������ �����߽��ϴ�. : " + apiUrl, e);
        }
	}
	
	private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API ������ �дµ� �����߽��ϴ�.", e);
        }
    }

	public static void main(String[] args) {
		new TextAPIConverter();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
