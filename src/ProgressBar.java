import java.awt.*;  

import javax.swing.*;  

@SuppressWarnings("serial")
public class ProgressBar extends JPanel  
{  

	private float progress=0;
	Image image = null;  

	public ProgressBar (Image image){  
		super();  
		this.image = image;  
	}  

	public Dimension getPreferredSize(){  
		return new Dimension(image.getWidth(this),image.getHeight(this));  
	}  

	public void paintComponent(Graphics g){
		g.setClip(0, 0, (int)(getWidth()*progress), getHeight());
		g.drawImage(image,0,0, getWidth(),getHeight(),this); 
	}  
	
	public void setProgress(float i){
		progress = i;
		repaint();
	}
}
