import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;


@SuppressWarnings("serial")
public class Windows extends JFrame implements HyperlinkListener, ActionListener{

	JEditorPane viewer       = new JEditorPane ();

	public Windows () {	
		JScrollPane scrollPane = new JScrollPane (viewer);
		getContentPane().add (scrollPane, BorderLayout.CENTER);
		viewer.setEditable (false);
	    setTitle("Launcher Voxelion");
	    setSize(800, 600);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadPage ("http://mcupdate.tumblr.com/");
	}

	public void actionPerformed (ActionEvent event){
		loadPage ("http://mcupdate.tumblr.com/");
	}

	public void loadPage (String urlText){
		try {
			viewer.setPage (new URL (urlText));
		} 
		catch (IOException ex) {
			System.err.println ("Acces impossible a " + urlText);
		}
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
