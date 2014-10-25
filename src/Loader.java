import java.io.File;
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
		Main.getMain().setInstaledVersion();
		Main.getMain().writeSettingsFile();
		Process proc = Runtime.getRuntime().exec("java -jar " + Main.getMain().getGameDirectory()+"Voxelion.jar " + mode);
		proc.getInputStream();
		proc.getErrorStream();
	}

}
