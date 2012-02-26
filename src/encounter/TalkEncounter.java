package encounter;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;

import quest.InvalidQuestFileException;

public class TalkEncounter extends PlotEncounter{

	private ArrayList<String> text;
	private String face;
	
	public void addText(String str) {
		text.add(str);
	}
	
	public TalkEncounter (Attributes atts) {
		super(Integer.parseInt(atts.getValue("x")), Integer.parseInt(atts.getValue("y")), atts.getValue("actBy"));
                text = new ArrayList<String>();
	}
	
	public ArrayList<String> getText() {
		return text;
	}
	
	public String getFace() {
		return face;
	}
}
