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
	
	public void runGame(int mode) throws Exception{
		
		Process proc = Runtime.getRuntime().exec("java -jar Voxelion.jar " + mode);
		proc.getInputStream();
		proc.getErrorStream();
	}

}
