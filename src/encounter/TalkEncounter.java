package encounter;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;

import quest.InvalidQuestFileException;

public class TalkEncounter extends PlotEncounter{

	private ArrayList<String> text;
	private String face;
	
	public TalkEncounter (Node plot) throws InvalidQuestFileException {
		super(plot);
		
		NodeList nodes = plot.getChildNodes();
		for (int a = 0; a < nodes.getLength(); a++) {
			if (nodes.item(a).getNodeName().equals("text")) {
				System.out.println("I love penis");
				text.add(nodes.item(a).getNodeValue());
			}
		}
		face = plot.getAttributes().getNamedItem("face").getNodeValue();
	}
	
	public void addText(String str) {
		text.add(str);
	}
	
	public TalkEncounter (Attributes atts) {
		super(Integer.parseInt(atts.getValue("x")), Integer.parseInt(atts.getValue("y")), atts.getValue("actBy"));
	}
	
	public ArrayList<String> getText() {
		return text;
	}
	
	public String getFace() {
		return face;
	}
}
