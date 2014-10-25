import java.applet.Applet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
	public static String gameVersion = "Last update";
	public static String instaledVersion="";
	private String directory = (new File(new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent())).getPath()+"/Voxelion/";;
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
		loadSettings();
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
			while((line = in.readLine()) != null){
				versionsList.add(line);
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

	public void writeSettingsFile(){
		File dir = new File(System.getenv("APPDATA")+"/Voxelion");
		dir.mkdir();
		BufferedWriter writer = null;
		try {
			File logFile = new File(System.getenv("APPDATA")+"/Voxelion/"+"settings.txt");
			writer = new BufferedWriter(new FileWriter(logFile));
			writer.write(directory);
			writer.newLine();
			writer.write(gameVersion);
			writer.newLine();
			writer.write(instaledVersion);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
			}
		}
	}
	
	private void loadSettings(){
		if(new File(System.getenv("APPDATA")+"/Voxelion/settings.txt").exists()){
			Scanner scanner = null;
			try {
				scanner = new Scanner(new File(System.getenv("APPDATA")+"/Voxelion/settings.txt"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			directory = scanner.nextLine();
			gameVersion = scanner.nextLine();
			instaledVersion = scanner.nextLine();
			scanner.close();
		}
	}
	
	public void setSelectedVersion(String var){
		gameVersion=var;
	}
	
	public String getSelectedVersion(){
		return gameVersion;
	}

	public static Main getMain(){
		return main;
	}
	
	public void setGameDirectory(String dir){
		directory = dir;
	}
	
	public String getGameDirectory(){
		return directory;
	}
	
	public String getInstaledVersion(){
		return instaledVersion;
	}
	
	public void setInstaledVersion(){
		if(gameVersion.contentEquals("Last update")){
			instaledVersion = versionsList.get(0);
		}
		else{
			instaledVersion = gameVersion;
		}
	}
	
	public ArrayList<String> getVersionsList(){
		return versionsList ;
	}
}
