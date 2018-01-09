package studio.papercube.capturity.res;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.InputStream;
import java.net.URL;

public class Resources {
	Class<?> c = this.getClass();
	public final static Resources instance = new Resources();
	public URL getResource(String str){
		return c.getResource(str);
	}
	
	public InputStream getResourceAsStream(String str){
		return c.getResourceAsStream(str);
	}
	
	public Image getIcon(){
		return Toolkit.getDefaultToolkit().getImage(Resources.instance.getResource("icon.png"));
	}
}
