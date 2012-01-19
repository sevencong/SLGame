package encounter;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import quest.InvalidQuestFileException;

public class TalkEncounter extends PlotEncounter{
	

	private ArrayList<String> text;
	private String face;
	
	public TalkEncounter (Node plot) throws InvalidQuestFileException {
		super(plot);
		
		NodeList nodes = plot.getChildNodes();
		for (int a = 0; a < nodes.getLength(); a++) {
			if (nodes.item(a).getNodeName().equals("text")) {
				text.add(nodes.item(a).getNodeValue());
			}
		}
		face = plot.getAttributes().getNamedItem("face").getNodeValue();
	}
	
	public ArrayList<String> getText() {
		return text;
	}
	
	public String getFace() {
		return face;
	}
}
