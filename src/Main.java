import java.applet.Applet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Main extends Applet {

	private Windows window;
	public static final String version = "1.0.0";
	public static String lastVersion;
	public static String launcherDownloadLink;
	public static String gameDownloadLink;
	private static Main main;

	public static void main(String[] args) throws Exception{

		main = new Main();
		main.Initialize();
		if(args.length!=0){
			File fileToDelete = new File("Voxelion_launcher_"+args[0]+".jar");
			fileToDelete.delete();
		}
	}  

	public void Initialize() throws Exception{
		if(isUpdated()){
			window = new Windows();
		}
		else{
			if(!new File("Voxelion.jar").exists()){
				new Download().downloadFile(launcherDownloadLink, "Voxelion_Launcher_"+lastVersion+".jar");
			}
			new Loader().runNewLauncher("Voxelion_launcher_"+lastVersion+".jar");		
		}
	}

	public Windows getWindow(){
		return window;
	}

	@SuppressWarnings("static-access")
	private static boolean isUpdated() throws IOException{
		new Download().downloadFile("http://voxelion.fr/Launcher/versions.txt", "versions.txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("versions.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		lastVersion = scanner.nextLine();
		launcherDownloadLink = scanner.nextLine();
		gameDownloadLink = scanner.nextLine();
		scanner.close();
		File fileToDelete = new File("versions.txt");
		fileToDelete.delete();
		if(lastVersion.contentEquals(version)){
			return true;
		}
		return false;
	}

	public static Main getMain(){
		return main;
	}
}
