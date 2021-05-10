package GUI;

//5¿ù 6ÀÏ ~ 5¿ù 10ÀÏ
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

//´Ü¾î¸¦ Ãß°¡ÇØµµ ÇÁ·Î±×·¥À» Á¾·áÇÏ¸é ¸Ş¸ğ¸®ÀÇ µ¥ÀÌÅÍ°¡ »ç¶óÁü
//±×·¡¼­ È®Àå - µ¥ÀÌÅÍº£ÀÌ½º¿¡ ÀúÀå. ÇÁ·Î±×·¥ ¶ã ¶§ µ¥ÀÌÅÍº£ÀÌ½º¿¡¼­ ÀĞ¾î¿Í¼­ ¸ÊÀ» ±¸¼º
//DB¿¡ dockerÀ» ÀÌ¿ëÇØ¼­ ±ô.ÇÁ·Î±×·¥ÀÇ ¶óÀÌºê·¯¸® µîÀÇ ÆĞÅ°Â¡ÇØ¼­ ÀÌ¹ÌÁöÈ­ÇÑ °Ô docker 


//key-value ÀÎ ¸Ê¿¡´Ù°¡ ÀúÀå. 
//simple°¡ »ı¼ºµÉ ¶§ ÀÚ±â°¡ Æä³ÎÀÌ´Ï±î ¹ØÀÇ ¹öÆ°°ú ÇÊµå¸¦ ¹Ù·Î ºÙÀÌ±â
public class SimpleDictionary extends JPanel implements ActionListener{
/*	ÇÊ¿äÇÑ °Í : ´Ü¾î ÀÔ·Â ¹ŞÀ» ¼ö ÀÖ´Â JTextField, °Ë»ö¹öÆ°, Ãß°¡¹öÆ°, ´Ü¾îÀå ±¸ÇöÀ» À§ÇÑ ÀÚ·á±¸Á¶ Map°´Ã¼   */
	
	private JTextField inputField = new JTextField(30); //30ÄÃ·³Â¥¸® ÅØ½ºÆ®ÇÊµå
	private JButton searchBtn = new JButton("°Ë»ö");
	private JButton addBtn = new JButton("Ãß°¡");
	
	//Map °´Ã¼¸¦ ´Ü¾îÀå ±¸Çö »ç¿ëÇÏ±â
	//Key, Value ÇüÀ¸·Î ÀúÀå, key´Â ÇÑ±Û´Ü¾î, value´Â ´ëÀÀµÇ´Â ¿µ¾î´Ü¾î
	private Map<String, String> words = new HashMap<>();//hashMap, TreeMap, linkedhashMap
	private static final String DIC_FILE_NAME = "doct.props";

	public SimpleDictionary() {
//		panelÀÇ ±âº» ·¹ÀÌ¾Æ¿ôÀº : FlowLayout - ¹°Èå¸£µí ºÙÀÌ°í ÀÚ¸®ºÎÁ·ÇÏ¸é ¹Ø¿¡ ºÙÀÌ°í. 
		this.add(inputField);
		this.add(searchBtn);
		this.add(addBtn);
		
/*		setSize - ÄÄÆ÷³ÍÆ®ÀÇ Å©±â¸¦ °áÁ¤ÇÏ´Â ¸Ş¼Òµå 
 * 		/ flowLayout¿¡¼­ Å©±â ÁöÁ¤ ºÒ°¡
		vs
		setPreferredSize -  ¸Ş¼Òµå´Â Dimension°´Ã¼¸¦ ÀÎÀÚ·Î ¹ŞÀ¸¸é¼­ 
		ÇØ´ç ÄŞÆ÷³ÍÆ®ÀÇ ±âº»Å©±â¸¦ °áÁ¤ / flowLayout¿¡¼­ Å©±â ÁöÁ¤ °¡´É*/
		this.setPreferredSize(new Dimension(600, 50));
		
//		1. searchBtn, addBtn¿¡ Å¬¸¯ ÀÌº¥Æ®°¡ ¹ß»ıÇßÀ» ¶§ Ã³¸®ÇÒ ¸®½º³Ê ÁöÁ¤ÇÏ±â
		searchBtn.addActionListener(this);
		addBtn.addActionListener(this);
		
		this.setPreferredSize(new Dimension(600, 50));
		
//		ÆÄÀÏ¿¡ key=value ÇüÅÂ·Î ÀúÀåµÈ ¿£Æ®¸®µéÀ» ÀĞ¾î¼­, words¸¦ ±¸¼ºÇÏ±â
		buildDictionaryFromFile();
	}
	
	private void buildDictionaryFromFile() {
//		Properties´Â key, valueÀÇ Å¸ÀÔÀÌ °¢°¢ String, StringÀ¸·Î °íÁ¤µÈ ÀÏÁ¾ÀÇ mapÀÌ´Ù.
		Properties props = new Properties();
		
//		props.put("»ç°ú", "apple");
//		System.out.println(props.get("»ç°ú"));
		
//		ÆÄÀÏ¿¡¼­ ÀĞ¾î¼­ props °´Ã¼ÀÇ <key, value> ½ÖÀ» ±¸¼ºÇÒ ¼ö ÀÖ´Ù.
		File file = new File(DIC_FILE_NAME);
		System.out.println(file.getAbsolutePath());
		FileReader fReader = null;
		try {
		fReader = new FileReader(DIC_FILE_NAME);
		props.load(fReader); // ·Îµå ÇØºÎ¸é
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}finally {
//			´İ±â ÇÊ¼ö - ±Ùµ¥ close¸¦ ÇÏ¸é ¶Ç ¿¹¿Ü ¹ß»ı ±×·¡¼­ try with À» ÀÌ¿ëÇØ ÀÌ¿ëÇÏ¸é ½¬¿ò - try() catch{}  
			try {
				fReader.close();
			}catch(Exception ignore) {}
		}
		
		Set<Object> set = props.keySet();
		for(Object obj : set) {
			words.put((String)obj,(String)props.get(obj)); // words´Â StringÀ¸·Î ¹Ş´Âµ¥ obj°ªÀ» ¹İÈ¯ÇÏ¸é ¹ŞÀ» ¼ö ÀÖ´Ù.
		}
		
	}
	
	public static void main(String[] args) {
		
		File file = new File("doct.props");
		System.out.println(file.getAbsolutePath());
		
//		½ÇÁ¦ º¸ÀÌ°Ô ÇÏ±â À§ÇØ¼­´Â ÇÁ·¹ÀÓÀÌ ÀÖ¾î¾ß ÇÔ.
		JFrame frame = new JFrame();
		SimpleDictionary dictPanel = new SimpleDictionary();
		frame.add(dictPanel); //ÀÌ·¸°Ô¸¸ ÇÑ´Ù°í º¸ÀÌÁö ¾ÊÀ½
		frame.setTitle("³ªÀÇ ÇÑ¿µ»çÀü");
		
		frame.setVisible(true); //º¸ÀÌ°Ô ÇÏ±â À§ÇØ
		frame.pack(); //¼±È£µÇ´Â »çÀÌÁî¸¦ µü ¸ÂÃã
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Å¬¸° ÈÄ ÀÛµ¿µÇ°Ô
		frame.setResizable(false); //flow·Î ¸¸µé¾úÀ¸´Ï±î ¹ØÀ¸·Î ³ª°¡Áö ¾Êµµ·Ï ¸®»çÀÌÁî ¸ø ÇÏ°Ô ¸·À½
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("hello");
		
		
//		1.ÀÔ·ÂµÈ ´Ü¾î Ãß­” - inputField¿¡¼­ 
		String key = inputField.getText();
		
//		1-1. °ø¹éÀÏ °æ¿ì - trim() - °ø¹éÁ¦°Å
		if(key.trim().length() == 0) return;
		
		if(e.getSource() == searchBtn) {	
			System.out.println("searchBtn");
	
				System.out.println(key);
				System.out.println(e.getSource());
			 /*2. ´Ü¾î¿Í ´ëÀÀµÇ´Â key·Î ¸Ê ¿£Æ®¸®°¡ ÀÖ´ÂÁö °Ë»ç -> dict.get(´Ü¾î);
			 * 3. JOptionPane.showMessageDialog() È£ÃâÇØ¼­
			 * ´Ü¾î¿Í ´ëÀÀµÇ´Â °ªÀÌ ÀÖÀ¸¸é ´ëÀÀµÇ´Â °ªÀ» º¸¿©ÁÖ°í
			 * ´Ü¾î¿Í ´ëÀÀµÇ´Â °ªÀÌ ¾øÀ¸¸é(null¹İÈ¯) '´Ü¾î¸¦ Ã£À» ¼ö ¾ø½À´Ï´Ù.'Ãâ·Â
			 */
			String value = words.get(key);
			if(value != null) {
				JOptionPane.showMessageDialog(this, value, key, JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this, "´Ü¾î¸¦ Ã£À» ¼ö ¾ø½À´Ï´Ù."
											, key, JOptionPane.ERROR_MESSAGE);
			}

		}else if(e.getSource() == addBtn) {
			System.out.println("addBtn");
			 /* 2. JOptionPane.showinputDialog() È£ÃâÇØ¼­
			 * 3. Ãß°¡ÇÒ ¿µ¾î ´Ü¾î ÀÔ·Â ¹ŞÀ½
			 * 4. dict.put(ÀÔ·ÂÇÊµå¿¡ ÀÔ·ÂµÈ ´Ü¾î, inputDialog¿¡ ÀÔ·ÂµÈ ´Ü¾î);
			 */
			
			String value = JOptionPane.showInputDialog(this, key + "¿¡ ´ëÀÀµÇ´Â ¿µ¾î ´Ü·ÂÀ» ÀÔ·ÂÇÏ¼¼¿ä");
			if(value.trim().length() == 0) return;
			words.put(key, value);
			addWordToFile(key, value);
			JOptionPane.showMessageDialog(this, value + "¿µ¾î´Ü¾î°¡ Ãß°¡µÇ¾ú½À´Ï´Ù." 
											,key, JOptionPane.INFORMATION_MESSAGE);
		}
//		4. inputField¸¦ ºó ¹®ÀÚ¿­·Î ¼³Á¤
		inputField.setText("");
	}
	
	private void addWordToFile(String key, String value) {
		
//		´İ±â ÇÊ¼ö
		try(FileWriter fWriter = new FileWriter(DIC_FILE_NAME, true) ){
			// ExceptionÀÌ ¹ß»ıÇÒ ¼ö ÀÖ°í, ´Ù ¾²¸é close ÇØ Áà¾ß ÇÔ
			fWriter.write(key + "=" + value + "\n"); //Ãß°¡ÇØ¾ß ÇÏ´Âµ¥ µ¤¾î ¾¸. ¾È µÊ..
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
}
