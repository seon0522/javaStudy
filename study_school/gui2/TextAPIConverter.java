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
//		자동 줄바꿈
		textIn = new JTextArea(20, 24);
		textOut = new JTextArea(20, 24);
		
		textIn.setLineWrap(true);
		textOut.setLineWrap(true);
		textOut.setEditable(false);
		
		JPanel textAreaJPanel = new JPanel(new GridLayout(1,2,20,20));
		textAreaJPanel.add(textIn);
		textAreaJPanel.add(textOut);
		
		converBtn = new JButton("변환");
		cancelBtn = new JButton("취소");
		
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
		
		this.setTitle("자바 번역기");
		this.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		변환버튼이 클릭되었다면
			좌측 textArea(textin)의 텍스를 읽어서
			영어로 변환하고 그 변환된 결과를 
			우측 textArea(textout)에 append
		
		취소 버튼이 클릭 되었다면
			우측 textarea(textout)을 빈 문자열로 설정
		*/
		
		if(e.getSource() == converBtn) {
//			String str = textIn.getText();
//			String result = toEnglish(str);
//			textOut.setText(result);
			textOut.setText("");
			String result = toEnglish(textIn.getText());
			textOut.append(result);
			System.out.println("변환");
			
		}else if(e.getSource() == cancelBtn){
			textOut.setText("");
			System.out.println("취소");
			
		}else {
			System.out.println("그 외 ");
		}
	}
	
	private String toEnglish(String Korean) {
/*			korean 문자열을 영어로 변환해서 반환..
 * 			텍스트 -> text
 * 			영어 => english
 * */
		
		String result = Korean;
		
//		원본을 바꾸는 것이 아니라 주소값을 바꿔줌
//		result = Korean.replace("텍스트", "text");
//		result = result.replace("영어", "english");
        String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
        String text;
        try {
            text = URLEncoder.encode(Korean, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("인코딩 실패", e);
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
        String postParams = "source=ko&target=en&text=" + text; //원본언어: 한국어 (ko) -> 목적언어: 영어 (en)
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
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }
	
	private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
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
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
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
