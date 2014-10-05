public class Main {
	
	static Windows window;

	public static void main(String[] args){

		window = new Windows();
		window.show ();
	}  
	
	public Windows getWindow(){
		return window;
	}
}
