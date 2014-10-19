import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class Windows extends JFrame
{

	private ProgressBar progressBar;
	JEditorPane viewer= new JEditorPane();

	public Windows()
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
		}
		setLayout(null);
		setUndecorated(true);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setBackground(new Color(0,0,0,0));
		setContentPane(new JLabel(new ImageIcon(getClass().getResource("background.png"))));
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		  Image image = toolkit.getImage(getClass().getResource("cursor.png"));
		  Point hotSpot = new Point(0,0);  
		  Cursor cursor = toolkit.createCustomCursor(image, hotSpot, "cursor");  
		  setCursor (cursor);
		MouseAdapter mouseHandler = new MouseAdapter() {

			private Point offset;

			protected boolean isWithinBorder(MouseEvent e) {
				Point p = e.getPoint();
				Component comp = e.getComponent();
				return p.x < 79 || p.y < 168 || p.x > comp.getWidth() - 85 || p.y > comp.getHeight()  - 73;
			}

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
		Button closeButton = new Button("", new ImageIcon(getClass().getResource("crossUnclick.jpg")).getImage(), new ImageIcon(getClass().getResource("crossClick.jpg")).getImage());
		Dimension borderButtonsize = closeButton.getPreferredSize();
		closeButton.setBounds(744 , 30, borderButtonsize.width, borderButtonsize.height);
		closeButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		Button minimizeButton = new Button("", new ImageIcon(getClass().getResource("reduceUnclick.jpg")).getImage(), new ImageIcon(getClass().getResource("reduceClick.jpg")).getImage());
		minimizeButton.setBounds(660 , 30, borderButtonsize.width, borderButtonsize.height);
		minimizeButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {
				setState(Frame.ICONIFIED);
			}
		});
		Button playButton = new Button("", new ImageIcon(getClass().getResource("playUnclick.png")).getImage(), new ImageIcon(getClass().getResource("playClick.png")).getImage());
		Dimension playButtonsize = playButton.getPreferredSize();
		playButton.setBounds(550 , 460, playButtonsize.width, playButtonsize.height);
		playButton.addActionListener(new ActionListener(){  
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				if(!new File("Voxelion.jar").exists()){
					DownloadGame game = new DownloadGame(Main.gameDownloadLink, "Voxelion.jar");
					game.start();
				} 
				else{
					try {
						if (!Main.getMain().isGameUpdated()){
							File fileToDelete = new File("Voxelion.jar");
							fileToDelete.delete();
							DownloadGame game = new DownloadGame(Main.gameDownloadLink, "Voxelion.jar");
							game.start();
						}
						else{
							try {
								new Loader().runGame();
								System.exit(0);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		progressBar = new ProgressBar(new ImageIcon(getClass().getResource("progressBar.png")).getImage());
		Dimension progressBarSize = progressBar.getPreferredSize();
		progressBar.setBounds(121 , 475, progressBarSize.width, progressBarSize.height);
		getContentPane().add(closeButton);
		getContentPane().add(minimizeButton);
		getContentPane().add(playButton);
		getContentPane().add(progressBar);
		setLocationRelativeTo(null);
		setVisible(true);
	}


	public ProgressBar getProgressBar(){
		return progressBar;
	}
}

