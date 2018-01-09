package studio.papercube.capturity.nativesupport;

import java.io.File;

public class Environment {
	public static void doUnitTest(){
		System.out.println(getProgramPath());
		System.out.println(getSystemName());
		System.out.println(getTemporaryDir());
		System.out.println(getProgramDir());
		System.out.println(getConfigFileDir());
	}
	
	
	
	public static String getProgramPath(){
		return System.getProperty("java.class.path").split(";")[0]+"\\";
	}
	
	public static String getSystemName(){
		return System.getProperty("os.name");
	}
	
	public static String getTemporaryDir(){
		return System.getProperty("java.io.tmpdir");
	}
	
	public static String getProgramDir(){
		File prog = new File(getProgramPath());
		return prog.isDirectory() ? prog.getAbsolutePath() : prog.getParent();
	}
	
	public static String getConfigFileDir(){
		File dir = new File(getProgramDir()+"/Capturity");
		dir.mkdirs();
		return dir.getAbsolutePath();
	}


}
