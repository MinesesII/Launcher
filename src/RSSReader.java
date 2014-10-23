import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RSSReader {

	public String[] parse(String feedurl) {
		String[] news = new String[8];

		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			URL url = new URL(feedurl);
			Document doc = builder.parse(url.openStream());
			NodeList nodes = null;
			Element element = null;
			nodes = doc.getElementsByTagName("title");
			nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < news.length/2; i++) {
				element = (Element) nodes.item(i);
				if(nodes.item(i)!=null){
					news[i]= new String(readNode(element, "pubDate")).substring(4,16)+" : "+readNode(element, "title")  /*readNode(element, "link") + */;
				}
			} 
			for (int i = 0; i < news.length/2; i++) {
				element = (Element) nodes.item(i);
				if(nodes.item(i)!=null){
					news[i+4]= new String(readNode(element, "link"));
				}
			} 
		} catch (SAXException ex) {
			Logger.getLogger(RSSReader.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(RSSReader.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ParserConfigurationException ex) {
			Logger.getLogger(RSSReader.class.getName()).log(Level.SEVERE, null, ex);
		}
		return news;
	}

	public String readNode(Node _node, String _path) {

		String[] paths = _path.split("\\|");
		Node node = null;

		if (paths != null && paths.length > 0) {
			node = _node;

			for (int i = 0; i < paths.length; i++) {
				node = getChildByName(node, paths[i].trim());
			}
		}

		if (node != null) {
			return node.getTextContent();
		} else {
			return "";
		}
	}

	public Node getChildByName(Node _node, String _name) {
		if (_node == null) {
			return null;
		}
		NodeList listChild = _node.getChildNodes();

		if (listChild != null) {
			for (int i = 0; i < listChild.getLength(); i++) {
				Node child = listChild.item(i);
				if (child != null) {
					if ((child.getNodeName() != null && (_name.equals(child.getNodeName()))) || (child.getLocalName() != null && (_name.equals(child.getLocalName())))) {
						return child;
					}
				}
			}
		}
		return null;
	}
}