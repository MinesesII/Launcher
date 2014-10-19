import java.io.InputStream;

@SuppressWarnings("unused")
public class Loader {

	public Loader(){  

	}  
	
	public void runNewLauncher(String file) throws Exception{
		
		Process proc = Runtime.getRuntime().exec("java -jar "+ file + " " + Main.version);
		proc.getInputStream();
		proc.getErrorStream();
	}
	
	public void runGame(String file) throws Exception{
		
		Process proc = Runtime.getRuntime().exec("java -jar "+ file + " 0" );
		proc.getInputStream();
		proc.getErrorStream();
	}

}
