import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	private Windows window;
	private static final String version = "1.0.1";
	private static String lastVersion;
	private static Main main;

	public static void main(String[] args){

		main = new Main();
		main.Initialize();
	}  

	public void Initialize(){
		window = new Windows();
		window.setVisible(true);	
	}

	public Windows getWindow(){
		return window;
	}


	private static boolean isUpdated(){
		try {
			new Download().downloadFile("http://1m4k8akux4.1fichier.com", "versions.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("versions.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    lastVersion = scanner.nextLine();
	    scanner.close();
		File fileToDelete = new File("versions.txt");
		fileToDelete.delete();
		lastVersion = "1.0.1";
		if(lastVersion.contentEquals(version)){
			return true;
		}
		return false;
	}

	public static Main getMain(){
		return main;
	}
}
