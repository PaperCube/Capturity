package studio.papercube.capturity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import studio.papercube.capturity.nativesupport.Environment;

public class SingleInstanceChecker {
	@SuppressWarnings("resource")
	public static boolean isSingle() throws FileNotFoundException, IOException{
		final String FILENAME = "/Capturity.LOCK";
		File file = new File(Environment.getConfigFileDir() + FILENAME);
		return (new FileOutputStream(file.getAbsolutePath()).getChannel().tryLock()!=null);
	}
}
