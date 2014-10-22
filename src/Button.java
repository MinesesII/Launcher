import java.awt.*;  

import javax.swing.*;  

import java.util.*;  
import java.awt.event.*;  

@SuppressWarnings("serial")
public class Button extends JPanel  
implements MouseListener{  

	String title = null;  
	private Vector<ActionListener> listeners = null;  
	boolean hit = false;  
	Image image = null;  
	Image image2 = null;

	public Button (String title, Image image, Image image2){  
		super();  
		this.title = title;  
		listeners = new Vector<ActionListener>();  
		addMouseListener(this);  
		this.image = image;  
		this.image2 = image2;
	}  

	public Dimension getPreferredSize(){  
		if (image!=null){  
			return new Dimension(image.getWidth(this),image.getHeight(this));  
		}else{  
			return new Dimension(120,80);  
		}  
	}  

	public void paintComponent(Graphics g){  
		Graphics2D g2D = (Graphics2D)g;   
		if (image!=null){  
			if(hit==true){
				g2D.drawImage(image2,0,0,getWidth(),getHeight(),this);  
			}
			else{
				g2D.drawImage(image,0,0,getWidth(),getHeight(),this);  
			}
		};  
	}  

	public void mousePressed(MouseEvent e){  
		hit=true;  
		repaint();  
	}  

	public void mouseReleased(MouseEvent e){  
	}  

	public void mouseClicked(MouseEvent e){  
		fireEvent(new ActionEvent(this,0,title));  
	}  

	public void mouseEntered(MouseEvent e){
		hit=true;  
		repaint();  	
	}  
	public void mouseExited(MouseEvent e){
		hit=false;  
		repaint();  
	}  

	public void addActionListener(ActionListener listener){  
		listeners.addElement(listener);  
	}  

	public void removeActionListener(ActionListener listener){  
		listeners.removeElement(listener);  
	}  

	private void fireEvent(ActionEvent event){  
		for (int i = 0;i<listeners.size() ;i++ ){  
			ActionListener listener = (ActionListener)listeners.elementAt(i);  
			listener.actionPerformed(event);  
		};  
	}  

}
