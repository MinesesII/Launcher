import java.awt.*;  

import javax.swing.*;

@SuppressWarnings("serial")
public class News extends JPanel{  
	
	String[] newsList = new String[5];

	public News (){  
		super();  
		RSSReader reader = new RSSReader();
		newsList=reader.parse("http://voxelion.over-blog.com/rss");
	}  

	public void paintComponent(Graphics g){  
		JTextArea news = new JTextArea();
		news.setBounds(2,2,400,400);
		for(int i = 0; i < newsList.length; i++){
		    String line = newsList[i].replaceAll("\\[|\\]|,", " ");
		    news.append(line + "\n");
		}
		news.setFont(new Font("Distant Galaxy", Font.PLAIN, 20));
		news.setOpaque(false);
		news.setEditable(false);
		add(news);
	}
}
