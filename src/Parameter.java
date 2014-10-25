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

	public Parameter (){  
		//Versions//
		JLabel useVersion = new JLabel("Use version : ");
		useVersion.setFont(new Font("Ebrima", Font.BOLD, 16));
		useVersion.setForeground(new Color(160,160,160));
		useVersion.setPreferredSize(new Dimension(100,20));
		add(useVersion);
		updateList.setPreferredSize(new Dimension(150, 20));
		for(int i = 0; i<Main.versionsList.size(); i++){
			if(Main.versionsList.get(i).contentEquals("null")){
				updateList.addItem("");
			}
			else if (i == 0){
				updateList.addItem(Main.versionsList.get(i) + " (Last update)");
			}
			else{
				updateList.addItem(Main.versionsList.get(i));
			}
		}
		updateList.setBackground(Color.LIGHT_GRAY);
		updateList.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent arg0) {
				Main.getMain().getWindow().setSelectedVersion(updateList.getSelectedIndex());
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
					d.browse(new URI("http://voxelion.fr/Launcher/PatchNotes/"+ Main.getMain().versionsList.get(updateList.getSelectedIndex()) +".txt"));
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
		JTextField fieldDirectory = new JTextField(); 
		fieldDirectory.setPreferredSize(new Dimension(220,20));
		File pathToJar = new File(new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent());
		fieldDirectory.setText(pathToJar.getPath()+"/Voxelion/");
		add(fieldDirectory);
		//End Repertory//
	}  

	public void paintComponent(Graphics g){  
		setOpaque(true);
	}
	
	public String getPatchNotes(){
		return null;
		
	}

}
