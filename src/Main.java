import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JPanel;

public class Main {

	private static Windows window;
	private static final String version = "1.0.0";
	private JPanel pan = new JPanel();

	public static void main(String[] args){

		Initialize();
	}  

	public static void Initialize(){

		window = new Windows();

		if(isUpdated()){
			System.out.println("je télécharge");
		}
		else{
			System.out.println("pas besoin");

		}
		window.setVisible(true);
	}

	public Windows getWindow(){
		return window;
	}


	private static boolean isUpdated(){
		new Download().downloadFile("versions.txt","https://www.dropbox.com/s/sres8zpv1fvobg2/versions.txt?dl=1");
		try{
			InputStream flux=new FileInputStream("versions.txt"); 
			InputStreamReader lecture=new InputStreamReader(flux);
			BufferedReader buff=new BufferedReader(lecture);
			String ligne=buff.readLine();
			buff.close(); 
			if(ligne.contentEquals(version)){
				return true;
			}
		}
		catch (Exception e){
			System.out.println(e.toString());
		}	
		return false;
	}
}
