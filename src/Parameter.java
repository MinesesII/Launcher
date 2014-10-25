import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class Parameter extends JPanel{  

	private JComboBox updateList = new JComboBox();

	public Parameter (){  
		JLabel useVersion = new JLabel("Use version : ");
		useVersion.setFont(new Font("Ebrima", Font.BOLD, 16));
		useVersion.setForeground(new Color(160,160,160));
		useVersion.setPreferredSize(new Dimension(100,20));
		add(useVersion);
		updateList.setPreferredSize(new Dimension(150, 20));
		updateList.addItem(Main.versionsList.get(2) + " (last update)");
		for(int i = 0; i<Main.versionsList.size(); i+=2){
			if(Main.versionsList.get(i).contentEquals("null")){
				updateList.addItem("");
			}
			else{
				updateList.addItem(Main.versionsList.get(i));
			}
		}
		updateList.setBackground(Color.LIGHT_GRAY);
		updateList.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent arg0) {
				Main.getMain().getWindow().setSelectedVersion(updateList.getSelectedIndex()*2+2);
			}
		});
		add(updateList);
	}  

	public void paintComponent(Graphics g){  
		setOpaque(true);
	}

}
