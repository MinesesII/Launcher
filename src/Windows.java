import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Windows extends JFrame
{

	public Windows()
	{
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
				}
				setLayout(null);
				setUndecorated(true);
				setSize(800, 600);
				setLocationRelativeTo(null);
				setBackground(new Color(0,0,0,0));
				setContentPane(new JLabel(new ImageIcon("assets/background.png")));
				MouseAdapter mouseHandler = new MouseAdapter() {

					private Point offset;

					protected boolean isWithinBorder(MouseEvent e) {
						Point p = e.getPoint();
						Component comp = e.getComponent();
						return p.x < 79 || p.y < 168 || p.x > comp.getWidth() - 85 || p.y > comp.getHeight()  - 73;
					}

					/*   @Override
	                    public void mouseMoved(MouseEvent e) {
	                    	  Component comp = e.getComponent();
		                        if (isWithinBorder(e)) {
		                            comp.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		                        } else {
		                            comp.setCursor(Cursor.getDefaultCursor());
		                        }
	                    }*/

					@Override
					public void mousePressed(MouseEvent e) {
						offset=null;
						if (isWithinBorder(e)) {
							Point pos = e.getComponent().getLocationOnScreen();
							offset = new Point(e.getLocationOnScreen());
							offset.x -= pos.x;
							offset.y -= pos.y;
						}
					}

					@Override
					public void mouseDragged(MouseEvent e) {
						if (offset != null) {
							Point pos = e.getLocationOnScreen();
							int x = pos.x - offset.x;
							int y = pos.y - offset.y;
							SwingUtilities.getWindowAncestor(e.getComponent()).setLocation(x, y);
						}
					}

				};

				getContentPane().addMouseListener(mouseHandler);
				getContentPane().addMouseMotionListener(mouseHandler);
				Button closeButton = new Button("", new ImageIcon("assets/crossUnclick.jpg").getImage(), new ImageIcon("assets/crossClick.jpg").getImage());
				Dimension size = closeButton.getPreferredSize();
				closeButton.setBounds(744 , 30, size.width-5, size.height-5);
				closeButton.addActionListener(new ActionListener(){  
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				Button minimizeButton = new Button("", new ImageIcon("assets/reduceUnclick.jpg").getImage(), new ImageIcon("assets/reduceClick.jpg").getImage());
				minimizeButton.setBounds(660 , 30, size.width-5, size.height-5);
				minimizeButton.addActionListener(new ActionListener(){  
					public void actionPerformed(ActionEvent e) {
						setState(Frame.ICONIFIED);
					}
				});
				getContentPane().add(closeButton);
				getContentPane().add(minimizeButton);
				setLocationRelativeTo(null);
				setVisible(true);
			}
		});
	}
}

