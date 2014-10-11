import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	private Windows window;
	private static final String version = "1.0.0";
	private static Main main;

	public static void main(String[] args){

		main = new Main();
		main.Initialize();
	}  

	public void Initialize(){
		window = new Windows();
		window.setVisible(true);	
		if(isUpdated()){
			window.loadPage("http://mcupdate.tumblr.com/");
		}
		else{
			window.showText("Downloading new Launcher");
		}
	}

	public Windows getWindow(){
		return window;
	}


	private static boolean isUpdated(){
		new Download().downloadFile("versions.txt","https://www.dropbox.com/s/sres8zpv1fvobg2/versions.txt?dl=1");
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("versions.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    String line = scanner.nextLine();
	    scanner.close();
		File fileToDelete = new File("versions.txt");
		fileToDelete.delete();
		if(line.contentEquals(version)){
			return true;
		}
		return false;
	}

	public static Main getMain(){
		return main;
	}
}
