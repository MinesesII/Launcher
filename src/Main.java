import javax.swing.JPanel;

public class Main {
	
	private static Windows window;
	private JPanel pan = new JPanel();

	public static void main(String[] args){

		Initialize();
	}  
	
	public static void Initialize(){
		
		window = new Windows();
		window.setVisible(true);
	}
	
	public Windows getWindow(){
		return window;
	}
}
