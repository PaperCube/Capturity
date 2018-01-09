package studio.papercube.capturity.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import studio.papercube.capturity.SealedTool;
import studio.papercube.capturity.nativesupport.Environment;
import studio.papercube.capturity.ui.swing.ErrorNotifier;

public class Settings {
	private static SettingsProvider sp = new SettingsProvider();
	
	public static String get(SettingItem item){
		return sp.getProperty(item.name, item.defaultValue);
	}
	
	public static boolean getBoolean(SettingItem item){
		return Boolean.parseBoolean(get(item));
	}
	
	public static int getInt(SettingItem item){
		return Integer.parseInt(get(item));
		
	}
	
	public static double getDouble(SettingItem item){
		return Double.parseDouble(get(item));
	}
	
	public static <T> void set(SettingItem item,T value){
		sp.setProperty(item.name, String.valueOf(value));
		sp.save();
	}
	
	
	
	
	@SuppressWarnings("serial")
	private static class SettingsProvider extends Properties{
		String path =Environment.getConfigFileDir() + "\\config.prop";
		public SettingsProvider() {
			super();
			File f = new File(path);
			try {
				
				if(!f.exists()) f.createNewFile();
				FileInputStream fis = new FileInputStream(path);
				load(fis);
			} catch (IOException e) {
				e.printStackTrace();
				ErrorNotifier.show("Error in settings" + f.getAbsolutePath() ,ErrorNotifier.toStackInfo(e));
			}
			
		}
		
		public void save(){
			FileWriter fos;
			try {
				fos = new FileWriter(path);
				store(fos, "last edited at "+SealedTool.DateFormatter.getCurrent());
			} catch (IOException e) {
				e.printStackTrace();
				ErrorNotifier.show("Error in settings",ErrorNotifier.toStackInfo(e));
			}
		}
	}
}
