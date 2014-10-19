import java.awt.*;  

import javax.swing.*;  

import java.util.*;  
import java.awt.event.*;  

@SuppressWarnings("serial")
public class ProgressBar extends JPanel  
implements MouseListener{  

	private float progress=0;
	
	@SuppressWarnings("unused")
	private Vector<ActionListener> listeners = null;  
	boolean hit = false;  
	Image image = null;  

	public ProgressBar (Image image){  
		super();  
		listeners = new Vector<ActionListener>();  
		addMouseListener(this);  
		this.image = image;  
	}  

	public Dimension getPreferredSize(){  
		return new Dimension(image.getWidth(this),image.getHeight(this));  
	}  

	public void paintComponent(Graphics g){  
		g.setColor(getParent().getBackground());  
		Graphics2D g2D = (Graphics2D)g;  
		g2D.setClip(0, 0, (int)(getWidth()*progress), getHeight());
		g2D.drawImage(image,0,0, getWidth(),getHeight(),this); 
	}  
	
	public void setProgress(float i){
		progress = i;
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {		
	}

	@Override
	public void mouseEntered(MouseEvent e) {		
	}

	@Override
	public void mouseExited(MouseEvent e) {		
	}

	@Override
	public void mousePressed(MouseEvent e) {		
	}

	@Override
	public void mouseReleased(MouseEvent e) {		
	}  
}
