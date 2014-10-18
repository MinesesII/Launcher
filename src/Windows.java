import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Windows extends JWindow
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

	                JWindow window = new JWindow();
	                window.setSize(800, 600);
	                window.setLocationRelativeTo(null);
	                window.setVisible(true);
	                window.setLayout(new BorderLayout());
	                window.setBackground(new Color(0,0,0,0));
	                window.setContentPane(new JLabel(new ImageIcon("assets/background.png")));
	                window.setLayout(new FlowLayout());
	                window.addWindowListener(new WindowAdapter() {
	                    @Override
	                    public void windowClosing(WindowEvent e) {
	                        System.exit(0);
	                    }

	                });

	                MouseAdapter mouseHandler = new MouseAdapter() {

	                    private Point offset;

	                    protected boolean isWithinBorder(MouseEvent e) {
	                        Point p = e.getPoint();
	                        Component comp = e.getComponent();
	                        return p.x < 62 || p.y < 160 || p.x > comp.getWidth() - 62 || p.y > comp.getHeight()  - 62;
	                    }

	                    @Override
	                    public void mouseMoved(MouseEvent e) {
	                    	  Component comp = e.getComponent();
		                        if (isWithinBorder(e)) {
		                            comp.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		                        } else {
		                            comp.setCursor(Cursor.getDefaultCursor());
		                        }
	                    }

	                    @Override
	                    public void mouseDragged(MouseEvent e) {
	                        if (offset != null) {
	                            Point pos = e.getLocationOnScreen();

	                            int x = pos.x - offset.x;
	                            int y = pos.y - offset.y;

	                            System.out.println(x + "x" + y);

	                            SwingUtilities.getWindowAncestor(e.getComponent()).setLocation(x, y);
	                        }
	                    }

	                    @Override
	                    public void mousePressed(MouseEvent e) {
	                        if (isWithinBorder(e)) {
	                            Point pos = e.getComponent().getLocationOnScreen();
	                            offset = new Point(e.getLocationOnScreen());
	                            offset.x -= pos.x;
	                            offset.y -= pos.y;
	                        }
	                    }

	                };

	                window.getContentPane().addMouseListener(mouseHandler);
	                window.getContentPane().addMouseMotionListener(mouseHandler);

	                window.setLocationRelativeTo(null);
	                window.setVisible(true);
	            }
	        });
	    }
	}

	       