import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;

@SuppressWarnings("serial")
public class News extends JPanel{  

	String[] newsList = new String[8];

	public News (){  
		super();  
		RSSReader reader = new RSSReader();
		newsList=reader.parse("http://voxelion.over-blog.com/rss");
		for(int i = 0; i < newsList.length/2; i++){
			JLabel news = new JLabel(newsList[i]);
			news.setFont(new Font("Ebrima", Font.BOLD, 16));
			news.setForeground(new Color(160,160,160));
			news.setPreferredSize(new Dimension(310,20));
			add(news);
			Button newsButton = new Button("", new ImageIcon(getClass().getResource("linkButtonUnclick.png")).getImage(), new ImageIcon(getClass().getResource("linkButtonClick.png")).getImage(), false);
			Dimension newsButtonsize = newsButton.getPreferredSize();
			newsButton.setBounds(744 , 30, newsButtonsize.width, newsButtonsize.height);
			final int link = i;
			newsButton.addActionListener(new ActionListener(){  
				public void actionPerformed(ActionEvent e) {
					Desktop d=Desktop.getDesktop();
					 try {
						d.browse(new URI(newsList[link+4]));
					} catch (IOException | URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			});
			add(newsButton);
		}
	}  
	public void paintComponent(Graphics g){  
		setOpaque(true);
	}
}
