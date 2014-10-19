import java.io.InputStream;

public class Loader {

	public void Loader(){  

	}  
	
	public void runNewLauncher(String file) throws Exception{
		
		Process proc = Runtime.getRuntime().exec("java -jar "+ file + " " + Main.version);
		InputStream in = proc.getInputStream();
		InputStream err = proc.getErrorStream();
	}
	
	public void runGame(String file) throws Exception{
		
		Process proc = Runtime.getRuntime().exec("java -jar "+ file + " 0" );
		InputStream in = proc.getInputStream();
		InputStream err = proc.getErrorStream();
	}

}
