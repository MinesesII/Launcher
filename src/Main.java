import java.applet.Applet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Main extends Applet {

	private Windows window;
	public static final String version = "1.0.0";
	public static String lastVersion;
	public static String gameVersion;
	public static ArrayList<String> versionsList = new ArrayList<String>();
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
			new Download("http://voxelion.fr/Launcher/Voxelion_launcher_1.0.0.jar", "Voxelion_Launcher_"+lastVersion+".jar");
			new Loader().runNewLauncher("Voxelion_launcher_"+lastVersion+".jar");		
		}
	}

	public Windows getWindow(){
		return window;
	}

	private static boolean isUpdated() throws IOException{
		try {
			URL url = new URL("http://voxelion.fr/Launcher/versions.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			lastVersion = in.readLine();
			String line;
			int i = 0;
			while((line = in.readLine()) != null){
				versionsList.add(line);
				i++;
			}
			in.close();
		}
		catch (MalformedURLException e) {}
		catch (IOException e) {}
		if(lastVersion.contentEquals(version)){
			return true;
		}
		return false;
	}

	static boolean isGameUpdated() throws IOException{
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("gameVersion.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		gameVersion = scanner.nextLine();
		scanner.close();
		if(gameVersion.contentEquals(versionsList.get(2))){
			return true;
		}
		return false;
	}

	public static Main getMain(){
		return main;
	}
}
