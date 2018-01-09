package studio.papercube.capturity;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;



public class SealedTool {
	
	public static class UIStyleInit{
		public static void init(){
			try{
				InitGlobalFont(new Font("微软雅黑", Font.PLAIN, 12));  
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				ToolTipManager.sharedInstance().setDismissDelay(20000);
			}
			catch(UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e){
				e.printStackTrace();
			}
		}
		
		/*Sources from Internet
		 * This part is used to initialize global fonts
		 * 
		 * @param font Font that you want to use
		 */
		private static void InitGlobalFont(Font font) { 
			FontUIResource fontRes = new FontUIResource(font);
			for (Enumeration<Object> keys = UIManager.getDefaults().keys();keys.hasMoreElements(); ) {
				Object key = keys.nextElement();
				Object value = UIManager.get(key);
				if (value instanceof FontUIResource) {
					UIManager.put(key, fontRes);
				}
			}
		}
	}
	
	public static class KeyPresser{
		private static Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();//获取剪切板
		// shift+ 按键
	    public static void keyPressWithShift(Robot r, int key) {
	            r.keyPress(KeyEvent.VK_SHIFT);
	            r.keyPress(key);
	            r.keyRelease(key);
	            r.keyRelease(KeyEvent.VK_SHIFT);
	            //r.delay(10);
	    }

	    // ctrl+ 按键
	    public static void keyPressWithCtrl(Robot r, int key) {
	            r.keyPress(KeyEvent.VK_CONTROL);
	            r.keyPress(key);
	            r.keyRelease(key);
	            r.keyRelease(KeyEvent.VK_CONTROL);
	            //r.delay(10);
	    }

	    // alt+ 按键
	    public static void keyPressWithAlt(Robot r, int key) {
	            r.keyPress(KeyEvent.VK_ALT);
	            r.keyPress(key);
	            r.keyRelease(key);
	            r.keyRelease(KeyEvent.VK_ALT);
	            //r.delay(10);
	    }
	    //打印出字符串
	    public static void keyPressString(Robot r, String str) {
	            setClipBoard(str);
	            paste(r);
	    }
	    
	    //单个 按键
	    public static void keyPress(Robot r,int key){
	            r.keyPress(key);
	            r.keyRelease(key);
	           // r.delay(10);
	    }
	    
	    public static void setClipBoard(String str) {
	    	try{
	            Transferable tText = new StringSelection(str);
	    		clip.setContents(tText, null); //设置剪切板内容
	    	}catch(IllegalStateException e){
	    		e.printStackTrace();
	    	}
	    	
	    }
	    
	    public static void paste(Robot r){
	    	keyPressWithCtrl(r, KeyEvent.VK_V);//粘贴
	        r.delay(5);
	    }
	    
	    public static String getClipboard() throws UnsupportedFlavorException, IOException{
	    	return clip.isDataFlavorAvailable(DataFlavor.stringFlavor)?(String)clip.getData(DataFlavor.stringFlavor):"Non-String Object";
	    }
	}
	
	@SuppressWarnings("serial")
	public static class FatalErrorWindow extends JFrame {
		private JLabel labelCrashReportPath;
		private JButton buttonExit;
		private JCheckBox cbIgnoreAll;
		private JLabel labelDescriptions;

		private FatalErrorWindow(){
			super("Fatal Error");
			setResizable(false);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			getContentPane().setLayout(null);
			setVisible(true);
			setSize(490,228);
			setLocationRelativeTo(null);
			
			JLabel labelText = new JLabel("<html><body>我们已经生成了错误报告，请将错误报告发送至开发者，来帮助我们排除故障<br>你可以将它发送至imzhy@hotmail.com来反馈这个问题</body></html>");
			labelText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			labelText.setBounds(10, 44, 463, 47);
			getContentPane().add(labelText);
			
			labelCrashReportPath = new JLabel();
			labelCrashReportPath.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			labelCrashReportPath.setText("错误报告未生成");
			labelCrashReportPath.setBounds(10, 125, 463, 23);
			getContentPane().add(labelCrashReportPath);
			
			buttonExit = new JButton("确定");
			buttonExit.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			buttonExit.setBounds(380, 158, 93, 23);
			getContentPane().add(buttonExit);
			
			JLabel label = new JLabel("很抱歉，发生了一个未知的错误");
			label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			label.setForeground(new Color(255, 69, 0));
			label.setBounds(10, 10, 463, 24);
			getContentPane().add(label);
			
			cbIgnoreAll = new JCheckBox("忽略一切问题并且不要再通知我（不建议）");
			cbIgnoreAll.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			cbIgnoreAll.setBounds(10, 154, 364, 23);
			//getContentPane().add(cbIgnoreAll);
			
			labelDescriptions = new JLabel("没有描述");
			labelDescriptions.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			labelDescriptions.setBounds(10, 101, 463, 15);
			getContentPane().add(labelDescriptions);
			
			buttonExit.addActionListener(evt->this.dispose());
			
		}
		
		public FatalErrorWindow(String path,String ...infos) {
			/*
			 * 
			 */
			this();
			
			String text=format("<html><body>错误报告位于 <br>%s</body><html>",path);
			
			StringBuffer infogenerator = new StringBuffer();
			
			for(String info:infos){
				infogenerator.append(info);
			}
			
			if(!infogenerator.toString().isEmpty()) labelDescriptions.setText(infogenerator.toString());
			
			labelCrashReportPath.setText(text);
			labelCrashReportPath.setToolTipText(text);
			
		}
	}
	
	public static class Fonter {
		private String[] fontList;
		public static Fonter fonter = new Fonter();
		private final String[] preferredFontList = new String []{"微软雅黑","微软雅黑 Light"};
		private String initializedFontName;
		
		private Fonter(){
			fontList=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		}
		
		public Font getPreferredFont(){
			if(getPreferredFontName()==null) return null;
			return new Font(getPreferredFontName(), Font.PLAIN, 12);
		}
		
		public String getPreferredFontName(){
			if(initializedFontName==null){
				for(String preferredFontName:preferredFontList){
					for(String fontName:fontList){
						if(preferredFontName.equals(fontName)) return preferredFontName;
					}
				}
				return null;
			}
			
			return initializedFontName;
		}
		
		public static Fonter sharedInstance(){
			return fonter;
		}
	}

	public static class ElapsedTimeCalculator {
		long start;
		public ElapsedTimeCalculator() {
			start=currentTimeMillis();
		}
		
		public long calculate(Runnable r){
			r.run();
			return getNode();
		}
		
		public long getNode(){
			return currentTimeMillis()-start;
		}
	}

	public static class DateFormatter {
		public static String getCurrent(){
			Date n = new Date();
		    
		    return String.format("%tF_%tH-%tM-%tS",n,n,n,n);
		}
	}
}
