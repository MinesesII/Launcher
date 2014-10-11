import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;


@SuppressWarnings("serial")
public class Windows extends JFrame implements HyperlinkListener, ActionListener{

	JEditorPane viewer= new JEditorPane();

	public Windows () {	
		JScrollPane scrollPane = new JScrollPane (viewer);
		getContentPane().add (scrollPane, BorderLayout.CENTER);
		viewer.setEditable (false);
		setTitle("Launcher Voxelion");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		showText("Check for updates");
		viewer.setBackground(Color.GRAY);
	}

	public void initializeButton(){
		JPanel boutonPane = new JPanel();
		JButton playButton = new JButton("Play");
		boutonPane.add(playButton);
		this.getContentPane().add(boutonPane, BorderLayout.SOUTH);
		playButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){	
			}});
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

	public void hyperlinkUpdate(HyperlinkEvent e) {		
	}

	public void showText(String text){
		viewer.setText(text);
	}
}
