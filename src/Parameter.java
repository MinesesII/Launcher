import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;

@SuppressWarnings("serial")
public class Parameter extends JPanel{  

	private JComboBox updateList = new JComboBox();
	private static JFrame frame = new JFrame();

	public Parameter (){  
		//Versions//
		JLabel useVersion = new JLabel("Use version : ");
		useVersion.setFont(new Font("Ebrima", Font.BOLD, 16));
		useVersion.setForeground(new Color(160,160,160));
		useVersion.setPreferredSize(new Dimension(100,20));
		add(useVersion);
		updateList.setPreferredSize(new Dimension(150, 20));
		updateList.addItem("Last update");
		for(int i = 0; i<Main.versionsList.size(); i++){
			if(Main.versionsList.get(i).contentEquals("null")){
				updateList.addItem("");
			}
			else{
				updateList.addItem(Main.versionsList.get(i));
			}
		}
		updateList.setBackground(Color.LIGHT_GRAY);
		for(int i = 0; i<updateList.getItemCount(); i++){
			if(Main.getMain().getSelectedVersion().contentEquals(updateList.getItemAt(i).toString())){
				updateList.setSelectedIndex(i);
			}
		}
		updateList.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent arg0) {
				Main.getMain().setSelectedVersion(updateList.getSelectedItem().toString());
			}
		});
		add(updateList);
		Button patchNotesButton = new Button("", new ImageIcon(getClass().getResource("linkButtonUnclick.png")).getImage(), new ImageIcon(getClass().getResource("linkButtonClick.png")).getImage(), false);
		Dimension patchNotesButtonSize = patchNotesButton.getPreferredSize();
		patchNotesButton.setBounds(744 , 30, patchNotesButtonSize.width, patchNotesButtonSize.height);
		patchNotesButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {
				Desktop d=Desktop.getDesktop();
				 try {
					d.browse(new URI("http://voxelion.fr/Launcher/PatchNotes/"+ Main.getMain().versionsList.get(updateList.getSelectedIndex()-1) +".txt"));
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(patchNotesButton);
		//End Versions//
		
		//Directory//
		JLabel directory = new JLabel("Directory : ");
		directory.setFont(new Font("Ebrima", Font.BOLD, 16));
		directory.setForeground(new Color(160,160,160));
		directory.setPreferredSize(new Dimension(90,20));
		add(directory);
		final JTextField fieldDirectory = new JTextField(); 
		fieldDirectory.setPreferredSize(new Dimension(220,20));
		final File pathToJar = new File(new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent());
		fieldDirectory.setText(Main.getMain().getGameDirectory());
		add(fieldDirectory);
		Button defineDirectoryButton = new Button("", new ImageIcon(getClass().getResource("linkButtonUnclick.png")).getImage(), new ImageIcon(getClass().getResource("linkButtonClick.png")).getImage(), false);
		Dimension defineDirectoryButtonSize = defineDirectoryButton.getPreferredSize();
		defineDirectoryButton.setBounds(744 , 30, defineDirectoryButtonSize.width, defineDirectoryButtonSize.height);
		defineDirectoryButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {
				fieldDirectory.setText(chooseDirectory("Choose game directory", pathToJar.getPath(), null));
				Main.getMain().setGameDirectory(fieldDirectory.getText()+"/");
			}
		});
		add(defineDirectoryButton);
		//End Repertory//
	}  
	
	public String chooseDirectory(String title, String path, String fileFilter) {    
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("Choose game directory...");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);

	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	      System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
	      System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
	    } else {
	      System.out.println("No Selection ");
	    }
		return chooser.getSelectedFile().getPath();
	}

	public void paintComponent(Graphics g){  
		setOpaque(true);
	}
	
	public String getPatchNotes(){
		return null;
		
	}

}
